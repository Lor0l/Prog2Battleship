package battleship;

import org.junit.Assert;
import org.junit.Test;

public class UsageTests {

    /*
    *
    * small ship:   (0,0)(1,0)
    * medium Ship:  (0,1)(1,1)(2,1)
    * big ship:     (0,3)(1,3)(2,3)(3,3)(4,3)
    *
    *      0  1  2  3  4  5  6  7  8  9     X
    *    0 [S][S][ ][ ][ ][ ][ ][ ][ ][ ]
    *    1 [S][S][S][ ][ ][ ][ ][ ][ ][ ]
    *    2 [S][S][S][S][S][ ][ ][ ][ ][ ]
    *    3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    *    4 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    *    5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    *    6 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    *    7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    *    8 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    *    9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
    *
    *    Y
    *
    * toDoList
    *
    * set() and printPlayerBoard()
    * pos tests for (small), medium, big and for (horizontally) and vertically to cover all possible input.
    * neg tests for each possible Exception case:
    *   - out of array bounds
    *   - set ship on ship case
    *
    * shoot()
    * pos tests for shoot on position with and without ship
    * neg tests for each possible Exception case:
    *   - out of array bounds
    *
    */


    private final String PLAYERNAME = "Alice";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                              POSITIVE TESTS                                                    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void setPosTest_SmallHorizontally() {
        // set small ship horizontally on x0,y0
        // expect an Object(S) of type ShipSmall in playerBoard[0][0] && playerBoard[0][1]

        /*
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
        */

        int xC = 0;
        int yC = 0;
        int ship = 0;
        int orientation = 0;

        PlayerBoard TestBoardAlice = new PlayerBoard(PLAYERNAME);
        ShipSuper[][] playerBoard;


        try {
            TestBoardAlice.set(xC, yC, ship, orientation);
        } catch (WrongStatusException | OutOfFieldException | FieldOccupiedException e) {
            Assert.fail();
        }

        playerBoard = TestBoardAlice.getShips();
        ShipSuper part1 = playerBoard[0][0];
        ShipSuper part2 = playerBoard[1][0];
        Assert.assertNotNull(part1);
        Assert.assertNotNull(part2);
    }

    @Test
    public void setPosTest_BigVertically() {
        // set big ship vertically on x9,y5
        // expect an Object(S) of type ShipSmall in playerBoard[0][0] && playerBoard[0][1]
        /*
        *     0  1  2  3  4  5  6  7  8  9     X
        *  0 (S)[S][ ][ ][ ][ ][ ][ ][ ][ ]
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
        */

        int xC = 0;
        int yC = 0;
        int ship = 0;
        int orientation = 0;

        PlayerBoard TestBoardAlice = new PlayerBoard(PLAYERNAME);
        ShipSuper[][] playerBoard;


        try {
            TestBoardAlice.set(xC, yC, ship, orientation);
        } catch (WrongStatusException | OutOfFieldException | FieldOccupiedException e) {
            Assert.fail();
        }

        playerBoard = TestBoardAlice.getShips();
        ShipSuper part1 = playerBoard[0][0];
        ShipSuper part2 = playerBoard[1][0];
        Assert.assertNotNull(part1);
        Assert.assertNotNull(part2);



    }


}
