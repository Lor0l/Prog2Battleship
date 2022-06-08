package battleship;

import static battleship.FieldValues.*;
import static battleship.OrientationValues.HORIZONTALLY;

public class BoardOwn {

    private final int ARRAYMAX = 9;
    private final int ARRAYMIN = 0;
    private final int SMALL_LENGTH = 2;
    private final int MEDIUM_LENGTH = 3;
    private final int BIG_LENGTH = 5;
    private final int ONE = 1;
    private ShipInterface[][] shipMap;
    private int smallCounter;
    private int mediumCounter;
    private int bigCounter;
    private int shipCounter;
    boolean complete;

    BoardOwn(){
        this.shipMap = new ShipInterface[ARRAYMAX+ONE][ARRAYMAX+ONE];
        this.smallCounter = 0;
        this.mediumCounter = 0;
        this.bigCounter = 0;
        this.complete = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //SERVICE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void shipCountCheck(ShipValues ship) throws IllegalInputException {
        switch (ship) {
            case SMALL:
                if (this.smallCounter > 2) {
                    throw new IllegalInputException();
                }
                break;
            case MEDIUM:
                if(this.mediumCounter > 1) {
                    throw new IllegalInputException();
                }
                break;
            case BIG:
                if (this.bigCounter > 0) {
                    throw new IllegalInputException();
                }
                break;
        }
    }

    private void edgeOfWorldCheck(int xCoordinate, int yCoordinate, int length, OrientationValues orientation)
            throws OutOfFieldException{

        switch (orientation) {
            case HORIZONTALLY:
                if((xCoordinate < ARRAYMIN
                        || xCoordinate > (ARRAYMAX - length + ONE))
                        || (yCoordinate < ARRAYMIN
                        || yCoordinate > ARRAYMAX)){
                    throw new OutOfFieldException();
                }
                break;
            case VERTICALLY:
                if((xCoordinate < ARRAYMIN
                        || xCoordinate > ARRAYMAX)
                        || (yCoordinate < ARRAYMIN
                        || yCoordinate > (ARRAYMAX - length + ONE))){
                    throw new OutOfFieldException();
                }
                break;
        }
    }

    private void collisionCheck(int x, int y, int length, OrientationValues orientation)
            throws FieldOccupiedException{

        int xExpansion;
        int yExpansion;

        if (orientation == HORIZONTALLY){
            xExpansion = length;
            yExpansion = y + ONE;
        }else{
            xExpansion = x + ONE;
            yExpansion = length;
        }

        for(int i = x - ONE; i <= xExpansion; i++){           //X
            for(int j = y - ONE; j <= yExpansion; j++) {      //Y
                try {
                    if (this.shipMap[i][j] != null) {
                        throw new FieldOccupiedException();
                        //shouldn't get stuck in following catch --> ask professor
                    }
                } catch (ArrayIndexOutOfBoundsException aioob) {
                    //exception for convenience - no need to handle
                }
            }
        }
    }

    /* without try catch 9 * 2 different array bound situations to manage
     * solution --> catch out of bound exception --> just two situations left
     *
     *     0  1  2  3  4  5  6  7  8  9     X
     *  0 (S)[S][ ][ ][S][S][ ][ ][S][S]  y=0
     *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
     *  2 ( )[ ][ ][ ][ ][ ][ ][ ][ ][ ]
     *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
     *  4 (S)[S][ ][ ][S][S][ ][ ][S][S]  y!=0
     *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
     *  6 ( )[ ][ ][ ][ ][ ][ ][ ][ ][ ]
     *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
     *  8 ( )[ ][ ][ ][ ][ ][ ][ ][ ][ ]
     *  9 [S][S][ ][ ][S][S][ ][ ][S][S]  y=9
     *
     *    x=0          x!=0         x=9
     *  Y
     */

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NOT SERVICE (find better name)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public ShipInterface[][] getShipMap() { return this.shipMap; }


