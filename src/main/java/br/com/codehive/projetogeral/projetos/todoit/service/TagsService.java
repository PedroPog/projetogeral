package br.com.codehive.projetogeral.projetos.todoit.service;

import br.com.codehive.projetogeral.projetos.todoit.model.TagsModel;
import br.com.codehive.projetogeral.projetos.todoit.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagsService {

    @Autowired
    private TagsRepository repository;

    /**
     * Lista todas as tags cadastradas para um usuário específico.
     *
     * @param idUser UUID do usuário.
     * @return Lista de TagsModel associadas ao usuário.
     */

    public List<TagsModel> listarTags(UUID idUser){
        if(idUser==null){
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }
        return repository.findByUsuarioID(idUser);
    }

    /**
     * Adiciona uma nova tag para um usuário, validando os campos obrigatórios.
     *
     * @param tagsModel Objeto TagsModel a ser salvo.
     * @return Objeto TagsModel salvo no banco de dados.
     */
    public TagsModel adicionarTag(TagsModel tagsModel){
        validarTag(tagsModel);
        return repository.save(tagsModel);
    }

    /**
     * Valida os campos obrigatórios de uma tag antes de salvá-la.
     *
     * @param usuarioID UUid do usuario
     * @param uuidTag UUID da tag a ser consultada
     * @return retorna se existe ou não
     */
    public boolean verificarUuid(UUID uuidTag,UUID usuarioID){
        return repository.findByUsuarioIDAndTagId(usuarioID,uuidTag) == null;
    }

    /**
     * Valida os campos obrigatórios de uma tag antes de salvá-la.
     *
     * @param tagsModel Objeto TagsModel a ser validado.
     */
    private void validarTag(TagsModel tagsModel) {
        if (tagsModel == null) {
            throw new IllegalArgumentException("A tag não pode ser nula.");
        }

        if (tagsModel.getUsuarioID() == null) {
            throw new IllegalArgumentException("O ID do usuário é obrigatório.");
        }

        if (tagsModel.getNome() == null || tagsModel.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da tag é obrigatório.");
        }

        if (tagsModel.getNome().length() > 50) {
            throw new IllegalArgumentException("O nome da tag não pode ter mais de 50 caracteres.");
        }
    }


}
