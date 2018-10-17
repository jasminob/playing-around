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

class Odsijek {
    private String nazivOdsijeka;
    private Predmet[] predmets = new Predmet[0];


    public Odsijek(String nazivOdsijeka) {
        this.nazivOdsijeka = nazivOdsijeka;

    }

    public Predmet[] getPredmets() {
        return predmets;
    }

    public void setPredmets(Predmet[] predmets) {
        this.predmets = predmets;
    }

    public String getNazivOdsijeka() {
        return nazivOdsijeka;
    }


    public int getMaxBrojStudenataZaGodinu(int godina) {

        int result = -1;

        for (int i = 0; i < predmets.length; i++) {

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

    public void upisiStudent(Student s) {

        for (int i = 0; i < predmets.length; i++) {
            if (predmets[i].getGodina() == 1) {
                predmets[i].upisi(s);
            }
        }
    }

    public void registrujPredmet(Predmet p) {

        Predmet[] noviNiz = new Predmet[predmets.length + 1];

        for (int i = 0; i < predmets.length; i++) {
            noviNiz[i] = predmets[i];
        }

        noviNiz[predmets.length] = p;
        predmets = noviNiz;

    }

    public int brojPotrebnihStudenata(int godina) {

        int result = -1;
        for (int i = 0; i < predmets.length; i++) {
            if (godina == predmets[i].getGodina()) {
                int min = predmets[i].minPreostalihMjesta();
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

    public int getGodinaStudenta(Student student){

         int result = 0;
         Predmet[] predmeteKojeSlusa = getPredmetKojeStudentSlusa(student);

        for(int i = 0; i < predmeteKojeSlusa.length; i++){
            int maxGodina = predmeteKojeSlusa[i].getGodina();
            if(result == 0){
                result = maxGodina;
            }
            if(result < maxGodina){
                result = maxGodina;
            }
        }
        return result;
    }




   public Predmet[] getPredmetKojeStudentSlusa(Student student){



       Predmet[] result = new Predmet[0];
       int counter = 0;


       for(int i = 0; i < predmets.length; i++) {
           for (int j = 0; j < predmets[i].getStudents().length; j++) {
               Predmet[] tempNiz = new Predmet[result.length+1];
               if (predmets[i].getStudents()[j].equals(student)) {

                   tempNiz[counter] = predmets[i];
                   counter++;
                   result = tempNiz;
               }

           }

       }
       return result;
   }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Odsijek)) return false;
        Odsijek odsijek = (Odsijek) o;
        return Objects.equals(nazivOdsijeka, odsijek.nazivOdsijeka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazivOdsijeka);
    }
}




/*
U odsijeku popraviti funkciju getGodinaStudenta tako da prima argument Studenta, implementirati uz
pomocnu funkciju Odsijek.getPredmeteKojeStudentSlusa(Student s) koja vraca niz predmeta koje student slusa
Funkcija getGodinaStudenta treba da se svede na nalazenje max elementa niza (po godini) koji vraca pomenuta funkcija.
 */



