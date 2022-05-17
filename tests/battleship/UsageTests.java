package battleship;

import org.junit.Assert;
import org.junit.Test;

public class UsageTests {

    /*    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                                          ⠀ ⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀ ⣀⣸⣇⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                ⢀⣀⣸⣇⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀                               ⠀ ⠀⠀⠀⠀⠀⠀⠀⠉⠉⢹⡏⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                ⠸⢾⡟⠛⠛⠛⠛⢻⡷⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                              ==⣾⣤⡴⠟⣿⣦⣤⣷==⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀                                ⠀==⢰⡟⠋⢿⡷⠀⣿⡇⠈⣿⣿⡆==⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀                                ⠀⠀⠀⠈⣿⠀⠀⠀⠀⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀                                ⠀⠀⠀⠀⢻⡀⠀⠀⠀⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀                                ⠀⠀⣀⣀⣀⣀⣠⣼⣧⣤⣤⣤⣿⣿⣿⣿⣧⣤⣤⣤⣀⣀⣀⣤⠀⠀⠀
                                           ⠀⠘⠛⠛⠛⠉⠉⠉⠉⠙⠛⠛⠷⠶⢶⣦⣤⣤⣤⣴⡶⠿⠟⠛⠋⠁⠀⠀⠀
                                           ⠀⠀⠀⠀⠀⠛⠛⠛⠛⠻⠷⠶⠶⣶⣤⣤⣤⣤⣤⣤⣤⣤⣤⡶⠶⠶⠀⠀⠀⠀
                                          ⠀⢠⡶⠶⠶⠿⠛⠛⠻⠷⠶⢶⣤⣤⣤⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀

    *///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              BATTLESHIP TESTS                                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    //
    //
    //  KEY
    //⠀⠀⠀⠀⠀⠀
    //  |small ship:   (x0,y0)(x1,y0)                                   |  + positive test      ~ started
    //  |medium Ship:  (x0,y1)(x1,y1)(x2,y1)                            |  ! edge test          X done
    //  |big ship:     (x0,y3)(x1,y3)(x2,y3)(x3,y3)(x4,y3)              |  - negative test
    //
    //    0  1  2  3  4  5  6  7  8  9     X
    //  0 [S][S][ ][ ][ ][ ][ ][ ][ ][ ]
    //  1 [M][M][M][ ][ ][ ][ ][ ][ ][ ]
    //  2 [B][B][B][B][B][ ][ ][ ][ ][ ]
    //  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    //  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    //  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    //  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    //  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    //  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    //  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    //
    //
    /////
    //  DOCUMENTATION
    //
    ////
    //  BOARD_PLAYER_INTERFACE TESTS (8)
    //
    ///
    //  set() + getShips()
    //   pos tests on small, medium, big and for horizontally, vertically to cover all possible input 1/6?
    //   edg tests on array bounds 2/2
    //   neg tests on each possible exception case 3+
    //
    //  DESCRIPTION                             AMOUNT      STATUS      NAME
    //
    //  + medium ship surrounded by water       (1)         ~           setPos_MediumHorizontally()
    //  ! small ship on first array field       (1)         ~           setEdge1_SmallHorizontally()
    //  ! big touching last array field         (1)         ~           setEdge2_BigVertically()
    //  - Coordinates out of array bounds       (2)         X           setNegTest_BadCoordinates1()
    //                                                                  setNegTest_BadCoordinates2()
    //  - set ship over boundary                (2)         X           setNegTest_ShipOverBounds1()
    //                                                                  setNegTest_ShipOverBounds2()
    //  - set ship over ship                    (1)         ~           setNegTest_ShipOverShip()
    //  - set ship touches ship
    ///
    //
    //  set() + getBoard()
    //   pos tests for ships represented on Board after set on valid position
    //   neg tests covered already with "set() + getShips()"
    //
    //  DESCRIPTION                             AMOUNT      STATUS      NAME
    //
    //  ...
    //
    ////
    //  BOARD_OPPONENT_INTERFACE TESTS
    //
    ///
    //  shoot()
    //   pos tests for shoot on position with and without ship
    //   neg tests for each possible Exception case:
    //     - out of array bounds
    //
    //  DESCRIPTION                             AMOUNT      STATUS      NAME
    //
    //
    //  ...
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                MOCK OBJECTS                                                    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private static BoardPlayerInterface getBoardPlayer() {
        return new BoardPlayerMock();
    }
    private static BoardOpponentInterface getBoardOpponent() {
        return new BoardOpponentMock();
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                POSITIVE TESTS                                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void setPos_MediumHorizontally()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

        /* set medium ship horizontally on (x4,y5)
         *  expect an Object(M) of type ShipMedium in boardPlayer[4][5] - boardPlayer[6][5]
         *
         *
         *     0  1  2  3  4  5  6  7  8  9     X
         *  0 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  5 [ ][ ][ ][ ][M][M][M][ ][ ][ ]
         *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *
         *  Y
         *
         */

        int xC = 0;
        int yC = 0;
        int ship = 0;
        int orientation = 0;

        BoardPlayerInterface BoardPlayerA = getBoardPlayer();   // how to set up test without knowing ShipSuperClass?
        ShipSuper[][] boardPlayer;                              // necessary to set up environment to test set method

        BoardPlayerA.set(xC, yC, ship, orientation);

        boardPlayer = BoardPlayerA.getShips();
        ShipSuper part1 = boardPlayer[0][0];
        ShipSuper part2 = boardPlayer[1][0];
        Assert.assertNotNull(part1);
        Assert.assertNotNull(part2);


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  EDGE TESTS                                                    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    public void setEdge1_SmallHorizontally()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

        /* set small ship horizontally on (x0,y0)
        *  expect an Object(S) of type ShipSmall in boardPlayer[0][0] && boardPlayer[0][1]
        *
        *
        *     0  1  2  3  4  5  6  7  8  9     X
        *  0 (S)[S][ ][ ][ ][ ][ ][ ][ ][ ]
        *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *  2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        *
        *  Y
        *
        */

        int xC = 0;
        int yC = 0;
        int ship = 0;
        int orientation = 0;

        BoardPlayerInterface BoardPlayerA = getBoardPlayer();   // how to set up test without knowing ShipSuperClass?
        ShipSuper[][] boardPlayer;                              // necessary to set up environment to test set method

        BoardPlayerA.set(xC, yC, ship, orientation);

        boardPlayer = BoardPlayerA.getShips();
        ShipSuper part1 = boardPlayer[0][0];
        ShipSuper part2 = boardPlayer[1][0];
        Assert.assertNotNull(part1);
        Assert.assertNotNull(part2);

    }


