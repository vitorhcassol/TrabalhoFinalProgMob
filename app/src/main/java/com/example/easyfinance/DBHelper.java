package com.example.easyfinance;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // ============ INFORMAÇÕES DO DATABASE =========
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "easyfinance.db";

    // ============= MOLDE DAS TABELAS ==========

    //TABELA USUÁRIO
    private static final String TABLE_USUARIO = "usuario";
    private static final String COL_USERNAME = "username";
    private static final String COL_NOME = "nome";
    private static final String COL_SENHA = "senha";

    //TABELA TRANSAÇÃO
    private static final String TABLE_TRANSACAO = "transacao";
    private static final String COL_IDTRANSACAO = "idTransacao";
    private static final String COL_USUARIO = "usuario";
    private static final String COL_NOMETRANSACAO = "nomeTransacao";
    private static final String COL_VALOR = "valor";
    private static final String COL_DESCRICAO = "descricao";
    private static final String COL_DATA = "data";

    // =========== SETTAR A CRIAÇÃO DAS TABELAS ===============

    //CRIAÇÃO DA TABELA USUÁRIO
    private static final String TABLE_CREATE_USUARIO ="create table " + TABLE_USUARIO + "(" +
            COL_USERNAME + " String primary key, " +
            COL_NOME + " text not null, " +
            COL_SENHA + " text not null);";

    //CRIAÇÃO DA TABELA TRANSAÇÃO
    private static final String TABLE_CREATE_TRANSACAO ="create table " + TABLE_TRANSACAO + "(" +
            COL_IDTRANSACAO + " integer primary key autoincrement, " +
            COL_NOMETRANSACAO + " text not null, " +
            COL_VALOR + " integer not null, " +
            COL_DESCRICAO + " text, " +
            COL_DATA + " text not null, " +
            COL_USUARIO + " text not null references " + TABLE_USUARIO + "(" + COL_USERNAME + "));";


    // ========== EXECUTAR CRIAÇÃO DAS TABELAS ===========
    SQLiteDatabase db;
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USUARIO);
        db.execSQL(TABLE_CREATE_TRANSACAO);
        this.db = db;
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryUm = "DROP TABLE IF EXISTS " + TABLE_USUARIO;
        db.execSQL(queryUm);
        String queryDois = "DROP TABLE IF EXISTS " + TABLE_TRANSACAO;
        db.execSQL(queryDois);
        this.onCreate(db);
    }

    // ======== MÉTODOS DA CLASSE USUARIO ==========

    //Método de cadastro de um usuário ---- implementar na tela de cadastro
    public void insereUsuario(Usuario u){
        db = this.getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(COL_USERNAME, u.getUsername());
            values.put(COL_NOME, u.getNome());
            values.put(COL_SENHA, u.getSenha());
            db.insertOrThrow(TABLE_USUARIO, null, values);
            db.setTransactionSuccessful();
        }catch (Exception e) {
            Log.d(TAG, "Erro ao inserir usuário na tabela");
        }finally {
            db.endTransaction();
        }
    }

    //busca senha --- implementar na tela de cadastro e login
    public String buscaSenha(String username) {
        db=this.getReadableDatabase();
        String query = String.format("SELECT %s FROM %s WHERE %s = ?",
                                    COL_SENHA, TABLE_USUARIO, COL_USERNAME);
        String senha = "senha não encontrada";
        db.beginTransaction();
        try{
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(username)});
            try {
                if(cursor.moveToFirst()){
                    senha=cursor.getString(0);
                    db.setTransactionSuccessful();
                }
            }finally {
                if(cursor!=null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "Usuário não encontrado");
        } finally {
            db.endTransaction();
        }
        return senha;
    }

    //busca um username ---- uso geral
    public Boolean buscarUsername(String username) {
        db = this.getReadableDatabase();
        String query = String.format("SELECT %s FROM %s WHERE %s = ?",
                                    COL_USERNAME, TABLE_USUARIO, COL_USERNAME);
        Boolean valor = false;
        db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(username)});
            try {
                if(cursor.moveToFirst()){
                    valor = true;
                    db.setTransactionSuccessful();
                }
            } finally {
                if(cursor!=null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }catch (Exception e){
            Log.d(TAG, "Usuário não encontrado");
        }  finally {
            db.endTransaction();
        }
        return valor;
    }

    //busca nome do usuário --- implementar na tela de perfil
    public String buscarNomeUsuario(String username) {
        db = this.getReadableDatabase();
        String query = String.format("SELECT %s FROM %s WHERE %s = ?",
                                    COL_NOME, TABLE_USUARIO, COL_USERNAME);
        String nome = "nome não encontrado";
        db.beginTransaction();
        try{
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(username)});
            try {
                if(cursor.moveToFirst()) {
                    nome = cursor.getString(0);
                    db.setTransactionSuccessful();
                }
            }finally {
                if(cursor!=null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        } catch (Exception e){
            Log.d(TAG, "Usuário não encontrado");
        }  finally {
            db.endTransaction();
        }
        return nome;
    }

    //atualiza os dados de um usuário um usuário ---- implementar na tela de editar perfil
    public long atualizarUsuario (Usuario u) {
        long retornoDB;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_USERNAME, u.getNome());
        values.put(COL_NOME, u.getNome());
        values.put(COL_SENHA, u.getSenha());

        String[] args = {String.valueOf(u.getUsername())};
        retornoDB = db.update(TABLE_USUARIO, values, "username=?", args);
        db.close();
        return retornoDB;
    }

    //deleta a conta de um usuario --- implementar na tela de editar perfil
    public long excluiUsuario (Usuario u) {
        long retornoDB;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(u.getUsername())};
        retornoDB = db.delete(TABLE_USUARIO, COL_USERNAME+ "=?", args);
        db.close();
        return retornoDB;
    }

    // ======== MÉTODOS DA CLASSE TRANSAÇÃO ==========

    //insere uma transação no banco
    public void insereTransacao (Transacao t) {
        db = this.getReadableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(COL_IDTRANSACAO, t.getIdTransacao());
            values.put(COL_NOMETRANSACAO, t.getNomeTransacao());
            values.put(COL_VALOR, t.getValor());
            values.put(COL_DESCRICAO, t.getDescricao());
            values.put(COL_DATA, t.getData());
            values.put(COL_USUARIO, t.getUsuario());
            db.insertOrThrow(TABLE_TRANSACAO, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Erro ao tentar inserir uma transação no banco de dados");
        } finally {
            db.endTransaction();
        }
    }

    //busca transação
    public ArrayList<Transacao> listaTransacoes() {
        String[] colunas = {COL_NOMETRANSACAO, COL_VALOR, COL_DESCRICAO, COL_DATA, COL_USUARIO};
        Cursor cursor = getReadableDatabase().query(TABLE_TRANSACAO, colunas,
                null, null, null, null, "upper(data)", null);
        ArrayList<Transacao> lista = new ArrayList<Transacao>();
        while (cursor.moveToFirst()){
            Transacao t = new Transacao();
            t.setIdTransacao(cursor.getInt(0));
            t.setNomeTransacao(cursor.getString(1));
            t.setValor(cursor.getInt(2));
            t.setDescricao(cursor.getString(3));
            t.setData(cursor.getString(4));
            t.setUsuario(cursor.getString(5));
        }
        return lista;
    }

    //

    //===============================================================================
    /**public ArrayList<Transacao> listaTransacoesD(Usuario u) {
        String nome = u.getNome();
        String query = String.format("SELECT * FROM %s WHERE %s = ?",TABLE_TRANSACAO, COL_USERNAME);
        db.beginTransaction();
        ArrayList<Transacao> lista = new ArrayList<Transacao>();
        try {
            Cursor cursor = db.rawQuery(query, new String[]{nome});
            while (cursor.moveToFirst()){
                Transacao t = new Transacao();
                t.setIdTransacao(cursor.getInt(0));
                t.setNomeTransacao(cursor.getString(1));
                t.setValor(cursor.getInt(2));
                t.setDescricao(cursor.getString(3));
                t.setData(cursor.getString(4));
                t.setUsuario(cursor.getString(5));
                lista.add(t);
                }

        } catch (Exception e){
            Log.d(TAG, "Erro");
        } finally {
            db.endTransaction();
        }

        return lista;
    }**/
    //=================================================================================
}
