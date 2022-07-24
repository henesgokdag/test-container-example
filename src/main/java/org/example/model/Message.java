package org.example.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.Instant;

@ToString
@Getter
@Document
public class Message {
    private String id;
    private String title;
    private String text;

    public Message(String title, String text) {
        this.title = title;
        this.text = text;
        this.id = title + "_" + Instant.now().toEpochMilli();
    }
}
