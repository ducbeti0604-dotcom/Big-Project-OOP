package quanlyphongmachcosoyte;
import java.util.ArrayList;
import java.util.List;
public class QL_DichVu {
	private List<DichVuYTe> danhSachDV;

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
        }
    }
}

