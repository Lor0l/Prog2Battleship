package battleship;

public class BoardPlayerMock implements BoardInterface, BoardPlayerInterface {
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
    public void set(int xCoordinate, int yCoordinate, int ship, int orientation) throws WrongStatusException, OutOfFieldException, FieldOccupiedException {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }

    @Override
    public void updateBoard() {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }
}
