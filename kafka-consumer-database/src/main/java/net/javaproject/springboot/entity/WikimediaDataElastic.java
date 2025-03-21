package net.javaproject.springboot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "wikimedia")
public class WikimediaDataElastic {

    @Id
    private String id;
    private String data;  // This field will store the event JSON

    public WikimediaDataElastic() {
    }

    public WikimediaDataElastic(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
