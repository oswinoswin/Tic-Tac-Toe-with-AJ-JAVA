package tictactoe;

import java.util.Random;

class Player {
    char playerSymbol;
    public char getPlayerSymbol() {
        return playerSymbol;
    }
    public char getOpponentSymbol() {
        return  playerSymbol == 'X' ? 'O' : 'X' ;
    }

    public void setPlayerSymbol(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }


    public static final Player getInstance(String playerType){
        switch (playerType){
            case "user":
                return new User();
            case "easy":
                return new Easy();
            case "medium":
                return new Medium();
            case "hard":
                return new Hard();

            default: return null;
        }
    }
    public void makeMove(Board board){
        int[][] emptySpaces = board.emptySpaces();
        Random random = new Random();
        int moveIndex = random.nextInt(emptySpaces.length);
        board.markMove(emptySpaces[moveIndex][0], emptySpaces[moveIndex][1], playerSymbol);
    }
}
