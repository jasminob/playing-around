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


class Odsijek {
    private String nazivOdsijeka;
    private Predmet[] predmets = new Predmet[0];


    public Odsijek(String nazivOdsijeka) {
        this.nazivOdsijeka = nazivOdsijeka;

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

        for(int i = 0; i < getPredmetKojeStudentSlusa(student).length; i++){
            int maxGodina = getPredmetKojeStudentSlusa(student)[i].getGodina();
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
       int counter = 0;


       for(int i = 0; i < predmets.length; i++) {
           for (int j = 0; j < predmets[i].getStudents().length; j++) {
               if (predmets[i].getStudents()[j].equals(student)) {
                   counter++;
               }
           }
           Predmet[] noviNiz = new Predmet[counter];
           int k = 0;
           for (int j = 0; j < predmets[i].getStudents().length; j++) {
               if (predmets[i].getStudents()[j].equals(student)) {
                   noviNiz[k] = predmets[i];
                   k++;
               }
           }
           predmets = noviNiz;
       }
       return predmets;
   }



}




/*
U odsijeku popraviti funkciju getGodinaStudenta tako da prima argument Studenta, implementirati uz
pomocnu funkciju Odsijek.getPredmeteKojeStudentSlusa(Student s) koja vraca niz predmeta koje student slusa
Funkcija getGodinaStudenta treba da se svede na nalazenje max elementa niza (po godini) koji vraca pomenuta funkcija.
 */



