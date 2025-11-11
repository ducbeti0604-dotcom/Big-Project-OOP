package quanlyphongmachcosoyte;

public class XetNghiem extends DichVuYTe{
private String yeuCauThietBi;
private String loaiXetNghiem;

public XetNghiem(String maDV,String tenDV,char xeploai,double giaTien,String yeuCauThietBi,String loaiXetNghiem) {
	super(maDV,tenDV,giaTien,xeploai);
	this.yeuCauThietBi=yeuCauThietBi;
	this.loaiXetNghiem=loaiXetNghiem;
}
@Override
public void HienThi() {
	System.out.println("thietbi:"+yeuCauThietBi);
	System.out.println("xep loai:"+xeploai());
	 System.out.println("Mã dịch vụ: " + maDV);
     System.out.println("Tên dịch vụ: " + tenDV);
     System.out.println("Loại xét nghiệm: " + loaiXetNghiem);
     System.out.println("Giá tiền: " + giaTien + " VNĐ");
     System.out.println("Đánh giá xét nghiệm: " + danhGiaXetNghiem());
     System.out.println("Loại thiết bị: " + ThietBi());
}
public char xeploai() {
	double diemthanhvien= giaTien*150;
	if (diemthanhvien>=1300)	return  'A';
	else if(diemthanhvien>=1250)return 'B';
	else if (diemthanhvien>=1000) return 'C';
	else return 'D';
}
public String  ThietBi() {
	 char xl = xeploai();
     switch (xl) {
         case 'A': return "Thiết bị hiện đại cao cấp";
         case 'B': return "Thiết bị tiêu chuẩn tốt";
         case 'C': return "Thiết bị cơ bản";
         default: return "Thiết bị đơn giản";
     }
 }
public String danhGiaXetNghiem() {
    switch (loaiXetNghiem.toLowerCase()) {
        case "máu":
            return "Yêu cầu vô trùng tuyệt đối và thiết bị cao cấp.";
        case "nước tiểu":
            return "Thiết bị cơ bản, dễ thực hiện.";
        case "sinh hóa":
            return "Cần máy phân tích tự động.";
        case "covid-19":
            return "Cần thiết bị PCR hiện đại.";
        default:
            return "Loại xét nghiệm khác.";
    }
}





	
	
	
	
}


