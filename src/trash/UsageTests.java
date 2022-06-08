package trash;

import battleship.*;
import org.junit.Assert;
import org.junit.Test;
import trash.BoardOpponentInterface;
import trash.BoardOpponentMock;
import trash.BoardOwnInterface;
import trash.BoardOwnMock;

import static battleship.FieldValues.*;
import static battleship.OrientationValues.HORIZONTALLY;
import static battleship.OrientationValues.VERTICALLY;
import static battleship.ShipValues.*;

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
    //                                                DOCUMENTATION                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    //
    //  KEY
    //⠀⠀⠀⠀⠀⠀
    //  |small ship:   (x0,y0)(x1,y0)                                   |  + positive test      ~ unfinished
    //  |medium Ship:  (x0,y1)(x1,y1)(x2,y1)                            |  ! edge test          X finished
    //  |big ship:     (x0,y3)(x1,y3)(x2,y3)(x3,y3)(x4,y3)              |  - negative test
    //  |hit:   X      |dead:   *
    //  |miss:  /      |water:  ~
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
    //  Y
    //
    //
    ////
    //  BOARD_PLAYER_INTERFACE TESTS (8)
    //
    ///
    //  set() + getShips()
    //   + tests on small, medium, big and for horizontally, vertically to cover all possible input 1/6?
    //   ! tests on array bounds 2/2
    //   - tests on each possible exception case 3+
    //
    //  NR      DESCRIPTION                             AMOUNT      STATUS      NAME
    //
    //  01      + medium ship surrounded by water       (1)         X           setPos_MediumHorizontally()
    //  02      ! small ship on first array field       (1)         X           setEdge1_SmallHorizontally()
    //  03      ! big touching last array field         (1)         X           setEdge2_BigVertically()
    //  04      - Coordinates out of array bounds       (2)         X           setNegTest_BadCoordinates1()
    //  05                                                          X           setNegTest_BadCoordinates2()
    //  06      - set ship over boundary                (2)         X           setNegTest_ShipOverBounds1()
    //  07                                                          X           setNegTest_ShipOverBounds2()
    //  08      - set ship over ship                    (1)         X           setNegTest_ShipOverShip()
    //  09      - set ship touches ship
    //
    //
    ////
    //  BOARD_OPPONENT_INTERFACE TESTS
    //
    ///
    //  shoot()
    //   + test shoot on initialized boardOpponent position with ship --> hitMap value "HIT"
    //   + test 2x shoot on initialized boardOpponent positions with small ship --> hitMap value "DEAD"
    //   + test shoot on initialized boardOpponent position without ship --> no exception + hitMap value "MISS"
    //   - test for each possible Exception case:
    //     - out of array bounds
    //
    //  NR      DESCRIPTION                             AMOUNT      STATUS      NAME
    //
    //  01      + shoot with hit                        (1)         X           shootPos_ShipHit()
    //  02      + shoot with dead                       (1)         X           shootPos_ShipDead()
    //  ...
    //
    //
    ////
    //  ROUND_SIMULATION
    //
    //  + test set up phase then playerA misses playerB misses too --> hitMapA/B MISS entries
    //  + test playerA hits and misses playerB misses --> hitMapA HIT, hitMapB MISS entry
    //  - test set up phase not all ships set and shoot is invoked --> WrongStatusException
    //  - test set up completed and set is invoked --> WrongStatus Exception
    //  - test playerA misses and shoots again then playerB hits
    //      --> hitMapA second shot no entry and WrongStatusException hitMapB HIT entry
    //
    //  NR      DESCRIPTION                             AMOUNT      STATUS      NAME
    //
    //  01      + set up then A misses then B misses    (1)         ~           simulationPos_BothMiss()
    //
    //  ...
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                           MOCK OBJECTS and PLAYERS                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private final String playerA = "Alice";
    private final String playerB = "Bob";

    private static BoardOwnInterface getBoardPlayer(String player) {
        return new BoardOwnMock(player);
    }
    private static BoardOpponentInterface getBoardOpponent(String player, ShipInterface[][] shipMap) {
        return new BoardOpponentMock(player, shipMap);
    }





    ///////////////////////////////////////////////////BOARD_PLAYER/////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                POSITIVE TESTS                                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void setPos_MediumHorizontally()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

        /* set medium ship horizontally on (x4,y5)
         * expect an object in shipMap[4][5] - shipMap[6][5]
         *
         *
         *     0  1  2  3  4  5  6  7  8  9     X
         *  0 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  5 [ ][ ][ ][ ](M)[M][M][ ][ ][ ]
         *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *
         *  Y
         *
         */

        int xC = 4;
        int yC = 5;
        ShipValues ship = MEDIUM;
        OrientationValues orientation = HORIZONTALLY;

        BoardOwnInterface boardPlayerA;
        ShipInterface[][] shipMap;

        boardPlayerA = getBoardPlayer(playerA);
        boardPlayerA.set(xC, yC, ship, orientation);

        shipMap = boardPlayerA.getShipMap();
        ShipInterface part1 = shipMap[xC][yC];
        ShipInterface part2 = shipMap[xC + 1][yC];
        ShipInterface part3 = shipMap[xC + 2][yC];
        Assert.assertNotNull(part1);
        Assert.assertNotNull(part2);
        Assert.assertNotNull(part3);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  EDGE TESTS                                                    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    public void setEdge1_SmallHorizontally()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

        /* set small ship horizontally on (x0,y0)
        *  expect an object in shipMap[0][0] && shipMap[0][1]
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
        ShipValues ship = SMALL;
        OrientationValues orientation = HORIZONTALLY;

        BoardOwnInterface boardPlayerA;
        ShipInterface[][] shipMap;

        boardPlayerA = getBoardPlayer(playerA);
        boardPlayerA.set(xC, yC, ship, orientation);

        shipMap = boardPlayerA.getShipMap();
        ShipInterface part1 = shipMap[xC][yC];
        ShipInterface part2 = shipMap[xC + 1][yC];
        Assert.assertNotNull(part1);
        Assert.assertNotNull(part2);

    }


    @Test
    public void setEdge2_BigVertically() throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

         /* set big ship vertically on (x9,y5)
         *  expect an object in shipMap[9][5] - shipMap[9][9]
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
        ShipValues ship = BIG;
        OrientationValues orientation = VERTICALLY;

        BoardOwnInterface boardPlayerA;
        ShipInterface[][] shipMap;

        boardPlayerA = getBoardPlayer(playerA);
        boardPlayerA.set(xC, yC, ship, orientation);

        shipMap = boardPlayerA.getShipMap();
        ShipInterface part1 = shipMap[xC][yC];
        ShipInterface part2 = shipMap[xC][yC + 1];
        ShipInterface part3 = shipMap[xC][yC + 2];
        ShipInterface part4 = shipMap[xC][yC + 3];
        ShipInterface part5 = shipMap[xC][yC + 4];
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
        ShipValues ship = SMALL;
        OrientationValues orientation = VERTICALLY;

        BoardOwnInterface boardPlayerA;

        boardPlayerA = getBoardPlayer(playerA);
        boardPlayerA.set(xC, yC, ship, orientation);

    }


    @Test (expected = OutOfFieldException.class)
    public void setNegTest_BadCoordinates2()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException {

        // set small ship horizontally on (x10,y10)
        // expect an OutOfFieldException

        int xC = 10;
        int yC = 10;
        ShipValues ship = SMALL;
        OrientationValues orientation = HORIZONTALLY;

        BoardOwnInterface boardPlayerA;

        boardPlayerA = getBoardPlayer(playerA);
        boardPlayerA.set(xC, yC, ship, orientation);

    }


    @Test
    public void setNegTest_ShipOverBounds1()
            throws WrongStatusException, FieldOccupiedException {

        /* set big ship vertically on (x6,y9)
         * expect an OutOfFieldException and values in shipMap[6][9] - shipMap[9][9] to be NULL
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
        ShipValues ship = BIG;
        OrientationValues orientation = HORIZONTALLY;

        BoardOwnInterface boardPlayerA;
        ShipInterface[][] shipMap;

        boardPlayerA = getBoardPlayer(playerA);

        try {
            boardPlayerA.set(xC, yC, ship, orientation);
            Assert.fail();
        } catch (OutOfFieldException oofe) {
            oofe.printStackTrace();
        }

        shipMap = boardPlayerA.getShipMap();
        ShipInterface part1 = shipMap[xC][yC];
        ShipInterface part2 = shipMap[xC][yC + 1];
        ShipInterface part3 = shipMap[xC][yC + 2];
        ShipInterface part4 = shipMap[xC][yC + 3];
        Assert.assertNull(part1);
        Assert.assertNull(part2);
        Assert.assertNull(part3);
        Assert.assertNull(part4);

    }
    @Test
    public void setNegTest_ShipOverBounds2()
            throws WrongStatusException, FieldOccupiedException {

        /* set medium ship horizontally on (x9,y8)
         * expect an OutOfFieldException and values in shipMap[9][8] and shipMap[9][9] to be NULL
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
        ShipValues ship = MEDIUM;
        OrientationValues orientation = VERTICALLY;

        BoardOwnInterface boardPlayerA;
        ShipInterface[][] boardPlayer;

        boardPlayerA = getBoardPlayer(playerA);

        try {
            boardPlayerA.set(xC, yC, ship, orientation);
            Assert.fail();
        } catch (OutOfFieldException oofe) {
            oofe.printStackTrace();
        }

        boardPlayer = boardPlayerA.getShipMap();
        ShipInterface part1 = boardPlayer[xC][yC];
        ShipInterface part2 = boardPlayer[xC][yC + 1];
        Assert.assertNull(part1);
        Assert.assertNull(part2);

    }


    @Test
    public void setNegTest_ShipOverShip()
            throws OutOfFieldException, WrongStatusException {

        /* set medium ship vertically on (x2,y2) and a small ship horizontally on (x1,y2)
         * expect an FieldOccupiedException and an object in shipMap[2][2] - shipMap[2][4]
         * and the value in shipMap[1][2] to be NULL
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
        ShipValues shipM = MEDIUM;
        OrientationValues orientationM = VERTICALLY;

        //Small ship
        int xS = 1;
        int yS = 2;
        ShipValues shipS = SMALL;
        OrientationValues orientationS = HORIZONTALLY;

        BoardOwnInterface boardPlayerA;
        ShipInterface[][] shipMap;

        boardPlayerA = getBoardPlayer(playerA);

        try {
            boardPlayerA.set(xM, yM, shipM, orientationM);
        } catch (FieldOccupiedException foe) {
            foe.printStackTrace();
            Assert.fail();
        }

        try {
            boardPlayerA.set(xS, yS, shipS, orientationS);
            Assert.fail();
        } catch (FieldOccupiedException foe) {
            foe.printStackTrace();
        }

        shipMap = boardPlayerA.getShipMap();

        Assert.assertNotNull(shipMap[xM][yM]);
        Assert.assertNotNull(shipMap[xM][yM + 1]);
        Assert.assertNotNull(shipMap[xM][yM + 2]);
        Assert.assertNull(shipMap[xS][yS]);

    }
    //////////////////////////////////////////////////BOARD_OPPONENT////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                POSITIVE TESTS                                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void shootPos_ShipHit() throws OutOfFieldException, WrongStatusException, FieldOccupiedException {
        /* playerA's turn, shoot on playerB --> hit
         * set up playerB's boardOwn with small ship horizontally on (0,0)
         * set up boardOpponentA with playerB shipMap
         * shoot on (0,0)
         * expect HIT marked on hitMap of playerA's boardOpponent
         * (and other ship location to be WATER)
         *
         *  boardOwnB
         *  boardOpponentA                                         boardOpponentA
         *  shipMap                                                hitMap
         *
         *     0  1  2  3  4  5  6  7  8  9     X                     0  1  2  3  4  5  6  7  8  9     X
         *  0 (S)[S][ ][ ][ ][ ][ ][ ][ ][ ]                       0 [H][~][~][~][~][~][~][~][~][~]
         *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       1 [~][~][~][~][~][~][~][~][~][~]
         *  2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       2 [~][~][~][~][~][~][~][~][~][~]
         *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       3 [~][~][~][~][~][~][~][~][~][~]
         *  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]          ==>          4 [~][~][~][~][~][~][~][~][~][~]
         *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       5 [~][~][~][~][~][~][~][~][~][~]
         *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       6 [~][~][~][~][~][~][~][~][~][~]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       7 [~][~][~][~][~][~][~][~][~][~]
         *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       8 [~][~][~][~][~][~][~][~][~][~]
         *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       9 [~][~][~][~][~][~][~][~][~][~]
         *
         *  Y                                                      y
                *
         */

        int xC = 0;
        int yC = 0;
        ShipValues ship = SMALL;
        OrientationValues orientation = HORIZONTALLY;
        FieldValues hit = HIT;
        FieldValues water = WATER;

        BoardOwnInterface boardPlayerB;
        BoardOpponentInterface boardOpponentA;

        boardPlayerB = getBoardPlayer(playerB);
        boardPlayerB.set(xC, yC, ship, orientation);

        //wrong status exception if set up is not completed ...

        //maybe both boards need to be set up complete?

        boardOpponentA = getBoardOpponent(playerA, boardPlayerB.getShipMap());
        boardOpponentA.shoot(xC, yC);

        FieldValues[][] hitMapOpponentA = boardOpponentA.getHitMap();
        Assert.assertEquals(hit, hitMapOpponentA[xC][yC]);
        Assert.assertEquals(water, hitMapOpponentA[xC + 1][yC]);
    }

    @Test
    public void shootPos_ShipDead() throws OutOfFieldException, WrongStatusException, FieldOccupiedException {
        /* playerA's turn, shoot on playerB
         * set up playerB shipMap with small ship horizontally on (0,0)
         * set up boardOpponentA with playerB shipMap
         * shoot on (0,0) and on (1,0)
         * expect DEAD marked on hitMap
         *
         * (set up not completed --> bypass wrong status exception ?!)
         *
         *
         *
         *  boardOpponentA                                         boardOpponentA
         *  shipMap                                                hitMap
         *
         *     0  1  2  3  4  5  6  7  8  9     X                     0  1  2  3  4  5  6  7  8  9     X
         *  0 (S)[S][ ][ ][ ][ ][ ][ ][ ][ ]                       0 [D][D][ ][ ][ ][ ][ ][ ][ ][ ]
         *  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]          ==>          4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *
         *  Y                                                      y
                *
         */

        int xC = 0;
        int yC = 0;
        ShipValues ship = SMALL;
        OrientationValues orientation = HORIZONTALLY;

        FieldValues dead = DEAD;

        BoardOwnInterface boardPlayerB;
        BoardOpponentInterface boardOpponentA;
        FieldValues[][] hitMap;

        boardPlayerB = getBoardPlayer(playerB);
        boardPlayerB.set(xC, yC, ship, orientation);

        boardOpponentA = getBoardOpponent(playerA, boardPlayerB.getShipMap());
        boardOpponentA.shoot(xC, yC);
        boardOpponentA.shoot(xC + 1, yC);

        hitMap = boardOpponentA.getHitMap();

        Assert.assertEquals(dead, hitMap[xC][yC]);
        Assert.assertEquals(dead, hitMap[xC + 1][yC]);
    }


    /////////////////////////////////////////////////ROUND_SIMULATION///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                POSITIVE TESTS                                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Test
    public void simulationPos_BothMiss() throws OutOfFieldException, WrongStatusException, FieldOccupiedException {
        /* A and B both miss
         * Set up both boards of each player
         * PlayerA's turn, A shoots on playerB --> miss
         * PlayerB's turn, B shoots on playerA --> miss
         * Expect on playerA's and playerB's boardOpponent the hitMap[5][5] value to be 'MISS'
         *
         *  boardOpponentA/B                                       boardOpponentA/B
         *  boardOwnB/A
         *  shipMap                                                hitMap
         *
         *     0  1  2  3  4  5  6  7  8  9     X                     0  1  2  3  4  5  6  7  8  9     X
         *  0 (S)[S][ ][ ](B)[ ][ ][ ][ ][ ]                       0 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  1 [ ][ ][ ][ ][B][ ][ ][ ][ ][ ]                       1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  2 (S)[S][ ][ ][B][ ][ ][ ][ ][ ]                       2 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  3 [ ][ ][ ][ ][B][ ][ ][ ][ ][ ]                       3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  4 (S)[S][ ][ ][B][ ][ ][ ][ ][ ]          ==>          4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       5 [ ][ ][ ][ ][ ][/][ ][ ][ ][ ]
         *  6 (M)[M][M][ ][ ][ ][ ][ ][ ][ ]                       6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  8 (M)[M][M][ ][ ][ ][ ][ ][ ][ ]                       8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]                       9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
         *
         *  Y                                                      y
         *
         */

        //shipsA/B
        int xS1 = 0;
        int yS1 = 0;
        int xS2 = 0;
        int yS2 = 2;
        int xS3 = 0;
        int yS3 = 4;
        int xM1 = 0;
        int yM1 = 6;
        int xM2 = 0;
        int yM2 = 8;
        int xB1 = 4;
        int yB1 = 0;
        final ShipValues S = SMALL;
        final ShipValues M = MEDIUM;
        final ShipValues B = BIG;
        final OrientationValues H = HORIZONTALLY;
        final OrientationValues V = VERTICALLY;

        //shoot (miss)
        int xShoot = 5;
        int yShoot = 5;

        //hitMap entry
        FieldValues miss = MISS;

        //variables
        //own board
        BoardOwnInterface ownA;
        BoardOwnInterface ownB;
        //opponent board
        BoardOpponentInterface oppA;
        BoardOpponentInterface oppB;
        //hitMaps
        FieldValues[][] hitMapA;
        FieldValues[][] hitMapB;

        //create own board
        ownA = getBoardPlayer(playerA);
        ownB = getBoardPlayer(playerB);

        //SET UP PHASE (STATUS: SET)
        ownA.set(xS1, yS1, S, H);
        ownA.set(xS2, yS2, S, H);
        ownA.set(xS3, yS3, S, H);
        ownA.set(xM1, yM1, M, H);
        ownA.set(xM2, yM2, M, H);
        ownA.set(xB1, yB1, B, V);

        ownB.set(xS1, yS1, S, H);
        ownB.set(xS2, yS2, S, H);
        ownB.set(xS3, yS3, S, H);
        ownB.set(xM1, yM1, M, H);
        ownB.set(xM2, yM2, M, H);
        ownB.set(xB1, yB1, B, V);

        //set up ready - board exchange
        oppA = getBoardOpponent(playerA, ownB.getShipMap());
        oppB = getBoardOpponent(playerB, ownA.getShipMap());

        //GAME PHASE (STATUS: YOUR TURN/ WAIT)
        //A's turn
        // A shoots
        oppA.shoot(xShoot, yShoot);
        //A misses --> B's turn
        //B shoots
        oppB.shoot(xShoot, yShoot);
        //END OF TEST SCENARIO

        //get hitMaps
        hitMapA = oppA.getHitMap();
        hitMapB = oppB.getHitMap();

        //check if hitMap value == 'MISS'
        Assert.assertEquals(miss, hitMapA);
        Assert.assertEquals(miss, hitMapB);















        //testklasse als basis für szenario --> wo objekte im programm erzeugt? "




    }


}
