public class CalculatePower {

    public static int calculatePower(int baseValue , int raisedValue) {
        int exponentValue = 1;
        while (raisedValue != 0)
        {
            if(raisedValue > 0) {
                exponentValue = exponentValue * baseValue;
                --raisedValue;
            }
            else if(raisedValue < 0 ) {
                exponentValue *= (1 / baseValue);
                ++raisedValue;
            }
        }
        System.out.println("Answer = " + exponentValue);
        return exponentValue;
    }
}
