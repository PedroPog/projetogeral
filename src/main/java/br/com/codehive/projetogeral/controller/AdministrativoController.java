package br.com.codehive.projetogeral.controller;

import br.com.codehive.projetogeral.config.UtilidadeGerais;
import br.com.codehive.projetogeral.model.dto.RetorneToken;
import br.com.codehive.projetogeral.model.dto.UpdateUserDto;
import br.com.codehive.projetogeral.model.dto.UsuarioDto;
import br.com.codehive.projetogeral.service.AdministrativoService;
import br.com.codehive.projetogeral.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/adm")
public class AdministrativoController {

    @Autowired
    private AdministrativoService administrativoService;


    @GetMapping("/list_usuarios")
    public ResponseEntity<?> validaToken(@RequestHeader("Authorization") String authorizationHeader,
                                         @RequestHeader("role") String role){
        if(role.equalsIgnoreCase("Adm")){//! Estou fazendo essa verificação dupla para testar falha
            RetorneToken retorneToken = UtilidadeGerais.validaToken(authorizationHeader);
            if(!retorneToken.isValidacao()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(retorneToken.getResposta());
            if(!administrativoService.retornRole(retorneToken.getResposta())
                    .equalsIgnoreCase("Adm")){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
            }
            List<UsuarioDto> usuarioDtos = administrativoService.listUsuarios();
            if(usuarioDtos.isEmpty()||usuarioDtos==null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(usuarioDtos);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }

    @PutMapping("/update_user")
    public ResponseEntity<?> updateUsuario(@RequestHeader("Authorization") String authorizationHeader,
                                           @RequestBody UpdateUserDto updateUserDto){
        RetorneToken retorneToken = UtilidadeGerais.validaToken(authorizationHeader);
        if(!retorneToken.isValidacao()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(retorneToken.getResposta());
        if(!administrativoService.retornRole(retorneToken.getResposta())
                .equalsIgnoreCase("Adm")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retorneToken.getResposta());
        }

        if(administrativoService.updateUsuarios(
                updateUserDto.getRole(),
                updateUserDto.isStatus(),
                updateUserDto.getUsuarioId()
                )==1){
            return ResponseEntity.ok("Atualizado!");
        }
        return ResponseEntity.badRequest().body("Não foi possivel atualizar!");
    }
}
