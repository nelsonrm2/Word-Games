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
    public String[] image1;
    public String[] image2;
    public String[] image3;
    public String[] image4;
    public String[] image5;
    public String[] image6;
    public String[] image7;
    public String chosenWord;
    public char[] wordInProgress;
    public String guessedLetters;
    public int attempts;

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

    // Fill the image arrays with strings from our image file
    public void loadImages()
    {
        int cursor = 0;
        String currentLine;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("images.txt"));
            image1 = new String[19];
            for(int index = 0; index < 19; index++)
            {
                currentLine = reader.readLine();
                image1[index] = currentLine;
            }
            image2 = new String[19];
            for(int index = 0; index < 19; index++)
            {
                currentLine = reader.readLine();
                image2[index] = currentLine;
            }
            image3 = new String[19];
            for(int index = 0; index < 19; index++)
            {
                currentLine = reader.readLine();
                image3[index] = currentLine;
            }
            image4 = new String[19];
            for(int index = 0; index < 19; index++)
            {
                currentLine = reader.readLine();
                image4[index] = currentLine;
            }
            image5 = new String[19];
            for(int index = 0; index < 19; index++)
            {
                currentLine = reader.readLine();
                image5[index] = currentLine;
            }
            image6 = new String[19];
            for(int index = 0; index < 19; index++)
            {
                currentLine = reader.readLine();
                image6[index] = currentLine;
            }
            image7 = new String[19];
            for(int index = 0; index < 19; index++)
            {
                currentLine = reader.readLine();
                image7[index] = currentLine;
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
        guessedLetters = "";
        attempts = 6;
    }

    // Print out the current word in progress
    // If a letter has not been guessed yet, print a '_' character
    public void printWord()
    {
        for(int index = 0; index < chosenWord.length(); index++)
        {
            System.out.print(" " + wordInProgress[index] + " ");
        }
        System.out.print("\n");
    }
    
    // Reads a character from the keyboard, always returns a capital letter
    // If multiple letters are typed, only the first one is considered
    public char takeInput()
    {
        Scanner keyboard = new Scanner(System.in);
        String word = keyboard.nextLine();
        char letter = word.charAt(0);
        if(letter >= 97)
        {
            letter -= 32;
        }
        if(letter != '?')
        {
            guessedLetters += letter + " ";
        }
        return letter;
    }

    // Returns true if the typed letter is valid in the word
    public boolean processInput(char letter)
    {
        if(letter == '?')
        {
            help();
            return true;
        }
        boolean hasLetter = false;
        for(int index = 0; index < chosenWord.length(); index++)
        {
            char current = chosenWord.charAt(index);
            if(current == letter || current == (letter + 32))
            {
                wordInProgress[index] = letter;
                hasLetter = true;
            }
        }
        if(hasLetter)
        {
            System.out.println("Nice guess!");
        }
        return hasLetter;
    }

    // Print the image of the hangman according to how many guesses are left
    public void drawPicture()
    {
        if(attempts == 6)
        {
            for(int index = 0; index < 18; index++)
            {
                String line = image1[index];
                System.out.println(line);
            }
        }
        else if(attempts == 5)
        {
            for(int index = 0; index < 18; index++)
            {
                String line = image2[index];
                System.out.println(line);
            }
        }
        else if(attempts == 4)
        {
            for(int index = 0; index < 18; index++)
            {
                String line = image3[index];
                System.out.println(line);
            }
        }
        else if(attempts == 3)
        {
            for(int index = 0; index < 18; index++)
            {
                String line = image4[index];
                System.out.println(line);
            }
        }
        else if(attempts == 2)
        {
            for(int index = 0; index < 18; index++)
            {
                String line = image5[index];
                System.out.println(line);
            }
        }
        else if(attempts == 1)
        {
            for(int index = 0; index < 18; index++)
            {
                String line = image6[index];
                System.out.println(line);
            }
        }
        else
        {
            for(int index = 0; index < 18; index++)
            {
                String line = image7[index];
                System.out.println(line);
            }
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
        if(remaining == 0)
        {
            System.out.println("Congratulations!");
            System.out.println("You solved the word with " + attempts + " guesses left");
        }
        if(attempts == 0)
        {
            System.out.println("Better luck next time!");
            System.out.println("You only needed " + remaining + " more letters");
            System.out.println("The word was " + chosenWord);
        }
        return(remaining == 0 || attempts == 0);
    }

    // Helper function for help()
    public boolean hasBeenGuessed(char letter)
    {
        for(int index = 0; index < guessedLetters.length(); index++)
        {
            if(letter == guessedLetters.charAt(index) || letter - 32 == guessedLetters.charAt(index))
            {
                return true;
            }
        }
        return false;
    }

    // Scans through the dictionary to look for likely words based on known letters
    public void help()
    {
        int cursor = 0;
        int length = chosenWord.length();
        String[] possibleWords = new String[20000];
        for(int index = 0; index < 73020; index++)
        {
            String current = dictionary[index];
            if(current.length() == length)
            {
                boolean possible = true;
                for(int jndex = 0; jndex < length; jndex++)
                {
                    if(wordInProgress[jndex] != '_' &&
                       wordInProgress[jndex] != current.charAt(jndex) &&
                       wordInProgress[jndex] + 32 != current.charAt(jndex))
//                       hasBeenGuessed(current.charAt(jndex)))
                    {
                        possible = false;
                    }
                }
                if(possible)
                {
                    possibleWords[cursor] = dictionary[index];
                    cursor++;
                }
            }
        }
        System.out.println("The word could be:");
        for(int index = 0; index < cursor; index++)
        {
            System.out.println(possibleWords[index]);
        }
    }

    // Run the game
    public static void main(String[] args)
    {
        HangmanGame game = new HangmanGame();
        game.loadDictionary();
        game.loadGameData();
        game.loadImages();
        game.printWord();
        game.drawPicture();
        while(!game.finished())
        {
            System.out.println("So far you have guessed: " + game.guessedLetters);
            System.out.println("Please enter your guess");
            char letter = game.takeInput();
            if(!game.processInput(letter))
            {
                System.out.println("Sorry!");
                game.attempts--;
            }
            if(letter != '?')
            {
                game.drawPicture();
            }
            game.printWord();
        }
    }
}
