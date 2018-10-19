public class StudentBachelor extends Student {

    public StudentBachelor(String firstName, String lastName, int indexNumber) {
        super(firstName, lastName, indexNumber);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d) Bachelor", getLastName(), getFirstName(), getIndexNumber());
    }

    @Override
    int getCiklus() {
        return 1;
    }
}
