package com.slfleet.listadetarefas.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.slfleet.listadetarefas.R;
import com.slfleet.listadetarefas.helper.TarefaDAO;
import com.slfleet.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editChecklist;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        editChecklist = findViewById(R.id.textTarefa);

        // Recuperar checklist, caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaEdicao");

        // Configurar checklist na caixa de texto
        if (tarefaAtual != null){

            editChecklist.setText(tarefaAtual.getNomeTarefa());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar:
                // Executa ação para o item salvar
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if (tarefaAtual != null){ //edição
                    String nomeChecklist = editChecklist.getText().toString();
                    if (!nomeChecklist.isEmpty()){

                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeChecklist);
                        tarefa.setId(tarefaAtual.getId());

                        // Atualizar no banco de dados
                        if (tarefaDAO.atualizar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar tarefa!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar tarefa!", Toast.LENGTH_SHORT).show();
                        }

                    }

                }else { //salvar

                    String nomeChecklist = editChecklist.getText().toString();
                    if (!nomeChecklist.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeChecklist);

                        if (tarefaDAO.salvar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                        }

                        finish();
                    }

                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
