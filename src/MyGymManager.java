import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyGymManager implements GymManager {

    private final List<DefaultMember> memberList = new ArrayList<>();

    @Override
    public void addMember(DefaultMember member) {
        if (memberList.size() == 100) {
            System.out.println("Sorry. No spaces available in the system");
        } else {
            memberList.add(member);
            saveList();
            System.out.println("Number of spaces left in the system: " + (100 - memberList.size()));
        }
    }

    @Override
    public void deleteMember(String membershipNumber) {
        if (memberList.size() == 0) {
            System.out.println("There are no members in the system to delete");
        } else {
            for (DefaultMember member : memberList) {
                if (member.getMembershipNumber().equals(membershipNumber)) {
                    memberList.remove(member);
                    saveList();
                    System.out.println("Number of spaces left in the system: " + (100 - memberList.size()));
                    if (member instanceof StudentMember) {
                        System.out.println("Removed member was a Student Member");
                        break;
                    } else if (member instanceof Over60Member) {
                        System.out.println("Removed member was a Over 60 Member");
                        break;
                    } else {
                        System.out.println("Removed member was a Default Member");
                        break;
                    }
                } else {
                    System.out.println("There were no member with that number in the system");
                }
            }
        }
    }

    @Override
    public void printList() {
        if (memberList.size() == 0) {
            System.out.println("There are no members to print in the system");
        } else {
            for (DefaultMember member : memberList) {
                System.out.println(member);
            }
        }
    }

    @Override
    public void sortList() {
        if (memberList.size() == 0) {
            System.out.println("There are no members to sort in the system");
        } else {
            memberList.sort(DefaultMember.Comparator);
            for (DefaultMember member : memberList) {
                System.out.println(member);
            }
        }
    }

    @Override
    public void saveList() {
        if (memberList.size() != 0) {
            File file = new File("GymManagerSaveFile.txt");
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                for (DefaultMember member : memberList) {
                    oos.writeObject(member);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert fos != null;
                    fos.close();
                    assert oos != null;
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("There are no members in the system to save");
        }
    }

    @Override
    public List<DefaultMember> displayList() {
        return this.memberList;
    }

    @Override
    public void searchMember(String membershipNumber) {
        for (DefaultMember member : memberList) {
            if (member.getMembershipNumber().equals(membershipNumber)) {
                System.out.println(member);
                return;
            }
        }
        System.out.println("There were no member with that number in the system");
    }

    public void readList() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("GymManagerSaveFile.txt");
            ois = new ObjectInputStream(fis);

            boolean check = true;
            while (check) {
                try {
                    DefaultMember member = (DefaultMember) ois.readObject();
                    memberList.add(member);
                } catch (EOFException e) {
                    check = false;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fis != null;
                fis.close();
                assert ois != null;
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
