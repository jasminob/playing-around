/*
Prosiriti program ispod sa sljedecim:

Napraviti klasu Odsijek koja ce imati sljedece fields:
- naziv odsijeka (sa getterom, postavlja se pri constructoru i ne moze se kasnije izmjeniti)
- niz predmeta koji se drze na ovom odsijeku
- metoda int getMaxBrojStudenataZaGodinu(int godina) koja ce dati maksimalan broj studenata koji bi se mogao upisati
  u nekoj godini. Taj podatak nece cuvati u varijabli nego ce ga racunati svaki put ispocetka tako sto prodjes
  kroz niz predmeta i vidis koliko maksimalno mozes upisati studenata da slusaju SVE predmete na odredjenoj godini
  (npr ako na prvoj godini ima 10 predmeta, i od toga 9 predmeta prima 100 studenata, a deseti prima 50, onda je
  max broj studenata na prvoj godini 50, jer svi moraju slusati svih 10 predmeta)

Napraviti klasu Fakultet koja ce imati sljedece fields:
- naziv fakulteta (samo getter, postavlja se pri constructoru i ne moze se kasnije izmjeniti)
- niz studenata koji su upisani na fakultet (bez get i set, pri kreiranju objekta se kreira kao prazan niz)
- niz odsijeka koji se realizuju na fakultetu (isto kao niz studenata)

U klasi Predmet dodati fields:
- godina, predstavlja godinu na kojoj se ovaj predmet drzi, postavlja se preko constructora i ima samo getter
 */

public class Application {

    public static Predmet kreiranjeNovogPredmeta() {

        return new Predmet(new Student[]{}, "Whatever", 5, 3);

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

        Predmet x = new Predmet(new Student[]{}, "test", 5, 10);
        Student w = new Student("Neko", "Neko", 555);
        upisStudentaNaPredmet(kreiranjeNovogStudenta(), x);
        upisStudentaNaPredmet(kreiranjeNovogStudenta(), x);
        upisStudentaNaPredmet(kreiranjeNovogStudenta(), x);
        upisStudentaNaPredmet(w, x);


        System.out.println(spisakStudentaNaPredmetu(x));

        ispisStudentaSaPredmeta(w, x);

        System.out.println(spisakStudentaNaPredmetu(x));
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


    public Predmet(Student[] students, String nazivPredmeta, int sifraPredmeta, int maxBrojStudenata) {
        this.students = students;
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

    @Override
    public String toString() {
        String buffer = new String();

        for (int i = 0; i < students.length; i++) {
            buffer += (i + 1) + ". " + students[i].toString() + "\n";
        }
        return buffer;
    }
}


