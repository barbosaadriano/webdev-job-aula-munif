package br.com.avantagem.registrodeconta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Administrador on 11/02/2017.
 */

public class Conexao extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "contas";
    public static final String ID = "id";
    public static final String DESCRICAO = "descricao";
    public static final String VALOR = "valor";
    public static final String STATUS = "status";
    public static final String TIPO = "tipo";

    private static final int VERSAO = 1;

    public Conexao(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + "("
                + ID + " integer primary key autoincrement, "
                + DESCRICAO + " text, "
                + VALOR + " real, "
                + STATUS + " text, "
                + TIPO + " text "
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }
}
