package data;

import java.util.List;

public class TramGiao {
	private int id;
	private String tenTram;
	private double chiPhi;
	private ToaDo toaDo;
	private List<DonHang>listDonHang;

	public TramGiao(int id, String tenTram, double chiPhi, ToaDo toaDo, List<DonHang> listDonHang) {
		this.id = id;
		this.tenTram = tenTram;
		this.chiPhi = chiPhi;
		this.toaDo = toaDo;
		this.listDonHang = listDonHang;
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

	    // Hiển thị thông tin trạm giao và các đơn hàng
	    @Override
	    public String toString() {
	        return "TramGiao [tenTram=" + tenTram + ", danhSachDonHang=" + listDonHang + "]";
	    }
	
}
