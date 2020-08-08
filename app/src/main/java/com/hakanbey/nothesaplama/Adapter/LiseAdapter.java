package com.hakanbey.nothesaplama.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hakanbey.nothesaplama.DataClass.LiseHesaplamalar;
import com.hakanbey.nothesaplama.R;

import java.util.List;

public class LiseAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private LiseAdapter.ViewHolder holder;
    private final List<LiseHesaplamalar> liseogrencisi;

    public LiseAdapter(Activity activity, List<LiseHesaplamalar> liseogrencisi) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.liseogrencisi = liseogrencisi;
    }

    @Override
    public int getCount() {
        return liseogrencisi.size();
    }

    @Override
    public Object getItem(int position) {
        return liseogrencisi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.satir_layout_lise, null);

            holder = new LiseAdapter.ViewHolder();
            //bilgilendirmeler
            holder.Simge = (ImageView) convertView.findViewById(R.id.simge);
            holder.KAYIT_ADI = (TextView) convertView.findViewById(R.id.kayit_adi);
            //yazılılar
            holder.BIRINCI_YAZILI_SATIR = (TextView) convertView.findViewById(R.id.satirlayout_birinciyazili);
            holder.IKINCI_YAZILI_SATIR = (TextView) convertView.findViewById(R.id.satirlayout_ikinciyazili);
            holder.UCUNCU_YAZILI_SATIR = (TextView) convertView.findViewById(R.id.satirlayout_ucuncuyazili);
            //sözlüler
            holder.BIRINCI_SOZLU_SATIR = (TextView) convertView.findViewById(R.id.satirlayout_birincisozlu);
            holder.IKINCI_SOZLU_SATIR = (TextView) convertView.findViewById(R.id.satirlayout_ikincisozlu);
            holder.UCUNCU_SOZLU_SATIR = (TextView) convertView.findViewById(R.id.satirlayout_ucuncusozlu);
            //performanslar
            holder.BIRINCI_PERFORMANS_SATIR = (TextView) convertView.findViewById(R.id.satirlayout_birinciperformans);
            holder.IKINCI_PERFORMANS_SATIR = (TextView) convertView.findViewById(R.id.satirlayout_ikinciperformans);
            //ortalama
            holder.ORTALAMA_SATIR_LISE = (TextView) convertView.findViewById(R.id.satirlayout_ortalamalise);
            convertView.setTag(holder);
            holder = (LiseAdapter.ViewHolder) convertView.getTag();

        } else {
            //Get viewholder we already created
            holder = (LiseAdapter.ViewHolder) convertView.getTag();
        }
        holder = (LiseAdapter.ViewHolder) convertView.getTag();
        LiseHesaplamalar veri = liseogrencisi.get(position);
        if (veri != null) {
            holder.Simge.setImageResource(R.drawable.unisinavicon);
            holder.KAYIT_ADI.setText("Lise Hesaplaması");
            //yazılılar
            holder.BIRINCI_YAZILI_SATIR.setText("Birinci Yazılı: " + veri.getBIRINCI_YAZILI());
            holder.IKINCI_YAZILI_SATIR.setText("İkinci Yazılı: " + veri.getIKINCI_YAZILI());
            holder.UCUNCU_YAZILI_SATIR.setText("Üçüncü Yazılı: " + veri.getUCUNCU_YAZILI());
            //sözlüler
            holder.BIRINCI_SOZLU_SATIR.setText("Birinci Sözlü: " + veri.getBIRINCI_SOZLU());
            holder.IKINCI_SOZLU_SATIR.setText("İkinci Sözlü: " + veri.getIKINCI_SOZLU());
            holder.UCUNCU_SOZLU_SATIR.setText("Üçüncü Sözlü: " + veri.getUCUNCU_SOZLU());
            //performanslar
            holder.BIRINCI_PERFORMANS_SATIR.setText("Birinci Perf.: " + veri.getBIRINCI_PERFORMANS());
            holder.IKINCI_PERFORMANS_SATIR.setText("İkinci Perf.: " + veri.getIKINCI_PERFORMANS());
            //ortalama
            holder.ORTALAMA_SATIR_LISE.setText("Ort.: " + veri.getORTALAMA());
        }
        return convertView;
    }

    //View Holder Pattern for better performance
    private static class ViewHolder {
        ImageView Simge;
        TextView KAYIT_ADI;
        TextView BIRINCI_YAZILI_SATIR;
        TextView IKINCI_YAZILI_SATIR;
        TextView UCUNCU_YAZILI_SATIR;
        TextView BIRINCI_SOZLU_SATIR;
        TextView IKINCI_SOZLU_SATIR;
        TextView UCUNCU_SOZLU_SATIR;
        TextView BIRINCI_PERFORMANS_SATIR;
        TextView IKINCI_PERFORMANS_SATIR;
        TextView ORTALAMA_SATIR_LISE;
    }
}
