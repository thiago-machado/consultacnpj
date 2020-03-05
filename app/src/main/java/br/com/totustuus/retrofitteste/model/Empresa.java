package br.com.totustuus.retrofitteste.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"qsa", "situacao", "bairro",
        "logradouro", "numero", "cep", "municipio",
        "fantasia", "porte", "abertura", "natureza_juridica",
        "uf", "cnpj", "ultima_atualizacao", "status", "efr",
        "situacao_especial", "data_situacao_especial",
        "atividade_principal", "atividades_secundarias",
        "capital_social", "extra", "billing", "message"})
public class Empresa {

    @JsonProperty("data_situacao")
    private String dataSituacao;

    @JsonProperty("motivo_situacao")
    private String motivoSituacao;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("email")
    private String email;

    public String getDataSituacao() {
        return dataSituacao;
    }

    public void setDataSituacao(String dataSituacao) {
        this.dataSituacao = dataSituacao;
    }

    public String getMotivoSituacao() {
        return motivoSituacao;
    }

    public void setMotivoSituacao(String motivoSituacao) {
        this.motivoSituacao = motivoSituacao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
