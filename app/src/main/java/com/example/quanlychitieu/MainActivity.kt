package com.example.quanlychitieu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtTenKhoanChi_346: EditText
    private lateinit var edtSoTien_346: EditText
    private lateinit var edtGhiChu_346: EditText

    private lateinit var btnThemKhoanChi_346: Button
    private lateinit var btnXemChiTiet_346: Button

    override fun onCreate(trangThai_346: Bundle?) {
        super.onCreate(trangThai_346)
        setContentView(R.layout.activity_main)

        supportActionBar?.title =
            "Nguyễn Hữu Tú - MSSV_CUA_BAN - Quản lý chi tiêu cá nhân"

        edtTenKhoanChi_346 = findViewById(R.id.edtTenKhoanChi_346)
        edtSoTien_346 = findViewById(R.id.edtSoTien_346)
        edtGhiChu_346 = findViewById(R.id.edtGhiChu_346)

        btnThemKhoanChi_346 = findViewById(R.id.btnThemKhoanChi_346)
        btnXemChiTiet_346 = findViewById(R.id.btnXemChiTiet_346)

        btnThemKhoanChi_346.setOnClickListener {
            if (kiemTraDuLieu_346()) {
                Toast.makeText(
                    this,
                    "Thêm khoản chi thành công",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnXemChiTiet_346.setOnClickListener {
            if (kiemTraDuLieu_346()) {
                val chuyenManHinh_346 =
                    Intent(this, ChiTietChiTieuActivity::class.java)

                startActivity(chuyenManHinh_346)
            }
        }
    }

    private fun kiemTraDuLieu_346(): Boolean {
        val tenKhoanChi_346 =
            edtTenKhoanChi_346.text.toString().trim()

        val soTien_346 =
            edtSoTien_346.text.toString().trim().toDoubleOrNull()

        if (tenKhoanChi_346.isBlank()) {
            edtTenKhoanChi_346.error = "Tên khoản chi không được để trống"
            edtTenKhoanChi_346.requestFocus()
            return false
        }

        if (soTien_346 == null ||
            !soTien_346.isFinite() ||
            soTien_346 <= 0
        ) {
            edtSoTien_346.error = "Số tiền phải là số hợp lệ lớn hơn 0"
            edtSoTien_346.requestFocus()
            return false
        }

        return true
    }
}