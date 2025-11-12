package quanlyphongmachcosoyte;

import java.io.BufferedWriter; // <--- Cần thêm
import java.io.File;         // <--- Cần thêm
import java.io.FileNotFoundException; // <--- Cần thêm
import java.io.FileWriter;   // <--- Cần thêm
import java.io.IOException;  // <--- Cần thêm
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Sửa: Thêm import

// Sửa: Thêm hàm xoaDV, suaDV
public class QL_DichVu {
    private List<DichVuYTe> danhSachDV;
    private final String dsDichVu = "dichvu.txt";

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
    
    //=========== Đọc & Ghi file ==========//
    public boolean ghiFile() {
        if (danhSachDV.isEmpty()) {
            System.out.println("Danh sach dich vu rong, khong co gi de ghi.");
            return true;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dsDichVu))) {
            writer.write("#LoaiDV,MaDV,TenDV,GiaTien,XepLoai,ThongTinRieng..."); // Header
            writer.newLine(); 

            for (DichVuYTe dv : danhSachDV) {
                String line = "";
                // Sử dụng 'instanceof' để xác định lớp con và gọi hàm taoChuoiCSV() tương ứng
                if (dv instanceof Thuoc) {
                    line = ((Thuoc) dv).taoChuoiCSV();
                } else if (dv instanceof XetNghiem) {
                    line = ((XetNghiem) dv).taoChuoiCSV();
                }
                
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("==> Da ghi thanh cong " + danhSachDV.size() + " dich vu vao file: " + dsDichVu);
            return true;
        } catch (IOException e) {
            System.out.println("Loi khi ghi file dich vu: " + e.getMessage());
            return false;
        }
    }

    /**
     * Đọc dữ liệu dịch vụ từ file text (CSV) và tải vào danh sách.
     */
    public void docFile() {
        File file = new File(dsDichVu);
        if (!file.exists()) {
            System.out.println("File du lieu " + dsDichVu + " chua ton tai. Bat dau voi du lieu rong.");
            return;
        }

        try (Scanner scannerFile = new Scanner(file)) {
            this.danhSachDV.clear(); // Xóa dữ liệu mẫu/cũ trong bộ nhớ
            int count = 0;
            
            while (scannerFile.hasNextLine()) {
                String line = scannerFile.nextLine().trim();
                // Bỏ qua dòng trống hoặc dòng header (bắt đầu bằng #)
                if (line.isEmpty() || line.startsWith("#")) continue; 
                
                DichVuYTe dv = phanTichDongDV(line); 
                
                if (dv != null) {
                    this.danhSachDV.add(dv);
                    count++;
                }
            }
            System.out.println("==> Da doc thanh cong " + count + " dich vu tu file: " + dsDichVu);
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file de doc.");
        }
    }
    
    /**
     * Phân tích một dòng CSV thành đối tượng DichVuYTe cụ thể (Thuoc hoặc XetNghiem).
     */
    private DichVuYTe phanTichDongDV(String line) {
        String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
        String loaiDV = parts[0]; 
        
        // Cần ít nhất 5 trường chung + 1 trường loại DV = 6 phần tử MIN
        if (parts.length < 6) {
             // System.out.println("Loi doc du lieu: Du lieu thieu truong tren dong: " + line);
             return null; 
        }

        try {
            // Lấy 5 thuộc tính chung (sử dụng index 1, 2, 3, 4)
            String maDV = parts[1];
            String tenDV = parts[2];
            double giaTien = Double.parseDouble(parts[3]);
            char xeploai = parts[4].charAt(0);
            
            if (loaiDV.equalsIgnoreCase("Thuoc")) {
                // Thuoc: 7 trường (LoaiDV, MaDV, TenDV, GiaTien, XepLoai, DonViTinh, HeSoThuoc)
                String donViTinh = parts[5];
                double hesoThuoc = Double.parseDouble(parts[6]);
                
                // Thuoc(String maDV, String tenDV, double giaTien, String donViTinh, double hesoThuoc, char xeploai)
                return new Thuoc(maDV, tenDV, giaTien, donViTinh, hesoThuoc, xeploai);
                
            } else if (loaiDV.equalsIgnoreCase("XetNghiem")) {
                // XetNghiem: 7 trường (LoaiDV, MaDV, TenDV, GiaTien, XepLoai, YeuCauThietBi, LoaiXetNghiem)
                String yeuCauThietBi = parts[5];
                String loaiXetNghiem = parts[6];
                
                // XetNghiem(String maDV, String tenDV, char xeploai, double giaTien, String yeuCauThietBi, String loaiXetNghiem)
                // Lưu ý thứ tự tham số khác nhau
                return new XetNghiem(maDV, tenDV, xeploai, giaTien, yeuCauThietBi, loaiXetNghiem);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Loi chuyen doi du lieu (so/ky tu) tren dong: " + line);
        }
        
        return null;
    }
}