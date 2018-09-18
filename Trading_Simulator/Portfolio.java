/*
Name: Raj (Phasut) Amarnani
Section: 04
This program creates a portfolio object that holds its value and an arraylist of positions

*/

import java.util.ArrayList;

public class Portfolio{
	//Instance variables of position and arraylist of positions
	private double value;
	ArrayList<Position> positions = new ArrayList<Position>();

	//Construct portfolio with value 
	public Portfolio(double value){
		this.value = value;
	}

	//Accessor method that returns portfolio value
	public double GetValue(){
		return value;
	}

	//Function that returns the profit and loss of portfolio for particular stock on particular day
	public double GetPnL(Stock a, int day){
		double pnl = 0;
		//Get Stock price of the day
		double p = a.GetValue(day);
		//Run through array list of positions to determine position value at particular timestep
		for(int i = 0; i < positions.size(); ++i){
			pnl += (p-positions.get(i).GetPrice()) * positions.get(i).GetShares();
		}
		return pnl;
	}

	//Add position to arraylist position in portfolio object
	public void AddPosition(Position pos){
		positions.add(pos);
	}

	//Computes net long/short position
	public double NetPosition(int day){
		double net = 0;
		//Cycle through positions and add up net sizes for portfolio
		for(Position pos : positions){
			net += pos.GetSize();
		}
		return net;
	}


	//Printing positions in our portfolio (file output with results)
	public String PrintPositions(){
		String positions = "";
		//Cycle through positions and add toStrings for Position  
		for(Position pos : this.positions){
			positions += pos.toString();
		}
		return positions;
	}

	//toString representation of portfolio(Prints out positions)
	public String toString(){
		String string = "These are the positions in your portfolio: " + "\n" + PrintPositions();
		return string;
	}


}