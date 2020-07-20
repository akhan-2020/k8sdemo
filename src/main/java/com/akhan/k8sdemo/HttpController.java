package com.akhan.k8sdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    private static final Logger logger = LogManager.getLogger(HttpController.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ConfigProperties config;


    @RequestMapping("/hello")
    public String thing() {
        return "hello";
    }

    @RequestMapping("/find")
    public Customer find(@RequestParam(value = "phone") String phone) {
        // save a couple of customers
        hello();
        Customer customer = repository.findByPhone(phone);
        logger.info("found customer ");
        return customer;
    }

    @Scheduled(fixedDelay = 5000)
    public void hello() {
        System.out.println("The message is: " + config.getMode());
    }

}
