/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.ast.tools;

import groovy.lang.Tuple2;
import groovy.transform.stc.IncorrectTypeHintException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.groovy.util.SystemUtil;
import org.codehaus.groovy.GroovyBugError;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.decompiled.DecompiledClassNode;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.ParserPlugin;
import org.codehaus.groovy.control.ResolveVisitor;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.memoize.ConcurrentSoftCache;
import org.codehaus.groovy.runtime.memoize.EvictableCache;
import org.codehaus.groovy.transform.stc.StaticTypeCheckingSupport;

public class GenericsUtils {
    public static final GenericsType[] EMPTY_GENERICS_ARRAY = GenericsType.EMPTY_ARRAY;
    public static final String JAVA_LANG_OBJECT = "java.lang.Object";
    private static final boolean PARAMETERIZED_TYPE_CACHE_ENABLED = Boolean.parseBoolean(SystemUtil.getSystemPropertySafe("groovy.enable.parameterized.type.cache", "true"));
    private static final EvictableCache<ParameterizedTypeCacheKey, SoftReference<ClassNode>> PARAMETERIZED_TYPE_CACHE = new ConcurrentSoftCache<ParameterizedTypeCacheKey, ClassNode>(64);

    @Deprecated
    public static GenericsType[] alignGenericTypes(GenericsType[] redirectGenericTypes, GenericsType[] parameterizedTypes, GenericsType[] alignmentTarget) {
        if (alignmentTarget == null) {
            return EMPTY_GENERICS_ARRAY;
        }
        if (parameterizedTypes == null || parameterizedTypes.length == 0) {
            return alignmentTarget;
        }
        GenericsType[] generics = new GenericsType[alignmentTarget.length];
        for (GenericsType currentTarget : alignmentTarget) {
            GenericsType match = null;
            if (redirectGenericTypes != null) {
                for (int j = 0; j < redirectGenericTypes.length && match == null; ++j) {
                    ClassNode[] upper;
                    GenericsType redirectGenericType = redirectGenericTypes[j];
                    if (!redirectGenericType.isCompatibleWith(currentTarget.getType())) continue;
                    if (currentTarget.isPlaceholder() && redirectGenericType.isPlaceholder() && !currentTarget.getName().equals(redirectGenericType.getName())) {
                        boolean skip = false;
                        for (int k = j + 1; k < redirectGenericTypes.length && !skip; ++k) {
                            GenericsType ogt = redirectGenericTypes[k];
                            if (!ogt.isPlaceholder() || !ogt.isCompatibleWith(currentTarget.getType()) || !ogt.getName().equals(currentTarget.getName())) continue;
                            skip = true;
                        }
                        if (skip) continue;
                    }
                    match = parameterizedTypes[j];
                    if (!currentTarget.isWildcard()) continue;
                    ClassNode lower = currentTarget.getLowerBound() != null ? match.getType() : null;
                    ClassNode[] currentUpper = currentTarget.getUpperBounds();
                    ClassNode[] classNodeArray = upper = currentUpper != null ? new ClassNode[currentUpper.length] : null;
                    if (upper != null) {
                        for (int k = 0; k < upper.length; ++k) {
                            upper[k] = currentUpper[k].isGenericsPlaceHolder() ? match.getType() : currentUpper[k];
                        }
                    }
                    match = new GenericsType(ClassHelper.makeWithoutCaching("?"), upper, lower);
                    match.setWildcard(true);
                }
            }
            if (match == null) {
                match = currentTarget;
            }
            generics[i] = match;
        }
        return generics;
    }

    public static GenericsType buildWildcardType(ClassNode ... upperBounds) {
        GenericsType gt = new GenericsType(ClassHelper.makeWithoutCaching("?"), upperBounds, null);
        gt.setWildcard(true);
        return gt;
    }

    public static Map<GenericsType.GenericsTypeName, GenericsType> extractPlaceholders(ClassNode type) {
        HashMap<GenericsType.GenericsTypeName, GenericsType> placeholders = new HashMap<GenericsType.GenericsTypeName, GenericsType>();
        GenericsUtils.extractPlaceholders(type, placeholders);
        return placeholders;
    }

