package battleship;

import java.io.*;
import java.util.Random;

import static battleship.FieldValues.*;
import static battleship.StatusValues.LOST;
import static battleship.StatusValues.READY;

public class RadioStation implements Runnable{

    private ArenaImpl arenaOwn;
    private InputStream is;
    private OutputStream os;
    private boolean oracle;

    // communication Thread
    private Thread protocolThread;

    // command codes
    private final int SHOT = 0;
    private final int GET_COORDINATES = 1;
    private final int GET_STATUS = 3;

    private final int RETURN_SHOT = 21;
    private final int RETURN_STATUS = 22;
    private final int RETURN_COORDINATES = 23;

    private final int HIT_CODE = 10;
    private final int DEAD_CODE = 11;
    private final int MISS_CODE = 12;
    private final int LOST_CODE = 13;
    private final int NOT_LOST_CODE = 14;

    // temporary "return" values
    private FieldValues returnedHitMapValueTemp;
    private int[] coordinatesDeadTemp;
    private StatusValues returnStatusTemp;

    // FOR TESTS (non-distributed)
    private long dice;
    private ArenaImpl arenaOpp;


    public RadioStation(ArenaImpl arenaOwn) {
        this.arenaOwn = arenaOwn;
    }

    // initiate communication points in radioStation when connected
    public void handleConnection(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
    }

    // run communication thread
    public void startCom(){
        this.protocolThread = new Thread(this);
        this.protocolThread.start();
    }

    // determine beginner
    public boolean getOracle(){
        return oracle;
    }


