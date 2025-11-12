package quanlyphongmachcosoyte;

import java.util.ArrayList;
import java.util.List;

// Sửa: Dùng dv.tinhChiPhi() thay vì dv.giaTien
public class HoaDon implements ThanhToan {
    private String maHoaDon;
    private String maBN;
    private String ngayLap;
    private List<DichVuYTe> danhSachDichVuSuDung;

    // Constructor
    public HoaDon(String maHoaDon, String maBN, String ngayLap) {
        this.maHoaDon = maHoaDon;
        this.maBN = maBN;
        this.ngayLap = ngayLap;
        this.danhSachDichVuSuDung = new ArrayList<>();
    }

    // Thêm một dịch vụ vào hóa đơn
    public void themDichVu(DichVuYTe dv) {
        this.danhSachDichVuSuDung.add(dv);
        System.out.println("Da them dich vu " + dv.tenDV + " vao hoa don.");
    }

    // Phương thức bắt buộc phải có do implements ThanhToan
    @Override
    public double tinhtongtien() {
        double tongTien = 0;
        for (DichVuYTe dv : danhSachDichVuSuDung) {
            // Sửa: SỬA DỤNG TÍNH ĐA HÌNH
            // Tự động gọi tinhChiPhi() của Thuoc hoặc XetNghiem
            tongTien += dv.tinhChiPhi();
        }
        return tongTien;
    }

    // Phương thức xuất thông tin hóa đơn
    public void xuatHoaDon() {
        System.out.println("--- THONG TIN HOA DON ---");
        System.out.println("Ma Hoa Don: " + this.maHoaDon);
        System.out.println("Ma Benh Nhan: " + this.maBN);
        System.out.println("Ngay Lap: " + this.ngayLap);
        System.out.println("--- Chi tiet dich vu ---");

        if (danhSachDichVuSuDung.isEmpty()) {
            System.out.println("Khong su dung dich vu nao.");
        } else {
            for (DichVuYTe dv : danhSachDichVuSuDung) {
                // Sửa: Hiển thị chi phí đã tính (đa hình)
                System.out.println("- " + dv.tenDV + ": " + dv.tinhChiPhi() + " VND");
            }
        }

        System.out.println("-------------------------");
        System.out.println("==> TONG TIEN: " + tinhtongtien() + " VND");
        System.out.println("-------------------------");
    }

    // Getter
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getMaBN() {
        return maBN;
    }
}