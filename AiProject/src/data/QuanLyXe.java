package data;

import java.util.List;

public class QuanLyXe {
	private List<Xe>listXe;

	public QuanLyXe(List<Xe> listXe) {
		this.listXe = listXe;
	}
	//Phan bo xe

	public List<Xe> getListXe() {
		return listXe;
	}

	public void setListXe(List<Xe> listXe) {
		this.listXe = listXe;
	}

	public double tongTaiTrongXe() {
		double sum = 0;
		for (Xe xe : listXe) {
			sum +=xe.getSucChuaToiDa();
		}
		return sum;
	}

	public int tongXLDonXe() {
		int sum = 0;
		for (Xe xe : listXe) {
			sum+=xe.getSoDonToiDa();
		}
		return sum;
	}
	
}
