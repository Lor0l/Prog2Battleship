package battleship;

public interface BoardInterface {

    /**
     *
     * @return representing game board
     */
    char[][] getBoard();

    /**
     *
     * @return array with ship objects
     * Exceptions: if not initialized
     */
    ShipSuper[][] getShips();


}
