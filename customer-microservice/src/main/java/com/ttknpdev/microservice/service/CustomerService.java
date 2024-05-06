package com.ttknpdev.microservice.service;

import com.ttknpdev.microservice.repository.ServiceRepository;
import com.ttknpdev.microservice.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ServiceRepository<Customer> {

    private final List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
        customers.addAll(List.of(
                new Customer(1L,"Mark Ryder",(short)33,'B'),
                new Customer(2L,"Kevin Owner",(short)31,'A'),
                new Customer(3L,"Json Slider",(short)30,'C')
                ));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findById(Long pk) {
        // Customer customer = customers.stream().filter(found -> found.getCid() == pk).findFirst().orElse(null);
        return customers
                .stream()
                .filter(customer -> customer.getCid() == pk)
                .findFirst() // if found it will return customer
                .orElse(null); // if not will return null
    }

    @Override
    public Boolean save(Customer obj) {
        boolean check = customers
                .stream()
                .anyMatch(customer -> customer.getCid() != obj.getCid()); // *** if customer.getCid() != obj.getCid() will return false

            // mean if [check == true] ? [if true returns customers.add(obj)] : [else returns false]
            // return condition ? if valueA : else valueB;
        return check ? customers.add(obj) : false;
    }

    @Override
    public Boolean edit(Customer obj, Long pk) {
        Customer customer = customers
                .stream()
                .filter(customerOnList -> customerOnList.getCid() == pk).findFirst().orElse(null);
        if (customer != null) {
            customers.remove(customer); // remove by obj
            // System.out.println(customers);
            customer.setFullname(obj.getFullname());
            customer.setAge(obj.getAge());
            customer.setLevel(obj.getLevel());
            customers.add(customer);
            return true;
        }
        return false;
    }

    @Override
    public Boolean remove(Long pk) {
        Customer customer = customers
                .stream()
                .filter(customerOnList -> customerOnList.getCid() == pk).findFirst().orElse(null);
        return customer != null ? customers.remove(customer) : false;
    }

    /*public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        System.out.println(customerService.findAll());
        System.out.println(customerService.edit(new Customer(0L,"Max Slider",(short)31,'B'),1L));
        System.out.println(customerService.findAll());
    }*/
}
