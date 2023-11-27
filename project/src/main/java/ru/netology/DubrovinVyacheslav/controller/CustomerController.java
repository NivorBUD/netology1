package ru.netology.DubrovinVyacheslav.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.DubrovinVyacheslav.entity.Customer;
import ru.netology.DubrovinVyacheslav.entity.dto.CustomerDTO;
import ru.netology.DubrovinVyacheslav.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerDTO postCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new CustomerDTO(customer.getId(), customer.getName());
    }

    @GetMapping
    public Iterable<CustomerDTO> getCustomers() {
        List<CustomerDTO> result = new ArrayList<>();
        for (Customer customer : customerService.getCustomers())
            result.add(new CustomerDTO(customer.getId(), customer.getName()));
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        for (Customer customer : customerService.getCustomers())
            if (customer.getId().equals(id))
                return new ResponseEntity<>(new CustomerDTO(customer.getId(), customer.getName()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
