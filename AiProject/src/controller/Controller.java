package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import data.CumGiao;
import data.DonHang;
import data.KhoangCachCacTram;
import data.NSX;
import data.QuanLyDon;
import data.QuanLyXe;
import data.ToaDo;
import data.TramGiao;
import data.TuyenDuongDuocTaoRa;
import data.Xe;
import model.Model;
import view.View;

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
					data.setLength(data.length() - 2);
					// Xóa 2 ký tự cuối (", ")
				}
				data.append("\n");
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
			List<TramGiao> tuyenDuong = entry.getValue().get(0).getTuyenDuong();
			Xe xe = entry.getValue().get(0).getXe();
			StringBuffer buiBuffer = new StringBuffer();
			tuyenDuong.forEach(tram -> {
				buiBuffer.append(tram.getTenTram()).append(", ");
			});
			views.addRowToRightPanel("Cum " + entry.getKey().getId(), "xe " + xe.getId(), xe.getSoLuongDon(), buiBuffer,
					entry.getKey().getGiaTriCum());

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
					String tuyenDuong = views.getTableModel().getValueAt(row, 3).toString(); // Chuyển StringBuffer
																								// thành String
					hienThiChiTietTuyenDuong2(tuyenDuong);
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

	private void hienThiChiTietTuyenDuong2(String tuyenDuong) {
		JDialog dialog = new JDialog(views, "Chi tiết tuyến đường", true);
		dialog.setSize(1000, 600);
		dialog.setLayout(new BorderLayout());

		dialog.add(new JLabel("Chi tiết tuyến đường"), BorderLayout.NORTH);

		// hiển thị chi tiết tuyến đường
		JTextArea textArea = new JTextArea(tuyenDuong);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane textScrollPane = new JScrollPane(textArea);
		textScrollPane.setPreferredSize(new java.awt.Dimension(250, 600));
		dialog.add(textScrollPane, BorderLayout.WEST);

		// JPanel để vẽ biểu đồ
		JPanel chartPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);

				// Parse dữ liệu tuyến đường và vẽ các trạm
				String[] tramNames = tuyenDuong.split(", ");
				ToaDo prevPoint = null;

				for (String tramName : tramNames) {
					TramGiao tram = models.timTram(tramName.trim()); // Hàm tìm trạm theo tên
					if (tram != null) {
						ToaDo toado = tram.getToaDo();
						// Tọa độ được phóng to để dễ thấy trên giao diện
						int scaledX = toado.getX() * 50; // Scale tọa độ
						int scaledY = toado.getY() * 50;
						g.fillOval(scaledX - 5, scaledY - 5, 10, 10); // Vẽ điểm

						// Vẽ tên trạm
						g.drawString(tram.getTenTram(), scaledX + 10, scaledY); // Vẽ tên trạm bên cạnh điểm

						if (prevPoint != null) {
							int prevScaledX = prevPoint.getX() * 50;
							int prevScaledY = prevPoint.getY() * 50;
							g.drawLine(prevScaledX, prevScaledY, scaledX, scaledY); // Vẽ đường nối
						}
						prevPoint = toado;
					}
				}
			}
		};

		chartPanel.setPreferredSize(new java.awt.Dimension(600, 600)); // Đặt kích thước cố định
		JScrollPane chartScrollPane = new JScrollPane(chartPanel);
		dialog.add(chartScrollPane, BorderLayout.CENTER);

		dialog.setLocationRelativeTo(views);
		dialog.setVisible(true);
	}

	public static void main(String[] args) {
		

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
		//test 4
		
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
		TramGiao batDau = new TramGiao(1111, "Nhà sản xuất nước ngọt", 0, new ToaDo(3, 3));
		TramGiao tram1 = new TramGiao(1 ,"Quận 1", 100000, new ToaDo(2, 5));
		TramGiao tram2 = new TramGiao(2, "Quận 3", 100000, new ToaDo(2, 5));
		TramGiao tram3 = new TramGiao(3, "Quận 5", 100000, new ToaDo(2, 5));
		TramGiao tram4 = new TramGiao(4, "Quận 6", 100000, new ToaDo(6, 4));
		TramGiao tram5 = new TramGiao(5, "Quận 7", 100000, new ToaDo(3, 6));
		TramGiao tram6 = new TramGiao(6, "Quận 10", 100000, new ToaDo(3, 13));
		TramGiao tram7 = new TramGiao(7, "Quận 12", 100000, new ToaDo(4, 18));
		TramGiao tram8 = new TramGiao(8, "Thành Phố Thủ Đức", 100000, new ToaDo(8,19));
		TramGiao tram9 = new TramGiao(9, "Quận Tân Bình", 100000, new ToaDo(11, 13));
		TramGiao tram10 = new TramGiao(10, "Quận Gò Vấp", 100000, new ToaDo(3, 13));
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
		List<DonHang> donHangList = new ArrayList<>();
		donHangList.add(new DonHang(1, "Quận 10", "Thường", 10, 1, 100000));
		
		QuanLyDon quanLyDon = new QuanLyDon(donHangList);
		NSX nsx = new NSX("Nhà sản xuất hàng hóa", quanLyDon, new QuanLyXe(xeList), danhSachTramGiao, map);
	//run
			new Controller(new Model(nsx), new View());
	}
}
