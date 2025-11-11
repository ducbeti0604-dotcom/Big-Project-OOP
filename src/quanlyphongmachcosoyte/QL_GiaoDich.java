package quanlyphongmachcosoyte;
import java.util.ArrayList;
import java.util.List;
public class QL_GiaoDich {
	private List<HoaDon> danhSachHoaDon;

    public QL_GiaoDich() {
        this.danhSachHoaDon = new ArrayList<>();
    }

    // Thêm một hóa đơn mới
    public void themHoaDon(HoaDon hd) {
        this.danhSachHoaDon.add(hd);
        System.out.println("Da lap hoa don " + hd.getMaHoaDon() + " cho BN " + hd.getMaBN());
    }

    // Tìm hóa đơn theo Mã Hóa Đơn
    public HoaDon timKiemHD(String maHD) {
        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getMaHoaDon().equalsIgnoreCase(maHD)) {
                return hd;
            }
        }
        return null;
    }

    // Xuất toàn bộ lịch sử giao dịch
    public void xuatLichSuGiaoDich() {
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Chua co giao dich nao.");
            return;
        }
        System.out.println("--- LICH SU GIAO DICH (HOA DON) ---");
        for (HoaDon hd : danhSachHoaDon) {
            hd.xuatHoaDon(); // Gọi hàm xuatHoaDon() của mỗi hóa đơn
        }
    }

    // Tính tổng doanh thu từ tất cả hóa đơn
    public double tinhTongDoanhThu() {
        double tong = 0;
        for (HoaDon hd : danhSachHoaDon) {
            tong += hd.tinhtongtien();
        }
        return tong;
    }
}

