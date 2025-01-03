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
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.Scanner;

public class fileIO 
{
    private Locale locale;

    //Normalize a string input
    final public String validate_sanitize(String input)
    {
        final String str = Normalizer.normalize(input, Form.NFKC);
        return str;
    }

    //Gets an input value from string user input
    public int game_choice(String input)
    {
    
        String temp = validate_sanitize(input);
        int result = Integer.parseInt(temp);
        return result;
    }

    //Returns a BufferedReader
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
                    reader.close();
                    System.exit(0);
                }
            }
        } while (validFileFlag != true);
        return null;
    }

    //Returns a BufferedWriter
    public BufferedWriter writeValidate(String file_name)
    {
        if (file_name == null) {
            throw new NullPointerException();
        }
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

    //Records a score
    public void recordHighScore(String user, int score, String file_name)
    {
        if(user==null || score < 0 || file_name==null)
        {
            throw new NullPointerException();
        }
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

    public void recordHighOrLowerScore(String user, String score, String file_name)
    {
        if(user==null || score == null || file_name==null)
        {
            throw new NullPointerException();
        }
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

    //Converts in to float
    public float safeIntToFloat(int value)
    {
        if (value >= 16777216)
        {
            System.out.println("Possible loss of precision when converting " + value + " to float.");
        }
        return (float) value;
    }

    //Converts byte[] to string[]
    public String safeByteArrayToString(byte[] byteArray)
    {
        Charset charSet = StandardCharsets.UTF_8;
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        CharBuffer charBuffer = CharBuffer.allocate(byteArray.length);

        CoderResult result = charSet.newDecoder().decode(byteBuffer, charBuffer, true);

        if (result.isError())
        {
            System.out.println("Partial or invalid characters found in byte array.");
            return null;
        }

        return new String(byteArray, charSet);
    }

    //Processes a unicode string
    public void processUnicodeString(String input)
    {
        System.out.println("Processing string as Unicode code points:");

        input.codePoints().forEach(cp -> {
            String character = new String(Character.toChars(cp));
            System.out.println("Code point: " + cp + " (Character: " + character + ")");
        });
    }

    //Sets locale
    public void setLocale()
    {
        this.locale = Locale.getDefault();
        System.out.println("Locale has been set to: ");
    }

    //Gets locale
    public Locale getLocale()
    {
        return this.locale;
    }

    //processes data
    public String processNonCharacterData(byte[] byteArray)
    {
        StringBuilder sb = new StringBuilder();

        for (byte b : byteArray)
        {
            sb.append(String.format("%02X", b));
        }

        return sb.toString();
    }

    //encodes string
    public byte[] encodeString(String input, Charset charset)
    {
        if (charset == null)
        {
            charset = StandardCharsets.UTF_8;
        }
        return input.getBytes(charset);
    }

    //decodes string
    public String decodeString(byte[] byteArray, Charset charset)
    {
        if (charset == null)
        {
            charset = StandardCharsets.UTF_8;
        }

        return new String(byteArray, charset);
    }

    /* looks through file of specified game and returns an array with the user's scores in that game */
    public String[] retrieveUserScores(String username, String game) throws FileNotFoundException, IOException {
        String[] scoresArray = new String[100];
        String fileName = "";
        switch(game)
        {
            case("D6") ->             {
                fileName = "D6.txt";
            }
            case("3D6") ->             {
                fileName = "3D6.txt";
            }
            case("D20") ->             {
                fileName = "D20.txt";
            }
            case("coin") ->             {
                fileName = "coin.txt";
            }
            case("compareFloats") ->             {
                fileName = "compareFloats.txt";
            }
            default ->             {
                System.out.println("invalid game file");
            }
        }
        // read file and create array with scores
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) 
        {
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
        }

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
