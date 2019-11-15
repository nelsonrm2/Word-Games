import java.io.*;
import java.util.Random;

public class CaesarDecoder
{
    public String[] encoded;
    public String[] decoded;
    public String[] dictionary;

    // Constructor for the cipher object
    public CaesarDecoder()
    {
        encoded = new String[100];
        decoded = new String[100];
    }
    
    // Fill the encoded array with strings from the encoded text file
    public void readFile()
    {
        String currentLine = "";
        int cursor = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("encoded.txt"));
            while((currentLine = reader.readLine()) != null)
            {
                encoded[cursor] = currentLine;
                cursor++;
            }
        }
        catch(Exception e)
        {
            System.out.println("There was trouble reading from the file");
            System.out.println(e);
        }
    }

    // Fills the dictionary array with strings from our dictionary file
    public void loadDictionary()
    {
        dictionary = new String[73029];
        int cursor = 0;
        String currentLine;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
            while((currentLine = reader.readLine()) != null)
            {
                dictionary[cursor] = currentLine;
                cursor++;
            }
        }
        catch(Exception e)
        {
            System.out.println("There was trouble reading from the dictionary");
            System.out.println(e);
        }
    }

    // Shift the letters in the first three words until we figure out the correct offset
    public int determineShift()
    {
        String line = encoded[0];
        String[] split = line.split(" ", 4);
        String firstWord = split[0];
        String secondWord = split[1];
        String thirdWord = split[2];

        for(int shift = 0; shift < 26; shift++)
        {
            System.out.println("Shift by " + shift);
            String decodeFirst = shiftWord(firstWord, shift);
            System.out.print("\t1: " + decodeFirst);
            String decodeSecond = shiftWord(secondWord, shift);
            System.out.print("\t2: " + decodeSecond);
            String decodeThird = shiftWord(thirdWord, shift);
            System.out.println("\t3: " + decodeThird);
            if(validWord(decodeFirst) &&
               validWord(decodeSecond) &&
               validWord(decodeThird))
            {
                return shift;
            }
        }
        return 0;
    }

    // Check a given word to see if it exists in our dictionary
    public boolean validWord(String word)
    {
        for(int index = 0; index < 73029; index++)
        {
            if(word.equalsIgnoreCase(dictionary[index]))
            {
                return true;
            }
        }
        return false;
    }

    // Shift the letters in a word by a given amount
    public String shiftWord(String word, int shift)
    {
        String result = "";
        for(int index = 0; index < word.length(); index++)
        {
            char currentLetter = word.charAt(index);
            if(currentLetter >= 65 && currentLetter <= (90 - shift))
            {
                currentLetter += shift;
            }
            else if(currentLetter > (90 - shift) && currentLetter <= 90)
            {
                currentLetter -= 26;
                currentLetter += shift;
            }
            else if(currentLetter >= 97 && currentLetter <= (122 - shift))
            {
                currentLetter += shift;
            }
            else if(currentLetter > (122 - shift) && currentLetter <= 122)
            {
                currentLetter -= 26;
                currentLetter += shift;
            }
            result += currentLetter;
        }
        return result;
    }

    // Convert the encoded text back to readable English
    public void decode()
    {
        String currentLine;
        int offset = determineShift();
        for(int index = 0; encoded[index] != null; index++)
        {
            currentLine = encoded[index];
            decoded[index] = shiftWord(currentLine, offset);
        }
    }

    // Write the decoded text into a separate file
    public void writeFile()
    {
        int cursor = 0;
        String line = "";
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("decoded.txt"));
            while(decoded[cursor] != null)
            {
                line = decoded[cursor];
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

    // Call the functions to run the program
    public static void main(String[] args)
    {
        CaesarDecoder cipher = new CaesarDecoder();
        cipher.loadDictionary();
        cipher.readFile();
        cipher.decode();
        cipher.writeFile();
    }
}
