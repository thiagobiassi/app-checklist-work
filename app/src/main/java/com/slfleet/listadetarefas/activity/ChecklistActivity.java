package com.slfleet.listadetarefas.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;


import com.slfleet.listadetarefas.helper.RecyclerItemTouchHelper;
import com.slfleet.listadetarefas.helper.DialogCloseListener;
import com.slfleet.listadetarefas.R;
import com.slfleet.listadetarefas.adapter.ToDoAdapter;
import com.slfleet.listadetarefas.helper.DatabaseHelperChecklist;
import com.slfleet.listadetarefas.model.Tarefa;
import com.slfleet.listadetarefas.model.ToDoModel;

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

    private List<ToDoModel> taskList;
    private List<ToDoModel> taskListFilterAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        Objects.requireNonNull(getSupportActionBar()).hide();

        nomeChecklist = findViewById(R.id.nomeChecklist);

        // Recuperar dados enviados (Nome do Checklist)
        Bundle dados = getIntent().getExtras();
        String nome = dados.getString("nomeChecklist");
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

        reloadList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("taskId", taskId);
                bundle.putBoolean("new", true);
                AddNewTask dialog = AddNewTask.newInstance();
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), AddNewTask.TAG);
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
