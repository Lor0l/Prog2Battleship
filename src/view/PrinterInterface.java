package view;

import battleship.FieldValues;
import battleship.ShipInterface;

public interface PrinterInterface {


    /**
     *
     * @param fieldOpp Array2D with opponents FieldValues
     * @param fieldOwn Array2D with own Ship objects
     */
    void printField(FieldValues[][] fieldOpp, ShipInterface[][] fieldOwn);

}
