package battleship;

public class ArenaMock implements ArenaInterface{
    @Override
    public FieldValues[][] getHitMap() {
        return new FieldValues[0][];
    }

    @Override
    public ShipInterface[][] getShipMap() {
        return new ShipInterface[0][];
    }


    @Override
    public void shoot(int xCoordinate, int yCoordinate) throws WrongStatusException, OutOfFieldException {

    }


    @Override
    public void set(int xCoordinate, int yCoordinate, ShipValues ship, OrientationValues orientation) throws WrongStatusException, OutOfFieldException, FieldOccupiedException {

    }
}
