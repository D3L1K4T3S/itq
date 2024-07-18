package org.example.numbergenerateservice.repository;

import org.example.numbergenerateservice.models.entity.NumberEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NumberRepository extends MongoRepository<NumberEntity, UUID> {
    NumberEntity findByNumber (String number);
}
