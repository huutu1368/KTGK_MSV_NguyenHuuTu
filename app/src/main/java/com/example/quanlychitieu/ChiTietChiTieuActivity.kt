package com.example.quanlychitieu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var txtThongTin_346: TextView
    private lateinit var btnQuayLai_346: Button

    private var tenKhoanChi_346 = ""
    private var soTien_346 = 0.0
    private var ghiChu_346 = ""

    override fun onCreate(trangThai_346: Bundle?) {
        super.onCreate(trangThai_346)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        // Hiển thị tiêu đề ActionBar
        supportActionBar?.title =
            "Nguyễn Hữu Tú - 2415053122346 - Quản lý chi tiêu cá nhân"

        // Ánh xạ giao diện
        txtThongTin_346 = findViewById(R.id.txtThongTin_346)
        btnQuayLai_346 = findViewById(R.id.btnQuayLai_346)

        // Nhận dữ liệu từ MainActivity
        tenKhoanChi_346 =
            intent.getStringExtra("tenKhoanChi_346")
                .orEmpty()
                .trim()

        soTien_346 =
            intent.getDoubleExtra("soTien_346", 0.0)

        ghiChu_346 =
            intent.getStringExtra("ghiChu_346").orEmpty()

        // Bước 2.5: Kiểm tra dữ liệu nhận được
        if (tenKhoanChi_346.isBlank() ||
            !soTien_346.isFinite() ||
            soTien_346 <= 0
        ) {
            Toast.makeText(
                this,
                "Thiếu tên khoản chi hoặc số tiền không hợp lệ",
                Toast.LENGTH_LONG
            ).show()

            // Đóng màn hình chi tiết nếu dữ liệu không hợp lệ
            finish()
            return
        }

        // Dữ liệu hợp lệ thì hiển thị
        hienThiThongTin_346()

        // Quay lại màn hình chính
        btnQuayLai_346.setOnClickListener {
            finish()
        }
    }

    private fun hienThiThongTin_346() {
        txtThongTin_346.text =
            "Tên khoản chi: $tenKhoanChi_346\n\n" +
                    "Số tiền: $soTien_346 VNĐ\n\n" +
                    "Ghi chú: ${ghiChu_346.ifBlank { "Không có" }}"
    }
}