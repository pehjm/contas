/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.ast.stmt;

import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.Statement;

public class IfStatement
extends Statement {
    private BooleanExpression booleanExpression;
    private Statement ifBlock;
    private Statement elseBlock;

    public IfStatement(BooleanExpression booleanExpression, Statement ifBlock, Statement elseBlock) {
        this.setBooleanExpression(booleanExpression);
        this.setIfBlock(ifBlock);
        this.setElseBlock(elseBlock);
    }

    public void setBooleanExpression(BooleanExpression booleanExpression) {
        this.booleanExpression = booleanExpression;
    }

    public void setIfBlock(Statement statement) {
        this.ifBlock = statement;
    }

    public void setElseBlock(Statement statement) {
        this.elseBlock = statement;
    }

    @Override
    public void visit(GroovyCodeVisitor visitor) {
        visitor.visitIfElse(this);
    }

    public BooleanExpression getBooleanExpression() {
        return this.booleanExpression;
    }

    public Statement getIfBlock() {
        return this.ifBlock;
    }

    public Statement getElseBlock() {
        return this.elseBlock;
    }

    @Override
    public String getText() {
        Statement thenStmt = this.getIfBlock();
        Statement elseStmt = this.getElseBlock();
        StringBuilder text = new StringBuilder(64);
        text.append("if (");
        text.append(this.getBooleanExpression().getText());
        text.append(") ");
        text.append(thenStmt.getText());
        if (elseStmt != null && !elseStmt.isEmpty()) {
            if (!(thenStmt instanceof BlockStatement)) {
                text.append(';');
            }
            text.append(" else ");
            text.append(elseStmt.getText());
        }
        return text.toString();
    }
}

