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

import com.hakanbey.nothesaplama.DataClass.LiseHesaplamalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;


public class ucuncuSayfa extends Fragment {

    private Switch ucuncuYaziliAktifEt3, ucuncuSozluAktifEt3, ikinciPerformansAktifEt3, ikinciSozluAktifEt3;
    private EditText txt_birinciYazili, txt_ikinciYazili, txt_ucuncuYazili, txt_birinciSozlu, txt_ikinciSozlu, txt_ucuncuSozlu, txt_birinciPerformans, txt_ikinciPerformans;
    private TextView lbl_Ortalama;
    private Button btn_hesapla;

    private float birinciyazili;
    private float ikinciyazili;
    private float ucuncuyazili;
    private float birincisozlu;
    private float ikincisozlu;
    private float ucuncusozlu;
    private float birinciperformans;
    private float ikinciperformans;
    private float ortalama;
    public LiseHesaplamalar yenihesap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ucuncu_sayfa, container, false);

        ucuncuYaziliAktifEt3 = (Switch) rootView.findViewById(R.id.ucuncuYaziliAktifEt3);
        ikinciSozluAktifEt3 = (Switch) rootView.findViewById(R.id.ikinciSozluAktifEt3);
        ucuncuSozluAktifEt3 = (Switch) rootView.findViewById(R.id.ucuncuSozluAktifEt3);
        ikinciPerformansAktifEt3 = (Switch) rootView.findViewById(R.id.ikinciPerformansAktifEt3);

        txt_birinciYazili = (EditText) rootView.findViewById(R.id.txt_birinciYazili3);
        txt_ikinciYazili = (EditText) rootView.findViewById(R.id.txt_ikinciYazili3);
        txt_ucuncuYazili = (EditText) rootView.findViewById(R.id.txt_ucuncuYazili3);
        txt_birinciSozlu = (EditText) rootView.findViewById(R.id.txt_birinciSozlu3);
        txt_ikinciSozlu = (EditText) rootView.findViewById(R.id.txt_ikinciSozlu3);
        txt_ucuncuSozlu = (EditText) rootView.findViewById(R.id.txt_ucuncuSozlu3);
        txt_birinciPerformans = (EditText) rootView.findViewById(R.id.txt_birinciPerformans3);
        txt_ikinciPerformans = (EditText) rootView.findViewById(R.id.txt_ikinciPerformans3);

        lbl_Ortalama = (TextView) rootView.findViewById(R.id.lbl_Ortalama3);

        btn_hesapla = (Button) rootView.findViewById(R.id.btn_hesapla3);

        ucuncuYaziliAktifEt3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_ucuncuYazili.setVisibility(View.VISIBLE);
                } else {
                    txt_ucuncuYazili.setVisibility(View.INVISIBLE);
                }
            }
        });

        ikinciSozluAktifEt3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_ikinciSozlu.setVisibility(View.VISIBLE);
                    ucuncuSozluAktifEt3.setEnabled(true);
                } else {
                    txt_ikinciSozlu.setVisibility(View.INVISIBLE);
                    ucuncuSozluAktifEt3.setChecked(false);
                    ucuncuSozluAktifEt3.setEnabled(false);
                }
            }
        });

        ucuncuSozluAktifEt3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_ucuncuSozlu.setVisibility(View.VISIBLE);
                } else {
                    txt_ucuncuSozlu.setVisibility(View.INVISIBLE);
                }
            }
        });

        ikinciPerformansAktifEt3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txt_ikinciPerformans.setVisibility(View.VISIBLE);
                } else {
                    txt_ikinciPerformans.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ucuncuYaziliAktifEt3.isChecked()) {
                    if (txt_birinciYazili.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(), "Lütfen birinci yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_ikinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen ikinci yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_ucuncuYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen üçüncü yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_birinciSozlu.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen birinci sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_birinciPerformans.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen birinci performans notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen birinci yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ikinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen ikinci yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ucuncuYazili.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen üçüncü yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciSozlu.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen birinci sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciPerformans.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen birinci performans notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    }
                    //eğer ikinci sözlü aktif ise
                    else if (ikinciSozluAktifEt3.isChecked()) {
                        if (txt_ikinciSozlu.getText().toString().trim().equals("")) {
                            Toast.makeText(getContext(), "Lütfen ikinci sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                        } else if (Float.parseFloat(txt_ikinciSozlu.getText().toString()) > 100) {
                            Toast.makeText(getContext(), "Lütfen ikinci sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                        } else {
                            ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_ucuncuYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_birinciPerformans.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString())) / 6;
                            lbl_Ortalama.setText("" + ortalama);

                            birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                            ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                            ucuncuyazili = Float.parseFloat(txt_ucuncuYazili.getText().toString());
                            birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                            ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                            birinciperformans = Float.parseFloat(txt_birinciPerformans.getText().toString());
                            yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,ucuncuyazili,birincisozlu,ikincisozlu,0,birinciperformans,0,ortalama);
                            MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);

                            // eğer üçüncü sözlü aktif ise
                            if (ucuncuSozluAktifEt3.isChecked()) {
                                if (txt_ucuncuSozlu.getText().toString().trim().equals("")) {
                                    Toast.makeText(getContext(), "Lütfen üçüncü sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                                } else if (Float.parseFloat(txt_ucuncuSozlu.getText().toString()) > 100) {
                                    Toast.makeText(getContext(), "Lütfen üçüncü sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                                } else {
                                    ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_ucuncuYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_birinciPerformans.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString()) + Float.parseFloat(txt_ucuncuSozlu.getText().toString())) / 7;
                                    lbl_Ortalama.setText("" + ortalama);

                                    birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                                    ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                                    ucuncuyazili = Float.parseFloat(txt_ucuncuYazili.getText().toString());
                                    birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                                    ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                                    ucuncusozlu=Float.parseFloat(txt_ucuncuSozlu.getText().toString());
                                    birinciperformans = Float.parseFloat(txt_birinciPerformans.getText().toString());
                                    yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,ucuncuyazili,birincisozlu,ikincisozlu,ucuncusozlu,birinciperformans,0,ortalama);
                                    MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);

                                    // Eğer ikinci performans aktif ise
                                    if (ikinciPerformansAktifEt3.isChecked()) {
                                        if (txt_ikinciPerformans.getText().toString().trim().equals("")) {
                                            Toast.makeText(getContext(), "Lütfen ikinci performans notunuzu girin.", Toast.LENGTH_SHORT).show();
                                        } else if (Float.parseFloat(txt_ikinciPerformans.getText().toString()) > 100) {
                                            Toast.makeText(getContext(), "Lütfen ikinci performans notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_ucuncuYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_birinciPerformans.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString()) + Float.parseFloat(txt_ucuncuSozlu.getText().toString()) + Float.parseFloat(txt_ikinciPerformans.getText().toString())) / 8;
                                            lbl_Ortalama.setText("" + ortalama);

                                            birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                                            ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                                            ucuncuyazili = Float.parseFloat(txt_ucuncuYazili.getText().toString());
                                            birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                                            ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                                            ucuncusozlu=Float.parseFloat(txt_ucuncuSozlu.getText().toString());
                                            birinciperformans = Float.parseFloat(txt_birinciPerformans.getText().toString());
                                            ikinciperformans = Float.parseFloat(txt_ikinciPerformans.getText().toString());
                                            yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,ucuncuyazili,birincisozlu,ikincisozlu,ucuncusozlu,birinciperformans,ikinciperformans,ortalama);
                                            MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_ucuncuYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_birinciPerformans.getText().toString())) / 5;
                        lbl_Ortalama.setText("" + ortalama);

                        birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                        ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                        ucuncuyazili = Float.parseFloat(txt_ucuncuYazili.getText().toString());
                        birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                        birinciperformans = Float.parseFloat(txt_birinciPerformans.getText().toString());
                        yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,ucuncuyazili,birincisozlu,0,0,birinciperformans,0,ortalama);
                        MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);

                    }
                }
                // üçüncü yazılı aktif değil ise else girer
                else {
                    if (txt_birinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen birinci yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_ikinciYazili.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen ikinci yazılı notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_birinciSozlu.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen birinci sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (txt_birinciPerformans.getText().toString().trim().equals("")) {
                        Toast.makeText(getContext(), "Lütfen birinci performans notunuzu girin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen birinci yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_ikinciYazili.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen ikinci yazılı notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciSozlu.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen birinci sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    } else if (Float.parseFloat(txt_birinciPerformans.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Lütfen birinci performans notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                    }
                    if (ikinciSozluAktifEt3.isChecked()) {
                        if (txt_ikinciSozlu.getText().toString().trim().equals("")) {
                            Toast.makeText(getContext(), "Lütfen ikinci sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                        } else if (Float.parseFloat(txt_ikinciSozlu.getText().toString()) > 100) {
                            Toast.makeText(getContext(), "Lütfen ikinci sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                        } else {
                            ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString()) + Float.parseFloat(txt_birinciPerformans.getText().toString())) / 5;
                            lbl_Ortalama.setText("" + ortalama);

                            birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                            ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                            birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                            ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                            birinciperformans = Float.parseFloat(txt_birinciPerformans.getText().toString());
                            yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,0,birincisozlu,ikincisozlu,0,birinciperformans,0,ortalama);
                            MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);

                            // üçüncü sözlü aktif ise
                            if (ucuncuSozluAktifEt3.isChecked()) {
                                if (txt_ucuncuSozlu.getText().toString().trim().equals("")) {
                                    Toast.makeText(getContext(), "Lütfen üçüncü sözlü notunuzu girin.", Toast.LENGTH_SHORT).show();
                                } else if (Float.parseFloat(txt_ucuncuSozlu.getText().toString()) > 100) {
                                    Toast.makeText(getContext(), "Lütfen üçüncü sözlü notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                                } else {
                                    ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString()) + Float.parseFloat(txt_birinciPerformans.getText().toString()) + Float.parseFloat(txt_ucuncuSozlu.getText().toString())) / 6;
                                    lbl_Ortalama.setText("" + ortalama);

                                    birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                                    ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                                    birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                                    ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                                    ucuncusozlu=Float.parseFloat(txt_ucuncuSozlu.getText().toString());
                                    birinciperformans = Float.parseFloat(txt_birinciPerformans.getText().toString());
                                    yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,0,birincisozlu,ikincisozlu,ucuncusozlu,birinciperformans,0,ortalama);
                                    MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);

                                    // ikinci performans aktif ise
                                    if (ikinciPerformansAktifEt3.isChecked()) {
                                        if (txt_ikinciPerformans.getText().toString().trim().equals("")) {
                                            Toast.makeText(getContext(), "Lütfen ikinci performans notunuzu girin.", Toast.LENGTH_SHORT).show();
                                        } else if (Float.parseFloat(txt_ikinciPerformans.getText().toString()) > 100) {
                                            Toast.makeText(getContext(), "Lütfen ikinci performans notunuzu 100'den yüksek girmeyin.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_ikinciSozlu.getText().toString()) + Float.parseFloat(txt_birinciPerformans.getText().toString()) + Float.parseFloat(txt_ucuncuSozlu.getText().toString()) + Float.parseFloat(txt_ikinciPerformans.getText().toString())) / 7;
                                            lbl_Ortalama.setText("" + ortalama);

                                            birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                                            ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                                            birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                                            ikincisozlu = Float.parseFloat(txt_ikinciSozlu.getText().toString());
                                            ucuncusozlu=Float.parseFloat(txt_ucuncuSozlu.getText().toString());
                                            birinciperformans = Float.parseFloat(txt_birinciPerformans.getText().toString());
                                            ikinciperformans = Float.parseFloat(txt_ikinciPerformans.getText().toString());
                                            yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,0,birincisozlu,ikincisozlu,ucuncusozlu,birinciperformans,ikinciperformans,ortalama);
                                            MainActivity.vb.getVeritabani().getDbref("GecmisHesaplamalar").child(MainActivity.vb.getUserID()).push().setValue(yenihesap);

                                        }
                                    }
                                }
                            }
                        }
                    }
                    // hiçbiri seçili değilse
                    else {
                        ortalama = (Float.parseFloat(txt_birinciYazili.getText().toString()) + Float.parseFloat(txt_ikinciYazili.getText().toString()) + Float.parseFloat(txt_birinciSozlu.getText().toString()) + Float.parseFloat(txt_ikinciPerformans.getText().toString())) / 4;
                        lbl_Ortalama.setText("" + ortalama);

                        birinciyazili = Float.parseFloat(txt_birinciYazili.getText().toString());
                        ikinciyazili = Float.parseFloat(txt_ikinciYazili.getText().toString());
                        birincisozlu = Float.parseFloat(txt_birinciSozlu.getText().toString());
                        ikinciperformans = Float.parseFloat(txt_ikinciPerformans.getText().toString());
                        yenihesap=new LiseHesaplamalar(birinciyazili,ikinciyazili,0,birincisozlu,0,0,0,ikinciperformans,ortalama);

                    }
                }


                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_hesapla.getWindowToken(), 0);
            }
        });

        return rootView;
    }
}
