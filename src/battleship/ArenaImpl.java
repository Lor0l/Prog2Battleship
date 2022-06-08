package battleship;

import static battleship.FieldValues.*;
import static battleship.StatusValues.*;

public class ArenaImpl implements ArenaInterface {

    private BoardOwn boardOwn;
    private BoardOpponent boardOpp;
    private RadioStation radioStation;
    private StatusValues status;


    public ArenaImpl(String playerName) {
        this.boardOwn = new BoardOwn();
        this.boardOpp = new BoardOpponent();
        this.radioStation = new RadioStation(this);
        this.status = SET_PHASE;
    }


    @Override
    public FieldValues[][] getHitMap() {
        return this.boardOpp.getHitMap();
    }


    @Override
    public ShipInterface[][] getShipMap() {
        return this.boardOwn.getShipMap();
    }


    @Override
    public void set(int xCoordinate, int yCoordinate, ShipValues ship, OrientationValues orientation)
            throws WrongStatusException, OutOfFieldException, FieldOccupiedException, IllegalInputException {


        if (this.status != SET_PHASE) {
            throw new WrongStatusException();
        }

        boolean setUpComplete;
        setUpComplete = this.boardOwn.set(xCoordinate, yCoordinate, ship, orientation);

        if (setUpComplete) {
            this.radioStation.rollDice();
            this.status = READY;
        }
        //send random number to other player
        //receive random number from other player
        //compare, if higher status --> TURN else WAIT
    }



    @Override
    public void shoot(int xCoordinate, int yCoordinate)
            throws WrongStatusException, OutOfFieldException, IllegalInputException {

        if (this.status != TURN) {
            throw new WrongStatusException();
        }

        //check if coordinates already been shot
        //maybe check for array bounds here already
        FieldValues[][] hitMap = this.boardOpp.getHitMap();
        if (hitMap[xCoordinate][yCoordinate] != WATER) {
            throw new IllegalInputException();
        }

        //(set status on WAIT_FOR_RESPONSE)

        //get back fieldValue of hit field (MISS, HIT, DEAD)
        FieldValues fieldValue;
        fieldValue = this.radioStation.shot(xCoordinate, yCoordinate);

        //depending on fieldValue make entry on hitMap
        if (fieldValue == DEAD) {
            //in case of DEAD ask for coordinates of all ship parts (or expect them to be sent over network)
            int[] coordinatesDead = this.radioStation.getCoordinates(xCoordinate, yCoordinate);
            this.boardOpp.setDead(coordinatesDead);
            //check if won
            // (other option: count DEATHS)
            if (this.radioStation.getStatusOpp() == LOST){
                this.status = WON;
            }
        } else {
            this.boardOpp.setHitMiss(xCoordinate, yCoordinate, fieldValue);
        }

        //change status if missed
        //(set status from WAIT_FOR_RESPONSE back to TURN or WAIT)
        if (fieldValue == MISS) {
            this.status = WAIT;
        }

    }


    public FieldValues shot(int xCoordinate, int yCoordinate) throws OutOfFieldException{

        FieldValues fieldvalue = this.boardOwn.shot(xCoordinate, yCoordinate);
        if (fieldvalue == MISS) {
            this.status = TURN;
        }

        //change status if ship count == 0
        if (fieldvalue == DEAD && !this.boardOwn.shipsLeft()) {
                this.status = LOST;
        }

        return fieldvalue;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              METHODS FOR TESTING                                               //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // network simulation //////////////////////////////////////////////////////////////////////////////////////////////

    StatusValues getStatus() {
        return this.status;
    }

    // set contact point
    void setRadioContact(ArenaImpl arenaOpp){
        this.radioStation.setArenaOpp(arenaOpp);
    }

    // send coordinates of DEAD ship parts
    int[] getCoordinates(int x, int y) {
        return this.boardOwn.getCoordinates(x, y);
    }

    // send dice to opponent
    long getDice() {
        return this.radioStation.getDice();
    }

    // determine beginner
    public void compareDice(){
        if(this.radioStation.compareDice()){
            this.status = TURN;
        } else {
            this.status = WAIT;
        }
    }



}
