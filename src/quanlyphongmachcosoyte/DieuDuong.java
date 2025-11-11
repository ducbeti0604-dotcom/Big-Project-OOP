package quanlyphongmachcosoyte;

public class DieuDuong extends NhanVien {
	 private double phuCapNgheNghiep;
	    private int soGioLamThem;

	    // Constructor
	    public DieuDuong(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maNV,
	            String chucVu, double heSoLuong, double phuCapNgheNghiep, int soGioLamThem) {
	        super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong);
	        this.phuCapNgheNghiep = phuCapNgheNghiep;
	        this.soGioLamThem = soGioLamThem;
	    }

	    @Override
	    public double TinhLuong() {
	        double luongCoCau = this.heSoLuong * NhanVien.LUONG_CO_BAN;
	        double phuCapUuDaiNghe = luongCoCau * 0.40;
	        final double GIO_LAM_CHUAN = 176; // Số giờ làm việc 1 tháng (22 ngày * 8 giờ)
	        double donGiaLuongGio = luongCoCau / GIO_LAM_CHUAN;
	        double luongLamThem = this.soGioLamThem * donGiaLuongGio * 1.5;
	        double tongLuong = luongCoCau + phuCapUuDaiNghe + luongLamThem + this.phuCapNgheNghiep;
	        return tongLuong;
	    }

	    @Override
	    public void xuat() {
	        super.xuat();
	        System.out.println("Phu Cap Nghe Nghiep: " + this.phuCapNgheNghiep);
	        System.out.println("So Gio Lam Them: " + this.soGioLamThem);
	        System.out.println("Tong luong: " + TinhLuong());
	    }
	}

