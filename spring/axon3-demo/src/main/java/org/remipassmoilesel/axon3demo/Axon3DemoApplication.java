package org.remipassmoilesel.axon3demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Axon3DemoApplication {

    private static final Logger logger = Logger.getLogger(Axon3DemoApplication.class.getName());

    public static void main(String[] args) {
        logger.severe("Starting application");
        SpringApplication.run(Axon3DemoApplication.class, args);
    }

}
