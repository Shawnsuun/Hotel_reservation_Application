package service;

import model.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationService {
    public Map<Customer, Reservation> mapofreservation = new HashMap<Customer, Reservation>();;
    public Set<IRoom> rooms = new HashSet<IRoom>();

    SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
    private static ReservationService reservationservice;

    private ReservationService() {}

    public static ReservationService getInstance() {
        if (Objects.isNull(reservationservice)) {
            reservationservice = new ReservationService();
        }
        return reservationservice;
    }

    public void addRoom(IRoom addroom) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(addroom.getRoomNumber())) {
                System.out.println("Room " + room.getRoomNumber() + " exists and fails to be added");
                return;
            }
        }
        rooms.add(addroom);
    }

    public IRoom getRoom(String RoomID) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(RoomID)) {
                return room;
            }
        }
        throw new IllegalArgumentException("Room " + RoomID + " not found!");
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availablerooms = this.findRooms(checkInDate, checkOutDate);
        for (IRoom aroom : availablerooms) {
            if (aroom.equals(room)) {
                Reservation newreservation = new Reservation(customer, room, checkInDate, checkOutDate);
                customer = new Customer(customer.getFirstname(), customer.getLastname(), customer.getEmail());
                mapofreservation.put(customer, newreservation);
                return newreservation;
            }
        }
        System.out.println("Room " + room.getRoomNumber() + " is currently not available");
        throw new IllegalArgumentException();
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Set<IRoom> availablerooms = new HashSet<IRoom>();
        availablerooms.addAll(rooms);
        for (Reservation reservation : mapofreservation.values()) {
            if ((reservation.checkInDate.compareTo(checkInDate) >= 0 && reservation.checkInDate.compareTo(checkOutDate) < 0)
                    || ((reservation.checkOutDate.compareTo(checkInDate) > 0 && reservation.checkOutDate.compareTo(checkOutDate) <= 0))
                    || ((reservation.checkInDate.compareTo(checkInDate) <= 0 && reservation.checkOutDate.compareTo(checkOutDate) >= 0))) {
                availablerooms.remove(reservation.room);
            }
        }
        if (availablerooms.isEmpty()) {
            this.findAlternativeRooms(checkInDate, checkOutDate);
        }
        return availablerooms;
    }

    public boolean findAlternativeRooms(Date checkInDate, Date checkOutDate) {
        Date newcheckInDate = DateChange(checkInDate, 7);
        Date newcheckOutDate = DateChange(checkOutDate, 7);

        Collection<IRoom> newavailablerooms = this.findRooms(newcheckInDate, newcheckOutDate);
        System.out.println("No available Room in choosing date");
        System.out.println("Here are some alternative option in " + dateformat.format(newcheckInDate) + " - " + dateformat.format(newcheckOutDate));
        Printer<IRoom> roomprinter = new Printer<IRoom>();
        roomprinter.PrintAll(newavailablerooms,"");
        return !newavailablerooms.isEmpty();
    }

    public Date DateChange(Date date, int d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, d);
        Date newDate = calendar.getTime();
        return newDate;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return mapofreservation.values();
    }

    public void printReservations(Collection<Reservation> reservations) {
        Printer<Reservation> reservationprinter = new Printer<Reservation>();
        reservationprinter.PrintAll(reservations,"No reservation found");
    }

    public void printAllRooms() {
        Printer<IRoom> roomprinter = new Printer<IRoom>();
        roomprinter.PrintAll(rooms,"no available room");
    }

    static class Printer<T> {
        void PrintAll(Collection<T> c, String warning) {
            if (c.isEmpty()) {
                System.out.println(warning);
            }
            for (T t : c) {
                System.out.println(t);
            }
        }
    }

    public void setdefultrooms(){
        IRoom hiddenroom1 = new Room("hiddenroom1",125.0, RoomType.DOUBLE);
        IRoom hiddenroom2 = new Room("hiddenroom2",125.0, RoomType.SINGLE);
        IRoom hiddenroom3 = new FreeRoom("hiddenroom3",123.0, RoomType.SINGLE);
        rooms.add(hiddenroom1);
        rooms.add(hiddenroom2);
        rooms.add(hiddenroom3);
    }
}
