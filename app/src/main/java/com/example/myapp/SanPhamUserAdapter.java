package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
        public void onBindViewHolder(@NonNull SanPhamUserViewHolder holder, int position) {
            MoHinh moHinh = moHinhList.get(position);
            Glide.with(context).load(moHinh.getImgUri()).into(holder.imgSp);
            holder.tvNameSp.setText(moHinh.getTenMh());
            holder.tvPriceSp.setText(CurrencyConvert.convertFromFloatToVNCurrency(moHinh.getGiaBan()));
            holder.tvRating.setText(moHinh.getDanhGia() + "");
            holder.btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(context, SanPhamDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("mamh",moHinh.getMaMh());
                    intent.putExtras(bundle);
                    ((Activity)context).startActivity(intent);
                }
            });
        }

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
