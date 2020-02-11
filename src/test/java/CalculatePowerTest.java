import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatePowerTest {

    CalculatePower calculatePower = new CalculatePower();

    @Test
    public void givenValuesOfAandB_ShouldReturnExponentialResult() {
        int result =  calculatePower.calculatePower(2,3);
        Assert.assertEquals(result,8);
    }

    @Test
    public void givenNegativeBaseAndPositiveRaisedValue_ShouldReturnNegativeResult() {
        int result = calculatePower.calculatePower(-2,1);
        Assert.assertEquals(-2,result);
    }
    
    @Test
    public void given0digitBaseAndPositiveRaised_ShouldReturn0Result() {
        int result =  calculatePower.calculatePower(0,30);
        Assert.assertEquals(0,result);
    }

    @Test
    public void givenPositiveBaseAnd0Raised_ShouldReturn0Result() {
        int result =  calculatePower.calculatePower(2,0);
        Assert.assertEquals(1,result);
    }

    @Test
    public void givenNegativeBaseAndNegativeRaised_ShouldReturn0Result() {
        int result =  calculatePower.calculatePower(-222,-3);
        Assert.assertEquals(0,result);
    }

}
