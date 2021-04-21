package ie.tudublin;

import java.util.ArrayList;


import processing.core.PApplet;
import processing.data.TableRow;

enum NoteType {
	NOTE,
	DURATION,
}

public class ScoreDisplay extends PApplet
{
	//String score = "DEFGABcd";
	String score = "D2E2F2G2A2B2c2d2";
	//String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";
	char[] scoreChars = score.toCharArray();
	
	ArrayList<Note> notes = new ArrayList<Note>();
	private float border;
	private float noteSpacing;

	private boolean[] isSelected;

	// public float leftMargin;
	// public float margin;



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
		// leftMargin = height * 0.2f;
		// margin = height * 0.05f;
		border = 100;
	    noteSpacing = 100;
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

		//int stringLength = score.length();
		for(int i=0; i < scoreChars.length; i++){
			if(i == 0 ){
				_note = scoreChars[i];
				//System.out.println(_note);
			}else {
				if(i % 2 == 0){
					_note = scoreChars[i];
					//System.out.println(_note);
				}
				else if(i % 2 == 1 ){
					//System.out.println("My Turn");
					_duration = scoreChars[i];
					//System.out.println(_duration);
					newNote = new Note(_note, Character.getNumericValue(_duration));
					notes.add(newNote);
				}
			}
		}
	}

	void printScore(){
		for(int i=0; i < notes.size(); i++){
			Note n = notes.get(i);
			String noteName = n.getDuration() > 2 ? "Quaver" : "Crotchet";
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


		isSelected = new boolean[notes.size()];

		//Display note
		for(int k=0; k < notes.size(); k++){
			Note tempNote = notes.get(k);
			float x = map(k, 0, notes.size(), border, width - border);
			//float y = map(k, 0, 10, border, height - noteSpacing);
			float offset = height / 20;
			//float y = map(k, 10, 0 , border, height - noteSpacing - offset);
			float y = map(k, 9, 0 , border, height - noteSpacing - offset);
			//Quaver
			if(tempNote.getDuration() == 1){
				stroke(255);
				fill(0);
				circle(x, y, 25);
			//Crotchet
			}else if(tempNote.getDuration() == 2){
				stroke(255);
				fill(0);
				circle(x, y, 25);

				// stroke(255);
				// strokeWeight(10);
				stroke(0);
				strokeWeight(3);
				//fill(0,0,0);
				line(x+8,y, x+8, y -50);
				//line(x+30,y+ 200, x+45, y +200);
			}
		}
	}
}
