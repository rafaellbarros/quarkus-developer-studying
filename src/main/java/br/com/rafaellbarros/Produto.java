package br.com.rafaellbarros;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Produto extends PanacheEntity {

    public String nome;

    public BigDecimal valor;

    @CreationTimestamp
    public Date dataCriacao;

    @CreationTimestamp
    public Date dataAtualizacao;
}
