package com.poc.publisherfb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.PubSubAdmin;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableScheduling
public class StartupApplication {

	@Autowired
	PubSubAdmin pubSubAdmin;

	public static void main(String[] args) {
		SpringApplication.run(StartupApplication.class, args);
	}

	@PostConstruct
	public void createSubscription() {
//		pubSubAdmin.deleteSubscription("mySub");
//		pubSubAdmin.deleteTopic("myTopic");
//
		if(pubSubAdmin.getSubscription("mySub") == null) {
			pubSubAdmin.createTopic("myTopic");
			pubSubAdmin.createSubscription("mySub", "myTopic");
		}
	}

}
