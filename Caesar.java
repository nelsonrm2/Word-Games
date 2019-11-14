import java.io.*;
import java.util.Random;

public class Caesar
{
    public String[] plaintext;
    public String[] encoded;

    public Caesar()
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
        Caesar cipher = new Caesar();
        cipher.readFile();
        cipher.writeFile();
    }
}
