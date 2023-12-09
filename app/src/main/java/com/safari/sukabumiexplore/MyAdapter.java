package com.safari.sukabumiexplore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;
    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataGambar()).into(holder.recImage);
        holder.txtjudulItem.setText(dataList.get(position).getDataJudul());
        holder.txtentangItem.setText(dataList.get(position).getDataTentang());
        holder.txtlokasiItem.setText(dataList.get(position).getDataLokasi());
        holder.txthargaItem.setText(dataList.get(position).getDatadataHarga());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Gambar", dataList.get(holder.getAdapterPosition()).getDataGambar());
                intent.putExtra("Judul", dataList.get(holder.getAdapterPosition()).getDataJudul());
                intent.putExtra("Deskripsi", dataList.get(holder.getAdapterPosition()).getDataDeskripsi());
                intent.putExtra("JamBuka", dataList.get(holder.getAdapterPosition()).getDataJambuka());
                intent.putExtra("Lokasi", dataList.get(holder.getAdapterPosition()).getDataLokasi());
                intent.putExtra("Harga", dataList.get(holder.getAdapterPosition()).getDatadataHarga());
                intent.putExtra("LinkMaps", dataList.get(holder.getAdapterPosition()).getDataLinkMaps());
                intent.putExtra("Tentang",dataList.get(holder.getAdapterPosition()).getDataTentang());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView txtjudulItem, txtentangItem, txtlokasiItem, txthargaItem;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        txtjudulItem = itemView.findViewById(R.id.judulItem);
        txtentangItem = itemView.findViewById(R.id.tentangItem);
        txtlokasiItem = itemView.findViewById(R.id.lokasiItem);
        txthargaItem = itemView.findViewById(R.id.hargaItem);
    }
}