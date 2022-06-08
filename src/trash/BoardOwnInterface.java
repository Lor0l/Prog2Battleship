package trash;

import battleship.*;
import trash.BoardInterface;

public interface BoardOwnInterface extends BoardInterface {

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
     */
    void set(int xCoordinate, int yCoordinate, ShipValues ship, OrientationValues orientation)
            throws WrongStatusException, OutOfFieldException, FieldOccupiedException;



}
