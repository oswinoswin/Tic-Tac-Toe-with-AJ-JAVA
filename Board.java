package tictactoe;

import java.util.Arrays;

class Board {
    static final int N = 3;

    public char[][] getBoard() {
        char [][] bcopy = new char[N][N];
        for (int row = 0; row < N; row++){
            bcopy[row] = Arrays.copyOf(board[row], N);
        }
        return bcopy;
    }

    char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static char[][][] Xwinning = {
            {
                    {'X', 'X', 'X'},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            },
            {
                    {' ', ' ', ' '},
                    {'X', 'X', 'X'},
                    {' ', ' ', ' '}
            },
            {
                    {' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {'X', 'X', 'X'}
            },
            {
                    {'X', ' ', ' '},
                    {'X', ' ', ' '},
                    {'X', ' ', ' '}
            },
            {
                    {' ', 'X', ' '},
                    {' ', 'X', ' '},
                    {' ', 'X', ' '}
            },
            {
                    {' ', ' ', 'X'},
                    {' ', ' ', 'X'},
                    {' ', ' ', 'X'}
            },
            {
                    {'X', ' ', ' '},
                    {' ', 'X', ' '},
                    {' ', ' ', 'X'}
            },
            {
                    {' ', ' ', 'X'},
                    {' ', 'X', ' '},
                    {'X', ' ', ' '}
            }
    };
    
    public Board(char[] initialState) {
        int i = 0;
        for (int row = 0; row < N; row++){
            for (int col = 0; col < N; col ++) {
                if (initialState[i] != '_') {
                    board[row][col] = initialState[i];
                }
                i++;
            }
        }
    }

    public Board() {
    }

    int countSymbol(char symbol){
        int sum = 0;
        for (int row = 0; row < N; row++){
            for (int col = 0; col < N; col ++) {
                if(board[row][col] == symbol) sum++;
            }
        }
        return sum;
    }

    public int[][] emptySpaces() {
        int[][] emptySpaces = new int[countSymbol(' ')][2];
        int j = 0;
        for (int row = 0; row < N; row++){
            for (int col = 0; col < N && j < emptySpaces.length; col ++) {
                if(board[row][col] == ' ') {
                    emptySpaces[j][0] = row;
                    emptySpaces[j][1] = col;
                    j++;
                }
            }
        }
        return emptySpaces;
    }

    public void markMove(int row, int col, char playerSymbol){
        board[row][col] = playerSymbol;
    }

    public void markMove(int row, int col){
        char symbol = countSymbol('X') > countSymbol('X') ? 'O' : 'X';
        board[row][col] = symbol; //using 0-start coords here
    }

    public boolean isCellEmpty(int row, int col){
        return board[row][col] == ' ';
    }

    public int countSymbolRow(int row, char symbol){
        int count = 0;
        for(int col = 0; col < N; col++){
            if (board[row][col] == symbol) count++;
        }
        return count;
    }

    public int countSymbolCol(int col, char symbol){
        int count = 0;
        for(int row=0; row < N; row++ ){
            if (board[row][col] == symbol) count++;
        }
        return count;
    }

    public int countSymbolDagLR(char symbol){
        int count = 0;
        if (board[0][0] == symbol) count++;
        if (board[1][1] == symbol) count++;
        if (board[2][2] == symbol) count++;
        return count;
    }

    public int countSymbolDagRL(char symbol){
        int count = 0;
        if (board[0][2] == symbol) count++;
        if (board[1][1] == symbol) count++;
        if (board[2][0] == symbol) count++;
        return count;
    }
    
    public boolean isWinning(char playerSymbol){
        for (int i = 0; i < N; i++){
            if(countSymbolRow(i, playerSymbol) == 3)
                return true;
            if (countSymbolCol(i, playerSymbol) == 3)
                return true;
        }
        if (countSymbolDagLR(playerSymbol) == 3)
            return true;
        if (countSymbolDagRL(playerSymbol) == 3)
            return true;
        return false;
    }

    public boolean isDraw(){
        if(countSymbol('X') + countSymbol('O') == N*N){
//            System.out.println("DRAW!!!");
            return true;
        }
        return false;
    }


    public String gameState(){

        if(isWinning('X'))
            return "X wins";

        if(isWinning('O'))
            return "O wins";

        if(isDraw()) return "Draw";
        return "Game not finished";
    }

    public String printBoard(int depth){
        return " ".repeat(depth) + "---------\n" +
                " ".repeat(depth) + "| " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " |\n" +
                " ".repeat(depth) + "| " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " |\n" +
                " ".repeat(depth) +"| " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " |\n" +
                " ".repeat(depth) + "---------\n";
    }

    @Override
    public String toString() {
        return "---------\n" +
                "| " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " |\n" +
                "| " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " |\n" +
                "| " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " |\n" +
                "---------\n";
    }
}
