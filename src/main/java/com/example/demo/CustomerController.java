package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("")
    public List<Customer> selectAll(){
        List<Customer> customerList = repository.findAll();
        return customerList;
    }

    @GetMapping("/{id}")
    public Customer getSpecificCustomer(@PathVariable String id){
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/search/lastName/{lastName}")
    public List<Customer> searchByLastName(@PathVariable String lastName){
        return repository.findByLastName(lastName);
    }

    @GetMapping("/search/firstname/{firstName}")
    public Customer searchByFirstName(@PathVariable String firstName){
        return repository.findByFirstName(firstName);
    }

    @PostMapping("")
    public Customer insert(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PatchMapping("/{id}")
    public void update(@RequestParam String id, @RequestBody Customer customer) {
        Customer oldCustomer = repository.findById(id).orElse(null);
        if(customer.getFirstName() != null) {
            oldCustomer.setFirstName(customer.getFirstName());
        }
        if(customer.getLastName() != null) {
            oldCustomer.setLastName(customer.getLastName());
        }
        repository.save(oldCustomer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Customer deleteCustomer = repository.findById(id).orElse(null);
        repository.delete(deleteCustomer);
    }

}
