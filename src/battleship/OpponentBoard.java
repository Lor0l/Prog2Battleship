package battleship;

public class OpponentBoard implements OpponentBoardInterface {

    public OpponentBoard(String opponentName) {

    }

    @Override
    public void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException {

    }

    @Override
    public ShipSuper[][] getOpponentShipBoard() throws WrongStatusException {
        return new ShipSuper[0][];
    }
}
