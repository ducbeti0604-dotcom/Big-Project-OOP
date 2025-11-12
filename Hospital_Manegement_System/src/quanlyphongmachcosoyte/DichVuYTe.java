package quanlyphongmachcosoyte;

// Sửa: Thêm abstract tinhChiPhi() và setGiaTien()
// Sửa: Xóa 'static' khỏi giaTien
abstract public class DichVuYTe {
    protected String maDV;
    protected String tenDV;
    protected double giaTien; // Sửa: Xóa 'static'
    protected char xeploai;

    public DichVuYTe(String maDV, String tenDV, double giaTien, char xeploai) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaTien = giaTien; // Sửa: Gán giá tiền
        this.xeploai = xeploai;
    }

    abstract public void HienThi();

    // Sửa: Thêm hàm trừu tượng này (sửa lỗi cho Thuoc, XetNghiem)
    abstract public double tinhChiPhi();

    // Sửa: Thêm hàm này để QL_DichVu có thể sửa giá
    // DÒNG NÀY SẼ SỬA LỖI Ở ẢNH 2
    public void setGiaTien(double giaMoi) {
        if (giaMoi > 0) {
            this.giaTien = giaMoi;
        }
    }
}