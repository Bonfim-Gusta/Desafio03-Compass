package com.desafio03.notify.infra.repository;

import com.desafio03.notify.UsernameOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyRepository extends MongoRepository<UsernameOperation, String> {
}
