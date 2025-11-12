package quanlyphongmachcosoyte;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_NhanVien {
    private List<NhanVien> danhSachNV;

    // Constructor
    public QL_NhanVien() {
        this.danhSachNV = new ArrayList<>();
    }

    /**
     * Thêm một nhân viên mới (có thể là Bác Sĩ, Điều Dưỡng, KTV
     * nhờ vào tính đa hình).
     */
    public void themNV(NhanVien nv) {
        // Kiểm tra xem mã NV đã tồn tại chưa
        if (timKiemNV(nv.getMaNV()) != null) {
            System.out.println("Loi: Ma NV " + nv.getMaNV() + " da ton tai.");
            return;
        }
        this.danhSachNV.add(nv);
        System.out.println("Da them nhan vien: " + nv.hoTen);
    }

    /**
     * Tìm kiếm nhân viên theo Mã NV.
     * Trả về đối tượng NhanVien nếu tìm thấy, ngược lại trả về null.
     */
    public NhanVien timKiemNV(String maNV) {
        for (NhanVien nv : danhSachNV) {
            // Sử dụng getMaNV()
            if (nv.getMaNV().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }

    /**
     * Xóa nhân viên khỏi danh sách dựa trên mã NV.
     */
    public void xoaNV(String maNV) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            danhSachNV.remove(nv);
            System.out.println("Da xoa nhan vien: " + nv.hoTen + " (Ma: " + maNV + ")");
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

    /**
     * Sửa thông tin nhân viên (ví dụ: sửa hệ số lương).
     * Thuộc tính heSoLuong là 'protected' trong NhanVien,
     * nên ta có thể truy cập trực tiếp.
     */
    public void suaNV(String maNV, Scanner scanner) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            System.out.println("Tim thay nhan vien: " + nv.hoTen);
            System.out.print("Nhap He So Luong moi (enter de bo qua): ");
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                try {
                    double heSoMoi = Double.parseDouble(input);
                    nv.heSoLuong = heSoMoi; // 'heSoLuong' là protected, có thể truy cập
                    System.out.println("Da cap nhat he so luong!");
                } catch (Exception e) {
                    System.out.println("Loi: Nhap sai dinh dang so.");
                }
            }
            // Bạn có thể thêm các câu hỏi để sửa các thông tin khác ở đây
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

    /**
     * Xuất danh sách toàn bộ nhân viên, sử dụng tính đa hình của hàm xuat().
     */
    public void xuatDanhSach() {
        if (danhSachNV.isEmpty()) {
            System.out.println("Danh sach nhan vien rong.");
            return;
        }
        System.out.println("--- DANH SACH TOAN BO NHAN VIEN ---");
        for (NhanVien nv : danhSachNV) {
            nv.xuat(); 
            System.out.println("---------------------------------");
        }
    }

    /**
     * Tính tổng quỹ lương phải trả cho tất cả nhân viên trong tháng.
     * Sử dụng tính đa hình của hàm TinhLuong().
     */
    public void tinhTongQuyLuong() {
        double tongLuong = 0;
        if (danhSachNV.isEmpty()) {
            System.out.println("Chua co nhan vien nao de tinh luong.");
            return;
        }
        
        for (NhanVien nv : danhSachNV) {
            // Tự động gọi TinhLuong() của lớp con
            tongLuong += nv.TinhLuong(); 
        }
        System.out.println("=================================");
        System.out.println("==> TONG QUY LUONG THANG: " + String.format("%.0f", tongLuong) + " VND");
        System.out.println("=================================");
    }
}