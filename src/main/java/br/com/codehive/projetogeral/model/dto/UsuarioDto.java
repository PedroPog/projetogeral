package br.com.codehive.projetogeral.model.dto;

import java.util.UUID;

public class UsuarioDto {

    private UUID usuarioID;
    private String username;
    private String email;
    private String criacaoTimestamp;
    private String atualizaoTimestamp;
    private boolean status;
    private String role = "Normal";

    public UsuarioDto(UUID usuarioID, String username, String email,String role,
                      boolean status, String atualizaoTimestamp,String criacaoTimestamp) {
        this.usuarioID = usuarioID;
        this.username = username;
        this.email = email;
        this.criacaoTimestamp = criacaoTimestamp;
        this.atualizaoTimestamp = atualizaoTimestamp;
        this.status = status;
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

    public String getCriacaoTimestamp() {
        return criacaoTimestamp;
    }

    public void setCriacaoTimestamp(String criacaoTimestamp) {
        this.criacaoTimestamp = criacaoTimestamp;
    }

    public String getAtualizaoTimestamp() {
        return atualizaoTimestamp;
    }

    public void setAtualizaoTimestamp(String atualizaoTimestamp) {
        this.atualizaoTimestamp = atualizaoTimestamp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
