package br.com.codehive.projetogeral.service;

import br.com.codehive.projetogeral.model.Usuario;
import br.com.codehive.projetogeral.model.dto.UsuarioDto;
import br.com.codehive.projetogeral.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AdministrativoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDto> listUsuarios(){
        return usuarioRepository.findAll()
                .stream()
                .map(ac -> new UsuarioDto(ac.getUsuarioID(),ac.getUsername(),
                        ac.getEmail(),ac.getRole(),ac.isStatus(),ac.getAtualizaoTimestamp().toString(),
                        ac.getCriacaoTimestamp().toString())).toList();
    }

    public String retornRole(String usuarioId){
        return usuarioRepository.selectRole(UUID.fromString(usuarioId));
    }

    @Transactional
    public int updateUsuarios(String role,boolean status,String uuid){
        return usuarioRepository.updateRoles(role,status, UUID.fromString(uuid));
    }


}
