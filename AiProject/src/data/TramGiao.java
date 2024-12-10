package data;

import java.util.ArrayList;
import java.util.List;

public class TramGiao {
	private int id;
	private String tenTram;
	private double chiPhi;
	private ToaDo toaDo;
	private List<DonHang> listDonHang;

	public TramGiao(int id, String tenTram, double chiPhi, ToaDo toaDo) {
		this.id = id;
		this.tenTram = tenTram;
		this.chiPhi = chiPhi;
		this.toaDo = toaDo;
		this.listDonHang = new ArrayList<DonHang>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenTram() {
		return tenTram;
	}

	public void setTenTram(String tenTram) {
		this.tenTram = tenTram;
	}

	public double getChiPhi() {
		return chiPhi;
	}

	public void setChiPhi(double chiPhi) {
		this.chiPhi = chiPhi;
	}

	public ToaDo getToaDo() {
		return toaDo;
	}

	public void setToaDo(ToaDo toaDo) {
		this.toaDo = toaDo;
	}

	public List<DonHang> getListDonHang() {
		return listDonHang;
	}

	public void setListDonHang(List<DonHang> listDonHang) {
		this.listDonHang = listDonHang;
	}
	public void themDonHang(DonHang donHang) {
		this.listDonHang.add(donHang);
	}

	// Xóa đơn hàng khỏi trạm
	public void xoaDonHang(DonHang donHang) {
		this.listDonHang.remove(donHang);
	}

	@Override
	public String toString() {
		return "TramGiao [id=" + id + ", tenTram=" + tenTram + ", chiPhi=" + chiPhi + ", toaDo=" + toaDo
				+ ", listDonHang=" + listDonHang + "]";
	}

	public int getSLDon() {
		return listDonHang.size();
	}

	public double getKhoiLuongDon() {
		double sum = 0;
		for (DonHang donHang : listDonHang) {
			sum += donHang.getTrongLuong();
		}
		return sum;
	}

	}
