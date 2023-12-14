package com.example.myapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.duanmau1.customview.CustomToast;
import com.example.duanmau1.dao.MoHinhDao;
import com.example.duanmau1.model.LoaiMoHinh;
import com.example.duanmau1.model.MoHinh;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public class DetailMoHinhActivity extends AppCompatActivity {




    private void showBottomSheetDialog() {
        ext(moHinh.getChieuCao()+"");
                edtDate.setText(moHinh.getNgaySx());
                edtAgeLimit.setText(moHinh.getGioiHanDoTuoi()+"");
                edtOrigin.setText(moHinh.getXuatXu());
                edtDate.setFocusable(false);
                edtDate.setFocusableInTouchMode(false);

                edtDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                DetailMoHinhActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        // Xử lý ngày được chọn
                                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                        edtDate.setText(selectedDate);
                                    }
                                },
                                // Thiết lập ngày mặc định khi mở dialog (năm, tháng, ngày)
                                Calendar.getInstance().get(Calendar.YEAR),
                                Calendar.getInstance().get(Calendar.MONTH),
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                        );

                        datePickerDialog.show();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = edtName.getText().toString();
                        int price = Integer.parseInt(edtPrice.getText().toString());
                        String material = edtMaterial.getText().toString();
                        String ratio = edtRatio.getText().toString();
                        int height = Integer.parseInt(edtHeight.getText().toString());
                        String date = edtDate.getText().toString();
                        int age = Integer.parseInt(edtAgeLimit.getText().toString());
                        String origin = edtOrigin.getText().toString();

                        moHinh.setTenMh(name);
                        moHinh.setGiaBan(price);
                        moHinh.setChatLieu(material);
                        moHinh.setTiLe(ratio);
                        moHinh.setChieuCao(height);
                        moHinh.setNgaySx(date);
                        moHinh.setGioiHanDoTuoi(age);
                        moHinh.setXuatXu(origin);

                        moHinhDao.updateMoHinh(moHinh);
                        CustomToast customToast = new CustomToast(DetailMoHinhActivity.this, CustomToast.SUCCESS, "Updated successfully!");
                        customToast.showToast();
                        setView();
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });

        bottomSheetDialog.show();
    }

    private void setView() {
        moHinhDao.getMoHinhById(mamh, new MoHinhDao.OnGetMoHinhSuccess() {
            @Override
            public void onGetSuccess(MoHinh moHinh, LoaiMoHinh loaiMoHinh) {
                Glide.with(DetailMoHinhActivity.this).load(Uri.parse(moHinh.getImgUri())).into(img);
                tvName.setText(moHinh.getTenMh());
                tvRating.setText(moHinh.getDanhGia() + "");
                tvPrice.setText(CurrencyConvert.convertFromFloatToVNCurrency(moHinh.getGiaBan()));
                tvAmount.setText(moHinh.getSoLuong()+"");
                tvSold.setText(moHinh.getSoLuongDaBan()+ "");
                tvType.setText(loaiMoHinh.getTenLoai());
                tvMaterial.setText(moHinh.getChatLieu());
                tvRatio.setText(moHinh.getTiLe());
                tvHeight.setText(moHinh.getChieuCao() +"cm");
                tvRemaining.setText((moHinh.getSoLuong() - moHinh.getSoLuongDaBan()) + "");
                tvDate.setText(moHinh.getNgaySx());
                tvSex.setText(moHinh.getGioiTinh());
                tvAgeLimit.setText(moHinh.getGioiHanDoTuoi()+"");
                tvOrigin.setText(moHinh.getXuatXu());
            }
        });
    }

    private void initView() {
        tvName = findViewById(R.id.tv_nameDetailSpAdmin);
        tvRating = findViewById(R.id.tv_ratingDetailSpAdmin);
        tvPrice = findViewById(R.id.tv_priceDetailSpAdmin);
        tvAmount = findViewById(R.id.tv_amountDetailSpAdmin);
        tvSold  = findViewById(R.id.tv_soldDetailSpAdmin);
        tvType  =findViewById(R.id.tv_typeDetailSpAdmin);
        tvMaterial = findViewById(R.id.tv_materialDetailSpAdmin);
        tvRatio = findViewById(R.id.tv_ratioDetailSpAdmin);
        tvHeight = findViewById(R.id.tv_heightDetailSpAdmin);
        tvRemaining = findViewById(R.id.tv_remainingDetailSpAdmin);
        tvDate= findViewById(R.id.tv_dateDetailSpAdmin);
        tvSex = findViewById(R.id.tv_sexDetailSpAdmin);
        tvAgeLimit = findViewById(R.id.tv_ageDetailSpAdmin);
        tvOrigin = findViewById(R.id.tv_originDetailSpAdmin);
        btnUpdate =findViewById(R.id.btn_updateDetailAdmin);
        btnDelete = findViewById(R.id.btn_deleteDetailSpAdmin);
        btnExit = findViewById(R.id.btn_exitDetailSpAdmin);
        img = findViewById(R.id.img_moHinhDetailMhAdmin);
    }

    private void showDialogDelete(int maMh, String tenMh) {
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_warning, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewDialog);
        AlertDialog dialog = builder.create();

        ImageButton btnCancel = viewDialog.findViewById(R.id.btn_closeWarningDialog);
        Button btnConFirm = viewDialog.findViewById(R.id.btn_conFirmWarningDialog);
        TextView tvTitle = viewDialog.findViewById(R.id.tv_titleWarningDialog);
        TextView tvMess = viewDialog.findViewById(R.id.tv_messageWarningDialog);

        tvTitle.setText(tenMh);
        tvMess.setText("Bạn có chắc chắn muốn xóa " + tenMh);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConFirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSubDialog(maMh);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showSubDialog(int maMh) {
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_error, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewDialog);
        AlertDialog dialog = builder.create();

        ImageButton btnCancel = viewDialog.findViewById(R.id.btn_closeErrorDialog);
        Button btnConFirm = viewDialog.findViewById(R.id.btn_conFirmErrorDialog);
        TextView tvTitle = viewDialog.findViewById(R.id.tv_titleErrorDialog);
        TextView tvMess = viewDialog.findViewById(R.id.tv_messageErrorDialog);

        tvTitle.setText("CẢNH BÁO");
        tvMess.setText("!!! Xóa mô hình, các hóa đơn liên quan đến mô hình sẽ bị xóa !!!");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConFirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moHinhDao.deleteMoHinh(maMh);
                CustomToast customToast = new CustomToast(DetailMoHinhActivity.this, CustomToast.SUCCESS, "Xóa thành công!");
                customToast.showToast();
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    private void getData() {
        moHinhDao = new MoHinhDao(this);
        Intent intent  = getIntent();
        mamh = intent.getExtras().getInt("mamh", -1);
    }

}