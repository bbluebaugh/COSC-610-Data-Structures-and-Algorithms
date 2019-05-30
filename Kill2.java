/**
*This Program is designed to remove the second letter of a provided word from the wod
*@author Bwbluebaugh
*@version 1
*/

import java.util.Scanner;

public class Kill2{
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Input your word");
		String originalWord = sc.next();
		Kill2 thisOne = new Kill2();
		thisOne.removeLetter(originalWord);
		//removeLetter(originalWord);
		sc.close();
	}
	
	public void removeLetter(String word){
		
		StringBuilder myWord = new StringBuilder(word);
		String newWord;
		myWord.deleteCharAt(1);
		
		System.out.println(myWord + "\n");
	}
	
	
}