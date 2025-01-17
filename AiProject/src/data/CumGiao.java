package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CumGiao {
	private int id;
	private List<TramGiao> listTram;
	private List<Xe> listXe;
	private double khoiLuongDon;

	public CumGiao(int id) {
		super();
		this.id = id;
		this.listTram = new ArrayList<TramGiao>();
		this.listXe = new ArrayList<Xe>();
		this.khoiLuongDon = 0;
	}

	public int getId() {
		return id;
	}

	public CumGiao(int id, List<TramGiao> listTram, List<Xe> listXe) {
		super();
		this.id = id;
		this.listTram = listTram;
		this.listXe = listXe;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getKhoiLuongDon() {
		double khoiluong = 0;
		for (TramGiao tramGiao : listTram) {
			khoiluong = tramGiao.getKhoiLuongDon();
		}
		this.khoiLuongDon = khoiluong;
		return khoiluong;
	}

	public void setKhoiLuongDon(double khoiLuongDon) {
		this.khoiLuongDon = khoiLuongDon;
	}

	public List<TramGiao> getListTram() {
		return listTram;
	}

	public void setListTram(List<TramGiao> listTram) {
		this.listTram = listTram;
	}

	public List<Xe> getListXe() {
		return listXe;
	}

	public void setListXe(List<Xe> listXe) {
		this.listXe = listXe;
	}

	@Override
	public String toString() {
		return "CumGiao [id=" + id + ", listTram=" + listTram + ", khoiLuongDon=" + khoiLuongDon + "]";
	}

	public void addTramGiao(TramGiao tramGiao) {
		if (!listTram.contains(tramGiao)) {
			listTram.add(tramGiao);
			khoiLuongDon += tramGiao.getKhoiLuongDon();
			System.out.println("them:" + tramGiao.getTenTram());
		}
	}

	public void addXe(Xe xe) {
		if (!listXe.contains(xe)) {
			listXe.add(xe);
		}
	}

	public int getSoLuongDon() {
		int count = 0;
		for (TramGiao tramGiao : listTram) {
			for (DonHang donHang : tramGiao.getListDonHang()) {
				count++;
			}
		}
		return count;
	}

	public List<DonHang> getDonHangList() {
		List<DonHang> list = new ArrayList<DonHang>();
		for (TramGiao tramGiao : listTram) {
			for (DonHang donHang : tramGiao.getListDonHang()) {
				list.add(donHang);
			}
		}
		return list;
	}

	public TramGiao getTram(String tenTram) {
		for (TramGiao tramGiao : getListTram()) {
			if (tenTram.equals(tramGiao.getTenTram())) {
				return tramGiao;
			}
		}
		return null;
	}

	public void updateXe(Xe xe) {
		// Giả sử listXe là danh sách các xe trong cụm giao
		for (int i = 0; i < listXe.size(); i++) {
			if (listXe.get(i).getId() == (xe.getId())) { // So sánh ID xe
				listXe.set(i, xe); // Cập nhật xe trong danh sách
				break;
			}
		}
	}

	// Trả về tổng giá trị các đơn hàng trong xe thuộc một cụm
	public double getGiaTriCum() {
		double tongGiaTri = 0.0;

		// Duyệt qua danh sách xe trong cụm
		for (Xe xe : listXe) {
			// Lấy danh sách đơn hàng trên xe
			List<DonHang> dsDonHang = xe.getDsDonHang();

			// Tính tổng giá trị của các đơn hàng
			for (DonHang donHang : dsDonHang) {
				tongGiaTri += donHang.getGiaTri();
			}
		}

		return tongGiaTri;
	}

}
