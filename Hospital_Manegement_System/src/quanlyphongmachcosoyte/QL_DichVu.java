package quanlyphongmachcosoyte;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Sửa: Thêm import

// Sửa: Thêm hàm xoaDV, suaDV
public class QL_DichVu {
    private List<DichVuYTe> danhSachDV;

    public QL_DichVu() {
        this.danhSachDV = new ArrayList<>();
    }

    // Thêm dịch vụ (có thể là Thuốc hoặc Xét Nghiệm)
    public void themDV(DichVuYTe dv) {
        this.danhSachDV.add(dv);
        System.out.println("Da them dich vu " + dv.tenDV + " vao danh sach.");
    }

    // Tìm dịch vụ theo Mã Dịch Vụ
    public DichVuYTe timKiemDV(String maDV) {
        for (DichVuYTe dv : danhSachDV) {
            if (dv.maDV.equalsIgnoreCase(maDV)) {
                return dv;
            }
        }
        return null;
    }

    // Xuất toàn bộ danh sách dịch vụ
    public void xuatDanhSach() {
        if (danhSachDV.isEmpty()) {
            System.out.println("Danh sach dich vu rong.");
            return;
        }
        System.out.println("--- DANH SACH DICH VU Y TE ---");
        for (DichVuYTe dv : danhSachDV) {
            dv.HienThi();
            System.out.println("-------------------------");
        }
    }

    // Sửa: Thêm hàm xóa
    public void xoaDV(String maDV) {
        DichVuYTe dv = timKiemDV(maDV);
        if (dv != null) {
            danhSachDV.remove(dv);
            System.out.println("Da xoa dich vu: " + dv.tenDV);
        } else {
            System.out.println("Khong tim thay dich vu voi ma: " + maDV);
        }
    }

    // Sửa: Thêm hàm sửa
    public void suaDV(String maDV, Scanner scanner) {
        DichVuYTe dv = timKiemDV(maDV);
        if (dv != null) {
            System.out.println("Tim thay dich vu: " + dv.tenDV);
            System.out.println("Gia goc hien tai: " + dv.giaTien);
            System.out.print("Nhap gia tien moi: ");
            try {
                double giaMoi = scanner.nextDouble();
                scanner.nextLine(); // Tiêu thụ ký tự enter
                dv.setGiaTien(giaMoi);
                System.out.println("Da cap nhat gia thanh cong!");
            } catch (Exception e) {
                System.out.println("Loi: Vui long nhap so. Huy cap nhat.");
                scanner.nextLine(); // Xóa bộ đệm nếu nhập sai
            }
        } else {
            System.out.println("Khong tim thay dich vu voi ma: " + maDV);
        }
    }
}