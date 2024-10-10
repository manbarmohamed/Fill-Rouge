package com.fil.rouge.controller;

import com.fil.rouge.dto.ClientDto;
import com.fil.rouge.model.Client;
import com.fil.rouge.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients() {
        List<ClientDto> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }


    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long clientId) {
        ClientDto client = clientService.findById(clientId);
        return ResponseEntity.ok(client);
    }
}
