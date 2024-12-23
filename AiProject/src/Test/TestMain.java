package Test;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import data.CumGiao;
import data.DonHang;
import data.KhoangCachCacTram;
import data.NSX;
import data.QuanLyDon;
import data.QuanLyXe;
import data.ToaDo;
import data.TramGiao;
import data.Xe;
import model.Model;
import model.ModelCumGiao;
import view.View;

public class TestMain {
		// test 1
		public static void main(String[] args) {
			List<Xe> xeList = new ArrayList<>();
			double sucChuaToiDa = 100.0;
			int soDonToiDa = 10;
			// Số đơn tối đa mỗi xe có thể chở
			for (int i = 1; i <= 10; i++) {
				int bienSo = 1000 + i;
				// biển số tăng dần từ 1001
				xeList.add(new Xe(i, bienSo, sucChuaToiDa, soDonToiDa));
			}
			List<TramGiao> danhSachTramGiao = new ArrayList<TramGiao>();
			TramGiao batDau = new TramGiao(111, "Nhà sản xuất nước ngọt", 11111, new ToaDo(8, 6));
			TramGiao tram1 = new TramGiao(1, "Quận 1", 100000, new ToaDo(6, 5));
			TramGiao tram2 = new TramGiao(2, "Quận 3", 100000, new ToaDo(11, 5));
			TramGiao tram3 = new TramGiao(3, "Quận 5", 100000, new ToaDo(11, 9));
			TramGiao tram4 = new TramGiao(4, "Quận 6", 100000, new ToaDo(3, 4));
			TramGiao tram5 = new TramGiao(5, "Quận 7", 100000, new ToaDo(2, 8));
			TramGiao tram6 = new TramGiao(6, "Quận 10", 100000, new ToaDo(5, 10));
			TramGiao tram7 = new TramGiao(7, "Quận 12", 100000, new ToaDo(13, 4));
			TramGiao tram8 = new TramGiao(8, "Thành Phố Thủ Đức", 100000, new ToaDo(16,6));
			TramGiao tram9 = new TramGiao(9, "Quận Tân Bình", 100000, new ToaDo(9, 11));
			TramGiao tram10 = new TramGiao(10, "Quận Gò Vấp", 100000, new ToaDo(9, 3));
			danhSachTramGiao.add(batDau);
			danhSachTramGiao.add(tram1);
			danhSachTramGiao.add(tram2);
			danhSachTramGiao.add(tram3);
			danhSachTramGiao.add(tram4);
			danhSachTramGiao.add(tram5);
			danhSachTramGiao.add(tram6);
			danhSachTramGiao.add(tram7);
			danhSachTramGiao.add(tram8);
			danhSachTramGiao.add(tram9);
			danhSachTramGiao.add(tram10);
			List<KhoangCachCacTram> map = new ArrayList<KhoangCachCacTram>();
			map.add(new KhoangCachCacTram(batDau, tram1, 5.0, 300.0));
			map.add(new KhoangCachCacTram(batDau, tram2, 5.0, 300.0));
			map.add(new KhoangCachCacTram(batDau, tram3, 5.0, 300.0));
			map.add(new KhoangCachCacTram(batDau, tram8, 5.0, 300.0));
			map.add(new KhoangCachCacTram(batDau, tram9, 5.0, 300.0));
			map.add(new KhoangCachCacTram(batDau, tram10, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram1, batDau, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram1, tram5, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram2, tram8, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram3, tram9, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram4, tram1, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram4, tram5, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram5, tram1, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram5, tram4, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram5, tram6, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram6, tram5, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram7, tram8, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram8, batDau, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram8, tram2, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram8, tram7, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram9, batDau, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram9, tram3, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram10, batDau, 5.0, 300.0));
			map.add(new KhoangCachCacTram(tram10, tram7, 5.0, 300.0));
	/////////////////////////////////////////////
			List<DonHang> donHangList = new ArrayList<>();
			donHangList.add(new DonHang(1, "Quận 1", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(2, "Quận 1", "Hỏa Tốc", 10, 1, 1000000));
			donHangList.add(new DonHang(4, "Quận 1", "Quan Trọng", 10, 1, 1000000));
			donHangList.add(new DonHang(5, "Quận 3", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(6, "Quận 3", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(7, "Quận 3", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(8, "Quận 5", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(9, "Quận 5", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(10, "Quận 6", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(11, "Quận 6", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(12, "Quận 6", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(13, "Quận 6", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(14, "Quận 6", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(15, "Quận 7", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(16, "Quận 7", "Hỏa Tốc", 10, 1, 1000000));
			donHangList.add(new DonHang(17, "Quận 7", "Hỏa Tốc", 10, 1, 1000000));
			donHangList.add(new DonHang(18, "Quận 7", "Hỏa Tốc", 10, 1, 1000000));
			donHangList.add(new DonHang(19, "Quận 10", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(20, "Quận 10", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(21, "Quận 10", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(22, "Quận 10", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(23, "Quận 10", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(24, "Quận 10", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(25, "Quận 12", "Hỏa Tốc", 10, 1, 1000000));
			donHangList.add(new DonHang(26, "Quận 12", "Quan Trọng", 10, 1, 1000000));
			donHangList.add(new DonHang(27, "Quận 12", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(28, "Quận 12", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(29, "Quận Gò Vấp", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(30, "Quận Gò Vấp", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(31, "Quận Gò Vấp", "Hỏa Tốc", 10, 1, 1000000));
			donHangList.add(new DonHang(32, "Quận Gò Vấp", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(33, "Quận Gò Vấp", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(34, "Quận Gò Vấp", "Quan Trọng", 10, 1, 1000000));
			donHangList.add(new DonHang(35, "Quận Tân Bình", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(36, "Thành Phố Thủ Đức", "Thường", 10, 1, 1000000));
			donHangList.add(new DonHang(37, "Thành Phố Thủ Đức", "Thường", 10, 1, 1000000));
			NSX nhaSanXuat = new NSX("Nhà sản xuất nước ngọt",new QuanLyDon(donHangList), new QuanLyXe(xeList) , danhSachTramGiao, map);
			new Controller(new Model(nhaSanXuat), new View());
		// test3
//		List<DonHang> donHangList = new ArrayList<>();
//		donHangList.add(new DonHang(1, "Trạm 10", "Thường", 10, 1, 100000));
//		donHangList.add(new DonHang(2, "Trạm 10", "Hỏa tốc", 30, 3, 500000));
//		donHangList.add(new DonHang(3, "Trạm 10", "Giá trị", 10, 2, 300000));
//		donHangList.add(new DonHang(4, "Trạm 3", "Thường", 20, 1, 120000));
//		donHangList.add(new DonHang(5, "Trạm 3", "Hỏa tốc", 20, 3, 450000));
//		donHangList.add(new DonHang(6, "Trạm 4", "Giá trị", 20, 2, 320000));
//		QuanLyDon quanLyDon = new QuanLyDon(donHangList);
//		List<Xe> xeList = new ArrayList<>();
//		double sucChuaToiDa = 200.0; // Đơn vị: kg
//		int soDonToiDa = 5; // Số đơn tối đa mỗi xe có thể chở
//		// Tạo danh sách 20 xe
//		for (int i = 1; i <= 20; i++) {
//			int bienSo = 1000 + i; // Giả định biển số tăng dần từ 1001
//			xeList.add(new Xe(i, bienSo, sucChuaToiDa, soDonToiDa));
//		}
//		QuanLyXe quanLyXe = new QuanLyXe(xeList);
//		List<TramGiao> danhSachTram = new ArrayList<>();
//		TramGiao tram1 = new TramGiao(1, "Nhà sản xuất hàng hóa", 0, new ToaDo(1, 1));
//		TramGiao tram2 = new TramGiao(2, "Trạm 2", 0, new ToaDo(4, 8));
//		TramGiao tram3 = new TramGiao(3, "Trạm 3", 0, new ToaDo(1, 4));
//		TramGiao tram4 = new TramGiao(4, "Trạm 4", 0, new ToaDo(1, 8));
//		TramGiao tram5 = new TramGiao(5, "Trạm 5", 0, new ToaDo(2, 5));
//		TramGiao tram6 = new TramGiao(6, "Trạm 6", 0, new ToaDo(2, 9));
//		TramGiao tram7 = new TramGiao(7, "Trạm 7", 0, new ToaDo(3, 7));
//		TramGiao tram8 = new TramGiao(8, "Trạm 8", 0, new ToaDo(5, 9));
//		TramGiao tram9 = new TramGiao(9, "Trạm 9", 0, new ToaDo(10, 10));
//		TramGiao tram10 = new TramGiao(10, "Trạm 10", 0, new ToaDo(3, 1));
//		TramGiao tram11 = new TramGiao(11, "Trạm 11", 0, new ToaDo(5, 4));
//		TramGiao tram12 = new TramGiao(12, "Trạm 12", 0, new ToaDo(5, 7));
//		TramGiao tram13 = new TramGiao(13, "Trạm 13", 0, new ToaDo(9, 2));
//		danhSachTram.add(tram1);
//		danhSachTram.add(tram2);
//		danhSachTram.add(tram3);
//		danhSachTram.add(tram4);
//		danhSachTram.add(tram5);
//		danhSachTram.add(tram6);
//		danhSachTram.add(tram7);
//		danhSachTram.add(tram8);
//		danhSachTram.add(tram9);
//		danhSachTram.add(tram10);
//		danhSachTram.add(tram11);
//		danhSachTram.add(tram12);
//		danhSachTram.add(tram13);
//		List<KhoangCachCacTram> khoangCachList = new ArrayList<>();
//		// Khoảng cách giữa các trạm
//		khoangCachList.add(new KhoangCachCacTram(tram1, tram2, 10.0, 300.0));
//		khoangCachList.add(new KhoangCachCacTram(tram2, tram1, 10.0, 300.0));
//		khoangCachList.add(new KhoangCachCacTram(tram6, tram1, 11.0, 500.0));
//
//		khoangCachList.add(new KhoangCachCacTram(tram1, tram7, 15, 700.0));
//		khoangCachList.add(new KhoangCachCacTram(tram7, tram1, 15, 850.0));
//		khoangCachList.add(new KhoangCachCacTram(tram2, tram3, 20, 300.0));
//		khoangCachList.add(new KhoangCachCacTram(tram3, tram1, 35, 850.0));
//		khoangCachList.add(new KhoangCachCacTram(tram2, tram4, 30, 850.0));
//		khoangCachList.add(new KhoangCachCacTram(tram4, tram5, 20, 850.0));
//		khoangCachList.add(new KhoangCachCacTram(tram5, tram2, 30, 850.0));
//		khoangCachList.add(new KhoangCachCacTram(tram5, tram2, 30, 850.0));
//		khoangCachList.add(new KhoangCachCacTram(tram7, tram8, 30, 850.0));
//		khoangCachList.add(new KhoangCachCacTram(tram8, tram5, 30, 850.0));
//
//		khoangCachList.add(new KhoangCachCacTram(tram7, tram9, 45.0, 800.0));
//		khoangCachList.add(new KhoangCachCacTram(tram9, tram13, 45.0, 800.0));
//		khoangCachList.add(new KhoangCachCacTram(tram13, tram7, 45.0, 800.0));
//		khoangCachList.add(new KhoangCachCacTram(tram9, tram10, 11.0, 500.0));
//		khoangCachList.add(new KhoangCachCacTram(tram10, tram11, 11.0, 500.0));
//		khoangCachList.add(new KhoangCachCacTram(tram10, tram12, 11.0, 500.0));
//		khoangCachList.add(new KhoangCachCacTram(tram10, tram12, 11.0, 500.0));
//		khoangCachList.add(new KhoangCachCacTram(tram10, tram12, 11.0, 500.0));
//		khoangCachList.add(new KhoangCachCacTram(tram12, tram6, 50.0, 500.0));
//		khoangCachList.add(new KhoangCachCacTram(tram6, tram12, 50.0, 500.0));
////		khoangCachList.add(new KhoangCachCacTram(tram11, tram3, 70.0, 500.0));
////		khoangCachList.add(new KhoangCachCacTram(tram11, tram7, 50.0, 500.0));
//		NSX nsx = new NSX("Nhà sản xuất hàng hóa", quanLyDon, quanLyXe, danhSachTram, khoangCachList);
////run
//		new Controller(new Model(nsx), new View());
//
	}
}
