package com.slfleet.checklistapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.slfleet.checklistapp.helper.RecyclerItemTouchHelper;
import com.slfleet.checklistapp.helper.DialogCloseListener;
import com.slfleet.checklistapp.R;
import com.slfleet.checklistapp.adapter.ToDoAdapter;
import com.slfleet.checklistapp.helper.DatabaseHelperChecklist;
import com.slfleet.checklistapp.model.Tarefa;
import com.slfleet.checklistapp.model.ToDoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChecklistActivity extends AppCompatActivity implements DialogCloseListener {

    private DatabaseHelperChecklist db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    private Long taskId;
    private TextView nomeChecklist;
    private Button buttonTerm;
    private Button buttonGerar;


    private List<ToDoModel> taskList;
    private List<ToDoModel> taskListFilterAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Direcionar para a activity SendTermActivity
        buttonTerm = findViewById(R.id.buttonTerm);
        buttonTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChecklistActivity.this, SendTermActivity.class);
                startActivity(intent);
            }
        });

        nomeChecklist = findViewById(R.id.nomeChecklist);
        buttonGerar = findViewById(R.id.buttonGerar);

        // Recuperar dados enviados (Nome do Checklist)
        Bundle dados = getIntent().getExtras();
        final String nome = dados.getString("nomeChecklist");

        //Configura valores recuperados
        nomeChecklist.setText(nome);

        Tarefa tarefa = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
        taskId = tarefa.getId();

        db = new DatabaseHelperChecklist(this);
        db.openDatabase();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db,ChecklistActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab);

        // teste

        final ToDoModel task = new ToDoModel();
        buttonGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.INVISIBLE);

                if (nome.charAt(0) == 'E'){

                    task.setTaskId(taskId);
                    task.setTask("Configurar perfil");
                    task.setStatus(0);
                    db.insertTask(task);

                    task.setTaskId(taskId);
                    task.setTask("Verificar acesso à VPN no Active Directory");
                    task.setStatus(0);
                    db.insertTask(task);

                    task.setTaskId(taskId);
                    task.setTask("Orientar o colaborador na troca da senha de rede");
                    task.setStatus(0);
                    db.insertTask(task);

                    task.setTaskId(taskId);
                    task.setTask("Configurar Outlook");
                    task.setStatus(0);
                    db.insertTask(task);

                    task.setTaskId(taskId);
                    task.setTask("Configurar Microsoft Teams");
                    task.setStatus(0);
                    db.insertTask(task);

                    task.setTaskId(taskId);
                    task.setTask("Configurar Okta Verify");
                    task.setStatus(0);
                    db.insertTask(task);

                    task.setTaskId(taskId);
                    task.setTask("Validar conexão à VPN");
                    task.setStatus(0);
                    db.insertTask(task);

                    task.setTaskId(taskId);
                    task.setTask("Verificar criptografia do equipamento");
                    task.setStatus(0);
                    db.insertTask(task);

                    task.setTaskId(taskId);
                    task.setTask("Clique no botão TERMO para iniciar a coleta dos dados do equipamento");
                    task.setStatus(0);
                    db.insertTask(task);


                }else if (nome.charAt(0) == 'T'){



                    task.setTaskId(taskId);
                    task.setTask("Clique no botão TERMO para iniciar a coleta dos dados do equipamento");
                    task.setStatus(0);
                    db.insertTask(task);
                }
                else if (nome.charAt(0) == 'R'){



                    task.setTaskId(taskId);
                    task.setTask("Clique no botão TERMO para iniciar a coleta dos dados do equipamento");
                    task.setStatus(0);
                    db.insertTask(task);

                }
                else if (nome.charAt(0) == 'M'){

                    task.setTaskId(taskId);
                    task.setTask("teste 1");
                    task.setStatus(0);
                    db.insertTask(task);

                }
                else if (nome.charAt(0) == 'N'){

                    task.setTaskId(taskId);
                    task.setTask("teste 1");
                    task.setStatus(0);
                    db.insertTask(task);

                }
                reloadList();
            }
        });


        // teste

        reloadList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("taskId", taskId);
                bundle.putBoolean("new", true);
                AddNewChecklist dialog = AddNewChecklist.newInstance();
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), AddNewChecklist.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        reloadList();
        tasksAdapter.notifyDataSetChanged();
    }

    public void reloadList(){
        taskList = db.getAllTasks();
        taskListFilterAdd = new ArrayList<>();
        for (int i=0; i<taskList.size(); i++){
            if (taskList.get(i).getTaskId().equals(taskId)){
                taskListFilterAdd.add(taskList.get(i));
            }
        }

        tasksAdapter.setTasks(taskListFilterAdd);
    }



}
