package battleship;

import java.util.Random;

public class RadioStation {

    private ArenaImpl arenaOwn;
    private ArenaImpl arenaOpp;
    private long dice;

    public RadioStation(ArenaImpl arenaOwn){ this.arenaOwn = arenaOwn; }


    public void setArenaOpp(ArenaImpl arenaOpp) {
        this.arenaOpp = arenaOpp;
    }


    public FieldValues shot(int xCoordinates, int yCoordinates) throws OutOfFieldException {
        return this.arenaOpp.shot(xCoordinates, yCoordinates);
    }


    public int[] getCoordinates(int x, int y) {
        return arenaOwn.getCoordinates(x, y);
    }

    public StatusValues getStatusOpp(){
        return this.arenaOpp.getStatus();
    }


    public void rollDice(){
        long seed = this.hashCode() * System.currentTimeMillis();
        Random random = new Random(seed);
        this.dice = random.nextInt();
    }

    public long getDice() {
        return this.dice;
    }

    public boolean compareDice(){
        long localDice = this.arenaOwn.getDice();
        long remoteDice = this.arenaOpp.getDice();
        if (localDice > remoteDice) {
            return true;
        } else {
            return false;
        }
    }



}
