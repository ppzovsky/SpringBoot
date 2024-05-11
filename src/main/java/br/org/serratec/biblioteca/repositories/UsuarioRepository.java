package br.org.serratec.biblioteca.repositories;

import br.org.serratec.biblioteca.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
