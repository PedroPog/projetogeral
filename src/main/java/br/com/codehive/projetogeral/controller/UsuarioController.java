package br.com.codehive.projetogeral.controller;

import br.com.codehive.projetogeral.config.JwtTokenUtil;
import br.com.codehive.projetogeral.config.UtilidadeGerais;
import br.com.codehive.projetogeral.model.dto.*;
import br.com.codehive.projetogeral.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.UUID;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
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

    @PutMapping("/update/{usuarioId}")
    public ResponseEntity<String> updateUsuario(@PathVariable("usuarioId") String usuarioId,
                                                @RequestBody UpdateUsuarioDto usuarioDto){
        if(usuarioId==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario não autotizado!");
        }
        usuarioDto.setUsuarioID(UUID.fromString(usuarioId));
        return usuarioService.atualizacaoUsuario(usuarioDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> findLogin(@RequestBody LoginRequest loginRequest){
        var auth = usuarioService.login(loginRequest);
        if(auth.getToken()!=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(auth);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(auth);
    }

    @GetMapping("/auth/validar-token")
    public ResponseEntity<Boolean> validaToken(@RequestHeader("Authorization") String authorizationHeader){
        RetorneToken retorneToken = UtilidadeGerais.validaToken(authorizationHeader);
        if(!retorneToken.isValidacao()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(retorneToken.isValidacao());

        return ResponseEntity.ok(retorneToken.isValidacao());

    }

}
