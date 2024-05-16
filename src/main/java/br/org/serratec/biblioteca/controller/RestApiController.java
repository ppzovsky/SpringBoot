package br.org.serratec.biblioteca.controller;


import java.util.List;

import br.org.serratec.biblioteca.dto.RestApiDto;
import br.org.serratec.biblioteca.services.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RestApiController {

    @Autowired
    RestApiService restApiService;

    @GetMapping("/finduser/{id}")
    public ResponseEntity<RestApiDto> findUserById(@PathVariable Integer id) {

        return new
                ResponseEntity<>(restApiService.findUserById(id), HttpStatus.OK);

    }

}
