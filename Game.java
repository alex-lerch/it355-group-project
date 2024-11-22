import java.security.SecureRandom;

public class Game 
{

    //Simulates rolling a dice
    public int rollD6()
    {
        SecureRandom number = new SecureRandom();
        number.setSeed(System.currentTimeMillis());
        return number.nextInt(7);
    }

    //Simulates rolling 3 dice 
    public int roll3D6()
    {
        int total =0;
        for(int i=0; i<3; i++)
        {
            total = total + rollD6();
        }
        return total;
    }

    //Simulates rolling a 20 sided dice
    public int rollD20()
    {
        SecureRandom number = new SecureRandom();
        number.setSeed(System.currentTimeMillis());
        return number.nextInt(21);
    }

    public int rollD100()
    {
        SecureRandom number = new SecureRandom();
        number.setSeed(System.currentTimeMillis());
        return number.nextInt(101);
    }

    //Simulates flipping a coin
    public int coinFlip()
    {
        SecureRandom number = new SecureRandom();
        number.setSeed(System.currentTimeMillis());
        return number.nextInt(2);
    }

    //Compares 2 random floats
    public float compareTwoFloats()
    {
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());
        float num1 = random.nextFloat() * 100;
        float num2 = random.nextFloat() * 100;
        return Math.max(num1, num2);
    }

    //Shift operators 
    public void shiftOperators()
    {
        int value = 8;
        int negValue = -8;
        
        System.out.println("Original value: " + value);

        int leftShiftResult = value << 1;
        System.out.println("After left shift (<< 1): " + leftShiftResult);

        int rightShiftResult = value >> 1;
        System.out.println("After right shift (>> 1): " + rightShiftResult);

        int unsignedRightShiftResult = value >>> 1;
        System.out.println("After unsigned right shift using " + value + " (>>> 1): " + unsignedRightShiftResult);

        unsignedRightShiftResult = negValue >>> 1;
        System.out.println("After unsigned right shift using " + negValue + " (>>> 1): " + unsignedRightShiftResult);

    }

    public boolean higherOrLower(int currentRoll, boolean guessHigher) {
        if (currentRoll < 1 || currentRoll > 100) {
            throw new IllegalArgumentException("Current roll must be between 1 and 100.");
        }

        SecureRandom number = new SecureRandom();
        number.setSeed(System.currentTimeMillis());
        int nextRoll = number.nextInt(100) + 1;

        System.out.println("Current Roll: " + currentRoll);
        System.out.println("Next Roll: " + nextRoll);

        return guessHigher ? nextRoll > currentRoll : nextRoll < currentRoll;
    }


}
