//package net.javaproject.springboot;
//
//import net.javaproject.springboot.entity.WikimediaData;
//import net.javaproject.springboot.repository.WikimediaDataRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaDatabaseConsumer {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
//
//    private WikimediaDataRepository dataRepository;
//
//    public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
//        this.dataRepository = dataRepository;
//    }
//
//
//    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
//    public void consume(String eventMessage){
//        LOGGER.info(String.format("Event Message received -> %s",eventMessage));
//
//        WikimediaData wikimediaData = new WikimediaData();
//        wikimediaData.setWikiEventData(eventMessage);
//
//        dataRepository.save(wikimediaData);
//    }
//
//}


package net.javaproject.springboot;

import net.javaproject.springboot.entity.WikimediaData;
import net.javaproject.springboot.entity.WikimediaDataElastic;
import net.javaproject.springboot.repository.WikimediaDataRepository;
import net.javaproject.springboot.repository.WikimediaElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private final WikimediaDataRepository dataRepository;
    private final WikimediaElasticRepository elasticRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository, WikimediaElasticRepository elasticRepository) {
        this.dataRepository = dataRepository;
        this.elasticRepository = elasticRepository;
    }

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message received -> %s", eventMessage));

        // Store in MySQL
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        dataRepository.save(wikimediaData);

        // Store in Elasticsearch
        WikimediaDataElastic wikimediaDataElastic = new WikimediaDataElastic();
        wikimediaDataElastic.setData(eventMessage);
        elasticRepository.save(wikimediaDataElastic);

        LOGGER.info("Message saved to both MySQL and Elasticsearch");
    }
}
