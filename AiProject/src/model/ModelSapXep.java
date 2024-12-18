package model;

import java.util.ArrayList;
import java.util.List;

import data.CumGiao;
import data.DonHang;
import data.TramGiao;
import data.Xe;

public class ModelSapXep {


	private double knapsack(double sucChua, double[] trongLuong, int[] giaTri, List<DonHang> danhSachDonHang) {
		List<DonHang> danhSachXep = new ArrayList<>();
		double sucChuaConLai = sucChua;

		if (danhSachDonHang.isEmpty() || sucChuaConLai == 0) {
			return 0;
		}

		DonHang donHang = danhSachDonHang.get(danhSachDonHang.size() - 1);

		List<DonHang> danhSachDonHangConLai = new ArrayList<>(danhSachDonHang);
		danhSachDonHangConLai.remove(donHang);

		if (donHang.getTrongLuong() > sucChuaConLai) {
			return knapsack(sucChuaConLai, trongLuong, giaTri, danhSachDonHangConLai);
		} else {
			double giaTriCoDonHang = donHang.getGiaTri()
					+ knapsack(sucChuaConLai - donHang.getTrongLuong(), trongLuong, giaTri, danhSachDonHangConLai);
			double giaTriKhongCoDonHang = knapsack(sucChuaConLai, trongLuong, giaTri, danhSachDonHangConLai);

			if (giaTriCoDonHang > giaTriKhongCoDonHang) {
				danhSachXep.add(donHang);
				return giaTriCoDonHang;
			} else {
				return giaTriKhongCoDonHang;
			}
		}
	}

//sắp hàng lên xe
	public void sapXepHangLenXe(List<CumGiao> listCumGiao) {
//		List<CumGiao> list = new ArrayList<CumGiao>();
		for (CumGiao cum : listCumGiao) {
			List<Xe> listXe = cum.getListXe();
			if (listXe.isEmpty()) {
				System.out.println("Không có xe trong cụm " + cum.getId());
				continue;
			}

			Xe xe = listXe.get(0);
			double W = xe.getSucChuaToiDa();
			List<DonHang> allDonHang = new ArrayList<>();
			List<TramGiao> tramList = cum.getListTram();

			for (TramGiao tram : tramList) {
				allDonHang.addAll(tram.getListDonHang());
			}

			int n = allDonHang.size();
			double[] wt = new double[n];
			int[] val = new int[n];

			for (int i = 0; i < n; i++) {
				wt[i] = allDonHang.get(i).getTrongLuong();
				val[i] = allDonHang.get(i).getGiaTri();
			}

			List<DonHang> danhSachXep = new ArrayList<>();

			double totalValue = knapsack(W, wt, val, danhSachXep);

			xe.setDsDonHang(danhSachXep);

			for (DonHang dh : danhSachXep) {
				TramGiao tram = cum.getTram(dh.getTramGiao());
				tram.xoaDonHang(dh);
			}

		}
	}
}
