package model;

import java.util.List;
import java.util.Map;

import data.CumGiao;
import data.DonHang;
import data.Xe;

public class ModelCumGiao {
	private List<CumGiao>listCumGiao;
	private Map<Integer,List<DonHang>> mapDonHangCumGiao;
	private Map<Integer,List<Xe>> mapXeCumGiao;
	public ModelCumGiao(List<CumGiao> listCumGiao, Map<Integer, List<DonHang>> mapDonHangCumGiao,
			Map<Integer, List<Xe>> mapXeCumGiao) {
		this.listCumGiao = listCumGiao;
		this.mapDonHangCumGiao = mapDonHangCumGiao;
		this.mapXeCumGiao = mapXeCumGiao;
	}
	public List<CumGiao> getListCumGiao() {
		return listCumGiao;
	}
	public void setListCumGiao(List<CumGiao> listCumGiao) {
		this.listCumGiao = listCumGiao;
	}
	public Map<Integer, List<DonHang>> getMapDonHangCumGiao() {
		return mapDonHangCumGiao;
	}
	public void setMapDonHangCumGiao(Map<Integer, List<DonHang>> mapDonHangCumGiao) {
		this.mapDonHangCumGiao = mapDonHangCumGiao;
	}
	public Map<Integer, List<Xe>> getMapXeCumGiao() {
		return mapXeCumGiao;
	}
	public void setMapXeCumGiao(Map<Integer, List<Xe>> mapXeCumGiao) {
		this.mapXeCumGiao = mapXeCumGiao;
	}
	//phan chia don hang ve dung tram giao
		public void phanChiaDon() {
			
		}
	//phan cum

}
