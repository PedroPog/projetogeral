package br.com.codehive.projetogeral.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "usuario_id")
    private UUID usuarioID;

    @Column(name = "username",nullable = false)
    @ColumnTransformer(write = "UPPER(?)")
    private String username;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    private Instant criacaoTimestamp;

    @UpdateTimestamp
    private Instant atualizaoTimestamp;

    @Column(name = "status_user")
    private boolean status;

    @Column(name = "role_user")
    private String role;

    public Usuario() {
    }

    //? Constructor para create
    public Usuario(String username, String email, String senha, Instant criacaoTimestamp) {
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.criacaoTimestamp = criacaoTimestamp;
        this.status = true;
        this.role = "Normal";
    }
    //? Constructor para update
    public Usuario(UUID usuarioID, String username, String email, String senha,boolean status, Instant atualizaoTimestamp) {
        this.usuarioID = usuarioID;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.status = status;
        this.atualizaoTimestamp = atualizaoTimestamp;
    }
    //? constructor para atualizar status/bloquear
    public Usuario(UUID usuarioID, Instant atualizaoTimestamp, boolean status) {
        this.usuarioID = usuarioID;
        this.atualizaoTimestamp = atualizaoTimestamp;
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Instant getCriacaoTimestamp() {
        return criacaoTimestamp;
    }

    public void setCriacaoTimestamp(Instant criacaoTimestamp) {
        this.criacaoTimestamp = criacaoTimestamp;
    }

    public Instant getAtualizaoTimestamp() {
        return atualizaoTimestamp;
    }

    public void setAtualizaoTimestamp(Instant atualizaoTimestamp) {
        this.atualizaoTimestamp = atualizaoTimestamp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
