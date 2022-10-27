package br.ulbra.folhapgto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNome, edtSalario, edtFilhos;
    TextView txtReNome, txtReINSS, txtReIR, txtReSalBru;
    RadioGroup radioGroup;
    RadioButton rbMasc, rbFem;
    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSalario = (EditText) findViewById(R.id.edtSalario);
        edtFilhos = (EditText) findViewById(R.id.edtFilhos);
        txtReNome = (TextView) findViewById(R.id.txtReNome);
        txtReINSS = (TextView) findViewById(R.id.txtReINSS);
        txtReIR = (TextView) findViewById(R.id.txtReIR);
        txtReSalBru = (TextView) findViewById(R.id.txtReSalBru);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc);
        rbFem = (RadioButton) findViewById(R.id.rbFem);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double Salario = Double.parseDouble(edtSalario.getText().toString());
                double novoSalario = 0;
                double INSS = 0;
                double IR = 0;
                double filhos = Double.parseDouble((edtFilhos).getText().toString()) ;
                String trata = null;


                //Desconto INSS

                if (Salario <= 1212) {

                    novoSalario = Salario - (Salario * 0.075);
                    INSS = Salario * 0.075;

                } else if (Salario >= 1212.01 && Salario <= 2427.35) {

                    novoSalario = Salario - (Salario * 0.09);
                    INSS = Salario * 0.09;

                } else if (Salario >= 2427.36 && Salario <= 3641.03) {

                    novoSalario = Salario - (Salario * 0.12);
                    INSS = Salario * 0.12;

                } else if (Salario >= 3641.04 && Salario <= 7087.22) {

                    novoSalario = Salario - (Salario * 0.14);
                    INSS = Salario * 0.14;

                }
                //Desconto IR

                if (Salario >= 1903.99 && Salario <= 2826.65) {

                    novoSalario = Salario - (Salario * 0.075);
                    IR = Salario * 0.075;

                } else if (Salario >= 2826.66 && Salario <= 3751.05) {

                    novoSalario = Salario - (Salario * 0.15);
                    IR = Salario * 0.15;

                } else if (Salario >= 3751.06 && Salario <= 4664.68) {

                    novoSalario = Salario - (Salario * 0.225);
                    IR = Salario * 0.225;
                }

                //Salario familia

                if (Salario >= 1212 && !edtFilhos.equals("")) {

                    novoSalario = novoSalario + (filhos * 56.47);


                }

                //Nome tratamento

                if (rbMasc.isSelected()){

                    trata  = "Sr. " + edtNome.getText();

                }else if (rbFem.isSelected()){

                     trata = "Sra . " + edtNome.getText();

                }

                txtReNome.setText(trata);
                txtReIR.setText("IR: R$" + String.valueOf(String.format("%.2f", IR)));
                txtReINSS.setText("INSS: R$" + String.valueOf(String.format("%.2f", INSS)));
                txtReSalBru.setText("Líquido: R$" + String.valueOf(String.format("%.2f", novoSalario - INSS - IR + filhos + (filhos * 56.47))));
            }
        });
    }
}