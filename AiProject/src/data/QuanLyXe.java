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
	
}