    public static void extractPlaceholders(ClassNode type, Map<GenericsType.GenericsTypeName, GenericsType> placeholders) {
        int n;
        if (type == null) {
            return;
        }
        if (type.isArray()) {
            GenericsUtils.extractPlaceholders(type.getComponentType(), placeholders);
            return;
        }
        if (!type.isUsingGenerics() || !type.isRedirectNode()) {
            return;
        }
        GenericsType[] genericsTypes = type.getGenericsTypes();
        if (genericsTypes == null || (n = genericsTypes.length) == 0) {
            return;
        }
        if (type.isGenericsPlaceHolder()) {
            GenericsType gt = genericsTypes[0];
            placeholders.putIfAbsent(new GenericsType.GenericsTypeName(gt.getName()), gt);
            return;
        }
        GenericsType[] redirectGenericsTypes = type.redirect().getGenericsTypes();
        if (redirectGenericsTypes == null) {
            redirectGenericsTypes = genericsTypes;
        } else if (redirectGenericsTypes.length != n) {
            throw new GroovyBugError("Expected earlier checking to detect generics parameter arity mismatch\nExpected: " + type.getName() + GenericsUtils.toGenericTypesString(redirectGenericsTypes) + "\nSupplied: " + type.getName() + GenericsUtils.toGenericTypesString(genericsTypes));
        }
        ArrayList typeArguments = new ArrayList(n);
        for (int i = 0; i < n; ++i) {
            GenericsType rgt = redirectGenericsTypes[i];
            if (!rgt.isPlaceholder()) continue;
            GenericsType typeArgument = genericsTypes[i];
            placeholders.computeIfAbsent(new GenericsType.GenericsTypeName(rgt.getName()), x -> {
                typeArguments.add(typeArgument);
                return typeArgument;
            });
        }
        for (GenericsType gt : typeArguments) {
            if (gt.isWildcard()) {
                ClassNode lowerBound = gt.getLowerBound();
                if (lowerBound != null) {
                    GenericsUtils.extractPlaceholders(lowerBound, placeholders);
                    continue;
                }
                ClassNode[] upperBounds = gt.getUpperBounds();
                if (upperBounds == null) continue;
                for (ClassNode upperBound : upperBounds) {
                    GenericsUtils.extractPlaceholders(upperBound, placeholders);
                }
                continue;
            }
            if (gt.isPlaceholder()) continue;
            GenericsUtils.extractPlaceholders(gt.getType(), placeholders);
        }
    }

