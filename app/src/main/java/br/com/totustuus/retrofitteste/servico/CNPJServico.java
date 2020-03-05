package br.com.totustuus.retrofitteste.servico;

import br.com.totustuus.retrofitteste.model.Empresa;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CNPJServico {

    String CHAVE_CNPJ = "cnpj";
    String PARAMETRO_BUSCA = "v1/cnpj/{cnpj}";

    @GET(PARAMETRO_BUSCA)
    Call<Empresa> buscarCNPJ(@Path(CHAVE_CNPJ) String cnpj);
}
