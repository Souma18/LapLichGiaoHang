package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.CumGiao;
import data.DonHang;
import data.KhoangCachCacTram;
import data.NSX;
import data.QuanLyDon;
import data.QuanLyXe;
import data.TramGiao;
import data.TuyenDuongDuocTaoRa;
import data.Xe;
import findingPath.BanDo;

public class Model {
	private ModelCumGiao modelCumGiao;
	private ModelTuyenDuong modelTuyenDuong;
	private ModelSapXep modelSapXep;
	private NSX nxs;

	public Model(NSX nsx) {
		this.nxs = nsx;
		this.modelCumGiao = new ModelCumGiao(nsx);
	}

	// lấy nsx
	public NSX getNSX() {
		return this.nxs;
	}

	// lấy danh sách cụm
	public List<CumGiao> getListCumGiao() {
		return modelCumGiao.getListCumGiao();
	}

	// lấy số lượng cụm
	public int soLuongCumDuocPhan() {
		return getListCumGiao().size();
	}

	// lấy ds đơn hàng cho từng xe
	public Map<Xe, List<DonHang>> xepHangLenXe() {
		return modelSapXep.sapXepHangLenXe(getListCumGiao());
	}

	// lấy tuyến đường
	public Map<CumGiao, List<TuyenDuongDuocTaoRa>> tuyenduong() {
		List<TuyenDuongDuocTaoRa> tuyenduong = new ArrayList<TuyenDuongDuocTaoRa>();
		Map<CumGiao, List<TuyenDuongDuocTaoRa>> map = new HashMap<CumGiao, List<TuyenDuongDuocTaoRa>>();
		for (CumGiao cumGiao : modelCumGiao.getListCumGiao()) {
			modelTuyenDuong = new ModelTuyenDuong(cumGiao, new BanDo(nxs.getKc()), cumGiao.getListXe());
			tuyenduong = modelTuyenDuong.listTuyenDuongTaoRa();
			map.put(cumGiao, tuyenduong);
		}
		return map;
	}

	public TramGiao timTram(String name) {
		for (TramGiao tram : nxs.getDsTram()) {
			if (name.equals(tram.getTenTram())) {
				return tram;
			}
		}
		return null;
	}
}
