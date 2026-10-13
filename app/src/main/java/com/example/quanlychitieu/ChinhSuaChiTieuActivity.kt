package com.example.quanlychitieu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    private lateinit var edtTenKhoanChi_346: EditText
    private lateinit var edtSoTien_346: EditText
    private lateinit var edtGhiChu_346: EditText

    override fun onCreate(trangThai_346: Bundle?) {
        super.onCreate(trangThai_346)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)

        supportActionBar?.title =
            "Nguyễn Hữu Tú - MSSV_CUA_BAN - Quản lý chi tiêu cá nhân"

        edtTenKhoanChi_346 = findViewById(R.id.edtTenKhoanChi_346)
        edtSoTien_346 = findViewById(R.id.edtSoTien_346)
        edtGhiChu_346 = findViewById(R.id.edtGhiChu_346)

        // Chỉ điền dữ liệu ban đầu ở lần mở đầu tiên.
        if (trangThai_346 == null) {
            edtTenKhoanChi_346.setText(
                intent.getStringExtra("tenKhoanChi_346")
            )

            edtSoTien_346.setText(
                intent.getDoubleExtra("soTien_346", 0.0).toString()
            )

            edtGhiChu_346.setText(
                intent.getStringExtra("ghiChu_346")
            )
        }

        findViewById<Button>(R.id.btnLuu_346).setOnClickListener {
            luuChinhSua_346()
        }

        findViewById<Button>(R.id.btnHuy_346).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun luuChinhSua_346() {
        val tenKhoanChi_346 =
            edtTenKhoanChi_346.text.toString().trim()

        val soTien_346 =
            edtSoTien_346.text.toString().trim().toDoubleOrNull()

        val ghiChu_346 =
            edtGhiChu_346.text.toString().trim()

        if (tenKhoanChi_346.isBlank()) {
            edtTenKhoanChi_346.error = "Tên khoản chi không được để trống"
            edtTenKhoanChi_346.requestFocus()
            return
        }

        if (soTien_346 == null ||
            !soTien_346.isFinite() ||
            soTien_346 <= 0
        ) {
            edtSoTien_346.error = "Số tiền phải là số hợp lệ lớn hơn 0"
            edtSoTien_346.requestFocus()
            return
        }

        val duLieuTraVe_346 = Intent()

        duLieuTraVe_346.putExtra("tenKhoanChi_346", tenKhoanChi_346)
        duLieuTraVe_346.putExtra("soTien_346", soTien_346)
        duLieuTraVe_346.putExtra("ghiChu_346", ghiChu_346)

        setResult(Activity.RESULT_OK, duLieuTraVe_346)
        finish()
    }
}