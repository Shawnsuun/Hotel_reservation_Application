package model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    Customer customer;
    public IRoom room;
    public Date checkInDate;
    public Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (checkInDate.compareTo(checkOutDate) >= 0) {
            System.out.println("Check out date should be at least 1 day after the check in date.");
            throw new IllegalArgumentException();
        }
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
        String checkindate = dateformat.format(checkInDate);
        String checkoutdate = dateformat.format(checkOutDate);
        return customer + "; "
                + room + "; "
                + "CheckInDate:" + checkindate + "; "
                + "CheckOutDate:" + checkoutdate;
    }
}
