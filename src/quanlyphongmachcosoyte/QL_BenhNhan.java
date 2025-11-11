package quanlyphongmachcosoyte;
import java.util.ArrayList;
import java.util.List;
public class QL_BenhNhan {
	 private List<BenhNhan> danhSachBN;

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
	            if (bn.hoTen.equalsIgnoreCase(maBN)) { 
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
	            bn.xuat(); // Gọi hàm xuat() của mỗi bệnh nhân
	            System.out.println("--------------------");
	        }
	    }
	}

