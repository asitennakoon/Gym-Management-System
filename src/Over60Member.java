public class Over60Member extends DefaultMember {

    private int age;

    public Over60Member(String membershipNumber, String name, int day, int month, int year, int age) {
        super(membershipNumber, name, day, month, year);
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 60) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Over 60 Members have to be older than the age of 60");
        }
    }

    public String toString() {
        return "Membership No: " + getMembershipNumber() + " | " + "Membership Type: Over 60" + " | " + "Name: " + getName() + " | " + "Start Date: " + getStartMembershipDate() + " | " + "Age: " + getAge();
    }
}
