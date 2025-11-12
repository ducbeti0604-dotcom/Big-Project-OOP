package quanlyphongmachcosoyte;

import java.util.Scanner;

// Sửa: Cập nhật các hàm new Thuoc() và new XetNghiem() cho đúng
public class CoSoYTe {
    private static QL_BenhNhan qlBenhNhan = new QL_BenhNhan();
    private static QL_DichVu qlDichVu = new QL_DichVu();
    private static QL_GiaoDich qlGiaoDich = new QL_GiaoDich();
    private static QL_NhanVien qlNhanVien = new QL_NhanVien(); // <--- THÊM MỚI
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        themDuLieuMau();

        boolean chay = true;
        while (chay) {
            hienThiMenuChinh();
            System.out.print("Chon chuc nang (0-5): "); // <--- SỬA SỐ
            int luaChon = -1; 
            try {
                 luaChon = scanner.nextInt();
            } catch (Exception e) {
                 System.out.println("Vui long nhap SO.");
            }
            
            scanner.nextLine();

            switch (luaChon) {
                case 1:
                    menuQuanLyBenhNhan();
                    break;
                case 2:
                    menuQuanLyDichVu();
                    break;
                case 3: // <--- THÊM CASE NÀY
                    menuQuanLyNhanVien();
                    break;
                case 4: // <--- SỬA SỐ
                    menuLapHoaDon();
                    break;
                case 5: // <--- SỬA SỐ
                    menuThongKe();
                    break;
                case 0:
                    chay = false;
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }

    // Menu chính
    public static void hienThiMenuChinh() {
        System.out.println("\n--- HE THONG QUAN LY CO SO Y TE ---");
        System.out.println("1. Quan ly Benh Nhan");
        System.out.println("2. Quan ly Dich Vu Y Te");
        System.out.println("3. Quan ly Nhan Vien");        // <--- THÊM MỚI
        System.out.println("4. Lap Hoa Don Thanh Toan");   // Sửa số
        System.out.println("5. Thong ke & Xuat danh sach"); // Sửa số
        System.out.println("0. Thoat chuong trinh");
    }

    // Menu con 1
    public static void menuQuanLyBenhNhan() {
        System.out.println("-- Menu Quan Ly Benh Nhan --");
        System.out.println("1. Them benh nhan moi");
        System.out.println("2. Xoa benh nhan");
        System.out.println("3. Sua thong tin benh nhan");
        System.out.println("4. Xuat danh sach benh nhan");
        System.out.println("0. Quay lai menu chinh");
        System.out.print("Chon: ");
        int chon = scanner.nextInt();
        scanner.nextLine();
        String maBN;

        switch (chon) {
            case 1:
                System.out.println("--- Them Benh Nhan Moi ---");
                System.out.print("Nhap Ma BN (VD: BN003): ");
                String ma = scanner.nextLine();
                BenhNhan bnMoi = new BenhNhan("CCCD003", "Tran Van C", "20/10/1992", "Nam", "0909090909",
                        ma, "11/11/2025", "Viem hong");
                qlBenhNhan.themBN(bnMoi);
                break;
            case 2:
                System.out.println("--- Xoa Benh Nhan ---");
                System.out.print("Nhap Ma BN can xoa: ");
                maBN = scanner.nextLine();
                qlBenhNhan.xoaBN(maBN); // Gọi hàm xóa đã thêm
                break;
            case 3:
                System.out.println("--- Sua Thong Tin Benh Nhan ---");
                System.out.print("Nhap Ma BN can sua: ");
                maBN = scanner.nextLine();
                qlBenhNhan.suaBN(maBN, scanner); // Gọi hàm sửa đã thêm
                break;
            case 4:
                qlBenhNhan.xuatDanhSach();
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("Lua chon khong hop le.");
        }
    }

    // Menu con 2
    public static void menuQuanLyDichVu() {
        System.out.println("-- Menu Quan Ly Dich Vu --");
        System.out.println("1. Xuat danh sach dich vu");
        System.out.println("2. Xoa dich vu");
        System.out.println("3. Sua gia dich vu");
        System.out.println("4. Them dich vu moi (test)");
        System.out.println("0. Quay lai menu chinh");
        System.out.print("Chon: ");
        int chon = scanner.nextInt();
        scanner.nextLine();
        String maDV;

        switch (chon) {
            case 1:
                qlDichVu.xuatDanhSach();
                break;
            case 2:
                System.out.println("--- Xoa Dich Vu ---");
                System.out.print("Nhap Ma DV can xoa (VD: T001, XN001): ");
                maDV = scanner.nextLine();
                qlDichVu.xoaDV(maDV); // Gọi hàm xóa đã thêm
                break;
            case 3:
                System.out.println("--- Sua Gia Dich Vu ---");
                System.out.print("Nhap Ma DV can sua gia: ");
                maDV = scanner.nextLine();
                qlDichVu.suaDV(maDV, scanner); // Gọi hàm sửa đã thêm
                break;
            case 4:
                // Sửa: Cập nhật constructor (6 tham số)
                qlDichVu.themDV(new Thuoc("T003", "Panadol Extra", 30000, "Vien", 1.0, 'B'));
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("Lua chon khong hop le.");
        }
    }

    // *** HÀM MỚI ĐƯỢC THÊM VÀO ***
    // Menu con 3 (Mới)
    public static void menuQuanLyNhanVien() {
        System.out.println("-- Menu Quan Ly Nhan Vien --");
        System.out.println("1. Them nhan vien moi (Demo)");
        System.out.println("2. Xoa nhan vien");
        System.out.println("3. Sua thong tin nhan vien");
        System.out.println("4. Xuat danh sach nhan vien");
        System.out.println("5. Tinh tong quy luong");
        System.out.println("0. Quay lai menu chinh");
        System.out.print("Chon: ");
        int chon = -1;
        try {
            chon = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Vui long nhap SO.");
        }
        scanner.nextLine(); // Luôn tiêu thụ ký tự enter
        String maNV;

        switch (chon) {
            case 1:
                System.out.println("--- Them Nhan Vien Moi (Demo Bac Si) ---");
                // Trong một ứng dụng thực tế, bạn sẽ yêu cầu người dùng nhập từng thông tin
                BacSi bsMoi = new BacSi("CCCD103", "Nguyen Thi G", "04/04/1988", "Nu", "044444444", "BS002", "Pho Khoa", 4.5, 3000000);
                qlNhanVien.themNV(bsMoi);
                break;
            case 2:
                System.out.println("--- Xoa Nhan Vien ---");
                System.out.print("Nhap Ma NV can xoa (VD: BS001): ");
                maNV = scanner.nextLine();
                qlNhanVien.xoaNV(maNV);
                break;
            case 3:
                System.out.println("--- Sua Thong Tin Nhan Vien ---");
                System.out.print("Nhap Ma NV can sua: ");
                maNV = scanner.nextLine();
                qlNhanVien.suaNV(maNV, scanner);
                break;
            case 4:
                qlNhanVien.xuatDanhSach();
                break;
            case 5:
                qlNhanVien.tinhTongQuyLuong();
                break;
            case 0:
                System.out.println("Quay lai menu chinh...");
                break;
            default:
                System.out.println("Lua chon khong hop le.");
        }
    }

    // Menu con 4 (Trước đó là 3)
    public static void menuLapHoaDon() {
        System.out.println("-- Menu Lap Hoa Don --");
        System.out.print("Nhap ma hoa don (VD: HD001): ");
        String maHD = scanner.nextLine();
        System.out.print("Nhap ma benh nhan (VD: BN001): ");
        String maBN = scanner.nextLine();
        
        BenhNhan bn = qlBenhNhan.timKiemBN(maBN);
        if (bn == null) {
            System.out.println("Loi: Benh nhan " + maBN + " khong ton tai!");
            return; 
        }

        System.out.print("Nhap ngay lap (VD: 11/11/2025): ");
        String ngayLap = scanner.nextLine();

        HoaDon hoaDonMoi = new HoaDon(maHD, maBN, ngayLap);

        while (true) {
            System.out.print("Nhap ma dich vu can them (hoac 'x' de ket thuc): ");
            String maDV = scanner.nextLine();
            if (maDV.equalsIgnoreCase("x")) {
                break;
            }

            DichVuYTe dv = qlDichVu.timKiemDV(maDV);
            if (dv != null) {
                hoaDonMoi.themDichVu(dv);
            } else {
                System.out.println("Khong tim thay dich vu co ma " + maDV);
            }
        }

        qlGiaoDich.themHoaDon(hoaDonMoi);
        hoaDonMoi.xuatHoaDon();
    }

    // Menu con 5 (Trước đó là 4)
    public static void menuThongKe() {
        System.out.println("-- Menu Thong Ke --");
        System.out.println("1. Xuat lich su giao dich (hoa don)");
        System.out.println("2. Xem tong doanh thu");
        System.out.print("Chon: ");
        int chon = scanner.nextInt();
        scanner.nextLine();

        if (chon == 1) {
            qlGiaoDich.xuatLichSuGiaoDich();
        } else if (chon == 2) {
            System.out.println("==> TONG DOANH THU: " + qlGiaoDich.tinhTongDoanhThu() + " VND");
        }
    }

    public static void themDuLieuMau() {
        System.out.println("Dang them du lieu mau...");
        qlBenhNhan.themBN(new BenhNhan("CCCD001", "Nguyen Van A", "10/10/2000", "Nam", "0123456789",
                "BN001", "10/11/2025", "Cam cum"));
        qlBenhNhan.themBN(new BenhNhan("CCCD002", "Thi Be B", "12/01/1995", "Nu", "0987654321",
                "BN002", "11/11/2025", "Dau bung"));

        // Thuoc(String maDV, String tenDV, double giaTien, String donViTinh, double hesoThuoc, char xeploai)
        qlDichVu.themDV(new Thuoc("T001", "Paracetamol", 15000, "Vien", 1.0, 'B'));
        qlDichVu.themDV(new Thuoc("T002", "Berberin", 20000, "Vien", 1.0, 'C'));
        
        // XetNghiem(String maDV, String tenDV, char xeploai, double giaTien, String yeuCauThietBi, String loaiXetNghiem)
        qlDichVu.themDV(new XetNghiem("XN001", "Xet nghiem mau", 'A', 150000, "May ly tam", "Mau"));
        qlDichVu.themDV(new XetNghiem("XN002", "Sieu am o bung", 'B', 200000, "May sieu am", "Hinh anh"));

        // *** THÊM DỮ LIỆU MẪU NHÂN VIÊN ***
        System.out.println("Them du lieu mau Nhan Vien...");
        qlNhanVien.themNV(new BacSi("CCCD100", "Nguyen Van Bac Si", "01/01/1980", "Nam", "011111111", "BS001", "Truong Khoa", 5.0, 5000000));
        qlNhanVien.themNV(new DieuDuong("CCCD101", "Tran Thi Dieu Duong", "02/02/1990", "Nu", "022222222", "DD001", "Dieu duong truong", 3.5, 2000000, 10));
        qlNhanVien.themNV(new KiThuatVien("CCCD102", "Le Van KTV", "03/03/1995", "Nam", "033333333", "KTV001", "KTV Xet nghiem", 3.0, 1000000, 500000, 20));

        System.out.println("Them du lieu mau thanh cong!\n");
    }
}