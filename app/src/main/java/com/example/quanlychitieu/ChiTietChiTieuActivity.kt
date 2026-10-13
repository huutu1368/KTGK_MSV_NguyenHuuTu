package com.example.quanlychitieu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var txtThongTin_346: TextView
    private lateinit var khoanChi_346: KhoanChi

    private val moChinhSua_346 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua_346 ->

        if (ketQua_346.resultCode == Activity.RESULT_OK) {
            val duLieu_346 = ketQua_346.data

            val khoanChiMoi_346 = khoanChi_346.copy(
                tenKhoanChi_346 =
                    duLieu_346?.getStringExtra("tenKhoanChi_346")
                        .orEmpty().trim(),

                soTien_346 =
                    duLieu_346?.getDoubleExtra("soTien_346", 0.0)
                        ?: 0.0,

                ghiChu_346 =
                    duLieu_346?.getStringExtra("ghiChu_346")
                        .orEmpty()
            )

            if (khoanChiMoi_346.tenKhoanChi_346.isNotBlank() &&
                khoanChiMoi_346.laSoTienHopLe_346()
            ) {
                khoanChi_346 = khoanChiMoi_346
                hienThiThongTin_346()
            } else {
                Toast.makeText(
                    this,
                    "Dữ liệu chỉnh sửa không hợp lệ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreate(trangThai_346: Bundle?) {
        super.onCreate(trangThai_346)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        supportActionBar?.title =
            "Nguyễn Hữu Tú - MSSV_CUA_BAN - Quản lý chi tiêu cá nhân"

        txtThongTin_346 = findViewById(R.id.txtThongTin_346)

        khoanChi_346 = KhoanChi(
            maKhoanChi_346 = "KC346_01",

            tenKhoanChi_346 =
                intent.getStringExtra("tenKhoanChi_346").orEmpty().trim(),

            soTien_346 =
                intent.getDoubleExtra("soTien_346", 0.0),

            ghiChu_346 =
                intent.getStringExtra("ghiChu_346").orEmpty(),

            daThanhToan_346 = false
        )

        if (khoanChi_346.tenKhoanChi_346.isBlank() ||
            !khoanChi_346.laSoTienHopLe_346()
        ) {
            Toast.makeText(
                this,
                "Thiếu tên khoản chi hoặc số tiền không hợp lệ",
                Toast.LENGTH_LONG
            ).show()

            finish()
            return
        }

        hienThiThongTin_346()

        val btnChinhSua_346 =
            findViewById<Button>(R.id.btnChinhSua_346)

        btnChinhSua_346.isEnabled = true

        btnChinhSua_346.setOnClickListener {
            val chuyenManHinh_346 =
                Intent(this, ChinhSuaChiTieuActivity::class.java)

            chuyenManHinh_346.putExtra(
                "tenKhoanChi_346",
                khoanChi_346.tenKhoanChi_346
            )

            chuyenManHinh_346.putExtra(
                "soTien_346",
                khoanChi_346.soTien_346
            )

            chuyenManHinh_346.putExtra(
                "ghiChu_346",
                khoanChi_346.ghiChu_346
            )

            moChinhSua_346.launch(chuyenManHinh_346)
        }

        findViewById<Button>(R.id.btnQuayLai_346).setOnClickListener {
            finish()
        }
    }

    private fun hienThiThongTin_346() {
        txtThongTin_346.text = khoanChi_346.layThongTin_346()
    }
}