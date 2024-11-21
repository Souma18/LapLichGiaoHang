package data;

public class DonHang {
	private int id;
	private TramGiao tramGiao;
	private String loaiDon;
	private double trongLuong;
	private int giaTri;
	private double giaTien;
	public DonHang(int id, TramGiao tramGiao, String loaiDon, double trongLuong, int giaTri, double giaTien) {
		this.id = id;
		this.tramGiao = tramGiao;
		this.loaiDon = loaiDon;
		this.trongLuong = trongLuong;
		this.giaTri = giaTri;
		this.giaTien = giaTien;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TramGiao getTramGiao() {
		return tramGiao;
	}
	public void setTramGiao(TramGiao tramGiao) {
		this.tramGiao = tramGiao;
	}
	public String getLoaiDon() {
		return loaiDon;
	}
	public void setLoaiDon(String loaiDon) {
		this.loaiDon = loaiDon;
	}
	public double getTrongLuong() {
		return trongLuong;
	}
	public void setTrongLuong(double trongLuong) {
		this.trongLuong = trongLuong;
	}
	public int getGiaTri() {
		return giaTri;
	}
	public void setGiaTri(int giaTri) {
		this.giaTri = giaTri;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	
}