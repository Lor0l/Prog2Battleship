package battleship;

public class BoardImpl implements BoardInterface{

    private final String PLAYERNAME; // necessary?

    private final int ARRAYMAX = 9;
    private final int ARRAYMIN = 0;
    private ShipSuper[][] shipBoard;
    private char[][] hitBoard;

    private int shipCounter;


    BoardImpl(String playerName){
        this.PLAYERNAME = playerName;
        this.hitBoard = new char[ARRAYMAX][ARRAYMAX];
        this.shipBoard = new ShipSuper[ARRAYMAX][ARRAYMAX];
    }

    @Override
    public char[][] getBoard() {
        return this.hitBoard;
    }

    @Override
    public ShipSuper[][] getShips() { return this.shipBoard; }

    // ich bin mir nicht sicher ob ich vererbung richtig verstanden habe
    // (wenn mir wieder einfallen sollte was ich damit meinte --> besseren Komentar schreiben)
    // ein mal mit set auf eigenes ShipArray anwendbar und ein mal mit init auf gegnerisches anwendbar !? ich glaube das wars
    void setShipBoard(ShipSuper[][] newBoard){
        this.shipBoard = newBoard;
    }

}
