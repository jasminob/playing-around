import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Application {


    public static void main(String[] args) {


        Polje field = new Polje();
        Human player1 = new Human("Chiaki", 5, 2);
        Human player2 = new Human("Makoto", 5, 2);
        Human player3 = new Human("Iroha", 5, 2);

        //Movement
        Vector3 x = new Vector3(1, 0, 0);
        Vector3 y = new Vector3(1, 0, 0);
        Vector3 z = new Vector3(0, 0, 1);

        Car auto = new Car();

        field.setPlayer1(player1);
        field.setPlayer2(player2);
        field.setPlayer3(player3);
        field.setAuto(auto);
/*
        player1.setPolje(field);
        player2.setPolje(field);
        player3.setPolje(field);
        auto.setPolje(field);
*/

        Vector3 c = new Vector3(1, 0, 0);


        player1.move(x);
        player2.move(y);
        player2.move(y);
        player2.move(y);

        player3.move(z);

        auto.move(c);
        field.kill();

        field.carHit();
        field.step();


        try {

            field.zapocniIgru();

            field.close();
            field.step();


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3 vector3 = (Vector3) o;
        return x == vector3.x &&
                y == vector3.y &&
                z == vector3.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
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
    private Polje polje = new Polje();

    public Polje getPolje() {
        return polje;

    }

    public void setPolje(Polje polje) {
        this.polje = polje;
    }

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
    private Polje polje;


    @Override
    public Polje getPolje() {
        return polje;
    }

    @Override
    public void setPolje(Polje polje) {
        this.polje = polje;
    }

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

        Vector3 oldPos = new Vector3();
        oldPos.setX(getPos().getX());
        oldPos.setY(getPos().getY());
        oldPos.setZ(getPos().getZ());

        newPos.setX(getPos().getX() + p.getX());
        newPos.setY(getPos().getY() + p.getY());
        newPos.setZ(getPos().getZ() + p.getZ());


        if (polje.isPlayerAtPosition(newPos)) {
            setPos(oldPos);
            polje.playerHit();
            polje.kill();

        } else {
            setPos(newPos);
        }


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

    public boolean isAlive() {
        if (polje.getPlayer1() != null && polje.getPlayer1().getHp() < 1) {
            return false;
        } else if (polje.getPlayer2() != null && polje.getPlayer2().getHp() < 1) {
            return false;
        } else if (polje.getPlayer3() != null && polje.getPlayer3().getHp() < 1) {
            return false;
        } else {
            return true;
        }
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
        this.player1.setPolje(this);
    }

    public Human getPlayer2() {
        return player2;

    }

    public void setPlayer2(Human player2) {
        this.player2 = player2;
        this.player2.setPolje(this);
    }

    public Human getPlayer3() {
        return player3;
    }

    public void setPlayer3(Human player3) {
        this.player3 = player3;
        this.player3.setPolje(this);
    }

    public Car getAuto() {
        return auto;
    }

    public void setAuto(Car auto) {
        this.auto = auto;
        this.auto.setPolje(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polje polje = (Polje) o;
        return Objects.equals(player1, polje.player1) &&
                Objects.equals(player2, polje.player2) &&
                Objects.equals(player3, polje.player3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, player3);
    }


    public void zapocniIgru() throws SamePos {

        if (player1 == null || player2 == null || player3 == null) {

        } else {
            if (player1.getPos().equals(player2.getPos()) ||
                    player1.getPos().equals(player3.getPos()) ||
                    player2.getPos().equals(player3.getPos())) {
                throw new SamePos();
            } else {
                System.out.println("Sve dure");
            }
        }
    }


    public double distance(Vector3 v1, Vector3 v2) {
        return sqrt(pow(v1.getX() - v2.getX(), 2)
                + pow(v1.getY() - v2.getY(), 2)
                + pow(v1.getZ() - v2.getZ(), 2));
    }

    public double distance(Human h1, Human h2) {
        return distance(h1.getPos(), h2.getPos());
    }

    public void close() {

        Double d1, d2, d3;
        if (player1 == null) {
            System.out.println("Player 2 and 3 are the closest to each other");
        } else if (player2 == null) {
            System.out.println("Player 1 and 3 are the closest to each other");
        } else if (player3 == null) {
            System.out.println("Player 1 and 2 are the closest to each other");
        } else {
            d1 = distance(player1, player2);
            d2 = distance(player1, player3);
            d3 = distance(player2, player3);

            if (d1 < d2 && d1 < d3) {
                System.out.println("Player 1 and 2 are the closest to each other");
            } else if (d2 < d3 && d2 < d1) {
                System.out.println("Player 1 and 3 are the closest to each other");
            } else if (d1.equals(d2) || d2.equals(d3) || d3.equals(d1)) {
                System.out.println("Same distance");
            } else if (d3 < d2 && d3 < d1) {
                System.out.println("Player 2 and 3 are the closest to each other");
            }
        }
    }

    public String calc(Vector3 x, Vector3 y) {
        return " X : " + (x.getX() - y.getX()) + " Y : " + (x.getY() - y.getY()) + " Z : " + (x.getZ() - y.getZ());
    }

    public String sablonAuto(Actor x) {
        return String.format("%s se nalazi na poziciji : %d, Y : %d, Z : %d", x.getName(), x.getPos().getX(), x.getPos().getY(), x.getPos().getZ());
    }

    public String sablon(Human hum, Human hum1) {
        return String.format("%s se nalazi na poziciji X : %d, Y : %d, Z : %d, a udaljen je od igraca %s za,%s", hum.getName(), hum.getPos().getX(), hum.getPos().getY(), hum.getPos().getZ(), hum1.getName(), calc(hum.getPos(), hum1.getPos()));
    }


    public void step() {

        if (player1 == null) {

            System.out.println(sablon(player2, player3));

            System.out.println(sablon(player3, player2));

            System.out.println(sablonAuto(auto));


        } else if (player2 == null) {

            System.out.println(sablon(player1, player3));

            System.out.println(sablon(player3, player1));

            System.out.println(sablonAuto(auto));

        } else if (player3 == null) {

            System.out.println(sablon(player1, player2));

            System.out.println(sablon(player2, player1));

            System.out.println(sablonAuto(auto));

        } else {

            System.out.println(String.format("Player 1 se nalazi na poziciji X : %d, Y : %d, Z : %d, a udaljen je od Player 2 za,%s, a od Player 3 za,%s", player1.getPos().getX(), player1.getPos().getY(), player1.getPos().getZ(),
                    calc(player1.getPos(), player2.getPos()), calc(player1.getPos(), player3.getPos())));

            System.out.println(String.format("Player 2 se nalazi na poziciji X : %d, Y : %d, Z : %d, a udaljen je od Player 1 za,%s, a od Player 3 za,%s", player2.getPos().getX(), player2.getPos().getY(), player2.getPos().getZ(),
                    calc(player2.getPos(), player1.getPos()), calc(player2.getPos(), player3.getPos())));

            System.out.println(String.format("Player 3 se nalazi na poziciji X : %d, Y : %d, Z : %d, a udaljen je od Player 1 za,%s, a od Player 3 za,%s", player3.getPos().getX(), player3.getPos().getY(), player3.getPos().getZ(),
                    calc(player3.getPos(), player1.getPos()), calc(player3.getPos(), player1.getPos())));

            System.out.println(sablonAuto(auto));


        }
    }

    public boolean jeAuto(Vector3 loc) {
        //Auto,  sa metodom bool jeAuto(Vector3){DA LI SE NALAZI TU AUTO}
        if (loc.equals(auto.getPos())) {
            return true;
        } else {
            return false;
        }
    }

    public void carHit() {

        if (player1 != null && jeAuto(getPlayer1().getPos())) {
            System.out.println(String.format("%s was run over", getPlayer1().getName()));
            player1.setHp(0);
            kill();
        } else if (player3 != null && jeAuto(getPlayer2().getPos())) {
            System.out.println(String.format("%s was run over", getPlayer2().getName()));
            player2.setHp(0);
            kill();
        } else if (player3 != null && jeAuto(getPlayer3().getPos())) {
            System.out.println(String.format("%s was run over", getPlayer3().getName()));
            player3.setHp(0);
            kill();
        }

    }


    public void playerHit() {

        if (player1 != null && isPlayerAtPosition(player1.getPos())) {
            dmg(player1);
            player1.isAlive();
        } else if (player2 != null && isPlayerAtPosition(player2.getPos())) {
            dmg(player2);
            player2.isAlive();
        } else if (player3 != null && isPlayerAtPosition(player3.getPos())) {
            dmg(player3);
            player3.isAlive();
        }
    }


    public void kill() {
        if (player1 != null && !player1.isAlive()) {

            System.out.println(String.format("%s was killed", player1.getName()));
            player1 = null;

        } else if (player2 != null && !player2.isAlive()) {

            System.out.println(String.format("%s was killed", player2.getName()));
            player2 = null;

        } else if (player3 != null && !player3.isAlive()) {
            System.out.println(String.format("%s was killed", player3.getName()));
            player3 = null;

        }
    }


    //pretpostavljam da sam trebao staviti player.setHp(player.getHp - someotherplayer.getDmg());
    public void dmg(Human player) {
        player.setHp(player.getHp() - 2);
        System.out.println(String.format("%s took %d damage, and now has %dhp", player.getName(), 2, player.getHp()));
    }


    public boolean isPlayerAtPosition(Vector3 v) {


        if (player1 != null && v.equals(player1.getPos())) {

            return true;
        } else if (player2 != null && v.equals(player2.getPos())) {

            return true;
        } else if (player3 != null && v.equals(player3.getPos())) {

            return true;
        } else {
            return false;
        }

    }


    public Actor actorAtPosition(Vector3 v) {


        if (player1 != null && v.equals(player1.getPos())) {

            return player1;
        } else if (player2 != null && v.equals(player2.getPos())) {


            return player2;
        } else if (player3 != null && v.equals(player3.getPos())) {


            return player3;
        } else if (v.equals(auto.getPos())) {
            return auto;
        } else {
            return null;
        }

    }


}

/*Main fun , inicijalizira tri coeka sa banalnim imeno, i klasu Polje, ta klasa polje ce u sebi imati

sastojat ce se od tri covjeka, bit ce NULL, i imat ce get and set, igrac 1 igrac 2, igrac 3 ce se zvati
imat ce metodu ZAPOCNIIGRU provjeri da li su na razlicitim pozicijama, ako su na istoj onda baca exception
uradi step metoda, gdje ce naci najbliza dva igraca i onaj igrac koji

ispisi polje metoda gdje se nalaze igraci i koliko su udaljeni
igrac 1 se nalazi na X Y Z, i udaljen je od igraca 2 bla bla bla , igraca 3 bla bla
igrac 2 bla bla
igrac 3 bla bla

kod Human ce biti dmg i hp, get and set, u main inicijalizirati

konstruktor za human ime, hp i dmg
constructor za actor ime, ako nema ime onda je No Name
car ima atribut human i govori ko je u njemu trenutno, getter and setter

u klasu Polje dodaj atribut JeAuto, getter setter
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
    private Polje polje;


    public Polje getPolje() {
        return polje;
    }


    public void setPolje(Polje polje) {
        this.polje = polje;
    }

    public Car() {
        super("Auto");
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }


    /*   @Override
       public void move(Vector3 p) {
           Vector3 newPos = new Vector3();
           newPos.setX(getPos().getX() + p.getX());
           newPos.setY(getPos().getY() + p.getY());
           newPos.setZ(getPos().getZ() + p.getZ());
           setPos(newPos);
           getPolje().carHit();

       }
   */
    @Override
    public void move(Vector3 p) {
        Vector3 newPos = getPos();
        Vector3 oldPos = new Vector3();

//Ovo sam ja za testing sa negativnim X
        int incrementorX = p.getX() < 0 ? -1 : 1;
        for (int i = 0; i != p.getX(); i += incrementorX) {

            oldPos.setX(newPos.getX() + incrementorX);
            System.out.println(polje.actorAtPosition(oldPos));
            newPos.setX(getPos().getX() + incrementorX);


        }
        int incrementorY = p.getY() < 0 ? -1 : 1;
        for (int i = 0; i != p.getY(); i += incrementorY) {

            oldPos.setY(newPos.getY() + incrementorY);
            System.out.println(polje.actorAtPosition(oldPos));
            newPos.setY(getPos().getY() + incrementorY);
        }
        int incrementorZ = p.getZ() < 0 ? -1 : 1;
        for (int i = 0; i != p.getZ(); i += incrementorZ) {

            oldPos.setZ(newPos.getZ() + incrementorZ);
            System.out.println(polje.actorAtPosition(oldPos));
            newPos.setZ(getPos().getZ() + incrementorZ);
        }
        setPos(newPos);

    }

}

//Zavrsiti to CHECK
//iznad svake petlje za xyz negativne brojeve i+=inc na plus ili minus 1
//kad se player pomjera da li je mjesto zauzeto, ako je tu human nece se omjeriti vec cce napraviti dmg
//
//ako je unutar humana da vidi koliko human hp, ako je ispod 1 onda
//ce polje unutar funkcije on ce staviti playera na null