package data;

public class NSX {
	private String TenNSX;
	private QuanLyDon quanLyDon;
	private QuanLyXe quanLyXe;
	private XuLy xuLy;
	public NSX(String tenNSX, QuanLyDon quanLyDon, QuanLyXe quanLyXe, XuLy xuLy) {
		TenNSX = tenNSX;
		this.quanLyDon = quanLyDon;
		this.quanLyXe = quanLyXe;
		this.xuLy = xuLy;
	}
	public String getTenNSX() {
		return TenNSX;
	}
	public void setTenNSX(String tenNSX) {
		TenNSX = tenNSX;
	}
	public QuanLyDon getQuanLyDon() {
		return quanLyDon;
	}
	public void setQuanLyDon(QuanLyDon quanLyDon) {
		this.quanLyDon = quanLyDon;
	}
	public QuanLyXe getQuanLyXe() {
		return quanLyXe;
	}
	public void setQuanLyXe(QuanLyXe quanLyXe) {
		this.quanLyXe = quanLyXe;
	}
	public XuLy getXuLy() {
		return xuLy;
	}
	public void setXuLy(XuLy xuLy) {
		this.xuLy = xuLy;
	}
	
}
