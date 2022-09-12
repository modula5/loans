package io.fourfinanceit.service;

import java.util.Optional;

import io.fourfinanceit.entity.Client;

public interface ClientService {

    Optional<Client> getById(Long id);

    Optional<Client> findByCode(String code);

    Client save(Client client);

}
