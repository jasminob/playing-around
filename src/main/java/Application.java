import java.util.Objects;


public class Application {


    public static void main(String[] args) {


    }
}

class Coordiantes {
    int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Ship {

    private Coordiantes pos;
    private int length;
    private int direction;

    public Ship(int length) {
        this.length = length;
    }

    public Coordiantes getPos() {
        return pos;
    }

    public void setPos(Coordiantes pos) {
        this.pos = pos;
    }
}

class Player {
    private int[] shipLength = {1, 2, 3};
    private int numberShips = 3;

    private Ship[] ship;

    public Player() {

        for (int i = 0; i < numberShips; i++) {

            Ship shipBucket = new Ship(shipLength[i]);
            ship[i] = shipBucket;
        }
    }

}

class Board {
    private int x = 10;
    private int y = 10;


    public void setShip(Ship ship) {

    }

}
