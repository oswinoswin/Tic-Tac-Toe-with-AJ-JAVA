package tictactoe;

import java.util.Scanner;

class User extends Player {
    @Override
    public void makeMove(Board board){
        Scanner scanner = new Scanner(System.in);
        boolean coordinatesAreCorrect = false;
        int row = 0, col = 0;
        while (!coordinatesAreCorrect) {
            System.out.println("Enter the coordinates:");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    col = scanner.nextInt();
                    if (row < 1 || row > 3 || col <1 || col > 3){
                        System.out.println("Coordinates should be from 1 to 3!");
                        row = 0; col = 0;
                    }else {
                        if (!board.isCellEmpty(row - 1, col - 1)){
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                        else {
                            coordinatesAreCorrect = true;
                        }
                    }

                }else {
                    System.out.println("You should enter numbers!");
                    scanner.next();
                    row=-1; col = -1;
                }
            }else {
                System.out.println("You should enter numbers!");
                row =- 1; col = -1;
                scanner.next();
            }
        }
        board.markMove(row - 1, col - 1); //from 1-start to 0-start
        System.out.println(board);
    }
}
