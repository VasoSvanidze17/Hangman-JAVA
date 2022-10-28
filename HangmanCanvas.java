/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	public HangmanCanvas(int sizeX, int sizeY) {
		this.setSize(sizeX, sizeY);
	}
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		enteredLetters.setLabel("");
		partOfBody = 0;
		drawScaffold();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String wordLookLike, char letter) {
		//draw word Which is similar to a guessing word
		wordLook.setLabel(wordLookLike); 
		wordLook.setFont("SenSerif-Bold-30");
		add(wordLook, (getWidth() - wordLook.getWidth())/2, getHeight()/2 + getHeight()/8);
		
		//draw letters which is entered by player
		enteredLetters.setLabel(enteredLetters.getLabel() + letter); 
		enteredLetters.setFont("SenSerif-Bold-15");
		add(enteredLetters, 0, getHeight()/2 + getHeight()/5);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess() {
			partOfBody++;
			
			drawHead();
			drawBody();
			drawLeftHand();
			drawRightHand();
			drawLeftLeg();
			drawRightLeg();
			drawLeftFoot();
			drawRightFoot();
		
		if(partOfBody == NUM_TRYING) {
			partOfBody = 0;
		}
	}
	
	private void drawScaffold() {
		GLine scaffold = new GLine(getWidth()/2 - BEAM_LENGTH, getHeight()/2, getWidth()/2 - BEAM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT);
		GLine beam = new GLine(getWidth()/2 - BEAM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT);
		GLine rope = new GLine(getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT, getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}
	
	private void drawHead() {
		if(partOfBody == 1) {
			GOval head = new GOval(2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
			add(head, getWidth()/2 - HEAD_RADIUS, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		}
	}
	
	private void drawBody() {
		if(partOfBody == 2) {
			GLine body = new GLine(getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS, 
									getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
			add(body);
		}
	}
	
	private void drawLeftHand() {
		if(partOfBody == 3) {
			GLine upperArm = new GLine(getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2 - UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			
			GLine lowArm = new GLine(getWidth()/2 - UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, 
					getWidth()/2 - UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			
			add(upperArm);
			add(lowArm);
		}
	}

	private void drawRightHand() {
		if(partOfBody == 4) {
			GLine upperArm = new GLine(getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			
			GLine lowArm = new GLine(getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, 
					getWidth()/2 + UPPER_ARM_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			
			add(upperArm);
			add(lowArm);
		}
	}
	
	private void drawLeftLeg() {
		if(partOfBody == 5) {
			GLine hip = new GLine(getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					getWidth()/2 - HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
			
			GLine leg = new GLine(getWidth()/2 - HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					getWidth()/2 - HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			
			add(hip);
			add(leg);
		}
	}
	
	private void drawRightLeg() {
		if(partOfBody == 6) {
			GLine hip = new GLine(getWidth()/2, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					getWidth()/2 + HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
			
			GLine leg = new GLine(getWidth()/2 + HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					getWidth()/2 + HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			
			add(hip);
			add(leg);
		}
	}
	
	private void drawLeftFoot() {
		if(partOfBody == 7) {
			GLine foot = new GLine(getWidth()/2 - HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					getWidth()/2 - HIP_WIDTH - FOOT_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			
			add(foot);
		}
	}
	
	private void drawRightFoot() {
		if(partOfBody == 8) {
			GLine foot = new GLine(getWidth()/2 + HIP_WIDTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					getWidth()/2 + HIP_WIDTH + FOOT_LENGTH, getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			
			add(foot);
		}
	}
	
	//instance variable, this variable helps us to determine which part of body must draw on canvas when player enters incorrect letter
	private int partOfBody = 0;
	
	//instance variables,
	private GLabel wordLook = new GLabel(""); //for drawing word Which is similar to a guessing word
	private GLabel enteredLetters = new GLabel(""); //for drawing letters which is entered by player

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private static final int NUM_TRYING = 8;

}
