package io.fourfinanceit.repositories;

import org.springframework.data.repository.CrudRepository;

import io.fourfinanceit.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByCode(String code);
}
