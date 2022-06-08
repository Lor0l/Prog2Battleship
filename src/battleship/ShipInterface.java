package battleship;

public interface ShipInterface {

    /**
     * check part of ship if hit
     *
     * @param xCoordinate
     * @param yCoordinate
     * @return boolean representing ship parts status: true = hit, false = not hit
     */
    boolean isHit(int xCoordinate, int yCoordinate);

    /**
     * check ship if destroyed
     *
     * @return boolean representing ships status: true = dead, false = alive
     */
    boolean isDead();


    /**
     * changes ship part status and whole ship status in case of last part hit
     *
     * @param xCoordinate
     * @param yCoordinate
     */
    void hitShip(int xCoordinate, int yCoordinate);

    /**
     *
     * @param xCoordinate
     * @param yCoordinate
     * @return int array with coordinates of all ship parts
     */
    int[] getCoordinates(int xCoordinate, int yCoordinate);



}
