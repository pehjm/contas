/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.transform.sc.transformers;

import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.ClassExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.transform.sc.transformers.StaticCompilationTransformer;
import org.codehaus.groovy.transform.stc.StaticTypesMarker;

class StaticMethodCallExpressionTransformer {
    private final StaticCompilationTransformer transformer;

    StaticMethodCallExpressionTransformer(StaticCompilationTransformer sct) {
        this.transformer = sct;
    }

    Expression transformStaticMethodCallExpression(StaticMethodCallExpression smce) {
        Object target = smce.getNodeMetaData((Object)StaticTypesMarker.DIRECT_METHOD_CALL_TARGET);
        if (target instanceof MethodNode) {
            ClassExpression receiver = new ClassExpression(smce.getOwnerType().getPlainNodeReference());
            MethodCallExpression mce = new MethodCallExpression((Expression)receiver, smce.getMethod(), smce.getArguments());
            mce.setMethodTarget((MethodNode)target);
            mce.setSourcePosition(smce);
            mce.copyNodeMetaData(smce);
            return this.transformer.transform(mce);
        }
        return this.transformer.superTransform(smce);
    }
}

