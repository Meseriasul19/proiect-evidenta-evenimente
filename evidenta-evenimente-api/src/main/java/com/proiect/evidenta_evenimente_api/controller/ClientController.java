package com.proiect.evidenta_evenimente_api.controller;

import com.proiect.evidenta_evenimente_api.model.Client;
import com.proiect.evidenta_evenimente_api.repository.ClientRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clienti")
@CrossOrigin(origins = "http://localhost:5173") // Permite conectarea cu React
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Client> getAllClienti() {
        return clientRepository.findAll();
    }

    @org.springframework.web.bind.annotation.PostMapping
    public String createClient(@org.springframework.web.bind.annotation.RequestBody Client client) {
        clientRepository.save(client);
        return "Client adaugat cu succes!";
    }

    // Endpoint pentru ȘTERGERE: DELETE /api/clienti/{id}
    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public String deleteClient(@org.springframework.web.bind.annotation.PathVariable Long id) {
        clientRepository.deleteById(id);
        return "Client sters cu succes!";
    }
}