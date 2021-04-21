package ie.tudublin;

import java.util.ArrayList;


import processing.core.PApplet;
import processing.data.TableRow;

public class ScoreDisplay extends PApplet
{
	//String score = "DEFGABcd";
	String score = "D2E2F2G2A2B2c2d2";
	//String score = "DEF2F2F2EFA2A2B2AFD2E2D2D2D2";
	char[] scoreChars = score.toCharArray();
	
	ArrayList<Note> notes = new ArrayList<Note>();
	private float border = 100;

	public float leftMargin;
	public float margin;

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
		// System.out.println("Note");
		// for(int i=0; i < scoreChars.length; i++){
		// 	System.out.println(scoreChars[i]);
		// }

		// leftMargin = height * 0.2f;
		// margin = height * 0.05f;
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
		// System.out.println("Debug ");
		// for(int i=0; i < notes.size(); i++){
		// 	Note n = notes.get(i);
		// 	System.out.println(n);
		// }

		
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
        textAlign(CENTER, CENTER);

		//Displays the grid
        for(int i = 0; i < 6; i++){

			// // //Choose where to display
            // //float x = map(i, 1, 5, leftMargin, width - margin);
			// //float y = map(i, 1, 5, leftMargin, width - margin);
			// float y = map(i, 1, 5, 200, 200);

			// //Displays the grid
            // line(margin, y, height - margin, y );

			// //Display the grid number
            // text(y, i, margin / 20);

			//float x = map(i, 0, 5, border, width - border);
			float x = map(i, 0, notes.size(), border, width - border);

			float y = map(i, 0, 5, border, height - border);

			//draw horizontal
			line(border, y, width - border, y);

			// stroke(5);
            // fill(100, 50, 50);
            // textSize(30);
            // text(i, x, border - 20);

			//vertical text
			for(int j=0; j< notes.size(); j++){
				int offset =(int)width/ notes.size();
				Note tempNote = notes.get(j);
				stroke(5);
				fill(100, 50, 50);
				textSize(30);
				//text(tempNote.getNote(), j * 100, border - 20);
				text(tempNote.getNote(), j * offset, border + 20);
			}
        }

	

		
	}
}
