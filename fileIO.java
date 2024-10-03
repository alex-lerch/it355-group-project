import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Scanner;

public class fileIO 
{
    final public String validate_sanitize(String input)
    {
        final String str = Normalizer.normalize(input, Form.NFKC);
        return str;
    }

    public int game_choice(String input)
    {
    
        String temp = validate_sanitize(input);
        int result = Integer.parseInt(temp);
        return result;
    }

    public BufferedReader readValidate(String file_name)
    {
        if (file_name == null) {
            throw new NullPointerException();
        }
        boolean validFileFlag = false;
        do {
            try 
            {
                File file = new File(file_name);
                if (!file.getPath().startsWith("c:\\")) {
                    System.out.println("invalid file");
                    return null;
                }
                validFileFlag = true;
                String canonicalPath = file.getCanonicalPath();  
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(canonicalPath)));
            } 
            catch(IOException e) 
            {
                System.out.println("file cannot be created. specify another file(1) name or quit(2)");
                Scanner reader = new Scanner(System.in);
                int input = reader.nextInt();
                if (input == 1) {
                    System.out.println("specify file name:");
                    file_name = reader.nextLine();
                }
                else {
                    System.exit(0);
                }
            }
        } while (validFileFlag != true);
        return null;
    }


    public BufferedWriter writeValidate(String file_name)
    {
        try 
        {
            File file = new File(file_name);
            String canonicalPath = file.getCanonicalPath();  
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(canonicalPath,true)));
            return writer;
        } 
        catch(IOException e) 
        {
            return null;
        }
    }

    public void recordHighScore(String user, int score, String file_name)
    {
        try (BufferedWriter writer = writeValidate(file_name)) 
        {
            writer.write(user+": "+ score);
            writer.write('\n');
        }
        catch (IOException e) 
        {
            System.out.println("Error, try again");
        }
        
    }

    /* looks through file of specified game and returns an array with the user's scores in that game */
    public String[] retrieveUserScores(String username, String game) throws FileNotFoundException, IOException {
        String[] scoresArray = new String[100];
        String fileName = "";
        switch(game)
        {
            case("D6"):
            {
                fileName = "D6.txt";
                break;
            }
            case("3D6"):
            {
                fileName = "3D6.txt";
                break;
            }
            case("D20"):
            {
                fileName = "D20.txt";
                break;
            }
            case("coin"):
            {
                fileName = "coin.txt";
                break;
            }
            default:
            {
                System.out.println("invalid game file");
                break;
            }
        }

        // read file and create array with scores
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int lineNum = 1;
        while((line = reader.readLine()) != null) {
            if (line.contains(username))
            if (addElement(scoresArray, lineNum - 1, line) == false) {
                reader.close();
                return scoresArray;
            }
            lineNum++;
        }
        reader.close();

        return scoresArray;
    }

    /* private helper method for retrieveUserScores */
    private static boolean addElement(String[] arr, int index, String element) {
        if (index > arr.length) {
            return false;
        }
        arr[index] = element;
        return true;
    }


}
