package quanlyphongmachcosoyte;

public class Thuoc extends DichVuYTe {
	  private String donViTinh;
private double hesoThuoc;
	    // Constructor
	    public Thuoc(String maDV, String tenDV, double giaTien, String donViTinh,double hesoThuoc,char xeploai) {
	        super(maDV, tenDV, giaTien,xeploai);
	        this.donViTinh = donViTinh;
	
	    }

	    @Override
	    public void HienThi() {
	        System.out.println("--- Thông tin Thuốc ---");
	        System.out.println("Mã Dịch vụ: " + this.maDV);
	        System.out.println("Tên Thuốc: " + this.tenDV);
	        System.out.println("Giá Tiền: " + DichVuYTe.giaTien + " VND");
	        System.out.println("Đơn vị Tính: " + this.donViTinh);
	        System.out.println("xếp loại: " + xeploai());
	        System.out.println("Đơn vị Tính: " + this.donViTinh);;
	        System.out.println("hệ số thuốc: " +hesoThuoc);
	        System.out.println("-------------------------");
	    }

	    // Getter và Setter
	    public String getDonViTinh() {
	        return donViTinh;
	    }

	    public void setDonViTinh(String donViTinh) {
	        this.donViTinh = donViTinh;
	    }
public double tinhtienthuoc() {
	 
	        double tienthuoc = hesoThuoc * giaTien;
	      double hoahong=giaTien*0.15;
	      double thueVat=0.15; 
	      switch (xeploai()) {
	            case 'A': return tienthuoc / 0.75 + thueVat-hoahong;       
	            case 'B': return tienthuoc / 0.5 + thueVat-hoahong;
	            case 'C': return tienthuoc + thueVat-hoahong;
	            default:  return tienthuoc+thueVat-hoahong ;
	        }
	
	
	
}	
private char xeploai() {
    double diemtichluy = (giaTien / hesoThuoc) * 100;
    if ( diemtichluy>= 100) return 'A';
    else if (diemtichluy >= 50) return 'B';
    else if(diemtichluy>=25)return 'C';
    else return 'D';
}
	
	
}


