package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {
	private JTextArea leftPanelTextArea;
	private JTable rightPanelTable;
	private DefaultTableModel tableModel;
	private JButton sortButton;

	public View() {
		// Thiết lập JFrame
		setTitle("Tuyến Đường Giao Hàng");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 2));

		// Tạo panel bên trái
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBackground(Color.green);
		leftPanelTextArea = new JTextArea();
		leftPanelTextArea.setEditable(false);
		JScrollPane leftScroll = new JScrollPane(leftPanelTextArea);
		leftPanel.add(new JLabel("Thông tin giao hàng"), BorderLayout.NORTH);
		leftPanel.add(leftScroll, BorderLayout.CENTER);

		// Thêm nút vào panel bên trái
		sortButton = new JButton("Sắp xếp tuyến đường");
		leftPanel.add(sortButton, BorderLayout.SOUTH);

		// Tạo panel bên phải
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBackground(Color.green);
		// Cấu trúc cột của bảng
		String[] columnNames = { "Cụm", "Xe", "Số lượng hàng", "Tuyến đường", "Giá trị" };
		tableModel = new DefaultTableModel(columnNames, 0);
		rightPanelTable = new JTable(tableModel);
		JScrollPane rightScroll = new JScrollPane(rightPanelTable);

		rightPanel.add(new JLabel("Thông tin tuyến đường"), BorderLayout.NORTH);
		rightPanel.add(rightScroll, BorderLayout.CENTER);

		// Thêm các panel vào JFrame
		add(leftPanel);
		add(rightPanel);

		setVisible(true);
	}

	// Hiển thị thông tin tổng quan
	public void updateLeftPanel(String data) {
		leftPanelTextArea.setText(data);
	}

	// Thêm một dòng dữ liệu vào bảng bên phải
	public void addRowToRightPanel(String cum, String xeId, int soLuongHang, StringBuffer buiBuffer, double giaTriCum) {
		tableModel.addRow(new Object[] { cum, xeId, soLuongHang, buiBuffer.toString(), giaTriCum });
	}

	// Xóa tất cả dữ liệu trong bảng bên phải
	public void clearRightPanel() {
		tableModel.setRowCount(0);
	}

	// Trả về nút để thêm action listener
	public JButton getSortButton() {
		return sortButton;
	}

	// Trả về DefaultTableModel của bảng
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	// Trả về bảng để thêm MouseListener
	public JTable getRightPanelTable() {
		return rightPanelTable;
	}
}
