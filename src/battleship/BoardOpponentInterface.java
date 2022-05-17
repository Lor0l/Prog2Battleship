package battleship;

public interface BoardOpponentInterface extends BoardInterface {

    /**
     * Invocable in play phase. Check opponents board if coordinates are occupied by a ship and sets hit marker
     *
     * @param xCoordinate
     * @param yCoordinate
     * @throws WrongStatusException when method is invoked in the wrong status
     * @throws OutOfFieldException  given coordinates are out of array bounds
     */
    void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException;

}
