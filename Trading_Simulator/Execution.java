/*
Name: Raj (Phasut) Amarnani
Section: 04
This program creates executes the three objects (test)

*/

public class Execution{
	public static void main(String[] args){
		Stock a = new Stock(30);
		Portfolio b = new Portfolio(100000);
		b.AddPosition(new Position(200,a.GetValue(0)));


		b.AddPosition(new Position(-1000, a.GetValue(2)));

		Double pnl = b.GetPnL(a,4);
		Double value = b.GetValue();
		Double net = b.NetPosition(4);

		String positions = b.toString();

		System.out.println("Stock Price Day 0: " + a.GetValue(0));
		System.out.println("Stock Price Day 1: " + a.GetValue(1));
		System.out.println("Stock Price Day 2: " + a.GetValue(2));
		System.out.println("Stock Price Day 3: " + a.GetValue(3));
		System.out.println("Stock Price Day 4: " + a.GetValue(4));
		System.out.println(positions);
	}
}

/*
 ISSUES WITH CODE:
 1. Portfolio value updating
 2. Buy/Sell similar, how to integrate into 1
 3. Portfolio.toString to print portfolio stuff? how to calculate updated value?
 4. General structure of graphics, uneeded code/shortening of code
 */


