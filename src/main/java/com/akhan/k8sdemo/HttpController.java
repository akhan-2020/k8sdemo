package com.akhan.k8sdemo;

import com.akhan.k8sdemo.config.HttpBinConfig;
import com.akhan.k8sdemo.data.CustomerDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class HttpController {

    private static final Logger logger = LogManager.getLogger(HttpController.class);


    @Autowired
    private CustomerDAO cbrepository;

    @Autowired
    private ConfigProperties config;


    @RequestMapping("/write")
    public String write() {
        Customer cust = new Customer();
        cust.setFirstName("Adnan");
        cust.setId("AK471");
        cust.setLastName("Khan");
        cust.setPhone("214-718-5556");
        cbrepository.create(cust);
        return "hello";
    }

    @PostMapping(path= "/customers", consumes = "application/json", produces = "application/json")
    public String customers(@RequestBody Customer customer) {
        cbrepository.create(customer);
        return customer.getId();
    }

    @RequestMapping("/customer/{id}")
    public Customer find(@PathVariable(value = "id") String id) {
        logger.info("found customer ");
        Optional<Customer> cust = cbrepository.findById(id);
        Customer cust2 = new Customer();
        cust2.setId("0000");
        //return customer;
        return cust.orElse(cust2);
    }

    @RequestMapping("/customers/{firstName}")
    public List<Customer> findByName(@PathVariable(value = "firstName") String firstName) {
        logger.info("found customer ");
        List<Customer> cust = cbrepository.findByFirstName(firstName);
        //return customer;
        return cust;
    }

    @Autowired
    HttpBinConfig httpBinConfig;




    /*
      executes external httP APi based on value in property file
     */
    @RequestMapping("/httpbin")
    public String executeHttpBinApi() {
        RestTemplate restTemplate = new RestTemplate();

        logger.info("Executing httpBin API " + httpBinConfig.getUrl());
        ResponseEntity<String> response
                = restTemplate.getForEntity(httpBinConfig.getUrl() + httpBinConfig.getApi(), String.class);
        return response.getBody();
    }


    @Scheduled(fixedDelay = 10000)
    public void hello() {
        System.out.println("The message is: " + config.getMode());
    }

}
