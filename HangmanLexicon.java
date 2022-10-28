/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.program.ConsoleProgram;

public class HangmanLexicon extends ConsoleProgram {

	private ArrayList<String> words = new ArrayList<String>(); //create ArrayList for saving word which is from HangmanLexicon.txt file
	
	public HangmanLexicon() {
		//try to read HangmanLexicon.txt file and save all line in arrayList 
		try {
			BufferedReader lexicon = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			String word;
			
			//read and save each line from lexicon file until (readLine == null)
			while(true) {
				word = lexicon.readLine();
				
				if(word == null) {
					lexicon.close();
					break;
				}else {
					words.add(word);
				}
			}
			
		}catch(IOException e){
			
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return words.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return words.get(index);
	}
}
