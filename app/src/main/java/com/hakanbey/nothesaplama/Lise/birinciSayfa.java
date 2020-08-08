package com.hakanbey.nothesaplama.Lise;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hakanbey.nothesaplama.DataClass.LiseHesaplamalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;


public class birinciSayfa extends Fragment {
    private Switch aSwitch;
    private EditText txt_birinciYazili, txt_ikinciYazili, txt_ucuncuYazili;
    private TextView lbl_Ortalama;
    private Button btn_hesapla;

    private float birinciyazili;
    private float ikinciyazili;
    private float ucuncuyazili;
    private float ortalama;
    public LiseHesaplamalar yenihesap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.birinci_sayfa, container, false);

        aSwitch = (Switch) rootView.findViewById(R.id.ucuncuYaziliAktifEt1);

        txt_birinciYazili = (EditText) rootView.findViewById(R.id.txt_birinciYazili1);
        txt_ikinciYazili = (EditText) rootView.findViewById(R.id.txt_ikinciYazili1);
        txt_ucuncuYazili = (EditText) rootView.findViewById(R.id.txt_ucuncuYazili1);

        lbl_Ortalama = (TextView) rootView.findViewById(R.id.lbl_Ortalama1);

        btn_hesapla = (Button) rootView.findViewById(R.id.btn_hesapla1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_ucuncuYazili.setVisibility(View.VISIBLE);
                } else {
                    txt_ucuncuYazili.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()) {
                    if (txt_birinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen birinci yazılı notunuzu girin", Toast.LENGTH_SHORT).show();
                    } else if (txt_ikinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen ikinci yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_ucuncuYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen üçüncü yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen birinci yazılı notunuzu 100'den yüksek girmeyin", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ikinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen ikinci yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ucuncuYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen üçüncü yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else {
                        ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_ucuncuYazili.getText().toString())) / 3;
                        lbl_Ortalama.setText("" + ortalama);

                        birinciyazili=Float.parseFloat(txt_birinciYazili.getText().toString());
                        ikinciyazili=Float.parseFloat(txt_ikinciYazili.getText().toString());
                        ucuncuyazili=Float.parseFloat(txt_ucuncuYazili.getText().toString());
                        yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,ucuncuyazili,ortalama);
                        MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);
                        Toast.makeText(getActivity(), "Ortalama başarılı şekilde hesaplandı.", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    if (txt_birinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen birinci yazılı notunuzu girin", Toast.LENGTH_SHORT).show();
                    } else if (txt_ikinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen ikinci yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen birinci yazılı notunuzu 100'den yüksek girmeyin", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ikinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen ikinci yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else {
                        ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString())) / 2;
                        lbl_Ortalama.setText("" + ortalama);
                        Toast.makeText(getActivity(), "Ortalama başarılı şekilde hesaplandı.", Toast.LENGTH_SHORT).show();

                        birinciyazili=Float.parseFloat(txt_birinciYazili.getText().toString());
                        ikinciyazili=Float.parseFloat(txt_ikinciYazili.getText().toString());
                        yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,ortalama);
                        MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);
                        Toast.makeText(getActivity(), "Ortalama başarılı şekilde hesaplandı.", Toast.LENGTH_SHORT).show();
                    }
                }
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_hesapla.getWindowToken(), 0);

            }
        });


        return rootView;
    }

}
