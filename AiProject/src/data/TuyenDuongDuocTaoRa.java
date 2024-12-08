package data;

import java.util.List;

import findingPath.TuyenDuong;

public class TuyenDuongDuocTaoRa {
	private Xe xe;
	private List<TramGiao> tuyenDuong;

	public TuyenDuongDuocTaoRa(Xe xe, List<TramGiao> tuyenDuong) {
		this.xe = xe;
		this.tuyenDuong = tuyenDuong;
	}

	public Xe getXe() {
		return xe;
	}

	public void setXe(Xe xe) {
		this.xe = xe;
	}

	public List<TramGiao> getTuyenDuong() {
		return tuyenDuong;
	}

	public void setTuyenDuong(List<TramGiao> tuyenDuong) {
		this.tuyenDuong = tuyenDuong;
	}

}
