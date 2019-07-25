package org.remipassmoilesel.clustering_playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClusteringPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClusteringPlaygroundApplication.class, args);
	}

}
