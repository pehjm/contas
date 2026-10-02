/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.classgen.asm;

import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.MethodVisitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import org.apache.groovy.ast.tools.ExpressionUtils;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.GroovyCodeVisitorAdapter;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.ArrayExpression;
import org.codehaus.groovy.ast.expr.CastExpression;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.SpreadExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.ast.tools.ParameterUtils;
import org.codehaus.groovy.ast.tools.WideningCategories;
import org.codehaus.groovy.classgen.AsmClassGenerator;
import org.codehaus.groovy.classgen.asm.BytecodeHelper;
import org.codehaus.groovy.classgen.asm.ClosureWriter;
import org.codehaus.groovy.classgen.asm.CompileStack;
import org.codehaus.groovy.classgen.asm.MethodCaller;
import org.codehaus.groovy.classgen.asm.MethodCallerMultiAdapter;
import org.codehaus.groovy.classgen.asm.OperandStack;
import org.codehaus.groovy.classgen.asm.OptimizingStatementWriter;
import org.codehaus.groovy.classgen.asm.WriterController;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.typehandling.ShortTypeHandling;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.transform.stc.ExtensionMethodNode;
import org.codehaus.groovy.transform.stc.StaticTypeCheckingSupport;

public class InvocationWriter {
    public static final MethodCallerMultiAdapter invokeMethodOnCurrent = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeMethodOnCurrent", true, false);
    public static final MethodCallerMultiAdapter invokeMethodOnSuper = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeMethodOnSuper", true, false);
    public static final MethodCallerMultiAdapter invokeMethod = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeMethod", true, false);
    public static final MethodCallerMultiAdapter invokeStaticMethod = MethodCallerMultiAdapter.newStatic(ScriptBytecodeAdapter.class, "invokeStaticMethod", true, true);
    public static final MethodCaller invokeClosureMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "invokeClosure");
    public static final MethodCaller castToVargsArray = MethodCaller.newStatic(DefaultTypeTransformation.class, "castToVargsArray");
    private static final MethodNode CLASS_FOR_NAME_STRING = ClassHelper.CLASS_Type.getDeclaredMethod("forName", new Parameter[]{new Parameter(ClassHelper.STRING_TYPE, "name")});
    private static final MethodCaller asTypeMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "asType");
    private static final MethodCaller castToTypeMethod = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "castToType");
    private static final MethodCaller castToClassMethod = MethodCaller.newStatic(ShortTypeHandling.class, "castToClass");
    private static final MethodCaller castToStringMethod = MethodCaller.newStatic(ShortTypeHandling.class, "castToString");
    private static final MethodCaller castToEnumMethod = MethodCaller.newStatic(ShortTypeHandling.class, "castToEnum");
    private static final MethodCaller selectConstructorAndTransformArguments = MethodCaller.newStatic(ScriptBytecodeAdapter.class, "selectConstructorAndTransformArguments");
    protected final WriterController controller;

    public InvocationWriter(WriterController controller) {
        this.controller = controller;
    }

    public void makeCall(Expression origin, Expression receiver, Expression message, Expression arguments, MethodCallerMultiAdapter adapter, boolean safe, boolean spreadSafe, boolean implicitThis) {
        ClassNode sender;
        if (ExpressionUtils.isSuperExpression(receiver) || ExpressionUtils.isThisExpression(receiver) && !implicitThis) {
            sender = this.controller.getThisType();
            implicitThis = false;
            safe = false;
        } else {
            sender = this.controller.getClassNode();
        }
        this.makeCall(origin, new ClassExpression(sender), receiver, message, arguments, adapter, safe, spreadSafe, implicitThis);
    }

    protected void makeCall(Expression origin, ClassExpression sender, Expression receiver, Expression message, Expression arguments, MethodCallerMultiAdapter adapter, boolean safe, boolean spreadSafe, boolean implicitThis) {
        boolean containsSpreadExpression = AsmClassGenerator.containsSpreadExpression(arguments);
        if (!this.makeDirectCall(origin, receiver, message, arguments, adapter, implicitThis, containsSpreadExpression) && !this.makeCachedCall(origin, sender, receiver, message, arguments, adapter, safe, spreadSafe, implicitThis, containsSpreadExpression)) {
            this.makeUncachedCall(origin, sender, receiver, message, arguments, adapter, safe, spreadSafe, implicitThis, containsSpreadExpression);
        }
    }

    protected boolean writeDirectMethodCall(MethodNode target, boolean implicitThis, Expression receiver, TupleExpression args) {
        ClassNode enclosingClass;
        if (target == null || target instanceof ExtensionMethodNode) {
            return false;
        }
        ClassNode declaringClass = target.getDeclaringClass();
        ClassNode receiverType = enclosingClass = this.controller.getClassNode();
        if (receiver != null) {
            receiverType = this.controller.getTypeChooser().resolveType(receiver, enclosingClass);
            if (target.isStatic() && StaticTypeCheckingSupport.isClassClassNodeWrappingConcreteType(receiverType)) {
                receiverType = receiverType.getGenericsTypes()[0].getType();
            }
        }
        CompileStack compileStack = this.controller.getCompileStack();
        OperandStack operandStack = this.controller.getOperandStack();
        MethodVisitor mv = this.controller.getMethodVisitor();
        int startDepth = operandStack.getStackLength();
        if (!target.isStatic()) {
            if (receiver != null) {
                Expression objectExpression = receiver;
                if (implicitThis && enclosingClass.getOuterClass() != null && !enclosingClass.isDerivedFrom(declaringClass) && !enclosingClass.implementsInterface(declaringClass)) {
                    compileStack.pushImplicitThis(false);
                    if (!this.controller.isInGeneratedFunction() && InvocationWriter.isThis(receiver)) {
                        objectExpression = new PropertyExpression((Expression)new ClassExpression(declaringClass), "this");
                    }
                } else {
                    compileStack.pushImplicitThis(implicitThis);
                }
                objectExpression.visit(this.controller.getAcg());
                operandStack.doGroovyCast(declaringClass);
                compileStack.popImplicitThis();
            } else {
                mv.visitIntInsn(25, 0);
                operandStack.push(enclosingClass);
            }
        }
        int opcode = target.isStatic() ? 184 : (ExpressionUtils.isSuperExpression(receiver) ? 183 : (declaringClass.isInterface() ? 185 : 182));
        ClassNode ownerClass = declaringClass;
        if (opcode == 183) {
            if (!declaringClass.isInterface() || receiverType.implementsInterface(declaringClass)) {
                ownerClass = receiverType;
            }
        } else if (opcode != 182 || !ClassHelper.isObjectType(declaringClass)) {
            if (!(opcode != 182 || receiverType.isArray() || receiverType.isInterface() || ClassHelper.isPrimitiveType(receiverType) || receiverType.equals(declaringClass) || !receiverType.isDerivedFrom(declaringClass))) {
                ownerClass = receiverType;
                if (!receiverType.equals(operandStack.getTopOperand())) {
                    mv.visitTypeInsn(192, BytecodeHelper.getClassInternalName(ownerClass));
                }
            } else if ((declaringClass.getModifiers() & 0x11) == 0 && !receiverType.equals(declaringClass) && (declaringClass.isInterface() ? receiverType.implementsInterface(declaringClass) : receiverType.isDerivedFrom(declaringClass))) {
                if (declaringClass.isInterface() && !receiverType.isInterface()) {
                    opcode = 182;
                }
                ownerClass = receiverType;
            }
        }
        this.loadArguments(args.getExpressions(), target.getParameters());
        String ownerName = BytecodeHelper.getClassInternalName(ownerClass);
        String methodName = target.getName();
        String signature = BytecodeHelper.getMethodDescriptor(target.getReturnType(), target.getParameters());
        mv.visitMethodInsn(opcode, ownerName, methodName, signature, ownerClass.isInterface());
        ClassNode returnType = target.getReturnType();
        if (ClassHelper.isPrimitiveVoid(returnType)) {
            returnType = ClassHelper.OBJECT_TYPE;
            mv.visitInsn(1);
        }
        operandStack.replace(returnType, operandStack.getStackLength() - startDepth);
        return true;
    }

    private static boolean isThis(Expression expression) {
        final boolean[] isThis = new boolean[1];
        expression.visit(new GroovyCodeVisitorAdapter(){

            @Override
            public void visitVariableExpression(VariableExpression vexp) {
                isThis[0] = vexp.isThisExpression();
            }
        });
        return isThis[0];
    }

    private boolean isArray(Expression expression) {
        if (ExpressionUtils.isNullConstant(expression)) {
            return true;
        }
        ClassNode type = this.controller.getTypeChooser().resolveType(expression, this.controller.getClassNode());
        return type.isArray();
    }

    protected void loadArguments(List<Expression> arguments, Parameter[] parameters) {
        int n;
        if (parameters.length == 0) {
            return;
        }
        int nthParameter = parameters.length - 1;
        ClassNode lastType = parameters[nthParameter].getOriginType();
        OperandStack operandStack = this.controller.getOperandStack();
        int expected = operandStack.getStackLength() + arguments.size();
        boolean varg = lastType.isArray() && (arguments.size() > parameters.length || arguments.size() == parameters.length - 1 || !arguments.isEmpty() && !this.isArray(DefaultGroovyMethods.last(arguments)));
        int n2 = n = varg ? nthParameter : arguments.size();
        for (int i = 0; i < n; ++i) {
            Expression argument = arguments.get(i);
            argument.visit(this.controller.getAcg());
            if (ExpressionUtils.isNullConstant(argument)) continue;
            operandStack.doGroovyCast(parameters[i].getType());
        }
        if (varg) {
            List<Expression> lastArgs = arguments.subList(nthParameter, arguments.size());
            Expression array = new ArrayExpression(lastType.getComponentType(), lastArgs);
            if (lastArgs.size() == 1) {
                Expression lastExpr = lastArgs.get(0);
                array = GeneralUtils.ternaryX(GeneralUtils.isNullOrInstanceOfX(lastExpr, lastType), GeneralUtils.castX(lastType, lastExpr), array);
            }
            ((ASTNode)array).visit(this.controller.getAcg());
            while (operandStack.getStackLength() < expected) {
                operandStack.push(ClassHelper.OBJECT_TYPE);
            }
            if (arguments.size() == nthParameter) {
                operandStack.remove(1);
            }
        }
    }

    protected boolean makeDirectCall(Expression origin, Expression receiver, Expression message, Expression arguments, MethodCallerMultiAdapter adapter, boolean implicitThis, boolean containsSpreadExpression) {
        MethodCallExpression mce;
        TupleExpression args;
        OptimizingStatementWriter.StatementMeta meta;
        String methodName;
        if (this.makeClassForNameCall(origin, receiver, message, arguments)) {
            return true;
        }
        if ((this.controller.optimizeForInt && this.controller.isFastPath() && adapter == invokeMethodOnCurrent || adapter == invokeStaticMethod) && (methodName = this.getMethodName(message)) != null && (meta = (OptimizingStatementWriter.StatementMeta)origin.getNodeMetaData(OptimizingStatementWriter.StatementMeta.class)) != null && meta.target != null && this.writeDirectMethodCall(meta.target, true, null, args = arguments instanceof TupleExpression ? (TupleExpression)arguments : new TupleExpression(receiver))) {
            return true;
        }
        if (containsSpreadExpression) {
            return false;
        }
        if (origin instanceof MethodCallExpression && (mce = (MethodCallExpression)origin).getMethodTarget() != null) {
            return this.writeDirectMethodCall(mce.getMethodTarget(), implicitThis, receiver, InvocationWriter.makeArgumentList(arguments));
        }
        return false;
    }

    protected boolean makeCachedCall(Expression origin, ClassExpression sender, Expression receiver, Expression message, Expression arguments, MethodCallerMultiAdapter adapter, boolean safe, boolean spreadSafe, boolean implicitThis, boolean containsSpreadExpression) {
        String methodName;
        if (!(adapter != invokeMethod && adapter != invokeMethodOnCurrent && adapter != invokeStaticMethod || spreadSafe || (methodName = this.getMethodName(message)) == null)) {
            this.controller.getCallSiteWriter().makeCallSite(receiver, methodName, arguments, safe, implicitThis, adapter == invokeMethodOnCurrent, adapter == invokeStaticMethod);
            return true;
        }
        return false;
    }

    protected void makeUncachedCall(Expression origin, ClassExpression sender, Expression receiver, Expression message, Expression arguments, MethodCallerMultiAdapter adapter, boolean safe, boolean spreadSafe, boolean implicitThis, boolean containsSpreadExpression) {
        int numberOfArguments;
        CompileStack compileStack = this.controller.getCompileStack();
        OperandStack operandStack = this.controller.getOperandStack();
        AsmClassGenerator acg = this.controller.getAcg();
        compileStack.pushLHS(false);
        if (adapter == AsmClassGenerator.setProperty) {
            ConstantExpression.NULL.visit(acg);
        } else {
            sender.visit(acg);
        }
        String methodName = this.getMethodName(message);
        if (adapter == invokeMethodOnSuper && methodName != null) {
            this.controller.getSuperMethodNames().add(methodName);
        }
        compileStack.pushImplicitThis(implicitThis);
        receiver.visit(acg);
        operandStack.box();
        compileStack.popImplicitThis();
        int operandsToRemove = 2;
        if (message != null) {
            message.visit(acg);
            operandStack.box();
            ++operandsToRemove;
        }
        int n = numberOfArguments = containsSpreadExpression ? -1 : AsmClassGenerator.argumentSize(arguments);
        if (numberOfArguments > 0 || containsSpreadExpression) {
            ArgumentListExpression ae = InvocationWriter.makeArgumentList(arguments);
            if (containsSpreadExpression) {
                acg.despreadList(ae.getExpressions(), true);
            } else {
                ae.visit(acg);
            }
        } else if (numberOfArguments > 0) {
            operandsToRemove += numberOfArguments;
            TupleExpression te = (TupleExpression)arguments;
            for (int i = 0; i < numberOfArguments; ++i) {
                Expression argument = te.getExpression(i);
                argument.visit(acg);
                operandStack.box();
                if (!(argument instanceof CastExpression)) continue;
                acg.loadWrapper(argument);
            }
        }
        if (adapter == null) {
            adapter = invokeMethod;
        }
        adapter.call(this.controller.getMethodVisitor(), numberOfArguments, safe, spreadSafe);
        compileStack.popLHS();
        operandStack.replace(ClassHelper.OBJECT_TYPE, operandsToRemove);
    }

    protected boolean makeClassForNameCall(Expression origin, Expression receiver, Expression message, Expression arguments) {
        if (!(receiver instanceof ClassExpression)) {
            return false;
        }
        ClassExpression ce = (ClassExpression)receiver;
        if (!ClassHelper.CLASS_Type.equals(ce.getType())) {
            return false;
        }
        String msg = this.getMethodName(message);
        if (!"forName".equals(msg)) {
            return false;
        }
        ArgumentListExpression ae = InvocationWriter.makeArgumentList(arguments);
        if (ae.getExpressions().size() != 1) {
            return false;
        }
        return this.writeDirectMethodCall(CLASS_FOR_NAME_STRING, false, receiver, ae);
    }

    public static ArgumentListExpression makeArgumentList(Expression arguments) {
        ArgumentListExpression ae;
        if (arguments instanceof ArgumentListExpression) {
            ae = (ArgumentListExpression)arguments;
        } else if (arguments instanceof TupleExpression) {
            TupleExpression te = (TupleExpression)arguments;
            ae = new ArgumentListExpression(te.getExpressions());
        } else {
            ae = new ArgumentListExpression();
            ae.addExpression(arguments);
        }
        return ae;
    }

    protected String getMethodName(Expression message) {
        Expression methodExpr;
        CastExpression msg;
        String methodName = null;
        if (message instanceof CastExpression && ClassHelper.isStringType((msg = (CastExpression)message).getType()) && (methodExpr = msg.getExpression()) instanceof ConstantExpression) {
            methodName = methodExpr.getText();
        }
        if (methodName == null && message instanceof ConstantExpression) {
            ConstantExpression constantExpression = (ConstantExpression)message;
            methodName = constantExpression.getText();
        }
        return methodName;
    }

    public void writeInvokeMethod(MethodCallExpression call) {
        if (this.isClosureCall(call)) {
            this.invokeClosure(call.getArguments(), call.getMethodAsString());
        } else {
            if (InvocationWriter.isFunctionInterfaceCall(call)) {
                call = InvocationWriter.transformToRealMethodCall(call);
            }
            Expression receiver = call.getObjectExpression();
            MethodCallerMultiAdapter adapter = invokeMethod;
            if (ExpressionUtils.isSuperExpression(receiver)) {
                adapter = invokeMethodOnSuper;
            } else if (ExpressionUtils.isThisExpression(receiver)) {
                adapter = invokeMethodOnCurrent;
            }
            if (this.isStaticInvocation(call)) {
                adapter = invokeStaticMethod;
            }
            CastExpression messageName = new CastExpression(ClassHelper.STRING_TYPE, call.getMethod());
            this.makeCall(call, receiver, messageName, call.getArguments(), adapter, call.isSafe(), call.isSpreadSafe(), call.isImplicitThis());
        }
    }

    private static boolean isFunctionInterfaceCall(MethodCallExpression call) {
        Expression objectExpression;
        if ("call".equals(call.getMethodAsString()) && !ExpressionUtils.isThisExpression(objectExpression = call.getObjectExpression())) {
            return ClassHelper.isFunctionalInterface(objectExpression.getType());
        }
        return false;
    }

    private static MethodCallExpression transformToRealMethodCall(MethodCallExpression call) {
        ClassNode type = call.getObjectExpression().getType();
        MethodNode methodNode = ClassHelper.findSAM(type);
        call = (MethodCallExpression)call.transformExpression(expression -> {
            if (!(expression instanceof ConstantExpression)) {
                return expression;
            }
            return new ConstantExpression(methodNode.getName());
        });
        call.setMethodTarget(methodNode);
        return call;
    }

    private boolean isClosureCall(MethodCallExpression call) {
        ClassNode classNode = this.controller.getClassNode();
        String methodName = call.getMethodAsString();
        if (methodName == null) {
            return false;
        }
        if (!call.isImplicitThis()) {
            return false;
        }
        if (!ExpressionUtils.isThisExpression(call.getObjectExpression())) {
            return false;
        }
        FieldNode field = classNode.getDeclaredField(methodName);
        if (field == null) {
            return false;
        }
        if (this.isStaticInvocation(call) && !field.isStatic()) {
            return false;
        }
        Expression arguments = call.getArguments();
        return !classNode.hasPossibleMethod(methodName, arguments);
    }

    private void invokeClosure(Expression arguments, String methodName) {
        AsmClassGenerator acg = this.controller.getAcg();
        acg.visitVariableExpression(new VariableExpression(methodName));
        this.controller.getOperandStack().box();
        if (arguments instanceof TupleExpression) {
            arguments.visit(acg);
        } else {
            new TupleExpression(arguments).visit(acg);
        }
        invokeClosureMethod.call(this.controller.getMethodVisitor());
        this.controller.getOperandStack().replace(ClassHelper.OBJECT_TYPE);
    }

    private boolean isStaticInvocation(MethodCallExpression call) {
        if (!ExpressionUtils.isThisExpression(call.getObjectExpression())) {
            return false;
        }
        if (this.controller.isStaticMethod()) {
            return true;
        }
        return this.controller.isStaticContext() && !call.isImplicitThis();
    }

    public void writeInvokeStaticMethod(StaticMethodCallExpression call) {
        ClassExpression receiver = new ClassExpression(call.getOwnerType());
        ConstantExpression messageName = new ConstantExpression(call.getMethod());
        this.makeCall(call, receiver, messageName, call.getArguments(), invokeStaticMethod, false, false, false);
    }

    public void writeInvokeConstructor(ConstructorCallExpression call) {
        if (this.writeDirectConstructorCall(call)) {
            return;
        }
        if (this.writeAICCall(call)) {
            return;
        }
        this.writeNormalConstructorCall(call);
    }

    private boolean writeDirectConstructorCall(ConstructorCallExpression call) {
        ConstructorNode ctor;
        if (!this.controller.isFastPath()) {
            return false;
        }
        OptimizingStatementWriter.StatementMeta meta = (OptimizingStatementWriter.StatementMeta)call.getNodeMetaData(OptimizingStatementWriter.StatementMeta.class);
        ConstructorNode constructorNode = ctor = meta != null ? (ConstructorNode)meta.target : null;
        if (ctor == null) {
            return false;
        }
        List<Expression> args = InvocationWriter.makeArgumentList(call.getArguments()).getExpressions();
        this.loadArguments(args, ctor.getParameters());
        String ownerDescriptor = this.prepareConstructorCall(ctor);
        this.finnishConstructorCall(ctor, ownerDescriptor, args.size());
        return true;
    }

    protected String prepareConstructorCall(ConstructorNode cn) {
        String type = BytecodeHelper.getClassInternalName(cn.getDeclaringClass());
        MethodVisitor mv = this.controller.getMethodVisitor();
        mv.visitTypeInsn(187, type);
        mv.visitInsn(89);
        return type;
    }

    protected void finnishConstructorCall(ConstructorNode cn, String ownerDescriptor, int argsToRemove) {
        String signature = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, cn.getParameters());
        MethodVisitor mv = this.controller.getMethodVisitor();
        mv.visitMethodInsn(183, ownerDescriptor, "<init>", signature, false);
        this.controller.getOperandStack().remove(argsToRemove);
        this.controller.getOperandStack().push(cn.getDeclaringClass());
    }

    protected void writeNormalConstructorCall(ConstructorCallExpression call) {
        TupleExpression tupleExpression;
        int size;
        Expression arguments = call.getArguments();
        if (arguments instanceof TupleExpression && (size = (tupleExpression = (TupleExpression)arguments).getExpressions().size()) == 0) {
            arguments = MethodCallExpression.NO_ARGUMENTS;
        }
        ClassExpression receiver = new ClassExpression(call.getType());
        this.controller.getCallSiteWriter().makeCallSite(receiver, "<$constructor$>", arguments, false, false, false, false);
    }

    protected boolean writeAICCall(ConstructorCallExpression call) {
        if (!call.isUsingAnonymousInnerClass()) {
            return false;
        }
        ConstructorNode ctor = call.getType().getDeclaredConstructors().get(0);
        String ownerDescriptor = this.prepareConstructorCall(ctor);
        List<Expression> args = InvocationWriter.makeArgumentList(call.getArguments()).getExpressions();
        this.controller.getCompileStack().pushImplicitThis(true);
        int i = 0;
        Parameter[] params = ctor.getParameters();
        for (Expression arg : args) {
            Parameter p = params[Math.min(i++, params.length)];
            if (arg instanceof VariableExpression) {
                VariableExpression var = (VariableExpression)arg;
                this.loadVariableWithReference(var);
            } else {
                arg.visit(this.controller.getAcg());
            }
            this.controller.getOperandStack().doGroovyCast(p.getType());
        }
        this.controller.getCompileStack().popImplicitThis();
        this.finnishConstructorCall(ctor, ownerDescriptor, args.size());
        return true;
    }

    private void loadVariableWithReference(VariableExpression var) {
        if (!var.isUseReferenceDirectly()) {
            var.visit(this.controller.getAcg());
        } else {
            ClosureWriter.loadReference(var.getName(), this.controller);
        }
    }

    public final void makeSingleArgumentCall(Expression receiver, String message, Expression arguments) {
        this.makeSingleArgumentCall(receiver, message, arguments, false);
    }

    public void makeSingleArgumentCall(Expression receiver, String message, Expression arguments, boolean safe) {
        this.controller.getCallSiteWriter().makeSingleArgumentCall(receiver, message, arguments, safe);
    }

    public void writeSpecialConstructorCall(ConstructorCallExpression call) {
        this.controller.getCompileStack().pushInSpecialConstructorCall();
        if (!this.controller.getClosureWriter().addGeneratedClosureConstructorCall(call)) {
            List<ConstructorNode> constructors;
            ClassNode callType = this.controller.getClassNode();
            if (call.isSuperCall()) {
                callType = callType.getSuperClass();
            }
            if (!this.makeDirectConstructorCall(constructors = InvocationWriter.sortConstructors(call, callType), call, callType)) {
                this.makeMOPBasedConstructorCall(constructors, call, callType);
            }
        }
        this.controller.getCompileStack().pop();
    }

    private static List<ConstructorNode> sortConstructors(ConstructorCallExpression call, ClassNode callType) {
        ArrayList<ConstructorNode> constructors = new ArrayList<ConstructorNode>(callType.getDeclaredConstructors());
        constructors.sort((c0, c1) -> {
            String descriptor0 = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, c0.getParameters());
            String descriptor1 = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, c1.getParameters());
            return descriptor0.compareTo(descriptor1);
        });
        return constructors;
    }

    private boolean makeDirectConstructorCall(List<ConstructorNode> constructors, ConstructorCallExpression call, ClassNode callType) {
        List<Expression> argumentList;
        if (!this.controller.isConstructor()) {
            return false;
        }
        Expression arguments = call.getArguments();
        if (arguments instanceof TupleExpression) {
            argumentList = ((TupleExpression)arguments).getExpressions();
        } else {
            argumentList = new ArrayList<Expression>();
            argumentList.add(arguments);
        }
        for (Expression argument : argumentList) {
            if (!(argument instanceof SpreadExpression)) continue;
            return false;
        }
        ConstructorNode ctor = null;
        ConstructorNode varg = null;
        int nArguments = argumentList.size();
        for (ConstructorNode constructor : constructors) {
            int nParameters = constructor.getParameters().length;
            if (nArguments == nParameters) {
                if (ctor == null) {
                    ctor = constructor;
                    continue;
                }
                return false;
            }
            if (!ParameterUtils.isVargs(constructor.getParameters()) || nArguments != nParameters - 1 && nArguments <= nParameters) continue;
            if (varg == null) {
                varg = constructor;
                continue;
            }
            return false;
        }
        if (ctor == null) {
            ctor = varg;
        }
        if (ctor == null) {
            return false;
        }
        MethodVisitor mv = this.controller.getMethodVisitor();
        OperandStack operandStack = this.controller.getOperandStack();
        mv.visitVarInsn(25, 0);
        int mark = operandStack.getStackLength();
        this.loadArguments(argumentList, ctor.getParameters());
        operandStack.remove(operandStack.getStackLength() - mark);
        String descriptor = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, ctor.getParameters());
        mv.visitMethodInsn(183, BytecodeHelper.getClassInternalName(callType), "<init>", descriptor, false);
        return true;
    }

    private void makeMOPBasedConstructorCall(List<ConstructorNode> constructors, ConstructorCallExpression call, ClassNode callNode) {
        MethodVisitor mv = this.controller.getMethodVisitor();
        OperandStack operandStack = this.controller.getOperandStack();
        call.getArguments().visit(this.controller.getAcg());
        mv.visitInsn(89);
        BytecodeHelper.pushConstant(mv, -1);
        this.controller.getAcg().visitClassExpression(new ClassExpression(callNode));
        operandStack.remove(1);
        selectConstructorAndTransformArguments.call(mv);
        if (this.controller.isConstructor()) {
            mv.visitVarInsn(25, 0);
        } else {
            mv.visitTypeInsn(187, BytecodeHelper.getClassInternalName(callNode));
        }
        mv.visitInsn(95);
        TreeMap<Integer, ConstructorNode> sortedConstructors = new TreeMap<Integer, ConstructorNode>();
        for (ConstructorNode constructor : constructors) {
            String typeDescriptor = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, constructor.getParameters());
            int hash = BytecodeHelper.hashCode(typeDescriptor);
            ConstructorNode sameHashNode = sortedConstructors.put(hash, constructor);
            if (sameHashNode == null) continue;
            this.controller.getSourceUnit().addError(new SyntaxException("Unable to compile class " + this.controller.getClassNode().getName() + " due to hash collision in constructors", call.getLineNumber(), call.getColumnNumber()));
        }
        Label[] targets = new Label[constructors.size()];
        int[] indices = new int[constructors.size()];
        Iterator hashIt = sortedConstructors.keySet().iterator();
        Iterator constructorIt = sortedConstructors.values().iterator();
        int n = targets.length;
        for (int i = 0; i < n; ++i) {
            targets[i] = new Label();
            indices[i] = (Integer)hashIt.next();
        }
        Label defaultLabel = new Label();
        Label afterSwitch = new Label();
        mv.visitLookupSwitchInsn(defaultLabel, indices, targets);
        for (Label target : targets) {
            mv.visitLabel(target);
            if (this.controller.isConstructor()) {
                mv.visitInsn(95);
                mv.visitInsn(90);
            } else {
                mv.visitInsn(90);
                mv.visitInsn(93);
                mv.visitInsn(87);
            }
            ConstructorNode cn = (ConstructorNode)constructorIt.next();
            String descriptor = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, cn.getParameters());
            Parameter[] parameters = cn.getParameters();
            int lengthWithoutVargs = parameters.length;
            if (parameters.length > 0 && parameters[parameters.length - 1].getType().isArray()) {
                --lengthWithoutVargs;
            }
            for (int p = 0; p < lengthWithoutVargs; ++p) {
                InvocationWriter.loadAndCastElement(operandStack, mv, parameters, p);
            }
            if (parameters.length > lengthWithoutVargs) {
                ClassNode type = parameters[lengthWithoutVargs].getType();
                BytecodeHelper.pushConstant(mv, lengthWithoutVargs);
                this.controller.getAcg().visitClassExpression(new ClassExpression(type));
                operandStack.remove(1);
                castToVargsArray.call(mv);
                BytecodeHelper.doCast(mv, type);
            } else {
                mv.visitInsn(87);
            }
            mv.visitMethodInsn(183, BytecodeHelper.getClassInternalName(callNode), "<init>", descriptor, false);
            mv.visitJumpInsn(167, afterSwitch);
        }
        mv.visitLabel(defaultLabel);
        mv.visitTypeInsn(187, "java/lang/IllegalArgumentException");
        mv.visitInsn(89);
        mv.visitLdcInsn("This class has been compiled with a super class which is binary incompatible with the current super class found on classpath. You should recompile this class with the new version.");
        mv.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
        mv.visitInsn(191);
        mv.visitLabel(afterSwitch);
        if (!this.controller.isConstructor()) {
            mv.visitInsn(95);
            operandStack.push(callNode);
        }
        mv.visitInsn(87);
    }

    private static void loadAndCastElement(OperandStack operandStack, MethodVisitor mv, Parameter[] parameters, int p) {
        operandStack.push(ClassHelper.OBJECT_TYPE);
        mv.visitInsn(89);
        BytecodeHelper.pushConstant(mv, p);
        mv.visitInsn(50);
        operandStack.push(ClassHelper.OBJECT_TYPE);
        ClassNode type = parameters[p].getType();
        operandStack.doGroovyCast(type);
        operandStack.swap();
        operandStack.remove(2);
    }

    public void castToNonPrimitiveIfNecessary(ClassNode sourceType, ClassNode targetType) {
        OperandStack os = this.controller.getOperandStack();
        ClassNode boxedType = os.box();
        if (WideningCategories.implementsInterfaceOrSubclassOf(boxedType, targetType)) {
            return;
        }
        MethodVisitor mv = this.controller.getMethodVisitor();
        if (ClassHelper.isClassType(targetType)) {
            castToClassMethod.call(mv);
        } else if (ClassHelper.isStringType(targetType)) {
            castToStringMethod.call(mv);
        } else if (targetType.isDerivedFrom(ClassHelper.Enum_Type)) {
            new ClassExpression(targetType).visit(this.controller.getAcg());
            os.remove(1);
            castToEnumMethod.call(mv);
            BytecodeHelper.doCast(mv, targetType);
        } else {
            new ClassExpression(targetType).visit(this.controller.getAcg());
            os.remove(1);
            castToTypeMethod.call(mv);
        }
    }

    public void castNonPrimitiveToBool(ClassNode last) {
        MethodVisitor mv = this.controller.getMethodVisitor();
        BytecodeHelper.unbox(mv, ClassHelper.boolean_TYPE);
    }

    public void coerce(ClassNode from, ClassNode target) {
        if (from.isDerivedFrom(target)) {
            return;
        }
        MethodVisitor mv = this.controller.getMethodVisitor();
        OperandStack os = this.controller.getOperandStack();
        os.box();
        new ClassExpression(target).visit(this.controller.getAcg());
        os.remove(1);
        asTypeMethod.call(mv);
        BytecodeHelper.doCast(mv, target);
        os.replace(target);
    }
}

