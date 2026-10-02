/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.transform.sc.transformers;

import groovyjarjarasm.asm.MethodVisitor;
import java.util.List;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ConstructorCallExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.ExpressionTransformer;
import org.codehaus.groovy.ast.expr.MapEntryExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.TupleExpression;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.classgen.AsmClassGenerator;
import org.codehaus.groovy.classgen.BytecodeExpression;
import org.codehaus.groovy.classgen.asm.BytecodeHelper;
import org.codehaus.groovy.classgen.asm.CompileStack;
import org.codehaus.groovy.classgen.asm.OperandStack;
import org.codehaus.groovy.classgen.asm.WriterController;
import org.codehaus.groovy.transform.sc.transformers.StaticCompilationTransformer;
import org.codehaus.groovy.transform.stc.StaticTypeCheckingSupport;
import org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor;
import org.codehaus.groovy.transform.stc.StaticTypesMarker;

public class ConstructorCallTransformer {
    private final StaticCompilationTransformer staticCompilationTransformer;

    public ConstructorCallTransformer(StaticCompilationTransformer staticCompilationTransformer) {
        this.staticCompilationTransformer = staticCompilationTransformer;
    }

    Expression transformConstructorCall(ConstructorCallExpression expr) {
        Expression expression;
        TupleExpression tupleExpression;
        List<Expression> expressions;
        Expression arguments;
        ConstructorNode node = (ConstructorNode)expr.getNodeMetaData((Object)StaticTypesMarker.DIRECT_METHOD_CALL_TARGET);
        if (node == null) {
            return expr;
        }
        Parameter[] params = node.getParameters();
        if ((params.length == 1 || params.length == 2) && StaticTypeCheckingSupport.implementsInterfaceOrIsSubclassOf(params[params.length - 1].getType(), ClassHelper.MAP_TYPE) && node.getCode() == StaticTypeCheckingVisitor.GENERATED_EMPTY_STATEMENT && (arguments = expr.getArguments()) instanceof TupleExpression && ((expressions = (tupleExpression = (TupleExpression)arguments).getExpressions()).size() == 1 || expressions.size() == 2) && (expression = expressions.get(expressions.size() - 1)) instanceof MapExpression) {
            MapExpression map = (MapExpression)expression;
            ClassNode declaringClass = node.getDeclaringClass();
            for (ConstructorNode constructorNode : declaringClass.getDeclaredConstructors()) {
                if (constructorNode != node) continue;
                return this.staticCompilationTransformer.superTransform(expr);
            }
            return new MapStyleConstructorCall(declaringClass, map, expr);
        }
        return this.staticCompilationTransformer.superTransform(expr);
    }

    private class MapStyleConstructorCall
    extends BytecodeExpression {
        private final MapExpression map;
        private final ConstructorCallExpression originalCall;
        private final boolean innerClassCall;
        private AsmClassGenerator acg;

        MapStyleConstructorCall(ClassNode declaringClass, MapExpression map, ConstructorCallExpression originalCall) {
            super(declaringClass);
            this.map = map;
            this.originalCall = originalCall;
            this.copyNodeMetaData(originalCall);
            this.setSourcePosition(originalCall);
            Expression originalArgs = originalCall.getArguments();
            this.innerClassCall = 2 == ((TupleExpression)originalArgs).getExpressions().size();
        }

        @Override
        public Expression transformExpression(ExpressionTransformer transformer) {
            MapStyleConstructorCall result = new MapStyleConstructorCall(this.getType(), (MapExpression)this.map.transformExpression(transformer), (ConstructorCallExpression)this.originalCall.transformExpression(transformer));
            result.copyNodeMetaData(this);
            return result;
        }

        @Override
        public void visit(GroovyCodeVisitor visitor) {
            if (visitor instanceof AsmClassGenerator) {
                this.acg = (AsmClassGenerator)visitor;
            } else {
                this.originalCall.visit(visitor);
            }
            super.visit(visitor);
        }

        @Override
        public void visit(MethodVisitor mv) {
            WriterController controller = this.acg.getController();
            CompileStack compileStack = controller.getCompileStack();
            OperandStack operandStack = controller.getOperandStack();
            ClassNode ctorType = this.getType();
            int tmpObj = compileStack.defineTemporaryVariable("tmpObj", ctorType, false);
            String ctorTypeName = BytecodeHelper.getClassInternalName(ctorType);
            mv.visitTypeInsn(187, ctorTypeName);
            mv.visitInsn(89);
            String signature = "()V";
            if (this.innerClassCall && ctorType.getOuterClass() != null) {
                this.acg.visitVariableExpression(GeneralUtils.varX("this"));
                Parameter[] params = new Parameter[]{new Parameter(ctorType.getOuterClass(), "$p$")};
                signature = BytecodeHelper.getMethodDescriptor(ClassHelper.VOID_TYPE, params);
            }
            mv.visitMethodInsn(183, ctorTypeName, "<init>", signature, false);
            mv.visitVarInsn(58, tmpObj);
            for (MapEntryExpression entryExpression : this.map.getMapEntryExpressions()) {
                Expression keyExpression = ConstructorCallTransformer.this.staticCompilationTransformer.transform(entryExpression.getKeyExpression());
                Expression valExpression = ConstructorCallTransformer.this.staticCompilationTransformer.transform(entryExpression.getValueExpression());
                Expression setExpression = GeneralUtils.assignX(GeneralUtils.propX((Expression)GeneralUtils.bytecodeX(ctorType, v -> v.visitVarInsn(25, tmpObj)), keyExpression), valExpression);
                setExpression.setSourcePosition(entryExpression);
                setExpression.visit(this.acg);
                operandStack.pop();
            }
            mv.visitVarInsn(25, tmpObj);
            compileStack.removeVar(tmpObj);
        }
    }
}

