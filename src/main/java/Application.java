/*
Prosiriti program ispod sa sljedecim:

U klasu Fakultet dodati metode:
  - void registrujOdsijek(Odsijek o) - registruje dati odsijek u fakultet, s tim da prvo provjeri da li ima odsijek sa
    tim imenom. Ako ima, ne radi nista
  - void upisiStudent(Student s, String nazivOdsijeka) - upisuje datog studenta u odsijek sa datim imenom.
    Kada se student upise u neki odsijek, on se upisuje u sve predmete sa prve godine tog odsijeka

Treba jos podrzati ispis objekta Fakultet preko System.out.println(fakultet); tako da se ispisu podaci o svim studentima
u sljedecem formatu:

1. Ime Prezime (indeks) je student N. godine na odsijeku NAZIV_ODSIJEKA
2. ....
3. ....

 */

import java.util.Arrays;
import java.util.OptionalDouble;

public class Application {

    public static Predmet kreiranjeNovogPredmeta() {

        return new Predmet(new Student[]{}, "Whatever", 5, 3, 1);

    }

    public static Student kreiranjeNovogStudenta() {
        return new Student("Jasmin", "Nesto", 54);
    }

    public static void upisStudentaNaPredmet(Student s, Predmet p) {
        p.upisi(s);
    }

    public static void ispisStudentaSaPredmeta(Student s, Predmet p) {
        p.ispisi(s);
    }

    public static void brisanjeStudenta(Student[] studentArr, Student student) {

    }

    public static Predmet[] brisanjePredmeta(Predmet[] predmetArr, Predmet predmet) {

        Predmet[] noviNiz = new Predmet[predmetArr.length];
        int index = -1;
        for (int i = 0; i < predmetArr.length; i++) {
            if (predmetArr[i] == predmet) {
                index = i;
            }
        }
        int j = 0;
        for (int i = 0; i < predmetArr.length; i++) {
            if (index != -1) {
                noviNiz[j] = predmetArr[i];
                j++;
            }
        }
        predmetArr = noviNiz;
        return predmetArr;
    }

    public static String spisakStudentaNaPredmetu(Predmet predmet) {
        return predmet.toString();
    }

    public static void main(String[] args) {

        Predmet x = new Predmet(new Student[]{}, "test", 5, 5, 1);
        Student w = new Student("Neko", "Neko", 555);
        Fakultet f = new Fakultet("ETF");

        OdsijekFactory.createOdsijek("IT").upisiStudent(w);
        x.upisi(StudentFactory.createStudent("La", "La", 2));

        System.out.println(spisakStudentaNaPredmetu(x));

        Odsijek test = new Odsijek("Pikachu");


        System.out.println(test.getMaxBrojStudenataZaGodinu(1));
    }
}














