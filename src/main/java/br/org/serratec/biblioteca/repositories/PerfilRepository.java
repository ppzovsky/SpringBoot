package br.org.serratec.biblioteca.repositories;

import br.org.serratec.biblioteca.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository
        extends JpaRepository<Perfil, Integer>{

}