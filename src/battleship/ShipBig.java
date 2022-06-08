package battleship;

public class ShipBig implements ShipInterface{

    private final int X1 = 0;
    private final int X2 = 2;
    private final int X3 = 4;
    private final int X4 = 6;
    private final int X5 = 8;
    private final int Y1 = 1;
    private final int Y2 = 3;
    private final int Y3 = 5;
    private final int Y4 = 7;
    private final int Y5 = 9;

    private final int COORDINATES = 10;
    private int[] coordinates;
    //0  1  2  3  4  5  6  7  8  9
    //1x 1y 2x 2y 3x 3y 4x 4y 5x 5y

    private boolean partOneHit;
    private boolean partTwoHit;
    private boolean partThreeHit;
    private boolean partFourHit;
    private boolean partFiveHit;

    private boolean dead;


    public ShipBig(int x, int y, OrientationValues orientation) {

        coordinates = new int[COORDINATES];
        coordinates[X1] = x;
        coordinates[Y1] = y;
        switch (orientation) {
            case HORIZONTALLY:
                for (int i = 0, j = 0; i < coordinates.length; i+=2, j++) {
                    coordinates[i] = x + j;
                }
                for (int i = 1; i < coordinates.length; i += 2) {
                    coordinates[i] = y;
                }
                break;
            case VERTICALLY:
                for (int i = 0; i < coordinates.length; i += 2) {
                    coordinates[i] = x;
                }
                for (int i = 1, j = 0; i < coordinates.length; i+=2, j++) {
                    coordinates[i] = y + j;
                }
                break;
        }

        this.partOneHit = false;
        this.partTwoHit = false;
        this.partThreeHit = false;
        this.partFourHit = false;
        this.partFiveHit = false;

        this.dead = false;
    }


    @Override
    public boolean isHit(int xCoordinate, int yCoordinate) {

        if(xCoordinate == coordinates[X1] && yCoordinate == coordinates[Y1]){
            return partOneHit;
        } else if (xCoordinate == coordinates[X2] && yCoordinate == coordinates[Y2]) {
            return partTwoHit;
        } else if (xCoordinate == coordinates[X3] && yCoordinate == coordinates[Y3]){
            return partThreeHit;
        } else if (xCoordinate == coordinates[X4] && yCoordinate == coordinates[Y4]){
            return partFourHit;
        } else { //else if check for specific values
            return partFiveHit;
        } //-> else exception "something went wrong"
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void hitShip(int xCoordinate, int yCoordinate) {

        if(xCoordinate == coordinates[X1] && yCoordinate == coordinates[Y1]){
            partOneHit = true;
        } else if (xCoordinate == coordinates[X2] && yCoordinate == coordinates[Y2]) {
            partTwoHit = true;
        } else if (xCoordinate == coordinates[X3] && yCoordinate == coordinates[Y3]){
            partThreeHit = true;
        } else if (xCoordinate == coordinates[X4] && yCoordinate == coordinates[Y4]){
            partFourHit = true;
        } else if (xCoordinate == coordinates[X5] && yCoordinate == coordinates[Y5]){
            partFiveHit = true;
        }


        if (partOneHit && partTwoHit && partThreeHit && partFourHit && partFiveHit) {
            dead = true;
        }

    }

    @Override
    public int[] getCoordinates(int xCoordinate, int yCoordinate) {
        return coordinates;
    }

}
