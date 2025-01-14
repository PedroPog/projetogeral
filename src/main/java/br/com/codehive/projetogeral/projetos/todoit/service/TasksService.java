package br.com.codehive.projetogeral.projetos.todoit.service;

import br.com.codehive.projetogeral.projetos.todoit.model.TagsModel;
import br.com.codehive.projetogeral.projetos.todoit.model.TasksModel;
import br.com.codehive.projetogeral.projetos.todoit.repository.TagsRepository;
import br.com.codehive.projetogeral.projetos.todoit.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TasksService {

    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private TasksRepository repository;

    public List<TasksModel> listarTasks(UUID iduser){
        if(iduser==null){
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }
        return repository.findByUsuarioID(iduser);
    }

    public TasksModel adicionarTasks(TasksModel model){
        return repository.save(model);
    }

}
