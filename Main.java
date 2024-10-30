import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main 
{
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Game game = new Game();
        fileIO file = new fileIO();
        boolean flag = true;
        int result =0;
        String username ="";
        int totalGamesPlayed=0;
        int totalScore=0;
        try (Scanner in = new Scanner(System.in)) 
        {
            while(username == "" || username.length() < 2) 
            {
                System.out.format("%s is an invalid username try again\n",username);
                System.out.println("Please enter username");
                username = file.validate_sanitize( in.nextLine());
            }
            

            while(flag)
            {
                System.out.print("Welcome please choose a game:\n1: Dice\n2: 3 Dice\n3: 1 D20\n4: Coin\n5: Compare Floats\n6: Shift Operators\n7: Exit\n8: Retrieve user scores for a game\n9:Calculate Average\nPlease enter a number 1-9: ");
                String input = in.nextLine();
                if(input.length() > 1)
                {
                    System.out.println("Error, try again");
                    in.close();
                }   
                
                float floatResult;
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
                        floatResult = game.compareTwoFloats();
                        System.out.println("The larger float is: " + floatResult);
                        file.recordHighScore(username, (int) floatResult, "compareFloats.txt");
                        break;
                    }
                    case(6):
                    {
                        game.shiftOperators();
                        break;
                    }
                    case(7):
                    {
                        flag = false;
                        break;
                    }
                    case(8):
                    {
                        /* asks user for name of game to get scores from, stores scores in scoresArray and prints to screen */
                        fileIO userScores = new fileIO();
                        System.out.println("please enter the name of a game (D6, 3D6, D20, coin, compareFloats):");
                        String gameName = in.nextLine();
                        String[] scoresArray = userScores.retrieveUserScores(username, gameName);
                        for (String score : scoresArray) {
                            if (score != null) {
                                System.out.println(score);
                            }
                        }
                        break;

                    }
                    case(9):
                    {
                        fileIO userScores = new fileIO();
                        System.out.println("please enter the name of a game (D6, 3D6, D20, coin):");
                        String gameName = in.nextLine();
                        String[] scoresArray = userScores.retrieveUserScores(username, gameName);
                        int intScore=0;
                        double avgScore=0;
                        totalGamesPlayed=0;
                        for (String score : scoresArray) {
                            try{
                                score=score.substring(username.length()+2);
                                intScore=Integer.parseInt(score);
                                totalScore=totalScore+intScore;
                                totalGamesPlayed++;
                            }
                            catch(NullPointerException e){
                                intScore=0;
                            }
                            catch(NumberFormatException e){
                                intScore=0;
                                System.out.println("Invalid Score: "+score);
                            }
                        }
                        if(totalGamesPlayed!=0){
                            avgScore = divideDoubles((double)totalScore, (double)totalGamesPlayed);
                            if(Double.isInfinite(avgScore)){
                                System.out.println("Invalid Average Score\tInfinite Score");
                            }
                            else if(Double.isNaN(avgScore)){
                                System.out.println("Invalid Average Score\tNon-existant Score");
                            }
                            else{
                                System.out.print("Game Average: ");
                                BigDecimal decimalScore = new BigDecimal(avgScore).setScale(2,RoundingMode.HALF_UP);
                                System.out.println(decimalScore);
                            }
                            
                        }
                        else{
                            System.out.println("No Games Played...");
                            
                        }
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
    } // end of main


    /* helper functions */

    /* provides division functionality while preventing division by zero. returns false if division by zero attempted */
    public static double divideDoubles(double numerator, double denominator) {
        if (denominator == 0) {
            System.out.println("attempting to divide by zero: returning 0");
            return 0;
        }
        else {
            return numerator/denominator;
        }
    }

}
