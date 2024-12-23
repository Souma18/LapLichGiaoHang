package model;

import java.security.KeyPair;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import data.CumGiao;
import data.DonHang;
import data.KhoangCachCacTram;
import data.NSX;
import data.QuanLyDon;
import data.QuanLyXe;
import data.SoLuongVaKhoiLuong;
import data.ToaDo;
import data.TramGiao;
import data.Xe;
import view.View;

public class ModelCumGiao {
	private List<CumGiao> listCumGiao;
	private NSX nsx;

	public ModelCumGiao(NSX nSX) {
		nsx = nSX;
		this.listCumGiao = new ArrayList<CumGiao>();
		clustering();
	}

	public List<CumGiao> getListCumGiao() {
		return listCumGiao;
	}

	public void setListCumGiao(List<CumGiao> listCumGiao) {
		this.listCumGiao = listCumGiao;
	}

	public TramGiao tramNSX() {
		try {
			List<TramGiao> dsTram = nsx.getDsTram();
			TramGiao tramNSX = null;
			for (TramGiao tramGiao : dsTram) {
				if (tramGiao.getTenTram().equals(nsx.getTenNSX())) {
					tramNSX = tramGiao;
					break;
				}
			}
			return tramNSX;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	trạm gần nhất
	public TramGiao timTramGanNhat(TramGiao tramHienTai, List<KhoangCachCacTram> danhSachKC,
            List<TramGiao> tramDaPhanCum) {
TramGiao tramGanNhat = null;

// Duyệt qua danh sách KhoangCachCacTram để tìm trạm chưa phân cụm gần trạm hiện tại
for (KhoangCachCacTram kc : danhSachKC) {
TramGiao tramXet = null;

// Xác định trạm đối diện với tramHienTai
if (kc.getTramXuatPhat().equals(tramHienTai)) {
tramXet = kc.getTramDich();
} else if (kc.getTramDich().equals(tramHienTai)) {
tramXet = kc.getTramXuatPhat();
}

// Kiểm tra xem trạm đã được phân cụm chưa
if (tramXet != null && !tramDaPhanCum.contains(tramXet)) {
tramGanNhat = tramXet;
break;  // Chọn trạm đầu tiên chưa phân cụm
}
}

return tramGanNhat;
}

//trạm đã được phân vào cụm
	public boolean TramDaPhanCum(TramGiao tramgiao, List<TramGiao> tramDaPhanCum) {
		return tramDaPhanCum.contains(tramgiao);
	}

	// thêm trạm vào cum
	public void addTram(TramGiao tramgiao, List<TramGiao> tramDaPhanCum) {
		if (!TramDaPhanCum(tramgiao, tramDaPhanCum)) {
			tramDaPhanCum.add(tramgiao);
		}
	}

//lỗi điều kiện 
	public boolean kiemTraXeVaDonHang() {
		return nsx.tongTaiTrongXe() >= nsx.khoiLuongDon() && nsx.tongXLDonXe() >= nsx.SoLuongDon();
	}

	public void clustering() {
		if (!kiemTraXeVaDonHang()) {
			System.out.println("Lỗi dữ liệu sai, không đủ xe hoặc đơn hàng");
			return;
		}

		// Dữ liệu từ NSX
		List<Xe> dsXe = nsx.getQuanLyXe().getListXe();
		List<KhoangCachCacTram> danhSachKC = nsx.getKc();
		List<TramGiao> dsTram = nsx.getDsTram();

		TramGiao tramNSX = this.tramNSX();
		List<TramGiao> tramDaPhanCum = new LinkedList<>();
		tramDaPhanCum.add(tramNSX);

		// Map để lưu thông tin trạm thừa hàng
		Map<TramGiao, SoLuongVaKhoiLuong> tramThuaHang = new HashMap<TramGiao, SoLuongVaKhoiLuong>();

		int idCum = 0;

		// Duyệt qua từng xe
		for (Xe xe : dsXe) {
			System.out.println("Xe " + xe.getBienSo() + "-------------");

			// Nếu đã đánh dấu tất cả trạm
			if (tramDaPhanCum.size() == dsTram.size()) {
				System.out.println("Thành công: Đã phân cụm cho tất cả trạm.");
				break;
			}

			// Tạo cụm mới
			CumGiao cum = new CumGiao(idCum++);
			cum.addTramGiao(tramNSX);
			cum.addXe(xe);
			listCumGiao.add(cum);

			// Tính toán khối lượng và số lượng đơn hàng còn lại trên xe
			double khoiLuong = xe.getSucChuaToiDa();
			int soLuong = xe.getSoDonToiDa();
			System.out.println("Khối lượng bắt đầu: " + khoiLuong);
			System.out.println("Số lượng bắt đầu: " + soLuong);

			// Duyệt qua các trạm giao
			for (TramGiao tramGiao : dsTram) {
				if (TramDaPhanCum(tramGiao, tramDaPhanCum)) {
					continue;
				}

				System.out.println("Đang xử lý trạm: " + tramGiao.getTenTram());

				// Tính hàng thừa
				double khoiLuongThua = Math.max(0, tramGiao.getKhoiLuongDon() - khoiLuong);
				int soLuongThua = Math.max(0, tramGiao.getSLDon() - soLuong);

				if (khoiLuongThua > 0 || soLuongThua > 0) {
					// Lưu thông tin hàng thừa vào Map
					tramThuaHang.put(tramGiao, new SoLuongVaKhoiLuong(soLuongThua, khoiLuongThua));
					System.out.println("Trạm " + tramGiao.getTenTram() + " bị vượt tải, hàng thừa: " + khoiLuongThua
							+ "kg, " + soLuongThua + " đơn.");
				}

				// Thêm trạm vào cụm hiện tại
				cum.addTramGiao(tramGiao);
				tramDaPhanCum.add(tramGiao);

				// Cập nhật tải trọng và số lượng nếu không vượt tải
				if (khoiLuongThua == 0 && soLuongThua == 0) {
					khoiLuong -= tramGiao.getKhoiLuongDon();
					soLuong -= tramGiao.getSLDon();
					System.out.println("Khối lượng còn lại: " + khoiLuong + ", số lượng còn lại: " + soLuong);
				} else {
					// Nếu vượt tải, dừng vòng lặp để xử lý xe tiếp theo
					break;
				}
			}
			// Xử lý danh sách hàng thừa
			xuLyHangThua(tramThuaHang, tramDaPhanCum);
		}

	}
	private void xuLyHangThua(Map<TramGiao, SoLuongVaKhoiLuong> map, List<TramGiao> tramDaPhanCum) {
//		for (Map.Entry<TramGiao, SoLuongVaKhoiLuong> entry : map.entrySet()) {
//			TramGiao tramGiao = entry.getKey();
//			SoLuongVaKhoiLuong hangThua = entry.getValue();
//
//			System.out.println("Xử lý hàng thừa tại trạm: " + tramGiao.getTenTram());
//			double khoiLuongThua = hangThua.getKhoiLuong();
//			int soLuongThua = hangThua.getSoLuong();
//
//			// Tìm trạm gần nhất có khả năng chứa thêm hàng
//			TramGiao tramGanNhat = timTramGanNhat(tramGiao, nsx.getKc(), tramDaPhanCum);
//			if (tramGanNhat != null) {
//				CumGiao cumGanNhat = timCumChuaTram(tramGanNhat);
//
//				// Cập nhật cụm và giảm hàng thừa
//				if (cumGanNhat != null) {
//					double khoiLuongConLai = cumGanNhat.getKhoiLuongDon();
//					int soLuongConLai = cumGanNhat.getSoLuongDon();
//
//					double khoiLuongPhanBo = Math.min(khoiLuongConLai, khoiLuongThua);
//					int soLuongPhanBo = Math.min(soLuongConLai, soLuongThua);
//
//					cumGanNhat.giamKhoiLuong(khoiLuongPhanBo);
//					cumGanNhat.giamSoLuong(soLuongPhanBo);
//
//					khoiLuongThua -= khoiLuongPhanBo;
//					soLuongThua -= soLuongPhanBo;
//
//					System.out.println("Phân phối thêm cho trạm gần nhất: " + tramGanNhat.getTenTram());
//				}
//			}
//
//			// Nếu còn hàng thừa, thông báo hoặc xử lý thêm xe mới
//			if (khoiLuongThua > 0 || soLuongThua > 0) {
//				System.out.println("Còn lại hàng thừa tại " + tramGiao.getTenTram() + ": " + khoiLuongThua + "kg, "
//						+ soLuongThua + " đơn.");
//			}
//		}
	}
}
