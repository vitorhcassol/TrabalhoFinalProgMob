package com.example.easyfinance;

import java.io.Serializable;

public class Transacao implements Serializable {
    private String usuario, nomeTransacao, descricao, data;
    private int idTransacao, valor;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeTransacao() {
        return nomeTransacao;
    }

    public void setNomeTransacao(String nomeTransacao) {
        this.nomeTransacao = nomeTransacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Nome: "+nomeTransacao+"\n"+"'"+descricao+"'"+"\n"+"R$: "+valor+",00"+"\n"+"DATA: "+data;
    }
}
