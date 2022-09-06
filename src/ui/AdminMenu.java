package ui;

import api.AdminResource;
import model.FreeRoom;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {

    private AdminResource adminresource = AdminResource.getInstance();
    Scanner scanner = new Scanner(System.in);

    boolean adminrunning;
    boolean optionrunning;
    private static AdminMenu adminmenu;

    private AdminMenu() {}

    public static AdminMenu getInstance() {
        if (Objects.isNull(adminmenu)) {
            adminmenu = new AdminMenu();
        }
        return adminmenu;
    }

    public void runAdminMenu() {
        adminrunning = true;
        while (adminrunning) {
            try {
                adminmenu.printAdminMenu();
                int input = Integer.parseInt(scanner.nextLine());
                switch(input) {
                    case 1 :
                        adminmenu.option1();
                        break;
                    case 2 :
                        adminmenu.option2();
                        break;
                    case 3 :
                        adminmenu.option3();
                        break;
                    case 4 :
                        adminmenu.option4();
                        break;
                    case 5 :
                        adminmenu.option5();
                        break;
                    default :
                        System.out.println("Please select a number between 1 and 5");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid input");
            }
        }
    }

    public void printAdminMenu() {
        System.out.println("Admin menu");
        System.out.println("---------------------------------------------------------------");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to main menu");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Please select a number for the admin option:");
    }

    private void option1() {
        System.out.println("Here are all customers");
        System.out.println("---------------------------------------------------------------");
        adminresource.getAllCustomers();
        System.out.println("---------------------------------------------------------------");
        System.out.println("press enter to back to menu");
        scanner.nextLine();
    }

    private void option2() {
        System.out.println("Here are all rooms");
        System.out.println("---------------------------------------------------------------");
        adminresource.getAllRooms();
        System.out.println("---------------------------------------------------------------");
        System.out.println("press enter to back to menu");
        scanner.nextLine();
    }

    private void option3() {
        System.out.println("Here are all the reservations");
        System.out.println("---------------------------------------------------------------");
        adminresource.displayAllReservations();
        System.out.println("---------------------------------------------------------------");
        System.out.println("press enter to back to menu");
        scanner.nextLine();
    }
    List<IRoom> rooms = new ArrayList<IRoom>();
    private void option4() {
        try {
            optionrunning = true;
            while (optionrunning) {
                System.out.println("Enter room number");
                String roomnumber = scanner.nextLine();
                System.out.println("Enter price per night");
                Double price = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter room type 1 for single bed. 2 for double bed");
                int input = Integer.parseInt(scanner.nextLine());
                while (input != 1 && input != 2) {
                    System.out.println("Please select a number between 1 and 2");
                    input = Integer.parseInt(scanner.nextLine());
                }
                RoomType enumeration;
                if (input == 1) {
                    enumeration = RoomType.SINGLE;
                } else {
                    enumeration = RoomType.DOUBLE;
                }

                IRoom addroom;
                if (price == 0.0) {
                    addroom = new FreeRoom(roomnumber, price, enumeration);
                } else {
                    addroom = new Room(roomnumber, price, enumeration);
                }
                rooms.add(addroom);

                System.out.println("Would you like to add another room? y/n");
                String input_yn = scanner.nextLine();
                while (!input_yn.equalsIgnoreCase("y") && !input_yn.equalsIgnoreCase("n")) {
                    System.out.println("Please enter y or n");
                    input_yn = scanner.nextLine();
                }
                if (input_yn.equalsIgnoreCase("y")) {
                    adminmenu.option4();
                } else if (input_yn.equalsIgnoreCase("n")) {
                    adminresource.addRoom(rooms);
                    rooms.clear();
                    optionrunning = false;
                }
            }
        } catch (Exception ex) {
            rooms.clear();
            optionrunning = false;
            System.out.println("Invalid input, please try again");
        }
    }

    private void option5() {
        adminrunning = false;
    }
}
