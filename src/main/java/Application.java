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
        upisStudentaNaPredmet(kreiranjeNovogStudenta(), x);
        upisStudentaNaPredmet(kreiranjeNovogStudenta(), x);
        upisStudentaNaPredmet(kreiranjeNovogStudenta(), x);
        upisStudentaNaPredmet(w, x);


        System.out.println(spisakStudentaNaPredmetu(x));

        ispisStudentaSaPredmeta(w, x);
        System.out.println(spisakStudentaNaPredmetu(x));

        Odsijek test = new Odsijek("Pikachu");


        System.out.println(test.getMaxBrojStudenataZaGodinu(1));
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
    private int godina;



    public Predmet(Student[] students, String nazivPredmeta, int sifraPredmeta, int maxBrojStudenata, int godina) {
        this.students = students;
        this.nazivPredmeta = nazivPredmeta;
        this.sifraPredmeta = sifraPredmeta;
        this.maxBrojStudenata = maxBrojStudenata;
        this.godina = godina;
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

    public int getGodina() {
        return godina;
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

    @Override
    public String toString() {
        String buffer = new String();

        for (int i = 0; i < students.length; i++) {
            buffer += (i + 1) + ". " + students[i].toString() + "\n";
        }
        return buffer;
    }
}

class Odsijek{
    private String nazivOdsijeka;
    private Predmet[] predmets = new Predmet[0];


    public Odsijek(String nazivOdsijeka) {
        this.nazivOdsijeka = nazivOdsijeka;

    }

    public String getNazivOdsijeka() {
        return nazivOdsijeka;
    }



    public int getMaxBrojStudenataZaGodinu(int godina){

        int result = -1;

        for(int i = 0; i < predmets.length; i++){

           if (godina == predmets[i].getGodina()) {
               int min = predmets[i].getMaxBrojStudenata();
               if (result == -1) {
                   result = min;
                }
               if (result > min) {
                   result = min;
               }
           }
        }
        return result;
    }

}

class Fakultet{

    String nazivFakulteta;
    Student[] students = new Student[0];
    Odsijek[] odsijeks = new Odsijek[0];

    public Fakultet(String nazivFakulteta) {
        this.nazivFakulteta = nazivFakulteta;
    }

    public String getNazivFakulteta() {
        return nazivFakulteta;
    }

    public void registrujOdsijek(Odsijek o){
        boolean check = false;
        Odsijek[] noviNiz = new Odsijek[odsijeks.length+1];

        for(int i = 0; i < odsijeks.length; i++){
            if(odsijeks[i] == o){
                check = true;
            }
        }

        if(!check){
            noviNiz[odsijeks.length] = o;
            odsijeks = noviNiz;
        }


    }

    public void upisiStudent(Student s, String nazivOdsijeka){

        int j = 0;

        for(int i = 0; i < odsijeks.length; i++){
            if(odsijeks[i].getNazivOdsijeka().equals(nazivOdsijeka)){
                students[j] = s;
                j++;
            }
        }
    }


    @Override
    public String toString() {

        String buffer = new String();

        for (int i = 0; i < students.length; i++) {
            buffer += (i + 1) + ". " + students[i].toString() + "je student " + [INSERT_YEAR] + " godine na odsijeku "
                    + odsijeks[i].getNazivOdsijeka() + "\n";
        }
        return buffer;


    }
}



