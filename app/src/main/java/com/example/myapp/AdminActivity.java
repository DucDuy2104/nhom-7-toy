package com.example.myapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.duanmau1.admin.fragment.HomeAdminFragment;
import com.example.duanmau1.dao.NguoiDungDao;
import com.example.duanmau1.dao.SharePreNguoiDung;
import com.example.duanmau1.model.NguoiDung;
import com.example.duanmau1.sign.SignInActivity;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity {

    private void setNavOnItemSelect() {

    }

    private void setDialog() {
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_change_pass, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewDialog);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nguoiDungDao.getNguoiDungById(mand, new NguoiDungDao.OnGetDataSuccess() {
                    @Override
                    public void onGetNdSuccess(NguoiDung nguoiDung) {
                        i if (!newPass.trim().equals(rePass)) {
                            Toast.makeText(AdminActivity.this, "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
                            return;
                        }else  {
                            nguoiDung.setMatKhau(newPass);
                            nguoiDungDao.updateNguoiDung(nguoiDung);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        dialog.show();


    }


}