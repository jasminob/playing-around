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


class Fakultet {

    String nazivFakulteta;
    Student[] students = new Student[0];
    Odsijek[] odsijeks = new Odsijek[0];

    public Fakultet(String nazivFakulteta) {
        this.nazivFakulteta = nazivFakulteta;
    }

    public String getNazivFakulteta() {
        return nazivFakulteta;
    }

    public void registrujOdsijek(Odsijek o) {
        boolean check = false;
        Odsijek[] noviNiz = new Odsijek[odsijeks.length + 1];

        for (int i = 0; i < odsijeks.length; i++) {
            if (odsijeks[i] == o) {
                check = true;
            }
            noviNiz[i] = odsijeks[i];
        }

        if (!check) {
            noviNiz[odsijeks.length] = o;
            odsijeks = noviNiz;
        }


    }

    public void upisiStudent(Student s, String nazivOdsijeka) {

        Student[] noviNiz = new Student[students.length+1];
        boolean check = false;

        for (int i = 0; i < odsijeks.length; i++) {
            if (odsijeks[i].getNazivOdsijeka().equals(nazivOdsijeka)) {
                 check = true;
                 odsijeks[i].upisiStudent(s);
            }

        }

        for(int i = 0; i < students.length; i++){
            noviNiz[i] = students[i];
        }
        if(check){
            noviNiz[students.length] = s;
        }
        students = noviNiz;
    }

 /*   @Override
    public String toString() {

        String buffer = new String();

        for (int i = 0; i < students.length; i++) {
            buffer += (i + 1) + ". " + students[i].toString() + "je student " + [INSERT_YEAR]+" godine na odsijeku "
                    + odsijeks[i].getNazivOdsijeka() + "\n";
        }
        return buffer;
        }

        */
}






