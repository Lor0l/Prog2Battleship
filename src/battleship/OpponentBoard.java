package battleship;

public class OpponentBoard extends BoardImpl implements OpponentBoardInterface {

    public OpponentBoard(String opponentName) {
        super(opponentName);
        // get shipBoard from opponent via TCP
    }

    @Override
    public void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException {
        /*
         * 1. check shipArray if not null update ship status and ship counter in case of death
         * 2. update hitArray
         */
    }

    // send updated hitArray via TCP

}
