package com.hakanbey.nothesaplama.Lise;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hakanbey.nothesaplama.DataClass.LiseHesaplamalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;


public class ikinciSayfa extends Fragment {

    private Switch ucuncuYaziliAktifEt2, ucuncuSozluAktifEt2, ikinciSozluAktifEt2;
    private EditText txt_birinciYazili, txt_ikinciYazili, txt_ucuncuYazili, txt_birinciSozlu, txt_ikinciSozlu, txt_ucuncuSozlu;
    private TextView lbl_Ortalama;
    private Button btn_hesapla;

    private float birinciyazili;
    private float ikinciyazili;
    private float ucuncuyazili;
    private float birincisozlu;
    private float ikincisozlu;
    private float ucuncusozlu;
    private float ortalama;
    private LiseHesaplamalar yenihesap;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private FirebaseUser aktifkullanici;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ikinci_sayfa, container, false);

        auth = FirebaseAuth.getInstance();
        aktifkullanici = auth.getCurrentUser();
        final String userID = aktifkullanici.getUid();
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("GecmisHesaplamalar").child(userID);

        ucuncuYaziliAktifEt2 = (Switch) rootView.findViewById(R.id.ucuncuYaziliAktifEt2);
        ikinciSozluAktifEt2 = (Switch) rootView.findViewById(R.id.ikinciSozluAktifEt2);
        ucuncuSozluAktifEt2 = (Switch) rootView.findViewById(R.id.ucuncuSozluAktifEt2);

        txt_birinciYazili = (EditText) rootView.findViewById(R.id.txt_birinciYazili2);
        txt_ikinciYazili = (EditText) rootView.findViewById(R.id.txt_ikinciYazili2);
        txt_ucuncuYazili = (EditText) rootView.findViewById(R.id.txt_ucuncuYazili2);
        txt_birinciSozlu = (EditText) rootView.findViewById(R.id.txt_birinciSozlu2);
        txt_ikinciSozlu = (EditText) rootView.findViewById(R.id.txt_ikinciSozlu2);
        txt_ucuncuSozlu = (EditText) rootView.findViewById(R.id.txt_ucuncuSozlu2);

        lbl_Ortalama = (TextView) rootView.findViewById(R.id.lbl_Ortalama2);

        btn_hesapla = (Button) rootView.findViewById(R.id.btn_hesapla2);

        ucuncuYaziliAktifEt2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_ucuncuYazili.setVisibility(View.VISIBLE);
                } else {
                    txt_ucuncuYazili.setVisibility(View.INVISIBLE);
                }
            }
        });

        ikinciSozluAktifEt2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_ikinciSozlu.setVisibility(View.VISIBLE);
                    ucuncuSozluAktifEt2.setEnabled(true);
                } else {
                    txt_ikinciSozlu.setVisibility(View.INVISIBLE);
                    ucuncuSozluAktifEt2.setChecked(false);
                    ucuncuSozluAktifEt2.setEnabled(false);
                }
            }
        });

        ucuncuSozluAktifEt2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_ucuncuSozlu.setVisibility(View.VISIBLE);
                } else {
                    txt_ucuncuSozlu.setVisibility(View.INVISIBLE);
                }
            }
        });


        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ucuncuYaziliAktifEt2.isChecked()) {
                    if (txt_birinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen birinci yazılı notunuzu girin", Toast.LENGTH_SHORT).show();
                    } else if (txt_ikinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen ikinci yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_ucuncuYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen üçüncü yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_birinciSozlu.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen birinci sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen birinci yazılı notunuzu 100'den yüksek girmeyin", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ikinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen ikinci yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ucuncuYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen üçüncü yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciSozlu.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen birinci sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (ikinciSozluAktifEt2.isChecked()) {
                        if (txt_ikinciSozlu.getText().toString().trim().equals("")) {
                            Toast.makeText(getContext(), "Lütfen ikinci sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                        } else if (Float.parseFloat(txt_ikinciSozlu.getText().toString()) > 100) {
                            Toast.makeText(getContext(), "Lütfen ikinci sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                        } else {
                            ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_ucuncuYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString())) / 5;
                            lbl_Ortalama.setText("" + ortalama);

                            birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                            ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                            ucuncuyazili = Float.parseFloat(txt_ucuncuYazili.getText().toString());
                            birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                            ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());

                            yenihesap = new LiseHesaplamalar(birinciyazili, ikinciyazili, ucuncuyazili, birincisozlu, ikincisozlu, ortalama);
                            MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);

                            if (ucuncuSozluAktifEt2.isChecked()) {
                                if (txt_ucuncuSozlu.getText().toString().trim().equals("")) {
                                    Toast.makeText(getContext(), "Lütfen üçüncü sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                                } else if (Float.parseFloat(txt_ucuncuSozlu.getText().toString()) > 100) {
                                    Toast.makeText(getContext(), "Lütfen üçüncü sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                                } else {
                                    ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_ucuncuYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString()) + Float.parseFloat(txt_ucuncuSozlu.getText().toString())) / 6;
                                    lbl_Ortalama.setText("" + ortalama);
                                    birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                                    ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                                    ucuncuyazili = Float.parseFloat(txt_ucuncuYazili.getText().toString());
                                    birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                                    ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                                    ucuncusozlu = Float.parseFloat(txt_ucuncuSozlu.getText().toString());
                                    yenihesap = new LiseHesaplamalar(birinciyazili, ikinciyazili, ucuncuyazili, birincisozlu, ikincisozlu, ucuncusozlu, ortalama);
                                    MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);
                                }
                            }
                        }
                    } else {
                        ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_ucuncuYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString())) / 4;
                        lbl_Ortalama.setText("" + ortalama);
                        birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                        ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                        ucuncuyazili = Float.parseFloat(txt_ucuncuYazili.getText().toString());
                        birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                        yenihesap = new LiseHesaplamalar(birinciyazili, ikinciyazili, ucuncuyazili, birincisozlu, ortalama);
                        MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);
                    }
                }
                // üçüncü yazılı yok ise buradan else yapısına giriyoruz.
                else {
                    if (txt_birinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen birinci yazılı notunuzu girin", Toast.LENGTH_SHORT).show();
                    } else if (txt_ikinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getActivity(), "Lütfen ikinci yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_birinciSozlu.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen birinci sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen birinci yazılı notunuzu 100'den yüksek girmeyin", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ikinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getActivity(), "Lütfen ikinci yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    }
                    /*
                    Eğer ikinci sözlü aktif ise bu blok girecek.
                     */
                    else if (ikinciSozluAktifEt2.isChecked()) {
                        if (txt_ikinciSozlu.getText().toString().trim().equals("")) {
                            Toast.makeText(getContext(), "Lütfen ikinci sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                        } else if (Float.parseFloat(txt_ikinciSozlu.getText().toString()) > 100) {
                            Toast.makeText(getContext(), "Lütfen ikinci sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                        } else {
                            ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString())) / 4;
                            lbl_Ortalama.setText("" + ortalama);

                            birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                            ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                            birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                            ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                            yenihesap = new LiseHesaplamalar(birinciyazili, ikinciyazili, 0, birincisozlu, ikincisozlu, ortalama);
                            MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);
                            /*
                            üçüncü sözlü aktif ise bu blok girecek.
                             */
                            if (ucuncuSozluAktifEt2.isChecked()) {
                                if (txt_ucuncuSozlu.getText().toString().trim().equals("")) {
                                    Toast.makeText(getContext(), "Lütfen üçüncü sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                                } else if (Float.parseFloat(txt_ucuncuSozlu.getText().toString()) > 100) {
                                    Toast.makeText(getContext(), "Lütfen üçüncü sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                                } else {
                                    ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString()) + Float.parseFloat(txt_ucuncuSozlu.getText().toString())) / 5;
                                    lbl_Ortalama.setText("" + ortalama);
                                    birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                                    ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                                    birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                                    ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                                    ucuncusozlu = Float.parseFloat(txt_ucuncuSozlu.getText().toString());
                                    yenihesap = new LiseHesaplamalar(birinciyazili, ikinciyazili, 0, birincisozlu, ikincisozlu, ucuncusozlu, ortalama);
                                    MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);
                                }
                            }
                        }
                    } else {
                        ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString())) / 3;
                        lbl_Ortalama.setText("" + ortalama);
                        birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                        ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                        birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                        yenihesap = new LiseHesaplamalar(birinciyazili, ikinciyazili, 0, birincisozlu, ortalama);
                        MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);
                    }
                }
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_hesapla.getWindowToken(), 0);
            }
        });

        return rootView;
    }

}
