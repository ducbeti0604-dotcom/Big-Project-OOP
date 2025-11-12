package quanlyphongmachcosoyte;

// Sửa: Implement hàm tinhChiPhi()
// Sửa: Sửa logic hàm xeploai() để tránh chia cho 0
public class Thuoc extends DichVuYTe {
    private String donViTinh;
    private double hesoThuoc;

    // Constructor
    public Thuoc(String maDV, String tenDV, double giaTien, String donViTinh, double hesoThuoc, char xeploai) {
        super(maDV, tenDV, giaTien, xeploai);
        this.donViTinh = donViTinh;
        this.hesoThuoc = hesoThuoc; // Sửa: Gán giá trị
    }

    @Override
    public void HienThi() {
        System.out.println("--- Thông tin Thuốc ---");
        System.out.println("Mã Dịch vụ: " + this.maDV);
        System.out.println("Tên Thuốc: " + this.tenDV);
        System.out.println("Giá Tiền (gốc): " + this.giaTien + " VND"); // Sửa: Lấy this.giaTien
        System.out.println("Đơn vị Tính: " + this.donViTinh);
        System.out.println("Xếp loại (tính toán): " + xeploai());
        System.out.println("Hệ số thuốc: " + hesoThuoc);
        System.out.println("-------------------------");
    }

    // Sửa: Thực thi (implement) phương thức abstract từ lớp cha
    @Override
    public double tinhChiPhi() {
        double tienthuocCoBan = hesoThuoc * giaTien;
        double tienTheoXepLoai;

        switch (xeploai()) {
            case 'A':
                tienTheoXepLoai = tienthuocCoBan / 0.75;
                break;
            case 'B':
                tienTheoXepLoai = tienthuocCoBan / 0.5;
                break;
            case 'C':
            default:
                tienTheoXepLoai = tienthuocCoBan;
                break;
        }

        double thueVat = tienTheoXepLoai * 0.15; // Giả định thuế 15%
        double hoahong = giaTien * 0.15; // Logic hoa hồng của bạn

        return tienTheoXepLoai + thueVat - hoahong;
    }

    // Hàm private này tính toán xếp loại dựa trên hệ số
    private char xeploai() {
        // Sửa: Kiểm tra hesoThuoc để tránh lỗi chia cho 0
        if (this.hesoThuoc == 0) return 'D';

        double diemtichluy = (giaTien / hesoThuoc) * 100;
        if (diemtichluy >= 100) return 'A';
        else if (diemtichluy >= 50) return 'B';
        else if (diemtichluy >= 25) return 'C';
        else return 'D';
    }
}