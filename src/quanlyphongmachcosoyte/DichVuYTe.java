package quanlyphongmachcosoyte;

abstract public class DichVuYTe {
protected String maDV;
protected String tenDV;
protected static double giaTien=1150;
protected char xeploai;


public DichVuYTe(String maDV,String tenDV,double giatien,char xeploai) {
	this.maDV=maDV;
	this.tenDV=tenDV;
	

this.xeploai=xeploai;
}
abstract public void HienThi(); 
	

}
