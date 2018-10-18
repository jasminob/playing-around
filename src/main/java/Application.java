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
import java.util.Scanner;

public class Application {

    private static Arhivar arhivar = new ArhivarOut();

    private static Fakultet[] fakultet = new Fakultet[0];




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


    private static void uiIspisFakulteta(PrintStream out) {
        out.println(ispisFakulteta());
    }

    private static String ispisFakulteta() {

        if(fakultet.length == 0){
            return "Nema registrovanih fakulteta  \n";

        }

            String buffer = new String();
            for (int i = 0; i < fakultet.length; i++) {
                buffer += (i + 1) + ". " + fakultet[i].getNazivFakulteta() + "\n";
            }
            return buffer;

    }

    private static void addFakultet(Fakultet f) {

        arhivar.dodaoFakultet(f);

        Fakultet[] newFak = new Fakultet[fakultet.length + 1];

        for (int i = 0; i < fakultet.length; i++) {
            newFak[i] = fakultet[i];
        }
        newFak[fakultet.length] = f;
        fakultet = newFak;

    }

    private static void deleteFakultet(Fakultet f) {
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


    public static String spisakStudentaNaPredmetu(Predmet predmet) {
        return "";
    }

    private static String ispisStudenata(String nazivFakulteta, String nazivOdsijeka) {
        return "";
    }


    private static void uiDeleteFakultet(PrintStream out, Scanner in) {
        uiIspisFakulteta(out);
        Fakultet name = uiIzabraniFakultet(out, in);
        if(name == null){
            out.println("Nema trazenog odsijeka");
            return;
        }
        deleteFakultet(name);
    }

    private static void uiAddFakultet(PrintStream out, Scanner in) {
        out.println("Unesite trazene podatke za unos fakulteta");
        out.println("Naziv? ");
        String name = in.nextLine();
        addFakultet(new Fakultet(name));
    }


    private static void uiIspisOdsijeka(PrintStream out, Scanner in) {

        out.println("Unesite trazene podatke za ispis Odsijeka");
        out.println("Naziv fakulteta? ");
        Fakultet izabraniFakultet = uiIzabraniFakultet(out, in);
        if(izabraniFakultet == null){
            out.println("Izabrani fakultet ne postoji");
            return;
        }
        out.println(ispisOdsijeka(izabraniFakultet));


    }

    private static String ispisOdsijeka(Fakultet nazivFakulteta) {

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

    private static boolean isFakultetEmpty(){
        return fakultet.length == 0;
    }

    private static void uiAddOdsijek(PrintStream out, Scanner in) {

        uiIspisFakulteta(out);

        if(isFakultetEmpty()){
            return;
        }
        out.println("Odaberite fakultet");
        String fakultetName = in.nextLine();
        out.println("Unesite trazene podatke za unos Odsijeka");
        out.println("Naziv?");
        String name = in.nextLine();
        addOdsijek(fakultetName, new Odsijek(name));

    }



    private static void addOdsijek(String nazivFakulteta, Odsijek odsijek) {

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

    private static void uiIspisPredmetaNaOdsijeku(PrintStream out, Scanner in) {

        Odsijek odsijek = uiIzabraniOdsijek(out, in);
        if(odsijek == null){
            out.println("Nema trazenog odsijeka");
            return;
        }
        out.println(ispisPredmetaNaOdsijeku(odsijek));
    }

    private static void uiDeleteOdsijek(PrintStream out, Scanner in) {

        Odsijek deleteOdsijek = uiIzabraniOdsijek(out, in);
        deleteOdsijek(deleteOdsijek);

    }



    private static void deleteOdsijek(Odsijek o) {

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


    private static void uiAddStudent(PrintStream out, Scanner in) {

        uiIspisFakulteta(out);
        out.println("Odaberite fakultet ");
        String nameFakultet = in.nextLine();
        out.println("Unesite trazene podatke za unos Studenta");
        out.println("Ime?");
        String name = in.nextLine();
        out.println("Prezime?");
        String lastName = in.nextLine();
        out.println("Index?");
        int index = in.nextInt();
        in.nextLine();
        addStudent(nameFakultet, new Student(name, lastName, index));

    }

    private static void addStudent(String nameFakultet, Student student) {


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


    private static void uiAddPredmet(PrintStream out, Scanner in) {



        Odsijek odsijek = uiIzabraniOdsijek(out, in);
        if(odsijek == null){
            out.println("Nema trazenog odsijeka");
            return;
        }

        out.println("Naziv predmeta?");
        String name = in.nextLine();
        out.println("Sifra predmeta?");
        int sifra = in.nextInt();
        in.nextLine();
        out.println("Max Broj Studenata?");
        int maxBrojStudenata = in.nextInt();
        in.nextLine();
        out.println("Godina?");
        int godina = in.nextInt();
        in.nextLine();
        addPredmet(new Predmet(name, sifra, maxBrojStudenata, godina), odsijek);
    }

    private static void addPredmet(Predmet p, Odsijek nameOdsijeka) {

        for (int i = 0; i < fakultet.length; i++) {
            for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                if (fakultet[i].odsijeks[j].equals(nameOdsijeka)) {
                    fakultet[i].odsijeks[j].registrujPredmet(p);
                }
            }
        }
        arhivar.dodaoPredmet(p);
    }



    private static String ispisPredmetaNaOdsijeku(Odsijek nameOdsijeka) {

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

    private static void deletePredmet(Predmet p) {

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



    public static void uiDeletePredmet(PrintStream out, Scanner in) {
        uiIspisPredmetaNaOdsijeku(out, in);
        out.println("Unesiti predmet koji zeliti izbrisati: ");
        Predmet uneseniPredmet = uiIzabraniPredmet(out, in);
        if(uneseniPredmet == null){
            out.println("Trazeni predmet ne postoji");
            return;
        }
        deletePredmet(uneseniPredmet);


    }


    private static String ispisStudenataNaFakultetu(String nazivFakulteta) {

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

    private static void uiIspisStudenataNaFakultetu(PrintStream out, Scanner in) {
        uiIspisFakulteta(out);
        out.println("Izaberite fakultet");
        String nameFakultet = in.nextLine();
        out.println(ispisStudenataNaFakultetu(nameFakultet));
    }

    private static void uiUpisiStudentaNaPredmet(PrintStream out, Scanner in) {

        Predmet izabraniPredmet = uiIzabraniPredmet(out, in);
        if(izabraniPredmet == null){
            out.println("Izabrani predmet ne postoji");
            return;
        }

        Student izabraniStudent = uiIzbraniStudent(out, in);
        if(izabraniStudent == null){
            out.println("Izabrani student nepostoji");
            return;
        }


        upisiStudentaNaPredmet(izabraniStudent, izabraniPredmet);

    }

    private static void upisiStudentaNaPredmet(Student s, Predmet p) {
        p.upisi(s);
    }

    private static void uiIspisiStudenteNaPredmetu(PrintStream out, Scanner in) {


        Odsijek izabraniOdsijek = uiIzabraniOdsijek(out, in);
        if(izabraniOdsijek == null){
            out.println("Izabrani odsijek ne postoji");
            return;
        }

        Predmet izabraniPredmet = uiIzabraniPredmet(out, in);
        if(izabraniPredmet == null){
            out.println("Izabrani predmet ne postoji");
            return;
        }

        out.println(ispisiStudenteNaPredmetu(izabraniPredmet));


    }

    private static Fakultet uiIzabraniFakultet(PrintStream out, Scanner in){
        uiIspisFakulteta(out);


        String ime = in.nextLine();
        for(Fakultet f : fakultet) {

            if( ime.equals(f.getNazivFakulteta()) ){
                return f;
            }
        }

        return null;
    }

    private static Odsijek uiIzabraniOdsijek(PrintStream out, Scanner in){
        uiIspisOdsijeka(out, in);

        String ime = in.nextLine();
        for(Fakultet f : fakultet) {
            for (Odsijek o : f.odsijeks){
                if( ime.equals(o.getNazivOdsijeka())){
                    return o;
                }
            }
        }
        return null;
    }

    private static Predmet uiIzabraniPredmet(PrintStream out, Scanner in){
        uiIspisPredmetaNaOdsijeku(out, in);

        String ime = in.nextLine();
        for(Fakultet f: fakultet){
            for(Odsijek o : f.odsijeks){
                for(Predmet p : o.getPredmets()){
                    if(ime.equals(p.getNazivPredmeta())){
                        return p;
                    }
                }
            }
        }
        return null;
    }

    private static Student uiIzbraniStudent(PrintStream out, Scanner in){

        uiIspisStudenataNaFakultetu(out, in);

        int index = in.nextInt();
        in.nextLine();
        for(Fakultet f : fakultet){
            for(Student s : f.students){
                if(index == s.getIndexNumber()){
                    return s;
                }
            }
        }
        return null;
    }





    private static String ispisiStudenteNaPredmetu(Predmet p) {

        String buffer = new String();
        for (int i = 0; i < fakultet.length; i++) {
            for (int j = 0; j < fakultet[i].odsijeks.length; j++) {
                for (int z = 0; z < fakultet[i].odsijeks[j].getPredmets().length; z++) {
                    if (fakultet[i].odsijeks[j].getPredmets()[z].equals(p)) {
                        for (int k = 0; k < fakultet[i].odsijeks[j].getPredmets()[z].getStudents().brojElemenata(); k++) {

                            Student sub = (Student)fakultet[i].odsijeks[j].getPredmets()[z].getStudents().get(k);
                            buffer += " - Ime: " + sub.getFirstName() + ", Prezime: " + sub.getLastName() + "\n";
                        }
                    }
                }
            }
        }
        return buffer;
    }



    private static void uiIspisStudenataNaOdsijeku(PrintStream out, Scanner in){


        Odsijek izabraniOdsijek = uiIzabraniOdsijek(out, in);

        out.println(ispisiStudentaNaOdsijeku(izabraniOdsijek));
    }

    private static String ispisiStudentaNaOdsijeku(Odsijek o){

        String buffer = new String();
        for(int i = 0; i < fakultet.length; i++){
            for(int j = 0; j < fakultet[i].odsijeks.length; j++){
                for(int z = 0; z < fakultet[i].odsijeks[j].getPredmets().length; z++){
                    for(int y = 0; y < fakultet[i].odsijeks[j].getPredmets()[z].getStudents().brojElemenata(); y++) {
                        buffer += "Ime: " + fakultet[i].odsijeks[j].getPredmets()[z].getStudents().get(y) + "\n";
                    }
                }
            }
        }
        return buffer;
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
                    uiUpisiStudentaNaPredmet(out, in);
                    break;
                case 6:

                    break;
                case 7:
                    uiDeleteOdsijek(out, in);
                    break;
                case 8:
                    uiDeletePredmet(out, in);
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
                    uiIspisStudenataNaOdsijeku(out, in);
                    break;
                case 13:
                    uiIspisPredmetaNaOdsijeku(out, in);
                    break;
                case 14:
                    uiIspisiStudenteNaPredmetu(out, in);
                    break;
                case 15:
                    return;

            }

        }


    }
}














