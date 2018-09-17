//Authoer: Dhiraj Amarnani, Oliver Cho
//Date: 29th November 2017

import java.util.*;
import java.awt.geom.Point2D;
import java.lang.*;
import java.util.Scanner;
import java.io.*;


//*******************************************************************
//* This class implements a spellchecker for java. It reads in 
//* 2 seperate (a long and short) dictionaries from the current 
//* directory. It takes a sentence from the user in standard input 
//* and returns the corrected (spellchecked) version to standard
//* output. 
//* 
//*******************************************************************
public class SpellCheck{ 

	//stores the co-ordinates for each letter
	private static Map<Character,Point2D> myLetters;
	//stores the short and long dictionary arrays to be used in our spellchecker
	private static String[] shortDictionary;
	private static String[] longDictionary;
	
	public static void main(String args[]){
		
		//Populates Hashmap of letter co-ordinates. 
		myLetters = new HashMap<Character,Point2D>();
		populateHashMap();


		//populate arrays of short and long dictionaries
		shortDictionary = new String[73];
		longDictionary = new String[121806];
		populateDictionaryArrays("short.txt", "long.txt");


		//Take user submitted input, return corrected sentence
		Scanner scanner1 = new Scanner(System.in);

		System.out.println("Please Enter a Sentence: ");
		String nextLine = scanner1.nextLine();
		Scanner scanner2 = new Scanner(nextLine);

		String Solution = " ";

		//reads in sentence word by word
		while(scanner2.hasNext()){

			double shortest = Double.POSITIVE_INFINITY;
			int short_index = 0;

			String curr = scanner2.next().toUpperCase();

			//computes index in longDictionary with shortest edit distance to current words being processed
			for(int i = 0; i < longDictionary.length; ++i){

				if(computeDistanceWords(longDictionary[i], curr) < shortest){

					shortest = computeDistanceWords(longDictionary[i], curr);
					short_index = i;
				}
			}

			//stores current solution and appends to solution string 
			Solution += longDictionary[short_index] + " ";

		}

		//return the solution string to standard output 
		System.out.println(Solution);
	}


	//*******************************************************************
	//* Computes the edit distance between two words passed as arguments
	//* to the function.
	//* 
	//* Pre-Conditions: Strings word1 and word2 to be compared
	//* 
	//* Post-Conditions: A double that represents the edit distance
	//*******************************************************************
	public static double computeDistanceWords(String word1, String word2){

		//add empty string to beggining of word1 and word2 to handle base cases
		String S1 = " ".concat(word1);
		String S2 = " ".concat(word2);

		int n=S1.length();
		int m=S2.length();

		double [][] M= new double [n][m];

		//base cases for our solution array M 
		for(int i = 0; i < n; ++i){

			for(int j = 0; j < m; ++j){

				if(i == 0 && j == 0){

					M[i][j] = 0;
				}

				if(j == 0){

					M[i][j] = 6*i;
				}

				if(i == 0){

					M[i][j] = 7*j;
				}

			}
		}

		//Dynamic programming solution for out solution array
		for(int i=1; i<n; i++){

			for(int j=1; j<m; j++){

				if(S1.charAt(i)==S2.charAt(j)){

					M[i][j]=M[i-1][j-1];
				}
				else{

					double modify=M[i-1][j-1]+computeDistanceLetters(S1.charAt(i),S2.charAt(j));
					double delete=M[i][j-1]+6;
					double add=M[i-1][j]+7;

					M[i][j]=Math.min(Math.min(modify,delete),add);
				}
			}
		}

		//return bottom right element in our solution array to determine edit distance of words
		return M[n-1][m-1];
	}

	//*******************************************************************
	//* The function takes two characters and computes the distance 
	//* between the two letters based on the prompt assigned to us. 
	//* 
	//* Pre-Conditions: Two characters a,b whose distance apart is to be
	//* calculated
	//* 
	//* Post-Conditions: The double distance between the two characters
	//*
	//*******************************************************************
	public static double computeDistanceLetters(Character a, Character b){

		//reads the characters from the hashmap and computes the euclidean distance between them 
		return myLetters.get(a).distance(myLetters.get(b));
	} 

		
	//*******************************************************************
	//* This function manually populates our hasmap to hold the key (char)
	//* value (Point) pairs assigned to each letter in our keyboard. 
	//* 
	//*******************************************************************
	public static void populateHashMap(){

		myLetters.put('0', new Point2D.Double(0,0));
		myLetters.put('Q', new Point2D.Double(0,2));
		myLetters.put('W', new Point2D.Double(1,2));
		myLetters.put('E', new Point2D.Double(2,2));
		myLetters.put('R', new Point2D.Double(3,2));
		myLetters.put('T', new Point2D.Double(4,2));
		myLetters.put('Y', new Point2D.Double(5,2));
		myLetters.put('U', new Point2D.Double(6,2));
		myLetters.put('I', new Point2D.Double(7,2));
		myLetters.put('O', new Point2D.Double(8,2));
		myLetters.put('P', new Point2D.Double(9,2));
		myLetters.put('A', new Point2D.Double(0.3333,1));
		myLetters.put('S', new Point2D.Double(1.3333,1));
		myLetters.put('D', new Point2D.Double(2.3333,1));
		myLetters.put('F', new Point2D.Double(3.3333,1));
		myLetters.put('G', new Point2D.Double(4.3333,1));
		myLetters.put('H', new Point2D.Double(5.3333,1));
		myLetters.put('J', new Point2D.Double(6.3333,1));
		myLetters.put('K', new Point2D.Double(7.3333,1));
		myLetters.put('L', new Point2D.Double(8.3333,1));
		myLetters.put('Z', new Point2D.Double(0.6666,0));
		myLetters.put('X', new Point2D.Double(1.6666,0));
		myLetters.put('C', new Point2D.Double(2.6666,0));
		myLetters.put('V', new Point2D.Double(3.6666,0));
		myLetters.put('B', new Point2D.Double(4.6666,0));
		myLetters.put('N', new Point2D.Double(5.6666,0));
		myLetters.put('M', new Point2D.Double(6.6666,0));
	}


	//*******************************************************************
	//* This function populates our global dictionary arrays for this 
	//* class. It reads the files from the current directory and stores
	//* each words in order into two seperate arrays. 
	//* 
	//*******************************************************************
	public static void populateDictionaryArrays(String short_dictionary, String long_dictionary){

		try{
			//scan in shorter dictionary
			Scanner scanner_short = new Scanner(new File(short_dictionary));

			int index = 0;

			while(scanner_short.hasNext()){

				shortDictionary[index] = scanner_short.next();
				index++;
			}

			//scan in longer dictionary 
			Scanner scanner_long = new Scanner(new File(long_dictionary));

			index = 0;

			while(scanner_long.hasNext()){

				longDictionary[index] = scanner_long.next();
				index++;
			}

		}
		catch(Exception e){
			//if file not in current directory or another issue with reading file 
			System.out.println("Issue with reading File: " + e);
		}

	}

}