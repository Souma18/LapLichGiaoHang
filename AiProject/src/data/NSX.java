package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class NSX {
	private String TenNSX;
	private QuanLyDon quanLyDon;
	private QuanLyXe quanLyXe;
	private List<TramGiao> dsTram;
	private List<KhoangCachCacTram> kc;// danh sách đường đi của cả map

	public NSX(String tenNSX, QuanLyDon quanLyDon, QuanLyXe quanLyXe, List<TramGiao> dsTram,
			List<KhoangCachCacTram> kc) {
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

	public List<TramGiao> getDsTramMinus() {
		List<TramGiao> ds = new ArrayList<TramGiao>();
		for (TramGiao tramGiao : dsTram) {
			if (!TenNSX.equals(tramGiao.getTenTram())) {
				ds.add(tramGiao);
			}
		}
		return ds;
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

	public int tongSoLuongXe() {
		return quanLyXe.tongSoLuongXe();
	}

	public int tongXLDonXe() {
		return quanLyXe.tongXLDonXe();
	}
	public List<Xe> getListXe() {
		return quanLyXe.getListXe();
	}
	public void capNhatDonHangChoXe(Map<Xe,List<DonHang>> map)
	{
		for (Entry<Xe, List<DonHang>> entry : map.entrySet()) {
			int key = entry.getKey().getId();
			List<DonHang> val = entry.getValue();
			capNhatDonHangChoXe(key,val);
		}
	}
	public void capNhatDonHangChoXe(int id,List<DonHang> donHangs) {
		for (Xe xe : getListXe()) {
			if(id==xe.getId())
				xe.setDsDonHang(donHangs);
		}
	}
}