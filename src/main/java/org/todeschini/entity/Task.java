package org.todeschini.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Artur
 */
@Entity
@Table(name = "task")
public class Task extends PanacheEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @Column(name = "dtCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;
    @Column(name = "dtConclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtConclusao;
    @Column(name = "dtUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUpdate;

    public Task() {
    }

    public Task(Long id) {
        this.id = id;
    }

    public Task(Long id, String titulo, int status, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.status = status;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Date getDtConclusao() {
        return dtConclusao;
    }

    public void setDtConclusao(Date dtConclusao) {
        this.dtConclusao = dtConclusao;
    }

    public Date getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Date dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sempro.Task[ id=" + id + " ]";
    }

}
