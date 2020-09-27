package part1;

import java.math.BigDecimal;
public class MyAcos{

    /**
     * Calculates factorial of a
     * @param a number of factorial, should be positive
     * @return value of a!
     * @throws IllegalArgumentException when given a<0 or a>20
     */
    public static BigDecimal calcFactorial(int a) throws IllegalArgumentException{
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
     * @param n number of calculated member
     * @param p precision (number of digits after comma)
     * @return value of n-th member of Maclaurin expansion
     */
    public static BigDecimal calcNthMember(double x, int n, int p){
        int n2 = 2*n;
        int n2plus1 = n2 + 1;
        BigDecimal factN = calcFactorial(n);
        return calcFactorial(n2).multiply(BigDecimal.valueOf(Math.pow(x,n2plus1))).divide(
                    factN.pow(2).multiply(
                            BigDecimal.valueOf(2).pow(n2).multiply(BigDecimal.valueOf(n2plus1))),
                p,
                BigDecimal.ROUND_HALF_UP);
    }

    /**
     * calculates acos(x) with expansion of Maclaurin method
     * @param x point in which function is calculated
     * @param p precision of members
     * @return value of acos(x)
     * @throws IllegalArgumentException when x is not in [-1; 1];
     */

    public static BigDecimal calcAcosIn(double x, int p) throws IllegalArgumentException{
        if (Math.abs(x) > 1) {
            throw new IllegalArgumentException();
        }
        boolean flag = true;
        BigDecimal res = BigDecimal.valueOf(Math.PI/2);
        BigDecimal nth;
        int i = 0;
        while(flag){
            nth = calcNthMember(x, i, p);
            res = res.subtract(nth);
            i++;

            if(nth.abs().compareTo(BigDecimal.valueOf(Math.pow(10,-p))) < 0) flag = false;
            //System.out.println(i + ") " + nth + "; " + Math.pow(10,-p));
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(calcAcosIn(1,5));
    }

}
