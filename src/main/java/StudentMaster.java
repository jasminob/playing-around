public class StudentMaster extends Student {
    public StudentMaster(String firstName, String lastName, int indexNumber) {
        super(firstName, lastName, indexNumber);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d) Master", getLastName(), getFirstName(), getIndexNumber());
    }

    @Override
    int getCiklus() {
        return 2;
    }
}
