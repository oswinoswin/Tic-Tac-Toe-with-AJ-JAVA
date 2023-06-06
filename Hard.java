package tictactoe;

public class Hard extends Player {

    int minMax(Board board, boolean maximize){
        if (board.isWinning(playerSymbol)) return 1;
        if (board.isWinning(getOpponentSymbol())) return -1;
        if (board.isDraw()) return 0;

        int[][] emptySpaces = board.emptySpaces();
        int bestScore = -100;
        if (maximize){ //"our" player is making a move
            bestScore = -2;
            for (int i=0; i< emptySpaces.length; i++){
                int row = emptySpaces[i][0];
                int col = emptySpaces[i][1];
                board.markMove(row, col, playerSymbol);
                int score = minMax(board,  false);
                if (score > bestScore){
                    bestScore = score;
                }
                board.markMove(row, col, ' '); //clearing up
            }
        } else if (!maximize){ //opponent move
            bestScore = 100;
            for (int i=0; i< emptySpaces.length; i++){
                int row = emptySpaces[i][0];
                int col = emptySpaces[i][1];
                board.markMove(row, col, getOpponentSymbol());
                int score = minMax(board, true);
                if (score < bestScore){
                    bestScore = score;
                }
                board.markMove(row, col, ' '); //clearing up
            }
        }
        return bestScore;
    }
    @Override
    public void makeMove(Board board) {
        int[][] emptySpaces = board.emptySpaces();
        int bestScore = -2;
        int bestRow = -1;
        int bestCol = -1;
        for (int i=0; i< emptySpaces.length; i++){
            int row = emptySpaces[i][0];
            int col = emptySpaces[i][1];
            board.markMove(row, col, playerSymbol);
            int score = minMax(board, false);
            if (score > bestScore){
                bestScore = score;
                bestRow = row;
                bestCol = col;
            }
            board.markMove(row, col, ' '); //clearing up
        }
        board.markMove(bestRow, bestCol, playerSymbol);
        System.out.println("Making move level \"hard\"");
        System.out.println(board);
    }
}
