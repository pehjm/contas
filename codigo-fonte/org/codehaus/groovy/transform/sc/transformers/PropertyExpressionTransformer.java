/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.transform.sc.transformers;

import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.transform.sc.transformers.StaticCompilationTransformer;
import org.codehaus.groovy.transform.stc.StaticTypesMarker;

class PropertyExpressionTransformer {
    private final StaticCompilationTransformer scTransformer;

    PropertyExpressionTransformer(StaticCompilationTransformer scTransformer) {
        this.scTransformer = scTransformer;
    }

    Expression transformPropertyExpression(PropertyExpression pe) {
        MethodNode dmct = (MethodNode)pe.getNodeMetaData((Object)StaticTypesMarker.DIRECT_METHOD_CALL_TARGET);
        if (dmct != null && dmct.getParameters().length == 0 && !GeneralUtils.isOrImplements(this.scTransformer.getTypeChooser().resolveType(pe.getObjectExpression(), this.scTransformer.getClassNode()), ClassHelper.MAP_TYPE)) {
            MethodCallExpression mce = GeneralUtils.callX(this.scTransformer.transform(pe.getObjectExpression()), dmct.getName());
            mce.setImplicitThis(pe.isImplicitThis());
            mce.setMethodTarget(dmct);
            mce.setSourcePosition(pe);
            mce.setSpreadSafe(pe.isSpreadSafe());
            mce.setSafe(pe.isSafe());
            mce.copyNodeMetaData(pe);
            return mce;
        }
        return this.scTransformer.superTransform(pe);
    }
}

