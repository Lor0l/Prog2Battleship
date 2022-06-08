package trash;

import battleship.FieldValues;
import battleship.OutOfFieldException;
import battleship.ShipInterface;
import battleship.WrongStatusException;
import trash.BoardInterface;
import trash.BoardOpponentInterface;

public class BoardOpponentMock implements BoardInterface, BoardOpponentInterface {

    private final String player;
    private final ShipInterface[][] ships;
    BoardOpponentMock(String player, ShipInterface[][] ships){
        this.player = player;
        this.ships = ships;
    }

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
    public void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException {
        throw new UnsupportedOperationException(
                "Not supported yet.");
    }
}
