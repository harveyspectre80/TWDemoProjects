import org.testng.Assert;
import org.testng.annotations.Test;

public class PowerCalculateTest {

    PowerCalculate powerCalculate = new PowerCalculate();

    @Test
    public void givenValuesOfAandB_ShouldReturnExponentialResult() {
        int result =  powerCalculate.calculatePower(2,3);
        Assert.assertEquals(result,8);
    }

    @Test
    public void givenNegativeBaseAndPositiveRaisedValue_ShouldReturnNegativeResult() {
        int result = powerCalculate.calculatePower(-2,1);
        Assert.assertEquals(-2,result);
    }
    
    @Test
    public void given0digitBaseAndPositiveRaised_ShouldReturn0Result() {
        int result =  powerCalculate.calculatePower(0,30);
        Assert.assertEquals(0,result);
    }

    @Test
    public void givenPositiveBaseAnd0Raised_ShouldReturn0Result() {
        int result =  powerCalculate.calculatePower(2,0);
        Assert.assertEquals(1,result);
    }

}
