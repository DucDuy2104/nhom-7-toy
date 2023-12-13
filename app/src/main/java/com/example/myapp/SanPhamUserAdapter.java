package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SanPhamUserAdapter extends RecyclerView.Adapter<SanPhamUserAdapter.SanPhamUserViewHolder>{
    Context context;
    List moHinhList;

    public SanPhamUserAdapter(Context context, List moHinhList) {
        this.context = context;
        this.moHinhList = moHinhList;
    }

    public static class SanPhamUserViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSp;
        TextView tvNameSp, tvPriceSp, tvRating;
        Button btnDetail;

        @Override
        public int getItemCount() {
            return moHinhList.size();
        }

        public SanPhamUserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSp = itemView.findViewById(R.id.img_itemSanPhamUser);
            tvNameSp = itemView.findViewById(R.id.tv_nameSpUser);
            tvRating =itemView.findViewById(R.id.tv_ratingSpUser);
            tvPriceSp = itemView.findViewById(R.id.tv_priceSpUser);
            btnDetail = itemView.findViewById(R.id.btn_detailSpUser);
        }
    }
}
