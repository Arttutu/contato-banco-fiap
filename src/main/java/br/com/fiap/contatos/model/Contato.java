package br.com.fiap.contatos.model;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "tbl_contato")
public class Contato {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TBL_CONTATOS_SEQ"
    )
    @SequenceGenerator(
            name = "TBL_CONTATOS_SEQ",
            sequenceName = "TBL_CONTATOS_SEQ",
            allocationSize = 1
    )
    private  Long id;
    private  String nome;
    private String email;
    @Column(name = "dta_nascimento")
    private LocalDate dataNascimento;
    @ManyToOne
    @JoinColumn(name = "tipoContato_id") // chave estrangeira nome, relacionamento bidirecional
    private  TipoContato tipoContato;

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", tipoContato=" + tipoContato +
                '}';
    }
}
