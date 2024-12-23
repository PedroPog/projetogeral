package br.com.codehive.projetogeral.repository;

import br.com.codehive.projetogeral.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByEmail(String email);

    @Modifying
    @Query(value = "update tb_usuario set role_user = ?1, status_user = ?2 where usuario_id = ?3"
            ,nativeQuery = true)
    int updateRoles(String role,boolean status, UUID usuarioID);

    @Query(value = "SELECT role_user FROM tb_usuario WHERE usuario_id = ?1"
            ,nativeQuery = true)
    String selectRole(UUID usuarioId);
}
