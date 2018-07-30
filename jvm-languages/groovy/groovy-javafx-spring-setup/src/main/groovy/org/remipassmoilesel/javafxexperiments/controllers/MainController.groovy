package org.remipassmoilesel.javafxexperiments.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class MainController {

    @Autowired
    DateService dateService;

    static Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController() {

    }

    @FXML
    void closeProgram(ActionEvent event) {
        logger.info("Closing application !");
        logger.info("Date: ${dateService.getDate()}");
        System.exit(0);
    }

}
