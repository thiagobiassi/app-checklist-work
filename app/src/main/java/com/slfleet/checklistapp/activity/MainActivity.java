package com.slfleet.checklistapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


import com.slfleet.checklistapp.R;
import com.slfleet.checklistapp.adapter.TarefaAdapter;
import com.slfleet.checklistapp.helper.RecyclerItemClickListener;
import com.slfleet.checklistapp.helper.TarefaDAO;
import com.slfleet.checklistapp.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas;
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configurar recycler
        recyclerView = findViewById(R.id.recyclerView);

        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                //Envia tarefa para tela adicionar tarefa
                                Intent intent = new Intent(MainActivity.this, ChecklistActivity.class);

                                // Recuperando nome da tarefa
                                Tarefa tarefaSelecionada = listaTarefas.get( position );
                                intent.putExtra("nomeChecklist", tarefaSelecionada.getNomeTarefa());

                                // Recuperando posição da tarefa selecionada
                                intent.putExtra("tarefaSelecionada", tarefaSelecionada );

                                // Iniciando a activity "ChecklistActivity"
                                startActivity( intent );

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                                //Recupera tarefa para deletar
                                tarefaSelecionada = listaTarefas.get( position );

                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                                //Configura título e mensagem
                                dialog.setTitle("Excluir ou Editar");
                                dialog.setMessage("Deseja excluir ou editar a tarefa " + " \"" + tarefaSelecionada.getNomeTarefa()+ "\"" + "?" );

                                dialog.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                                        if ( tarefaDAO.deletar(tarefaSelecionada) ){

                                            carregarListaTarefas();
                                            Toast.makeText(getApplicationContext(),
                                                    "Sucesso ao excluir tarefa!",
                                                    Toast.LENGTH_SHORT).show();

                                        }else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Erro ao excluir tarefa!",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                                dialog.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        // Recuperar tarefa para edição
                                        Intent intent = new Intent(MainActivity.this, AddNewTask.class);
                                        intent.putExtra("tarefaEdicao", tarefaSelecionada);

                                        startActivity(intent);

                                    }
                                });

                                //Exibir dialog
                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarListaTarefas(){

        //Listar tarefas
        TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );
        listaTarefas = tarefaDAO.listar();

        //Configurar um adapter
        tarefaAdapter = new TarefaAdapter( listaTarefas );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter( tarefaAdapter );

    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }

}
