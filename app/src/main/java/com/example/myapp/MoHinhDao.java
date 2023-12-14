package com.example.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.duanmau1.customview.CustomToast;
import com.example.duanmau1.model.LoaiMoHinh;
import com.example.duanmau1.model.MoHinh;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList

    public void updateSold(int mamh,int soldAdd) {
        getMoHinhById(mamh, new OnGetMoHinhSuccess() {
            @Override
            public void onGetSuccess(MoHinh moHinh, LoaiMoHinh loaiMoHinh) {
                dbReference.child("mo_hinh_" + moHinh.getMaMh()).child("soLuongDaBan")
                        .setValue(moHinh.getSoLuongDaBan() + soldAdd);
            }
        });
    }

    public void deleteAllMoHinhByLoai(int maLmh){
        Log.e("ducduy", "deleteAllMoHinhByLoai: start");
        getAllMoHinh(new OnGetListMoHinhSuccess() {
            @Override
            public void onGetSuccess(List<MoHinh> moHinhList) {
                for (MoHinh moHinh: moHinhList) {
                    if (moHinh.getMaLmh() == maLmh) {
                        deleteMoHinh(moHinh.getMaMh());
                    }
                }
            }
        });

    }


}
