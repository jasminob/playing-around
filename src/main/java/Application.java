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

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    private static Arhivar arhivar = new ArhivarOut();

    private static Saveable saveable;

    private static Fakultet[] fakultet = new Fakultet[0];

    public static String ispisFakulteta() {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            buffer += (i + 1) + ". " + fakultet[i].getNazivFakulteta() + "\n";
        }
        return buffer;

    }

    public static void inputStringCheck(String name, PrintStream out, Scanner in) {
        while (!name.matches("[a-zA-Z]+")) {
            out.println("Samo slova. Try again.");
            name = in.nextLine();
        }
    }

    public static void addFakultet(Fakultet f) {

        arhivar.dodaoFakultet(f);

        Fakultet[] newFak = new Fakultet[fakultet.length + 1];

        for (int i = 0; i < fakultet.length; i++) {
            newFak[i] = fakultet[i];
        }
        newFak[fakultet.length] = f;
        fakultet = newFak;

    }

    public static void deleteFakultet(Fakultet f) {
        int index = -1;

        for (int i = 0; i < fakultet.length; i++) {
            if (fakultet[i].equals(f)) {
                index = i;
            }
        }

        if (index == -1) {
            return;
        }

        arhivar.izbrisaoFakultet(f);
        Fakultet[] newFak = new Fakultet[fakultet.length - 1];
        int counter = 0;
        for (int i = 0; i < fakultet.length; i++) {
            if (index != i) {
                newFak[counter] = fakultet[i];
                counter++;
            }
        }
        fakultet = newFak;
    }


    public static String ispisOdsijeka(Fakultet nazivFakulteta) {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            if (fakultet[i].equals(nazivFakulteta)) {
                for (int j = 0; j < fakultet[i].odsijeks.length; j++) {

                    buffer += " - " + fakultet[i].odsijeks[j].getNazivOdsijeka() + "\n";
                }
            }
        }
        return buffer;
    }

    public static boolean isFakultetEmpty() {
        return fakultet.length == 0;
    }


    public static void addOdsijek(String nazivFakulteta, Odsijek odsijek) {

        arhivar.dodaoOdsijek(odsijek);

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

    public static void deleteOdsijek(Odsijek o) {

        int index = -1;

        for (int i = 0; i < fakultet.length; i++) {
            for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                if (fakultet[i].odsijeks[j].equals(o)) {
                    index = j;
                }
            }

            if (index == -1) {
                return;
            }
            arhivar.izbrisaoOdsijek(o);
            Odsijek[] newOd = new Odsijek[fakultet[i].odsijeks.length - 1];
            int counter = 0;
            for (int k = 0; k < fakultet[i].odsijeks.length; k++) {
                if (index != k) {
                    newOd[counter] = fakultet[i].odsijeks[k];
                    counter++;
                }
            }
            fakultet[i].odsijeks = newOd;
        }

    }

    public static void addStudent(String nameFakultet, Student student) {


        for (int i = 0; i < fakultet.length; i++) {
            if (fakultet[i].getNazivFakulteta().equals(nameFakultet)) {

                Student[] newStud = new Student[fakultet[i].students.length + 1];
                for (int j = 0; j < fakultet[i].students.length; j++) {
                    newStud[j] = fakultet[i].students[j];
                }
                newStud[fakultet[i].students.length] = student;
                fakultet[i].students = newStud;
            }
        }
        arhivar.dodaoStudent(student);
    }

    public static void addPredmet(Predmet p, Odsijek nameOdsijeka) {

        for (int i = 0; i < fakultet.length; i++) {
            for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                if (fakultet[i].odsijeks[j].equals(nameOdsijeka)) {
                    fakultet[i].odsijeks[j].registrujPredmet(p);
                }
            }
        }
        arhivar.dodaoPredmet(p);
    }


    public static String ispisPredmetaNaOdsijeku(Odsijek nameOdsijeka) {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                if (fakultet[i].odsijeks[j].equals(nameOdsijeka)) {
                    for (int k = 0; k < fakultet[i].odsijeks[j].getPredmets().length; k++) {
                        Predmet sub = fakultet[i].odsijeks[j].getPredmets()[k];
                        buffer += " - Naziv: " + sub.getNazivPredmeta() + ", Sifra: " + sub.getSifraPredmeta() + "\n";
                    }
                }
            }
        }
        return buffer;

    }

    public static void deletePredmet(Predmet p) {

        int index = -1;

        for (int i = 0; i < fakultet.length; i++) {
            for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                Predmet[] predmet = fakultet[i].odsijeks[j].getPredmets();
                for (int k = 0; k < predmet.length; k++)
                    if (fakultet[i].odsijeks[j].getPredmets()[k].equals(p)) {
                        index = j;
                    }

                if (index == -1) {
                    return;
                }

                arhivar.izbrisaoPredmet(p);
                Predmet[] newPred = new Predmet[predmet.length - 1];
                int counter = 0;
                for (int k = 0; k < predmet.length; k++) {
                    if (index != k) {
                        newPred[counter] = predmet[k];
                        counter++;
                    }
                }
                predmet = newPred;
                fakultet[i].odsijeks[j].setPredmets(newPred);
            }
        }
    }

    public static String ispisStudenataNaFakultetu(String nazivFakulteta) {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            if (fakultet[i].getNazivFakulteta().equals(nazivFakulteta)) {
                Student[] sub = fakultet[i].students;
                for (int j = 0; j < sub.length; j++) {

                    buffer += "Ime: " + sub[j].getFirstName() + " Prezime: " + sub[j].getLastName() + " Index: " + sub[j].getIndexNumber() + "\n";
                }
            }

        }
        return buffer;

    }


    public static void upisiStudentaNaPredmet(Student s, Predmet p) {
        p.upisi(s);
    }


    public static Fakultet izabraniFakultet(PrintStream out, Scanner in) {
        UiUtility.uiIspisFakulteta(out);

        String ime = in.nextLine();

        for (Fakultet f : fakultet) {
            if (ime.equals(f.getNazivFakulteta())) {
                return f;
            }
        }
        return null;
    }


    public static Odsijek izabraniOdsijek(PrintStream out, Scanner in) {
        UiUtility.uiIspisOdsijeka(out, in);
        out.println("Izaberite odsijek jedan od ponudjenih odsijeka: ");

        String ime = in.nextLine();
        for (Fakultet f : fakultet) {
            for (Odsijek o : f.odsijeks) {
                if (ime.equals(o.getNazivOdsijeka())) {
                    return o;
                }
            }
        }
        return null;
    }

    public static Predmet izabraniPredmet(PrintStream out, Scanner in) {
        UiUtility.uiIspisPredmetaNaOdsijeku(out, in);
        out.println("Izaberite jedan od ponudjenih predmeta(ime): ");

        String ime = in.nextLine();
        for (Fakultet f : fakultet) {
            for (Odsijek o : f.odsijeks) {
                for (Predmet p : o.getPredmets()) {
                    if (ime.equals(p.getNazivPredmeta())) {
                        return p;
                    }
                }
            }
        }
        return null;
    }

    public static Student izabraniStudent(PrintStream out, Scanner in) {

        UiUtility.uiIspisStudenataNaFakultetu(out, in);
        out.println("Unesite broj indexa");
        int index = in.nextInt();
        in.nextLine();
        for (Fakultet f : fakultet) {
            for (Student s : f.students) {
                if (index == s.getIndexNumber()) {
                    return s;
                }
            }
        }
        return null;
    }


    public static String ispisiStudenteNaPredmetu(Predmet p) {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                for (int z = 0; z < fakultet[i].odsijeks[j].getPredmets().length; z++) {
                    if (fakultet[i].odsijeks[j].getPredmets()[z].equals(p)) {
                        for (int k = 0; k < fakultet[i].odsijeks[j].getPredmets()[z].getStudents().brojElemenata(); k++) {

                            Student sub = (Student) fakultet[i].odsijeks[j].getPredmets()[z].getStudents().get(k);
                            buffer += " - " + sub.getFirstName() + " " + sub.getLastName() + " " + sub.getIndexNumber() + "\n";
                        }
                    }
                }
            }
        }
        return buffer;
    }

    public static String ispisiStudentaNaOdsijeku(Odsijek o) {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                for (int z = 0; z < fakultet[i].odsijeks[j].getPredmets().length; z++) {
                    for (int y = 0; y < fakultet[i].odsijeks[j].getPredmets()[z].getStudents().brojElemenata(); y++) {
                        buffer += "Ime: " + fakultet[i].odsijeks[j].getPredmets()[z].getStudents().get(y) + "\n";
                    }
                }
            }
        }
        return buffer;
    }


    public static Student ucitajMaster(Scanner in) {
        String name = in.nextLine();
        String lastName = in.nextLine();
        int index = in.nextInt();
        in.nextLine();

        String studentString = String.format("%s,%s,%d", name, lastName, index);
        return new StudentMaster(studentString);
    }

    public static Student ucitajBachelor(Scanner in) {

        String name = in.nextLine();
        String lastName = in.nextLine();
        int index = in.nextInt();
        in.nextLine();

        String studentString = String.format("%s,%s,%d", name, lastName, index);
        return new StudentBachelor(studentString);
    }

    public static void saveStudent(PrintStream out, Student s) {
        String saveData = s.save();
        out.println(saveData);
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;

        Scanner g = new Scanner(System.in);


        /*
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

            try {
                int choice = g.nextInt();
                g.nextLine();

                switch (choice) {
                    case 1:
                        UiUtility.uiAddFakultet(out, in);
                        break;
                    case 2:
                        UiUtility.uiAddOdsijek(out, in);
                        break;
                    case 3:
                        UiUtility.uiAddStudent(out, in);
                        break;
                    case 4:
                        UiUtility.uiAddPredmet(out, in);
                        break;
                    case 5:
                        UiUtility.uiUpisiStudentaNaPredmet(out, in);
                        break;
                    case 6:

                        break;
                    case 7:
                        UiUtility.uiDeleteOdsijek(out, in);
                        break;
                    case 8:
                        UiUtility.uiDeletePredmet(out, in);
                        break;
                    case 9:
                        UiUtility.uiDeleteFakultet(out, in);
                        break;
                    case 10:
                        UiUtility.uiIspisFakulteta(out);
                        break;
                    case 11:
                        UiUtility.uiIspisOdsijeka(out, in);
                        break;
                    case 12:
                        UiUtility.uiIspisStudenataNaOdsijeku(out, in);
                        break;
                    case 13:
                        UiUtility.uiIspisPredmetaNaOdsijeku(out, in);
                        break;
                    case 14:
                        UiUtility.uiIspisiStudenteNaPredmetu(out, in);
                        break;
                    case 15:
                        return;
                }
            } catch (InputMismatchException e) {
                g.skip(".*");
                System.out.println("Unesi neki integer izmedju 1 i 15 \n");
            }
        }

        */


    }
}














