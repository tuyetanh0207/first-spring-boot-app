package com.tuyetanh0207;
import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.autoconfigure.orm.jpa.*;
import java.util.List;
import java.util.Optional;
//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan
@SpringBootApplication
@RestController
@RequestMapping("api/v1/customer")
public class Main {
    private final  CustomerRepository customerRepository;
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public static void main(String[] args) {
      //  System.setProperty("spring.profiles.default", "production");

        SpringApplication.run(Main.class, args);
    }

    @GetMapping()
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){}
    @PostMapping()
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setAge(request.age());
        customer.setEmail(request.email());
      //  customer.setId(request.id());
        customerRepository.save(customer);

    }
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }
    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            // Update customer information based on the request
            if (request.name() != null) {
                existingCustomer.setName(request.name());
            }

            if (request.age() != null) {
                existingCustomer.setAge(request.age());
            }

            if (request.email() != null) {
                existingCustomer.setEmail(request.email());
            }

            // Save the updated customer
            customerRepository.save(existingCustomer);

            //return ResponseEntity.ok("Customer updated successfully");
        } else {
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }


}



