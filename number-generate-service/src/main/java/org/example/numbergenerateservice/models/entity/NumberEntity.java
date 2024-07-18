package org.example.numbergenerateservice.models.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class NumberEntity {
    @Id
    private UUID id;

    @Indexed(unique = true)
    private String number;

    private Date creationDate;

    public NumberEntity(String number) {
        this.id = UUID.randomUUID();
        this.number = number;
        this.creationDate = new Date();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
