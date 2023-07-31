import java.util.Scanner;

public class Hangman {
    private String[] words = {"programming", "hangman", "parallelepiped", "dinosaur", "strawberry"};
    private String word;
    private char[] guessedLetters;
    private int attemptsLeft;

    public Hangman() {
        word = getRandomWord();
        guessedLetters = new char[word.length()];
        attemptsLeft = 6;
    }

    public void play() {
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.println("Guessed letters: " + String.valueOf(guessedLetters));
            System.out.print("Enter a letter: ");
            char letter = scanner.nextLine().charAt(0);

            if (isLetterAlreadyGuessed(letter)) {
                System.out.println("You already guessed that letter. Try again.");
                continue;
            }

            if (isLetterInWord(letter)) {
                updateGuessedLetters(letter);
                if (isWordGuessed()) {
                    gameOver = true;
                    System.out.println("Congratulations! You guessed the word: " + word);
                }
            } else {
                attemptsLeft--;
                if (attemptsLeft == 0) {
                    gameOver = true;
                    System.out.println("Game over! The word was: " + word);
                }
            }
        }

        scanner.close();
    }

    private String getRandomWord() {
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }

    private boolean isLetterAlreadyGuessed(char letter) {
        for (char guessedLetter : guessedLetters) {
            if (guessedLetter == letter) {
                return true;
            }
        }
        return false;
    }

    private boolean isLetterInWord(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                return true;
            }
        }
        return false;
    }

    private void updateGuessedLetters(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessedLetters[i] = letter;
            }
        }
    }

    private boolean isWordGuessed() {
        for (char guessedLetter : guessedLetters) {
            if (guessedLetter == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Hangman game = new Hangman();
        game.play();
    }
}