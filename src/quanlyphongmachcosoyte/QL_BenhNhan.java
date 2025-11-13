package quanlyphongmachcosoyte;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;  
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_BenhNhan {
    protected List<BenhNhan> danhSachBN;
    private final String dsBenhNhan = "benhnhan.txt";

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
            if (bn.layMaBN().equalsIgnoreCase(maBN)) {
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
            bn.xuat();
            System.out.println("--------------------");
        }
    }

    public void xoaBN(String maBN) {
        BenhNhan bn = timKiemBN(maBN);
        if (bn != null) {
            danhSachBN.remove(bn);
            System.out.println("Da xoa benh nhan: " + bn.hoTen);
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }

    // Sửa bệnh nhân
    public void suaBN(String maBN, Scanner scanner) {
        BenhNhan bn = timKiemBN(maBN);
        if (bn != null) {
            System.out.println("Tim thay benh nhan: " + bn.hoTen);
            System.out.print("Nhap benh ly moi (enter de bo qua): ");
            String benhLyMoi = scanner.nextLine();
            if (!benhLyMoi.trim().isEmpty()) {
                bn.datBenhLy(benhLyMoi);
                System.out.println("Da cap nhat benh ly!");
            }
        } else {
            System.out.println("Khong tim thay benh nhan voi ma: " + maBN);
        }
    }
    
    //============ Đọc/Ghi file ============//
    public boolean ghiFile() {
        if (danhSachBN.isEmpty()) {
            System.out.println("Danh sach benh nhan rong, khong co gi de ghi.");
            return true;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dsBenhNhan))) {
            writer.write("#MaID,HoTen,NgaySinh,GioiTinh,SoDT,MaBN,NgayVaoVien,BenhLy"); // Header
            writer.newLine(); 

            for (BenhNhan bn : danhSachBN) {
                writer.write(bn.taoChuoiCSV());
                writer.newLine();
            }
            System.out.println("==> Da ghi thanh cong " + danhSachBN.size() + " benh nhan vao file: " + dsBenhNhan);
            return true;
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
            return false;
        }
    }
  
     //Đọc dữ liệu bệnh nhân từ file text (CSV) và tải vào danh sách.
    public void docFile() {
        File file = new File(dsBenhNhan);
        if (!file.exists()) {
            System.out.println("File du lieu " + dsBenhNhan + " chua ton tai. Bat dau voi du lieu rong.");
            return;
        }

        try (Scanner scannerFile = new Scanner(file)) {
            this.danhSachBN.clear(); // Xóa dữ liệu mẫu/cũ trong bộ nhớ
            int count = 0;
            
            while (scannerFile.hasNextLine()) {
                String line = scannerFile.nextLine();
                if (line.trim().isEmpty() || line.startsWith("#")) continue; 
                
                BenhNhan bn = phanTichDong(line); 
                
                if (bn != null) {
                    this.danhSachBN.add(bn);
                    count++;
                }
            }
            System.out.println("==> Da doc thanh cong " + count + " benh nhan tu file: " + dsBenhNhan);
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file de doc.");
        }
    }
    
    private BenhNhan phanTichDong(String line) {
        String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
        
        // BenhNhan có 8 thuộc tính
        if (parts.length != 8) {
            System.out.println("Loi doc du lieu: Du lieu thieu truong tren dong: " + line);
            return null;
        }

        try {
            // Lấy 8 thuộc tính theo thứ tự
            String maID = parts[0];
            String hoTen = parts[1];
            String ngaySinh = parts[2];
            String gioiTinh = parts[3];
            String soDienThoai = parts[4];
            String maBN = parts[5];
            String ngayVaoVien = parts[6];
            String benhLy = parts[7];

            // Trả về đối tượng BenhNhan mới
            return new BenhNhan(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maBN, ngayVaoVien, benhLy);
            
        } catch (Exception e) {
            System.out.println("Loi chuyen doi du lieu tren dong: " + line + ". Chi tiet: " + e.getMessage());
        }
        
        return null;
    }
}