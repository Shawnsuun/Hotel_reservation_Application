package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AdminResource {

    ReservationService reservationservice = ReservationService.getInstance();
    CustomerService customerservice = CustomerService.getInstance();

    private static AdminResource adminresource;
    private AdminResource() {
        reservationservice.setdefultrooms();
    }

    public static AdminResource getInstance() {
        if (Objects.isNull(adminresource)) {
            adminresource = new AdminResource();
        }
        return adminresource;
    }

    public Customer getCustomer(String email) {
        return customerservice.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationservice.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        reservationservice.printAllRooms();
        return reservationservice.rooms;
    }

    public Collection<Customer> getAllCustomers() {
        customerservice.printAllCustomers();
        return reservationservice.mapofreservation.keySet();
    }

    public void displayAllReservations() {
        reservationservice.printReservations(reservationservice.mapofreservation.values());
    }
}
