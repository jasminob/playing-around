import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Application {


    public static void main(String[] args) {


        Polje field = new Polje();
        Human player1 = new Human("Chiaki", 5, 2);
        Human player2 = new Human("Makoto", 5, 2);
        Human player3 = new Human("Iroha", 5, 2);

        Car auto = new Car();
        player1.getPos().setX(4);
        player1.getPos().setY(4);
        player1.getPos().setZ(3);

        player2.getPos().setX(4);
        player2.getPos().setY(4);
        player2.getPos().setZ(3);

        player3.getPos().setX(10);
        player3.getPos().setY(4);
        player3.getPos().setZ(3);

       try {



            field.zapocniIgru();
            // field.Driver(auto, player1);


            field.close(player1, player2, player3);
            field.step(player1, player2, player3);
          //  Vector3 t = new Vector3(1, 0, 0);
         //   player1.move(t);

        } catch (SamePos samePos) {
            System.out.println("Nesto nije dure. " + samePos.getMessage());
        }


    }
}

class SamePos extends Exception {
    public SamePos() {
        super("Dva ili vise igraca se nalaze na istoj poziciji");
    }
}


class Entity {
    private Vector3 pos = new Vector3();
    private Vector3 rot = new Vector3();

    public Vector3 getPos() {
        return pos;
    }

    public void setPos(Vector3 pos) {
        this.pos = pos;
    }

    public Vector3 getRot() {
        return rot;
    }

    public void setRot(Vector3 rot) {
        this.rot = rot;
    }
}

class Vector3 {
    private int x, y, z;

    public Vector3() {
        this.x = 4;
        this.y = 4;
        this.z = 4;
    }

    public Vector3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
}

class Sobject extends Entity {
}

abstract class Actor extends Entity {
    private String name;


    Actor() {
        this.name = "No Name";
    }

    Actor(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void move(Vector3 p);
}

class Human extends Actor {

    private int hp;
    private int dmg;

    Human() {
        super("No Name");
        this.hp = 5;
        this.dmg = 2;
    }

    public Human(String name, int hp, int dmg) {
        super(name);
        this.hp = hp;
        this.dmg = dmg;
    }

    @Override
    public void move(Vector3 p) {
        Vector3 newPos = new Vector3();
        newPos.setX(getPos().getX() + p.getX());
        newPos.setY(getPos().getY() + p.getY());
        newPos.setZ(getPos().getZ() + p.getZ());
        setPos(newPos);

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}

class Polje {

    private Human player1;
    private Human player2;
    private Human player3;
    private Car auto;

    public Human getPlayer1() {
        return player1;
    }

    public void setPlayer1(Human player1) {
        this.player1 = player1;
    }

    public Human getPlayer2() {
        return player2;
    }

    public void setPlayer2(Human player2) {
        this.player2 = player2;
    }

    public Human getPlayer3() {
        return player3;
    }

    public void setPlayer3(Human player3) {
        this.player3 = player3;
    }

    public Car getAuto() {
        return auto;
    }

    public void setAuto(Car auto) {
        this.auto = auto;
    }

    public void zapocniIgru() throws SamePos {

        if (player1.getPos().equals(player2.getPos()) ||
            player1.getPos().equals(player3.getPos()) ||
            player2.getPos().equals(player3.getPos())) {
            throw new SamePos();
        } else {
            System.out.println("Sve dure");

        }
    }

    public double distance(Human player1, Human player2) {
        return sqrt(pow(player1.getPos().getX() - player2.getPos().getX(), 2) +
                pow(player1.getPos().getY() - player2.getPos().getY(), 2) + pow(player1.getPos().getZ() - player2.getPos().getZ(), 2));
    }

    public void close(Human player1, Human player2, Human player3) {
        this.player3 = player3;
        this.player2 = player2;
        this.player1 = player1;


        Double d1, d2, d3;

        d1 = distance(player1, player2);
        d2 = distance(player1, player3);
        d3 = distance(player2, player3);

        if (d1 < d2 && d1 < d3) {
            System.out.println("Player 1 and 2 are the closest to each other");
        } else if (d2 < d3 && d2 < d1) {
            System.out.println("Player 1 and 3 are the closest to each other");
        } else {
            System.out.println("Player 2 and 3 are the closest to each other");
        }
    }

    public String Calc(Vector3 x, Vector3 y){
        return " X : " + (x.getX()-y.getX()) + " Y : " + (x.getY()-y.getY()) + " Z : " + (x.getZ()-y.getZ());
    }

    public void step(Human player1, Human player2, Human player3) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;

        System.out.println("Player 1 se nalazi na poziciji, X: " + player1.getPos().getX() + " Y: " + player1.getPos().getY() + " Z: " + player1.getPos().getZ()
                + ", a udaljen je od Player 2 za," + Calc(player1.getPos(), player2.getPos())
                + ", a  od Player 3 za,: " + Calc(player1.getPos(), player3.getPos()));

        System.out.println("Player 2 se nalazi na poziciji, X: " + player2.getPos().getX() + " Y: " + player2.getPos().getY() + " Z: " + player2.getPos().getZ()
                + ", a udaljen je od Player 1 za" + Calc(player2.getPos(), player1.getPos())
                + ", a od Player 3 za," + Calc(player2.getPos(), player3.getPos()));

        System.out.println("Player 3 se nalazi na poziciji, X: " + player3.getPos().getX() + " Y: " + player3.getPos().getY() + " Z: " + player3.getPos().getZ()
                + ", a udaljen je od Player 1 za" + Calc(player3.getPos(), player1.getPos())
                + ", a od Player 2 za," + Calc(player3.getPos(), player2.getPos()));
    }

}

//Main fun , inicijalizira tri coeka sa banalnim imeno, i klasu Polje, ta klasa polje ce u sebi imati
/*
sastojat ce se od tri covjeka, bit ce NULL, i imat ce get and set, igrac 1 igrac 2, igrac 3 ce se zvati
imat ce metodu ZAPOCNIIGRU provjeri da li su na razlicitim pozicijama, ako su na istoj onda baca exception
uradi step metoda, gdje ce naci najbliza dva igraca i onaj igrac koji
 */
/*
ispisi polje metoda gdje se nalaze igraci i koliko su udaljeni
igrac 1 se nalazi na X Y Z, i udaljen je od igraca 2 bla bla bla , igraca 3 bla bla
igrac 2 bla bla
igrac 3 bla bla
 */
/*
kod Human ce biti dmg i hp, get and set, u main inicijalizirati
 */
/*
konstruktor za human ime, hp i dmg
constructor za actor ime, ako nema ime onda je No Name
car ima atribut human i govori ko je u njemu trenutno, getter and setter
 */
/*
u klasu Polje dodaj atribut JeAuto, getter setter
 */
/*
kod ispisa u Polju u autu se nalazi ili niko se ne nalazi u autu ispisati


disance(vector v1 i v2){}
distance(human h1, h2){}
refaktorisati imena fun lowerCamel
Polje = setplayer

step(izbaci argumente)
{
System.pou.printly(String format())
}

Auto,  sa metodom bool jeAuto(Vector3){DA LI SE NALAZI TU AUTO}



kretanje auta,

U actor dodati referencu na polje, i pri po cetku igre ko kad svog actora ce se pozvati setpolje
auto ce se pomjerati tkao sto isto kao human, ali ce paziti da li je udario nekoga, i ispisati na ekran ako udari

 */

class Car extends Actor {

    private Human human;

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public void move(Vector3 p) {

    }
}