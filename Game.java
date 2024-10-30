import java.security.SecureRandom;

public class Game 
{
    //static variable to keep track of number of played games
    private static int numGamesPlayed = 0;

    //Simulates rolling a dice
    public int rollD6()
    {
        SecureRandom number = new SecureRandom();
        numGamesPlayed++;
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
        numGamesPlayed++;
        return total;
    }

    //Simulates rolling a 20 sided dice
    public int rollD20()
    {
        SecureRandom number = new SecureRandom();
        numGamesPlayed++;
        return number.nextInt(21);
    }

    //Simulates flipping a coin
    public int coinFlip()
    {
        SecureRandom number = new SecureRandom();
        numGamesPlayed++;
        return number.nextInt(2);
    }

    //Compares 2 random floats
    public float compareTwoFloats()
    {
        SecureRandom random = new SecureRandom();
        float num1 = random.nextFloat() * 100;
        float num2 = random.nextFloat() * 100;
        numGamesPlayed++;
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

        numGamesPlayed++;
    }

}
