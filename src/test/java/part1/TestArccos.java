package part1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

public class TestArccos {

    private double calcNthMember(double x, int n){
        int a = 1;
        int b = 1;
        for(int i = 1; i + 1< 2*n; i+=2){
            a*=i;
            b*=(i+1);
        }
        return Math.pow(x, 2*n+1) * a / (b * (2*n+1));
    }

    @Test
    public void calcFactorialTestNegative(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> MyAcos.calcFactorial(-1));
    }

    @Test
    public void calcFactorialTestZero(){
        Assertions.assertEquals(MyAcos.calcFactorial(0), BigDecimal.ONE);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1d, 0d, -1d, 0.5d, -0.5d})
    public void calcNthMemberTest(double x){
        int n = 10;
        int p =5;
        double res = calcNthMember(x, n);
        Assertions.assertEquals(MyAcos.calcNthMember(x, n, p).doubleValue(), res, Math.pow(10, -p));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1d, 0d, -1d, 0.5d, -0.5d, Double.MAX_VALUE})
    public void calcNthMemberTestPrecision(int p){

    }
}
