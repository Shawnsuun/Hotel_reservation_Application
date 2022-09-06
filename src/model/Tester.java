package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("first","last", "j@domain.com");
        System.out.println(customer);

        //Customer wrongcustomer = new Customer("first","last", "email");
        //System.out.println(wrongcustomer);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        IRoom testroom = new Room("101", 100.0, RoomType.SINGLE);
        Date checkindate = new Date(2022,1,2);
        Date checkoutdate = new Date(2022,1,5);
        Reservation wrongreservation = new Reservation(customer, testroom, checkindate,checkoutdate);
        System.out.println(wrongreservation);
    }
}
