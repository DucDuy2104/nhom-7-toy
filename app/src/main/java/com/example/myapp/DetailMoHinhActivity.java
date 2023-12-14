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

    private void initView() {

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