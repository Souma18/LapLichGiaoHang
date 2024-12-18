package findingPath;

import java.util.ArrayList;
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

	public PhanPhoi(List<Xe> listXe) {
		super();
		this.listXe = listXe;
		this.listTuyenDuongDuocTaoRa= new ArrayList<TuyenDuongDuocTaoRa>();
		
	}
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
				System.out.println("Xe: " + tuyenDuongDuocTaoRa.getXe().getId() + " ,tram: " + tentram);
			}
		}
	}


}
