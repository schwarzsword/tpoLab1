package part1;

import java.math.BigDecimal;
class MyAcos{

    /**
     * Calculates factorial of a
     * @param a number of factorial, should be positive
     * @return value of a!
     * @throws IllegalArgumentException when given a<0 or a>20
     */
    static BigDecimal calcFactorial(int a) throws IllegalArgumentException{
        if( a >= 0) {
            BigDecimal res = BigDecimal.ONE;
            for (int i = 2; i <= a; i++)
                res = res.multiply(BigDecimal.valueOf(i));
            return res;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Calculates member of Maclaurin expansion
     * @param x point in which member is calculated
     * @param n number of calculated member(positive)
     * @return value of n-th member of Maclaurin expansion
     */
    static BigDecimal calcNthMember(double x, int n) throws IllegalArgumentException{
        if(n < 0) throw new IllegalArgumentException();
        int n2 = 2*n;
        int n2plus1 = n2 + 1;
        BigDecimal factN = calcFactorial(n);
        return calcFactorial(n2).multiply(BigDecimal.valueOf(Math.pow(x,n2plus1))).divide(
                    factN.pow(2).multiply(
                            BigDecimal.valueOf(2).pow(n2).multiply(BigDecimal.valueOf(n2plus1))),
                20, BigDecimal.ROUND_HALF_UP );
    }

    /**
     * calculates acos(x) with expansion of Maclaurin method
     * @param x point in which function is calculated
     * @return value of acos(x)
     * @throws IllegalArgumentException when x is not in [-1; 1];
     */
    static BigDecimal calcAcosIn(double x) throws IllegalArgumentException{
        if (Math.abs(x) > 1) {
            throw new IllegalArgumentException();
        }
        boolean flag = true;
        BigDecimal res = BigDecimal.valueOf(Math.PI/2);
        BigDecimal nth = BigDecimal.ZERO, old;
        int i = 0;
        while(flag){
            old = nth;
            nth = calcNthMember(x, i);
            res = res.subtract(nth);
            i++;
            if(nth.subtract(old).abs().compareTo(BigDecimal.valueOf(0.0000001)) < 0) flag = false;
        }
        return res;
    }
}
