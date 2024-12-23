package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

}
