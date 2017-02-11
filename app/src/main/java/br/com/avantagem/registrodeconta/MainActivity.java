package br.com.avantagem.registrodeconta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    private EditText edDescricao;
    private EditText edValor;
    private RadioButton rbPagar;
    private RadioButton rbReceber;
    private CheckBox ckStatus;
    private ListView listView;
    private List<Conta> lista;

    private boolean editando;
    private Conta contaSelecionada;

    private ContaService servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edDescricao = (EditText) findViewById(R.id.edDescricao);
        edValor = (EditText) findViewById(R.id.edValor);
        rbPagar = (RadioButton) findViewById(R.id.rdPagar);
        rbReceber = (RadioButton) findViewById(R.id.rdReceber);
        ckStatus = (CheckBox) findViewById(R.id.ckFinalizado);
        listView = (ListView) findViewById(R.id.lvLista);
        listView.setOnItemClickListener(this);
        lista = new ArrayList<Conta>();
        servico = new ContaService(getBaseContext());
        contaSelecionada = getConta();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selecionar(position);
    }

    private void limparCampos() {
        edDescricao.setText("");
        edValor.setText("");
        rbPagar.setChecked(true);
        ckStatus.setChecked(false);
    }
    private void preencherValores(){
        contaSelecionada.setDescricao(edDescricao.getText().toString());
        contaSelecionada.setValor(Double.parseDouble(edValor.getText().toString()));
        if (rbPagar.isChecked()) {
            contaSelecionada.setTipo(Conta.TIPO_P);
        } else {
            contaSelecionada.setTipo(Conta.TIPO_R);
        }
        if (ckStatus.isChecked()) {
            contaSelecionada.setStatus(Conta.STATUS_F);
        } else {
            contaSelecionada.setStatus(Conta.STATUS_P);
        }
    }
    private void preencherCampos(){
        edDescricao.setText(contaSelecionada.getDescricao().toString());
        edValor.setText(Double.toString(contaSelecionada.getValor()));
        if (contaSelecionada.getTipo().equals(Conta.TIPO_P)) {
            rbPagar.setChecked(true);
        } else {
            rbReceber.setChecked(true);
        }
        rbPagar.refreshDrawableState();
        rbReceber.refreshDrawableState();
        ckStatus.setChecked(false);
        if (contaSelecionada.getStatus().equals(Conta.STATUS_F)) {
            ckStatus.setChecked(true);
        }
        ckStatus.refreshDrawableState();
    }

    private void limparLista(){
        lista.clear();
    }

    public void novo(View view){
        limparCampos();
        limparLista();
        contaSelecionada = getConta();
    }
    private Conta getConta(){
        return new Conta(null,"",0.0,Conta.TIPO_P,Conta.STATUS_P);
    }
    public void salvar(View view){
        if (validar()) {
            preencherValores();
            servico.salvar(contaSelecionada);
            editando = false;
            listar(view);
            limparCampos();
        }
    }
    private boolean validar() {
        if (edDescricao.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Descrição Vazia!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edValor.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Valor Vazio!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }
    public void remover(View view){
        if (editando) {
            servico.remover(contaSelecionada.getId());
            limparCampos();
            contaSelecionada=getConta();
            listar(view);
        }
    }

    public void listar(View view){
        lista = servico.buscar();
       // ArrayAdapter<Conta> adapter = new ArrayAdapter<Conta>(this,
       //         android.R.layout.simple_list_item_1,lista);
        AdapterAdriano adapter =  new AdapterAdriano(lista,this);
        listView.setAdapter(adapter);
    }

    public void selecionar(int id) {
        contaSelecionada = (Conta) listView.getItemAtPosition(id);
        preencherCampos();
        editando = true;
    }

}