    public boolean set(int xCoordinate, int yCoordinate, ShipValues ship, OrientationValues orientation)
            throws OutOfFieldException, FieldOccupiedException, IllegalInputException {

        shipCountCheck(ship);

        switch (ship) {
            case SMALL:
                switch (orientation) {
                    case HORIZONTALLY:
                        edgeOfWorldCheck(xCoordinate, yCoordinate, SMALL_LENGTH, orientation);
                        collisionCheck(xCoordinate, yCoordinate, SMALL_LENGTH, orientation);
                        ShipInterface shipSH = new ShipSmall(xCoordinate, yCoordinate, orientation);
                        for (int i = 0; i < 2; i++) {
                            this.shipMap[xCoordinate + i][yCoordinate] = shipSH;
                        }
                        break;
                    case VERTICALLY:
                        edgeOfWorldCheck(xCoordinate, yCoordinate, SMALL_LENGTH, orientation);
                        collisionCheck(xCoordinate, yCoordinate, SMALL_LENGTH, orientation);
                        ShipInterface shipSV = new ShipSmall(xCoordinate, yCoordinate, orientation);
                        for (int i = 0; i < 2; i++) {
                            this.shipMap[xCoordinate][yCoordinate + i] = shipSV;
                        }
                        break;
                }
                this.smallCounter++;
                break;
            case MEDIUM:
                switch (orientation) {
                    case HORIZONTALLY:
                        edgeOfWorldCheck(xCoordinate, yCoordinate, MEDIUM_LENGTH, orientation);
                        collisionCheck(xCoordinate, yCoordinate, MEDIUM_LENGTH, orientation);
                        ShipInterface shipMH = new ShipMedium(xCoordinate, yCoordinate, orientation);
                        for (int i = 0; i < 3; i++) {
                            this.shipMap[xCoordinate + i][yCoordinate] = shipMH;
                        }
                        break;
                    case VERTICALLY:
                        edgeOfWorldCheck(xCoordinate, yCoordinate, MEDIUM_LENGTH, orientation);
                        collisionCheck(xCoordinate, yCoordinate, MEDIUM_LENGTH, orientation);
                        ShipInterface shipMV = new ShipMedium(xCoordinate, yCoordinate, orientation);
                        for (int i = 0; i < 3; i++) {
                            this.shipMap[xCoordinate][yCoordinate + i] = shipMV;
                        }
                        break;
                }
                this.mediumCounter++;
                break;
            case BIG:
                switch (orientation) {
                    case HORIZONTALLY:
                        edgeOfWorldCheck(xCoordinate, yCoordinate, BIG_LENGTH, orientation);
                        collisionCheck(xCoordinate, yCoordinate, BIG_LENGTH, orientation);
                        ShipInterface shipBH = new ShipBig(xCoordinate, yCoordinate, orientation);
                        for (int i = 0; i < 5; i++) {
                            this.shipMap[xCoordinate + i][yCoordinate] = shipBH;
                        }
                        break;
                    case VERTICALLY:
                        edgeOfWorldCheck(xCoordinate, yCoordinate, BIG_LENGTH, orientation);
                        collisionCheck(xCoordinate, yCoordinate, BIG_LENGTH, orientation);
                        ShipInterface shipBV = new ShipBig(xCoordinate, yCoordinate, orientation);
                        for (int i = 0; i < 5; i++) {
                            this.shipMap[xCoordinate][yCoordinate+ i] = shipBV;
                        }
                        break;
                        
                }
                this.bigCounter++;
                break;
        }

        //if all ships set --> complete = true
        if(this.smallCounter == 3 && this.mediumCounter == 2 && this.bigCounter == 1){
            this.complete = true;
            this.shipCounter = this.smallCounter + this.mediumCounter + this.bigCounter;
        }
        return this.complete;
    }


    public FieldValues shot(int xCoordinates, int yCoordinates) throws OutOfFieldException {

        //check if coordinates within array bounds
        if (xCoordinates < ARRAYMIN
                || xCoordinates > ARRAYMAX
                || yCoordinates < ARRAYMIN
                || yCoordinates > ARRAYMAX) {
            throw new OutOfFieldException();
        }
        //if object on coordinates hit it send back HIT or DEAD
        if(this.shipMap[xCoordinates][yCoordinates] != null){
            ShipInterface ship = this.shipMap[xCoordinates][yCoordinates];
            ship.hitShip(xCoordinates,yCoordinates);
            //if ship dead --> increment shipCounter
            if(ship.isDead()){
                //if 0 --> game over
                this.shipCounter--;
                //(when DEAD send coordinateArray over network, it will be expected)
                return DEAD;
            } else {
                return HIT;
            }
        }
        //change status
        return MISS;
    }

    public boolean shipsLeft(){
        if (this.shipCounter == 0) {
            return false;
        }
        return true;
    }

    // (maybe sent coordinateArray directly when DEAD occurs no need to wait for being asked)
    public int[] getCoordinates(int xCoordinates, int yCoordinates) {
        return this.shipMap[xCoordinates][yCoordinates].getCoordinates(xCoordinates, yCoordinates);
    }
}





