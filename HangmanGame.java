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
    public String chosenWord;
    public char[] wordInProgress;
    public int attempts;

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

    // Prepare the game for play
    public void loadGameData()
    {
        Random random = new Random();
        int position = random.nextInt(73029);
        chosenWord = dictionary[position];
        wordInProgress = new char[chosenWord.length()];
        for(int index = 0; index < chosenWord.length(); index++)
        {
            wordInProgress[index] = '_';
        }
        attempts = 6;
    }

    // Print out the current word in progress
    public void printWord()
    {
        for(int index = 0; index < chosenWord.length(); index++)
        {
            System.out.print(" " + wordInProgress[index] + " ");
        }
        System.out.print("\n");
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

    // Returns true if the typed letter is in the word
    public boolean processInput(char letter)
    {
        boolean hasLetter = false;
        for(int index = 0; index < chosenWord.length(); index++)
        {
            char current = chosenWord.charAt(index);
            if(current == letter || current == (letter + 32))
            {
                wordInProgress[index] = letter;
                System.out.println("Nice guess!");
                hasLetter = true;
            }
        }
        return hasLetter;
    }

    // Print the image of the hangman according to how many guesses are left
    public void drawPicture()
    {
        if(attempts == 6)
        {
            System.out.println("                                  ");
            System.out.println("                                  ");
            System.out.println("               ________           ");
            System.out.println("              /        \\          ");
            System.out.println("             /          \\         ");
            System.out.println("                         \\        ");
            System.out.println("                          \\       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                     _____|_____  ");

        }
        if(attempts == 5)
        {
            System.out.println("                                  ");
            System.out.println("                                  ");
            System.out.println("               ________           ");
            System.out.println("              /        \\          ");
            System.out.println("             /          \\         ");
            System.out.println("           ___           \\        ");
            System.out.println("          /   \\           \\       ");
            System.out.println("          \\___/           |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                     _____|_____  ");

        }
        if(attempts == 4)
        {
            System.out.println("                                  ");
            System.out.println("                                  ");
            System.out.println("               ________           ");
            System.out.println("              /        \\          ");
            System.out.println("             /          \\         ");
            System.out.println("           ___           \\        ");
            System.out.println("          /   \\           \\       ");
            System.out.println("          \\___/           |       ");
            System.out.println("            |             |       ");
            System.out.println("            |             |       ");
            System.out.println("            |             |       ");
            System.out.println("            |             |       ");
            System.out.println("            |             |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                     _____|_____  ");
        }
        if(attempts == 3)
        {
            System.out.println("                                  ");
            System.out.println("                                  ");
            System.out.println("               ________           ");
            System.out.println("              /        \\          ");
            System.out.println("             /          \\         ");
            System.out.println("           ___           \\        ");
            System.out.println("          /   \\           \\       ");
            System.out.println("          \\___/           |       ");
            System.out.println("            |             |       ");
            System.out.println("           /|             |       ");
            System.out.println("          / |             |       ");
            System.out.println("         /  |             |       ");
            System.out.println("            |             |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                     _____|_____  ");
        }
        if(attempts == 2)
        {
            System.out.println("                                  ");
            System.out.println("                                  ");
            System.out.println("               ________           ");
            System.out.println("              /        \\          ");
            System.out.println("             /          \\         ");
            System.out.println("           ___           \\        ");
            System.out.println("          /   \\           \\       ");
            System.out.println("          \\___/           |       ");
            System.out.println("            |             |       ");
            System.out.println("           /|\\            |       ");
            System.out.println("          / | \\           |       ");
            System.out.println("         /  |  \\          |       ");
            System.out.println("            |             |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                          |       ");
            System.out.println("                     _____|_____  ");
        }
        if(attempts == 1)
        {
            System.out.println("                                  ");
            System.out.println("                                  ");
            System.out.println("               ________           ");
            System.out.println("              /        \\          ");
            System.out.println("             /          \\         ");
            System.out.println("           ___           \\        ");
            System.out.println("          /   \\           \\       ");
            System.out.println("          \\___/           |       ");
            System.out.println("            |             |       ");
            System.out.println("           /|\\            |       ");
            System.out.println("          / | \\           |       ");
            System.out.println("         /  |  \\          |       ");
            System.out.println("            |             |       ");
            System.out.println("           /              |       ");
            System.out.println("          /               |       ");
            System.out.println("         /                |       ");
            System.out.println("                          |       ");
            System.out.println("                     _____|_____  ");
        }
        if(attempts == 0)
        {
            System.out.println("                                  ");
            System.out.println("                                  ");
            System.out.println("               ________           ");
            System.out.println("              /        \\          ");
            System.out.println("             /          \\         ");
            System.out.println("           ___           \\        ");
            System.out.println("          /   \\           \\       ");
            System.out.println("          \\___/           |       ");
            System.out.println("            |             |       ");
            System.out.println("           /|\\            |       ");
            System.out.println("          / | \\           |       ");
            System.out.println("         /  |  \\          |       ");
            System.out.println("            |             |       ");
            System.out.println("           / \\            |       ");
            System.out.println("          /   \\           |       ");
            System.out.println("         /     \\          |       ");
            System.out.println("                          |       ");
            System.out.println("                     _____|_____  ");
        }
    }
    
    // Determines whether the game should continue
    public boolean finished()
    {
        int remaining = 0;
        for(int index = 0; index < chosenWord.length(); index++)
        {
            if(wordInProgress[index] == '_')
            {
                remaining++;
            }
        }
        System.out.println("There are " + remaining + " letters left to go");
        return(remaining == 0 || attempts == 0);
    }

    // Run the game
    public static void main(String[] args)
    {
        HangmanGame game = new HangmanGame();
        game.loadDictionary();
        game.loadGameData();
        System.out.println("The word is " + game.chosenWord);
        game.printWord();
        while(!game.finished())
        {
            System.out.println("Please enter your guess");
            char letter = game.takeInput();
            System.out.println("You typed: " + letter);
            if(!game.processInput(letter))
            {
                System.out.println("Sorry!");
                game.attempts--;
            }
            game.drawPicture();
            System.out.println("You have " + game.attempts + " guesses left");
            game.printWord();
        }
    }
}
