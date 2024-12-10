package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.XuLy;

public class NSX {
	private String TenNSX;
	private QuanLyDon quanLyDon;
	private QuanLyXe quanLyXe;
	private List<TramGiao> dsTram;
	private List<KhoangCachCacTram> kc;//danh sách đường đi của cả map
	private XuLy xuLy;
//xuly để lấy dữ liệu từ db
//	public NSX(String tenNSX, QuanLyDon quanLyDon, QuanLyXe quanLyXe) {
//		TenNSX = tenNSX;
//		this.quanLyDon = quanLyDon;
//		this.quanLyXe = quanLyXe;
//		dsTram = xuLy.getDSTram();
//		CapNhatDonChoTram();
//	}

	public NSX(String tenNSX, QuanLyDon quanLyDon, QuanLyXe quanLyXe, List<TramGiao> dsTram,
			List<KhoangCachCacTram> kc) {
		super();
		TenNSX = tenNSX;
		this.quanLyDon = quanLyDon;
		this.quanLyXe = quanLyXe;
		this.dsTram = dsTram;
		this.kc = kc;
		CapNhatDonChoTram();
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

	public List<KhoangCachCacTram> getKc() {
		return kc;
	}

	public void setKc(List<KhoangCachCacTram> kc) {
		this.kc = kc;
	}

	// xuLyduLieu
//	cap nhat hang cho tram
	public void CapNhatDonChoTram() {
		for (DonHang donHang : quanLyDon.getQuanLyDon()) {
			for (TramGiao tram : dsTram) {
				if (tram.getTenTram().equals(donHang.getTramGiao())) {
					tram.themDonHang(donHang);
					break;
				}
			}
		}	
	}

	public double khoiLuongDon() {
		double num = 0;
		for (TramGiao tramGiao : dsTram) {
			num += tramGiao.getKhoiLuongDon();
		}
		return num;
	}

	public int SoLuongDon() {
		return quanLyDon.getQuanLyDon().size();
	}

	public double tongTaiTrongXe() {
		return quanLyXe.tongTaiTrongXe();
	}

	public int tongXLDonXe() {
		return quanLyXe.tongXLDonXe();
	}
}
