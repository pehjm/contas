/*
 * Decompiled with CFR 0.152.
 */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.NumericFunction;

public class Poisson {
    private static final double DEFAULT_RETURN_RESULT = 1.0;
    private static final long[] FACTORIALS = new long[]{1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L, 39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};

    private static boolean isDefaultResult(double x, double mean) {
        return x == 0.0 && mean == 0.0;
    }

    private static void checkArgument(double aDouble) throws EvaluationException {
        NumericFunction.checkValue(aDouble);
        if (aDouble < 0.0) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }

    private static double probability(int k, double lambda) {
        return Math.pow(lambda, k) * Math.exp(-lambda) / (double)Poisson.factorial(k);
    }

    private static double cumulativeProbability(int x, double lambda) {
        double result = 0.0;
        for (int k = 0; k <= x; ++k) {
            result += Poisson.probability(k, lambda);
        }
        return result;
    }

    private static long factorial(int n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("Valid argument should be in the range [0..20]");
        }
        return FACTORIALS[n];
    }

    public static ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length != 3) {
            return ErrorEval.VALUE_INVALID;
        }
        ValueEval arg0 = args[0];
        ValueEval arg1 = args[1];
        ValueEval arg2 = args[2];
        try {
            double x = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
            double mean = NumericFunction.singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
            if (Poisson.isDefaultResult(x, mean)) {
                return new NumberEval(1.0);
            }
            Poisson.checkArgument(x);
            Poisson.checkArgument(mean);
            boolean cumulative = ((BoolEval)arg2).getBooleanValue();
            double result = cumulative ? Poisson.cumulativeProbability((int)x, mean) : Poisson.probability((int)x, mean);
            NumericFunction.checkValue(result);
            return new NumberEval(result);
        }
        catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}

