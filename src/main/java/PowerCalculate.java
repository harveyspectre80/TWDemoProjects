public class PowerCalculate {

    public static int calculatePower(int baseValue , int raisedValue) {
        int exponentValue = 1;
        while (raisedValue != 0)
        {
            exponentValue = exponentValue * baseValue;
            --raisedValue;
        }
        System.out.println("Answer = " + exponentValue);
        return exponentValue;
    }
}
