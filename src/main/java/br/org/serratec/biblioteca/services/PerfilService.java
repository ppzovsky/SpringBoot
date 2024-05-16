package br.org.serratec.biblioteca.services;

import br.org.serratec.biblioteca.dto.PerfilResumidoDTO;
import br.org.serratec.biblioteca.entities.Perfil;
import br.org.serratec.biblioteca.entities.Usuario;
import br.org.serratec.biblioteca.exceptions.EntidadeNotFoundException;
import br.org.serratec.biblioteca.repositories.PerfilRepository;
import br.org.serratec.biblioteca.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmailService emailService;
    public List<Perfil> findAll(){
        return perfilRepository.findAll();
    }
    public List<PerfilResumidoDTO> findAllResumidos(){
        List <Perfil> perfis = perfilRepository.findAll();
        List <PerfilResumidoDTO> perfisDto = new ArrayList<>();

        for (Perfil perfil : perfis){
            PerfilResumidoDTO perfilDTO = new PerfilResumidoDTO();
            perfilDTO.setNome(perfil.getNome());
            perfilDTO.setDescricao(perfil.getDescricao());

            perfisDto.add(perfilDTO);
        }
     return perfisDto;
    }

    public Perfil findById(Integer id){

        return perfilRepository.findById(id).orElseThrow(
                () -> new EntidadeNotFoundException
                        ("Não foi encontrado um Perfil com o id " + id)
        );
    }
    public PerfilResumidoDTO findByIdResumido(Integer id){
        Perfil perfil = perfilRepository.findById(id).orElse(null);
        PerfilResumidoDTO perfilDto = null;

        perfilDto = modelMapper.map(perfil, PerfilResumidoDTO.class);


        emailService.enviarEmail("gugutrembala@gmail.com", "Cadastro", perfilDto.toString());

        return perfilDto;
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

    public List<Usuario> findUsuarioByPerfilId(Integer id){
        Optional<Perfil> perfil = perfilRepository.findById(id);
        return perfil.get().getUsuarios();
    }

    public long count (){
        return perfilRepository.count();
    }
}
