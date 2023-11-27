package ru.netology.DubrovinVyacheslav.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.DubrovinVyacheslav.OperationHistoryApiApplicationTest;
import ru.netology.DubrovinVyacheslav.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void assertThatCustomerServiceWorksRight() {
        String name = "Vadik";
        int id = 0;
        String password = "qwertyuiop";

        Customer vadik = new Customer(id, name, password);

        assertEquals(new Customer(1, "Spring", "qwertyuiop"), customerService.getCustomer(0));
        assertEquals(new Customer(2, "Boot", "qwertyuiop"), customerService.getCustomer(1));

        customerService.addCustomer(vadik);
        Customer customer = customerService.getCustomer(2);

        assertEquals(vadik, customer);
        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(password, customer.getPassword());
    }
}