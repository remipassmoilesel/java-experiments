package org.remipassmoilesel.springplayground.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AspectController {

    @Autowired
    private ExampleService exampleService;

    @RequestMapping(path = "/service", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<String> getResponseFromService(@RequestParam String item){
        return exampleService.getList(item);
    }

}
