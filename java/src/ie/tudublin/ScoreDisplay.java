package ie.tudublin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.data.TableRow;

enum NoteType {
	NOTE,
	DURATION,
}

public class ScoreDisplay extends PApplet
{
	//String score= "DEFGABcd";
   
    //String score = "D2E2F2G2A2B2c2d2";
	String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";
	char[] scoreChars = score.toCharArray();

	String scoreTypeString = "DEFGABcd";
	char[] scoreTypes = scoreTypeString.toCharArray();
	float[] scoreY = {400, 350, 290, 250, 210, 180,130,100};

	
	ArrayList<Note> notes = new ArrayList<Note>();
	private float border;
	private float noteSpacing;

	float section;

	public void settings()
	{
		size(1000, 500);

		// How to convert a character to a number
		char c = '7'; // c holds the character 7 (55)
		int i = c - '0'; // i holds the number 7 (55 - 48) 
		println(i);
	}

	public void setup() 
	{
		loadScore();
		printScore();
		border = height / 10;
	    noteSpacing = height / 10;

		section = (height - border)/scoreTypes.length ;
		System.out.println(section);
		
	}

	public void draw()
	{
		background(255);
		drawNotes();
		
	}

	public void loadScore()
	{
		Note newNote;

		char _note= ' ';
		char _duration= ' ';

		//String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";
		for(int i=0; i < scoreChars.length-1; i++){
			if(Character.isLetter(scoreChars[i]) && Character.isDigit(scoreChars[i+1])){
				_note = scoreChars[i];
				_duration = scoreChars[i+1];
				newNote = new Note(_note, Character.getNumericValue(_duration));
				notes.add(newNote);
			}else if(Character.isLetter(scoreChars[i]) && !Character.isDigit(scoreChars[i+1])){
				_note = scoreChars[i];
				_duration = '1';
				newNote = new Note(_note, Character.getNumericValue(_duration));
				notes.add(newNote);
			}
			
		}
	}

	void printScore(){
		for(int i=0; i < notes.size(); i++){
			Note n = notes.get(i);
			String noteName = n.getDuration() != 2 ? "Quaver" : "Crotchet";
			System.out.println(n.getNote() + " " + n.getDuration() + " " + noteName );
		}
	}

	void drawNotes()
	{
		stroke(255);
        fill(0);
		strokeWeight(1);//
        textAlign(CENTER, CENTER);

		//Displays the grid
        for(int i = 0; i < 5; i++){

			stroke(5);
			fill(100, 50, 50);
			float x = map(i, 0, notes.size(), border, width - border);

			float y = map(i, 0, 5, border, height - noteSpacing);

			//draw horizontal
			line(border, y, width - border, y);
        }

		//Display letter
		for(int j=0; j< notes.size(); j++){
			float x = map(j, 0, notes.size(), border, width - border);
			//int offset =(int)width/ notes.size();
			Note tempNote = notes.get(j);
			stroke(5);
			fill(100, 50, 50);
			textSize(30);
			//text(tempNote.getNote(), j * 100, border - 20);
			text(tempNote.getNote(), x, height/50);
			//text(tempNote.getNote(), j * offset, border + height/4);
		}

		//Display note
		for(int k=0; k < notes.size(); k++){
			Note tempNote = notes.get(k);
			float x = map(k, 0, notes.size(), border, width - border);
			float offset = height / 20;
			//float y = map(k, 10, 0 , border, height - noteSpacing - offset);

		    float y=0;

			for(int i=0; i < scoreTypes.length; i++){
				if(scoreTypes[i] == tempNote.getNote()){
					y = scoreY[i];
				}
			}

			//Black Circle
			noStroke();
			//fill(0);
			selectNote(x, y);
			circle(x, y, 25);

			//Black line
			//stroke(0);
			strokeWeight(3);
			line(x+8,y, x+8, y -50);
			
			//Quaver
			// if(tempNote.getDuration() == 1){
			// 	stroke(255);
			// 	fill(0);
			// 	circle(x, y, 25);
			//Crotchet
			if(tempNote.getDuration() == 2){
				//Tick
				line(x+8,y-50, x+18, y-30);
			}
		}
	}

	// public void mouseClicked(){
	// 	selectNote();
	// }

	void selectNote(float x, float y){
		if(mouseX > x-10 && mouseX < x+10 ){
			fill(255,0,0);
			stroke(255,0,0);
		}
		else
		{
			fill(0);
			stroke(0);
		}
		
	}
}
