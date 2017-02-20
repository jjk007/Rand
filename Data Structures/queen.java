import java.util.Scanner;
import java.util.Arrays;
import java.util.Stack;

public class queen {
    static Scanner scan;
    static int N = 4;
	
    static void drawLayout(int board[][])
    {
        for (int i = 0; i < N; i++)
	    {
		for (int j = 0; j < N; j++)
		    if (board[i][j] == 1)
			System.out.print("Q   ");
		    else
			System.out.print("_   ");
		System.out.println("\n");
	    }
    }

    static boolean recursiveSolution(int board[][], int coloumn) {
	if (coloumn >= N)
	    return true; // This is our base case
	for (int i = 0; i < N; i++)
	    {
		if (conflictCheck(board, i, coloumn))
		    {
			board[i][coloumn] = 1;
			if (recursiveSolution(board, coloumn + 1))
			    return true;
			board[i][coloumn] = 0; // We are backtracking here
		    }
	    }
	return false; // This statement is returned, when no solutions are found
    }

    static boolean conflictCheck(int board[][], int row, int coloumn)
    {
	int i, j;
	for (i = 0; i < coloumn; i++) {
	    if (board[row][i] == 1)
		return false; // Returns false if there is a conflict
	}
	for (i = row, j = coloumn; i >= 0 && j >= 0; i--, j--) {
	    if (board[i][j] == 1)
		return false;
	}
	for (i = row, j = coloumn; j >= 0 && i < N; i++, j--) {
	    if (board[i][j] == 1)
		return false;
	}
	return true; //No conflicts are occuring, queen can be placed
    }

    static boolean iterativeSolution(int board[][]) 
    {
	int queenCount = 0;
	int lastCol = 0;
	int lastRow = 0;
	int row = 0;
	int coloumn = 0;
	while(row < N)
	    {
		while(coloumn < N)
		    {
			System.out.println(row+" "+coloumn);
			if (conflictCheck(board, row, coloumn))
			    {
				// If there is no conflicts place the queen
				board[row][coloumn] = 1;
				queenCount++;
				lastCol = coloumn;
				lastRow = row;
				coloumn++;
				System.out.println("Queen Placed");
				if(queenCount == N)
				    return false; // We have a soltion
			    }
			else if(row == N)
			    {
				board[lastRow][lastCol] = 0;
				System.out.println("Queen Removed");
				queenCount--;
				coloumn = lastCol+1;
				row = lastRow;
			    }
			else
			    {
				if(row < N-1) 
				    row++;
				else
				    row = 0;
			    }
		    }
	    }
	return true; // This statement is returned, when no solutions are found
    } 
    
    public static void main(String[] args)
    {
	scan = new Scanner(System.in);
	System.out.println("State the value of N in this program!");
	N = scan.nextInt();
	System.out.println("");
	System.out.println("");
	int[][] board = new int[N][N];

	// if (!recursiveSolution(board, 0)) { 
	//     System.out.println("Solution not found.");
	// }
	// drawLayout(board);

	// for(int i =0; i < N; i++)
	//     {
	//         for(int[] eachRow: board)
	//             Arrays.fill(eachRow,0); // Resets the array every loop
	//         if (recursiveSolution(board, i))
	//             {
	//                 drawLayout(board);
	//                 System.out.println("-------------------------------------------------------");
	//             }
	//     }
	
	if (iterativeSolution(board)) { 
	    System.out.println("Solution not found.");
	}
	else
	    drawLayout(board);

    } // End of main method
}


// http://stackoverflow.com/questions/18610003/can-we-solve-n-queens-without-backtracking-and-how-to-calculate-and-what-will-b

// https://www.cs.usfca.edu/~galles/visualization/RecQueens.html
