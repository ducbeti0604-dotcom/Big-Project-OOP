package quanlyphongmachcosoyte;
import java.util.Scanner;
public class CoSoYTe {
	private static QL_BenhNhan qlBenhNhan = new QL_BenhNhan();
    private static QL_DichVu qlDichVu = new QL_DichVu();
    private static QL_GiaoDich qlGiaoDich = new QL_GiaoDich();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Thêm một số dữ liệu mẫu để test cho nhanh
        themDuLieuMau();

        boolean chay = true;
        while (chay) {
            hienThiMenuChinh();
            System.out.print("Chon chuc nang (0-4): ");
            int luaChon = scanner.nextInt();
            scanner.nextLine(); 

            switch (luaChon) {
                case 1:
                    menuQuanLyBenhNhan();
                    break;
                case 2:
                    menuQuanLyDichVu();
                    break;
                case 3:
                    menuLapHoaDon();
                    break;
                case 4:
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
        System.out.println("3. Lap Hoa Don Thanh Toan");
        System.out.println("4. Thong ke & Xuat danh sach");
        System.out.println("0. Thoat chuong trinh");
    }

    // Menu con 1
    public static void menuQuanLyBenhNhan() {
        System.out.println("-- Menu Quan Ly Benh Nhan --");
        System.out.println("1. Them benh nhan moi");
        System.out.println("2. Xuat danh sach benh nhan");
        System.out.print("Chon: ");
        int chon = scanner.nextInt();
        scanner.nextLine();

        if (chon == 1) {
            BenhNhan bnMoi = new BenhNhan("CCCD003", "Tran Van C", "20/10/1992", "Nam", "0909090909",
                    "BN003", "11/11/2025", "Viem hong");
            qlBenhNhan.themBN(bnMoi);
        } else {
            qlBenhNhan.xuatDanhSach();
        }
    }

    // Menu con 2
    public static void menuQuanLyDichVu() {
        qlDichVu.xuatDanhSach();
    }

    // Menu con 3
    public static void menuLapHoaDon() {
        System.out.println("-- Menu Lap Hoa Don --");
        System.out.print("Nhap ma hoa don (VD: HD001): ");
        String maHD = scanner.nextLine();
        System.out.print("Nhap ma benh nhan (VD: BN001): ");
        String maBN = scanner.nextLine();
        System.out.print("Nhap ngay lap (VD: 11/11/2025): ");
        String ngayLap = scanner.nextLine();

        HoaDon hoaDonMoi = new HoaDon(maHD, maBN, ngayLap);

        // Thêm dịch vụ vào hóa đơn
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

        // Thêm hóa đơn vào danh sách quản lý
        qlGiaoDich.themHoaDon(hoaDonMoi);
        // In chi tiết hóa đơn vừa lập
        hoaDonMoi.xuatHoaDon();
    }
    
    // Menu con 4
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
        // Mẫu Bệnh Nhân
        qlBenhNhan.themBN(new BenhNhan("CCCD001", "Nguyen Van A", "10/10/2000", "Nam", "0123456789",
                "BN001", "10/11/2025", "Cam cum"));
        qlBenhNhan.themBN(new BenhNhan("CCCD002", "Thi Be B", "12/01/1995", "Nu", "0987654321",
                "BN002", "11/11/2025", "Dau bung"));

        // Mẫu Dịch Vụ
        qlDichVu.themDV(new Thuoc("T001", "Paracetamol", 15000, "Vien",12f,'A'));
        qlDichVu.themDV(new Thuoc("T002", "Berberin", 20000, "Vien",30f,'B'));
        qlDichVu.themDV(new XetNghiem("XN001", "Xet nghiem mau",'B', 150000, "May ly tam", "Mau"));
        qlDichVu.themDV(new XetNghiem("XN002", "Sieu am o bung", 'C',200000, "May sieu am", "Hinh anh"));
        
        System.out.println("Them du lieu mau thanh cong!\n");
    }
}

