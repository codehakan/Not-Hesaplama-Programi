package com.hakanbey.nothesaplama.Adapter;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hakanbey.nothesaplama.DataClass.LiseHesaplamalar;
import com.hakanbey.nothesaplama.DataClass.Notlar;
import com.hakanbey.nothesaplama.MainActivity;
import com.hakanbey.nothesaplama.R;

import java.util.List;

public class NotlarAdapter extends BaseAdapter {
    private final List<Notlar> notlarim;
    private final LayoutInflater inflater;
    private NotlarAdapter.ViewHolder holder;


    public NotlarAdapter(Activity activity, List<Notlar> notlarim) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.notlarim = notlarim;
    }

    @Override
    public int getCount() {
        return notlarim.size();
    }

    @Override
    public Object getItem(int position) {
        return notlarim.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.tekli_not_gorunumu, null);
            holder = new NotlarAdapter.ViewHolder();
            holder.not_sil=convertView.findViewById(R.id.notsil);
            holder.Simge = (ImageView) convertView.findViewById(R.id.notimage);
            holder.NOT_BASLIGI2 = (TextView) convertView.findViewById(R.id.notbaslik);
            holder.NOT_ICERIGI = (TextView) convertView.findViewById(R.id.noticerik);
            convertView.setTag(holder);
            holder = (NotlarAdapter.ViewHolder) convertView.getTag();

        } else {
            //Get viewholder we already created
            holder = (NotlarAdapter.ViewHolder) convertView.getTag();
        }

        holder = (NotlarAdapter.ViewHolder) convertView.getTag();

        final Notlar veri = notlarim.get(position);

        if (veri != null) {
            holder.Simge.setImageResource(R.drawable.icon_notlarim);
            holder.NOT_BASLIGI2.setText(veri.getBaslik());
            holder.NOT_ICERIGI.setText(veri.getNot());

        }

        holder.not_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.vb.getDbref("Notlar")
                        .child(MainActivity.vb.getUserID())
                        .orderByChild("id")
                        .equalTo(veri.getId())
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

    //View Holder Pattern for better performance
    private static class ViewHolder {
        ImageView Simge;
        TextView NOT_BASLIGI2;
        TextView NOT_ICERIGI;
        ImageButton not_sil;
    }

}
