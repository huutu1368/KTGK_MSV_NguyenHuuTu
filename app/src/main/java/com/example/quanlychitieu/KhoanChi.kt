package com.example.quanlychitieu

data class KhoanChi(
    val maKhoanChi_346: String,
    val tenKhoanChi_346: String,
    val soTien_346: Double,
    val ghiChu_346: String,
    val daThanhToan_346: Boolean
) {

    fun laSoTienHopLe_346(): Boolean {
        return soTien_346.isFinite() && soTien_346 > 0
    }

    fun layThongTin_346(): String {
        val trangThai_346 =
            if (daThanhToan_346) "Đã thanh toán" else "Chưa thanh toán"

        return "Mã khoản chi: $maKhoanChi_346\n\n" +
                "Tên khoản chi: $tenKhoanChi_346\n\n" +
                "Số tiền: $soTien_346 VNĐ\n\n" +
                "Ghi chú: ${ghiChu_346.ifBlank { "Không có" }}\n\n" +
                "Trạng thái: $trangThai_346"
    }
}