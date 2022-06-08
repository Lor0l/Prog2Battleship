package battleship;

public interface ArenaInterface {

    /**
     * @values WATER, HIT, DEAD, MISS
     * @return charArray representing game board
     */
    FieldValues[][] getHitMap();

    /**
     * @return array with ship object
     */
    ShipInterface[][] getShipMap();


    /**
     * Invokable in set up phase
     * Sets a ship on players board
     * Ships have to be surrounded by water and edge of board or only water
     *
     * @param xCoordinate
     * @param yCoordinate
     * @param ship        Ship type: 0=small(1x2), 1=medium(1x3), 2=big(1x5)
     * @param orientation Ship orientation: 0=horizontally, 1=vertically
     * @throws WrongStatusException   when method is invoked in the wrong status
     * @throws OutOfFieldException    given coordinates and/or neighbouring fields within ship length in "orientation"
     *                                direction are out of array bounds
     * @throws FieldOccupiedException given coordinates and/or neighbouring fields within ship length in "orientation"
     *                                are blocked by an already set ship and/or fields surrounding new ship are
     *                                already taken
     * @throws IllegalInputException  when it is tried to set one more ship of one type than it is allowed
     */
    void set(int xCoordinate, int yCoordinate, ShipValues ship, OrientationValues orientation)
            throws WrongStatusException, OutOfFieldException, FieldOccupiedException, IllegalInputException;

    /**
     * Invocable in play phase. Check playerA's boardOpponent if ship object in array on given coordinates.
     * Invoke shot on other player's boardPlayer internally.
     * In case of hit changes ship status and in case of miss changes games status (your turn -> wait).
     * Make entry on playerA's hitMap
     *
     * @param xCoordinate
     * @param yCoordinate
     * @throws WrongStatusException when method is invoked in the wrong status
     * @throws OutOfFieldException  given coordinates are out of array bounds
     * @throws IllegalInputException when it is tried to shoot on same position again
     */
    void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException, IllegalInputException;

}
