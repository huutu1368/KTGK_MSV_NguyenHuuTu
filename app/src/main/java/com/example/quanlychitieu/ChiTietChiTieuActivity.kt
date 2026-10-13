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
    private lateinit var btnChinhSua_346: Button
    private lateinit var btnQuayLai_346: Button

    private lateinit var khoanChi_346: KhoanChi

    // Nhận kết quả từ màn hình chỉnh sửa
    private val moChinhSua_346 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua_346 ->

        if (ketQua_346.resultCode == Activity.RESULT_OK) {
            val duLieu_346 = ketQua_346.data

            // Tạo bản mới, giữ nguyên mã và trạng thái thanh toán
            val khoanChiMoi_346 = khoanChi_346.copy(
                tenKhoanChi_346 =
                    duLieu_346?.getStringExtra("tenKhoanChi_346")
                        .orEmpty()
                        .trim(),

                soTien_346 =
                    duLieu_346?.getDoubleExtra("soTien_346", 0.0)
                        ?: 0.0,

                ghiChu_346 =
                    duLieu_346?.getStringExtra("ghiChu_346")
                        .orEmpty()
            )

            // Chỉ cập nhật nếu dữ liệu hợp lệ
            if (khoanChiMoi_346.tenKhoanChi_346.isNotBlank() &&
                khoanChiMoi_346.laSoTienHopLe_346()
            ) {
                khoanChi_346 = khoanChiMoi_346
                hienThiThongTin_346()

                Toast.makeText(
                    this,
                    "Đã cập nhật khoản chi",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Dữ liệu chỉnh sửa không hợp lệ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Khi Hủy hoặc nhấn Back, giữ nguyên khoản chi.
    }

    override fun onCreate(trangThai_346: Bundle?) {
        super.onCreate(trangThai_346)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        supportActionBar?.title =
            "Nguyễn Hữu Tú - 2415053122346 - Quản lý chi tiêu cá nhân"

        // Ánh xạ giao diện
        txtThongTin_346 = findViewById(R.id.txtThongTin_346)
        btnChinhSua_346 = findViewById(R.id.btnChinhSua_346)
        btnQuayLai_346 = findViewById(R.id.btnQuayLai_346)

        // Khôi phục dữ liệu khi Activity được tạo lại
        khoanChi_346 = if (trangThai_346 != null) {
            KhoanChi(
                maKhoanChi_346 =
                    trangThai_346.getString("maKhoanChi_346")
                        .orEmpty(),

                tenKhoanChi_346 =
                    trangThai_346.getString("tenKhoanChi_346")
                        .orEmpty(),

                soTien_346 =
                    trangThai_346.getDouble("soTien_346", 0.0),

                ghiChu_346 =
                    trangThai_346.getString("ghiChu_346")
                        .orEmpty(),

                daThanhToan_346 =
                    trangThai_346.getBoolean("daThanhToan_346", false)
            )
        } else {
            // Lần mở đầu tiên: nhận dữ liệu từ MainActivity
            KhoanChi(
                maKhoanChi_346 =
                    intent.getStringExtra("maKhoanChi_346")
                        ?: "KC346_01",

                tenKhoanChi_346 =
                    intent.getStringExtra("tenKhoanChi_346")
                        .orEmpty()
                        .trim(),

                soTien_346 =
                    intent.getDoubleExtra("soTien_346", 0.0),

                ghiChu_346 =
                    intent.getStringExtra("ghiChu_346")
                        .orEmpty(),

                daThanhToan_346 =
                    intent.getBooleanExtra("daThanhToan_346", false)
            )
        }

        // Kiểm tra khoản chi trước khi hiển thị
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

        // Bật và xử lý nút Chỉnh sửa
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

        // Quay lại màn hình chính
        btnQuayLai_346.setOnClickListener {
            finish()
        }
    }

    // Hiển thị thông tin và trạng thái thanh toán
    private fun hienThiThongTin_346() {
        txtThongTin_346.text = khoanChi_346.layThongTin_346()
    }

    // Lưu khoản chi hiện tại trước khi Activity được tạo lại
    override fun onSaveInstanceState(trangThai_346: Bundle) {
        if (::khoanChi_346.isInitialized) {
            trangThai_346.putString(
                "maKhoanChi_346",
                khoanChi_346.maKhoanChi_346
            )

            trangThai_346.putString(
                "tenKhoanChi_346",
                khoanChi_346.tenKhoanChi_346
            )

            trangThai_346.putDouble(
                "soTien_346",
                khoanChi_346.soTien_346
            )

            trangThai_346.putString(
                "ghiChu_346",
                khoanChi_346.ghiChu_346
            )

            trangThai_346.putBoolean(
                "daThanhToan_346",
                khoanChi_346.daThanhToan_346
            )
        }

        super.onSaveInstanceState(trangThai_346)
    }
}