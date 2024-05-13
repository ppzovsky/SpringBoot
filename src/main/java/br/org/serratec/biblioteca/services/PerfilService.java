package br.org.serratec.biblioteca.services;

import br.org.serratec.biblioteca.entities.Perfil;
import br.org.serratec.biblioteca.entities.Usuario;
import br.org.serratec.biblioteca.repositories.PerfilRepository;
import br.org.serratec.biblioteca.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    PerfilRepository perfilRepository;
    public List<Perfil> findAll(){
        return perfilRepository.findAll();
    }

    public Perfil findById(Integer id){
        return perfilRepository.findById(id).orElse(null);
    }

    public Perfil save(Perfil perfil){
        return perfilRepository.save(perfil);
    }

    public Perfil update(Perfil perfil){
        return perfilRepository.save(perfil);
    }

    /*public String delete(Integer id){
        if(perfilRepository.existsById(id) == true) {
         Perfil excluir = perfilRepository.findById(id).orElse(null);
            try{
                perfilRepository.deleteById(id);
                return excluir.toString()+ "\nDELETADO COM SUCESSO";
            }catch (Exception e){
                System.out.println(e);
                return "ERRO AO EXCLUIR";
            }
        return "PERFIL NAO ENCONTRADO!";
    }*/

    public Perfil delete(Integer id){
        if(perfilRepository.existsById(id) == true) {
            Perfil excluir = perfilRepository.findById(id).orElse(null);
            try {
                perfilRepository.deleteById(id);
                return excluir;
            }catch (Exception e){
                System.out.println(e);
                return  null;
            }
        }
        return null;
    }

    public List<Usuario> findByPerfil(Integer id){
        Optional<Perfil> perfil = perfilRepository.findById(id);
        return perfil.get().getUsuarios();
    }

    public long count (){
        return perfilRepository.count();
    }
}
