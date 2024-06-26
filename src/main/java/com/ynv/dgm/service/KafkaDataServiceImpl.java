package com.ynv.dgm.service;

import com.ynv.dgm.model.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final KafkaSender<String, Object> sender;

    @Override
    public void send(Data data) {

        String topic = switch (data.getMeasurementType()) {
            case TEMPERATURE -> "data_temperature";
            case VOLTAGE -> "data_voltage";
            case POWER -> "data_power";
        };

        sender.send(
                Mono.just(
                        SenderRecord.create(
                                topic,
                                0,
                                System.currentTimeMillis(),
                                String.valueOf(data.hashCode()),
                                data,
                                null
                        )
                )
        ).subscribe();
    }
}
