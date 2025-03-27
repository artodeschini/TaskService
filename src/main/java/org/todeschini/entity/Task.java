package org.todeschini.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Artur
 */
@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task extends PanacheEntity implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

    @Column(name = "criada")
    private LocalDate criada;

    @Basic(optional = true)
    @Column(name = "finalizada")
    private LocalDate finalizada;

    public Task(Long id) {
        this.id = id;
    }

    public Task(Long id, String titulo, int status, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.status = status;
        this.descricao = descricao;
    }

    public Task(String titulo, int status, String descricao) {
        this.titulo = titulo;
        this.status = status;
        this.descricao = descricao;
    }
}
