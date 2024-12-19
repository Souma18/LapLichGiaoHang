package model;

import java.util.List;

import data.CumGiao;
import data.TuyenDuongDuocTaoRa;
import data.Xe;
import findingPath.BanDo;
import findingPath.PhanPhoi;
import findingPath.TuyenDuong;

public class ModelTuyenDuong {
	// xu ly tuyen duong
	private CumGiao cum;
	private BanDo bd;
	private List<Xe> listXe;

	public ModelTuyenDuong(CumGiao cum, BanDo bd, List<Xe> listXe) {
		this.cum = cum;
		this.bd = bd;
		this.listXe = listXe;
	}

	public BanDo getBd() {
		return bd;
	}

	public void setBd(BanDo bd) {
		this.bd = bd;
	}

	public List<Xe> getListXe() {
		return listXe;
	}

	public void setListXe(List<Xe> listXe) {
		this.listXe = listXe;
	}

	public CumGiao getCum() {
		return cum;
	}

	public void setCum(CumGiao cum) {
		this.cum = cum;
	}

	public BanDo getBando() {
		return bd;
	}

	public void setBando(BanDo bando) {
		this.bd = bando;
	}

	public List<TuyenDuongDuocTaoRa> listTuyenDuongTaoRa() {
		TuyenDuong t = new TuyenDuong();
		PhanPhoi p = new PhanPhoi();
		t.setBanDo(bd);
		t.addTuyenDuong(cum);
		p.setListXe(getListXe());
		p.coordinatedAction(t);
		return p.getListTuyenDuongDuocTaoRa();
	}

	public  void printtt(List<TuyenDuongDuocTaoRa> list) {
		for (TuyenDuongDuocTaoRa tuyenDuongDuocTaoRa : list) {
			System.out.println(tuyenDuongDuocTaoRa.toString());
		}
	}

}
