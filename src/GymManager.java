import java.util.List;

public interface GymManager {

    void addMember(DefaultMember member);
    void deleteMember(String membershipNumber);
    void printList();
    void sortList();
    void saveList();
    List<DefaultMember> displayList();
    void searchMember(String membershipNumber);
}
