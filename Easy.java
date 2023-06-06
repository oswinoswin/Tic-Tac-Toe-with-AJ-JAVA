package tictactoe;


class Easy extends Player {
    @Override
    public void makeMove(Board board) {
        super.makeMove(board);
        System.out.println("Making move level \"easy\"");
        System.out.println(board);
    }
}
