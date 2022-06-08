package trash;

import battleship.OutOfFieldException;
import battleship.WrongStatusException;
import trash.BoardInterface;

public interface BoardOpponentInterface extends BoardInterface {

    /**
     * Implementing object is given an array with ship objects in constructor
     */

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
     */
     void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException;

     // rückhabewert?

}
