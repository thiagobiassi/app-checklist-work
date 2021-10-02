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
    private TextInputEditText patrimonio;

    public GiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_give, container, false);

        // Enviando para o Microsoft Teams
        // Declarando variáveis para cópia
        motivo = view.findViewById(R.id.motivoEntregaEdit);
        nome = view.findViewById(R.id.nomeColaboradorEdit);
        empresa = view.findViewById(R.id.empresaColaboradorEdit);
        matricula = view.findViewById(R.id.matriculaColaboradorEdit);
        gestor = view.findViewById(R.id.gestorColaboradorEdit);
        setor = view.findViewById(R.id.setorColaboradorEdit);

        hostname = view.findViewById(R.id.hostnameEquipEdit);
        serviceTag = view.findViewById(R.id.serviceTagEquipEdit);
        patrimonio = view.findViewById(R.id.patrimonioEquipEdit);
        tipoCpu = view.findViewById(R.id.tipoDeCpuEquipEdit);
        velocidadeGhz = view.findViewById(R.id.velocidadeEmGhzEquipEdit);
        marca = view.findViewById(R.id.marcaEquipEdit);
        modelo = view.findViewById(R.id.modeloEquipEdit);
        memoria = view.findViewById(R.id.memoriaEquipEit);
        disco = view.findViewById(R.id.discoEquipEdit);
        tamanhoDisco = view.findViewById(R.id.tamanhoEquipEdit);
        hostNoAd = view.findViewById(R.id.adEquipEdit);

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
                        +"PATRIMÔNIO: " + patrimonio.getText().toString().toUpperCase()+"\n"
                        +"VELOCIDADE EM GHz: " + velocidadeGhz.getText().toString().toUpperCase()+"\n"
                        +"MARCA: " + marca.getText().toString().toUpperCase()+"\n"
                        +"MODELO: " + modelo.getText().toString().toUpperCase()+"\n"
                        +"MEMORIA: " + memoria.getText().toString().toUpperCase()+"GB"+"\n"
                        +"VERSÃO DO WINDOWS: WINDOWS 10 ENTERPRISE 64 BITS\n"
                        +"BUILD: 2004\n"
                        +"DISCO: " + disco.getText().toString().toUpperCase()+"\n"
                        +"TAMANHO: " + tamanhoDisco.getText().toString().toUpperCase()+"GB"+"\n"
                        +"HOST MOVIMENTADO NO AD: " + hostNoAd.getText().toString().toUpperCase()+ "\n\n"
                );
                clipBoard.setPrimaryClip(clipData);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://teams.microsoft.com/l/channel/19%3aSmMBtnY6jAihuDo0HwSYng9-Zw-DMAS0izuO46Q2qfc1%40thread.tacv2/Geral?" +
                        "groupId=84413faf-7487-40c5-ae2e-acff6bcc2f89&tenantId=db84cc25-a34d-48ae-81b9-7d26ab3769eb"));
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
