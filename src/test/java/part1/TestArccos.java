package part1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.math.BigDecimal;
import java.math.RoundingMode;

class TestArccos {

    private double calcNthMember(double x, int n){
        long a = 1;
        long b = 1;
        for(int i = 1; i + 1< 2*n + 1; i+=2){
            a*=i;
            b*=(i+1);
        }

        return (Math.pow(x, 2*n+1) * a) / (b * (2*n+1));
    }

    private long getFact(int n){
        switch (n){
            case 0:
            case 1: return 1;
            case 2: return 2;
            case 5: return 120;
            case 15: return 1307674368000L;
            case 20: return 2432902008176640000L;
        }
        return -1;
    }

    @Test
    void calcFactorialTestNegative(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> MyAcos.calcFactorial(-1));
    }

    @Test
    void calcFactorialTestZero(){
        Assertions.assertEquals(MyAcos.calcFactorial(0), BigDecimal.ONE);
    }


    @ParameterizedTest
    @ValueSource(ints ={0, 1, 2, 5, 15, 20})
    void calcFactorial(int a){
        Assertions.assertEquals(MyAcos.calcFactorial(a), BigDecimal.valueOf(getFact(a)));
    }

    @Test
    void calcNegativeMember(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> MyAcos.calcNthMember(0, -1));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1d, 0d, -1d, 0.5d, -0.5d})
    void calcNthMemberTest(double x){
        int n = 7;
        BigDecimal res = BigDecimal.valueOf(calcNthMember(x, n)).setScale(4, RoundingMode.HALF_DOWN);
        Assertions.assertEquals(MyAcos.calcNthMember(x, n).doubleValue(), res.doubleValue(), 0.001);
    }

    @ParameterizedTest
    @ValueSource(ints ={0, 1, 2, 10, 15})
    void calcMembersInX(int n){
        double x = 1.0d;
        BigDecimal res = BigDecimal.valueOf(calcNthMember(x, n)).setScale(4, RoundingMode.HALF_DOWN);
        Assertions.assertEquals(MyAcos.calcNthMember(x, n).doubleValue(), res.doubleValue(), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-100, 100, -1.1, 1.1})
    void calcUndefinedAcos(double x){
        Assertions.assertThrows(IllegalArgumentException.class, () -> MyAcos.calcAcosIn(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 1, 0.5, -0.5, 0, 0.95, -0.75})
    void calcDefinedAcos(double x){
        Assertions.assertEquals(MyAcos.calcAcosIn(x).doubleValue(), Math.acos(x), 0.03);
    }
}
