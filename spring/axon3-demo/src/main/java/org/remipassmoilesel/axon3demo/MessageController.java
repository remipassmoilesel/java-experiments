package org.remipassmoilesel.axon3demo;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.remipassmoilesel.axon3demo.axon3.CreateMessageCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class MessageController {

    @Autowired
    private CommandGateway commandGateway;

    @ResponseBody
    @RequestMapping(value = "/create-message", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CreateMessageCommand createMessage(@RequestParam String content) {
        String itemId = UUID.randomUUID().toString();
        CreateMessageCommand command = new CreateMessageCommand(itemId, content);
        commandGateway.send(command);
        return command;
    }

}
