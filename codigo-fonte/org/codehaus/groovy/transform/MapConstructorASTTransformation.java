/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.transform;

import groovy.lang.GroovyClassLoader;
import groovy.transform.CompilationUnitAware;
import groovy.transform.MapConstructor;
import groovy.transform.options.PropertyHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.groovy.ast.tools.ClassNodeUtils;
import org.apache.groovy.ast.tools.VisibilityUtils;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassCodeExpressionTransformer;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ConstructorNode;
import org.codehaus.groovy.ast.DynamicVariable;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.transform.ImmutableASTTransformation;

@GroovyASTTransformation(phase=CompilePhase.CANONICALIZATION)
public class MapConstructorASTTransformation
extends AbstractASTTransformation
implements CompilationUnitAware {
    private CompilationUnit compilationUnit;
    static final Class<?> MY_CLASS = MapConstructor.class;
    static final ClassNode MY_TYPE = ClassHelper.make(MY_CLASS);
    static final String MY_TYPE_NAME = "@" + MY_TYPE.getNameWithoutPackage();
    private static final ClassNode MAP_TYPE = ClassHelper.MAP_TYPE.getPlainNodeReference();
    private static final ClassNode LHMAP_TYPE = ClassHelper.makeWithoutCaching(LinkedHashMap.class, false);

    @Override
    public String getAnnotationName() {
        return MY_TYPE_NAME;
    }

    @Override
    public void setCompilationUnit(CompilationUnit unit) {
        this.compilationUnit = unit;
    }

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        this.init(nodes, source);
        AnnotatedNode parent = (AnnotatedNode)nodes[1];
        AnnotationNode anno = (AnnotationNode)nodes[0];
        if (!MY_TYPE.equals(anno.getClassNode())) {
            return;
        }
        if (parent instanceof ClassNode) {
            ClassNode cNode = (ClassNode)parent;
            if (!this.checkNotInterface(cNode, MY_TYPE_NAME)) {
                return;
            }
            boolean includeFields = this.memberHasValue(anno, "includeFields", Boolean.TRUE);
            boolean includeProperties = !this.memberHasValue(anno, "includeProperties", Boolean.FALSE);
            boolean includeSuperProperties = this.memberHasValue(anno, "includeSuperProperties", Boolean.TRUE);
            boolean includeSuperFields = this.memberHasValue(anno, "includeSuperFields", Boolean.TRUE);
            boolean includeStatic = this.memberHasValue(anno, "includeStatic", Boolean.TRUE);
            boolean allProperties = this.memberHasValue(anno, "allProperties", Boolean.TRUE);
            boolean noArg = this.memberHasValue(anno, "noArg", Boolean.TRUE);
            boolean specialNamedArgHandling = !this.memberHasValue(anno, "specialNamedArgHandling", Boolean.FALSE);
            List<String> excludes = MapConstructorASTTransformation.getMemberStringList(anno, "excludes");
            List<String> includes = MapConstructorASTTransformation.getMemberStringList(anno, "includes");
            boolean allNames = this.memberHasValue(anno, "allNames", Boolean.TRUE);
            if (!this.checkIncludeExcludeUndefinedAware(anno, excludes, includes, MY_TYPE_NAME)) {
                return;
            }
            if (!this.checkPropertyList(cNode, includes, "includes", anno, MY_TYPE_NAME, includeFields, includeSuperProperties, allProperties)) {
                return;
            }
            if (!this.checkPropertyList(cNode, excludes, "excludes", anno, MY_TYPE_NAME, includeFields, includeSuperProperties, allProperties)) {
                return;
            }
            GroovyClassLoader classLoader = this.compilationUnit != null ? this.compilationUnit.getTransformLoader() : source.getClassLoader();
            PropertyHandler handler = PropertyHandler.createPropertyHandler(this, classLoader, cNode);
            if (handler == null) {
                return;
            }
            if (!handler.validateAttributes(this, anno)) {
                return;
            }
            Expression pre = anno.getMember("pre");
            if (pre != null && !(pre instanceof ClosureExpression)) {
                this.addError("Expected closure value for annotation parameter 'pre'. Found " + pre, cNode);
                return;
            }
            Expression post = anno.getMember("post");
            if (post != null && !(post instanceof ClosureExpression)) {
                this.addError("Expected closure value for annotation parameter 'post'. Found " + post, cNode);
                return;
            }
            MapConstructorASTTransformation.createConstructors(this, anno, handler, cNode, includeFields, includeProperties, includeSuperProperties, includeSuperFields, noArg, allNames, allProperties, specialNamedArgHandling, includeStatic, excludes, includes, (ClosureExpression)pre, (ClosureExpression)post, source);
            if (pre != null) {
                anno.setMember("pre", new ClosureExpression(Parameter.EMPTY_ARRAY, EmptyStatement.INSTANCE));
            }
            if (post != null) {
                anno.setMember("post", new ClosureExpression(Parameter.EMPTY_ARRAY, EmptyStatement.INSTANCE));
            }
        }
    }

    private static void createConstructors(AbstractASTTransformation xform, AnnotationNode anno, PropertyHandler handler, ClassNode cNode, boolean includeFields, boolean includeProperties, boolean includeSuperProperties, boolean includeSuperFields, boolean noArg, boolean allNames, boolean allProperties, boolean specialNamedArgHandling, boolean includeStatic, List<String> excludes, List<String> includes, ClosureExpression pre, ClosureExpression post, SourceUnit source) {
        cNode.getDeclaredConstructors().removeIf(next -> next.getFirstStatement() == null);
        boolean includePseudoGetters = false;
        boolean includePseudoSetters = allProperties;
        boolean skipReadOnly = false;
        HashSet<String> names = new HashSet<String>();
        List<PropertyNode> properties = includeSuperProperties || includeSuperFields ? GeneralUtils.getAllProperties(names, cNode, cNode.getSuperClass(), includeSuperProperties, includeSuperFields, includePseudoGetters, includePseudoSetters, true, skipReadOnly, false, allNames, includeStatic) : new ArrayList<PropertyNode>();
        properties.addAll(GeneralUtils.getAllProperties(names, cNode, cNode, includeProperties, includeFields, includePseudoGetters, includePseudoSetters, false, skipReadOnly, false, allNames, includeStatic));
        BlockStatement body = new BlockStatement();
        ClassCodeExpressionTransformer transformer = MapConstructorASTTransformation.makeMapTypedArgsTransformer(source);
        if (pre != null) {
            ClosureExpression transformed = (ClosureExpression)transformer.transform(pre);
            GeneralUtils.copyStatementsWithSuperAdjustment(transformed, body);
        }
        if (!handler.validateProperties(xform, body, cNode, properties)) {
            return;
        }
        BlockStatement inner = new BlockStatement();
        Parameter map = new Parameter(MAP_TYPE, "args");
        boolean specialNamedArgsCase = specialNamedArgHandling && ImmutableASTTransformation.isSpecialNamedArgCase(properties, true);
        MapConstructorASTTransformation.createInitializers(xform, anno, cNode, handler, allNames, excludes, includes, properties, map, inner);
        if (specialNamedArgsCase) {
            map = new Parameter(LHMAP_TYPE, "args");
        }
        body.addStatement(inner);
        if (post != null) {
            ClosureExpression transformed = (ClosureExpression)transformer.transform(post);
            body.addStatement(transformed.getCode());
        }
        int modifiers = VisibilityUtils.getVisibility(anno, cNode, ConstructorNode.class, 1);
        MapConstructorASTTransformation.createConstructors(cNode, modifiers, map, body, noArg && !properties.isEmpty());
    }

    private static void createConstructors(final ClassNode cNode, int mods, final Parameter args, Statement body, boolean noArg) {
        Parameter[] parameters = new Parameter[]{args};
        if (cNode.getDeclaredConstructor(parameters) == null) {
            ConstructorNode ctor = ClassNodeUtils.addGeneratedConstructor(cNode, mods, parameters, ClassNode.EMPTY_ARRAY, body);
            ClassCodeVisitorSupport variableExpressionFix = new ClassCodeVisitorSupport(){

                @Override
                public void visitVariableExpression(VariableExpression expression) {
                    super.visitVariableExpression(expression);
                    if ("args".equals(expression.getName())) {
                        expression.setAccessedVariable(args);
                    }
                }

                @Override
                protected SourceUnit getSourceUnit() {
                    return cNode.getModule().getContext();
                }
            };
            variableExpressionFix.visitConstructor(ctor);
        }
        if (noArg && !ClassNodeUtils.hasNoArgConstructor(cNode)) {
            ClassNodeUtils.addGeneratedConstructor(cNode, mods, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, GeneralUtils.stmt(GeneralUtils.ctorThisX(GeneralUtils.args(new MapExpression()))));
        }
    }

    private static void createInitializers(AbstractASTTransformation xform, AnnotationNode aNode, ClassNode cNode, PropertyHandler handler, boolean allNames, List<String> excludes, List<String> includes, List<PropertyNode> list, Parameter map, BlockStatement block) {
        for (PropertyNode pNode : list) {
            Statement propInit;
            String name = pNode.getName();
            if (MapConstructorASTTransformation.shouldSkipUndefinedAware(name, excludes, includes, allNames) || (propInit = handler.createPropInit(xform, aNode, cNode, pNode, map)) == null) continue;
            block.addStatement(propInit);
        }
    }

    private static ClassCodeExpressionTransformer makeMapTypedArgsTransformer(final SourceUnit unit) {
        return new ClassCodeExpressionTransformer(){

            @Override
            public Expression transform(Expression exp) {
                VariableExpression ve;
                if (exp instanceof ClosureExpression) {
                    ClosureExpression ce = (ClosureExpression)exp;
                    ce.getCode().visit(this);
                } else if (exp instanceof VariableExpression && "args".equals((ve = (VariableExpression)exp).getName()) && ve.getAccessedVariable() instanceof DynamicVariable) {
                    VariableExpression newVe = GeneralUtils.varX(new Parameter(MAP_TYPE, "args"));
                    newVe.setSourcePosition(ve);
                    return newVe;
                }
                return exp.transformExpression(this);
            }

            @Override
            protected SourceUnit getSourceUnit() {
                return unit;
            }
        };
    }
}

