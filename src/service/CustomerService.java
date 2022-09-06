package service;

import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomerService {
    private Map<String, Customer> mapofcustomer = new HashMap<String, Customer>();
    private static CustomerService customerservice;

    private CustomerService() {}

    public static CustomerService getInstance() {
        if (Objects.isNull(customerservice)) {
            customerservice = new CustomerService();
        }
        return customerservice;
    }

    public void addCustomer(String email, String firstName,  String lastName) {
        for (String currentemail : mapofcustomer.keySet()) {
            if (email.equals(currentemail)) {
                System.out.println("Email " + email + " exists and fails to be added");
                return;
            }
        }
        Customer newcustomer = new Customer(firstName, lastName, email);
        mapofcustomer.put(email, newcustomer);
    }

    public Customer getCustomer(String email) {
        for (String currentemail : mapofcustomer.keySet()) {
            if (email.equals(currentemail)) {
                return mapofcustomer.get(email);
            }
        }
        System.out.println("Customer not found");
        throw new IllegalArgumentException();
    }

    public Collection<Customer> getAllCustomers() {
        return mapofcustomer.values();
    }

    public void printAllCustomers() {
        ReservationService.Printer<Customer> customerprinter = new ReservationService.Printer<Customer>();
        customerprinter.PrintAll(this.getAllCustomers(),"No customer found");
    }
}
