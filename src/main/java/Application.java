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

import java.io.OutputStream;
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


    private static void uiOdsijek(OutputStream out, Scanner in) {

    }

    private static void uiFakultet(OutputStream out, Scanner in) {

    }

    private static String ispisFakulteta() {
        //  return fakultet.toString();
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

        int counter = 0;
        for (int i = 0; i < fakultet.length; i++) {
            if (!fakultet[i].equals(f)) {
                newFak[counter] = fakultet[i];
                counter++;
            }
        }
        fakultet = newFak;
    }

    public static String spisakStudentaNaPredmetu(Predmet predmet) {
        return predmet.toString();
    }

    private static String ispisStudenata(String nazivFakulteta, String nazivOdsijeka) {
        return "";
    }

    private static String ispisOdsijeka(String nazivFakulteta) {

        /*Odsijek[] result = new Odsijek[5];
        for(int i = 0; i < fakultet.length; i++){
            if(fakultet[i].getNazivFakulteta().equals(nazivFakulteta)){
                for(int j = 0; j<fakultet[i].odsijeks.length; j++){
                    fakultet[i].odsijeks[j].getNazivOdsijeka();
                }
            }
            result = fakultet[i].odsijeks;
        }
        return result.toString();
        */


        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            if (fakultet[i].getNazivFakulteta().equals(nazivFakulteta)) {
                for (int j = 0; j < fakultet[i].odsijeks.length; j++) {

                    buffer += (i + 1) + ". " + fakultet[i].odsijeks[j].getNazivOdsijeka() + "\n";
                }
            }

        }
        return buffer;

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

    public static void main(String[] args) {


        Student student = new Student("Test", "What", 5);
      /*
        fakultet.upisiStudent(x, "IT");
        fakultet.registrujOdsijek(odsijek);
        fakultet.upisiStudent(student, "IT");
       */
        Student x = StudentFactory.createStudent("Neko", "Nesto", 555);

        Fakultet fakultet = new Fakultet("NL");
        Fakultet fakultet1 = new Fakultet("RT");
        Odsijek odsijek = OdsijekFactory.createOdsijek("IT");

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
            switch (choice) {
                case 1:
                    addFakultet(fakultet);
                    addFakultet(fakultet1);
                    break;
                case 2:
                    System.out.println(ispisFakulteta());
                    addOdsijek("NL", odsijek);
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    //    brisanjeStudenta();
                    break;
                case 7:
                    break;
                case 8:
                    //   brisanjePredmeta();
                    break;
                case 9:
                    deleteFakultet(fakultet1);
                    break;
                case 10:
                    System.out.println(ispisFakulteta());
                    break;
                case 11:
                    System.out.println(ispisOdsijeka("NL"));
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














