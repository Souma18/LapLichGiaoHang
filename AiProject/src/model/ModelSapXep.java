package model;

import java.util.*;

import data.CumGiao;
import data.DonHang;
import data.TramGiao;
import data.Xe;

public class ModelSapXep {

	public List<DonHang> knapsack(double sucChua, List<DonHang> danhSachDonHang) {
		double sucChuaConLai = sucChua;

		if (danhSachDonHang.isEmpty() || sucChuaConLai == 0) {
			return new ArrayList<>();
		}

		DonHang donHang = danhSachDonHang.get(danhSachDonHang.size() - 1);

		// Tạo danh sách còn lại (không bao gồm đơn hàng hiện tại)
		List<DonHang> danhSachConLai = new ArrayList<>(danhSachDonHang);
		danhSachConLai.remove(donHang);

		if (donHang.getTrongLuong() > sucChuaConLai) {
			// Bỏ qua đơn hàng hiện tại nếu quá tải
			return knapsack(sucChua, danhSachConLai);
		} else {
			// Xét 2 trường hợp:
			// 1. Lấy đơn hàng hiện tại
			List<DonHang> danhSachCoDonHang = knapsack(sucChua - donHang.getTrongLuong(), danhSachConLai);
			danhSachCoDonHang.add(donHang);

			// 2. Không lấy đơn hàng hiện tại
			List<DonHang> danhSachKhongCoDonHang = knapsack(sucChua, danhSachConLai);

			// So sánh giá trị tổng giữa 2 lựa chọn
			double giaTriCoDonHang = danhSachCoDonHang.stream().mapToDouble(DonHang::getGiaTri).sum();
			double giaTriKhongCoDonHang = danhSachKhongCoDonHang.stream().mapToDouble(DonHang::getGiaTri).sum();

			return giaTriCoDonHang > giaTriKhongCoDonHang ? danhSachCoDonHang : danhSachKhongCoDonHang;
		}
	}

	// sắp hàng lên xe
	public Map<Xe, List<DonHang>> sapXepHangLenXe(List<CumGiao> listCumGiao) {
		Map<Xe, List<DonHang>> ketQuaXepHang = new HashMap<>();

		for (CumGiao cum : listCumGiao) {
			List<Xe> listXe = cum.getListXe();
			if (listXe.isEmpty()) {
				System.out.println("Không có xe trong cụm " + cum.getId());
				continue;
			}

			for (Xe xe : listXe) {
				double sucChuaToiDa = xe.getSucChuaToiDa();
				List<DonHang> allDonHang = new ArrayList<>();
				List<TramGiao> tramList = cum.getListTram();

				// Tập hợp tất cả đơn hàng từ các trạm giao
				for (TramGiao tram : tramList) {
					allDonHang.addAll(tram.getListDonHang());
				}

				// Gọi hàm knapsack để lấy danh sách đơn hàng phù hợp
				List<DonHang> danhSachXep = knapsack(sucChuaToiDa, allDonHang);

				// Gắn danh sách đơn hàng vào xe
				xe.setDsDonHang(danhSachXep);

				// Lưu trạng thái xe vào cụm giao
				ketQuaXepHang.put(xe, danhSachXep);

				// Xóa các đơn hàng đã được sắp xếp khỏi trạm giao
				for (DonHang dh : danhSachXep) {
					TramGiao tram = cum.getTram(dh.getTramGiao());
					if (tram != null) {
						tram.xoaDonHang(dh);
					}
				}
			}
		}
		return ketQuaXepHang;
	}

}
