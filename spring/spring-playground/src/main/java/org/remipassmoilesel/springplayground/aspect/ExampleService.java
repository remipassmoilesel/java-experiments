package org.remipassmoilesel.springplayground.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ExampleService {

    private Logger logger = LoggerFactory.getLogger(ExampleService.class);

    public List<String> getList(String item){
        logger.info("GetList called with item: " + item);
        return Arrays.asList(item, item, item, item, item, item);
    }

}
