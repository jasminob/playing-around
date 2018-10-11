import java.lang.reflect.Array;
import java.util.*;

/*
Koristeći IntelliJ IDEA napraviti projekat pod nazivom "rpr-t1-z2".
Napravite program koji naekranu ispisuje sve brojeve između 1 i n koji su djeljivi sa sumom svojih cifara, pri čemu se ​nunosi sa tastature.
U programu se obavezno treba nalaziti funkcija ​sumaCifara​​.
Po završetkuzadatak treba postaviti na GitHub koristeći isključivo funkcionalnosti IntelliJ IDEA okruženja
 */

public class Application {

    public static void kreiranjeNovogPredmeta() {
        Predmet p = new Predmet("Bla", 544, 10);
    }

    public static void kreiranjeNovogStudenta() {
        Student s = new Student("Bla", "Blasic", 500);
    }

    public static void upisStudentaNaPredmet(Student s, Predmet p) {
        p.upisi(s);
    }

    public static void ispisStudentaSaPredmeta(Student s, Predmet p) {
        p.ispisi(s);
    }

    public static void brisanjeStudenta(Student s, Predmet p) {

        Student[] noviNiz = new Student[p.getStudents().length - 1];
        int index = -1;
        for (int i = 0; i < p.getStudents().length; i++) {
            if (p.getStudents()[i] == s) {
                index = i;
            }
        }
        int j = 0;
        for (int i = 0; i < p.getStudents().length; i++) {
            if (index != -1) {
                noviNiz[j] = p.getStudents()[i];
                j++;
            }
        }


    }

    public static void brisanjePredmeta() {
    }

    public static void spisakStudentaNaPredmetu() {

    }

    public static void main(String[] args) {


    }
}

class Student {
    private String firstName;
    private String lastName;
    private int indexNumber;

    public Student(String firstName, String lastName, int indexNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d)", lastName, firstName, indexNumber);
    }

}


class Predmet {
    private Student[] students;
    private String nazivPredmeta;
    private int sifraPredmeta;
    private int maxBrojStudenata;


    public Predmet(String nazivPredmeta, int sifraPredmeta, int maxBrojStudenata) {
        this.nazivPredmeta = nazivPredmeta;
        this.sifraPredmeta = sifraPredmeta;
        this.maxBrojStudenata = maxBrojStudenata;
    }

    public Student[] getStudents() {
        return students;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public int getSifraPredmeta() {
        return sifraPredmeta;
    }

    public void setSifraPredmeta(int sifraPredmeta) {
        this.sifraPredmeta = sifraPredmeta;
    }

    public int getMaxBrojStudenata() {
        return maxBrojStudenata;
    }


    public void upisi(Student student) {

        Student[] noviNiz = new Student[students.length + 1];
        if (students.length == maxBrojStudenata) {
            return;
        }

        for (int i = 0; i < students.length; i++) {
            noviNiz[i] = students[i];
        }

        noviNiz[students.length] = student;

        students = noviNiz;
    }


    public void ispisi(Student student) {
        Student[] noviNiz = new Student[students.length - 1];
        int index = -1;

        for (int i = 0; i < students.length; i++) {
            if (students[i] == student) {
                index = i;
            }
        }
        int j = 0;
        if (index == -1) {
            return;
        }
        for (int i = 0; i < students.length; i++) {
            if (index != i) {
                noviNiz[j] = students[i];
                j++;
            }
        }
        students = noviNiz;
    }


}


