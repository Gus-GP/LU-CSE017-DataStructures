package cs2114.minesweeper;

public class PlayMineSweeper
{

    public static void main(String[] args)
    {
        MineSweeperBoard b = new MineSweeperBoard(5,5,2);

        System.out.println(b.toString());

        b.uncoverCell(3, 0);

        System.out.println(b.toString());

        b.loadBoardState("+++++","+++++","+++++","+++++", "+++++");

        System.out.println(b.toString());

    }

}
