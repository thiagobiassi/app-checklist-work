package com.slfleet.checklistapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.slfleet.checklistapp.R;
import com.slfleet.checklistapp.helper.DatabaseHelperChecklist;
import com.slfleet.checklistapp.helper.TarefaDAO;
import com.slfleet.checklistapp.model.Tarefa;
import com.slfleet.checklistapp.model.ToDoModel;

public class AddNewTask extends AppCompatActivity {

    private TextInputEditText editChecklist;
    private Tarefa tarefaAtual;
    private Long taskId;
    private DatabaseHelperChecklist db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
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
                        Bundle dadosOption = getIntent().getExtras();
                        String optionName = dadosOption.getString("optionName");

                        if (optionName.charAt(0) == 'E'){
                            tarefa.setNomeTarefa("Entrega: " + nomeChecklist);
                        }
                        else if (optionName.charAt(0) == 'T'){
                            tarefa.setNomeTarefa("Troca: " + nomeChecklist);
                        }
                        else if (optionName.charAt(0) == 'R'){
                            tarefa.setNomeTarefa("Rollout: " + nomeChecklist);
                        }
                        else if (optionName.charAt(0) == 'M'){
                            tarefa.setNomeTarefa("Monitor: " + nomeChecklist);
                        }
                        else if (optionName.charAt(0) == 'N'){
                            tarefa.setNomeTarefa("Novo equipamento " + nomeChecklist);
                        }
                        else if (optionName.charAt(0) == 'C'){
                            tarefa.setNomeTarefa("Criar " + nomeChecklist);
                        }
                        else {
                            tarefa.setNomeTarefa(nomeChecklist);
                        }

                        if (tarefaDAO.salvar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);

    }

}
