package dev1.alexkjam64.SpringBootProject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev1.alexkjam64.SpringBootProject.service.ClientPhoneService;

@RestController
@RequestMapping("/batch")
public class ClientPhoneBatchController {
    private final ClientPhoneService clientPhoneService;

    public ClientPhoneBatchController(ClientPhoneService clientPhoneService){
        this.clientPhoneService = clientPhoneService;
    }

    @PostMapping
    public ResponseEntity<?> batchPhone(@RequestBody List<Integer> ids){
        return ResponseEntity.ok(clientPhoneService.retrieveAllPhones(ids));
    }
}
