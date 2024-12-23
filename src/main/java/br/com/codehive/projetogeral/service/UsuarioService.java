package br.com.codehive.projetogeral.service;

import br.com.codehive.projetogeral.config.JwtTokenUtil;
import br.com.codehive.projetogeral.model.Usuario;
import br.com.codehive.projetogeral.model.dto.AuthResponse;
import br.com.codehive.projetogeral.model.dto.CreateUsuarioDto;
import br.com.codehive.projetogeral.model.dto.LoginRequest;
import br.com.codehive.projetogeral.model.dto.UpdateUsuarioDto;
import br.com.codehive.projetogeral.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario adicionarUsuario(CreateUsuarioDto usuario){
        var model = new Usuario(
                usuario.getUsername(),
                usuario.getEmail(),
                passwordEncoder.encode(usuario.getSenha()),
                Instant.now()
        );
        return usuarioRepository.save(model);
    }

    public ResponseEntity<String> atualizacaoUsuario(UpdateUsuarioDto usuarioDto){
        var existUser = usuarioRepository.findById(usuarioDto.getUsuarioID());
        ResponseEntity<String> response;
        if(existUser.isPresent()){
            if(usuarioDto.getUsername()==null||usuarioDto.getUsername().isEmpty()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario não pode ser vazio!");
            }
            if(usuarioDto.getEmail()==null||usuarioDto.getEmail().isEmpty()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email não pode ser Vazio!");
            }
            if(usuarioDto.getSenha()==null||usuarioDto.getSenha().isEmpty()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Senha não pode ser Vazio!");
            }
            var model = new Usuario(
                    usuarioDto.getUsuarioID(),
                    usuarioDto.getUsername(),
                    usuarioDto.getEmail(),
                    passwordEncoder.encode(usuarioDto.getSenha()),
                    existUser.get().getCriacaoTimestamp(),
                    Instant.now(),
                    usuarioDto.isStatus(),
                    existUser.get().getRole()
            );
            if(usuarioRepository.save(model).getUsuarioID()!=null){
                response = ResponseEntity.status(HttpStatus.OK).body("Usuario atualizado!");
            }else{
                response = ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possivel atualizar!");
            }
        }else{
            response = ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario não consta na base do banco!");
        }
        return response;
    }

    public AuthResponse login(LoginRequest loginRequest){
        var retornoAuth = new AuthResponse();
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());
        if(usuario == null){
            retornoAuth.setMessage("Email não encontrado!");
            return retornoAuth;
        }
        if(!usuario.isStatus()){
            retornoAuth.setMessage("Conta Bloqueado!");
            return retornoAuth;
        }
        var verificarSenha = passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha());
        if(verificarSenha){
            retornoAuth.setUsername(usuario.getUsername());
            retornoAuth.setToken("Bearer "+JwtTokenUtil.generateToken(usuario.getUsuarioID()));
            retornoAuth.setRole(usuario.getRole());
            retornoAuth.setMessage("Usuario Aceito!");
        }else{
            retornoAuth.setMessage("Senha incorreta!");
        }
        return retornoAuth;
    }

}
