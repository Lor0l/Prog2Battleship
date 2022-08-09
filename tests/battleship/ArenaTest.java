package battleship;

import org.junit.Assert;
import org.junit.Test;

import static battleship.FieldValues.*;
import static battleship.OrientationValues.HORIZONTALLY;
import static battleship.OrientationValues.VERTICALLY;
import static battleship.ShipValues.*;
import static battleship.StatusValues.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ALL TESTS ON PRE-DISTRIBUTED VERSION                                                                               //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class ArenaTest {

    final ShipValues S = SMALL;
    final ShipValues M = MEDIUM;
    final ShipValues B = BIG;
    final OrientationValues H = HORIZONTALLY;
    final OrientationValues V = VERTICALLY;
    final String PLAYER_A = "Alice";
    final String PLAYER_B = "Bob";


    @Test
    public void arenaSETtoReady_Pos()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaImpl arenaA = new ArenaImpl(PLAYER_A);

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

        arenaA.set(xS1, yS1, S, H);
        arenaA.set(xS2, yS2, S, H);
        arenaA.set(xS3, yS3, S, H);
        arenaA.set(xM1, yM1, M, H);
        arenaA.set(xM2, yM2, M, H);
        arenaA.set(xB1, yB1, B, V);

        ////////////////////////////////////////////////
        StatusValues status = arenaA.getStatus();
        Assert.assertEquals(status, StatusValues.READY);
        ////////////////////////////////////////////////

    }

    @Test (expected = OutOfFieldException.class)
    public void setOutOfBounds_Neg()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaInterface arenaA = new ArenaImpl(PLAYER_A);

        int xS1 = 9;
        int yS1 = 9;

        arenaA.set(xS1, yS1, S, H);

    }


    @Test(expected = FieldOccupiedException.class)
    public void setShipOnShip_Neg()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaInterface arenaA = new ArenaImpl(PLAYER_A);

        int xS1 = 0;
        int yS1 = 0;
        int xS2 = 0;
        int yS2 = 0;

        arenaA.set(xS1, yS1, S, H);
        arenaA.set(xS2, yS2, S, H);

    }


    @Test(expected = FieldOccupiedException.class)
    public void setShipOnShip2_Neg() throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaInterface arenaA = new ArenaImpl(PLAYER_A);

        int xS1 = 1;
        int yS1 = 0;
        int xS2 = 0;
        int yS2 = 1;

        arenaA.set(xS1, yS1, S, V);
        arenaA.set(xS2, yS2, S, H);

    }


    @Test(expected = FieldOccupiedException.class)
    public void setShipNextToShip1_Neg()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaInterface arenaA = new ArenaImpl(PLAYER_A);

        int xS1 = 0;
        int yS1 = 0;
        int xS2 = 0;
        int yS2 = 1;

        arenaA.set(xS1, yS1, S, H);
        arenaA.set(xS2, yS2, S, H);

    }


    @Test(expected = IllegalInputException.class)
    public void toManyShips_Neg()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaInterface arenaA = new ArenaImpl(PLAYER_A);

        int xS1 = 0;
        int yS1 = 0;
        int xS2 = 0;
        int yS2 = 2;
        int xS3 = 0;
        int yS3 = 4;
        int xM1 = 0;
        int yM1 = 6;

        arenaA.set(xS1, yS1, S, H);
        arenaA.set(xS2, yS2, S, H);
        arenaA.set(xS3, yS3, S, H);
        arenaA.set(xM1, yM1, S, H);

    }


    @Test(expected = WrongStatusException.class)
    public void setAfterSetUpComplete_Neg()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaInterface arenaA = new ArenaImpl(PLAYER_A);

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

        arenaA.set(xS1, yS1, S, H);
        arenaA.set(xS2, yS2, S, H);
        arenaA.set(xS3, yS3, S, H);
        arenaA.set(xM1, yM1, M, H);
        arenaA.set(xM2, yM2, M, H);
        arenaA.set(xB1, yB1, B, V);
        arenaA.set(8, 8, S, H);

    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Test
    public void deadShip_Pos()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaImpl arenaA = new ArenaImpl(PLAYER_A);
        ArenaImpl arenaB = new ArenaImpl(PLAYER_B);

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

        ///////////////////////////
        //need to know each other for test scenario
        //via network different
        arenaA.setRadioContact(arenaB);
        arenaB.setRadioContact(arenaA);
        ///////////////////////////

        arenaA.set(xS1, yS1, S, H);
        arenaA.set(xS2, yS2, S, H);
        arenaA.set(xS3, yS3, S, H);
        arenaA.set(xM1, yM1, M, H);
        arenaA.set(xM2, yM2, M, H);
        arenaA.set(xB1, yB1, B, V);

        arenaB.set(xS1, yS1, S, H);
        arenaB.set(xS2, yS2, S, H);
        arenaB.set(xS3, yS3, S, H);
        arenaB.set(xM1, yM1, M, H);
        arenaB.set(xM2, yM2, M, H);
        arenaB.set(xB1, yB1, B, V);

        /////////////////////
        arenaA.compareDice();
        arenaB.compareDice();
        /////////////////////

        if (arenaA.getStatus() == TURN) {
            arenaA.shoot(xS1, yS1);
            Assert.assertEquals(HIT, arenaA.getHitMap()[xS1][yS1]);
            arenaA.shoot(xS1 + 1, yS1);
            Assert.assertEquals(DEAD, arenaA.getHitMap()[yS1][yS1]);
            Assert.assertEquals(DEAD, arenaA.getHitMap()[xS1+1][yS1]);
        }

        if (arenaB.getStatus() == TURN) {
            arenaB.shoot(xS1, yS1);
            Assert.assertEquals(HIT, arenaB.getHitMap()[xS1][yS1]);
            arenaB.shoot(xS1 + 1, yS1);
            Assert.assertEquals(DEAD, arenaB.getHitMap()[yS1][yS1]);
            Assert.assertEquals(DEAD, arenaB.getHitMap()[xS1+1][yS1]);
        }

    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    @Test (expected = IllegalInputException.class)
    public void shootSamePosition_Neg()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        ArenaImpl arenaA = new ArenaImpl(PLAYER_A);
        ArenaImpl arenaB = new ArenaImpl(PLAYER_B);

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

        int NINE = 9;

        ///////////////////////////
        //set contact point
        arenaA.setRadioContact(arenaB);
        arenaB.setRadioContact(arenaA);
        ///////////////////////////

        arenaA.set(xS1, yS1, S, H);
        arenaA.set(xS2, yS2, S, H);
        arenaA.set(xS3, yS3, S, H);
        arenaA.set(xM1, yM1, M, H);
        arenaA.set(xM2, yM2, M, H);
        arenaA.set(xB1, yB1, B, V);

        arenaB.set(xS1, yS1, S, H);
        arenaB.set(xS2, yS2, S, H);
        arenaB.set(xS3, yS3, S, H);
        arenaB.set(xM1, yM1, M, H);
        arenaB.set(xM2, yM2, M, H);
        arenaB.set(xB1, yB1, B, V);

        /////////////////////
        arenaA.compareDice();
        arenaB.compareDice();
        /////////////////////


        if (arenaA.getStatus() == TURN) {
            arenaA.shoot(NINE, NINE);
            arenaB.shoot(NINE, NINE);
            arenaA.shoot(NINE, NINE);

        }

        if (arenaB.getStatus() == TURN) {
            arenaB.shoot(NINE, NINE);
            arenaA.shoot(NINE, NINE);
            arenaB.shoot(NINE, NINE);

        }

    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    @Test
    public void simulateFullGame()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        //beginning player wins in first turn (big horizontal)

        ArenaImpl arenaA = new ArenaImpl(PLAYER_A);
        ArenaImpl arenaB = new ArenaImpl(PLAYER_B);

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

        ///////////////////////////
        //set contact point
        arenaA.setRadioContact(arenaB);
        arenaB.setRadioContact(arenaA);
        ///////////////////////////

        arenaA.set(xS1, yS1, S, H);                           //     0  1  2  3  4  5  6  7  8  9
        arenaA.set(xS2, yS2, S, H);                           //  0 (S)[S][ ][ ](B)[ ][ ][ ][ ][ ]
        arenaA.set(xS3, yS3, S, H);                           //  1 [ ][ ][ ][ ][B][ ][ ][ ][ ][ ]
        arenaA.set(xM1, yM1, M, H);                           //  2 (S)[S][ ][ ][B][ ][ ][ ][ ][ ]
        arenaA.set(xM2, yM2, M, H);                           //  3 [ ][ ][ ][ ][B][ ][ ][ ][ ][ ]
        arenaA.set(xB1, yB1, B, V);                           //  4 (S)[S][ ][ ][B][ ][ ][ ][ ][ ]
                                                              //  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xS1, yS1, S, H);                           //  6 (M)[M][M][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xS2, yS2, S, H);                           //  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xS3, yS3, S, H);                           //  8 (M)[M][M][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xM1, yM1, M, H);                           //  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xM2, yM2, M, H);                           //
        arenaB.set(xB1, yB1, B, V);                           //

        /////////////////////
        arenaA.compareDice();
        arenaB.compareDice();
        /////////////////////


        if (arenaA.getStatus() == TURN) {

            arenaA.shoot(0,0);
            arenaA.shoot(1,0);

            arenaA.shoot(0,2);
            arenaA.shoot(1,2);

            arenaA.shoot(0,4);
            arenaA.shoot(1,4);

            arenaA.shoot(0,6);
            arenaA.shoot(1,6);
            arenaA.shoot(2,6);

            arenaA.shoot(0,8);
            arenaA.shoot(1,8);
            arenaA.shoot(2,8);

            arenaA.shoot(4,0);
            arenaA.shoot(4,1);
            arenaA.shoot(4,2);
            arenaA.shoot(4,3);
            arenaA.shoot(4,4);

            Assert.assertEquals(WON, arenaA.getStatus());
            Assert.assertEquals(LOST, arenaB.getStatus());

        }

        if (arenaB.getStatus() == TURN) {

            arenaB.shoot(0,0);
            arenaB.shoot(1,0);

            arenaB.shoot(0,2);
            arenaB.shoot(1,2);

            arenaB.shoot(0,4);
            arenaB.shoot(1,4);

            arenaB.shoot(0,6);
            arenaB.shoot(1,6);
            arenaB.shoot(2,6);

            arenaB.shoot(0,8);
            arenaB.shoot(1,8);
            arenaB.shoot(2,8);

            arenaB.shoot(4,0);
            arenaB.shoot(4,1);
            arenaB.shoot(4,2);
            arenaB.shoot(4,3);
            arenaB.shoot(4,4);

            Assert.assertEquals(WON, arenaB.getStatus());
            Assert.assertEquals(LOST, arenaA.getStatus());

        }

    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @Test
    public void simulateFullGame2()
            throws OutOfFieldException, WrongStatusException, FieldOccupiedException, IllegalInputException {

        //beginning player wins in first turn (big vertical)

        ArenaImpl arenaA = new ArenaImpl(PLAYER_A);
        ArenaImpl arenaB = new ArenaImpl(PLAYER_B);

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

        ///////////////////////////
        //set contact point
        arenaA.setRadioContact(arenaB);
        arenaB.setRadioContact(arenaA);
        ///////////////////////////

        arenaA.set(xS1, yS1, S, H);                          //     0  1  2  3  4  5  6  7  8  9
        arenaA.set(xS2, yS2, S, H);                          //  0 (S)[S][ ][ ](B)[B][B][B][B][ ]
        arenaA.set(xS3, yS3, S, H);                          //  1 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaA.set(xM1, yM1, M, H);                          //  2 (S)[S][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaA.set(xM2, yM2, M, H);                          //  3 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaA.set(xB1, yB1, B, H);                          //  4 (S)[S][ ][ ][ ][ ][ ][ ][ ][ ]
                                                             //  5 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xS1, yS1, S, H);                          //  6 (M)[M][M][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xS2, yS2, S, H);                          //  7 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xS3, yS3, S, H);                          //  8 (M)[M][M][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xM1, yM1, M, H);                          //  9 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
        arenaB.set(xM2, yM2, M, H);                          //
        arenaB.set(xB1, yB1, B, H);                          //

        /////////////////////
        arenaA.compareDice();
        arenaB.compareDice();
        /////////////////////


        if (arenaA.getStatus() == TURN) {

            arenaA.shoot(0,0);
            arenaA.shoot(1,0);

            arenaA.shoot(0,2);
            arenaA.shoot(1,2);

            arenaA.shoot(0,4);
            arenaA.shoot(1,4);

            arenaA.shoot(0,6);
            arenaA.shoot(1,6);
            arenaA.shoot(2,6);

            arenaA.shoot(0,8);
            arenaA.shoot(1,8);
            arenaA.shoot(2,8);

            arenaA.shoot(4,0);
            arenaA.shoot(5,0);
            arenaA.shoot(6,0);
            arenaA.shoot(7,0);
            arenaA.shoot(8,0);

            Assert.assertEquals(WON, arenaA.getStatus());
            Assert.assertEquals(LOST, arenaB.getStatus());

        }

        if (arenaB.getStatus() == TURN) {

            arenaB.shoot(0,0);
            arenaB.shoot(1,0);

            arenaB.shoot(0,2);
            arenaB.shoot(1,2);

            arenaB.shoot(0,4);
            arenaB.shoot(1,4);

            arenaB.shoot(0,6);
            arenaB.shoot(1,6);
            arenaB.shoot(2,6);

            arenaB.shoot(0,8);
            arenaB.shoot(1,8);
            arenaB.shoot(2,8);

            arenaB.shoot(4,0);
            arenaB.shoot(5,0);
            arenaB.shoot(6,0);
            arenaB.shoot(7,0);
            arenaB.shoot(8,0);

            Assert.assertEquals(WON, arenaB.getStatus());
            Assert.assertEquals(LOST, arenaA.getStatus());

        }

    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    @Test
    public void simulateFullGame3()
            throws OutOfFieldException, FieldOccupiedException, IllegalInputException, WrongStatusException {

        //playerA and playerB hit, kill and miss

        ArenaImpl arenaA = new ArenaImpl(PLAYER_A);
        ArenaImpl arenaB = new ArenaImpl(PLAYER_B);

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

        ///////////////////////////
        //need to know each other for test scenario
        //via network different
        arenaA.setRadioContact(arenaB);
        arenaB.setRadioContact(arenaA);
        ///////////////////////////

        arenaA.set(xS1, yS1, S, H);
        arenaA.set(xS2, yS2, S, H);
        arenaA.set(xS3, yS3, S, H);
        arenaA.set(xM1, yM1, M, H);
        arenaA.set(xM2, yM2, M, H);
        arenaA.set(xB1, yB1, B, V);

        arenaB.set(xS1, yS1, S, H);
        arenaB.set(xS2, yS2, S, H);
        arenaB.set(xS3, yS3, S, H);
        arenaB.set(xM1, yM1, M, H);
        arenaB.set(xM2, yM2, M, H);
        arenaB.set(xB1, yB1, B, V);

        /////////////////////
        arenaA.compareDice();
        arenaB.compareDice();
        /////////////////////

        if (arenaA.getStatus() == TURN) {

            arenaA.shoot(xS1, yS1);
            Assert.assertEquals(HIT, arenaA.getHitMap()[xS1][yS1]);

            arenaA.shoot(xS1 + 1, yS1);
            Assert.assertEquals(DEAD, arenaA.getHitMap()[yS1][yS1]);
            Assert.assertEquals(DEAD, arenaA.getHitMap()[xS1+1][yS1]);

            arenaA.shoot(8, 5);
            Assert.assertEquals(MISS, arenaA.getHitMap()[8][5]);

            try {
                arenaA.shoot(9, 9);
            } catch (WrongStatusException wse) {
                wse.printStackTrace();
            }

            arenaB.shoot(0, 0);
            arenaB.shoot(1, 0);
            Assert.assertEquals(DEAD, arenaB.getHitMap()[0][0]);
            Assert.assertEquals(DEAD, arenaB.getHitMap()[1][0]);

            try {
                arenaB.shoot(0, 0);
            } catch (IllegalInputException iee) {
                iee.printStackTrace();
            }

            arenaB.shoot(2, 0);
            Assert.assertEquals(MISS, arenaB.getHitMap()[2][0]);

            arenaA.shoot(4, 4);
            Assert.assertEquals(HIT, arenaA.getHitMap()[4][4]);

            Assert.assertEquals(WAIT, arenaB.getStatus());
            Assert.assertEquals(TURN, arenaA.getStatus());
        }

        if (arenaB.getStatus() == TURN) {

            arenaB.shoot(xS1, yS1);
            Assert.assertEquals(HIT, arenaB.getHitMap()[xS1][yS1]);

            arenaB.shoot(xS1 + 1, yS1);
            Assert.assertEquals(DEAD, arenaB.getHitMap()[yS1][yS1]);
            Assert.assertEquals(DEAD, arenaB.getHitMap()[xS1+1][yS1]);

            arenaB.shoot(8, 5);
            Assert.assertEquals(MISS, arenaB.getHitMap()[8][5]);

            try {
                arenaB.shoot(9, 9);
            } catch (WrongStatusException e) {
                e.printStackTrace();
            }

            arenaA.shoot(0, 0);
            arenaA.shoot(1, 0);
            Assert.assertEquals(DEAD, arenaA.getHitMap()[0][0]);
            Assert.assertEquals(DEAD, arenaA.getHitMap()[1][0]);

            try {
                arenaA.shoot(0, 0);
            } catch (IllegalInputException iee) {
                iee.printStackTrace();
            }

            arenaA.shoot(2, 0);
            Assert.assertEquals(MISS, arenaA.getHitMap()[2][0]);

            arenaB.shoot(4, 4);
            Assert.assertEquals(HIT, arenaB.getHitMap()[4][4]);

            Assert.assertEquals(WAIT, arenaA.getStatus());
            Assert.assertEquals(TURN, arenaB.getStatus());
        }

    }


}
