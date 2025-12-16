package com.upt.pt.api.dto;

public class NotificacaoDTO {

    private String id;
    private String mensagem;
    private boolean lida;
    private String data; // já formatada

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public boolean isLida() { return lida; }
    public void setLida(boolean lida) { this.lida = lida; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
