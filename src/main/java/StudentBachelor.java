public class StudentBachelor extends Student implements Saveable {

    public StudentBachelor(String firstName, String lastName, int indexNumber) {
        super(firstName, lastName, indexNumber);
    }

    public StudentBachelor(String saveData) {
        super(saveData);
    }


    @Override
    public String toString() {
        return String.format("%s, %s, (%d), Bachelor", getFirstName(), getLastName(), getIndexNumber());
    }

    @Override
    int getCiklus() {
        return 1;
    }

    @Override
    public String save() {
        return String.format("%s,%s,%d", getFirstName(), getLastName(), getIndexNumber());
    }


    @Override
    public void load(String saved) {

        String[] tempStudent = saved.split(",");
        this.setFirstName(tempStudent[0]);
        this.setLastName(tempStudent[1]);
        this.setIndexNumber(Integer.parseInt(tempStudent[2]));
    }
}
