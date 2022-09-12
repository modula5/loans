package io.fourfinanceit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.fourfinanceit.repositories.ClientRepository;
import io.fourfinanceit.entity.Client;
import io.fourfinanceit.service.impl.ClientServiceImpl;

@ExtendWith(MockitoExtension.class)
class ClientServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void getByIdFoundTest() {
        Client client2 = new Client("code", "name", "lastName");
        client2.setId(1L);
        Optional<Client> optionalClient = Optional.of(client2);
        when(clientRepository.findById(1L)).thenReturn(optionalClient);

        assertEquals(optionalClient, clientService.getById(1L));

        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void getByIdNotFoundTest() {
        assertEquals(Optional.empty(), clientService.getById(1L));

        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void saveTest() {
        Client client = new Client("code", "name", "lastName");

        clientService.save(client);

        verify(clientRepository, times(1)).save(client);
    }
}
