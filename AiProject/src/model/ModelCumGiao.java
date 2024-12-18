package model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import data.CumGiao;
import data.DonHang;
import data.KhoangCachCacTram;
import data.NSX;
import data.QuanLyDon;
import data.QuanLyXe;
import data.TramGiao;
import data.Xe;

public class ModelCumGiao {
	private List<CumGiao> listCumGiao;
	private NSX nsx;

	public ModelCumGiao(NSX nSX) {
		nsx = nSX;
		this.listCumGiao = new ArrayList<CumGiao>();
		clusteringB();
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
		int xeTheoSL = (int) Math.ceil(cumGiao.getListTram().size() / soLuongDonXe);
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
			if (tramDaPhanCum == null || !tramDaPhanCum.contains(tramGanNhat))
				return tramGanNhat;
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

	// clustering
	public void clusteringA() {

		if (!kiemTraXeVaDonHang()) {
			System.out.println("Lỗi dữ liệu sai, không đủ xe hoặc đơn hàng");
			return;
		}

		// dữ liệu từ nsx
		List<Xe> dsXe = nsx.getQuanLyXe().getListXe();
		List<KhoangCachCacTram> danhSachKC = nsx.getKc();
		List<TramGiao> dsTram = nsx.getDsTram();

		TramGiao tramNSX = this.tramNSX();
		List<TramGiao> tramDaPhanCum = new LinkedList<TramGiao>();
		tramDaPhanCum.add(tramNSX);

		int idCum = 0;
		TramGiao tramTamThoi = null;
		// Duyệt qua từng xe
		for (Xe xe : dsXe) {
			System.out.println("xe " + xe.getBienSo() + "-------------");
//			nếu đã đánh dấu tất cả trạm
			if (tramDaPhanCum.size() == dsTram.size()) {
				System.out.println("thành công");
				break;
			}

//			tạo cụm
			CumGiao cum = new CumGiao(idCum++);

			// Nếu có trạm giao tạm thời, thêm vào cụm
			if (tramTamThoi != null) {
				System.out.println("thêm trạm tạm thời thành công");
				cum.addTramGiao(tramTamThoi);
			}

			cum.addTramGiao(tramNSX);
			cum.addXe(xe);
			listCumGiao.add(cum);

			// Tính toán khối lượng và số lượng đơn hàng còn lại trên xe
			double khoiLuong = xe.getSucChuaToiDa();
			int soLuong = xe.getSoDonToiDa();
			System.out.println("khối lượng bắt đầu" + khoiLuong);
			System.out.println("số lượng bắt đầu" + soLuong);

			// Duyệt qua các trạm giao
			for (TramGiao tramGiao : dsTram) {
				// Kiểm tra xem trạm này đã được phân vào cụm chưa
				if (!TramDaPhanCum(tramGiao, tramDaPhanCum)) {
					System.out.println("+++" + tramGiao.getTenTram());
					khoiLuong -= tramGiao.getKhoiLuongDon();
					soLuong -= tramGiao.getSLDon();
					System.out.println("khối lượng còn:" + khoiLuong);
					System.out.println("số lượng còn:" + soLuong);
//					
					TramGiao tramGanNhat = timTramGanNhat(tramGiao, danhSachKC, tramDaPhanCum);
//					nếu khối lượng xe vẫn còn

					if (khoiLuong >= 0 && soLuong >= 0) {
						System.out.println("--điều kiện 1--");
						cum.addTramGiao(tramGiao);
						tramDaPhanCum.add(tramGiao);
//							set lại dữ liệu
						tramTamThoi = null;
					}
//						nếu hàng thêm vào xe và còn thừa
					else if (khoiLuong < 0 || soLuong < 0) {
						System.out.println("--điều kiện 2--");
						cum.addTramGiao(tramGiao);
						tramTamThoi = tramGiao;
//							bỏ qua trạm này vì đã thêm vào 2 cụm
						tramDaPhanCum.add(tramGiao);
						break;
					}
				}
			}
		}
		for (TramGiao tramGiao : tramDaPhanCum) {
			System.out.println("cụm+" + tramGiao.getTenTram());
		}
//			setTuyenDuong();
	}

	public void clusteringB() {
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
    List<TramGiao> tramThuaHang = new LinkedList<>();

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
            // Bỏ qua trạm đã được phân cụm
            if (TramDaPhanCum(tramGiao, tramDaPhanCum)) {
                continue;
            }

            System.out.println("Đang xử lý trạm: " + tramGiao.getTenTram());

            // Kiểm tra tải trọng và số lượng đơn hàng
            boolean danhDauThuaHang = false;
            if (khoiLuong < tramGiao.getKhoiLuongDon() || soLuong < tramGiao.getSLDon()) {
                danhDauThuaHang = true;
                tramThuaHang.add(tramGiao);
                System.out.println("Trạm " + tramGiao.getTenTram() + " bị vượt tải, đã đánh dấu còn thừa hàng.");
            }

            cum.addTramGiao(tramGiao);
            tramDaPhanCum.add(tramGiao);

            // Cập nhật tải trọng và số lượng nếu không vượt tải
            if (!danhDauThuaHang) {
                khoiLuong -= tramGiao.getKhoiLuongDon();
                soLuong -= tramGiao.getSLDon();
                System.out.println("Khối lượng còn lại: " + khoiLuong + ", số lượng còn lại: " + soLuong);
            } else {
                // Nếu vượt tải, dừng vòng lặp để xử lý xe tiếp theo
                break;
            }
        }
    }
xuLyHangThua(tramThuaHang);
 
  
}

private void xuLyHangThua(List<TramGiao> tramThuaHang) {
		for (CumGiao cumGiao : listCumGiao) {
			
		}
		
	}

	//	public void setTuyenDuong() {
//		List<KhoangCachCacTram> kc = nsx.getKc();
//		for (CumGiao cumGiao : listCumGiao) {
//			cumGiao.setTuyenDuong(kc);
//		}
//	}
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
		khoangCachList.add(new KhoangCachCacTram(tramNSX, tramB, 11.0, 500.0));
		khoangCachList.add(new KhoangCachCacTram(tramA, tramB, 10, 700.0));
		khoangCachList.add(new KhoangCachCacTram(tramA, tramB, 9, 850.0));
		khoangCachList.add(new KhoangCachCacTram(tramC, tramB, 9, 850.0));

		NSX nsx = new NSX("Nhà sản xuất hàng hóa", quanLyDon, quanLyXe, danhSachTram, khoangCachList);
		ModelCumGiao md = new ModelCumGiao(nsx);
//		System.out.println(md.tramNSX().getTenTram());
//		System.out.print("số lượng xe cần thiết: ");
//		System.out.println(md.soLuongXeCanThiet());
//		System.out.print("trạm gần nhất của " + tramNSX.getTenTram() + " là: ");
//		List<TramGiao> A = new ArrayList<TramGiao>();
//		A.add(tramA);
//		System.out.println(md.timTramGanNhat(tramNSX, khoangCachList, A).getTenTram());
//		System.out.println("trạm đã phân cụm " + md.TramDaPhanCum(tramB, A));
		md.clusteringB();
		for (CumGiao cum : md.getListCumGiao()) {
			int sl = cum.getSoLuongDon();
			System.out.println("số lượng đơn cụm: " + cum.getId() + " là: " + sl);
			System.out.println("số lượng trạm: " + cum.getListTram().size());

		}
	}
}