    public static String toGenericTypesString(GenericsType[] genericsTypes) {
        if (genericsTypes == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("<");
        int n = genericsTypes.length;
        for (int i = 0; i < n; ++i) {
            sb.append(genericsTypes[i].toString());
            if (i >= n - 1) continue;
            sb.append(",");
        }
        sb.append("> ");
        return sb.toString();
    }

    @Deprecated
    public static ClassNode parameterizeInterfaceGenerics(ClassNode hint, ClassNode target) {
        return GenericsUtils.parameterizeType(hint, target);
    }

    public static ClassNode parameterizeType(ClassNode hint, ClassNode target) {
        Map<String, ClassNode> gt;
        if (hint.isArray()) {
            if (target.isArray()) {
                return GenericsUtils.parameterizeType(hint.getComponentType(), target.getComponentType()).makeArray();
            }
            return target;
        }
        if (hint.isGenericsPlaceHolder()) {
            ClassNode bound = hint.redirect();
            return GenericsUtils.parameterizeType(bound, target);
        }
        if (target.redirect().getGenericsTypes() == null) {
            return target;
        }
        ClassNode cn = target;
        if (!cn.equals(hint) && StaticTypeCheckingSupport.implementsInterfaceOrIsSubclassOf(target, hint)) {
            do {
                if (!GenericsUtils.hasUnresolvedGenerics(cn = ClassHelper.getNextSuperClass(cn, hint))) continue;
                gt = GenericsUtils.createGenericsSpec(hint);
                GenericsUtils.extractSuperClassGenerics(hint, cn, gt);
                cn = GenericsUtils.correctToGenericsSpecRecurse(gt, cn);
            } while (!cn.equals(hint));
            hint = cn;
        }
        cn = target.redirect();
        gt = GenericsUtils.createGenericsSpec(hint);
        gt = GenericsUtils.createGenericsSpec(cn, gt);
        GenericsUtils.extractSuperClassGenerics(hint, cn, gt);
        return GenericsUtils.correctToGenericsSpecRecurse(gt, cn);
    }

    public static ClassNode nonGeneric(ClassNode type) {
        int dims = 0;
        ClassNode temp = type;
        while (temp.isArray()) {
            ++dims;
            temp = temp.getComponentType();
        }
        if (temp instanceof DecompiledClassNode ? ((DecompiledClassNode)temp).isParameterized() : temp.isUsingGenerics()) {
            ClassNode result = ClassHelper.makeWithoutCaching(temp.getName());
            result.setRedirect(temp);
            result.setGenericsTypes(null);
            result.setUsingGenerics(false);
            while (dims > 0) {
                --dims;
                result = result.makeArray();
            }
            return result;
        }
        return type;
    }

    public static ClassNode newClass(ClassNode type) {
        return type.getPlainNodeReference();
    }

    public static ClassNode makeClassSafe(Class klass) {
        return GenericsUtils.makeClassSafeWithGenerics(ClassHelper.make(klass), new GenericsType[0]);
    }

    public static ClassNode makeClassSafeWithGenerics(Class klass, ClassNode genericsType) {
        GenericsType[] genericsTypes = new GenericsType[]{new GenericsType(genericsType)};
        return GenericsUtils.makeClassSafeWithGenerics(ClassHelper.make(klass), genericsTypes);
    }

    public static ClassNode makeClassSafe0(ClassNode type, GenericsType ... genericTypes) {
        ClassNode plainNodeReference = GenericsUtils.newClass(type);
        if (genericTypes != null && genericTypes.length > 0) {
            plainNodeReference.setGenericsTypes(genericTypes);
            if (type.isGenericsPlaceHolder()) {
                plainNodeReference.setGenericsPlaceHolder(true);
            }
        }
        return plainNodeReference;
    }

    public static ClassNode makeClassSafeWithGenerics(ClassNode type, GenericsType ... genericTypes) {
        GenericsType[] gTypes;
        int nTypes;
        if (type.isArray()) {
            return GenericsUtils.makeClassSafeWithGenerics(type.getComponentType(), genericTypes).makeArray();
        }
        int n = nTypes = genericTypes == null ? 0 : genericTypes.length;
        if (nTypes == 0) {
            gTypes = EMPTY_GENERICS_ARRAY;
        } else {
            gTypes = new GenericsType[nTypes];
            System.arraycopy(genericTypes, 0, gTypes, 0, nTypes);
        }
        return GenericsUtils.makeClassSafe0(type, gTypes);
    }

    public static MethodNode correctToGenericsSpec(Map<String, ClassNode> genericsSpec, MethodNode mn) {
        if (genericsSpec == null) {
            return mn;
        }
        if (mn.getGenericsTypes() != null) {
            genericsSpec = GenericsUtils.addMethodGenerics(mn, genericsSpec);
        }
        ClassNode returnType = GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, mn.getReturnType());
        Parameter[] oldParameters = mn.getParameters();
        int nParameters = oldParameters.length;
        Parameter[] newParameters = new Parameter[nParameters];
        for (int i = 0; i < nParameters; ++i) {
            Parameter oldParameter = oldParameters[i];
            newParameters[i] = new Parameter(GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, oldParameter.getType()), oldParameter.getName(), oldParameter.getInitialExpression());
        }
        MethodNode newMethod = new MethodNode(mn.getName(), mn.getModifiers(), returnType, newParameters, mn.getExceptions(), mn.getCode());
        newMethod.setGenericsTypes(mn.getGenericsTypes());
        return newMethod;
    }

    public static ClassNode correctToGenericsSpecRecurse(Map<String, ClassNode> genericsSpec, ClassNode type) {
        return GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, type, Collections.emptyList());
    }

    public static ClassNode[] correctToGenericsSpecRecurse(Map<String, ClassNode> genericsSpec, ClassNode[] types) {
        if (types == null || types.length == 1) {
            return types;
        }
        ClassNode[] newTypes = new ClassNode[types.length];
        boolean modified = false;
        for (int i = 0; i < types.length; ++i) {
            newTypes[i] = GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, types[i], Collections.emptyList());
            modified = modified || types[i] != newTypes[i];
        }
        if (!modified) {
            return types;
        }
        return newTypes;
    }

    public static ClassNode correctToGenericsSpecRecurse(Map<String, ClassNode> genericsSpec, ClassNode type, List<String> exclusions) {
        if (type.isArray()) {
            return GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, type.getComponentType(), exclusions).makeArray();
        }
        String name = type.getUnresolvedName();
        if (type.isGenericsPlaceHolder() && !exclusions.contains(name)) {
            exclusions = DefaultGroovyMethods.plus(exclusions, name);
            type = genericsSpec.get(name);
            if (type != null && type.isGenericsPlaceHolder()) {
                if (type.getGenericsTypes() == null) {
                    ClassNode placeholder = ClassHelper.makeWithoutCaching(type.getUnresolvedName());
                    placeholder.setGenericsPlaceHolder(true);
                    return GenericsUtils.makeClassSafeWithGenerics(type, new GenericsType(placeholder));
                }
                if (!name.equals(type.getUnresolvedName())) {
                    return GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, type, exclusions);
                }
            }
        }
        if (type == null) {
            type = ClassHelper.OBJECT_TYPE.getPlainNodeReference();
        }
        GenericsType[] oldgTypes = type.getGenericsTypes();
        GenericsType[] newgTypes = EMPTY_GENERICS_ARRAY;
        if (oldgTypes != null) {
            newgTypes = new GenericsType[oldgTypes.length];
            for (int i = 0; i < newgTypes.length; ++i) {
                GenericsType oldgType = oldgTypes[i];
                if (oldgType.isWildcard()) {
                    ClassNode[] oldUpper = oldgType.getUpperBounds();
                    ClassNode[] upper = null;
                    if (oldUpper != null) {
                        upper = new ClassNode[oldUpper.length];
                        for (int j = 0; j < oldUpper.length; ++j) {
                            upper[j] = GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, oldUpper[j], exclusions);
                        }
                    }
                    ClassNode oldLower = oldgType.getLowerBound();
                    ClassNode lower = null;
                    if (oldLower != null) {
                        lower = GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, oldLower, exclusions);
                    }
                    GenericsType fixed = new GenericsType(oldgType.getType(), upper, lower);
                    fixed.setWildcard(true);
                    newgTypes[i] = fixed;
                    continue;
                }
                newgTypes[i] = oldgType.isPlaceholder() ? new GenericsType(genericsSpec.getOrDefault(oldgType.getName(), ClassHelper.OBJECT_TYPE)) : new GenericsType(GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, GenericsUtils.correctToGenericsSpec(genericsSpec, oldgType), exclusions));
            }
        }
        return GenericsUtils.makeClassSafeWithGenerics(type, newgTypes);
    }

    public static ClassNode correctToGenericsSpec(Map<String, ClassNode> genericsSpec, GenericsType type) {
        ClassNode cn = null;
        if (type.isPlaceholder()) {
            String name = type.getName();
            if (name.charAt(0) != '#') {
                cn = genericsSpec.get(name);
            }
        } else if (type.isWildcard() && type.getUpperBounds() != null) {
            cn = type.getUpperBounds()[0];
        }
        if (cn == null) {
            cn = type.getType();
        }
        return cn;
    }

    public static ClassNode correctToGenericsSpec(Map<String, ClassNode> genericsSpec, ClassNode type) {
        String name;
        if (type.isArray()) {
            return GenericsUtils.correctToGenericsSpec(genericsSpec, type.getComponentType()).makeArray();
        }
        if (type.isGenericsPlaceHolder() && type.getGenericsTypes() != null && (type = genericsSpec.get(name = type.getGenericsTypes()[0].getName())) != null && type.isGenericsPlaceHolder() && !name.equals(type.getUnresolvedName())) {
            return GenericsUtils.correctToGenericsSpec(genericsSpec, type);
        }
        return type != null ? type : ClassHelper.OBJECT_TYPE.getPlainNodeReference();
    }

    public static Map<String, ClassNode> createGenericsSpec(ClassNode type) {
        return GenericsUtils.createGenericsSpec(type, Collections.emptyMap());
    }

    public static Map<String, ClassNode> createGenericsSpec(ClassNode type, Map<String, ClassNode> oldSpec) {
        ClassNode oc = (ClassNode)type.getNodeMetaData("outer.class");
        HashMap<String, ClassNode> newSpec = oc != null ? GenericsUtils.createGenericsSpec(oc, oldSpec) : new HashMap();
        GenericsType[] gt = type.getGenericsTypes();
        GenericsType[] rgt = type.redirect().getGenericsTypes();
        if (gt != null && rgt != null) {
            int n = gt.length;
            for (int i = 0; i < n; ++i) {
                newSpec.put(rgt[i].getName(), GenericsUtils.correctToGenericsSpec(oldSpec, gt[i]));
            }
        }
        return newSpec;
    }

    public static Map<String, ClassNode> addMethodGenerics(MethodNode current, Map<String, ClassNode> oldSpec) {
        HashMap<String, ClassNode> newSpec = new HashMap<String, ClassNode>(oldSpec);
        GenericsType[] gts = current.getGenericsTypes();
        if (gts != null) {
            for (GenericsType gt : gts) {
                String name = gt.getName();
                ClassNode type = gt.getType();
                if (gt.isPlaceholder()) {
                    ClassNode redirect = gt.getUpperBounds() != null ? gt.getUpperBounds()[0] : (gt.getLowerBound() != null ? gt.getLowerBound() : ClassHelper.OBJECT_TYPE);
                    if (redirect.isGenericsPlaceHolder()) {
                        type = redirect;
                    } else {
                        type = ClassHelper.makeWithoutCaching(name);
                        type.setGenericsPlaceHolder(true);
                        type.setRedirect(redirect);
                    }
                }
                newSpec.put(name, type);
            }
        }
        return newSpec;
    }

    public static void extractSuperClassGenerics(ClassNode type, ClassNode target, Map<String, ClassNode> spec) {
        if (target == null || target == type) {
            return;
        }
        if (target.isGenericsPlaceHolder()) {
            spec.put(target.getUnresolvedName(), type);
        } else if (type.isArray() && target.isArray()) {
            GenericsUtils.extractSuperClassGenerics(type.getComponentType(), target.getComponentType(), spec);
        } else if (!type.isArray() || !target.getName().equals(JAVA_LANG_OBJECT)) {
            if (type.equals(target) || !StaticTypeCheckingSupport.implementsInterfaceOrIsSubclassOf(type, target)) {
                GenericsUtils.extractSuperClassGenerics(type.getGenericsTypes(), target.getGenericsTypes(), spec);
            } else {
                ClassNode superClass = GenericsUtils.getSuperClass(type, target);
                if (superClass != null) {
                    GenericsType[] tp;
                    if (GenericsUtils.hasUnresolvedGenerics(superClass) && (tp = type.redirect().getGenericsTypes()) != null) {
                        GenericsType[] ta = type.getGenericsTypes();
                        boolean noTypeArguments = ta == null || ta.length == 0 || !type.isRedirectNode();
                        HashMap<String, ClassNode> genericsSpec = new HashMap<String, ClassNode>();
                        int n = tp.length;
                        for (int i = 0; i < n; ++i) {
                            GenericsType gt;
                            ClassNode cn = noTypeArguments || StaticTypeCheckingSupport.isUnboundedWildcard(ta[i]) ? ((gt = tp[i]).getUpperBounds() != null ? gt.getUpperBounds()[0] : gt.getType().redirect()) : ((gt = ta[i]).isWildcard() && gt.getUpperBounds() != null ? gt.getUpperBounds()[0] : gt.getType());
                            genericsSpec.put(tp[i].getName(), cn);
                        }
                        superClass = GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, superClass);
                    }
                    GenericsUtils.extractSuperClassGenerics(superClass, target, spec);
                } else {
                    throw new GroovyBugError("The type " + type + " seems not to normally extend " + target + ". Sorry, I cannot handle this.");
                }
            }
        }
    }

    public static ClassNode getSuperClass(ClassNode type, ClassNode target) {
        return ClassHelper.getNextSuperClass(ClassHelper.isPrimitiveType(type) ? ClassHelper.getWrapper(type) : type, target);
    }

    private static void extractSuperClassGenerics(GenericsType[] usage, GenericsType[] declaration, Map<String, ClassNode> spec) {
        if (declaration == null || declaration.length == 0) {
            return;
        }
        if (usage == null) {
            for (GenericsType dt : declaration) {
                String name = dt.getName();
                ClassNode type = spec.get(name);
                if (type == null || !type.isGenericsPlaceHolder() || !type.getUnresolvedName().equals(name)) continue;
                type = type.asGenericsType().getUpperBounds()[0];
                spec.put(name, type);
            }
            return;
        }
        if (usage.length != declaration.length) {
            return;
        }
        int n = usage.length;
        for (int i = 0; i < n; ++i) {
            GenericsType ui = usage[i];
            GenericsType di = declaration[i];
            if (di.isPlaceholder()) {
                spec.put(di.getName(), ui.getType());
                continue;
            }
            if (di.isWildcard()) {
                if (ui.isWildcard()) {
                    GenericsUtils.extractSuperClassGenerics(ui.getLowerBound(), di.getLowerBound(), spec);
                    GenericsUtils.extractSuperClassGenerics(ui.getUpperBounds(), di.getUpperBounds(), spec);
                    continue;
                }
                ClassNode cu = ui.getType();
                GenericsUtils.extractSuperClassGenerics(cu, di.getLowerBound(), spec);
                ClassNode[] upperBounds = di.getUpperBounds();
                if (upperBounds == null) continue;
                for (ClassNode cn : upperBounds) {
                    GenericsUtils.extractSuperClassGenerics(cu, cn, spec);
                }
                continue;
            }
            GenericsUtils.extractSuperClassGenerics(ui.getType(), di.getType(), spec);
        }
    }

    private static void extractSuperClassGenerics(ClassNode[] usage, ClassNode[] declaration, Map<String, ClassNode> spec) {
        if (usage == null || declaration == null || declaration.length == 0) {
            return;
        }
        for (int i = 0; i < usage.length; ++i) {
            ClassNode ui = usage[i];
            ClassNode di = declaration[i];
            if (di.isGenericsPlaceHolder()) {
                spec.put(di.getGenericsTypes()[0].getName(), di);
                continue;
            }
            if (!di.isUsingGenerics()) continue;
            GenericsUtils.extractSuperClassGenerics(ui.getGenericsTypes(), di.getGenericsTypes(), spec);
        }
    }

    public static ClassNode[] parseClassNodesFromString(String option, SourceUnit sourceUnit, CompilationUnit compilationUnit, MethodNode mn, ASTNode usage) {
        try {
            ModuleNode moduleNode = ParserPlugin.buildAST("Dummy<" + option + "> dummy;", compilationUnit.getConfiguration(), compilationUnit.getClassLoader(), null);
            DeclarationExpression dummyDeclaration = (DeclarationExpression)((ExpressionStatement)moduleNode.getStatementBlock().getStatements().get(0)).getExpression();
            ClassNode dummyNode = dummyDeclaration.getLeftExpression().getType();
            GenericsType[] dummyNodeGenericsTypes = dummyNode.getGenericsTypes();
            if (dummyNodeGenericsTypes != null) {
                int n = dummyNodeGenericsTypes.length;
                ClassNode[] signature = new ClassNode[n];
                for (int i = 0; i < n; ++i) {
                    GenericsType genericsType = dummyNodeGenericsTypes[i];
                    signature[i] = genericsType.isWildcard() ? ClassHelper.dynamicType() : GenericsUtils.resolveClassNode(sourceUnit, compilationUnit, mn, usage, genericsType.getType());
                }
                return signature;
            }
        }
        catch (Exception | LinkageError e) {
            sourceUnit.addError(new IncorrectTypeHintException(mn, e, usage.getLineNumber(), usage.getColumnNumber()));
        }
        return null;
    }

    private static ClassNode resolveClassNode(final SourceUnit sourceUnit, CompilationUnit compilationUnit, final MethodNode mn, final ASTNode usage, ClassNode parsedNode) {
        ClassNode dummyClass = new ClassNode("dummy", 0, ClassHelper.OBJECT_TYPE);
        dummyClass.setModule(new ModuleNode(sourceUnit));
        dummyClass.setGenericsTypes(mn.getDeclaringClass().getGenericsTypes());
        MethodNode dummyMN = new MethodNode("dummy", 0, parsedNode, Parameter.EMPTY_ARRAY, ClassNode.EMPTY_ARRAY, EmptyStatement.INSTANCE);
        dummyMN.setGenericsTypes(mn.getGenericsTypes());
        dummyClass.addMethod(dummyMN);
        ResolveVisitor visitor = new ResolveVisitor(compilationUnit){

            @Override
            public void addError(String msg, ASTNode expr) {
                sourceUnit.addError(new IncorrectTypeHintException(mn, msg, usage.getLineNumber(), usage.getColumnNumber()));
            }
        };
        visitor.startResolving(dummyClass, sourceUnit);
        return dummyMN.getReturnType();
    }

    public static GenericsType[] applyGenericsContextToPlaceHolders(Map<String, ClassNode> genericsSpec, GenericsType[] oldPlaceHolders) {
        if (oldPlaceHolders == null || oldPlaceHolders.length == 0) {
            return oldPlaceHolders;
        }
        if (genericsSpec.isEmpty()) {
            return oldPlaceHolders;
        }
        GenericsType[] newTypes = new GenericsType[oldPlaceHolders.length];
        for (int i = 0; i < oldPlaceHolders.length; ++i) {
            ClassNode newLower;
            ClassNode lower;
            ClassNode[] upper;
            GenericsType old = oldPlaceHolders[i];
            if (!old.isPlaceholder()) {
                throw new GroovyBugError("Given generics type " + old + " must be a placeholder!");
            }
            ClassNode fromSpec = genericsSpec.get(old.getName());
            if (fromSpec != null) {
                newTypes[i] = fromSpec.asGenericsType();
                continue;
            }
            ClassNode[] newUpper = upper = old.getUpperBounds();
            if (upper != null && upper.length > 0) {
                ClassNode[] upperCorrected = new ClassNode[upper.length];
                for (ClassNode classNode : upper) {
                    upperCorrected[i] = GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, classNode);
                }
                upper = upperCorrected;
            }
            if ((lower = old.getLowerBound()) == (newLower = GenericsUtils.correctToGenericsSpecRecurse(genericsSpec, lower)) && upper == newUpper) {
                newTypes[i] = oldPlaceHolders[i];
                continue;
            }
            ClassNode newPlaceHolder = ClassHelper.make(old.getName());
            GenericsType gt = new GenericsType(newPlaceHolder, newUpper, newLower);
            gt.setPlaceholder(true);
            newTypes[i] = gt;
        }
        return newTypes;
    }

    public static void clearParameterizedTypeCache() {
        PARAMETERIZED_TYPE_CACHE.clearAll();
    }

    public static ClassNode findParameterizedTypeFromCache(ClassNode genericsClass, ClassNode actualType) {
        return GenericsUtils.findParameterizedTypeFromCache(genericsClass, actualType, false);
    }

    public static ClassNode findParameterizedTypeFromCache(ClassNode genericsClass, ClassNode actualType, boolean tryToFindExactType) {
        if (!PARAMETERIZED_TYPE_CACHE_ENABLED) {
            return GenericsUtils.findParameterizedType(genericsClass, actualType, tryToFindExactType);
        }
        SoftReference sr = PARAMETERIZED_TYPE_CACHE.getAndPut(new ParameterizedTypeCacheKey(genericsClass, actualType), key -> new SoftReference<ClassNode>(GenericsUtils.findParameterizedType(key.getGenericsClass(), key.getActualType(), tryToFindExactType)));
        return sr != null ? (ClassNode)sr.get() : null;
    }

    public static ClassNode findParameterizedType(ClassNode genericsClass, ClassNode actualType) {
        return GenericsUtils.findParameterizedType(genericsClass, actualType, false);
    }

    public static ClassNode findParameterizedType(ClassNode genericsClass, ClassNode actualType, boolean tryToFindExactType) {
        ClassNode type;
        GenericsType[] genericsTypes = genericsClass.getGenericsTypes();
        if (genericsTypes == null || genericsClass.isGenericsPlaceHolder()) {
            return null;
        }
        if (actualType.equals(genericsClass)) {
            return actualType;
        }
        LinkedList<ClassNode> todo = new LinkedList<ClassNode>();
        HashSet<ClassNode> done = new HashSet<ClassNode>();
        todo.add(actualType);
        while ((type = (ClassNode)todo.poll()) != null) {
            ClassNode cn;
            if (!done.add(type)) continue;
            if (!type.isInterface() && (cn = type.getUnresolvedSuperClass()) != null && cn.redirect() != ClassHelper.OBJECT_TYPE) {
                if (GenericsUtils.hasUnresolvedGenerics(cn)) {
                    cn = GenericsUtils.parameterizeType(type, cn);
                }
                if (cn.equals(genericsClass)) {
                    return cn;
                }
                todo.add(cn);
            }
            for (ClassNode cn2 : type.getInterfaces()) {
                if (GenericsUtils.hasUnresolvedGenerics(cn2)) {
                    cn2 = GenericsUtils.parameterizeType(type, cn2);
                }
                if (cn2.equals(genericsClass)) {
                    return cn2;
                }
                todo.add(cn2);
            }
        }
        return null;
    }

    public static Map<GenericsType, GenericsType> makeDeclaringAndActualGenericsTypeMap(ClassNode declaringClass, ClassNode actualReceiver) {
        return GenericsUtils.correlateTypeParametersAndTypeArguments(declaringClass, actualReceiver, false);
    }

    public static Map<GenericsType, GenericsType> makeDeclaringAndActualGenericsTypeMapOfExactType(ClassNode declaringClass, ClassNode actualReceiver) {
        return GenericsUtils.correlateTypeParametersAndTypeArguments(declaringClass, actualReceiver, true);
    }

    private static Map<GenericsType, GenericsType> correlateTypeParametersAndTypeArguments(ClassNode declaringClass, ClassNode actualReceiver, boolean tryToFindExactType) {
        GenericsType[] typeParameters;
        ClassNode parameterizedType = GenericsUtils.findParameterizedTypeFromCache(declaringClass, actualReceiver, tryToFindExactType);
        if (parameterizedType != null && parameterizedType.isRedirectNode() && !parameterizedType.isGenericsPlaceHolder() && (typeParameters = parameterizedType.redirect().getGenericsTypes()) != null) {
            GenericsType[] typeArguments = parameterizedType.getGenericsTypes();
            int m = typeArguments == null ? 0 : typeArguments.length;
            int n = typeParameters.length;
            LinkedHashMap<GenericsType, GenericsType> map = new LinkedHashMap<GenericsType, GenericsType>();
            for (int i = 0; i < n; ++i) {
                map.put(typeParameters[i], i < m ? typeArguments[i] : GenericsUtils.erasure(typeParameters[i]));
            }
            return map;
        }
        return Collections.emptyMap();
    }

    private static GenericsType erasure(GenericsType gt) {
        ClassNode cn = gt.getType().redirect();
        if (gt.getType().getGenericsTypes() != null) {
            gt = gt.getType().getGenericsTypes()[0];
        }
        if (gt.getUpperBounds() != null) {
            cn = gt.getUpperBounds()[0];
        }
        return cn.asGenericsType();
    }

    public static boolean hasNonPlaceHolders(ClassNode type) {
        return GenericsUtils.checkPlaceHolders(type, gt -> !gt.isPlaceholder());
    }

    public static boolean hasPlaceHolders(ClassNode type) {
        return GenericsUtils.checkPlaceHolders(type, gt -> gt.isPlaceholder());
    }

    private static boolean checkPlaceHolders(ClassNode type, Predicate<GenericsType> p) {
        GenericsType[] genericsTypes;
        if (type != null && (genericsTypes = type.getGenericsTypes()) != null) {
            for (GenericsType genericsType : genericsTypes) {
                if (!p.test(genericsType)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean hasUnresolvedGenerics(ClassNode type) {
        if (type.isGenericsPlaceHolder()) {
            return true;
        }
        if (type.isArray()) {
            return GenericsUtils.hasUnresolvedGenerics(type.getComponentType());
        }
        GenericsType[] genericsTypes = type.getGenericsTypes();
        if (genericsTypes != null) {
            for (GenericsType genericsType : genericsTypes) {
                if (genericsType.isPlaceholder()) {
                    return true;
                }
                ClassNode lowerBound = genericsType.getLowerBound();
                ClassNode[] upperBounds = genericsType.getUpperBounds();
                if (lowerBound != null) {
                    if (!GenericsUtils.hasUnresolvedGenerics(lowerBound)) continue;
                    return true;
                }
                if (upperBounds != null) {
                    for (ClassNode upperBound : upperBounds) {
                        if (!GenericsUtils.hasUnresolvedGenerics(upperBound)) continue;
                        return true;
                    }
                    continue;
                }
                if (!GenericsUtils.hasUnresolvedGenerics(genericsType.getType())) continue;
                return true;
            }
        }
        return false;
    }

    public static Tuple2<ClassNode[], ClassNode> parameterizeSAM(ClassNode samType) {
        MethodNode abstractMethod = ClassHelper.findSAM(samType);
        ClassNode declaringClass = abstractMethod.getDeclaringClass();
        Map<GenericsType.GenericsTypeName, GenericsType> spec = GenericsUtils.extractPlaceholders(samType.equals(declaringClass) ? samType : GenericsUtils.parameterizeType(samType, declaringClass));
        if (spec.isEmpty() && declaringClass.getGenericsTypes() != null) {
            for (GenericsType tp : declaringClass.getGenericsTypes()) {
                spec.put(new GenericsType.GenericsTypeName(tp.getName()), GenericsUtils.erasure(tp));
            }
        } else {
            spec.replaceAll((name, type) -> type.isWildcard() && type.getLowerBound() != null ? type.getLowerBound().asGenericsType() : type);
        }
        ClassNode[] parameterTypes = (ClassNode[])Arrays.stream(abstractMethod.getParameters()).map(p -> StaticTypeCheckingSupport.resolveClassNodeGenerics(spec, null, p.getType())).toArray(ClassNode[]::new);
        ClassNode returnType = StaticTypeCheckingSupport.resolveClassNodeGenerics(spec, null, abstractMethod.getReturnType());
        return new Tuple2<ClassNode[], ClassNode>(parameterTypes, returnType);
    }

    public static ClassNode findActualTypeByGenericsPlaceholderName(String placeholderName, Map<GenericsType, GenericsType> genericsPlaceholderAndTypeMap) {
        Function<GenericsType, ClassNode> resolver = gt -> {
            if (gt.isWildcard()) {
                if (gt.getLowerBound() != null) {
                    return gt.getLowerBound();
                }
                if (gt.getUpperBounds() != null) {
                    return gt.getUpperBounds()[0];
                }
            }
            return gt.getType();
        };
        return genericsPlaceholderAndTypeMap.entrySet().stream().filter(e -> ((GenericsType)e.getKey()).getName().equals(placeholderName)).map(Map.Entry::getValue).map(resolver).findFirst().orElse(null);
    }

    private static class ParameterizedTypeCacheKey {
        private ClassNode genericsClass;
        private ClassNode actualType;

        public ParameterizedTypeCacheKey(ClassNode genericsClass, ClassNode actualType) {
            this.genericsClass = genericsClass;
            this.actualType = actualType;
        }

        public ClassNode getGenericsClass() {
            return this.genericsClass;
        }

        public void setGenericsClass(ClassNode genericsClass) {
            this.genericsClass = genericsClass;
        }

        public ClassNode getActualType() {
            return this.actualType;
        }

        public void setActualType(ClassNode actualType) {
            this.actualType = actualType;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            ParameterizedTypeCacheKey cacheKey = (ParameterizedTypeCacheKey)o;
            return this.genericsClass == cacheKey.genericsClass && this.actualType == cacheKey.actualType;
        }

        public int hashCode() {
            return Objects.hash(this.genericsClass, this.actualType);
        }
    }
}

