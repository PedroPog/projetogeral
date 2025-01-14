package br.com.codehive.projetogeral.projetos.todoit.repository;

import br.com.codehive.projetogeral.projetos.todoit.model.TagsModel;
import br.com.codehive.projetogeral.projetos.todoit.model.TasksModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TasksRepository extends JpaRepository<TasksModel, UUID> {
    List<TasksModel> findByUsuarioID(UUID iduser);
}
