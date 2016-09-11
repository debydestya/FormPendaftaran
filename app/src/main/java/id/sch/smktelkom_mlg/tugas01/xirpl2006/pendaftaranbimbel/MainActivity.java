package id.sch.smktelkom_mlg.tugas01.xirpl2006.pendaftaranbimbel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etNama;
    TextView tvNama, tvTipe, tvKelas, tvMapel, tvPel;
    RadioGroup rgTipe;
    Spinner spS, spK;
    CheckBox cbM, cbI, cbB;
    Button bOK;

    int nMapel;

    Integer[][] arKelas = {{1, 2, 3, 4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
    ArrayList<Integer> listKelas = new ArrayList<>();
    ArrayAdapter<Integer> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        rgTipe = (RadioGroup) findViewById(R.id.radioGroupTipe);
        spS = (Spinner) findViewById(R.id.spinnerSekolah);
        spK = (Spinner) findViewById(R.id.spinnerKelas);
        cbM = (CheckBox) findViewById(R.id.checkBoxMat);
        cbI = (CheckBox) findViewById(R.id.checkBoxIpa);
        cbB = (CheckBox) findViewById(R.id.checkBoxBahasa);
        tvPel = (TextView) findViewById(R.id.textViewPel);
        tvNama = (TextView) findViewById(R.id.textViewNama);
        tvTipe = (TextView) findViewById(R.id.textViewTipe);
        tvKelas = (TextView) findViewById(R.id.textViewKelas);
        tvMapel = (TextView) findViewById(R.id.textViewMapel);
        bOK = (Button) findViewById(R.id.buttonOK);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listKelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spK.setAdapter(adapter);

        cbM.setOnCheckedChangeListener(this);
        cbI.setOnCheckedChangeListener(this);
        cbB.setOnCheckedChangeListener(this);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
                doProcess2();
                doProcess3();
                doProcess4();
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

        spS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                listKelas.clear();
                listKelas.addAll(Arrays.asList(arKelas[position]));
                adapter.notifyDataSetChanged();
                spK.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void doProcess4() {
        String hasil = "Mata Pelajaran  :\n";
        int startlen = hasil.length();
        if (cbB.isChecked()) hasil += cbB.getText() + "\n";
        if (cbI.isChecked()) hasil += cbI.getText() + "\n";
        if (cbM.isChecked()) hasil += cbM.getText() + "\n";

        if (hasil.length() == startlen) hasil += "Anda belum memilih Mata Pelajaran";

        tvMapel.setText(hasil);
    }

    private void doProcess3() {
        tvKelas.setText("Sekolah               : " + spS.getSelectedItem().toString()
                + " Kelas " + spK.getSelectedItem().toString());
    }

    private void doProcess2() {
        String hasil = null;

        if (rgTipe.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgTipe.getCheckedRadioButtonId());
            hasil = rb.getText().toString();

            if (rgTipe.getCheckedRadioButtonId() != R.id.radioButtonP) {
                EditText etJS = (EditText) findViewById(R.id.editTextJumlah);
                String siswa = etJS.getText().toString();

                if (siswa.isEmpty()) {
                    etJS.setError("Jumlah siswa belum diisi");
                } else {
                    hasil += "\nJumlah Siswa    : " + etJS.getText();
                }


            }
        }

        if (hasil == null) {
            tvTipe.setText("Anda belum memilih tipe bimbel");
        } else {
            tvTipe.setText("Tipe Bimbel        : " + hasil);
        }
    }

    private void doProcess() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            tvNama.setText("Nama                  : " + nama);
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nMapel += 1;
        else nMapel -= 1;

        tvPel.setText("Mata Pelajaran (" + nMapel + " terpilih)");
    }
}
