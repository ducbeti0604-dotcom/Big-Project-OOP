package quanlyphongmachcosoyte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_GiaoDich {
	protected List<HoaDon> danhSachHoaDon;
	private final String dsGiaoDich = "giaodich.txt";

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
    
    //============ Đọc & Ghi file =============//
    public boolean ghiFile() {
        if (danhSachHoaDon.isEmpty()) {
            System.out.println("Danh sach giao dich rong, khong co gi de ghi.");
            return true;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dsGiaoDich))) {
            // Header giúp dễ debug hơn
            writer.write("# HD: MaHD,MaBN,NgayLap | DV: LoaiDV,MaDV,TenDV,GiaTien,XepLoai,ThongTinRieng..."); 
            writer.newLine(); 

            for (HoaDon hd : danhSachHoaDon) {
                // 1. Ghi thông tin Hóa Đơn chính (Header)
                String hdLine = "HD," + hd.getMaHoaDon() + "," + hd.getMaBN() + "," + hd.getNgayLap();
                writer.write(hdLine);
                writer.newLine();
                
                // 2. Ghi thông tin chi tiết các Dịch vụ
                for (DichVuYTe dv : hd.getDanhSachDichVuSuDung()) { // Giả sử có getter cho List
                    String dvLine = "";
                    if (dv instanceof Thuoc) {
                        dvLine = ((Thuoc) dv).taoChuoiCSV();
                    } else if (dv instanceof XetNghiem) {
                        dvLine = ((XetNghiem) dv).taoChuoiCSV();
                    }
                    
                    if (!dvLine.isEmpty()) {
                        writer.write("DV," + dvLine); // Thêm marker "DV"
                        writer.newLine();
                    }
                }
            }
            System.out.println("==> Da ghi thanh cong " + danhSachHoaDon.size() + " hoa don vao file: " + dsGiaoDich);
            return true;
        } catch (IOException e) {
            System.out.println("Loi khi ghi file giao dich: " + e.getMessage());
            return false;
        }
    }

    /**
     * Đọc dữ liệu Giao dịch từ file và tải vào danh sách.
     */
    public void docFile() {
        File file = new File(dsGiaoDich);
        if (!file.exists()) {
            System.out.println("File du lieu " + dsGiaoDich + " chua ton tai.");
            return;
        }

        try (Scanner scannerFile = new Scanner(file)) {
            this.danhSachHoaDon.clear(); // Xóa dữ liệu cũ
            HoaDon currentHoaDon = null;
            int countHD = 0;
            
            while (scannerFile.hasNextLine()) {
                String line = scannerFile.nextLine().trim();
                if (line.isEmpty() || line.startsWith("#")) continue; 
                
                String[] parts = line.split(","); 
                String marker = parts[0]; 
                
                if (marker.equalsIgnoreCase("HD")) {
                    // Thêm hóa đơn cũ vào danh sách (nếu có)
                    if (currentHoaDon != null) {
                        this.danhSachHoaDon.add(currentHoaDon);
                        countHD++;
                    }
                    // Bắt đầu hóa đơn mới
                    currentHoaDon = phanTichDongHD(parts);
                    
                } else if (marker.equalsIgnoreCase("DV") && currentHoaDon != null) {
                    // Thêm dịch vụ vào hóa đơn đang được xử lý
                    DichVuYTe dv = phanTichDongDV(parts);
                    if (dv != null) {
                        currentHoaDon.themDichVu(dv); // Sử dụng hàm themDichVu() để thêm vào List
                    }
                }
            }
            
            // Thêm hóa đơn cuối cùng sau khi hết vòng lặp
            if (currentHoaDon != null) {
                this.danhSachHoaDon.add(currentHoaDon);
                countHD++;
            }
            
            System.out.println("==> Da doc thanh cong " + countHD + " hoa don tu file: " + dsGiaoDich);
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file de doc.");
        }
    }
    
    /**
     * Phân tích dòng HD (Header) thành đối tượng HoaDon.
     * Dòng: HD,MaHD,MaBN,NgayLap
     */
    private HoaDon phanTichDongHD(String[] parts) {
        if (parts.length != 4) {
             System.out.println("Loi: Thieu truong du lieu Header Hoa Don.");
             return null;
        }
        
        try {
            String maHoaDon = parts[1];
            String maBN = parts[2];
            String ngayLap = parts[3];
            
            return new HoaDon(maHoaDon, maBN, ngayLap);
        } catch (Exception e) {
            System.out.println("Loi tao doi tuong Hoa Don: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Phân tích dòng DV (Detail) thành đối tượng Thuoc hoặc XetNghiem.
     * Dòng: DV,LoaiDV,MaDV,TenDV,GiaTien,...
     */
    private DichVuYTe phanTichDongDV(String[] parts) {
        // parts[0] là "DV"
        String loaiDV = parts[1]; // parts[1] là LoaiDV ("Thuoc" hoặc "XetNghiem")

        try {
            if (loaiDV.equalsIgnoreCase("Thuoc")) {
                // Thuoc: 7 trường (DV, Thuoc, MaDV, TenDV, GiaTien, XepLoai, DonViTinh, HeSoThuoc) -> parts.length = 8
                if (parts.length != 8) throw new IllegalArgumentException("Thieu truong du lieu Thuoc.");
                
                String maDV = parts[2];
                String tenDV = parts[3];
                double giaTien = Double.parseDouble(parts[4]);
                char xeploai = parts[5].charAt(0);
                String donViTinh = parts[6];
                double hesoThuoc = Double.parseDouble(parts[7]);
                
                return new Thuoc(maDV, tenDV, giaTien, donViTinh, hesoThuoc, xeploai);
                
            } else if (loaiDV.equalsIgnoreCase("XetNghiem")) {
                // XetNghiem: 7 trường (DV, XetNghiem, MaDV, TenDV, GiaTien, XepLoai, YeuCauThietBi, LoaiXetNghiem) -> parts.length = 8
                if (parts.length != 8) throw new IllegalArgumentException("Thieu truong du lieu Xet Nghiem.");
                
                String maDV = parts[2];
                String tenDV = parts[3];
                double giaTien = Double.parseDouble(parts[4]);
                char xeploai = parts[5].charAt(0);
                String yeuCauThietBi = parts[6];
                String loaiXetNghiem = parts[7];
                
                return new XetNghiem(maDV, tenDV, xeploai, giaTien, yeuCauThietBi, loaiXetNghiem);
            }
        } catch (Exception e) {
            System.out.println("Loi doc du lieu Dich Vu: " + e.getMessage());
        }
        
        return null;
    }
}