/*
Name: Raj (Phasut) Amarnani
Section: 04
This program executes the graphics of my program as well as utilizes all the object created

*/

import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Graphics{

	//Enum class to store gamestates (windows)
	public enum State {TITLE, PAUSE, PLAY, BUY, SELL, QUIT, END}

	//To Store gamestate
	State state;

	//Store key pressed and mouse co-ordinates, keep track of frames, and create stock and portfolio object
	char key;
	double mouseX;
	double mouseY;
	int frames;
	//Price and portfolio metrics update every 50 frames
	int j = frames/50;
	Stock stock = new Stock(30);
	Portfolio portfolio = new Portfolio(100000);

	//Switch statement for draw
	public void Draw(){
		switch(state){
			case TITLE:
				DrawTitle();
				break;
			case PAUSE:
				DrawPause();
				break;
			case PLAY: 
				DrawPlay();
				break;
			case QUIT:
				DrawQuit();
				break;
			case END:
				DrawEnd();
				break;
			case BUY:
				DrawBuy();
				break;
			case SELL:
				DrawSell();
				break;
			default:
				break;
		}
	}

	//Show drawings
	public void Show(){
		StdDraw.show();
		StdDraw.pause(16);
	}

	//Clears Window
	public void Clear(){
		StdDraw.clear();
	}


	//Process input (depends on keys and mouse pressed) 
	public void ProcessInput() throws FileNotFoundException {
		if(StdDraw.hasNextKeyTyped()){
			key = StdDraw.nextKeyTyped();
			switch(state){
				//On Title Page, press s/S to start or q/Q to quit game
				case TITLE:
					if(key == 's' || key == 'S') {state = State.PAUSE;}
					else if (key == 'q' || key == 'Q') {System.exit(0);}
					break;
				//If on Quit page, press y/Y to quit or n/N to continue game
				case QUIT:
					if(key == 'y' || key == 'Y') {System.exit(0);}
					else if (key == 'n' || key == 'N') {state = State.PLAY;}
					break;
				//For both buy and sell windows, press c to cancel purchase and resume else
					//Type integer in (catch numbers that are not integers) to make purchase/sale x100 shares
				case BUY:
				case SELL:
					if(key == 'c' || key == 'C'){state = State.PLAY;}
					else{
						try{
							//Call Transaction function that creates position based on state
							Transaction(Integer.parseInt(String.valueOf(key)));
							state = State.PLAY;
						}		
						//If number not inputted, flashes exception for 2 second, then resumes simulation
						catch(NumberFormatException e){
							StdDraw.text(90,-8,"Please Enter a Valid Number");
							StdDraw.pause(2000);
						}
					}
					break;
				case END:
					//After Simulation ends, call PrintReport Function to print report of performance
					PrintReport();
					break;
				default:
					break;
			}
		}

		if(StdDraw.mousePressed()){
			mouseX = StdDraw.mouseX();
			mouseY = StdDraw.mouseY();
			switch(state){
				//If Paused, click within Pause rectangle to change to play
				case PAUSE:
					if(mouseX < 160 && mouseX > 130 && mouseY < -23 && mouseY > -37){
						state = State.PLAY;
					}
					break;
				//If Play, access Pause, Quit, Buy & Sell screens based on where mouse is clicked
				case PLAY:
					if(mouseX < 160 && mouseX > 130 && mouseY < -23 && mouseY > -37){
						state = State.PAUSE;
					}
					else if(mouseX < 120 && mouseX > 90 && mouseY < -23 && mouseY > -37){
						state = State.QUIT;
					}
					else if(mouseX < 40 && mouseX > 10 && mouseY < -23 && mouseY > -37){
						state = State.BUY;
					}
					else if(mouseX < 80 && mouseX > 50 && mouseY < -23 && mouseY > -37){
						state = State.SELL;
					}
					break;
				default:
					break;	
			}
		}
	}

	//Transaction Function that takes key as parameter (key typed by user)
	public void Transaction(int key){
		switch(state){
			//If we try to buy, create position object
				//check if net positions in portfolio exceed 100,000
			case BUY:
				Position pos_buy = new Position(key*100, stock.GetValue(j));
				
				if(portfolio.NetPosition(j) + pos_buy.GetSize() < portfolio.GetValue()){
					if(key > 0 && key < 10){
						portfolio.AddPosition(pos_buy);
					}
				}
				//If net > portfolio value, no more cash remains and flash warning message for 2 seconds
				else{
					StdDraw.text(90,-8,"Net Position will exceeds $100,000: Cannot Buy More");
					StdDraw.pause(2000);
				}
				break;
			//If we try to sell, create position object
				//check if net positions above maximum short position threshold (margin requirement)
			case SELL:
				Position pos_sell = new Position(-key*100, stock.GetValue(j));
				//If net < -portfolio value, cannot short anymore (limited) due to margin 
				if(portfolio.NetPosition(j) + pos_sell.GetSize() > -portfolio.GetValue()){
					if(key > 0 && key < 10){
						portfolio.AddPosition(pos_sell);
					}
				}
				//If net < -portfolio value, then margin requirement no longer met, flash warning message for 2 seconds
				else{
					StdDraw.text(90,-8,"Net Position will be lower than -$100,000: Cannot Sell More");
					StdDraw.pause(2000);
				}				
				break;
			default:
				break;
		}
	}	

	//Initialize graphics object to be title page (State.TITLE)
	public Graphics(){
		state = State.TITLE;
		mouseX = mouseY = 0;
		frames = 0;
	}

	//Initialize window
	public void InitializeGraphics(){
		StdDraw.setCanvasSize(1350,750);
		StdDraw.setXscale(0, 180);
   		StdDraw.setYscale(-40, 60);
    	StdDraw.enableDoubleBuffering();
	}


	//Draw Title Screen
	public void DrawTitle(){
		StdDraw.picture(90,10,"title.jpg",80,80);
		StdDraw.setPenRadius(0.03);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(90,52,"Welcome to Raj's Trading Simulator");
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(90,-20,"Press   s: Start   q: Quit");
	}

	//Main Screen function to use for many of the other states 
	private void DrawMain(){
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(25,56,"Profit/Loss: ");
		StdDraw.text(65,56,"Current Price: ");
		StdDraw.text(105,56,"Portfolio Value: ");
		StdDraw.text(145,56,"NetPosition: ");
		StdDraw.setPenRadius(0.001);
		StdDraw.line(0,-12,180,-12);


		//Creates Grid and axis labels for chart
		StdDraw.text(4,50,"Price");
		for(int i = 0; i <= 50; i += 5){
			StdDraw.text(12,i,"" + i);
			StdDraw.line(15,i,165,i);
		}	

		StdDraw.text(160,-8,"Days");
		for(int j = 15; j <= 165; j += 10){
			StdDraw.text(j,-3,"" + (j-15));
			StdDraw.line(j,0,j,50);
		}


		//Draw rectangles where if mouse clicked within boundries, state changes 
		StdDraw.setPenColor(StdDraw.RED);
		for(int k = 25; k <= 145; k += 40){
			StdDraw.rectangle(k,-30,15,7);
		}

		//Draw Words in Rectangles
		StdDraw.text(25,-30,"Buy");
		StdDraw.text(65,-30,"Sell");
		StdDraw.text(105,-30,"Quit");
		StdDraw.text(145,-30,"Start/Pause");
	}


	//Draw Pause Screen
	public void DrawPause(){
		DrawMain();
		StdDraw.text(90,-15,"To Start/Continue Simulation, Press Start");
		StdDraw.circle(130,-37,0.5);
		StdDraw.circle(160,-37,0.5);
		StdDraw.circle(130,-23,0.5);
		StdDraw.circle(160,-23,0.5);
	}

	//Draw Play screen 
	public void DrawPlay(){
		frames++;
		j = frames/50;
		DrawMain();
		//Keep track of days passed
		StdDraw.text(10,-18,"Day: " + j);
		//Every 100 frames, draw line to the next stock price
		DrawStock(j);
		//Update PnL, Stock Price, Portfolio Value and Net Position 
		StdDraw.text(38,56,"$ " + String.format("%2.2f%n",portfolio.GetPnL(stock,j)));
		StdDraw.text(77,56,"$ " + String.format("%2.2f%n",stock.GetValue(j)));
		StdDraw.text(120,56,"$ " + String.format("%2.2f%n",portfolio.GetValue()+portfolio.GetPnL(stock,j)));
		StdDraw.text(158,56,"$ " + String.format("%2.2f%n", portfolio.NetPosition(j)));
	}

	//Draw quit screen 
	public void DrawQuit(){
		DrawMain();
		StdDraw.text(90,-15,"Are You Sure you would like to quit?");
		StdDraw.text(90,-18,"y: Yes   n: No");
	}

	//Draw End Screen for the end of simulation
	public void DrawEnd(){
		DrawMain();
		StdDraw.text(90,-15,"Press any key to exit application and see performance.");

	}

	//Draw Buy screen to buy shares
	public void DrawBuy(){
		DrawMain();
		StdDraw.text(90,-15,"Click an number from 1-9 to Buy x100 Shares");
		StdDraw.text(90,-18,"Press c to cancel");
	}

	//Draw Sell screen to sell shares
	public void DrawSell(){
		DrawMain();
		StdDraw.text(90,-15,"Click an number from 1-9 to Sell x100 Shares");
		StdDraw.text(90,-18,"Press c to cancel");
	}

	//Draws line graph of stock up to ith iteration (150 periods): In this case, frames/50
	public void DrawStock(int i){
		//If frames are less than 7500, continue simulation
		if(frames <= 7500){
			for(int j = 0; j < i; j++){
				StdDraw.line(15+j,stock.GetValue(j),j+16,stock.GetValue(j+1));
			}
		}
		//Else, simulation is over and change state to end
		else{state = State.END;}
	}


	//Prints Report of Performance to File performance.txt
	public void PrintReport()throws FileNotFoundException {
		//Writes info to file
		PrintWriter p = new PrintWriter("performance.txt");
		//Prints performance measures such as PnL, Net Positions value and whether or not you passed (made profit) or failed
		p.printf(portfolio.toString());
		p.printf("This is your total Profit/Loss: $ %2.2f \n", portfolio.GetPnL(stock,150));
		p.printf("This is your Net Position: $ %2.2f \n", portfolio.NetPosition(j));
		p.printf( (portfolio.GetPnL(stock,150) > 0) ? "Congratulations You Passed the Simulator" : "Better Luck Next Time!");
		p.flush();
		p.close();
		//Print Stock data to another file with list of stock prices
		stock.PrintData();
		System.exit(0);
	}


	//Main Function that runs the game when executing file
	public static void main(String[] args) throws FileNotFoundException{
		Graphics g = new Graphics();
		g.InitializeGraphics();
		while(true){
			g.ProcessInput();

			g.Draw();
			g.Show();
			g.Clear();
		}
	}
}

