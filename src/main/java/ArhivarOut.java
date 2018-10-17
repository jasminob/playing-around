public class ArhivarOut implements Arhivar {
    @Override
    public void dodaoFakultet(Fakultet f) {
        System.out.println(String.format("Dodao fakultet: %s \n", f.getNazivFakulteta()));
    }

    @Override
    public void izbrisaoFakultet(Fakultet f) {
        System.out.println(String.format("Izbrisao fakultet: %s", f.getNazivFakulteta()));
    }

    @Override
    public void dodaoStudent(Student s) {
        System.out.println(String.format("Dodao studenta: %s %s", s.getFirstName(), s.getLastName()));
    }

    @Override
    public void izbrisaoStudent(Student s) {
        System.out.println(String.format("Izbrisao studenta: %s %s", s.getFirstName(), s.getLastName()));
    }

    @Override
    public void dodaoPredmet(Predmet p) {
        System.out.println(String.format("Dodao predmet: %s", p.getNazivPredmeta()));
    }

    @Override
    public void izbrisaoPredmet(Predmet p) {
        System.out.println(String.format("Izbrisao predmet: %s", p.getNazivPredmeta()));
    }

    @Override
    public void dodaoOdsijek(Odsijek o) {
        System.out.println(String.format("Dodao odsijek: %s", o.getNazivOdsijeka()));
    }

    @Override
    public void izbrisaoOdsijek(Odsijek o) {
        System.out.println(String.format("Izbrisao odsijek: %s", o.getNazivOdsijeka()));
    }
}
