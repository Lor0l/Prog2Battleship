package battleship;

import static battleship.FieldValues.*;

public class BoardOpponent {

    private final int ARRAY_LENGTH = 10;
    private FieldValues[][] hitMap;
    private ShipInterface[][] shipMap;

    public BoardOpponent() {

        this.hitMap = new FieldValues[ARRAY_LENGTH][ARRAY_LENGTH];
        for (int i = 0; i < hitMap.length; i++) {
            for (int j = 0; j < hitMap.length; j++) {
                this.hitMap[i][j] = WATER;
            }
        }
    }

    public FieldValues[][] getHitMap(){
        return this.hitMap;
    }

    public void setHitMiss(int xCoordinates, int yCoordinates, FieldValues fieldValue) {
        this.hitMap[xCoordinates][yCoordinates] = fieldValue;
    }

    public void setDead(int[] coordinatesDead){
        for(int i = 0; i < coordinatesDead.length; i+=2) {
            this.hitMap[coordinatesDead[i]][coordinatesDead[i+1]] = DEAD;
        }
    }
}
