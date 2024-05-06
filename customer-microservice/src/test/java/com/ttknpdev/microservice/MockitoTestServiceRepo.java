package com.ttknpdev.microservice;

import com.ttknpdev.microservice.entity.Customer;
import com.ttknpdev.microservice.repository.ServiceRepository;
import com.ttknpdev.microservice.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MockitoTestServiceRepo {

    @Mock
    public CustomerService customerService;
    public ServiceRepository<Customer> serviceRepository;

    @BeforeEach
    public void initialUseCase() {
        /*
            *** Remember No need to create new object because
            @Mock annotation it will do auto
            So meaning it will do like below
            *** customerService = new CustomerService();
            So now i can use customerService it at all method!!
        */
        serviceRepository = customerService;
    }

    @Test
    public void callFindAll() {
        /*
        So, we have to tell Mockito to return something when serviceRepository.findAll() is called. (abs layer)
        We do this with the static when() method.
        */
        Mockito
                .when(customerService.findAll())
                .thenReturn(List.of(
                        new Customer(1L, "Mark Ryder", (short) 33, 'B'),
                        new Customer(2L, "Kevin Owner", (short) 31, 'A'),
                        new Customer(3L, "Json Slider", (short) 30, 'C')
                ));
        /*
        Assume !
        Now users will call serviceRepository.findAll()
        it means user gonna request /reads this path So,
        In process you set up it to call serviceRepository.findAll();
        */
        List<Customer> customers = serviceRepository.findAll();
        ///
        Assertions.assertEquals("Mark Ryder", customers.get(0).getFullname()); // if it's equal it is not error
        ///
        Mockito.verify(customerService, Mockito.times(1)).findAll(); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
    }

    @Test
    public void callFindFindById() {

        Mockito
                .when(customerService.findById(2L))
                .thenReturn(new Customer(2L, "Kevin Owner", (short) 31, 'A'));
        /*
        Assume !
        Now users will call serviceRepository.findAll()
        it means user gonna request /reads this path So,
        In process you set up it to call serviceRepository.findAll();
        */
        Customer customer = serviceRepository.findById(2L); // 1 times that you called mocked list
        ///
        Assertions.assertEquals("Kevin Owner", customer.getFullname()); // if it's equal it is not error
        ///
        Mockito.verify(customerService, Mockito.times(1)).findById(2L); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
    }

    // doReturn() it works for call save(...) , edit(...) , remove(...)

    @Test
    public void callSave() {
        Customer customer = new Customer(5L, "Kevin Ryder", (short) 31, 'A');
        /* ******
            When do this way
            Mockito.when(customerService.save(Mockito.any(Customer.class))).then(AdditionalAnswers.returnsFirstArg());
            got error => org.mockito.exceptions.misusing.WrongTypeOfReturnValue:
                         The argument of type 'Customer' cannot be returned because the following
                         method should return the type 'Boolean'
            And I solve it by this way (use doReturn(<return value>).when(<object>).<method of object>(<arg if you have>))
        */
         Mockito
                 .doReturn(true)
                 .when(customerService)
                 .save(customer);
        /*
        Assume !
        In process you set up it to call serviceRepository.save(Customer);
        */
        // is called only once on the mocked list object. It’s the same as calling with times(1) argument with verify method.
        Boolean create = serviceRepository.save(customer);
        ///
        Assertions.assertEquals(true, create); // if it's equal it is not error
        // If we want to make sure 1 times a method is called verify()
        Mockito.verify(customerService, Mockito.times(1)).save(customer); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
    }

    @Test
    public void callRemove() {
        Mockito
                .doReturn(true)
                .when(customerService)
                .remove(1L);
        /*
        Assume !
        In process you set up it to call serviceRepository.save(Customer);
        */
        Boolean remove = serviceRepository.remove(1L);
        ///
        Assertions.assertEquals(true, remove); // if it's equal it is not error
        ///
        // Mockito.verify(customerService, Mockito.times(0)).save(customer); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
        Mockito.verify(customerService, Mockito.times(1)).remove(1L);
    }

    @Test
    public void callEdit() {
        Customer customer = new Customer(0L, "Kevin Ryder", (short) 31, 'A');
        Long cid = 1L;
        Mockito
                .doReturn(true)
                .when(customerService)
                .edit(customer,cid);
        /*
        Assume !
        In process you set up it to call serviceRepository.edit(Customer,Long);
        */
        Boolean update = serviceRepository.edit(customer,1L);
        ///
        Assertions.assertEquals(true, update); // if it's equal it is not error
        Mockito.verify(customerService, Mockito.times(1)).edit(customer,cid); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
    }
}
