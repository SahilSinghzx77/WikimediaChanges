package net.javaproject.springboot.repository;

import net.javaproject.springboot.entity.WikimediaDataElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaElasticRepository extends ElasticsearchRepository<WikimediaDataElastic, String> {
}
