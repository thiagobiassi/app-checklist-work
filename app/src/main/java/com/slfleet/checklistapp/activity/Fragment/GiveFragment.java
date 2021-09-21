package com.slfleet.checklistapp.activity.Fragment;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.slfleet.checklistapp.R;

public class GiveFragment extends Fragment implements View.OnClickListener{

    private Button buttonTeams;
    private TextInputEditText motivo;
    private TextInputEditText nome;
    private TextInputEditText empresa;
    private TextInputEditText matricula;
    private TextInputEditText gestor;
    private TextInputEditText setor;

    private TextInputEditText hostname;
    private TextInputEditText serviceTag;
    private TextInputEditText tipoCpu;
    private TextInputEditText velocidadeGhz;
    private TextInputEditText marca;
    private TextInputEditText modelo;
    private TextInputEditText memoria;
    private TextInputEditText disco;
    private TextInputEditText tamanhoDisco;
    private TextInputEditText hostNoAd;

    public GiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_give, container, false);

        // Enviando para o Microsoft Teams
        // Declarando variáveis para cópia
        motivo = view.findViewById(R.id.motivoEntregaEdit2);
        nome = view.findViewById(R.id.nomeColaboradorEdit2);
        empresa = view.findViewById(R.id.empresaColaboradorEdit2);
        matricula = view.findViewById(R.id.matriculaColaboradorEdit2);
        gestor = view.findViewById(R.id.gestorColaboradorEdit2);
        setor = view.findViewById(R.id.setorColaboradorEdit2);

        hostname = view.findViewById(R.id.hostnameEquipEdit2);
        serviceTag = view.findViewById(R.id.serviceTagEquipEdit2);
        tipoCpu = view.findViewById(R.id.tipoDeCpuEquipEdit2);
        velocidadeGhz = view.findViewById(R.id.velocidadeEmGhzEquipEdit2);
        marca = view.findViewById(R.id.marcaEquipEdit2);
        modelo = view.findViewById(R.id.modeloEquipEdit2);
        memoria = view.findViewById(R.id.memoriaEquipEit2);
        disco = view.findViewById(R.id.discoEquipEdit2);
        tamanhoDisco = view.findViewById(R.id.tamanhoEquipEdit2);
        hostNoAd = view.findViewById(R.id.adEquipEdit2);

        buttonTeams = view.findViewById(R.id.buttonTeams);
        buttonTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipBoard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("EditText", "" +
                        "MOTIVO DA ENTREGA: " + motivo.getText().toString().toUpperCase()+"\n\n"

                        +"NOME DO COLABORADOR: " + nome.getText().toString().toUpperCase()+"\n"
                        +"EMPRESA: " + empresa.getText().toString().toUpperCase()+"\n"
                        +"MATRICULA: " + matricula.getText().toString().toUpperCase()+"\n"
                        +"GESTOR: " + gestor.getText().toString().toUpperCase()+"\n"
                        +"SETOR: " + setor.getText().toString().toUpperCase()+"\n\n"

                        +"DADOS DO EQUIPAMENTO"+"\n"
                        +"HOSTNAME: " + hostname.getText().toString().toUpperCase()+"\n"
                        +"SERVICE TAG: " + serviceTag.getText().toString().toUpperCase()+"\n"
                        +"TIPO DE CPU: " + tipoCpu.getText().toString().toUpperCase()+"\n"
                        +"VELOCIDADE EM GHz: " + velocidadeGhz.getText().toString().toUpperCase()+"\n"
                        +"MARCA: " + marca.getText().toString().toUpperCase()+"\n"
                        +"MODELO: " + modelo.getText().toString().toUpperCase()+"\n"
                        +"MEMORIA: " + memoria.getText().toString().toUpperCase()+"GB"+"\n"
                        +"VERSÃO DO WINDOWS: WINDOWS 10 ENTERPRISE 64 BITS\n"
                        +"BUILD: 2004\n"
                        +"DISCO: " + disco.getText().toString().toUpperCase()+"\n"
                        +"TAMANHO: " + tamanhoDisco.getText().toString().toUpperCase()+"GB"+"\n"
                        +"HOST MOVIMENTADO NO AD: " + hostNoAd.getText().toString().toUpperCase()
                );
                clipBoard.setPrimaryClip(clipData);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://teams.microsoft.com/l/channel/19%3ayJaLcoyVGP6PyeRaRHhISvoPq1GUR-73Bzz5hKWRcsc1%40thread.tacv2/" +
                        "Geral?groupId=d4ec5d34-4c49-4dfd-89fe-cd71b5669143&tenantId=da17bd8d-38b0-45a2-a80a-af014c246e20"));
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}