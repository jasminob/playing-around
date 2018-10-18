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


import java.util.Objects;

class Predmet {
    private Kolekcija students = new Kolekcija();
    private String nazivPredmeta;
    private int sifraPredmeta;
    private int maxBrojStudenata;
    private int godina;
    private int minBrojStudenata = 0;

    public Predmet(int sifraPredmeta) {
        this.sifraPredmeta = sifraPredmeta;
    }

   
    public Predmet(String nazivPredmeta, int sifraPredmeta, int maxBrojStudenata, int godina) {
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

    public Kolekcija getStudents() {
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

        students.dodaj(student);
    }


    public void ispisi(Student student) {

        students.obrisi(student);
    }

    public int minPreostalihMjesta(){

        int preostalaMjesta = getMinBrojStudenata()-students.brojElemenata();

        if(preostalaMjesta < 0){
            return 0;
        }
        return preostalaMjesta;
    }

    @Override
    public String toString() {
        String buffer = new String();

        for (int i = 0; i < students.brojElemenata(); i++) {
            buffer += (i + 1) + ". " + students.get(i).toString() + "\n";
        }
        return buffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Predmet)) return false;
        Predmet predmet = (Predmet) o;
        return sifraPredmeta == predmet.sifraPredmeta &&
                maxBrojStudenata == predmet.maxBrojStudenata &&
                godina == predmet.godina &&
                Objects.equals(nazivPredmeta, predmet.nazivPredmeta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazivPredmeta, sifraPredmeta, maxBrojStudenata, godina);
    }
}









