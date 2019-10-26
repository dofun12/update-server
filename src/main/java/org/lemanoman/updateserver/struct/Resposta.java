package org.lemanoman.updateserver.struct;

public class Resposta {
    private String mensagem;
    private Object resposta;
    private boolean sucesso = false;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getResposta() {
        return resposta;
    }

    public void setResposta(Object resposta) {
        this.resposta = resposta;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }
}
