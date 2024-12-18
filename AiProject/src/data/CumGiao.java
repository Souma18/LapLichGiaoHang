package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CumGiao {
	private int id;
	private List<TramGiao> listTram;
	private List<Xe> listXe;
//		private TuyenDuong tuyenDuong;
	private double khoiLuongDon;

	public CumGiao(int id) {
		super();
		this.id = id;
		this.listTram = new ArrayList<TramGiao>();
		this.listXe = new ArrayList<Xe>();
//			this.tuyenDuong = new TuyenDuong();
		this.khoiLuongDon = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//		public TuyenDuong getTuyenDuong() {
//			return tuyenDuong;
//		}
	//
//		public void setTuyenDuong(TuyenDuong tuyenDuong) {
//			this.tuyenDuong = tuyenDuong;
//		}

//		public void setTuyenDuong(List<KhoangCachCacTram> kc) {
//			List<KhoangCachCacTram> TuyenCum = new ArrayList<KhoangCachCacTram>();
//			for (TramGiao tramGiao : listTram) {
//				for (KhoangCachCacTram khoangCachCacTram : kc) {
//					if (!TuyenCum.contains(khoangCachCacTram)) {
//						if (khoangCachCacTram.getTramDich().equals(tramGiao)
//								|| khoangCachCacTram.getTramXuatPhat().equals(tramGiao)) {
//							TuyenCum.add(khoangCachCacTram);
//						}
//					}
//				}
//			}
//			this.tuyenDuong = new TuyenDuong(TuyenCum);
//		}

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
}
