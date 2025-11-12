package quanlyphongmachcosoyte;



abstract public class Nguoi {
protected String maID;
protected String hoTen;
protected String ngaySinh;
protected String gioiTinh;
protected String soDienThoai;

public Nguoi(String maID,String hoTen,String ngaySinh,String gioiTinh,String soDienThoai) {
	this.maID=maID;
	this.hoTen=hoTen;
	this.ngaySinh=ngaySinh;
	this.gioiTinh=gioiTinh;
	this.soDienThoai=soDienThoai;
}

abstract public void xuat();
}
