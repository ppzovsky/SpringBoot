package br.org.serratec.biblioteca.configurations;

import br.org.serratec.biblioteca.exceptions.GlobalExceptionHandler;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Bean
    public ModelMapper modelMapperBean(){
        return new ModelMapper();
    }

}
