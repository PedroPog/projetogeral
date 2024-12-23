package br.com.codehive.projetogeral.repository;

import br.com.codehive.projetogeral.model.Projetos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projetos,Long> {
}
