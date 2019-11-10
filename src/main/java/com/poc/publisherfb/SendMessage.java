package com.poc.publisherfb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class SendMessage {

    @Autowired
    private PublishConfig.PubsubOutboundGateway messagingGateway;

    @Autowired
    FirebaseService service;

    @Scheduled(fixedDelay = 30000)
    public void sendMessageOldWay() {
        String msg = "Random: " + Math.random();

        PushNotificationRequest request = new PushNotificationRequest();
        request.setTopic("myTopic");
        request.setTitle("number");
        request.setMessage(msg);

        Map<String, String> data = new HashMap<String, String>() {{
            put("key1", "value1");
        }};

        try {
            service.sendMessage(data, request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        messagingGateway.sendToPubsub(msg);
    }
}
