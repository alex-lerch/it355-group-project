import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class fileIO 
{
    public String validate_sanitize(String input)
    {
        String str = Normalizer.normalize(input, Form.NFKC);
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
        try 
        {
            File file = new File(file_name);
            String canonicalPath = file.getCanonicalPath();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(canonicalPath)));
            return reader;
        } 
        catch(IOException e) 
        {
            return null;
        }
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



}
