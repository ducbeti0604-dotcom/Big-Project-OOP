package quanlyphongmachcosoyte;

import java.io.BufferedWriter; 
import java.io.File;      
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_NhanVien {
    protected List<NhanVien> danhSachNV;
    private final String dsNhanVien = "nhanvien.txt";

    // Constructor
    public QL_NhanVien() {
        this.danhSachNV = new ArrayList<>();
    }

    public void themNV(NhanVien nv) {
        // Kiểm tra xem mã NV đã tồn tại chưa
        if (timKiemNV(nv.layMaNV()) != null) {
            System.out.println("Loi: Ma NV " + nv.layMaNV() + " da ton tai.");
            return;
        }
        this.danhSachNV.add(nv);
        System.out.println("Da them nhan vien: " + nv.hoTen);
    }

    public NhanVien timKiemNV(String maNV) {
        for (NhanVien nv : danhSachNV) {
            if (nv.layMaNV().equalsIgnoreCase(maNV)) {
                return nv;
            }
        }
        return null;
    }

    public void xoaNV(String maNV) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            danhSachNV.remove(nv);
            System.out.println("Da xoa nhan vien: " + nv.hoTen + " (Ma: " + maNV + ")");
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

    public void suaNV(String maNV, Scanner scanner) {
        NhanVien nv = timKiemNV(maNV);
        if (nv != null) {
            System.out.println("Tim thay nhan vien: " + nv.hoTen);
            System.out.print("Nhap He So Luong moi (enter de bo qua): ");
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                try {
                    double heSoMoi = Double.parseDouble(input);
                    nv.heSoLuong = heSoMoi;
                    System.out.println("Da cap nhat he so luong!");
                } catch (Exception e) {
                    System.out.println("Loi: Nhap sai dinh dang so.");
                }
            }
        } else {
            System.out.println("Khong tim thay nhan vien voi ma: " + maNV);
        }
    }

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

    public void tinhTongQuyLuong() {
        double tongLuong = 0;
        if (danhSachNV.isEmpty()) {
            System.out.println("Chua co nhan vien nao de tinh luong.");
            return;
        }
        
        for (NhanVien nv : danhSachNV) {
            tongLuong += nv.TinhLuong(); 
        }
        System.out.println("=================================");
        System.out.println("==> TONG QUY LUONG THANG: " + String.format("%.0f", tongLuong) + " VND");
        System.out.println("=================================");
    }
    
    //============ Đọc/Ghi file ==========//
    public boolean ghiFile() {
        if (danhSachNV.isEmpty()) {
            System.out.println("Danh sach rong, khong co gi de ghi.");
            return true;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dsNhanVien))) {
            writer.write("#Loai,MaID,HoTen,NgaySinh,GioiTinh,SoDT,MaNV,ChucVu,HeSoLuong,ThongTinRieng..."); // Header
            writer.newLine(); 

            for (NhanVien nv : danhSachNV) {
                String line = "";
                // Sử dụng 'instanceof' và tính đa hình để gọi hàm tạo chuỗi riêng
                if (nv instanceof BacSi) {
                    line = ((BacSi) nv).taoChuoiCSV();
                } else if (nv instanceof DieuDuong) {
                    line = ((DieuDuong) nv).taoChuoiCSV();
                } else if (nv instanceof KiThuatVien) {
                    line = ((KiThuatVien) nv).taoChuoiCSV();
                }
                
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("==> Da ghi thanh cong " + danhSachNV.size() + " nhan vien vao file: " + dsNhanVien);
            return true;
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Đọc dữ liệu nhân viên từ file text (CSV) và tải vào danh sách.
     */
    public void docFile() {
        File file = new File(dsNhanVien);
        if (!file.exists()) {
            System.out.println("File du lieu " + dsNhanVien + " chua ton tai. Bat dau voi du lieu rong.");
            return;
        }

        try (Scanner scannerFile = new Scanner(file)) { // Đổi tên biến Scanner để tránh trùng
            this.danhSachNV.clear(); // Xóa dữ liệu mẫu/cũ trong bộ nhớ
            int count = 0;
            
            while (scannerFile.hasNextLine()) {
                String line = scannerFile.nextLine();
                // Bỏ qua dòng trống hoặc dòng header (bắt đầu bằng #)
                if (line.trim().isEmpty() || line.startsWith("#")) continue; 
                
                NhanVien nv = phanTichDong(line); 
                
                if (nv != null) {
                    // Kiểm tra trùng lặp nếu cần, nhưng thường khi đọc file thì bỏ qua
                    this.danhSachNV.add(nv);
                    count++;
                }
            }
            System.out.println("==> Da doc thanh cong " + count + " nhan vien tu file: " + dsNhanVien);
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file de doc.");
        }
    }
    
    /**
     * Phân tích một dòng CSV thành đối tượng NhanVien cụ thể.
     */
    private NhanVien phanTichDong(String line) {
        String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
        String loaiNV = parts[0]; 
        
        // 9 thuộc tính chung + 1 loại NV = 10 phần tử MIN
        if (parts.length < 10) {
            return null; // Bỏ qua dòng bị lỗi
        }

        try {
            // Lấy 8 thuộc tính chung
            String maID = parts[1];
            String hoTen = parts[2];
            String ngaySinh = parts[3];
            String gioiTinh = parts[4];
            String soDienThoai = parts[5];
            String maNV = parts[6];
            String chucVu = parts[7];
            double heSoLuong = Double.parseDouble(parts[8]);

            if (loaiNV.equalsIgnoreCase("BacSi")) {
                // BacSi: 10 trường
                double phuCapChucVu = Double.parseDouble(parts[9]);
                return new BacSi(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong, phuCapChucVu);
                
            } else if (loaiNV.equalsIgnoreCase("DieuDuong")) {
                // DieuDuong: 11 trường
                double phuCapNgheNghiep = Double.parseDouble(parts[9]);
                int soGioLamThem = Integer.parseInt(parts[10]);
                return new DieuDuong(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong, phuCapNgheNghiep, soGioLamThem);
                
            } else if (loaiNV.equalsIgnoreCase("KiThuatVien")) {
                // KTV: 12 trường
                double phuCapNguyHiem = Double.parseDouble(parts[9]);
                double thuongHieuSuat = Double.parseDouble(parts[10]);
                int soGioTrucDem = Integer.parseInt(parts[11]);
                return new KiThuatVien(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong, phuCapNguyHiem, thuongHieuSuat, soGioTrucDem);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Loi chuyen doi du lieu (so) tren dong: " + line);
        }
        
        return null;
    }
}