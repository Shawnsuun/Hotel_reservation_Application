package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class HotelResource {

    ReservationService reservationservice = ReservationService.getInstance();
    CustomerService customerservice = CustomerService.getInstance();

    private static HotelResource hotelresource;
    private HotelResource() {}

    public static HotelResource getInstance() {
        if (Objects.isNull(hotelresource)) {
            hotelresource = new HotelResource();
        }
        return hotelresource;
    }

    public Customer getCustomer(String email) {
        return customerservice.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerservice.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationservice.getRoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerservice.getCustomer(customerEmail);
        if (reservationservice.findRooms(checkInDate, checkOutDate).isEmpty()
                && reservationservice.findAlternativeRooms(checkInDate, checkOutDate)) {
            return reservationservice.reserveARoom(customer, room, reservationservice.DateChange(checkInDate, 7),
                    reservationservice.DateChange(checkOutDate, 7));
        }
        return reservationservice.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations (String customerEmail) {
        Customer customer = customerservice.getCustomer(customerEmail);
        reservationservice.printReservations(reservationservice.getCustomersReservation(customer));
        return reservationservice.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationservice.findRooms(checkIn, checkOut);
    }
}
