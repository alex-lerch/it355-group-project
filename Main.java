import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

//Main class of the program
//Runs the menu
public class Main 
{
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        try (Scanner in = new Scanner(System.in)) 
        {
            //Create Game and fileIO objects to use in the program
            Game game = new Game();
            fileIO file = new fileIO();

            boolean flag = true;//Program ends when set to false

            //variables used to by program to keep track of data
            int result;
            String username;
            int totalGamesPlayed;
            int totalScore=0;

            //Gets username from user to for scorekeeping
            System.out.println("Please enter username");
            username = file.validate_sanitize( in.nextLine());
            while(username.equals("") || username.length() < 2) 
            {
                System.out.format("%s is an invalid username try again\n",username);
                System.out.println("Please enter username");
                username = file.validate_sanitize( in.nextLine());
            }
            
            //Main loop of the program 
            while(flag)
            {
                //Gets menu option choice from the user 
                System.out.print("Welcome please choose a game:\n1: Dice\n2: 3 Dice\n3: 1 D20\n4: Coin\n5: Compare Floats\n6: Shift Operators\n7: Retrieve user scores for a game\n8: Calculate Average\n9: Exit\nPlease enter a number 1-9: ");
                String input = in.nextLine();
                if(input.length() > 1)
                {
                    System.out.println("Error, try again");
                    in.close();
                }   
                float floatResult;
                int choice = file.game_choice(input);

                //Switch case that controls the program
                switch(choice)
                {
                    //Roll 1 Dice
                    case(1) ->                     {
                        result = game.rollD6();
                        System.out.println("Score: "+result);
                        file.recordHighScore(username, result, "D6.txt");
                    }
                    //Roll 3 Dice
                    case(2) ->                     {
                        result = game.roll3D6();
                        System.out.println("Score: "+result);
                        file.recordHighScore(username, result, "3D6.txt");
                    }
                    //Roll a 20 sided die
                    case(3) ->                     {
                        result = game.rollD20();
                        System.out.println("Score: "+result);
                        file.recordHighScore(username, result, "D20.txt");
                    }
                    //Flips a coin
                    case(4) ->                     {
                        result = game.coinFlip();
                        System.out.println("Score: "+result);
                        file.recordHighScore(username, result, "coin.txt");
                    }
                    //Compares two floating point numbers 
                    case(5) ->                     {
                        floatResult = game.compareTwoFloats();
                        System.out.println("The larger float is: " + floatResult);
                        file.recordHighScore(username, (int) floatResult, "compareFloats.txt");
                    }
                    //Shift operators
                    case(6) ->                     {
                        game.shiftOperators();
                    }
                    //Ends loop
                    case(9) ->                     {
                        flag = false;
                    }
                    //Gets user scores
                    case(7) ->                     {
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

                    }
                    case(8) ->                     {
                        fileIO userScores = new fileIO();
                        System.out.println("please enter the name of a game (D6, 3D6, D20, coin):");
                        String gameName = in.nextLine();
                        String[] scoresArray = userScores.retrieveUserScores(username, gameName);
                        int intScore;
                        double avgScore;
                        totalGamesPlayed=0;
                        for (String score : scoresArray) {
                            try{
                                score=score.substring(username.length()+2);
                                intScore=Integer.parseInt(score);
                                totalScore=totalScore+intScore;
                                totalGamesPlayed++;
                            }
                            catch(NullPointerException | NumberFormatException e){
                                //intScore=0;
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
                    }
                    default ->                     {
                        System.out.println("Error, try again");
                    } 
                }
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
