package battleship;

import java.util.function.ToDoubleBiFunction;

public class BoardImpl implements BoardInterface{
    protected final String PLAYERNAME;
    protected ShipSuper[][] shipBoard;
    protected char[][] hitBoard;
    protected int shipCounter;


    BoardImpl(String playerName){ this.PLAYERNAME = playerName; }

    @Override
    public char[][] getBoard() {
        return this.hitBoard;
    }

    @Override
    public ShipSuper[][] getShips() { return this.shipBoard; }

}
