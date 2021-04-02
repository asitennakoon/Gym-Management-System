import java.io.Serializable;
import java.util.Comparator;

public class DefaultMember implements Serializable {

    private String membershipNumber;
    private String name;
    private Date startMembershipDate;

    public DefaultMember(String membershipNumber, String name, int day, int month, int year) {
        super();
        setMembershipNumber(membershipNumber);
        setName(name);
        setStartMembershipDate(day, month, year);
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartMembershipDate() {
        return startMembershipDate;
    }

    public void setStartMembershipDate(int day, int month, int year) {
        this.startMembershipDate = new Date(day, month, year);
    }

    public String toString() {
        return "Membership No: " + getMembershipNumber() + " | " + "Membership Type: Default" + " | " + "Name: " + getName() + " | " + "Start Date: " + getStartMembershipDate();
    }

    public static Comparator<DefaultMember> Comparator = (o1, o2) -> {
        String name1 = o1.getName().toUpperCase();
        String name2 = o2.getName().toUpperCase();

        return name1.compareTo(name2);
    };
}
