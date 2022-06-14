package view;

import battleship.FieldValues;
import battleship.ShipInterface;

import java.lang.reflect.Field;

public class BattlefieldPrinter implements PrinterInterface{

   private final int ARRAY_MIN = 0;
   private final int ARRAY_MAX = 9;

   @Override
    public void printField(FieldValues[][] fieldOpp, ShipInterface[][] fieldOwn) {
        System.out.println("opp:");
        for(int i = ARRAY_MIN; i <= ARRAY_MAX; i++){
            for(int j = ARRAY_MIN; j <= ARRAY_MAX; j++){
                FieldValues value = fieldOpp[j][i];
                switch (value) {
                    case WATER:
                        System.out.print(" ~ ");
                        break;
                    case HIT:
                        System.out.print(" H ");
                        break;
                    case MISS:
                        System.out.print(" x ");
                        break;
                    case DEAD:
                        System.out.print(" D ");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("own");
        for(int i = ARRAY_MIN; i <= ARRAY_MAX; i++){
            for(int j = ARRAY_MIN; j <= ARRAY_MAX; j++){
                ShipInterface ship = fieldOwn[j][i];
                if (ship == null) {
                    System.out.print(" ~ ");
                } else if (ship.isDead()) {
                    System.out.print(" D ");
                } else if (ship.isHit(i, j)) {
                    System.out.print(" H ");
                } else {
                    System.out.print(" * ");
                }

            }
            System.out.println();
        }
    }
}
