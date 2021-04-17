import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Scanner;

public class UserInterface extends Application {

    private final static MyGymManager manager = new MyGymManager();

    @Override
    public void start(Stage primaryStage) {
        manager.readList();
        System.out.println("-------------------My Gym Manager-------------------");
        System.out.println("Select an option from the menu");
        System.out.println("1 - Add a new member");
        System.out.println("2 - Delete a member");
        System.out.println("3 - Print the list of members");
        System.out.println("4 - Sort the list");
        System.out.println("5 - Save the list to a file");
        System.out.println("6 - Open GymManager GUI");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number relevant to the option: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid input: ");
            scanner.nextLine();
        }
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                addMember();
                break;
            case 2:
                deleteMember();
                break;
            case 3:
                manager.printList();
                break;
            case 4:
                manager.sortList();
                break;
            case 5:
                manager.saveList();
                break;
            case 6:
                primaryStage.show();
                break;
            default:
                System.out.print("Invalid input");
        }

        TableView<DefaultMember> tableView = new TableView<>();
        ObservableList<DefaultMember> data = FXCollections.observableArrayList(manager.displayList());
        tableView.setLayoutY(37.0);
        tableView.setMaxHeight(500);
        tableView.setMaxWidth(610);

        TableColumn<DefaultMember, String> tableColumn1 = new TableColumn<>();
        tableColumn1.setMinWidth(135);
        tableColumn1.setText("Membership Number");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));
        TableColumn<DefaultMember, String> tableColumn2 = new TableColumn<>();
        tableColumn2.setMinWidth(143);
        tableColumn2.setText("Name");
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<DefaultMember, String> tableColumn3 = new TableColumn<>();
        tableColumn3.setMinWidth(142);
        tableColumn3.setText("Start Membership Date");
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("startMembershipDate"));
        TableColumn<DefaultMember, String> tableColumn4 = new TableColumn<>();
        tableColumn4.setMinWidth(129);
        tableColumn4.setText("School Name");
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        TableColumn<DefaultMember, String> tableColumn5 = new TableColumn<>();
        tableColumn5.setMinWidth(59);
        tableColumn5.setText("Age");
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("age"));

        tableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn4, tableColumn5);
        tableView.setItems(data);

        Label label = new Label("My Gym Manager");
        label.setLayoutX(18);
        label.setLayoutY(4);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(Font.font("SansSerif Bold", FontWeight.BOLD, 20));

        TextField textField = new TextField();
        textField.setPromptText("Membership Number");
        textField.setLayoutX(370);
        textField.setLayoutY(6);
        textField.setMinWidth(85);
        textField.setMinHeight(25);

        Button button = new Button("Search");
        button.setLayoutX(530);
        button.setLayoutY(6);
        button.setFont(Font.font("SansSerif Bold", FontWeight.BOLD, 12));

        button.setOnAction(event -> manager.searchMember(textField.getText()));

        AnchorPane root = new AnchorPane();
        root.getChildren().add(tableView);
        root.getChildren().add(label);
        root.getChildren().add(textField);
        root.getChildren().add(button);
        primaryStage.setTitle("My Gym Manager");
        primaryStage.setScene(new Scene(root, 610, 438));
    }

    private static void addMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n1 - Default Member");
        System.out.println("2 - Student Member");
        System.out.println("3 - Over 60 Member");
        System.out.print("Select the type of member: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid input: ");
            scanner.nextLine();
        }
        int type = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Membership Number: ");
        String membershipNumber = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Start Year: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid input: ");
            scanner.nextLine();
        }
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Start Month: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid input: ");
            scanner.nextLine();
        }
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Start Day: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid input: ");
            scanner.nextLine();
        }
        int day = Integer.parseInt(scanner.nextLine());
        DefaultMember member = null;
        switch (type) {
            case 1:
                member = new DefaultMember(membershipNumber, name, day, month, year);
                break;
            case 2: {
                System.out.print("Enter School Name: ");
                String schoolName = scanner.nextLine();
                member = new StudentMember(membershipNumber, name, day, month, year, schoolName);
                break;
            }
            case 3: {
                System.out.print("Enter Age: ");
                int age = Integer.parseInt(scanner.nextLine());
                member = new Over60Member(membershipNumber, name, day, month, year, age);
                break;
            }
            default:
                System.out.println("Invalid input");
        }
        manager.addMember(member);
    }

    private static void deleteMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter membership number: ");
        String membershipNumber = scanner.nextLine();
        manager.deleteMember(membershipNumber);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
