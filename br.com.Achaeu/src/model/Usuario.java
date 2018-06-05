package model;

import java.security.Timestamp;

public class Usuario extends IEntidade{
    private String nome;
    private String email;
    private String senha;
    private int nivel;
    private Timestamp dataCriacao;
    private Timestamp dataAlteracao;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, String senha, int nivel, Timestamp dataCriacao, Timestamp dataAlteracao) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Timestamp getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Timestamp dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + super.getId() + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", nivel=" + nivel + ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + '}';
    }    
}
