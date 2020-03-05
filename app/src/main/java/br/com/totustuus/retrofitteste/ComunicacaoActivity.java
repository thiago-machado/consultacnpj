package br.com.totustuus.retrofitteste;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.totustuus.retrofitteste.model.Empresa;
import br.com.totustuus.retrofitteste.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComunicacaoActivity extends AppCompatActivity {

    public static final String URL_CONSULTA_CNPJ = "https://www.receitaws.com.br/";
    public static final String TITULO_APPBAR = "Consulta CNPJ";
    private EditText cnpj;
    private EditText nome;
    private EditText tipo;
    private EditText dataSituacao;
    private EditText motivoSituacao;
    private String cnpjTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicacao);

        setTitle(TITULO_APPBAR);

        configuraCampos();
        configuraBotao();
    }

    private boolean isValidoCNPJ() {
        if(cnpjTexto == null || cnpjTexto.isEmpty() || cnpjTexto.length() != 14) {
            return false;
        } else {
            return true;
        }
    }

    private void configuraCampos() {
        cnpj = findViewById(R.id.acitivity_comunicacao_cnpj);
        nome = findViewById(R.id.acitivity_comunicacao_info_nome);
        tipo = findViewById(R.id.acitivity_comunicacao_info_tipo);
        dataSituacao = findViewById(R.id.acitivity_comunicacao_info_data_situacao);
        motivoSituacao = findViewById(R.id.acitivity_comunicacao_info_motivo_situacao);
    }

    private void configuraBotao() {
        final Button botaoConsulta = findViewById(R.id.activity_comunicacao_botao_consulta);
        botaoConsulta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                configurarCNPJ();
                tratarClique();
            }
        });
    }

    private void tratarClique() {
        if(isValidoCNPJ()) {
            realizarComunicacao();
        } else {
            limparInfoCampos();
            exibirAlerta("CNPJ inválido!", "CNPJ deve ter 14 dígitos.");
        }
    }

    private void configurarCNPJ() {
        cnpjTexto = cnpj.getText().toString();
    }

    private void limparInfoCampos() {
        nome.setText(null);
        tipo.setText(null);
        dataSituacao.setText(null);
        motivoSituacao.setText(null);
    }

    private void realizarComunicacao() {
        Log.i("empresa", "botao apertado...");

        Call<Empresa> call = new RetrofitConfig(URL_CONSULTA_CNPJ).getCNPJServico().buscarCNPJ(cnpjTexto);
        call.enqueue(new Callback<Empresa>() {

            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                tratarSucesso(response);
            }

            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {
                tratarProblema(t);
            }
        });
    }

    private void tratarProblema(Throwable t) {
        Log.e("comunicacao   ", "Erro ao buscar o CNPJ:" + t.getMessage());
        limparInfoCampos();
        exibirAlerta("Problema na consulta!", "Erro: " + t.getMessage());
    }

    private void tratarSucesso(Response<Empresa> response) {
        Log.i("comunicacao", "Comunicação com sucesso");

        Empresa empresa = response.body();
        if(empresa == null) {
            limparInfoCampos();
            exibirAlerta("CNPJ não encontrado!", "O CNPJ utilizado não retornou informações.");
        } else {
            preencherCamposInformacoes(empresa);
        }
    }

    private void preencherCamposInformacoes(Empresa empresa) {
        nome.setText(empresa.getNome());
        tipo.setText(empresa.getTipo());
        dataSituacao.setText(empresa.getDataSituacao());
        motivoSituacao.setText(empresa.getMotivoSituacao());
    }

    private void exibirAlerta(String titulo, String mensagem) {
        new AlertDialog.Builder(this).setTitle(titulo).setMessage(mensagem).create().show();
    }
}
