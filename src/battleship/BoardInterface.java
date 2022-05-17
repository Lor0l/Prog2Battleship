package battleship;

public interface BoardInterface {

    /**
     *
     * @return representing game board
     */
    char[][] getBoard();

    /**
     *
     * @return array with ship object (if invoked on opponents Board might be NULL if not connected jet)
     */
    ShipSuper[][] getShips();


}
