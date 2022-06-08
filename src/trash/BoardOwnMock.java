package trash;

import battleship.*;
import trash.BoardInterface;
import trash.BoardOwnInterface;

public class BoardOwnMock implements BoardInterface, BoardOwnInterface {

    private final String player;
    BoardOwnMock(String player){
        this.player = player; }

    @Override
    public FieldValues[][] getHitMap() {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }

    @Override
    public ShipInterface[][] getShipMap() {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }

    @Override
    public void set(int xCoordinate, int yCoordinate, ShipValues ship, OrientationValues orientation)
            throws WrongStatusException, OutOfFieldException, FieldOccupiedException {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }
}