    @Test
    public void setEdge2_BigVertically() throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

         /* set big ship vertically on (x9,y5)
         *  expect an Object(B) of type ShipSmall in boardPlayer[9][5] - boardPlayer[9][9]
         *
         *     0  1  2  3  4  5  6  7  8  9     X
         *  0 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ](B)
         *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][B]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][B]
         *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][B]
         *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][B]
         *
         *  Y
         *
         */

        int xC = 9;
        int yC = 5;
        int ship = 2;
        int orientation = 1;

        BoardPlayerInterface BoardPlayerA = getBoardPlayer();
        ShipSuper[][] boardPlayer;

        BoardPlayerA.set(xC, yC, ship, orientation);

        boardPlayer = BoardPlayerA.getShips();
        ShipSuper part1 = boardPlayer[9][5];
        ShipSuper part2 = boardPlayer[9][6];
        ShipSuper part3 = boardPlayer[9][7];
        ShipSuper part4 = boardPlayer[9][8];
        ShipSuper part5 = boardPlayer[9][9];
        Assert.assertNotNull(part1);
        Assert.assertNotNull(part2);
        Assert.assertNotNull(part3);
        Assert.assertNotNull(part4);
        Assert.assertNotNull(part5);

    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                NEGATIVE TESTS                                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test (expected = OutOfFieldException.class)
    public void setNegTest_BadCoordinates1()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

        // set small ship vertically on (x-1,y-1)
        // expect an OutOfFieldException

        int xC = -1;
        int yC = -1;
        int ship = 0;
        int orientation = 1;

        BoardPlayerInterface BoardPlayerA = getBoardPlayer();

