package tictactoe;

class Medium extends Player {
    boolean canWinNow(Board board, char playerSymbol){
        char opponentSymbol = playerSymbol == 'X' ? 'O' : 'X';
        for (int row=0; row <3; row++){
            if (board.countSymbolRow(row, playerSymbol) == 2 && board.countSymbolRow(row, opponentSymbol) == 0)
                return true;
        }
        return false;
    }

    boolean canLoseNow(Board board, char playerSymbol){
        char opponentSymbol = playerSymbol == 'X' ? 'O' : 'X';
        for (int i=0; i <3; i++){
            if (board.countSymbolRow(i, opponentSymbol) == 2 && board.countSymbolRow(i, playerSymbol) == 0)
                return true;
            if (board.countSymbolCol(i, opponentSymbol) == 2 && board.countSymbolCol(i, playerSymbol) == 0)
                return true;
        }
        if (board.countSymbolDagRL(opponentSymbol) == 2 && board.countSymbolDagRL(playerSymbol) == 0)
            return true;
        if (board.countSymbolDagLR(opponentSymbol) == 2 && board.countSymbolDagLR(playerSymbol) == 0)
            return true;
        return false;
    }

    int[] findWinningMove(Board board, char playerSymbol){
        int[] cords = new int[2];
        for(int row = 0; row < 3; row++){
            if(board.countSymbolRow(row, playerSymbol) == 2){
                for(int col = 0; col < 3; col++){
                    if(board.isCellEmpty(row, col)){
                        cords[0] = row;
                        cords[1] = col;
                        return cords;
                    }
                }
            }

        }
        return null;
    }

    int[] findBlockingMove(Board board, char playerSymbol){
        int[] cords = new int[2];
        char opponentSymbol = playerSymbol == 'X' ? 'O' : 'X';

        if (board.countSymbolDagRL(opponentSymbol) == 2){
            if (board.isCellEmpty(0,2)){
                cords[0] = 0;
                cords[1] = 2;
                return cords;
            } else if (board.isCellEmpty(1,1)) {
                cords[0] = 1;
                cords[1] = 1;
                return cords;
            } else if (board.isCellEmpty(2, 0)){
                cords[0] = 2;
                cords[1] = 0;
                return cords;
            }
        }

        if (board.countSymbolDagLR(opponentSymbol) == 2){
            if (board.isCellEmpty(0,0)){
                cords[0] = 0;
                cords[1] = 0;
                return cords;
            } else if (board.isCellEmpty(1,1)) {
                cords[0] = 1;
                cords[1] = 1;
                return cords;
            } else if (board.isCellEmpty(2, 2)){
                cords[0] = 2;
                cords[1] = 2;
                return cords;
            }
        }
        for (int row=0; row < 3; row++){
            if(board.countSymbolRow(row, opponentSymbol) == 2){
                for(int col=0; col < 3; col++){
                    if(board.isCellEmpty(row, col)){
                        cords[0] = row;
                        cords[1] = col;
                        return cords;
                    }
                }
            }
        }
        for (int col=0; col <3; col++){
            if(board.countSymbolCol(col, opponentSymbol) == 2){
                for (int row=0; row<3; row++){
                    if (board.isCellEmpty(row, col)){
                        cords[0] = row;
                        cords[1] = col;
                        return cords;
                    }
                }
            }
        }
        return null;
    }
    @Override
    public void makeMove(Board board) {
        if (canWinNow(board, playerSymbol)){
            int[] cords = findWinningMove(board, playerSymbol);
            board.markMove(cords[0], cords[1], playerSymbol);
        }
        else if(canLoseNow(board, playerSymbol)) {
            int[] cords = findBlockingMove(board, playerSymbol);
            board.markMove(cords[0], cords[1], playerSymbol);
        } else {
            super.makeMove(board);
        }
        System.out.println("Making move level \"medium\"");
        System.out.println(board);
    }
}
