import java.io.*;
import java.util.Random;

public class CaesarEncoder
{
    public String[] plaintext;
    public String[] encoded;

    public CaesarEncoder()
    {
        plaintext = new String[100];
        encoded = new String[100];
    }

    public void readFile()
    {
        String currentLine = "";
        int cursor = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("plaintext.txt"));
            while((currentLine = reader.readLine()) != null)
            {
                plaintext[cursor] = currentLine;
                cursor++;
            }
        }
        catch(Exception e)
        {
            System.out.println("There was trouble reading from the file");
            System.out.println(e);
        }
    }

    public void encode()
    {
        Random rand = new Random();
        int offset = rand.nextInt(25) + 1;
        String currentLine;
        for(int index = 0; plaintext[index] != null; index++)
        {
            currentLine = plaintext[index];
            encoded[index] = "";
            for(int jndex = 0; jndex < currentLine.length(); jndex++)
            {
                char currentLetter = currentLine.charAt(jndex);
                if(currentLetter >= 65 && currentLetter <= (90 - offset))
                {
                    currentLetter += offset;
                }
                else if(currentLetter > (90 - offset) && currentLetter <= 90)
                {
                    currentLetter -= 26;
                    currentLetter += offset;
                }
                else if(currentLetter >= 97 && currentLetter <= (122 - offset))
                {
                    currentLetter += offset;
                }
                else if(currentLetter > (122 - offset) && currentLetter <= 122)
                {
                    currentLetter -= 26;
                    currentLetter += offset;
                }
                encoded[index] += currentLetter;
            }
        }
    }

    public void writeFile()
    {
        int cursor = 0;
        String line = "";
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("encoded.txt"));
            while(encoded[cursor] != null)
            {
                line = encoded[cursor];
                line += "\n";
                writer.write(line);
                cursor++;
            }
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("There was trouble writing to the file");
            System.out.println(e);
        }
    }
    
    public static void main(String[] args)
    {
        CaesarEncoder cipher = new CaesarEncoder();
        cipher.readFile();
        cipher.encode();
        cipher.writeFile();
    }
}
