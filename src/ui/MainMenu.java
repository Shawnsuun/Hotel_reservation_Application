package ui;

import api.AdminResource;
import api.HotelResource;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import model.*;

public class MainMenu {

    private HotelResource hotelresource = HotelResource.getInstance();
    private AdminMenu adminmenu = AdminMenu.getInstance();
    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
    private static MainMenu mainmenu;
    boolean optionrunning;

    private MainMenu() {}

    public static MainMenu getInstance() {
        if (Objects.isNull(mainmenu)) {
            mainmenu = new MainMenu();
        }
        return mainmenu;
    }

    public void runMainMenu() {
        boolean running = true;
        while (running) {
            try {
                mainmenu.printMainMenu();
                int input = Integer.parseInt(scanner.nextLine());
                switch(input) {
                    case 1 :
                        mainmenu.option1();
                        break;
                    case 2 :
                        mainmenu.option2();
                        break;
                    case 3 :
                        mainmenu.option3();
                        break;
                    case 4 :
                        mainmenu.option4();
                        break;
                    case 5 :
                        mainmenu.option5();
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

    public void printMainMenu() {
        System.out.println("Welcome to the hotel reservation system!");
        System.out.println("---------------------------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. admin");
        System.out.println("5. Exit");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Please select a number for the menu option:");
    }

    private void option1() {
        boolean optionrunning = true;

        while (optionrunning) {
            try {
                System.out.println("Enter check in date mm/dd/yyyy");
                Date checkindate = dateformat.parse(scanner.nextLine());
                System.out.println("Enter check out date mm/dd/yyyy");
                Date checkoutdate = dateformat.parse(scanner.nextLine());
                if (checkindate.compareTo(checkoutdate) >= 0) {
                    System.out.println("Check out date should be at least 1 day after the check in date.");
                    throw new IllegalArgumentException();
                }
                for (IRoom room : hotelresource.findARoom(checkindate, checkoutdate)) {
                    System.out.println(room);
                }
                System.out.println("Would you like to book a room? y/n");
                String input_yn = scanner.nextLine();
                while (!input_yn.equalsIgnoreCase("y") && !input_yn.equalsIgnoreCase("n")) {
                    System.out.println("Please enter y or n");
                    input_yn = scanner.nextLine();
                }
                if (input_yn.equalsIgnoreCase("y")) {
                    System.out.println("Do you hava an account with us? y/n");
                    input_yn = scanner.nextLine();
                    while (!input_yn.equalsIgnoreCase("y") && !input_yn.equalsIgnoreCase("n")) {
                        System.out.println("Please enter y or n");
                        input_yn = scanner.nextLine();
                    }
                    String email;
                    if (input_yn.equalsIgnoreCase("y")) {
                        System.out.println("Enter email format:name@domin.xxx");
                        email = scanner.nextLine();
                    } else {
                        email = mainmenu.option3();
                    }
                    System.out.println("What room would you like to reserve?");
                    String roomnumber = scanner.nextLine();
                    IRoom room = hotelresource.getRoom(roomnumber);
                    Reservation reservation = hotelresource.bookARoom(email, room, checkindate, checkoutdate);
                    System.out.println("Your reservation has been completed!");
                    System.out.println(reservation);
                    optionrunning = false;
                } else if (input_yn.equalsIgnoreCase("n")) {
                    optionrunning = false;
                }
            } catch(Exception ex) {
                optionrunning = false;
                System.out.println("Invalid input, please try again");
            }
        }
    }

    private void option2() {
        System.out.println("Enter email format:name@domin.xxx");
        String email = scanner.nextLine();
        Collection<Reservation> customersreservations= hotelresource.getCustomersReservations(email);
    }

    private String option3() {
        try {
            optionrunning = true;
            while (optionrunning) {
                System.out.println("Enter email format:name@domin.xxx");
                String email = scanner.nextLine();
                System.out.println("Firstname");
                String first = scanner.nextLine();
                System.out.println("Lastname");
                String last = scanner.nextLine();
                hotelresource.createACustomer(email, first, last);
                System.out.println("user "+ first+ last +" has been created!");
                optionrunning = false;
                return email;
            }
        } catch (Exception ex) {
            optionrunning = false;
            System.out.println("Invalid input, please try again");
        }
        return null;
    }

    private void option4() {
        adminmenu.runAdminMenu();
    }
    private void option5() {
        System.exit(0);
    }
}
