/*
Name: Raj (Phasut) Amarnani
Section: 04
This program creates a position object responsible for holding the price and size of the position 

*/

public class Position{
	//Each position has a share size and a price
		//Negative shares indicate a selling position, and price determined by stock price at creation
	private int shares;
	private double price;

	//Constructor for position initializing instance variables
	public Position(int shares, double price){
		this.shares = shares;
		this.price = price;
	}

	//Accessor methods for shares, price and position size
	public int GetShares() {return shares;}
	public double GetPrice() {return price;}
	public double GetSize() {return shares*price;}

	//ToString function that prints out the position and price
	public String toString(){return "Position: " + shares + " @  $" + String.format("%2.2f%n",price);}
}