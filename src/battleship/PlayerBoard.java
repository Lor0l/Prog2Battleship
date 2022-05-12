package battleship;

public class PlayerBoard implements PlayerBoardInterface {
    private final String playerName;
    //private final String opponentName;
    private ShipSuper[][] playerShipBoard;
    private char[][] playerHitBoard;

    public PlayerBoard(String playerName) /* +Parameter opponentName */ {
        this.playerName = playerName;
        //this.opponentName = opponentName;
        this.playerShipBoard = new ShipSuper[9][9];
        //this.opponentBoard = opponentBoard.getBoard();
    }

    @Override
    public void set(int xCoordinate, int yCoordinate, int ship, int orientation)
            throws WrongStatusException, OutOfFieldException, FieldOccupiedException {

        // check if possible (needed array places taken/ out of bounds)
        // switch which object on ship-parameter
        // create Ship Object with x,y,orientation
        // give object to playerboard on x,y and x or (orientation) y + ship length(and all in between)
    }

    @Override
    public ShipSuper[][] getPlayerShipBoard() {
        return this.playerShipBoard;
    }

    @Override
    public char[][] getPlayerHitBoard() {
        return this.playerHitBoard;
    }


}