    public FieldValues shot(int xCoordinates, int yCoordinates) throws OutOfFieldException {
        //return this.arenaOpp.shot(xCoordinates, yCoordinates); TEST

        DataOutputStream dos = new DataOutputStream(os);

        // send shot_x_y
        try {
            dos.writeInt(SHOT);
            dos.writeInt(xCoordinates);
            dos.writeInt(yCoordinates);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // wait for answer
        while(this.returnedHitMapValueTemp == null){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // extract return value from communication thread
        FieldValues returnedHitMapValue = this.returnedHitMapValueTemp;
        this.returnedHitMapValueTemp = null;

        return returnedHitMapValue;

    }

    // if DEAD get all coordinates for hitMap entry
    public int[] getCoordinates(int x, int y) {

        DataOutputStream dos = new DataOutputStream(os);

        //send coordinate request
        try {
            dos.writeInt(GET_COORDINATES);
            dos.writeInt(x);
            dos.writeInt(y);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // wait for answer
        while (this.coordinatesDeadTemp == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // debug helper
            // System.out.println("got stuck");
        }

        // extract return value from communication Thread
        int [] coordinates = this.coordinatesDeadTemp;
        this.coordinatesDeadTemp = null;

        // debug helper
        // System.out.println(" returning coordinates");

        return coordinates;
    }

    // check after each DEAD if opponent lost
    public StatusValues getStatusOpp() {
        //return this.arenaOpp.getStatus(); TEST

        DataOutputStream dos = new DataOutputStream(os);

        // send status request
        try {
            dos.writeInt(GET_STATUS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // wait for answer
        while (this.returnStatusTemp == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // extract return value from communication Thread
        StatusValues returnStatus = this.returnStatusTemp;
        this.returnStatusTemp = null;

        return returnStatus;
    }



    public boolean read(){

        int commandCode;
        DataInputStream dis = new DataInputStream(is);
        DataOutputStream dos = new DataOutputStream(os);

        try {
            commandCode = dis.readInt();                            // read command code
            switch (commandCode) {
                case SHOT:                                              // get shot
                    int x = dis.readInt();
                    int y = dis.readInt();
                    FieldValues value = this.arenaOwn.shot(x, y);       // check if hit
                    dos.writeInt(this.RETURN_SHOT);                         // send command code
                    switch (value) {                                        // send value
                        case HIT:
                            dos.writeInt(this.HIT_CODE);
                            break;
                        case DEAD:
                            dos.writeInt(this.DEAD_CODE);
                            break;
                        case MISS:
                            dos.writeInt(this.MISS_CODE);
                            break;
                    }
                    break;
                case RETURN_SHOT:                                       // receive hitMap value
                    int returnedValue = dis.readInt();                      // read code
                    switch (returnedValue){
                        case HIT_CODE:
                           this.returnedHitMapValueTemp = HIT;
                           break;
                        case DEAD_CODE:
                            this.returnedHitMapValueTemp = DEAD;
                            break;
                        case MISS_CODE:
                            this.returnedHitMapValueTemp = MISS;
                            break;
                    }
                    break;
                case GET_STATUS:                                        // status request
                    StatusValues status = this.arenaOwn.getStatus();        // get own status
                    dos.writeInt(RETURN_STATUS);                            // send command code
                    switch (status) {                                       // send status code
                        case LOST:
                            dos.writeInt(LOST_CODE);
                            break;
                        default:
                            dos.writeInt(NOT_LOST_CODE);
                            break;
                    }
                    break;
                case RETURN_STATUS:                                     // receive status value
                    int returnStatus = dis.readInt();
                    switch (returnStatus){
                        case LOST_CODE:
                            this.returnStatusTemp = LOST;
                            break;
                        case NOT_LOST_CODE:
                            this.returnStatusTemp = READY;
                            break;
                    }
                    break;
                case GET_COORDINATES:                                   // coordinate request (if DEAD)
                    int xC = dis.readInt();
                    int yC = dis.readInt();
                    int[] coordinates = this.arenaOwn.getCoordinates(xC, yC);
                    dos.writeInt(RETURN_COORDINATES);                       // send command code
                    dos.writeInt(coordinates.length);                       // send array length
                    for (int i = 0; i < coordinates.length; i++) {          // send series of coordinates
                        dos.writeInt(coordinates[i]);
                    }
                    break;
                case RETURN_COORDINATES:                                // receive DEAD coordinates
                    int coordinatesLength = dis.readInt();                  // read array length
                    int[] coordinatesBack = new int[coordinatesLength];
                    for (int i = 0; i < coordinatesLength; i++) {           // read series of coordinates
                        coordinatesBack[i] = dis.readInt();
                    }
                    this.coordinatesDeadTemp = coordinatesBack;
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                this.close();
            } catch (IOException ioException) {
                // ignore
            }
            return false;
        } catch (OutOfFieldException e) {
            e.printStackTrace();
        }

        return true;
    }


    @Override
    public void run() {
        long seed = this.hashCode() * System.currentTimeMillis();
        Random random = new Random(seed);

        int localInt = 0, remoteInt = 0;
        try {
            DataOutputStream dos = new DataOutputStream(this.os);
            DataInputStream dis = new DataInputStream(this.is);
            do {
                localInt = random.nextInt();
                dos.writeInt(localInt);
                System.out.println("wait for other player");
                remoteInt = dis.readInt();
            } while (localInt == remoteInt);

            this.oracle = localInt < remoteInt;
            System.out.println("coin flipped");

            // notify listening arena (should give oracle in method)
            this.arenaOwn.opponentReady();

        } catch (IOException e) {
            e.printStackTrace();
        }


        boolean again = true;
        while(again) {
            again = this.read();
        }

    }

    public void close() throws IOException {
        if(this.os != null) { this.os.close();}
        if(this.is != null) { this.is.close();}
    }





    // for tests (non-distributed) /////////////////////////////////////////////////////////////////////////////////////






    public void setArenaOpp(ArenaImpl arenaOpp) {
        this.arenaOpp = arenaOpp;
    }

    public void rollDice() {
        long seed = this.hashCode() * System.currentTimeMillis();
        Random random = new Random(seed);
        this.dice = random.nextInt();
    }

    public long getDice() {
        return this.dice;
    }

    public boolean compareDice() {
        long localDice = this.arenaOwn.getDice();
        long remoteDice = this.arenaOpp.getDice();
        if (localDice > remoteDice) {
            return true;
        } else {
            return false;
        }
    }

}
