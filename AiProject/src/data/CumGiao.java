package data;

import java.util.LinkedList;
import java.util.List;

public class CumGiao {
	private int id;
	private List<TramGiao> cumGiao = new LinkedList<TramGiao>();
	private TuyenDuong tuyenDuong;
	private double khoiLuongDon;

	public CumGiao(int id) {
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TuyenDuong getTuyenDuong() {
		return tuyenDuong;
	}

	public void setTuyenDuong(TuyenDuong tuyenDuong) {
		this.tuyenDuong = tuyenDuong;
	}

	public double getKhoiLuongDon() {
		return khoiLuongDon;
	}

	public void setKhoiLuongDon(double khoiLuongDon) {
		this.khoiLuongDon = khoiLuongDon;
	}

	public List<TramGiao> getCumGiao() {
		return cumGiao;
	}

	public void setCumGiao(List<TramGiao> cumGiao) {
		this.cumGiao = cumGiao;
	}

	public void addTramGiao(TramGiao t) {
		this.cumGiao.add(t);
	}
}
