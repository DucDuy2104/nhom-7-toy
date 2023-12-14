package com.example.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.duanmau1.customview.CustomToast;
import com.example.duanmau1.model.HoaDon;
import com.example.duanmau1.model.HoaDonChiTiet;
import com.example.duanmau1.model.NguoiDung;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



    public void deleteAllHoaDonByMaMh(int mamh) {
        Log.e("ducduy", "deleteAllHoaDonByMaMh: onStart" );
        nguoiDungDao = new NguoiDungDao(context);
        nguoiDungDao.getListNguoiDung(new NguoiDungDao.OnGetListSuccess() {
            @Override
            public void onGetListSuccess(List<NguoiDung> nguoiDungs) {
                for (NguoiDung nguoiDung: nguoiDungs) {
                    getAllHoaDonByMaNd(nguoiDung.getIdNd(), new OnGetListHoaDon() {
                        @Override
                        public void onGetSuccess(List<HoaDon> hoaDonList) {
                            for (HoaDon hoaDon: hoaDonList) {
                                for (HoaDonChiTiet hoaDonChiTiet: hoaDon.getHoaDonChiTietList()) {
                                    if (hoaDonChiTiet.getMaMh() == mamh) {
                                        deleteHoaDon(hoaDon.getMaHd(), nguoiDung.getIdNd());
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });
    }

}
