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

//lấy nsx
	public NSX getNSX() {
		return this.nxs;
	}

//lấy danh sách cụm
	public List<CumGiao> getListCumGiao() {
		return modelCumGiao.getListCumGiao();
	}

//	lấy số lượng cụm
	public int soLuongCumDuocPhan() {
		return getListCumGiao().size();
	}

//lấy tuyến đường
	public Map<CumGiao, List<TuyenDuongDuocTaoRa>> tuyenduong() {
		List<TuyenDuongDuocTaoRa> tuyenduong = new ArrayList<TuyenDuongDuocTaoRa>();
		Map<CumGiao, List<TuyenDuongDuocTaoRa>> map = new HashMap<CumGiao, List<TuyenDuongDuocTaoRa>>();
		for (CumGiao cumGiao : modelCumGiao.getListCumGiao()) {
			modelTuyenDuong = new ModelTuyenDuong(cumGiao,new BanDo(nxs.getKc()), cumGiao.getListXe());
			tuyenduong = modelTuyenDuong.listTuyenDuongTaoRa();
			map.put(cumGiao, tuyenduong);
		}
		return map;
	}

	public static void main(String[] args) {
		List<DonHang> donHangList = new ArrayList<>();
		donHangList.add(new DonHang(1, "Trạm A", "Thường", 10, 1, 100000));
		donHangList.add(new DonHang(2, "Trạm A", "Hỏa tốc", 10, 3, 500000));
		donHangList.add(new DonHang(3, "Trạm A", "Giá trị", 10, 2, 300000));
		donHangList.add(new DonHang(4, "Trạm A", "Thường", 20, 1, 120000));
		donHangList.add(new DonHang(5, "Trạm A", "Hỏa tốc", 20, 3, 450000));
		donHangList.add(new DonHang(6, "Trạm A", "Giá trị", 20, 2, 320000));
		donHangList.add(new DonHang(7, "Trạm A", "Hỏa tốc", 20, 3, 470000));
		donHangList.add(new DonHang(8, "Trạm B", "Giá trị", 10, 2, 350000));
		donHangList.add(new DonHang(8, "Trạm C", "Giá trị", 10, 2, 350000));
		donHangList.add(new DonHang(8, "Trạm C", "Giá trị", 10, 2, 350000));
		QuanLyDon quanLyDon = new QuanLyDon(donHangList);
		List<Xe> xeList = new ArrayList<>();
		double sucChuaToiDa = 100.0; // Đơn vị: kg
		int soDonToiDa = 10; // Số đơn tối đa mỗi xe có thể chở
		// Tạo danh sách 20 xe
		for (int i = 1; i <= 3; i++) {
			int bienSo = 1000 + i; // Giả định biển số tăng dần từ 1001
			xeList.add(new Xe(i, bienSo, sucChuaToiDa, soDonToiDa));
		}
		QuanLyXe quanLyXe = new QuanLyXe(xeList);
		List<TramGiao> danhSachTram = new ArrayList<>();
		TramGiao tramNSX = new TramGiao(111, "Nhà sản xuất hàng hóa", 0, null);
		TramGiao tramA = new TramGiao(1, "Trạm A", 0, null);
		TramGiao tramB = new TramGiao(2, "Trạm B", 0, null);
		TramGiao tramC = new TramGiao(3, "Trạm C", 0, null);
		danhSachTram.add(tramNSX);
		danhSachTram.add(tramA);
		danhSachTram.add(tramB);
		danhSachTram.add(tramC);
		List<KhoangCachCacTram> khoangCachList = new ArrayList<>();
		// Khoảng cách giữa các trạm
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramA, 10.0, 300.0));
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramB, 11.0, 500.0));
		khoangCachList.add(new KhoangCachCacTram(tramA, tramB, 10, 700.0));
		khoangCachList.add(new KhoangCachCacTram(tramA, tramB, 9, 850.0));
		khoangCachList.add(new KhoangCachCacTram(tramC, tramB, 9, 850.0));

		NSX nsx = new NSX("Nhà sản xuất hàng hóa", quanLyDon, quanLyXe, danhSachTram, khoangCachList);
		Model model = new Model(nsx);
		CumGiao cum = new CumGiao(1, danhSachTram, xeList);
		System.out.println("--" + model.getListCumGiao().size());
		ModelTuyenDuong m = new ModelTuyenDuong(cum, new BanDo(khoangCachList), xeList);
		List<TuyenDuongDuocTaoRa> tuyen = m.listTuyenDuongTaoRa();
		int i=0;
		for (TuyenDuongDuocTaoRa tuyenDuongDuocTaoRa : tuyen) {
			System.out.println("lan"+i++);
		}
	}
}
