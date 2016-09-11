package id.sch.smktelkom_mlg.tugas01.xirpl2006.pendaftaranbimbel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etNama;
    TextView tvNama, tvTipe;
    RadioGroup rgTipe;
    Button bOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        rgTipe = (RadioGroup) findViewById(R.id.radioGroupTipe);
        tvNama = (TextView) findViewById(R.id.textViewNama);
        tvTipe = (TextView) findViewById(R.id.textViewTipe);
        bOK = (Button) findViewById(R.id.buttonOK);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
                doProcess2();
            }
        });

        rgTipe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radioButtonP) {
                    findViewById(R.id.editTextJumlah).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.editTextJumlah).setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void doProcess2() {
        String hasil = null;

        if (rgTipe.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgTipe.getCheckedRadioButtonId());
            hasil = rb.getText().toString();

            if (rgTipe.getCheckedRadioButtonId() != R.id.radioButtonP) {
                EditText etJS = (EditText) findViewById(R.id.editTextJumlah);
                hasil += "\nJumlah Siswa    : " + etJS.getText();
            }
        }

        if (hasil == null) {
            tvTipe.setText("Anda belum memilih tipe bimbel");
        } else {
            tvTipe.setText("Tipe Bimbel : " + hasil);
        }
    }

    private void doProcess() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            tvNama.setText("Nama    : " + nama);
        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        return valid;
    }
}
