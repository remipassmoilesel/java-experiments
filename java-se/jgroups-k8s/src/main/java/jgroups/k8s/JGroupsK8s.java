package jgroups.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JGroupsK8s {

	// Try: https://github.com/slaskawi/jgroups-dns-ping-example/blob/master/src/main/resources/config-test.xml

	public static void main(String[] args) {
		SpringApplication.run(JGroupsK8s.class, args);
	}

}
