/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private static final int NUM_TRYING = 8; 
	
	private RandomGenerator rgen = new RandomGenerator().getInstance();
	
	private HangmanCanvas canvas;
	
	public void init() {
		setSize(1200, 800);
		canvas = new HangmanCanvas(600, 800);
		add(canvas);
	}
	
    public void run() {
    	setup(); 
    	
    	playHangMan();
	}
    
    private void setup() { //Before begin the game
    	println("Welcome to Hangman!");
    }
    
    private void playHangMan() {
    	multiplePlaying(); // With the help of this method, the player can play multiple times
    }
    
 // With the help of this method, the player can play multiple times
    private void multiplePlaying() {
    	HangmanLexicon words = new HangmanLexicon(); //create words constructor which is type of HengmanLexicon class
    	String word; //declaration word
    	
    	//if player wants to play multiple times then method multiplePlaying must have while loop in implementation 
    	//this method contain three main method
    	//with help of first method, program chooses one word from lexicon
    	//second method is for one playing loop and third method is restart
    	while(true) {
    		canvas.reset();

    		word = words.getWord(rgen.nextInt(words.getWordCount())); //program chooses one word from lexicon
    		
    		//this method have 2 parameters, first is word which is chosen by program during playing and second is number of trying
    		oneTimePlaying(word, NUM_TRYING);
    		
    		//if player wants to restart game then player must enter special symbols in console. special symbols are 'r' or 'R'
    		restart();
    	}
    }
    
    private void oneTimePlaying(String word, int numTrying) {
    	/*when player enters symbol in console then program would print word in which will be letters which are entered by player or 
    		may not be letters and program only print '-' because of this i create variable wordLookLike
    	*/
    	String wordLookLike = "";
    	char letter = ' ';
    	
    	//before player enters first letter in console word which will guess player looks like about this" - - - - - "
    	wordLookLike = wordFirstLook(word);
    	canvas.displayWord(wordLookLike, letter);
    	
    	//player have some trying because of this i use while loop
    	//this while loop have condition which determines players can if can't  continues playing 
    	//condition have three sub conditions
    	while(conditionOfPlaying(word, wordLookLike, numTrying)) {
    		aboutPlaying(wordLookLike, numTrying); //this method print all informations about playing
    		
    		//if it doesn't matter that which letter (low case if upper case) will be entered by player for this program can see all entered letter as or low case
    		//or upper case, in this case i chose upper case
    		//enterLetter returns letter which is entered by player
    		letter = Character.toUpperCase(enterLetter());
    		
    		//when player enters letter
    		wordLookLike = checkLetterInWord(word, letter, wordLookLike);
    		canvas.displayWord(wordLookLike, letter);
    		
    		//player has 8 trying and this method count number of trying
    		numTrying = checkNumTrying(numTrying, word, letter);
    		println();
    	}
    	
    	println("The word was: " + word);
    	
    	if(numTrying == 0) {
    		println("You Lose");
    		println();
    	}else {
    		println("YOU WON");
    		println();
    	}
    }
    
    private String wordFirstLook(String word) {
    	String wordLookLike = "";
    	for(int i = 0; i < word.length(); i++) {
    		wordLookLike += "- ";
    	}
    	return wordLookLike;
    }
    
    //if trying is zero or all letter is guessed then game must end
    private boolean conditionOfPlaying(String word, String wordLookLike, int numTrying) {
    	if(numTrying != 0 && playerWins(wordLookLike)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    //if all places are full then player is winner if not :(
    private boolean playerWins(String wordLookLike) {
    	boolean win = false;
    	for(int i = 0; i < wordLookLike.length()/2; i++) {
    		if(wordLookLike.charAt(2 * i) == '-') {
    			win = true;
    		}
    	}
    	return win;
    }
    
    //This method prints how the word looks now and how many attempts the player has left
    private void aboutPlaying(String wordLookLike, int numTrying) {
    	println("The word now looks like this: " + wordLookLike);
    	println("You have " + numTrying + " guesses left.");
    }
    
//with help of this method players can enter only one letter. if entered symbol isn't letter then programs print relevant text and it gives chance players again   
    private char enterLetter() {
    	//players enters String symbol which size is one then program take first symbol from this string
    	String letterString = "";
    	char letter = 0;
    	
    	while(true) {
    		letterString = readLine("Your guess: ");
    		if(!letterString.equals("")) {
    			letter = letterString.charAt(0);
    		}	
    		//if entered letter isn't letter then player must enter symbol again
    		if(conditionOfEnterLetter(letterString)) {
    			println();
    			println("Enter only one letter. try again!");
    		}else {
    			break;
    		}
    	}
    	return letter;
    }
    
  //if size of symbol isn't 1 or isn't letter return true
    private boolean conditionOfEnterLetter(String letterString) {
    	if(letterString.length() != 1 || !Character.isLetter(letterString.charAt(0))) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    //this method checks that is entered letter in word and return wordLookLike
    private String checkLetterInWord(String word, char letter, String wordLookLike) {
    	String newWordLookLike = "";
    	for(int i = 0; i < word.length(); i++) {
    		if(letter == word.charAt(i)) {
    			newWordLookLike += letter + " ";
    		}else {
    			newWordLookLike += wordLookLike.charAt(2 * i) + " ";
    		}
    	}
    	return newWordLookLike;
    }
    
    private int checkNumTrying(int numTrying, String word, char letter) {
    	boolean decrease = true;
    	for(int i = 0; i < word.length(); i++) {
    		if(letter == word.charAt(i)) {
    			decrease = false;
    		}
    	}
    	if(decrease) {
    		canvas.noteIncorrectGuess(); //draw relevant part of body
    		return --numTrying;
    	}else {
    		return numTrying;
    	}
    }
    
    
    private void restart() {
    	println("If you want to restart game enter only 'r' or 'R' ");
    	String rest = "r";
    	while(true) {
    		rest = readLine("Enter symbol: ");
    		if(rest.equals("r") || rest.equals("R")) {
    			println();
    			break;
    		}else {
    			println();
    			println("Incorect symbol. Try again!");
    		}
    	}
    	
    }
}
