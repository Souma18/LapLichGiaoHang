package data;

import java.util.List;

public class Xe {
	private int id;
	private int bienSo;
	private double sucChuaToiDa;
	private int soDonToiDa;
	private double soDiemDatDuoc;
	private List<DonHang> dsDonHang;

	public Xe(int id, int bienSo, double sucChuaToiDa, int soDonToiDa) {
		this.id = id;
		this.bienSo = bienSo;
		this.sucChuaToiDa = sucChuaToiDa;
		this.soDonToiDa = soDonToiDa;
		this.soDiemDatDuoc = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBienSo() {
		return bienSo;
	}

	public void setBienSo(int bienSo) {
		this.bienSo = bienSo;
	}

	public double getSucChuaToiDa() {
		return sucChuaToiDa;
	}

	public void setSucChuaToiDa(double sucChuaToiDa) {
		this.sucChuaToiDa = sucChuaToiDa;
	}

	public int getSoDonToiDa() {
		return soDonToiDa;
	}

	public void setSoDonToiDa(int soDonToiDa) {
		this.soDonToiDa = soDonToiDa;
	}

	public double getSoDiemDatDuoc() {
		return soDiemDatDuoc;
	}

	public void setSoDiemDatDuoc(double soDiemDatDuoc) {
		this.soDiemDatDuoc = soDiemDatDuoc;
	}

	public List<DonHang> getDsDonHang() {
		return dsDonHang;
	}

	public void setDsDonHang(List<DonHang> dsDonHang) {
		this.dsDonHang = dsDonHang;
	}
	public int soLuongDon() {
		return dsDonHang.size();
	}
}
