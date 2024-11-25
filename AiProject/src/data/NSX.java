package data;

import java.util.ArrayList;
import java.util.List;

import model.XuLy;

public class NSX {
	private String TenNSX;
	private QuanLyDon quanLyDon;
	private QuanLyXe quanLyXe;
	private List<TramGiao> dsTram;
	private XuLy xuLy;
	public NSX(String tenNSX, QuanLyDon quanLyDon, QuanLyXe quanLyXe) {
		TenNSX = tenNSX;
		this.quanLyDon = quanLyDon;
		this.quanLyXe = quanLyXe;
		dsTram = new ArrayList<TramGiao>();
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
	
public List<TramGiao> getDsTram() {
		return dsTram;
	}
	public void setDsTram(List<TramGiao> dsTram) {
		this.dsTram = dsTram;
	}
	//	xuLyduLieu 
	public void CapNhatDonChoTram() {
	
	}
}
