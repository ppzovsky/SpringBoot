package br.org.serratec.biblioteca.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.biblioteca.dto.RestApiDto;

@Service
public class RestApiService {

    @Autowired
    RestTemplate restTemplate;

    public RestApiDto findUserById(Integer id) {
        String uri = "https://fakestoreapi.com/users/{id}";
        Map<String, Integer> params = new HashMap<String, Integer>();

        params.put("id", id);

        RestApiDto dto = restTemplate.getForObject(uri, RestApiDto.class, params);

        return dto;
    }

    public List<RestApiDto> findAll(){
        String uri = "https://fakestoreapi.com/users";
        List <RestApiDto> users = new ArrayList<>();

        RestApiDto[] dto = restTemplate.getForObject(uri, RestApiDto[].class);
        for (RestApiDto user : dto){
            users.add(user);
        }
        return users;
    }
}
