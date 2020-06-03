import java.util.*;
import java.util.Scanner;

class TicTac{
	/*Steps:


	*/

	//global variables
	boolean filled = false;
	int turn = 1;
	int[][] board = new int[3][3];

	public void setup(){
		int won = checkWinner(board);
		if(won == 1 && turn == 1){
			System.out.println("Winner: Player Number 1!");
		}
		if(won == 1 && turn == -1){
			System.out.println("Winner: Player Number 2!");
		}
		else if(won == 2){
			System.out.println("Tie! Good game to both players!");
		}
	}

	int checkWinner(int[][] board){
		boolean full = fullBoard(board);
		boolean threes = threeRow(board);
		if(threes == true){
			createLastBoard(board);
			if((turn*-1) == 1) System.out.println("Winner: Player Number 1!"); //find out who the last player was
			else System.out.println("Winner: Player Number 2!");
			return 1; //three in a row w/o running out of moves
		}
		else if(full == true){ //no more moves can be made, but no one won
			System.out.println("Tie! Good game to both players!");
			return 2;
		}
		createBoard(board); //continue the game
		return 0;
	}


	boolean fullBoard(int[][] board){
		for(int[] b : board){
			for(int a : b){
				if(a == 0){
					return false;
				}
			}
		}
		return true;
	}

	boolean threeRow(int[][] board){
		//verticals:

		//vertical in the 0th col:
		if(board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] != 0) return true;

		//vertical in the 1st col:
		if(board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] != 0) return true;

		//vertical in the 2nd col:
		if(board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] != 0) return true;


		//horizontals:

		//horizontal in the 0th row:
		if(board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] != 0) return true;

		//horizontal in the 1st row:
		if(board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] != 0) return true;

		//horizontal in the 2nd row:
		if(board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] != 0) return true;


		//diagonals:

		//diagonal sloping down from left to right:
		if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) return true;

		//diagonal sloping up from left to right:
		if(board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != 0) return true;

		return false;
	}

	void createBoard(int[][] board){
	    System.out.println("--------");
	    for(int i = 0; i < board.length; i++){
	        System.out.print("|");
	        for(int b = 0; b < board.length; b++){
	            System.out.print(board[i][b]);
	            System.out.print("|");
	        }
	        System.out.println("");
	        System.out.println("--------");
	    }
	    if(turn == 1) System.out.println("Player 1's turn");
	    else System.out.println("Player 2's turn");
	    Scanner reader = new Scanner(System.in);
	    String moveNum = reader.nextLine();
	    move(moveNum, board);
	}

	void createLastBoard(int[][] board){
		System.out.println("--------");
	    for(int i = 0; i < board.length; i++){
	        System.out.print("|");
	        for(int b = 0; b < board.length; b++){
	            System.out.print(board[i][b]);
	            System.out.print("|");
	        }
	        System.out.println("");
	        System.out.println("--------");
	    }
	}

	void move(String moveNum, int[][] board){
		int space = 0;
		try{
			space = Integer.parseInt(moveNum);	
		}
		catch(Exception nfe){
			System.out.println("");
	    	System.out.println("Oops! That is an invalid input! Please go again and type a number 1-9.");
	    	checkWinner(board);
		}

	    if(space < 1 || space > 9){
	    	System.out.println("");
	    	System.out.println("Oops! That is an invalid input! Please go again and type a number 1-9.");
	    	checkWinner(board);
	    }

	    System.out.println("making move..." + space);
	    int moveS = space-1; //moveS will convert space to be 0-8 so that board indexes are easier to calculate
	    
	    int r = moveS/3;
	    int c = moveS%3;
	    if(board[r][c] == 0){
	        if(turn == 1) board[r][c] = 1;
	        else board[r][c] = 2;
	        turn *= -1;
	        checkWinner(board);
	    }
	    else{
	    	System.out.println("");
	    	System.out.println("Oops! That spot is already taken! Please go again.");
	    	checkWinner(board);
	    }
	}

	public static void main(String[] args){
		TicTac toe = new TicTac();
		toe.setup();
	}

}