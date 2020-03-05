package br.com.totustuus.retrofitteste.retrofit;

import br.com.totustuus.retrofitteste.servico.CNPJServico;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig(final String url) {

        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public CNPJServico getCNPJServico() {
        return this.retrofit.create(CNPJServico.class);
    }
}
