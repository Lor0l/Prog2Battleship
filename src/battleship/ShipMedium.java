package battleship;

public class ShipMedium implements ShipInterface{

    private final int X1 = 0;
    private final int X2 = 2;
    private final int X3 = 4;
    private final int Y1 = 1;
    private final int Y2 = 3;
    private final int Y3 = 5;

    private final int COORDINATES = 6;
    private int[] coordinates;
    //0  1  2  3  4  5
    //1x 1y 2x 2y 3x 3y

    private boolean partOneHit;
    private boolean partTwoHit;
    private boolean partThreeHit;

    private boolean dead;


    public ShipMedium(int x, int y, OrientationValues orientation) {

        coordinates = new int[COORDINATES];
        coordinates[X1] = x;
        coordinates[Y1] = y;
        switch (orientation) {
            case HORIZONTALLY:
                coordinates[X2] = x + 1;
                coordinates[Y2] = y;
                coordinates[X3] = x + 2;
                coordinates[Y3] = y;
                break;
            case VERTICALLY:
                coordinates[X2] = x;
                coordinates[Y2] = y + 1;
                coordinates[X3] = x;
                coordinates[Y3] = y + 2;
                break;
        }

        this.partOneHit = false;
        this.partTwoHit = false;
        this.partThreeHit = false;

        this.dead = false;
    }


    @Override
    public boolean isHit(int xCoordinate, int yCoordinate) {

        if(xCoordinate == coordinates[X1] && yCoordinate == coordinates[Y1]){
            return partOneHit;
        } else if (xCoordinate == coordinates[X2] && yCoordinate == coordinates[Y2]) {
            return partTwoHit;
        } else { //else if check for specific values
            return partThreeHit;
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
        } else {
            partThreeHit = true;
        }

        if (partOneHit && partTwoHit && partThreeHit) {
            dead = true;
        }
    }

    @Override
    public int[] getCoordinates(int xCoordinate, int yCoordinate) {
        return coordinates;
    }
}
