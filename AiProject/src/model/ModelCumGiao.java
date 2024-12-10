package model;

import java.util.ArrayList;
import java.util.List;

import data.CumGiao;
import data.KhoangCachCacTram;
import data.NSX;
import data.TramGiao;
import data.Xe;

public class ModelCumGiao {
	private List<CumGiao> listCumGiao;
	private NSX nsx;

	public ModelCumGiao(NSX nSX) {
		nsx = nSX;
		this.listCumGiao = new ArrayList<CumGiao>();
	}

	public List<CumGiao> getListCumGiao() {
		return listCumGiao;
	}

	public void setListCumGiao(List<CumGiao> listCumGiao) {
		this.listCumGiao = listCumGiao;
	}

	public TramGiao tramNSX() {
		List<TramGiao> dsTram = nsx.getDsTram();
		TramGiao tramNSX = null;
		for (TramGiao tramGiao : dsTram) {
			if (tramGiao.getTenTram().equals(nsx.getTenNSX())) {
				tramNSX = tramGiao;
				break;
			}
		}
		if (tramNSX == null) {
			throw new IllegalStateException("Không tìm thấy trạm NSX!");
		}
		return tramNSX;
	}

	/*
	 * mô tả bài toám điều kiện : mỗi cụm phải thỏa yêu cầu cả về trọng lượng và số
	 * lượng đơn xe :có bao nhieu xe đưa ra được khối lượng tối đa và số đơn chở tối
	 * đa tổng kết được số lượng đơn hàng của mỗi trạm
	 * 
	 */
///////////////////////////////////////////////////////////////
	public int soLuongXeCanThiet(CumGiao cumGiao) {
		double khoiLuong = cumGiao.getKhoiLuongDon();
		double sucChuaXe = nsx.getQuanLyXe().getListXe().get(0).getSucChuaToiDa();
		double soLuongDonXe = nsx.getQuanLyXe().getListXe().get(0).getSoDonToiDa();

		// Tính số lượng xe cần thiết dựa trên khối lượng đơn và số lượng đơn
		int xeTheoKL = (int) Math.ceil(khoiLuong / sucChuaXe);
		int xeTheoSL = (int) Math.ceil(cumGiao.getCumGiao().size() / soLuongDonXe);

		// Trả về số lượng xe tối thiểu cần thiết cho cụm
		return Math.max(xeTheoKL, xeTheoSL);
	}

	public int soLuongXeCanThiet() {
		double xeTheoKL = Math.ceil(nsx.khoiLuongDon() / nsx.getQuanLyXe().getListXe().get(0).getSucChuaToiDa());
		double xeTheoSL = Math.ceil(nsx.SoLuongDon() / nsx.getQuanLyXe().getListXe().get(0).getSoDonToiDa());
		return (int) Math.max(xeTheoKL, xeTheoSL);
	}

/////////////////////////////////////////////////////////////
//	trạm gần nhất
	public TramGiao timTramGanNhat(TramGiao tramHienTai, List<KhoangCachCacTram> danhSachKC,
			List<TramGiao> tramDaPhanCum) {
		TramGiao tramGanNhat = null;
		for (KhoangCachCacTram kc : danhSachKC) {
			if (kc.getTramXuatPhat().equals(tramHienTai)) {
				tramGanNhat = kc.getTramDich();
			} else if (kc.getTramDich().equals(tramHienTai)) {
				tramGanNhat = kc.getTramXuatPhat();
			}

			if (!tramDaPhanCum.contains(tramGanNhat)) {
				return tramGanNhat;
			}
		}
		return null;
	}

//trạm đã được phân vào cụm
	public boolean TramDaPhanCum(TramGiao tramgiao, List<TramGiao> tramDaPhanCum) {
		return tramDaPhanCum.contains(tramgiao);
	}

	// clustering
	public void clusteringA() throws Exception {
//dữ liệu từ nsx
		List<Xe> dsXe = new ArrayList<Xe>();
		List<TramGiao> dsTram = new ArrayList<TramGiao>();
		List<KhoangCachCacTram> danhSachKC = new ArrayList<KhoangCachCacTram>();
		dsXe = nsx.getQuanLyXe().getListXe();
		danhSachKC = nsx.getKc();
		dsTram = nsx.getDsTram();
//
		TramGiao tramNSX = this.tramNSX();
		dsTram.remove(tramNSX);
		List<TramGiao> tramDaPhanCum = new ArrayList<TramGiao>();
		int idCum = 0;

		// Kiểm tra dữ liệu đầu vào
		if (nsx.tongTaiTrongXe() < nsx.khoiLuongDon() || nsx.tongXLDonXe() < nsx.SoLuongDon()) {
			throw new Exception("Lỗi dữ liệu sai, không đủ xe hoặc đơn hàng!");
		}

		double khoiLuongThua = 0;
		int soLuongThua = 0;
		TramGiao tramTamThoi = null;
		// Duyệt qua từng xe
		for (Xe xe : dsXe) {
			System.out.println("--------------------------");
			System.out.println(tramDaPhanCum.size());
			System.out.println(dsTram.size());
			System.out.println("--------------------------");
//			nếu đã đánh dấu tất cả trạm
			if (tramDaPhanCum.size() == dsTram.size()) {
				break;
			}
			CumGiao cum = new CumGiao(idCum++);
			cum.addTramGiao(tramNSX);
			// Nếu có trạm giao tạm thời, thêm vào cụm
			if (tramTamThoi != null) {
				cum.addTramGiao(tramTamThoi);
			}

			cum.addXe(xe);
			listCumGiao.add(cum);

			// Tính toán khối lượng và số lượng đơn hàng còn lại trên xe
			double khoiLuong = xe.getSucChuaToiDa() - khoiLuongThua;
			int soLuong = xe.getSoDonToiDa() - soLuongThua;
			// Duyệt qua các trạm giao
			for (TramGiao tramGiao : dsTram) {
				// Kiểm tra xem trạm này đã được phân vào cụm chưa
				if (!TramDaPhanCum(tramGiao, tramDaPhanCum)) {
					khoiLuong -= tramGiao.getKhoiLuongDon();
					soLuong -= tramGiao.getSLDon();
					TramGiao tramGanNhat = timTramGanNhat(tramGiao, danhSachKC, tramDaPhanCum);
					if (tramGanNhat != null) {
						// Kiểm tra nếu xe còn đủ chỗ để chứa trạm giao này
						if (khoiLuong >= 0 && soLuong >= 0) {
							tramTamThoi = null;
							tramDaPhanCum.add(tramGiao);
							cum.addTramGiao(tramGiao);
						} else {
							// Nếu xe không đủ chỗ để chứa hết trạm giao, lưu lại phần dư
							tramTamThoi = tramGiao;
							khoiLuongThua = tramGiao.getKhoiLuongDon() - khoiLuong;
							soLuongThua = tramGiao.getSLDon() - soLuong;

							// Thêm trạm giao vào cụm mặc dù không thể chứa hết
							cum.addTramGiao(tramGiao);

							// Lưu trạm giao tạm thời vào danh sách đã phân cụm
							tramDaPhanCum.add(tramTamThoi);
							break;
						}
					}
				}
			}
//			setTuyenDuong();
		}
	}

//	public void setTuyenDuong() {
//		List<KhoangCachCacTram> kc = nsx.getKc();
//		for (CumGiao cumGiao : listCumGiao) {
//			cumGiao.setTuyenDuong(kc);
//		}
//	}
}
