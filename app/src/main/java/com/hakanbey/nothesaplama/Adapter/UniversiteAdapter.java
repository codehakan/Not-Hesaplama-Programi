package com.hakanbey.nothesaplama.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hakanbey.nothesaplama.DataClass.UniversiteHesaplamalar;
import com.hakanbey.nothesaplama.R;

import java.util.List;

public class UniversiteAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private ViewHolder holder;
    private final List<UniversiteHesaplamalar> universiteogrencisi;

    public UniversiteAdapter(Activity activity, List<UniversiteHesaplamalar> universiteogrencisi) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.universiteogrencisi = universiteogrencisi;
    }


    @Override
    public int getCount() {
        return universiteogrencisi.size();
    }

    @Override
    public Object getItem(int position) {
        return universiteogrencisi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.satir_layout_universite, null);

            holder = new ViewHolder();
            holder.Simge = (ImageView) convertView.findViewById(R.id.simge);
            holder.KAYIT_ADI = (TextView) convertView.findViewById(R.id.kayit_adi);
            holder.VIZE_SATIR = (TextView) convertView.findViewById(R.id.vize_satir);
            holder.FINAL_SATIR = (TextView) convertView.findViewById(R.id.final_satir);
            holder.ORTALAMA_SATIR = (TextView) convertView.findViewById(R.id.ortalama_satir);
            convertView.setTag(holder);
            holder = (ViewHolder) convertView.getTag();

        } else {
            //Get viewholder we already created
            holder = (ViewHolder) convertView.getTag();
        }
        holder = (ViewHolder) convertView.getTag();
        UniversiteHesaplamalar veri = universiteogrencisi.get(position);
        if (veri != null) {
            holder.Simge.setImageResource(R.drawable.unisinavicon);
            holder.KAYIT_ADI.setText("Üniversite Hesaplaması");
            holder.VIZE_SATIR.setText(String.valueOf("Vize: " + veri.getVIZE_NOTU()));
            holder.FINAL_SATIR.setText(String.valueOf("Final: " + veri.getFINAL_NOTU()));
            holder.ORTALAMA_SATIR.setText(String.valueOf("Ort.: " + veri.getORTALAMA()));
        }
        return convertView;
    }

    //View Holder Pattern for better performance
    private static class ViewHolder {
        ImageView Simge;
        TextView KAYIT_ADI;
        TextView VIZE_SATIR;
        TextView FINAL_SATIR;
        TextView ORTALAMA_SATIR;
    }
}
