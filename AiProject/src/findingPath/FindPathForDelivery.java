package findingPath;

import java.util.LinkedList;
import java.util.List;

import data.CumGiao;
import data.KhoangCachCacTram;
import data.TramGiao;

public class FindPathForDelivery {
	// hàm execute
	public List<List<TramGiao>> getTuyenDuong(CumGiao cluster, List<KhoangCachCacTram> listKhoangCach) {

		return null;
	}

	// find path
	
	//checklist similar
	public static boolean checkListPath(List<List<TramGiao>>listPath,List<TramGiao>listNeedCheck) {
		for (List li : listPath) {
			if(listPath.isEmpty())return false;
			if((checkSimilar(li, listNeedCheck))) {
				return true;
			}
		}
		return false;
	}
	// Hàm phụ trợ để kiểm tra hai danh sách có giống nhau hoàn toàn không
	private static boolean checkSimilar(List<TramGiao> list1, List<TramGiao> list2) {
		if (list1.size() != list2.size()) {
			return false; 
		}
		int index=0;
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i).equals(list2.get(i))) {
				index++;
			}
		}
		if((index==list1.size()))return true;

		return false;
	}
	// tạo node 
		public List<Nodee> createNodee(CumGiao cum, BanDo banDo) {
			List<Nodee> listNode = new LinkedList<>();

			for (TramGiao t : cum.getListTram()) {
				Nodee n = new Nodee(t);
				n.setTram(t);
				n.setChiPhi(t.getChiPhi());
				listNode.add(n);
			}
			return listNode;
		}

}
