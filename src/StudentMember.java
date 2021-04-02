public class StudentMember extends DefaultMember {

    private String schoolName;

    public StudentMember(String membershipNumber, String name, int day, int month, int year, String schoolName) {
        super(membershipNumber, name, day, month, year);
        setSchoolName(schoolName);
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String toString() {
        return "Membership No: " + getMembershipNumber() + " | " + "Membership Type: Student" + " | " + "Name: " + getName() + " | " + "Start Date: " + getStartMembershipDate() + " | " + "School: " + getSchoolName();
    }
}