        BoardPlayerA.set(xC, yC, ship, orientation);

    }


    @Test (expected = OutOfFieldException.class)
    public void setNegTest_BadCoordinates2()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

        // set small ship horizontally on (x10,y10)
        // expect an OutOfFieldException

        int xC = 10;
        int yC = 10;
        int ship = 0;
        int orientation = 0;

        BoardPlayerInterface BoardPlayerA = getBoardPlayer();
        ShipSuper[][] boardPlayer;

        BoardPlayerA.set(xC, yC, ship, orientation);

    }


    @Test
    public void setNegTest_ShipOverBounds1()
            throws WrongStatusException, FieldOccupiedException {

        /* set big ship vertically on (x6,y9)
         * expect an OutOfFieldException and Values in boardPlayer[6][9] - [9][9] to be NULL
         *
         *     0  1  2  3  4  5  6  7  8  9     X
         *  0 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  9 [ ][ ][ ][ ][ ][ ](B)[B][B][B]!B!
         *
         *  Y
         *
         */

        int xC = 6;
        int yC = 9;
        int ship = 2;
        int orientation = 0;

        BoardPlayerInterface BoardPlayerA = getBoardPlayer();
        ShipSuper[][] boardPlayer;


        try {
            BoardPlayerA.set(xC, yC, ship, orientation);
            Assert.fail();
        } catch (OutOfFieldException oofe) {
            oofe.printStackTrace();
        }

        boardPlayer = BoardPlayerA.getShips();
        ShipSuper part1 = boardPlayer[6][9];
        ShipSuper part2 = boardPlayer[7][9];
        ShipSuper part3 = boardPlayer[8][9];
        ShipSuper part4 = boardPlayer[9][9];
        Assert.assertNull(part1);
        Assert.assertNull(part2);
        Assert.assertNull(part3);
        Assert.assertNull(part4);

    }
    @Test
    public void setNegTest_ShipOverBounds2()
            throws WrongStatusException, FieldOccupiedException {

        /* set medium ship horizontally on (x9,y8)
         * expect an OutOfFieldException and Values in boardPlayer[9][8] and [9][9] to be NULL
         *
         *     0  1  2  3  4  5  6  7  8  9     X
         *  0 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][M]
         *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][M]
         *                               !M!
         *  Y
         *
         */

        int xC = 9;
        int yC = 8;
        int ship = 1;
        int orientation = 1;

        BoardPlayerInterface BoardPlayerA = getBoardPlayer();
        ShipSuper[][] boardPlayer;


        try {
            BoardPlayerA.set(xC, yC, ship, orientation);
            Assert.fail();
        } catch (OutOfFieldException oofe) {
            oofe.printStackTrace();
        }

        boardPlayer = BoardPlayerA.getShips();
        ShipSuper part1 = boardPlayer[8][9];
        ShipSuper part2 = boardPlayer[9][9];
        Assert.assertNull(part1);
        Assert.assertNull(part2);

    }


    @Test
    public void setNegTest_ShipOverShip()
            throws OutOfFieldException, WrongStatusException {

        /* set medium ship vertically on (x2,y2) and a small ship horizontally on (x1,y2)
         * expect an FieldOccupiedException and an object(M) of type ship medium in
         * boardPlayer[2][2] - boardPlayer[2][4] and the Value in boardPlayer[1][2] to be NULL
         *
         *     0  1  2  3  4  5  6  7  8  9     X
         *  0 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  2 [ ](S)(!)[ ][ ][ ][ ][ ][ ][ ]
         *  3 [ ][ ][M][ ][ ][ ][ ][ ][ ][ ]
         *  4 [ ][ ][M][ ][ ][ ][ ][ ][ ][ ]
         *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *
         *  Y
         *
         */

        //Medium ship
        int xM = 2;
        int yM = 2;
        int shipM = 1;
        int orientationM = 1;

        //Small ship
        int xS = 2;
        int yS = 1;
        int shipS = 0;
        int orientationS = 1;

        BoardPlayerInterface BoardPlayerA = getBoardPlayer();
        ShipSuper[][] boardPlayer;


        try {
            BoardPlayerA.set(xM, yM, shipM, orientationM);
        } catch (FieldOccupiedException foe) {
            foe.printStackTrace();
            Assert.fail();
        }

        try {
            BoardPlayerA.set(xS, yS, shipS, orientationS);
        } catch (FieldOccupiedException foe) {
            foe.printStackTrace();
        }

        boardPlayer = BoardPlayerA.getShips();
        ShipSuper part1 = boardPlayer[2][2];
        ShipSuper part2 = boardPlayer[2][3];
        ShipSuper part3 = boardPlayer[2][4];
        ShipSuper part4 = boardPlayer[1][2];

        Assert.assertNotNull(part1);
        Assert.assertNotNull(part2);
        Assert.assertNotNull(part3);
        Assert.assertNull(part4);

    }
}
