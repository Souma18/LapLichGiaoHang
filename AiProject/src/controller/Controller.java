package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Model;
import view.View;
import data.CumGiao;
import data.DonHang;
import data.KhoangCachCacTram;
import data.NSX;
import data.QuanLyDon;
import data.QuanLyXe;
import data.TramGiao;
import data.TuyenDuongDuocTaoRa;
import data.Xe;

public class Controller {
	private Model models;
	private View views;

	public Controller(Model models, View views) {
		this.models = models;
		this.views = views;

		// Khi khởi tạo Controller, tự động hiển thị thông tin lên panel trái
		capNhatPanelTrai();

		// Gắn sự kiện cho nút "Sắp xếp tuyến đường" để cập nhật panel phải
		this.views.getSortButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				capNhatPanelPhai();
			}
		});
	}

	// Cập nhật panel trái với thông tin từ NSX
	private void capNhatPanelTrai() {
		NSX CurrNSX = models.getNSX(); // Lấy thông tin nhà sản xuất từ model
		StringBuilder data = new StringBuilder();
		if (CurrNSX != null) {
			data.append("Tên NSX: ").append(CurrNSX.getTenNSX()).append("\n");
			data.append("Tổng số trạm cần giao: ").append(CurrNSX.getDsTram().size()).append("\n");
			data.append("Tổng số lượng đơn: ").append(CurrNSX.SoLuongDon()).append("\n");
			data.append("Tổng khối lượng đơn: ").append(CurrNSX.khoiLuongDon()).append(" kg\n");
			data.append("Tổng số lượng xe: ").append(CurrNSX.tongSoLuongXe()).append(" Xe\n");
			data.append("------------------------------------\n");
			// Thêm danh sách các trạm giao
			data.append("Danh sách các trạm giao:\n");
			CurrNSX.getDsTramMinus().forEach(tram -> {
				data.append("- ").append(tram.getTenTram()).append(": ").append(tram.getSLDon()).append(" Đơn\n");
			});
			data.append("------------------------------------\n");
			// danh sách cụm được phân
			models.getListCumGiao().forEach(cum -> {
				data.append("- ").append("Cụm ").append(cum.getId()).append(": ");
				cum.getListTram().forEach(tram -> {
					data.append(tram.getTenTram()).append(", ");
				});
				// Xóa dấu phẩy thừa ở cuối
				if (!cum.getListTram().isEmpty()) {
					data.setLength(data.length() - 2); // Xóa 2 ký tự cuối (", ")
				}
				data.append("\n"); // Xuống dòng sau mỗi cụm
			});
			views.updateLeftPanel(data.toString());
		}
//        test
		else {
			data.append("Tên NSX: ").append("\n");
			data.append("Tổng số trạm: ").append("\n");
			data.append("Tổng số lượng đơn: ").append("\n");
			data.append("Tổng khối lượng đơn: ").append("\n");
			data.append("Tổng tải trọng xe: ").append("\n");
			data.append("Tổng số đơn xe xử lý: ").append("\n");
			// Cập nhật thông tin lên panel trái trong View
			views.updateLeftPanel(data.toString());
		}
		themSuKienChoBang();
	}

	// Cập nhật panel phải (hiển thị tuyến đường sau khi sắp xếp)
	private void capNhatPanelPhai() {
		// Xóa dữ liệu cũ trong bảng
		views.clearRightPanel();

		// Lấy danh sách cụm giao từ model
		Map<CumGiao, List<TuyenDuongDuocTaoRa>> map = models.tuyenduong();
		List<CumGiao> cumGiaoList = models.getListCumGiao();
		for (Map.Entry<CumGiao, List<TuyenDuongDuocTaoRa>> entry : map.entrySet()) {
			StringBuffer buiBuffer = new StringBuffer();
			entry.getValue().get(0).getTuyenDuong().forEach(tram -> {
				buiBuffer.append(tram.getTenTram()).append(", ");
			});
			views.addRowToRightPanel("Cum " + entry.getKey().getId(), "xe " + entry.getValue().get(0).getXe().getId(), 0,
					buiBuffer);
//			String cum, String xeId, int soLuongHang, String tuyenDuong
		}
	}
	// Lắng nghe sự kiện khi click vào bảng
	private void themSuKienChoBang() {
	    views.getRightPanelTable().addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent e) {
	            int row = views.getRightPanelTable().getSelectedRow(); // Lấy dòng được chọn
	            if (row != -1) {
	            	  String tuyenDuong = views.getTableModel().getValueAt(row, 3).toString(); // Chuyển StringBuffer thành String
	                hienThiChiTietTuyenDuong(tuyenDuong);
	            }
	        }
	    });
	}

	// Hàm hiển thị chi tiết tuyến đường trong cửa sổ nhỏ
	private void hienThiChiTietTuyenDuong(String tuyenDuong) {
	    JDialog dialog = new JDialog(views, "Chi tiết tuyến đường", true);
	    dialog.setSize(400, 300);
	    dialog.setLayout(new BorderLayout());
	    dialog.add(new JLabel("Chi tiết tuyến đường"), BorderLayout.NORTH);

	    JTextArea textArea = new JTextArea(tuyenDuong);
	    textArea.setEditable(false);
	    dialog.add(new JScrollPane(textArea), BorderLayout.CENTER);

	    dialog.setLocationRelativeTo(views);
	    dialog.setVisible(true);
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
		khoangCachList.add(new KhoangCachCacTram(tramA, tramNSX, 10.0, 300.0));
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramB, 11.0, 500.0));
		khoangCachList.add(new KhoangCachCacTram(tramB, tramNSX, 11.0, 500.0));
		khoangCachList.add(new KhoangCachCacTram(tramA, tramB, 10, 700.0));
		khoangCachList.add(new KhoangCachCacTram(tramA, tramB, 9, 850.0));
		khoangCachList.add(new KhoangCachCacTram(tramB, tramA, 9, 850.0));
		khoangCachList.add(new KhoangCachCacTram(tramC, tramB, 9, 850.0));
		khoangCachList.add(new KhoangCachCacTram(tramB, tramC, 9, 850.0));

		NSX nsx = new NSX("Nhà sản xuất hàng hóa", quanLyDon, quanLyXe, danhSachTram, khoangCachList);
//run
		new Controller(new Model(nsx), new View());
	}
}
