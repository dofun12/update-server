package org.lemanoman.updateserver.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Versao")
public class Versao implements Serializable {
    public Versao() {}

    public Versao(String nome) {
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(nullable = false, unique = true)
    private String nome;

    @Column
    private Date dataRelease;

    @Column
    private String status;

    @Column
    private String diretorio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataRelease() {
        return dataRelease;
    }

    public void setDataRelease(Date dataRelease) {
        this.dataRelease = dataRelease;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }
}
