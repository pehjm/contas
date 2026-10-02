/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.groovy.classgen.asm;

import groovyjarjarasm.asm.Label;
import groovyjarjarasm.asm.MethodVisitor;
import java.util.ArrayList;
import java.util.List;
import org.apache.groovy.ast.tools.ExpressionUtils;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.AssertStatement;
import org.codehaus.groovy.classgen.asm.CompileStack;
import org.codehaus.groovy.classgen.asm.OperandStack;
import org.codehaus.groovy.classgen.asm.WriterController;
import org.codehaus.groovy.control.Janitor;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.codehaus.groovy.runtime.powerassert.SourceTextNotAvailableException;
import org.codehaus.groovy.syntax.Token;

public class AssertionWriter {
    private final WriterController controller;
    private AssertionTracker assertionTracker;
    private AssertionTracker disabledTracker;

    public AssertionWriter(WriterController wc) {
        this.controller = wc;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void writeAssertStatement(AssertStatement statement) {
        AssertionTracker oldTracker = this.assertionTracker;
        this.assertionTracker = null;
        try {
            SourceText sourceText;
            MethodVisitor mv = this.controller.getMethodVisitor();
            OperandStack operandStack = this.controller.getOperandStack();
            CompileStack compileStack = this.controller.getCompileStack();
            if (!ExpressionUtils.isNullConstant(statement.getMessageExpression()) || (sourceText = this.getSourceText(statement)) == null) {
                statement.getBooleanExpression().visit(this.controller.getAcg());
                Label falseBranch = operandStack.jump(153);
                Label afterAssert = new Label();
                mv.visitJumpInsn(167, afterAssert);
                mv.visitLabel(falseBranch);
                this.writeSourcelessAssertText(statement);
                operandStack.push(ClassHelper.STRING_TYPE);
                statement.getMessageExpression().visit(this.controller.getAcg());
                operandStack.box();
                this.throwAssertError();
                mv.visitLabel(afterAssert);
                return;
            }
            this.assertionTracker = new AssertionTracker();
            this.assertionTracker.sourceText = sourceText;
            mv.visitTypeInsn(187, "org/codehaus/groovy/runtime/powerassert/ValueRecorder");
            mv.visitInsn(89);
            mv.visitMethodInsn(183, "org/codehaus/groovy/runtime/powerassert/ValueRecorder", "<init>", "()V", false);
            operandStack.push(ClassHelper.OBJECT_TYPE);
            this.assertionTracker.recorderIndex = compileStack.defineTemporaryVariable("recorder", true);
            Label tryStart = new Label();
            Label tryEnd = new Label();
            mv.visitLabel(tryStart);
            statement.getBooleanExpression().visit(this.controller.getAcg());
            Label falseBranch = operandStack.jump(153);
            mv.visitVarInsn(25, this.assertionTracker.recorderIndex);
            mv.visitMethodInsn(182, "org/codehaus/groovy/runtime/powerassert/ValueRecorder", "clear", "()V", false);
            Label afterAssert = new Label();
            mv.visitJumpInsn(167, afterAssert);
            mv.visitLabel(falseBranch);
            mv.visitLdcInsn(this.assertionTracker.sourceText.getNormalizedText());
            mv.visitVarInsn(25, this.assertionTracker.recorderIndex);
            mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/powerassert/AssertionRenderer", "render", "(Ljava/lang/String;Lorg/codehaus/groovy/runtime/powerassert/ValueRecorder;)Ljava/lang/String;", false);
            operandStack.push(ClassHelper.STRING_TYPE);
            int recorderIndex = this.assertionTracker.recorderIndex;
            this.assertionTracker = null;
            statement.getMessageExpression().visit(this.controller.getAcg());
            operandStack.box();
            this.throwAssertError();
            mv.visitLabel(tryEnd);
            Label catchAll = new Label();
            mv.visitLabel(catchAll);
            mv.visitVarInsn(25, recorderIndex);
            mv.visitMethodInsn(182, "org/codehaus/groovy/runtime/powerassert/ValueRecorder", "clear", "()V", false);
            mv.visitInsn(191);
            compileStack.addExceptionBlock(tryStart, tryEnd, catchAll, null);
            mv.visitLabel(afterAssert);
            compileStack.removeVar(recorderIndex);
        }
        finally {
            this.assertionTracker = oldTracker;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private SourceText getSourceText(AssertStatement statement) {
        Janitor janitor = new Janitor();
        try {
            SourceText sourceText = new SourceText(statement, this.controller.getSourceUnit(), janitor);
            return sourceText;
        }
        catch (SourceTextNotAvailableException e) {
            SourceText sourceText = null;
            return sourceText;
        }
        finally {
            janitor.cleanup();
        }
    }

    private void writeSourcelessAssertText(AssertStatement statement) {
        MethodVisitor mv = this.controller.getMethodVisitor();
        OperandStack operandStack = this.controller.getOperandStack();
        CompileStack compileStack = this.controller.getCompileStack();
        BooleanExpression booleanExpression = statement.getBooleanExpression();
        String expressionText = booleanExpression.getText();
        ArrayList<String> names = new ArrayList<String>();
        AssertionWriter.addVariableNames(booleanExpression, names);
        if (names.isEmpty()) {
            mv.visitLdcInsn(expressionText);
        } else {
            mv.visitTypeInsn(187, "java/lang/StringBuilder");
            mv.visitInsn(89);
            mv.visitLdcInsn(expressionText + ". Values: ");
            mv.visitMethodInsn(183, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
            operandStack.push(ClassHelper.OBJECT_TYPE);
            int tempIndex = compileStack.defineTemporaryVariable("assert", true);
            boolean first = true;
            for (String name : names) {
                String text = name + " = ";
                if (first) {
                    first = false;
                } else {
                    text = ", " + text;
                }
                mv.visitVarInsn(25, tempIndex);
                mv.visitLdcInsn(text);
                mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
                mv.visitInsn(87);
                mv.visitVarInsn(25, tempIndex);
                new VariableExpression(name).visit(this.controller.getAcg());
                operandStack.box();
                mv.visitMethodInsn(184, "org/codehaus/groovy/runtime/InvokerHelper", "toString", "(Ljava/lang/Object;)Ljava/lang/String;", false);
                mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                mv.visitInsn(87);
                operandStack.remove(1);
            }
            mv.visitVarInsn(25, tempIndex);
            compileStack.removeVar(tempIndex);
        }
    }

    private static void addVariableNames(Expression expression, List<String> list) {
        if (expression instanceof BooleanExpression) {
            BooleanExpression boolExp = (BooleanExpression)expression;
            AssertionWriter.addVariableNames(boolExp.getExpression(), list);
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression binExp = (BinaryExpression)expression;
            AssertionWriter.addVariableNames(binExp.getLeftExpression(), list);
            AssertionWriter.addVariableNames(binExp.getRightExpression(), list);
        } else if (expression instanceof VariableExpression) {
            VariableExpression varExp = (VariableExpression)expression;
            list.add(varExp.getName());
        }
    }

    private void throwAssertError() {
        this.controller.getMethodVisitor().visitMethodInsn(184, "org/codehaus/groovy/runtime/InvokerHelper", "createAssertError", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/AssertionError;", false);
        this.controller.getMethodVisitor().visitInsn(191);
        this.controller.getOperandStack().remove(2);
    }

    public void disableTracker() {
        if (this.assertionTracker != null) {
            this.disabledTracker = this.assertionTracker;
            this.assertionTracker = null;
        }
    }

    public void reenableTracker() {
        if (this.disabledTracker != null) {
            this.assertionTracker = this.disabledTracker;
            this.disabledTracker = null;
        }
    }

    public void record(Token token) {
        if (this.assertionTracker != null) {
            this.record(this.assertionTracker.sourceText.getNormalizedColumn(token.getStartLine(), token.getStartColumn()));
        }
    }

    public void record(Expression expression) {
        if (this.assertionTracker != null) {
            this.record(this.assertionTracker.sourceText.getNormalizedColumn(expression.getLineNumber(), expression.getColumnNumber()));
        }
    }

    private void record(int normalizedColumn) {
        MethodVisitor mv = this.controller.getMethodVisitor();
        OperandStack operandStack = this.controller.getOperandStack();
        operandStack.dup();
        operandStack.box();
        mv.visitVarInsn(25, this.assertionTracker.recorderIndex);
        operandStack.push(ClassHelper.OBJECT_TYPE);
        operandStack.swap();
        mv.visitLdcInsn(normalizedColumn);
        mv.visitMethodInsn(182, "org/codehaus/groovy/runtime/powerassert/ValueRecorder", "record", "(Ljava/lang/Object;I)Ljava/lang/Object;", false);
        mv.visitInsn(87);
        operandStack.remove(2);
    }

    private static class AssertionTracker {
        int recorderIndex;
        SourceText sourceText;

        private AssertionTracker() {
        }
    }
}

