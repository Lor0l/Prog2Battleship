package battleship;

public class ShipSmall implements ShipInterface{

    //replace hard coded values
    private int[] coordinates;
    //0  1  2  3
    //1x 1y 2x 2y
    private boolean partOneHit;
    private boolean partTwoHit;

    private boolean dead;


    public ShipSmall(int x, int y, OrientationValues orientation) {

        coordinates = new int[4];
        coordinates[0] = x;
        coordinates[1] = y;
        switch (orientation) {
            case HORIZONTALLY:
                coordinates[2] = x + 1;
                coordinates[3] = y;
                break;
            case VERTICALLY:
                coordinates[2] = x;
                coordinates[3] = y + 1;
                break;

        }


        this.partOneHit = false;
        this.partTwoHit = false;

        this.dead = false;
    }


    @Override
    public boolean isHit(int xCoordinate, int yCoordinate) {

        if(xCoordinate == coordinates[0] && yCoordinate == coordinates[1]){
            return partOneHit;
        }else{ //else if check for specific values
            return partTwoHit;
        } //-> else exception "something went wrong"
    }


    @Override
    public boolean isDead() {
        return dead;
    }


    @Override
    public void hitShip(int xCoordinate, int yCoordinate) {

        if(xCoordinate == coordinates[0] && yCoordinate == coordinates[1]){
            partOneHit = true;
        }else{
            partTwoHit = true;
        }

        if (partOneHit && partTwoHit) {
            dead = true;
        }
    }

    @Override
    public int[] getCoordinates(int xCoordinate, int yCoordinate) {
        return coordinates;
    }
}
