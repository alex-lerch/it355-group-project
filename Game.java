import java.security.SecureRandom;

public class Game 
{

    public int rollD6()
    {
        SecureRandom number = new SecureRandom();
        return number.nextInt(7);
    }

    public int roll3D6()
    {
        int total =0;
        for(int i=0; i<3; i++)
        {
            total = total + rollD6();
        }
        return total;
    }

    public int rollD20()
    {
        SecureRandom number = new SecureRandom();
        return number.nextInt(21);
    }

    public int coinFlip()
    {
        SecureRandom number = new SecureRandom();
        return number.nextInt(2);
    }




}
