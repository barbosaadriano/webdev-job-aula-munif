package br.com.avantagem.registrodeconta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 11/02/2017.
 */

public class ContaService {

    private SQLiteDatabase db;
    private Conexao banco;

    public ContaService(Context context) {
        banco = new Conexao(context);
    }

    public boolean salvar(Conta conta) {
        ContentValues valores;
        long resultado = -1;

        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put(Conexao.DESCRICAO, conta.getDescricao());
        valores.put(Conexao.VALOR, conta.getValor());
        valores.put(Conexao.STATUS, conta.getStatus());
        valores.put(Conexao.TIPO, conta.getTipo());


        if (conta.getId() != null && conta.getId() != 0){
            String where = Conexao.ID + " = " + conta.getId();
            resultado = db.update(Conexao.TABELA, valores, where, null);
        } else {
            resultado = db.insert(Conexao.TABELA, null, valores);
        }

        db.close();
        return resultado != -1;
    }

    public boolean remover(Integer id){
        String where = Conexao.ID + " = " + id;
        db = banco.getWritableDatabase();
        int resultado = db.delete(Conexao.TABELA, where, null);
        db.close();
        return resultado != -1;
    }

    public List<Conta> buscar(){
        Cursor dados;
        List<Conta> contas = new ArrayList<>();
        String[] campos =  {Conexao.ID,Conexao.DESCRICAO, Conexao.VALOR, Conexao.TIPO, Conexao.STATUS};

        db = banco.getReadableDatabase();
        dados = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(dados!=null && dados.moveToFirst()){
            do {
                contas.add(new Conta(dados.getInt(0), dados.getString(1), dados.getDouble(2), dados.getString(3),dados.getString(4)));
            } while (dados.moveToNext());
        }

        db.close();
        return contas;
    }

    public Conta getById(Integer id) {
        Cursor dados;
        String[] campos =  {Conexao.ID,Conexao.DESCRICAO, Conexao.VALOR, Conexao.TIPO, Conexao.STATUS};
        String where = Conexao.ID+"=?";
        String[] param = {id.toString()};
        db = banco.getReadableDatabase();
        dados = db.query(banco.TABELA, campos, where, param, null, null, null, null);
        if (dados!=null && dados.moveToFirst()) {
            return  new Conta(dados.getInt(0), dados.getString(1), dados.getDouble(2), dados.getString(3),dados.getString(4));
        } else {
            return null;
        }
    }
}
