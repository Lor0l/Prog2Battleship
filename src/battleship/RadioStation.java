package battleship;

import java.io.*;
import java.util.Random;

import static battleship.StatusValues.LOST;
import static battleship.StatusValues.READY;

public class RadioStation implements Runnable{

    private ArenaImpl arenaOwn;
    private ArenaImpl arenaOpp; // substitute with os
    private InputStream is;
    private OutputStream os;
    private boolean oracle;
    private long dice;

    private final int SHOT = 0;
    private final int GET_COORDINATES = 1;
    private final int RETURN_VALUE = 2;
    private final int GET_STATUS = 3;

    private final int HIT_CODE = 10;
    private final int DEAD_CODE = 11;
    private final int MISS_CODE = 12;

    private final int LOST_CODE = 20;
    private final int NOT_LOST_CODE = 21;
    private final int RETURN_STATUS = 22;
    private final int RETURN_COORDINATES = 23;

    private FieldValues returnedValueTemp;
    private int[] coordinatesTemp;
    private StatusValues returnStatusTemp;

    private Thread protocolThread;

    public RadioStation(ArenaImpl arenaOwn) {
        this.arenaOwn = arenaOwn;
    }



    public FieldValues shot(int xCoordinates, int yCoordinates) throws OutOfFieldException {
        //return this.arenaOpp.shot(xCoordinates, yCoordinates);
        DataOutputStream dos = new DataOutputStream(os);
        try {
            dos.writeInt(SHOT);
            dos.writeInt(xCoordinates);
            dos.writeInt(yCoordinates);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(this.returnedValueTemp == null){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        FieldValues value = this.returnedValueTemp;
        this.returnedValueTemp = null;

        return value;

    }


    public int[] getCoordinates(int x, int y) {

        DataOutputStream dos = new DataOutputStream(os);
        try {
            dos.writeInt(GET_COORDINATES);
            System.out.println("sent coordinate request");
            dos.writeInt(x);
            dos.writeInt(y);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail");
        }

        while (this.coordinatesTemp == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("got stuck");
        }

        int [] coordinates = this.coordinatesTemp;
        this.coordinatesTemp = null;

        System.out.println(" returning coordinates");

        return coordinates;
    }



    public void handleConnection(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
    }

    public void startCom(){
        this.protocolThread = new Thread(this);
        this.protocolThread.start();
        //start radioStation com thread
    }

    public boolean getOracle(){
        return oracle;
    }


    public boolean read(){

        int commandID;

        DataInputStream dis = new DataInputStream(is);
        DataOutputStream dos = new DataOutputStream(os);
        try {
            commandID = dis.readInt();
            switch (commandID) {
                case SHOT:
                    int xCoordinates = dis.readInt();
                    int yCoordinates = dis.readInt();
                    FieldValues value = this.arenaOwn.shot(xCoordinates, yCoordinates);
                    dos.writeInt(this.RETURN_VALUE);
                    switch (value) {
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
                case RETURN_VALUE:
                    int returnedValue = dis.readInt();
                    switch (returnedValue){
                        case HIT_CODE:
                           this.returnedValueTemp = FieldValues.HIT;
                           break;
                        case DEAD_CODE:
                            this.returnedValueTemp = FieldValues.DEAD;
                            break;
                        case MISS_CODE:
                            this.returnedValueTemp = FieldValues.MISS;
                            break;
                    }
                    break;
                case GET_STATUS:
                    StatusValues status = this.arenaOwn.getStatus();
                    dos.writeInt(RETURN_STATUS);
                    switch (status) {
                        case LOST:
                            dos.writeInt(LOST_CODE);
                            break;
                        default:
                            dos.writeInt(NOT_LOST_CODE);
                            break;
                    }
                    break;
                case RETURN_STATUS:
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
                case GET_COORDINATES:
                    int xC = dis.readInt();
                    int yC = dis.readInt();
                    int[] coordinates = this.arenaOwn.getCoordinates(xC, yC);
                    System.out.println(coordinates.length);
                    dos.writeInt(RETURN_COORDINATES);
                    dos.writeInt(coordinates.length);
                    for (int i = 0; i < coordinates.length; i++) {
                        dos.writeInt(coordinates[i]);
                    }
                    break;
                case RETURN_COORDINATES:
                    int coordinatesLength = dis.readInt();
                    int[] coordinatesBack = new int[coordinatesLength];
                    for (int i = 0; i < coordinatesLength; i++) {
                        coordinatesBack[i] = dis.readInt();
                    }
                    this.coordinatesTemp = coordinatesBack;
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (OutOfFieldException e) {
            e.printStackTrace();
        }


        return true;
    }


    @Override
    public void run() {
        //this.log("Protocol Engine started - flip a coin");
        long seed = this.hashCode() * System.currentTimeMillis();
        Random random = new Random(seed);

        int localInt = 0, remoteInt = 0;
        try {
            DataOutputStream dos = new DataOutputStream(this.os);
            DataInputStream dis = new DataInputStream(this.is);
            do {
                localInt = random.nextInt();
                //this.log("flip and take number " + localInt);
                dos.writeInt(localInt);
                System.out.println("wait for other player");
                remoteInt = dis.readInt();
            } while (localInt == remoteInt);

            this.oracle = localInt < remoteInt;
            System.out.println("coin flipped");
            //this.log("Flipped a coin and got an oracle == " + this.oracle);
            //this.oracleSet = true;

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





    // not necessary anymore

    public StatusValues getStatusOpp() {
        //return this.arenaOpp.getStatus();
        DataOutputStream dos = new DataOutputStream(os);
        try {
            dos.writeInt(GET_STATUS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (this.returnStatusTemp == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        StatusValues returnStatus = this.returnStatusTemp;
        this.returnStatusTemp = null;

        return returnStatus;
    }

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
