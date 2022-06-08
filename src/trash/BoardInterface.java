package trash;

import battleship.FieldValues;
import battleship.ShipInterface;

public interface BoardInterface {

    /**
     * @values WATER, HIT, DEAD
     * @return charArray representing game board
     * (maybe needed only in boardOpponent)
     */
    FieldValues[][] getHitMap();

    /**
     *
     * @return array with ship object (if invoked on opponents Board might be NULL if not connected jet)
     */
    ShipInterface[][] getShipMap();


}
