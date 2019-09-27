package pl.potera.hotel.kafka;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class KafkaClient {

    @Value("${kafka.topic}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        addCallback(future, message);
    }

    public void sendMessage(String key, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, key, message);
        addCallback(future, String.format("{key: %s, message: %s}", key, message));
    }

    private void addCallback(ListenableFuture<SendResult<String, String>> future, String record) {
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent record=[" + record +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send record=[" + record + "] due to : " + ex.getMessage());
            }
        });
    }
}
