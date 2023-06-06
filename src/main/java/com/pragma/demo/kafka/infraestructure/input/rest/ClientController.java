package com.pragma.demo.kafka.infraestructure.input.rest;


import com.pragma.demo.kafka.application.dto.request.ClientRequestDTO;
import com.pragma.demo.kafka.application.handler.IClientHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final IClientHandler clientHandler;

    public ClientController(IClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }


    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody ClientRequestDTO clientRequestDto) {
        clientHandler.saveClient(clientRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
