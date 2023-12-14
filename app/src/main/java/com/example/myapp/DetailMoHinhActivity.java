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

    private void showSubDialog(int maMh) {
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_error, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewDialog);
        AlertDialog dialog = builder.create();

        btnConFirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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