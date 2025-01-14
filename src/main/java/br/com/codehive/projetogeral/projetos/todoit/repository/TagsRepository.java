package br.com.codehive.projetogeral.projetos.todoit.repository;

import br.com.codehive.projetogeral.projetos.todoit.model.TagsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TagsRepository extends JpaRepository<TagsModel, UUID> {

    /**
     * Busca todas as tags de um usuário específico.
     *
     * @param usuarioID UUID do usuário.
     * @return Lista de TagsModel associadas ao usuário.
     */
    List<TagsModel> findByUsuarioID(UUID usuarioID);

    /**
     * Busca tag de um usuário específico.
     *
     * @param usuarioID UUID do usuário.
     * @param uuidTag UUID do Tag.
     * @return Lista de TagsModel associadas ao usuário.
     */
    TagsModel findByUsuarioIDAndTagId(UUID usuarioID, UUID uuidTag);
}
