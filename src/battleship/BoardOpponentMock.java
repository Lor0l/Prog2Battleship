package battleship;

public class BoardOpponentMock implements BoardInterface, BoardOpponentInterface {
    @Override
    public char[][] getBoard() {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }

    @Override
    public ShipSuper[][] getShips() {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }

    @Override
    public void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }
}
