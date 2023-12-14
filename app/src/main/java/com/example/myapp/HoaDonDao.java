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

public class HoaDonDao {

s(Void unused) {
                        Log.e("ducduy", "onSuccess: deleted hd: " + mahd + " of user: "  + mand);
        HoaDon hoaDon = dataSnapshot.getValue(HoaDon.class);
                        onGetHoaDon.onGetSuccess(hoaDon);
                    }
                });
    }

    public void getListHoaDonByStatus(int mand, OnGetHoaDonListByStatus onGetHoaDonListByStatus) {
        getAllHoaDonByMaNd(mand, new OnGetListHoaDon() {
            @Override
            public void onGetSuccess(List<HoaDon> hoaDonList) {
                List<HoaDon> hoaDonListOnGoing = new ArrayList<>();
                List<HoaDon> hoaDonListGone = new ArrayList<>();
                List<HoaDon> hoaDonListCancel = new ArrayList<>();
                for (HoaDon hoaDon: hoaDonList) {
                    if (hoaDon.getTinhTrang() == 0) {
                        hoaDonListOnGoing.add(hoaDon);
                    } else if (hoaDon.getTinhTrang() == 1) {
                        hoaDonListGone.add(hoaDon);
                    } else {
                        hoaDonListCancel.add(hoaDon);
                    }
                }
                onGetHoaDonListByStatus.onGetSuccess(hoaDonListOnGoing, hoaDonListGone,hoaDonListCancel);
            }
        });
    }

    public void cancelBill(int mand, int mahd) {
        dbReference.child("list_hoa_don_user_" +mand)
                .child("hoa_don_user_" +mahd)
                .child("tinhTrang")
                .setValue(-1);
        CustomToast customToast = new CustomToast(context, CustomToast.SUCCESS, "Hủy thành công");
        customToast.showToast();
    }

    public void reOrder(int mand, int mahd) {
        dbReference.child("list_hoa_don_user_" +mand)
                .child("hoa_don_user_" +mahd)
                .child("tinhTrang")
                .setValue(0);
        CustomToast customToast = new CustomToast(context, CustomToast.SUCCESS, "Đặt thành công");
        customToast.showToast();
    }

    public void confirmBill(int mand, int mahd) {

        moHinhDao = new MoHinhDao(context);
        dbReference.child("list_hoa_don_user_" +mand)
                .child("hoa_don_user_" +mahd)
                .child("tinhTrang")
                .setValue(1);

        getHoaDonByMahd(mahd, mand, new OnGetHoaDon() {
            @Override
            public void onGetSuccess(HoaDon hoaDon) {
                for (HoaDonChiTiet hoaDonChiTiet: hoaDon.getHoaDonChiTietList()) {
                    moHinhDao.updateSold(hoaDonChiTiet.getMaMh(), hoaDonChiTiet.getSoLuong());
                    CustomToast customToast = new CustomToast(context, CustomToast.SUCCESS, "Đã xác nhận đơn hàng");
                    customToast.showToast();
                }
            }
        });
    }

    public void deleteAllHoaDonByMand(int mand) {
        dbReference.child("list_hoa_don_user_" +mand).removeValue();
    }

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
