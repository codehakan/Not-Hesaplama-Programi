package com.hakanbey.nothesaplama.Adapter;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hakanbey.nothesaplama.DataClass.Hatirlatmalar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

import java.util.List;

public class HatirlatmaAdaptor extends BaseAdapter {

    private List<Hatirlatmalar> hatirlatmalarim;
    private final LayoutInflater inflater;
    private HatirlatmaAdaptor.ViewHolder holder;

    public HatirlatmaAdaptor(List<Hatirlatmalar> hatirlatmalarim, Activity activity) {
        this.hatirlatmalarim = hatirlatmalarim;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return hatirlatmalarim.size();
    }

    @Override
    public Object getItem(int position) {
        return hatirlatmalarim.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.single_hatirlatma_gorunum, null);
            holder = new HatirlatmaAdaptor.ViewHolder();
            holder.HATIRLATMA_SIL=convertView.findViewById(R.id.hatirlatmasil);
            holder.Simge =  convertView.findViewById(R.id.hatirlatmaimage);
            holder.HATIRLATMA_BASLIGI = convertView.findViewById(R.id.hatirlatmabaslik);
            holder.HATIRLATMA_ZAMANI =  convertView.findViewById(R.id.hatirlatmazamani);
            convertView.setTag(holder);
            holder = (HatirlatmaAdaptor.ViewHolder) convertView.getTag();

        } else {
            //Get viewholder we already created
            holder = (HatirlatmaAdaptor.ViewHolder) convertView.getTag();
        }

        holder = (HatirlatmaAdaptor.ViewHolder) convertView.getTag();

        final Hatirlatmalar veri = hatirlatmalarim.get(position);

        if (veri != null) {
            holder.HATIRLATMA_BASLIGI.setText(veri.getBaslik());
            holder.HATIRLATMA_ZAMANI.setText(veri.getSaat()+":"+veri.getDakika()+" "+veri.getGün()+"/"+veri.getAy()+"/"+veri.getYil());
        }

        holder.HATIRLATMA_SIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.vb.getDbref("Hatirlatmalar")
                        .child(MainActivity.vb.getUserID())
                        .orderByChild("id").equalTo(veri.getId())
                        .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            snapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        ImageView Simge;
        TextView HATIRLATMA_BASLIGI;
        TextView HATIRLATMA_ZAMANI;
        ImageButton HATIRLATMA_SIL;
    }
}
