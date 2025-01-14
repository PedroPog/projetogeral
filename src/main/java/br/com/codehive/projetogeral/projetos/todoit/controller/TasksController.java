package br.com.codehive.projetogeral.projetos.todoit.controller;

import br.com.codehive.projetogeral.config.UtilidadeGerais;
import br.com.codehive.projetogeral.model.dto.RetorneToken;
import br.com.codehive.projetogeral.projetos.todoit.model.TagsModel;
import br.com.codehive.projetogeral.projetos.todoit.model.TasksModel;
import br.com.codehive.projetogeral.projetos.todoit.service.TasksService;
import br.com.codehive.projetogeral.service.UsuarioService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("todoit/tasks")
public class TasksController {

    @Autowired
    private TasksService service;
    @Autowired
    private UsuarioService serviceUser;

    @GetMapping
    public ResponseEntity<?> listar(
            @RequestHeader("Authorization")String authorizationHeader,
            @NonNull @RequestHeader String iduser
    ){
        RetorneToken retorneToken = UtilidadeGerais.validaToken(authorizationHeader);
        if(!retorneToken.isValidacao()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(retorneToken.getResposta());
        UUID idUsuario = UUID.fromString(iduser);
        if(serviceUser.consultarUUid(idUsuario)) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.listarTasks(idUsuario));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionar(
            @RequestHeader("Authorization") String authorizationHeader,
            @NonNull @RequestBody TasksModel tasksModel
    ){
        RetorneToken retorneToken = UtilidadeGerais.validaToken(authorizationHeader);
        if(!retorneToken.isValidacao()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(retorneToken.getResposta());
        var retu = service.adicionarTasks(tasksModel);
        if(retu == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(retu);
    }


}
