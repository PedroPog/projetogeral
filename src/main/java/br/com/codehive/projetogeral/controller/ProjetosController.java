package br.com.codehive.projetogeral.controller;

import br.com.codehive.projetogeral.config.UtilidadeGerais;
import br.com.codehive.projetogeral.model.Projetos;
import br.com.codehive.projetogeral.model.dto.RetornGeneric;
import br.com.codehive.projetogeral.model.dto.RetorneToken;
import br.com.codehive.projetogeral.model.dto.UpdateUserDto;
import br.com.codehive.projetogeral.model.dto.UsuarioDto;
import br.com.codehive.projetogeral.service.AdministrativoService;
import br.com.codehive.projetogeral.service.ProjetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/projetos")
public class ProjetosController {

    @Autowired
    private ProjetosService projetosService;
    @Autowired
    private AdministrativoService administrativoService;

    @GetMapping("/list_projetos")
    public ResponseEntity<?> listarProjetos(){
        List<Projetos> projetos = projetosService.listarProjetos();
        if(projetos.isEmpty()||projetos==null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(projetos);
    }

    @PostMapping("/adicionar_projeto")
    public ResponseEntity<?> adicionarProjeto(@RequestHeader("Authorization") String authorizationHeader,
                                           @RequestBody Projetos projetos){
        RetorneToken retorneToken = UtilidadeGerais.validaToken(authorizationHeader);
        if(!retorneToken.isValidacao()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(retorneToken.getResposta());
        if(!administrativoService.retornRole(retorneToken.getResposta())
                .equalsIgnoreCase("Adm")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retorneToken.getResposta());
        }

        RetornGeneric retornGeneric = projetosService.adicionarProjeto(projetos);
        if(!retornGeneric.isStatus()){
            return ResponseEntity.badRequest().body(retornGeneric.getDescricao());
        }
        return ResponseEntity.ok(retornGeneric.getDescricao());
    }

}
