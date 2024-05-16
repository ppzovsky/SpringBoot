package br.org.serratec.biblioteca.controller;

import br.org.serratec.biblioteca.dto.PerfilResumidoDTO;
import br.org.serratec.biblioteca.entities.Perfil;
import br.org.serratec.biblioteca.entities.Usuario;
import br.org.serratec.biblioteca.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/perfil")
public class PerfilController {
    @Autowired
    PerfilService perfilService;

    @GetMapping
    public ResponseEntity<List<Perfil>> resgataPerfis() {
        return new ResponseEntity<>(perfilService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/perfil-resumido")
    public ResponseEntity<List<PerfilResumidoDTO>> resgataPerfisResumido() {
        return new ResponseEntity<>(perfilService.findAllResumidos(), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Perfil> findById(@PathVariable Integer id){
        Perfil perfil = perfilService.findById(id);
        if (perfil == null){
            return new ResponseEntity<>(perfil, HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(perfil, HttpStatus.OK);
    }

    @GetMapping ("/perfil-resumido/{id}")
    public ResponseEntity<PerfilResumidoDTO> findByIdResumido(@PathVariable Integer id) {
        PerfilResumidoDTO perfilDto = null;
//        try{
            perfilDto = perfilService.findByIdResumido(id);
//        }catch (IllegalArgumentException e){
//            throw new IllegalArgumentException("Erro ao buscar perfil", e);
//        }

        if (perfilDto == null){
            return new ResponseEntity<>(perfilDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(perfilDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Perfil> criaPerfil(@RequestBody Perfil perfil) {
        return new ResponseEntity<>(perfilService.save(perfil), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Perfil> update(@RequestBody Perfil perfil) {
        return new ResponseEntity<>(perfilService.update(perfil), HttpStatus.OK);
    }

    /*@DeleteMapping("/{id}")  retorna uma string de aviso
    public ResponseEntity<String> deletaPerfil(@PathVariable Integer id) {
        return new ResponseEntity<>(perfilService.delete(id), HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Perfil> deletaPerfil(@PathVariable Integer id) {
        Perfil perfil = perfilService.findById(id);
        if (perfil == null){
            return new ResponseEntity<>(perfil, HttpStatus.NOT_FOUND);
        }
        else {
            perfilService.delete(id);
            return new ResponseEntity<>(perfil, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/{listusers}")
    public ResponseEntity<List<Usuario>> findUsuarioByPerfilId(@PathVariable Integer id){
        return new ResponseEntity<>(perfilService.findUsuarioByPerfilId(id), HttpStatus.OK);
    }
}
