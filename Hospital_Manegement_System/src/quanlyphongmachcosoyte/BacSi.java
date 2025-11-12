package quanlyphongmachcosoyte;

public class BacSi extends NhanVien {
	 private double phuCapChucVu;

	    // Constructor
	    public BacSi(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maNV,
	            String chucVu, double heSoLuong, double phuCapChucVu) {
	        super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong);
	        this.phuCapChucVu = phuCapChucVu;
	    }

	    @Override
	    public double TinhLuong() {
	        double luong;
	        double luongCoCau = this.heSoLuong * NhanVien.LUONG_CO_BAN;
	        double phuCapUuDaiNghe = luongCoCau * 0.40; // Phụ cấp nghề 40%
	        double tongLuong = luongCoCau + phuCapUuDaiNghe + phuCapChucVu;
	        return tongLuong;
	    }

	    @Override
	    public void xuat() {
	        super.xuat();
	        System.out.println("Phu Cap Chuc Vu: " + this.phuCapChucVu);
	        System.out.println("Tong Luong: " + TinhLuong());
	    }
	}

