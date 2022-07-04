package com.example.easyfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Tela_Historico extends AppCompatActivity {

    private Spinner filter;
    private List<Transacao> transacoes;
    private ListView viewTransacao;
    private Usuario user;
    private Transacao transacao;

    ArrayList<Transacao> arrayListTransacao;
    ArrayAdapter<Transacao> arrayAdapterTransacao;

    DBHelper helper = new DBHelper(this);
    String[] opcoes ={"Mês atual", "3 meses", "6 meses", "1 ano"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historico);

        Intent it=getIntent();
        user = (Usuario) it.getSerializableExtra("chave_user");

        filter = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Tela_Historico.this,
                android.R.layout.simple_spinner_dropdown_item,opcoes);
        filter.setAdapter(adapter);

        viewTransacao = findViewById(R.id.listaItens);
        registerForContextMenu(viewTransacao);   //Menu de contexto

        //Popula o listView com as transações
        /*
        ArrayAdapter<Transacao> adapter_spinner = new ArrayAdapter<Transacao>(this,
                android.R.layout.simple_list_item_1,helper.listaTransacoes(user));
        viewTransacao.setAdapter(adapter_spinner);
         */

        //Interações com os ítens do listView
        viewTransacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Transacao transacaoSelecionada =  (Transacao)
                        arrayAdapterTransacao.getItem(i);
                Intent intent = new Intent(Tela_Historico.this, Tela_EditaTransacao.class);
                intent.putExtra("chave_transacao", transacaoSelecionada);
                startActivity(intent);
            }
        });
        viewTransacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView,View view, int
                    position, long id){
                transacao = arrayAdapterTransacao.getItem(position);
                return false;
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        preencheLista();
    }

    //Popula o lisView de Transações
    public void preencheLista(){
        helper=new DBHelper(Tela_Historico.this);
        arrayListTransacao = helper.listaTransacoes(user);
        helper.close();
        if(viewTransacao!=null){
            arrayAdapterTransacao = new ArrayAdapter<Transacao>(
                    Tela_Historico.this,
                    android.R.layout.simple_list_item_1,
                    arrayListTransacao);
            viewTransacao.setAdapter(arrayAdapterTransacao);
        }
    }

    //Menu de Contexto
    public void onCreateContextMenu(ContextMenu menu, View
            v, ContextMenu.ContextMenuInfo menuInfo){
        MenuItem mDelete = menu.add(Menu.NONE, 1, 1,"Exclui Transação");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoBD=1;
                helper=new DBHelper(Tela_Historico.this);
                retornoBD = helper.excluirTransacao(transacao);
                helper.close();
                if(retornoBD==-1){
                    alert("Erro de exclusão!");
                }
                else{
                    alert("Transação excluída com sucesso!");
                }
                preencheLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //Método de alerta
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
