package data;

public class KhoangCachCacTram implements Comparable<KhoangCachCacTram>{
	private TramGiao tramXuatPhat;
	private TramGiao tramDich;
	private double khoangCach;
	private double chiPhi;
	public KhoangCachCacTram(TramGiao tramXuatPhat, TramGiao tramDich, double khoangCach, double chiPhi) {
		this.tramXuatPhat = tramXuatPhat;
		this.tramDich = tramDich;
		this.khoangCach = khoangCach;
		this.chiPhi = chiPhi;
	}
	public TramGiao getTramXuatPhat() {
		return tramXuatPhat;
	}
	public void setTramXuatPhat(TramGiao tramXuatPhat) {
		this.tramXuatPhat = tramXuatPhat;
	}
	public TramGiao getTramDich() {
		return tramDich;
	}
	public void setTramDich(TramGiao tramDich) {
		this.tramDich = tramDich;
	}
	public double getKhoangCach() {
		return khoangCach;
	}
	public void setKhoangCach(double khoangCach) {
		this.khoangCach = khoangCach;
	}
	public double getChiPhi() {
		return chiPhi;
	}
	public void setChiPhi(double chiPhi) {
		this.chiPhi = chiPhi;
	}
	@Override
	public String toString() {
		return "KhoangCachCacTram [tramXuatPhat=" + tramXuatPhat + ", tramDich=" + tramDich + ", khoangCach="
				+ khoangCach +"]";
	}
	@Override
	public int compareTo(KhoangCachCacTram o) {
		
		return 0;
	}
	 

}
