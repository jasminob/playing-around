import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UiUtility {

    public static void uiIspisFakulteta(PrintStream out) {
        out.println(Application.ispisFakulteta());
    }

    public static void uiDeleteFakultet(PrintStream out, Scanner in) {
        uiIspisFakulteta(out);
        Fakultet name = Application.izabraniFakultet(out, in);
        if (name == null) {
            out.println("Nema trazenog odsijeka");
            return;
        }
        Application.deleteFakultet(name);
    }

    public static void uiAddFakultet(PrintStream out, Scanner in) {
        out.println("Unesite trazene podatke za unos fakulteta");
        out.println("Naziv? ");
        String name = in.nextLine();
        Application.inputStringCheck(name, out, in);
        Application.addFakultet(new Fakultet(name));
    }

    public static void uiIspisOdsijeka(PrintStream out, Scanner in) {

        out.println("Unesite trazene podatke za ispis Odsijeka");
        out.println("Naziv fakulteta? ");
        Fakultet izabraniFakultet = Application.izabraniFakultet(out, in);
        if (izabraniFakultet == null) {
            out.println("Izabrani fakultet ne postoji");
            return;
        }

        out.println(Application.ispisOdsijeka(izabraniFakultet));

    }

    public static void uiAddOdsijek(PrintStream out, Scanner in) {

        uiIspisFakulteta(out);
        if (Application.isFakultetEmpty()) {
            return;
        }
        out.println("Odaberite fakultet");
        String fakultetName = in.nextLine();
        out.println("Unesite trazene podatke za unos Odsijeka");
        out.println("Naziv?");
        String name = in.nextLine();
        Application.inputStringCheck(name, out, in);
        Application.addOdsijek(fakultetName, new Odsijek(name));
    }

    public static void uiIspisPredmetaNaOdsijeku(PrintStream out, Scanner in) {

        Odsijek odsijek = Application.izabraniOdsijek(out, in);
        if (odsijek == null) {
            out.println("Nema trazenog odsijeka");
            return;
        }
        out.println(Application.ispisPredmetaNaOdsijeku(odsijek));
    }

    public static void uiDeleteOdsijek(PrintStream out, Scanner in) {

        Odsijek deleteOdsijek = Application.izabraniOdsijek(out, in);
        Application.deleteOdsijek(deleteOdsijek);

    }

    public static void uiAddStudent(PrintStream out, Scanner in) {

        boolean checker = true;

        uiIspisFakulteta(out);
        out.println("Odaberite fakultet ");
        String nameFakultet = in.nextLine();

        do {
            try {
                out.println("Unesite trazene podatke za unos Studenta");
                out.println("Ime?");
                String name = in.nextLine();
                out.println("Prezime?");
                String lastName = in.nextLine();
                out.println("Index?");
                int index = in.nextInt();
                in.nextLine();
                out.println("Ciklus?");
                int ciklus = in.nextInt();
                in.nextLine();
                Application.addStudent(nameFakultet, Student.studentFactory(name, lastName, index, ciklus));
                break;
            } catch (InputMismatchException e) {
                in.skip(".*");
                System.out.println("Student nije kreiran, unesite validne podatke \n");
                in.nextLine();
            }
        } while (true);

    }

    public static void uiAddPredmet(PrintStream out, Scanner in) {

        Odsijek odsijek = Application.izabraniOdsijek(out, in);
        if (odsijek == null) {
            out.println("Nema trazenog odsijeka");
            return;
        }
        do {
            try {
                out.println("Naziv predmeta?");
                String name = in.nextLine();
                Application.inputStringCheck(name, out, in);
                out.println("Sifra predmeta?");
                int sifra = in.nextInt();
                in.nextLine();
                out.println("Max Broj Studenata?");
                int maxBrojStudenata = in.nextInt();
                in.nextLine();
                out.println("Godina?");
                int godina = in.nextInt();
                in.nextLine();
                Application.addPredmet(new Predmet(name, sifra, maxBrojStudenata, godina), odsijek);
                break;
            } catch (InputMismatchException e) {
                in.skip(".*");
                System.out.println("Predmet nije kreiran, unesite validne podatke \n");
                in.nextLine();
            }
        } while (true);
    }

    public static void uiDeletePredmet(PrintStream out, Scanner in) {
        uiIspisPredmetaNaOdsijeku(out, in);
        out.println("Unesiti predmet koji zeliti izbrisati: ");
        Predmet uneseniPredmet = Application.izabraniPredmet(out, in);
        if (uneseniPredmet == null) {
            out.println("Trazeni predmet ne postoji");
            return;
        }
        Application.deletePredmet(uneseniPredmet);
    }

    public static void uiIspisStudenataNaFakultetu(PrintStream out, Scanner in) {
        uiIspisFakulteta(out);
        out.println("Izaberite fakultet");
        String nameFakultet = in.nextLine();
        out.println(Application.ispisStudenataNaFakultetu(nameFakultet));
    }

    public static void uiUpisiStudentaNaPredmet(PrintStream out, Scanner in) {

        Predmet izabraniPredmet = Application.izabraniPredmet(out, in);
        if (izabraniPredmet == null) {
            out.println("Izabrani predmet ne postoji");
            return;
        }

        Student izabraniStudent = Application.izabraniStudent(out, in);
        if (izabraniStudent == null) {
            out.println("Izabrani student nepostoji");
            return;
        }

        Application.upisiStudentaNaPredmet(izabraniStudent, izabraniPredmet);
    }

    public static void uiIspisStudenataNaOdsijeku(PrintStream out, Scanner in) {

        Odsijek izabraniOdsijek = Application.izabraniOdsijek(out, in);
        out.println(Application.ispisiStudentaNaOdsijeku(izabraniOdsijek));
    }

    public static void uiIspisiStudenteNaPredmetu(PrintStream out, Scanner in) {

        Predmet izabraniPredmet = Application.izabraniPredmet(out, in);
        if (izabraniPredmet == null) {
            out.println("Izabrani predmet ne postoji");
            return;
        }
        out.println(Application.ispisiStudenteNaPredmetu(izabraniPredmet));
    }
}
