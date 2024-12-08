package findingPath;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import data.CumGiao;
import data.KhoangCachCacTram;
import data.TramGiao;
import data.TuyenDuongDuocTaoRa;
import data.Xe;

public class PhanPhoi {
	private List<Xe> listXe;
	private List<TuyenDuongDuocTaoRa> listTuyenDuongDuocTaoRa;

	public List<Xe> getListXe() {
		return listXe;
	}

	public void setListXe(List<Xe> listXe) {
		this.listXe = listXe;
	}

	public List<TuyenDuongDuocTaoRa> getListTuyenDuongDuocTaoRa() {
		return listTuyenDuongDuocTaoRa;
	}

	public void setListTuyenDuongDuocTaoRa(List<TuyenDuongDuocTaoRa> listTuyenDuongDuocTaoRa) {
		this.listTuyenDuongDuocTaoRa = listTuyenDuongDuocTaoRa;
	}

	public void coordinatedAction(TuyenDuong t) {
		List<TuyenDuongDuocTaoRa> listTuyenDuong = new LinkedList<>();
		Queue<List<TramGiao>> q = new LinkedList<>();
		for (List<TramGiao> l : t.convertPath()) {
			q.offer(l);
		}
		if (!listXe.isEmpty()) {
			for (Xe x : listXe) {
				List<TramGiao> li = new LinkedList<>();
				li.addAll(q.poll());
				q.offer(li);
				TuyenDuongDuocTaoRa tuyenduong = new TuyenDuongDuocTaoRa(x, li);
				listTuyenDuong.add(tuyenduong);
			}
		}
		if (!listTuyenDuong.isEmpty()) {
			this.setListTuyenDuongDuocTaoRa(listTuyenDuong);
		}else System.out.println("fail");
	}

	public void printListTuyenDuongDuocTaoRa() {
		if (listTuyenDuongDuocTaoRa != null) {
			for (TuyenDuongDuocTaoRa tuyenDuongDuocTaoRa : listTuyenDuongDuocTaoRa) {
				String tentram = "";
				for (TramGiao t : tuyenDuongDuocTaoRa.getTuyenDuong()) {
					tentram += t.getTenTram() + " ";
				}
				System.out.println("Xe: " + tuyenDuongDuocTaoRa.getXe().getId() + " ,tuyen: " + tentram);
			}
		}
	}

	public static void main(String[] args) {
		TramGiao t1 = new TramGiao(1, "1", 0, null, null);
		TramGiao t2 = new TramGiao(2, "2", 0, null, null);
		TramGiao t3 = new TramGiao(3, "3", 0, null, null);
		TramGiao t4 = new TramGiao(4, "4", 0, null, null);
		TramGiao t5 = new TramGiao(5, "5", 0, null, null);

		KhoangCachCacTram k0 = new KhoangCachCacTram(t1, t4, 10, 0);
		KhoangCachCacTram k1 = new KhoangCachCacTram(t1, t2, 50, 0);
		KhoangCachCacTram k2 = new KhoangCachCacTram(t1, t3, 45, 0);
		KhoangCachCacTram k22 = new KhoangCachCacTram(t3, t1, 15, 0);

		KhoangCachCacTram k3 = new KhoangCachCacTram(t2, t3, 10, 0);
		KhoangCachCacTram k4 = new KhoangCachCacTram(t2, t4, 15, 0);

		KhoangCachCacTram k11 = new KhoangCachCacTram(t3, t5, 30, 0);

		KhoangCachCacTram k6 = new KhoangCachCacTram(t4, t5, 15, 0);
		KhoangCachCacTram k7 = new KhoangCachCacTram(t4, t1, 10, 0);

		KhoangCachCacTram k5 = new KhoangCachCacTram(t5, t2, 20, 0);
		KhoangCachCacTram k9 = new KhoangCachCacTram(t5, t3, 35, 0);

		List<KhoangCachCacTram> listKC = new LinkedList<>();
		listKC.add(k0);
		listKC.add(k1);
		listKC.add(k2);
		listKC.add(k3);
		listKC.add(k4);
		listKC.add(k5);
		listKC.add(k6);
		listKC.add(k7);
		listKC.add(k9);
		listKC.add(k11);
		TuyenDuong t = new TuyenDuong();
		t.createNodee(new BanDo(listKC));

		CumGiao c = new CumGiao(1);
		c.addTramGiao(t2);
		c.addTramGiao(t5);
		c.addTramGiao(t3);
		BanDo bd = new BanDo(listKC);
		t.setBanDo(bd);
		t.addTuyenDuong(c);
		PhanPhoi p = new PhanPhoi();
		Xe x1 = new Xe(0, 0, 0, 0, 0);
		Xe x2 = new Xe(1, 0, 0, 0, 0);
		Xe x3 = new Xe(2, 0, 0, 0, 0);
		Xe x4 = new Xe(3, 0, 0, 0, 0);
		List<Xe> listXe = new LinkedList<>();
		listXe.add(x1);
		listXe.add(x2);
		listXe.add(x3);
		listXe.add(x4);
		p.setListXe(listXe);
		p.coordinatedAction(t);
		p.printListTuyenDuongDuocTaoRa();
	}

}
