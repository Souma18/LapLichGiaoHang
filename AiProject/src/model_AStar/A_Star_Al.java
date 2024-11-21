package model_AStar;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import data.CumGiao;
import data.KhoangCachCacTram;
import data.TramGiao;
import data.TuyenDuong;

public class A_Star_Al {
	private TuyenDuong tuyenDuong = new TuyenDuong(null);
	private CumGiao cum;
	private BanDo banDo;

	public A_Star_Al(CumGiao cum, BanDo banDo) {
		this.cum = cum;
		this.banDo = banDo;
	}

	public CumGiao getCum() {
		return cum;
	}

	public void setCum(CumGiao cum) {
		this.cum = cum;
	}

	public BanDo getBanDo() {
		return banDo;
	}

	public void setBanDo(BanDo banDo) {
		this.banDo = banDo;
	}

	// tạo list ngắn nhất giữa 2 node
	public void shortestPath_2Node(BanDo banDo) {
		List<KhoangCachCacTram> result = new LinkedList<>();
		for (int i = 0; i < banDo.getListKhoangCach().size(); i++) {
			for (int j = 0; j < banDo.getListKhoangCach().size(); j++) {
				if ((banDo.getListKhoangCach().get(i).getTramXuatPhat()
						.equals(banDo.getListKhoangCach().get(j).getTramXuatPhat())
						&& banDo.getListKhoangCach().get(i).getTramDich()
								.equals(banDo.getListKhoangCach().get(j).getTramDich()))
						|| (banDo.getListKhoangCach().get(i).getTramXuatPhat()
								.equals(banDo.getListKhoangCach().get(j).getTramDich())
								&& banDo.getListKhoangCach().get(i).getTramDich()
										.equals(banDo.getListKhoangCach().get(j).getTramXuatPhat()))) {
					if (banDo.getListKhoangCach().get(i).getKhoangCach() < banDo.getListKhoangCach().get(j)
							.getKhoangCach()) {
						banDo.getListKhoangCach().remove(j);
					}
				}
			}
		}
	}

	// tạo node Star
	public List<NodeStar> createNodeStar(CumGiao cum, BanDo banDo) {
		List<NodeStar> listNode = new LinkedList<>();

		for (TramGiao t : cum.getCumGiao()) {
			NodeStar n = new NodeStar();
			n.setTram(t);
			n.setChiPhi(t.getChiPhi());
			for (KhoangCachCacTram k : banDo.getListKhoangCach()) {
				if (!k.getTramXuatPhat().getTenTram().equals(k.getTramDich().getTenTram())) {
					NodeStar newNode = new NodeStar();
					if (k.getTramXuatPhat().getTenTram().equals(n.getTram().getTenTram())) {
						newNode.setTram(k.getTramDich());
						n.addNeighbour(newNode, k.getKhoangCach());
					}
					if (k.getTramDich().getTenTram().equals(n.getTram().getTenTram())) {
						newNode.setTram(k.getTramXuatPhat());
						n.addNeighbour(newNode, k.getKhoangCach());
					}
				}
			}
			listNode.add(n);
		}
		return listNode;
	}

	public static void printKC_2node(List<KhoangCachCacTram> k) {
		for (KhoangCachCacTram khoangCachCacTram : k) {
			System.out.println(khoangCachCacTram.toString());
		}
	}

	// mahattan
	public double MahattanAl(TramGiao bd, TramGiao dich) {
		return Math.sqrt(Math.pow(bd.getToaDo().getX() - dich.getToaDo().getX(), 2)
				+ Math.pow(bd.getToaDo().getY() - dich.getToaDo().getY(), 2));
	}

	// tim tuyen duong
	public List<TramGiao> TimDuong_AStar(List<NodeStar> li, TramGiao bd, TramGiao dich) {
		List<TramGiao> tuyenDuong = new LinkedList<TramGiao>();
		Stack<NodeStar> stack = new Stack<>();
		for (NodeStar n : li) {
			n.setHn(MahattanAl(n.getTram(), dich));
			if (n.getTram().getTenTram().equals(bd.getTenTram())) {
				stack.add(n);
			}
		}
		while (!stack.isEmpty()) {
			NodeStar term = stack.pop();

			if (term.getTram().getTenTram().equals(dich.getTenTram())) {
				while (term.getParent() != null) {
					tuyenDuong.add(term.getTram());
					term = term.getParent();
				}
			}
			double longPath = 99999999;

		}

		return tuyenDuong;
	}

	public static void printListNodeStar(List<NodeStar> li) {
		for (NodeStar nodeStar : li) {
			System.out.println(nodeStar.toString());
		}
	}

	public static void main(String[] args) {

		List<TramGiao> li = new LinkedList<TramGiao>();

		List lc = new LinkedList<KhoangCachCacTram>();

		BanDo b = new BanDo(lc);
	}
}
