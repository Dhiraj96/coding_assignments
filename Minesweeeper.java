/* Name: Raj (Phasut) Amarnani
 Section: 04
 
Takes 3 command line arguments, producing M by N boolean array in the form of a Minesweeper game and its solution. 
 */

 public class ch1sec4prob30 {
 	public static void main(String[] args){
 		//initialize m,n,p as values passed from command line
 		int m = Integer.parseInt(args[0]);
 		int n = Integer.parseInt(args[1]);
 		double p = Double.parseDouble(args[2]);

 		//initialize boolean array mines with dimension m+2, n+2
 		boolean[][] mines = new boolean[m+2][n+2];
 		//loop to generate true/false values for each cell based on p
 		for(int i = 1; i <= m; i++) {
 			for(int j = 1; j <= n; j++) {
 				mines[i][j] = (Math.random() < p);
 			}
 		}
 		//loop to generate * for bomb cells and . for empty cells
 		for(int k = 1; k <= m; k++) {
 			for(int l = 1; l <= n; l++) {
 				if(mines[k][l] == true) {
 					System.out.print("* ");
 				}
 				else {
 					System.out.print(". ");
 				}
 			}
 			System.out.println();
 		}

 		//initialize integer array with dimensions m+2, n+2
 		int[][] result = new int[m+2][n+2];
 		//loop to generate a count of bordering bombs for cells
 		for(int a = 1; a <= m; a++) {
 			for(int b = 1; b <= n; b++){
 				 for(int c = a-1; c <= a+1; c++){
 				 	for(int d = b-1; d <= d+1; d++){
 				 		if(mines[c][d] == true){
 				 			result[a][b] = result[a][b] + 1;
 				 		}
 				 	}
 				 }
 			}
 		}

 		System.out.println();
 		//loop to print * if bomb present, result of array cells result if no bomb
 		for (int e = 1; e <= m; e++){
 			for(int j = 1; j <= n; j++){
 				if(mines[e][j] == true){
 					System.out.print("* ");
 				}
 				else {
 					System.out.print(result[e][j] + " ");
 				}
 			}
 			System.out.println();
 		}
 	}
 }