package battleship;

public interface OpponentBoardInterface {

    /**
     * Invocable in play phase. Check opponents board if coordinates are occupied by a ship and sets hit marker
     *
     * @param xCoordinate
     * @param yCoordinate
     * @throws WrongStatusException when method is invoked in the wrong status
     * @throws OutOfFieldException  given coordinates are out of array range
     */
    void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException;

    /**
     * Invocable in game phase
     * @return 2DCharArray representing board of opponent with ships hidden showing hits only
     * @throws WrongStatusException when method is invoked in the wrong status
     */
    ShipSuper[][] getOpponentShipBoard() throws WrongStatusException;

    char[][] getOpponentHitBoard
}
