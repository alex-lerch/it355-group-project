import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
        Game game = new Game();
        fileIO file = new fileIO();
        boolean flag = true;
        int result =0;
        String username ="";
        try (Scanner in = new Scanner(System.in)) 
        {
            System.out.println("Please enter username");
            username = file.validate_sanitize( in.nextLine());

            while(flag)
            {
                System.out.print("Welcome please choose a game:\n1: Dice\n2: 3 Dice\n3: 1 D20\n4: Coin\n5: Exit\nPlease enter a number 1-5: ");
                String input = in.nextLine();
                if(input.length() > 1)
                {
                    System.out.println("Error, try again");
                    in.close();
                }   
                
                int choice = file.game_choice(input);
                switch(choice)
                {
                    case(1):
                    {
                        result = game.rollD6();
                        System.out.println("Score: "+result);
                        file.recordHighScore(username, result, "D6.txt");
                        break;
                    }
                    case(2):
                    {
                        result = game.roll3D6();
                        System.out.println("Score: "+result);
                        file.recordHighScore(username, result, "3D6.txt");
                        break;
                    }
                    case(3):
                    {
                        result = game.rollD20();
                        System.out.println("Score: "+result);
                        file.recordHighScore(username, result, "D20.txt");
                        break;
                    }
                    case(4):
                    {
                        result = game.coinFlip();
                        System.out.println("Score: "+result);
                        file.recordHighScore(username, result, "coin.txt");
                        break;
                    }
                    case(5):
                    {
                        flag = false;
                        break;
                    }
                    default:
                    {
                        System.out.println("Error, try again");
                        break;
                    } 
                }
                result =0;
            }
            in.close();
        }
    }
}
