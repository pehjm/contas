/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.ast.stmt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.stmt.Statement;

public class BlockStatement
extends Statement {
    private List<Statement> statements;
    private VariableScope scope;

    public BlockStatement() {
        this(new ArrayList<Statement>(), new VariableScope());
    }

    public BlockStatement(Statement[] statements, VariableScope scope) {
        this(new ArrayList<Statement>(Arrays.asList(statements)), scope);
    }

    public BlockStatement(List<Statement> statements, VariableScope scope) {
        this.statements = statements;
        this.scope = scope;
    }

    @Override
    public void visit(GroovyCodeVisitor visitor) {
        visitor.visitBlockStatement(this);
    }

    public List<Statement> getStatements() {
        return this.statements;
    }

    public void addStatement(Statement statement) {
        this.statements.add(statement);
    }

    public void addStatements(List<Statement> listOfStatements) {
        this.statements.addAll(listOfStatements);
    }

    @Override
    public String getText() {
        StringJoiner text = new StringJoiner("; ", "{ ", " }");
        for (Statement statement : this.statements) {
            text.add(statement.getText());
        }
        return text.toString();
    }

    public String toString() {
        return super.toString() + this.statements;
    }

    @Override
    public boolean isEmpty() {
        return this.statements.isEmpty();
    }

    public VariableScope getVariableScope() {
        return this.scope;
    }

    public void setVariableScope(VariableScope scope) {
        this.scope = scope;
    }
}

