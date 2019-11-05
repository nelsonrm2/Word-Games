import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class HangmanGame
{
    // Constructor for the game object
    public HangmanGame()
    {
    }
    
    public String[] dictionary;

    // Fills the dictionary array with strings from our text file
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

    public String chosenWord;
    public String wordInProgress;

    // Prepare the game for play
    public void loadGameData()
    {
        Random random = new Random();
        int position = random.nextInt(73029);
        chosenWord = dictionary[position];
        wordInProgress = "";
        for(int index = 0; index < chosenWord.length(); index++)
        {
            wordInProgress += " _ ";
        }
    }

    // Reads a character from the keyboard, always returns a capital letter
    public char takeInput()
    {
        Scanner keyboard = new Scanner(System.in);
        String word = keyboard.nextLine();
        char letter = word.charAt(0);
        if(letter >= 97)
        {
            letter -= 32;
        }
        return letter;
    }

    // Call the necessary functions to run the game
    public static void main(String[] args)
    {
        HangmanGame game = new HangmanGame();
        game.loadDictionary();
        game.loadGameData();
        System.out.println("The word is " + game.chosenWord);
        System.out.println(game.wordInProgress);
        char letter = game.takeInput();
        System.out.println("You typed: " + letter);
    }
}
