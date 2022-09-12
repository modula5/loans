package io.fourfinanceit.service.impl;

import static java.util.Optional.ofNullable;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.fourfinanceit.repositories.ClientRepository;
import io.fourfinanceit.entity.Client;
import io.fourfinanceit.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> findByCode(String code) {
        return ofNullable(clientRepository.findByCode(code));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
