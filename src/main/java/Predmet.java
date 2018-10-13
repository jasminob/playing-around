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


class Predmet {
    private Student[] students;
    private String nazivPredmeta;
    private int sifraPredmeta;
    private int maxBrojStudenata;
    private int godina;
    private int minBrojStudenata = 0;


    public Predmet(Student[] students, String nazivPredmeta, int sifraPredmeta, int maxBrojStudenata, int godina) {
        this.students = students;
        this.nazivPredmeta = nazivPredmeta;
        this.sifraPredmeta = sifraPredmeta;
        this.maxBrojStudenata = maxBrojStudenata;
        this.godina = godina;
    }

    public int getMinBrojStudenata() {
        return minBrojStudenata;
    }

    public void setMinBrojStudenata(int minBrojStudenata) {
        this.minBrojStudenata = minBrojStudenata;
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

    public int brojPotrebnihStudenata(){

        int preostalaMjesta = getMinBrojStudenata()-students.length;

        if(preostalaMjesta < 0){
            //Exception
        }
        return preostalaMjesta;
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








