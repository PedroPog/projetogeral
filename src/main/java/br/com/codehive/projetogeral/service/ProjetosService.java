package br.com.codehive.projetogeral.service;

import br.com.codehive.projetogeral.model.Projetos;
import br.com.codehive.projetogeral.model.dto.RetornGeneric;
import br.com.codehive.projetogeral.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetosService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projetos> listarProjetos(){
        return projetoRepository.findAll();
    }

    public RetornGeneric adicionarProjeto(Projetos projetos){
        RetornGeneric retornGeneric = new RetornGeneric();
        if(projetos.getNameProjeto().isEmpty()||projetos.getNameProjeto()==null){
            retornGeneric.setDescricao("Nome do projeto invalido!");
            retornGeneric.setStatus(false);
            return retornGeneric;
        }
        if(projetos.getUrl().isEmpty()||projetos.getUrl()==null){
            retornGeneric.setDescricao("Url do projeto invalido!");
            retornGeneric.setStatus(false);
            return retornGeneric;
        }
        if(projetos.getLinguagemProgramacao().isEmpty()||projetos.getLinguagemProgramacao()==null){
            retornGeneric.setDescricao("Linguagem invalido!");
            retornGeneric.setStatus(false);
            return retornGeneric;
        }
        Projetos projetos1 = projetoRepository.save(projetos);
        if(projetos1!=null){
            retornGeneric.setStatus(true);
            retornGeneric.setDescricao("Projeto Criado!");
        }else{
            retornGeneric.setStatus(false);
            retornGeneric.setDescricao("Falha!");
        }
        return retornGeneric;
    }

}
