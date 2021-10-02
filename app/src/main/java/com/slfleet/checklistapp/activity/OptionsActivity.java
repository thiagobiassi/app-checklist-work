package com.slfleet.checklistapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.slfleet.checklistapp.R;
import com.slfleet.checklistapp.adapter.ToDoAdapter;
import com.slfleet.checklistapp.model.Tarefa;
import com.slfleet.checklistapp.model.ToDoModel;

import java.util.List;

public class OptionsActivity extends AppCompatActivity {

    private Button buttonEntrega;
    private Button buttonTroca;
    private Button buttonRollout;
    private Button buttonMonitor;
    private Button buttonEquipNovo;
    private Button buttonCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        buttonEntrega = findViewById(R.id.buttonEntrega);
        buttonTroca = findViewById(R.id.buttonTroca);
        buttonRollout = findViewById(R.id.buttonRollout);
        buttonMonitor = findViewById(R.id.buttonMonitor);
        buttonEquipNovo = findViewById(R.id.buttonEquipNovo);
        buttonCriar = findViewById(R.id.buttonCriar);

        final Tarefa opcao = new Tarefa();

        buttonEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcao.setOptionId(1);
                Intent intent = new Intent(OptionsActivity.this, AddNewTask.class);
                intent.putExtra("optionName", "Entrega");
                startActivity(intent);
                finish();
            }
        });

        buttonTroca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcao.setOptionId(2);
                Intent intent = new Intent(OptionsActivity.this, AddNewTask.class);
                intent.putExtra("optionName", "Troca");
                startActivity(intent);
                finish();
            }
        });

        buttonRollout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, AddNewTask.class);
                intent.putExtra("optionName", "Rollout");
                startActivity(intent);
                finish();
            }
        });

        buttonMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, AddNewTask.class);
                intent.putExtra("optionName", "Monitor");
                startActivity(intent);
                finish();
            }
        });

        buttonEquipNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, AddNewTask.class);
                intent.putExtra("optionName", "Novo equipamento");
                startActivity(intent);
                finish();
            }
        });

        buttonCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, AddNewTask.class);
                intent.putExtra("optionName", "Criar");
                startActivity(intent);
                finish();
            }
        });

    }

}
