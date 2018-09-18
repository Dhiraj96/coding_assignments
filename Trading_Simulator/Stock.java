/*
Name: Raj (Phasut) Amarnani
Section: 04
This program creates a stock object with price data avaialable for 150 days in a hashmap 
	It also stores the value of the daily data in a file table.txt
	Stock price must be at least 2 Dollars (listed security)

*/

import java.util.Random;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Stock{

	//Instance variable for price and array of prices for 150 timesteps
	private double price;
	private double[] historical = new double[151];

	//Construct stock object with price as parameter 
	public Stock(double price){
		this.price = price;
		//Set first element in array of prices to initial price
		historical[0] = price;
		//Call CreatePrice function to create price when Stock object constructed
		CreatePrice();
	}

	//Accessor Method to get Value of stock for particular timestep
	public double GetValue(int key){
		Double value = historical[key];
		return value;
	}

	//Simulates random walk of stock prices
	public void CreatePrice(){
		//delta is average movement
		double delta;
		double newprice = price;
		Random random = new Random();
		for(int i = 1; i <= 150; ++i){
			//different delta for every timestep(how much price moves or daily volatility)
			//50-50 for delta to be positive or negative (since normal distribution with mean=0 sd=1)
			delta = random.nextGaussian();

			//If new price + delta is less than 50 and greater than zero, than add delta, else subtract 
				//Corner cases for drawing window scale
			if(newprice + delta < 50 && newprice + delta > 0){
			newprice += delta;
			}
			else{newprice -= delta;}

			historical[i] = newprice;
		}
	}


	//Stores price data in file table.txt
	public void PrintData()  throws FileNotFoundException{
		PrintWriter table = new PrintWriter("table.txt");
		//For all elements in historical array of prices, print with following format
		for(int i = 0; i < historical.length; ++i){
			table.printf("Day: " + i + " Price: $ %2.2f", historical[i]);
			table.println();
		}
		table.flush();
		table.close();
	}
}