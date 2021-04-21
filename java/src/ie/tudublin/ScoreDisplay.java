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
		// System.out.println("Note");
		// for(int i=0; i < scoreChars.length; i++){
		// 	System.out.println(scoreChars[i]);
		// }
	}

	public void draw()
	{
		background(255);
		
	}

	public void loadScore()
	{
		// TableRow table = loadTable("tasks.csv", "header");
        // for(TableRow row:table.rows())
        // {
        //     Note n = new Note(row);
        //     notes.add(n);
        // }
		Note newNote;

		char _note= ' ';
		char _duration= ' ';
		//int stringLength = score.length();
		for(int i=0; i < scoreChars.length; i++){
			if(i == 0 ){
				_note = scoreChars[i];
				System.out.println(_note);
			}else {
				if(i % 2 == 0){
					_note = scoreChars[i];
					System.out.println(_note);
				}
				else if(i % 2 == 1 ){
					//System.out.println("My Turn");
					_duration = scoreChars[i];
					System.out.println(_duration);
					newNote = new Note(_note, Character.getNumericValue(_duration));
					notes.add(newNote);
				}
			}
		}
	
		// System.out.println("Debug ");
		// for(int i=0; i < notes.size(); i++){
		// 	Note n = notes.get(i);
		// 	System.out.println(n);
		// }
	}

	void drawNotes()
	{

	}
}
