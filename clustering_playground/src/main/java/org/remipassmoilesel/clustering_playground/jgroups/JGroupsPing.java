package org.remipassmoilesel.clustering_playground.jgroups;

import org.jgroups.JChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JGroupsPing {

    @PostConstruct
    public void setup() throws Exception {
        JChannel channel = new JChannel("src/main/resources/jgroups/udp.xml");
        channel.connect("cluster-playground:cluster-name");
    }

}
