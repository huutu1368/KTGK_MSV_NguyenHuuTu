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

    // Lưu khoản chi đã thêm trong bộ nhớ
    private var khoanChi_346: KhoanChi? = null

    override fun onCreate(trangThai_346: Bundle?) {
        super.onCreate(trangThai_346)
        setContentView(R.layout.activity_main)

        supportActionBar?.title =
            "Nguyễn Hữu Tú - 2415053122346 - Quản lý chi tiêu cá nhân"

        // Ánh xạ các thành phần giao diện
        edtTenKhoanChi_346 = findViewById(R.id.edtTenKhoanChi_346)
        edtSoTien_346 = findViewById(R.id.edtSoTien_346)
        edtGhiChu_346 = findViewById(R.id.edtGhiChu_346)

        btnThemKhoanChi_346 = findViewById(R.id.btnThemKhoanChi_346)
        btnXemChiTiet_346 = findViewById(R.id.btnXemChiTiet_346)

        // Bước 5.3: Điền dữ liệu mẫu khi mở màn hình lần đầu
        if (trangThai_346 == null) {
            edtTenKhoanChi_346.setText("In tài liệu Kotlin của Tú")
            edtSoTien_346.setText("27000")
            edtGhiChu_346.setText(
                "In 18 trang bài thực hành buổi sáng"
            )
        }

        // Xử lý nút Thêm khoản chi
        btnThemKhoanChi_346.setOnClickListener {
            if (kiemTraDuLieu_346()) {
                khoanChi_346 = KhoanChi(
                    maKhoanChi_346 = "KC346_01",

                    tenKhoanChi_346 =
                        edtTenKhoanChi_346.text.toString().trim(),

                    soTien_346 =
                        edtSoTien_346.text.toString().trim().toDouble(),

                    ghiChu_346 =
                        edtGhiChu_346.text.toString().trim(),

                    daThanhToan_346 = false
                )

                Toast.makeText(
                    this,
                    "Đã thêm khoản chi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Xử lý nút Xem chi tiết khoản chi
        btnXemChiTiet_346.setOnClickListener {
            val khoanChiHienTai_346 = khoanChi_346

            if (khoanChiHienTai_346 == null) {
                Toast.makeText(
                    this,
                    "Bạn cần thêm khoản chi trước",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val chuyenManHinh_346 =
                    Intent(this, ChiTietChiTieuActivity::class.java)

                chuyenManHinh_346.putExtra(
                    "tenKhoanChi_346",
                    khoanChiHienTai_346.tenKhoanChi_346
                )

                chuyenManHinh_346.putExtra(
                    "soTien_346",
                    khoanChiHienTai_346.soTien_346
                )

                chuyenManHinh_346.putExtra(
                    "ghiChu_346",
                    khoanChiHienTai_346.ghiChu_346
                )

                startActivity(chuyenManHinh_346)
            }
        }
    }

    // Kiểm tra dữ liệu nhập
    private fun kiemTraDuLieu_346(): Boolean {
        val tenKhoanChi_346 =
            edtTenKhoanChi_346.text.toString().trim()

        val soTien_346 =
            edtSoTien_346.text.toString().trim().toDoubleOrNull()

        // Xóa thông báo lỗi cũ
        edtTenKhoanChi_346.error = null
        edtSoTien_346.error = null

        if (tenKhoanChi_346.isBlank()) {
            edtTenKhoanChi_346.error =
                "Tên khoản chi không được để trống"

            edtTenKhoanChi_346.requestFocus()
            return false
        }

        if (soTien_346 == null ||
            !soTien_346.isFinite() ||
            soTien_346 <= 0
        ) {
            edtSoTien_346.error =
                "Số tiền phải là số hợp lệ lớn hơn 0"

            edtSoTien_346.requestFocus()
            return false
        }

        return true
    }
}