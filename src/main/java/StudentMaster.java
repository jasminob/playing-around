public class StudentMaster extends Student implements Saveable {
    public StudentMaster(String firstName, String lastName, int indexNumber) {
        super(firstName, lastName, indexNumber);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, (%d), Master", getFirstName(), getLastName(), getIndexNumber());
    }

    public StudentMaster(String saveData) {
        super(saveData);
    }

    @Override
    int getCiklus() {
        return 2;
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
