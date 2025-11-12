package quanlyphongmachcosoyte;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Sửa: Thêm import

// Sửa: Thêm hàm xoaBN, suaBN
// Sửa: Sửa logic timKiemBN
public class QL_BenhNhan {
    private List<BenhNhan> danhSachBN;

    // Constructor
    public QL_BenhNhan() {
        this.danhSachBN = new ArrayList<>();
    }

    // Thêm bệnh nhân
    public void themBN(BenhNhan bn) {
        this.danhSachBN.add(bn);
        System.out.println("Da them benh nhan " + bn.hoTen + " vao danh sach.");
    }

    // Tìm bệnh nhân theo Mã Bệnh Nhân
    public BenhNhan timKiemBN(String maBN) {
        for (BenhNhan bn : danhSachBN) {
            // Sửa: Tìm theo getMaBN()
            if (bn.getMaBN().equalsIgnoreCase(maBN)) {
                return bn;
            }
        }
        return null;
    }

    // Xuất toàn bộ danh sách bệnh nhân
    public void xuatDanhSach() {
        if (danhSachBN.isEmpty()) {
            System.out.println("Danh sach benh nhan rong.");
            return;
        }
        System.out.println("--- DANH SACH BENH NHAN ---");
        for (BenhNhan bn : danhSachBN) {
            bn.xuat(); // Gọi hàm xuat() của mỗi bệnh nhân
            System.out.println("--------------------");
        }
    }

    // Sửa: Thêm hàm xóa
    public void xoaBN(String maBN) {
        BenhNhan bn = timKiemBN(maBN);
        if (bn != null) {
            danhSachBN.remove(bn);
            System.out.println("Da xoa benh nhan: " + bn.hoTen);
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }

    // Sửa: Thêm hàm sửa
    public void suaBN(String maBN, Scanner scanner) {
        BenhNhan bn = timKiemBN(maBN);
        if (bn != null) {
            System.out.println("Tim thay benh nhan: " + bn.hoTen);
            System.out.print("Nhap benh ly moi (enter de bo qua): ");
            String benhLyMoi = scanner.nextLine();
            if (!benhLyMoi.trim().isEmpty()) {
                bn.setBenhLy(benhLyMoi);
                System.out.println("Da cap nhat benh ly!");
            }
            // Thêm các câu hỏi sửa thông tin khác (họ tên, sđt...) ở đây
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }
}