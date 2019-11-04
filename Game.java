import java.io.*;
import java.util.Random;

public class Game
{
    // Constructor for game object
    public Game()
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

    // Prepare the game for play
    public void loadGameData()
    {
        Random random = new Random();
        int position = random.nextInt(73029);
        chosenWord = dictionary[position];
    }

    // Call the necessary functions to run the game
    public static void main(String[] args)
    {
        Game game = new Game();
        game.loadDictionary();
        game.loadGameData();
        System.out.println("The word is " + game.chosenWord);
    }
}
