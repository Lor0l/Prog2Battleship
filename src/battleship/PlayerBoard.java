package battleship;

public class PlayerBoard extends BoardImpl implements PlayerBoardInterface {

    public PlayerBoard(String playerName) {
        super(playerName);
    }

    @Override
    public void set(int xCoordinate, int yCoordinate, int ship, int orientation)
            throws WrongStatusException, OutOfFieldException, FieldOccupiedException {
        /* check if possible (needed array places taken/ out of bounds)
         * switch which object on ship-parameter
         * create Ship Object with x,y,orientation
         * give object to playerBoard on x,y and x or (orientation) y + ship length(and all in between)
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
