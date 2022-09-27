package com.comsultant.domain.builder.repository;

import com.comsultant.domain.builder.entity.Builder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuilderRepository extends MongoRepository<Builder, String> {

}
