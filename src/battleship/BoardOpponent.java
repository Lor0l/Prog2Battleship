package battleship;

public class BoardOpponent extends BoardImpl implements BoardOpponentInterface {

    public BoardOpponent(String opponentName) {
        super(opponentName);
        // setShipBoard( get shipBoard from opponent via TCP );
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
