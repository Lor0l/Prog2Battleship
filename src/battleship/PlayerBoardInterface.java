package battleship;

public interface PlayerBoardInterface extends BoardInterface{

    /**
     * Invokable in set up phase. Sets a ship on players board
     *
     * @param xCoordinate
     * @param yCoordinate
     * @param ship        Ship type: 0=small(1x2), 1=medium(1x3), 2=big(1x5)
     * @param orientation Ship orientation: 0=horizontally, 1=vertically
     * @throws WrongStatusException   when method is invoked in the wrong status
     * @throws OutOfFieldException    given coordinates and/or neighbouring fields within ship length in "orientation"
     *                                direction are out of array range
     * @throws FieldOccupiedException given coordinates and/or neighbouring fields within ship length in "orientation"
     *                                are colliding with an already set ship
     */
    void set(int xCoordinate, int yCoordinate, int ship, int orientation)
            throws WrongStatusException, OutOfFieldException, FieldOccupiedException;

    /**
     * gets new hitBoard via TCP and updates hitBoard (and ship objects status in case of hit)
     * Exceptions: IO problems, wrong Status
     */
    void updateBoard();


}
