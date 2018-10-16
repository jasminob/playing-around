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

import io.vertx.core.streams.WriteStream;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Scanner;

public class Application {

    private static Fakultet[] fakultet = new Fakultet[0];

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


    private static void uiIspisFakulteta(PrintStream out){
        out.println(ispisFakulteta());
    }
    private static String ispisFakulteta() {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            buffer += (i + 1) + ". " + fakultet[i].getNazivFakulteta() + "\n";
        }
        return buffer;
    }

    private static void addFakultet(Fakultet f) {
        Fakultet[] newFak = new Fakultet[fakultet.length + 1];


        for (int i = 0; i < fakultet.length; i++) {
            newFak[i] = fakultet[i];
        }
        newFak[fakultet.length] = f;
        fakultet = newFak;

    }

    private static void deleteFakultet(Fakultet f) {
        Fakultet[] newFak = new Fakultet[fakultet.length - 1];
        int index = -1;


        for (int i = 0; i < fakultet.length; i++) {
            if (fakultet[i].equals(f)) {
                index = i;
            }
        }
        int counter = 0;
        for(int i = 0; i < fakultet.length; i++){
            if(index != i){
                newFak[counter] = fakultet[i];
                counter++;
            }
        }
        fakultet = newFak;
    }



    public static String spisakStudentaNaPredmetu(Predmet predmet) {
  return "";
    }

    private static String ispisStudenata(String nazivFakulteta, String nazivOdsijeka) {
        return "";
    }


    private static void uiDeleteFakultet(PrintStream out, Scanner in){
        uiIspisFakulteta(out);
        String name = in.nextLine();
        deleteFakultet(new Fakultet(name));
    }

    private static void uiAddFakultet(PrintStream out, Scanner in) {
        out.println("Unesite trazene podatke za unos fakulteta");
        out.println("Naziv? ");
        String name = in.nextLine();
        addFakultet(new Fakultet(name));
    }


    private static void uiIspisOdsijeka(PrintStream out, Scanner in){
        uiIspisFakulteta(out);
        out.println("Unesite trazene podatke za ispis Odsijeka");
        out.println("Naziv fakuteta? ");
        String nameFakultet = in.nextLine();
        out.println(ispisOdsijeka(nameFakultet));


    }

    private static String ispisOdsijeka(String nazivFakulteta) {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            if (fakultet[i].getNazivFakulteta().equals(nazivFakulteta)) {
                for (int j = 0; j < fakultet[i].odsijeks.length; j++) {

                    buffer += (i + 1) + "- " + fakultet[i].odsijeks[j].getNazivOdsijeka() + "\n";
                }
            }

        }
        return buffer;
    }

    private static void uiAddOdsijek(PrintStream out, Scanner in){

        uiIspisFakulteta(out);
        out.println("Odaberite fakultet");
        String fakultetName = in.nextLine();
        in.nextLine();
        out.println("Unesite trazene podatke za unos Odsijeka");
        out.println("Naziv?");
        String name = in.nextLine();
        addOdsijek(fakultetName, new Odsijek(name));

    }
    private static void addOdsijek(String nazivFakulteta, Odsijek odsijek) {


        for (int i = 0; i < fakultet.length; i++) {
            if (fakultet[i].getNazivFakulteta().equals(nazivFakulteta)) {
                Odsijek[] newOd = new Odsijek[fakultet[i].odsijeks.length + 1];
                for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                    newOd[j] = fakultet[i].odsijeks[j];
                }
                newOd[fakultet[i].odsijeks.length] = odsijek;
                fakultet[i].odsijeks = newOd;
            }
        }
    }



    private static void uiAddStudent(PrintStream out, Scanner in){
        uiIspisFakulteta(out);
        String nameFakultet = in.nextLine();
        out.println("Unesite trazene podatke za unos Studenta");
        out.println("Ime?");
        String name = in.nextLine();
        out.println("Prezime?");
        String lastName = in.nextLine();
        out.println("Index?");
        int index = in.nextInt();
        addStudent(nameFakultet, new Student(name, lastName, index));

    }

    private static void addStudent(String nameFakultet, Student student){

        for(int i = 0; i < fakultet.length; i++){
            if(fakultet[i].equals(nameFakultet)){

                Student[] newStud = new Student[fakultet[i].students.length + 1];
                for (int j = 0; j < fakultet[i].students.length; j++) {
                    newStud[j] = fakultet[i].students[j];
                }
                newStud[fakultet[i].students.length] = student;
                fakultet[i].students = newStud;
            }
        }
    }



    private static void uiAddPredmet(PrintStream out, Scanner in){
        uiIspisFakulteta(out);
        String nameFakultet = in.nextLine();
        out.println("Unesite trazene podatke za unos Predmeta");
        out.println("Naziv?");
        String name = in.nextLine();
        out.println("Sifra?");
        int sifra = in.nextInt();
        out.println("Max Broj Studenata?");
        int maxBrojStudenata = in.nextInt();
        out.println("Godina?");
        int godina = in.nextInt();

        addPredmet();
    }

    private static void addPredmet(){


    }

    private static void ispisStudenataNaOdsijeku(Odsijek odsijek){


    }


    private static void uiIspisStudenataNaOdsijeku(PrintStream out, Scanner in){
        uiIspisOdsijeka(out, in);
        out.println("Izaberite Odsijek");
        String nameOdsijek = in.nextLine();
    }





    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;



        Scanner g = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "1. Kreiranje novog Fakulteta\n" +
                            "2. Kreiranje novog odsijeka\n" +
                            "3. Kreiranje novog studenta\n" +
                            "4. Kreiranje novog predmeta\n" +
                            "5. Upis studenta na predmet\n" +
                            "6. Brisanje studenta\n" +
                            "7. Brisanje odsijeka\n" +
                            "8. Brisanje predmeta\n" +
                            "9. Brisanje fakulteta\n" +
                            "10. Ispis svih fakulteta\n" +
                            "11. Ispis detalja jednog fakulteta\n" +
                            "12. Ispis svih studenata na odsijeku\n" +
                            "13. Ispis svih predmeta na odsijeku\n" +
                            "14. Ispis svih studenata na predmetu\n" +
                            "15. Izlaz\n");

            int choice = g.nextInt();
            g.nextLine();
            switch (choice) {
                case 1:
                   uiAddFakultet(out, in);
                    break;
                case 2:
                    uiAddOdsijek(out, in);
                    break;
                case 3:
                    uiAddStudent(out, in);
                    break;
                case 4:
                    uiAddPredmet(out, in);
                    break;
                case 5:
                    break;
                case 6:

                    break;
                case 7:
                    break;
                case 8:

                    break;
                case 9:
                    uiDeleteFakultet(out, in);
                    break;
                case 10:
                    uiIspisFakulteta(out);
                    break;
                case 11:
                    uiIspisOdsijeka(out, in);
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    return;

            }

        }


    }
}














