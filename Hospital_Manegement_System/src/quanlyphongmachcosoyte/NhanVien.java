package quanlyphongmachcosoyte;


abstract public class NhanVien extends Nguoi {
	  private String maNV;
	    private String chucVu;
	    protected double heSoLuong;
	    public static final double LUONG_CO_BAN = 4300000;

	    // Constructor
	    public NhanVien(String maID, String hoTen, String ngaySinh, String gioiTinh, String soDienThoai, String maNV,
	            String chucVu, double heSoLuong) {
	        super(maID, hoTen, ngaySinh, gioiTinh, soDienThoai);
	        this.maNV = maNV;
	        this.chucVu = chucVu;
	        this.heSoLuong = heSoLuong;
	    }

	    abstract public double TinhLuong();

	    @Override
	    public void xuat() {
	        System.out.println("MaID: " + this.maID);
	        System.out.println("Ho ten: " + this.hoTen);
	        System.out.println("Ngay Sinh: " + this.ngaySinh);
	        System.out.println("Gioi Tinh: " + this.gioiTinh);
	        System.out.println("So Dien Thoai: " + this.soDienThoai);
	        System.out.println("Ma NV: " + this.maNV);
	        System.out.println("Chuc Vu: " + this.chucVu);
	        System.out.println("He So Luong: " + this.heSoLuong);
	    }

	    public String getMaNV() {
	        return maNV;
	    }
	}