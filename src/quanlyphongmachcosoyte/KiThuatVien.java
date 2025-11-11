package quanlyphongmachcosoyte;



public class KiThuatVien extends NhanVien {
	 private double phuCapNguyHiem;
	    private double thuongHieuSuat;
	    private int soGioTrucDem;

	    // Constructor
	    public KiThuatVien(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maNV,
	            String chucVu, double heSoLuong, double phuCapNguyHiem, double thuongHieuSuat, int soGioTrucDem) {
	        super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai, maNV, chucVu, heSoLuong);
	        this.phuCapNguyHiem = phuCapNguyHiem;
	        this.thuongHieuSuat = thuongHieuSuat;
	        this.soGioTrucDem = soGioTrucDem;
	    }

	    @Override
	    public double TinhLuong() {

	        final double LUONG_CO_BAN = NhanVien.LUONG_CO_BAN;
	        double luongCoCau = this.heSoLuong * LUONG_CO_BAN;
	        double phuCapDocHai = luongCoCau * 0.30;
	        final double GIO_CHAM_CHUAN = 176; // Số giờ làm việc chuẩn trong tháng
	        double donGiaLuongGio = luongCoCau / GIO_CHAM_CHUAN;
	        double luongTrucDem = this.soGioTrucDem * donGiaLuongGio * 1.3;
	        double thuongHieuSuat = this.thuongHieuSuat;
	        double tongLuong = luongCoCau + phuCapDocHai + luongTrucDem + thuongHieuSuat + this.phuCapNguyHiem;

	        return tongLuong;
	    }

	    @Override
	    public void xuat() {
	        super.xuat();
	        System.out.println("Phu Cap Nguy Hiem: " + this.phuCapNguyHiem);
	        System.out.println("Thuong Hieu Suat: " + this.thuongHieuSuat);
	        System.out.println("So Gio Truc Dem: " + this.soGioTrucDem);
	        System.out.println("Tong Luong: " + TinhLuong());
	    }
	}

