package tictactoe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Game {
    Board board;
    Player firstPlayer;
    Player secondPlayer;

    public Game(String firstPlayer, String secondPlayer){
        board = new Board();
        System.out.println(board);
        this.firstPlayer = Player.getInstance(firstPlayer);
        this.firstPlayer.setPlayerSymbol('X');
        this.secondPlayer = Player.getInstance(secondPlayer);
        this.secondPlayer.setPlayerSymbol('O');
    }

    public void play(){
        while (true){
            firstPlayer.makeMove(board);
            if (!board.gameState().equals("Game not finished")){
                System.out.println(board.gameState());
                break;
            }
            secondPlayer.makeMove(board);
            if (!board.gameState().equals("Game not finished")){
                System.out.println(board.gameState());
                break;
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {
        playGame();
    }
    private static void testHard(){
        Board board = new Board("O0_XX____".toCharArray());
//        Board board = new Board();
        Player hard = Player.getInstance("hard");
        System.out.println(board);
        hard.setPlayerSymbol('X');

        hard.makeMove(board);
    }
    private static void runTests() {
        Board board = new Board("X________".toCharArray());
        System.out.println(board);
        char[][] bcopy = board.getBoard();
        bcopy[0][1] = 'X';
        for(char[] row : bcopy){
            System.out.println(Arrays.toString(row));
        }
        System.out.println(board);
    }

    private static void playGame() {
        String command = "";
        String firstPlayer = "";
        String secondPlayer = "";
        ArrayList<String> allowedPlayers = new ArrayList<>();
        allowedPlayers.add("user");
        allowedPlayers.add("easy");
        allowedPlayers.add("medium");
        allowedPlayers.add("hard");

        Scanner scanner = new Scanner(System.in);

        while (true){
            while (true){
                System.out.println("Input command: ");
                command = scanner.nextLine();
                if (command.equals("exit")){
                    break;
                }else if (command.startsWith("start") && command.split(" ").length == 3){
                    firstPlayer = command.split(" ")[1];
                    secondPlayer = command.split(" ")[2];
                    if(allowedPlayers.contains(firstPlayer) && allowedPlayers.contains(secondPlayer)){
                        break;
                    }
                }
                System.out.println("Bad parameters!");
            }
            if(command.equals("exit")) break;
            Game game = new Game(firstPlayer, secondPlayer);
            game.play();
        }
    }
}
