package com.example.SimpleDiscussion.Customer;

public interface CustomerDAO {
    public int insertCustomer(Customer c);
    public Customer getCustomer(String username, String pass);
}
