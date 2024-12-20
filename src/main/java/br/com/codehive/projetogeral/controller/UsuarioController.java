package br.com.codehive.projetogeral.controller;

import br.com.codehive.projetogeral.model.dto.AuthResponse;
import br.com.codehive.projetogeral.model.dto.CreateUsuarioDto;
import br.com.codehive.projetogeral.model.dto.LoginRequest;
import br.com.codehive.projetogeral.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/v1/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/add")
    public ResponseEntity<String> adicionarUsuario(@RequestBody CreateUsuarioDto usuarioDto){
        try{
            var usuario = usuarioService.adicionarUsuario(usuarioDto);
            if(usuario==null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Houve uma falha no cadastro do Usuario!");
            }
            return ResponseEntity.status(HttpStatus.OK).body("Cadastro realizado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito! "+e.getCause().getMessage());
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> findLogin(@RequestBody LoginRequest loginRequest){
        var auth = usuarioService.login(loginRequest);
        if(auth.getToken()!=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(auth);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(auth);
    }

}
