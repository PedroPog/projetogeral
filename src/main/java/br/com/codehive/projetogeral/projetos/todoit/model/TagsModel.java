package br.com.codehive.projetogeral.projetos.todoit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_tags")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tag_id", updatable = false, nullable = false)
    private UUID tagId;

    @Column(length = 50, nullable = true)
    private String nome;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioID;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    //@JsonBackReference
    private Set<TasksModel> tasks = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

}
