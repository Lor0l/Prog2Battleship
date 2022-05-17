package battleship;

public class BoardPlayer extends BoardImpl implements BoardPlayerInterface {

    public BoardPlayer(String playerName) {
        super(playerName);
    }

    @Override
    public void set(int xCoordinate, int yCoordinate, int ship, int orientation)
            throws WrongStatusException, OutOfFieldException, FieldOccupiedException {

        // not sure if right //////////////////////////////
        ShipSuper s1 = new ShipSuper();
        ShipSuper[][] sa = getShips();
        sa[xCoordinate][yCoordinate] = s1;
        setShipBoard(sa);
        // right implementation in this way below /////////

        /* check if possible (needed array places taken/ out of bounds)
         * switch which object on ship-parameter
         * create ship object with x,y,orientation
         * shipCounter++
         * give object to shipBoard on x,y and x or (orientation) y + ship length(and all in between)
         * update hitBoard
         */
    }

    @Override
    public void updateBoard() {

        /* 0. get updated hitBoard via TCP
         * 1. compares updated hitBoard with old hitBoard(ships whited out)
         * 2. save coordinates where difference occurs and new symbol
         * 3. write new symbol in old hitBoard
         * 4. if x or + update objects status in on coordinates in shipArray (if + update shipCounter)
         */
    }

}
