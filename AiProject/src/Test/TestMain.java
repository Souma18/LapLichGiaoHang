package Test;

import java.util.ArrayList;
import java.util.List;

import data.CumGiao;
import data.DonHang;
import data.KhoangCachCacTram;
import data.NSX;
import data.QuanLyDon;
import data.QuanLyXe;
import data.ToaDo;
import data.TramGiao;
import data.Xe;
import model.ModelCumGiao;
import model.XuLy;

public class TestMain {
	public static void main(String[] args) throws Exception {
		List<DonHang> donHangList = new ArrayList<>();
//	
		donHangList.add(new DonHang(1, "Trạm A", "Thường", 10.5, 1, 100000));
		donHangList.add(new DonHang(2, "Trạm B", "Hỏa tốc", 5.2, 3, 500000));
		donHangList.add(new DonHang(3, "Trạm C", "Giá trị", 8.0, 2, 300000));
		donHangList.add(new DonHang(4, "Trạm D", "Thường", 12.3, 1, 120000));
		donHangList.add(new DonHang(5, "Trạm E", "Hỏa tốc", 7.1, 3, 450000));
		donHangList.add(new DonHang(6, "Trạm F", "Giá trị", 9.5, 2, 320000));
		donHangList.add(new DonHang(7, "Trạm A", "Hỏa tốc", 6.3, 3, 470000));
		donHangList.add(new DonHang(8, "Trạm B", "Giá trị", 8.4, 2, 350000));
		donHangList.add(new DonHang(9, "Trạm C", "Thường", 14.0, 1, 140000));
		donHangList.add(new DonHang(10, "Trạm D", "Hỏa tốc", 5.8, 3, 520000));
		donHangList.add(new DonHang(11, "Trạm E", "Giá trị", 7.6, 2, 310000));
		donHangList.add(new DonHang(12, "Trạm F", "Thường", 11.5, 1, 150000));
		donHangList.add(new DonHang(13, "Trạm A", "Thường", 9.0, 1, 90000));
		donHangList.add(new DonHang(14, "Trạm B", "Hỏa tốc", 4.9, 3, 490000));
		donHangList.add(new DonHang(15, "Trạm C", "Giá trị", 6.3, 2, 260000));
		donHangList.add(new DonHang(16, "Trạm D", "Thường", 15.0, 1, 150000));
		donHangList.add(new DonHang(17, "Trạm E", "Hỏa tốc", 6.7, 3, 470000));
		donHangList.add(new DonHang(18, "Trạm F", "Giá trị", 10.3, 2, 330000));
		donHangList.add(new DonHang(19, "Trạm A", "Giá trị", 7.8, 2, 280000));
		donHangList.add(new DonHang(20, "Trạm B", "Thường", 13.0, 1, 130000));
		donHangList.add(new DonHang(21, "Trạm C", "Hỏa tốc", 4.5, 3, 450000));
		donHangList.add(new DonHang(22, "Trạm D", "Giá trị", 8.1, 2, 310000));
		donHangList.add(new DonHang(23, "Trạm E", "Thường", 14.5, 1, 145000));
		donHangList.add(new DonHang(24, "Trạm F", "Hỏa tốc", 5.3, 3, 500000));
		donHangList.add(new DonHang(25, "Trạm A", "Thường", 9.5, 1, 95000));
		donHangList.add(new DonHang(26, "Trạm B", "Giá trị", 7.4, 2, 270000));
		donHangList.add(new DonHang(27, "Trạm C", "Hỏa tốc", 6.1, 3, 470000));
		donHangList.add(new DonHang(28, "Trạm D", "Giá trị", 11.2, 2, 310000));
		donHangList.add(new DonHang(29, "Trạm E", "Thường", 10.0, 1, 100000));
		donHangList.add(new DonHang(30, "Trạm F", "Hỏa tốc", 5.5, 3, 550000));
		donHangList.add(new DonHang(31, "Trạm A", "Hỏa tốc", 6.8, 3, 480000));
		donHangList.add(new DonHang(32, "Trạm B", "Giá trị", 7.2, 2, 270000));
		donHangList.add(new DonHang(33, "Trạm C", "Thường", 12.0, 1, 120000));
		donHangList.add(new DonHang(34, "Trạm D", "Hỏa tốc", 5.7, 3, 570000));
		donHangList.add(new DonHang(35, "Trạm E", "Giá trị", 8.0, 2, 310000));
		donHangList.add(new DonHang(36, "Trạm F", "Thường", 14.0, 1, 140000));
		donHangList.add(new DonHang(37, "Trạm A", "Thường", 10.0, 1, 100000));
		donHangList.add(new DonHang(38, "Trạm B", "Giá trị", 6.9, 2, 290000));
		donHangList.add(new DonHang(39, "Trạm C", "Hỏa tốc", 5.0, 3, 500000));
		donHangList.add(new DonHang(40, "Trạm D", "Giá trị", 7.5, 2, 310000));
		donHangList.add(new DonHang(41, "Trạm E", "Thường", 11.8, 1, 118000));
		donHangList.add(new DonHang(42, "Trạm F", "Hỏa tốc", 4.8, 3, 480000));
		donHangList.add(new DonHang(43, "Trạm A", "Giá trị", 9.7, 2, 330000));
		donHangList.add(new DonHang(44, "Trạm B", "Thường", 10.5, 1, 105000));
		donHangList.add(new DonHang(45, "Trạm C", "Hỏa tốc", 4.6, 3, 460000));
		donHangList.add(new DonHang(46, "Trạm D", "Giá trị", 7.9, 2, 310000));
		donHangList.add(new DonHang(47, "Trạm E", "Thường", 13.2, 1, 132000));
		donHangList.add(new DonHang(48, "Trạm F", "Hỏa tốc", 6.2, 3, 620000));
		donHangList.add(new DonHang(49, "Trạm A", "Thường", 11.5, 1, 115000));
		donHangList.add(new DonHang(50, "Trạm B", "Giá trị", 7.3, 2, 320000));
//		
		QuanLyDon quanLyDon = new QuanLyDon(donHangList);
		List<Xe> xeList = new ArrayList<>();
		double sucChuaToiDa = 100.0; // Đơn vị: kg
		int soDonToiDa = 10; // Số đơn tối đa mỗi xe có thể chở
		// Tạo danh sách 20 xe
		for (int i = 1; i <= 20; i++) {
			int bienSo = 1000 + i; // Giả định biển số tăng dần từ 1001
			xeList.add(new Xe(i, bienSo, sucChuaToiDa, soDonToiDa));
		}
//		
		QuanLyXe quanLyXe = new QuanLyXe(xeList);
		List<TramGiao> danhSachTram = new ArrayList<>();

		// Tọa độ trạm từ A đến F trên ma trận 20x20
		ToaDo toaDoA = new ToaDo(2, 3);
		ToaDo toaDoB = new ToaDo(5, 10);
		ToaDo toaDoC = new ToaDo(8, 15);
		ToaDo toaDoD = new ToaDo(12, 7);
		ToaDo toaDoE = new ToaDo(15, 3);
		ToaDo toaDoF = new ToaDo(18, 18);
		ToaDo toaDoNSX = new ToaDo(10, 10);
		// Tạo trạm giao với tên giống trong đơn hàng
		TramGiao tramNSX = new TramGiao(111, "Trạm NSX", 0, toaDoNSX);
		TramGiao tramA = new TramGiao(1, "Trạm A", 1000.0, toaDoA);
		TramGiao tramB = new TramGiao(2, "Trạm B", 1200.0, toaDoB);
		TramGiao tramC = new TramGiao(3, "Trạm C", 1500.0, toaDoC);
		TramGiao tramD = new TramGiao(4, "Trạm D", 1700.0, toaDoD);
		TramGiao tramE = new TramGiao(5, "Trạm E", 2000.0, toaDoE);
		TramGiao tramF = new TramGiao(6, "Trạm F", 2500.0, toaDoF);
		danhSachTram.add(tramA);
		danhSachTram.add(tramB);
		danhSachTram.add(tramC);
		danhSachTram.add(tramD);
		danhSachTram.add(tramE);
		danhSachTram.add(tramF);
		danhSachTram.add(tramNSX);
//        
		List<KhoangCachCacTram> khoangCachList = new ArrayList<>();
		// Khoảng cách giữa các trạm
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramA, 3.0, 300.0));
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramB, 5.0, 500.0));
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramC, 7.0, 700.0));
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramD, 8.5, 850.0));
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramE, 6.5, 650.0));
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramF, 10.0, 1000.0));

		// Các tuyến gần nhau
		khoangCachList.add(new KhoangCachCacTram(tramA, tramB, 2.0, 200.0));
		khoangCachList.add(new KhoangCachCacTram(tramA, tramC, 3.5, 350.0));
		khoangCachList.add(new KhoangCachCacTram(tramA, tramD, 4.0, 400.0));
		khoangCachList.add(new KhoangCachCacTram(tramB, tramC, 2.5, 250.0));
		khoangCachList.add(new KhoangCachCacTram(tramB, tramE, 5.5, 550.0));
		khoangCachList.add(new KhoangCachCacTram(tramC, tramD, 3.0, 300.0));
		khoangCachList.add(new KhoangCachCacTram(tramC, tramF, 4.5, 450.0));
		khoangCachList.add(new KhoangCachCacTram(tramD, tramE, 4.0, 400.0));
		khoangCachList.add(new KhoangCachCacTram(tramD, tramF, 6.5, 650.0));
		khoangCachList.add(new KhoangCachCacTram(tramE, tramF, 5.0, 500.0));

		// Tuyến dài hơn
		khoangCachList.add(new KhoangCachCacTram(tramA, tramF, 15.0, 1500.0));
		khoangCachList.add(new KhoangCachCacTram(tramB, tramD, 10.0, 1000.0));
		khoangCachList.add(new KhoangCachCacTram(tramC, tramA, 9.0, 900.0));
		khoangCachList.add(new KhoangCachCacTram(tramB, tramC, 6.5, 650.0));
		khoangCachList.add(new KhoangCachCacTram(tramE, tramA, 8.0, 800.0));
		khoangCachList.add(new KhoangCachCacTram(tramF, tramA, 13.0, 1300.0));
		khoangCachList.add(new KhoangCachCacTram(tramF, tramB, 14.0, 1400.0));
		khoangCachList.add(new KhoangCachCacTram(tramE, tramD, 9.0, 900.0));
		khoangCachList.add(new KhoangCachCacTram(tramC, tramE, 6.0, 600.0));
		khoangCachList.add(new KhoangCachCacTram(tramD, tramB, 7.5, 750.0));

		// Các tuyến kết hợp
		khoangCachList.add(new KhoangCachCacTram(tramA, tramE, 7.0, 700.0));
		khoangCachList.add(new KhoangCachCacTram(tramC, tramF, 10.5, 1050.0));
		khoangCachList.add(new KhoangCachCacTram(tramE, tramB, 9.5, 950.0));
//        Nhà sản xuất
		NSX nhaSanXuat = new NSX("Trạm NSX", quanLyDon, quanLyXe, danhSachTram, khoangCachList);

		for (TramGiao tramGiao : nhaSanXuat.getDsTram()) {
			System.out.println(tramGiao.getTenTram());
			System.out.println(tramGiao.getSLDon());
		}
//        /////////////////////////////
		{
		ModelCumGiao modelCumGiao = new ModelCumGiao(nhaSanXuat);
		modelCumGiao.clusteringA();
		for (CumGiao cum : modelCumGiao.getListCumGiao()) {
			System.out.println(cum.getId());
			cum.getKhoiLuongDon();
		}
	}
	}
}
