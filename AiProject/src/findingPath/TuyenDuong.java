package findingPath;

import java.util.LinkedList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import data.CumGiao;
import data.KhoangCachCacTram;
import data.TramGiao;

public class TuyenDuong {
	static List<List<Nodee>> allPaths = new LinkedList<>();
	private BanDo banDo;
	private List<Nodee> listNode;

	public TuyenDuong() {

	}

	public List<Nodee> getListNode() {
		return listNode;
	}

	public void setListNode(List<Nodee> listNode) {
		this.listNode = listNode;
	}

	private List<List<TramGiao>> listTuyenDuong;

	public List<List<TramGiao>> getListTuyenDuong() {
		return listTuyenDuong;
	}

	public BanDo getBanDo() {
		return banDo;
	}

	public void setBanDo(BanDo banDo) {
		this.banDo = banDo;
	}

	public void setListTuyenDuong(List<List<TramGiao>> listTuyenDuong) {
		this.listTuyenDuong = listTuyenDuong;
	}

	// convert nodee=> tram
	public List<List<TramGiao>> convertPath() {
		List<List<TramGiao>> result = new LinkedList<>();
		for (List<Nodee> list : allPaths) {
			List l = new LinkedList<>();
			for (Nodee n : list) {
				l.add(n.getTram());
			}
			result.add(l);
		}
		return result;
	}

	// hàm execute
	public void addTuyenDuong(CumGiao cluster) {
		allPaths = new LinkedList<>();
		this.createNodee(banDo);

		// first path
		List<Nodee> firstPath = new LinkedList<>();
		alDijkstra(listNode, listNode.get(0));
		List<Nodee> s = new LinkedList<>();

		List<Nodee> listCheckPoint = new LinkedList<>();

		// ánh xạ node trong main map sang cluster
		for (TramGiao tram : cluster.getListTram()) {
			for (Nodee nodee : listNode) {
				if (nodee.getTram().equals(tram)) {
					listCheckPoint.add(nodee);
				}
			}
		}
		Collections.sort(listCheckPoint);
		// từ node nguồn đến node gần nhất
		Nodee afterBegin = new Nodee(listCheckPoint.get(0).getTram());
		s.clear();
		afterBegin.setParent(listCheckPoint.get(0).getParent());
		afterBegin.setNeighbours(listCheckPoint.get(0).getNeighbours());
		while (afterBegin != null) {
			s.add(afterBegin);
			firstPath.add(afterBegin);
			afterBegin = afterBegin.getParent();
		}
//		Collections.reverse(firstPath);
		firstPath.remove(firstPath.size()-1);
//		allPaths.add(firstPath);
//		Collections.reverse(firstPath);
//		allPaths.add(firstPath);
		// list các đường đi
		for (int i = 0; i < listCheckPoint.size() - 1; i++) {
			Nodee start = listCheckPoint.get(i);
			Nodee end = listCheckPoint.get(i + 1);
			// Tìm tất cả các đường đi từ start đến end
			List<List<Nodee>> paths = findAllPaths(start, end, listNode);
			allPaths.addAll(paths);
		}

		// Chuyển đổi allPaths thành listTuyenDuong (danh sách các đường đi dưới dạng
		// TramGiao)
		listTuyenDuong = new LinkedList<>();
		for (List<Nodee> path : allPaths) {
			List<TramGiao> tuyenDuong = new LinkedList<>();
			for (Nodee nodee : path) {
				tuyenDuong.add(nodee.getTram());
			}
			if (!checkListPath(listTuyenDuong, tuyenDuong)) {
				listTuyenDuong.add(tuyenDuong);
			}
		}
		// merge path

		allPaths = mergePath(allPaths);
		// check invalid paths
		allPaths = checkValidPath(allPaths, listCheckPoint);

		// way back to source
		List<Nodee> WayReturn = new LinkedList<>();
		WayReturn = alDijkstra2Node(listNode, listCheckPoint.get(listCheckPoint.size() - 1), listNode.get(0));
		WayReturn.remove(0);
		List<Nodee> temp = new LinkedList<>();

		for (List<Nodee> li : allPaths) {
			for (Nodee nodee : WayReturn) {
				li.add(nodee);
			}
//			temp.addAll(firstPath);
			if(!li.get(0).equals(listCheckPoint.get(0))) {
				List<Nodee>listFirstWay=new LinkedList<>();
				listFirstWay=alDijkstra2Node(li, li.get(0), listCheckPoint.get(0));
				Collections.reverse(listFirstWay);
				temp.addAll(listFirstWay);
			}
			temp.addAll(li);
			li.clear();
			for (Nodee nodee : temp) {
				li.add(nodee);
			}
			temp.clear();
		}

		// In các đường đi
		printAllpaths(allPaths);
	}

// check valid list in cluster
	private List<List<Nodee>> checkValidPath(List<List<Nodee>> allPaths2, List<Nodee> listCheckPoint) {
		for (int i = 0; i < allPaths2.size(); i++) {
			boolean valid = true;
			for (Nodee nodee : listCheckPoint) {
				if (!allPaths2.get(i).contains(nodee)) {
					valid = false;
					break;
				}
			}
			if (!valid)
				allPaths2.remove(allPaths2.get(i));
		}
		return allPaths2;
	}

	private List<List<Nodee>> mergePath(List<List<Nodee>> allPaths2) {
		boolean merged; // Kiểm tra xem có hợp nhất nào xảy ra không
		do {
			merged = false;
			for (int i = 0; i < allPaths2.size(); i++) {
				for (int j = 0; j < allPaths2.size(); j++) {
					if (i != j && canMerge(allPaths2.get(i), allPaths2.get(j))) {
						// Hợp nhất hai danh sách theo điều kiện
						List<Nodee> mergedList = mergeTwoLists(allPaths2.get(i), allPaths2.get(j));
						allPaths2.remove(j);
//	                    allPaths2.remove(i);  
						allPaths2.add(mergedList);
						merged = true;
						break;
					}
				}
				if (merged)
					break;
			}
		} while (merged);
		return allPaths2;
	}

	// Kiểm tra nếu list1 và list2 có thể hợp nhất (phần tử cuối list1 trùng phần tử
	// đầu list2)
	public boolean canMerge(List<Nodee> list1, List<Nodee> list2) {
		return !list1.isEmpty() && !list2.isEmpty() && list1.get(list1.size() - 1).equals(list2.get(0));
	}

	// (ghép nối list1 và list2)
	private List<Nodee> mergeTwoLists(List<Nodee> list1, List<Nodee> list2) {
		List<Nodee> mergedList = new LinkedList<>(list1);
		mergedList.remove(mergedList.size() - 1);
		mergedList.addAll(list2);
		return mergedList;
	}

	// print
	public static void printAllpaths(List<List<Nodee>> path) {
		for (int i = 0; i < path.size(); i++) {
			for (Nodee n : path.get(i)) {
				System.out.print(n.getTram().getTenTram() + " ");
			}
			System.out.println();
		}
	}
	// find path

	public List<List<Nodee>> findAllPaths(Nodee start, Nodee end, List<Nodee> listCheckPoint) {
		List<List<Nodee>> allPaths = new LinkedList<>();
		Set<Nodee> visited = new HashSet<>();
		List<Nodee> currentPath = new LinkedList<>();

		dfs(start, end, visited, currentPath, allPaths);
		return allPaths;
	}

	private void dfs(Nodee current, Nodee end, Set<Nodee> visited, List<Nodee> currentPath,
			List<List<Nodee>> allPaths) {
		visited.add(current);
		currentPath.add(current);
		// tìm được đích
		if (current.equals(end)) {
			allPaths.add(new LinkedList<>(currentPath));
		} else {
			for (KhoangCachCacTram edge : current.getNeighbours()) {
				Nodee nextNode = null;
				for (Nodee nodee : this.getListNode()) {
					if (nodee.getTram().equals(edge.getTramDich())) {
						nextNode = nodee;
						break;
					}
				}

				if (nextNode != null && !visited.contains(nextNode)) {
					dfs(nextNode, end, visited, currentPath, allPaths);
				}
			}
		}

		// Backtrack
		currentPath.remove(currentPath.size() - 1);
		visited.remove(current);
	}

	// checklist similar
	public static boolean checkListPath(List<List<TramGiao>> listPath, List<TramGiao> listNeedCheck) {
		for (List li : listPath) {
			if (listPath.isEmpty())
				return false;
			if ((checkSimilar(li, listNeedCheck))) {
				return true;
			}
		}
		return false;
	}

	// Hàm phụ trợ để kiểm tra hai danh sách có giống nhau hoàn toàn không
	public static boolean checkSimilar(List<TramGiao> list1, List<TramGiao> list2) {
		if (list1.size() != list2.size()) {
			return false;
		}
		int index = 0;
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i).equals(list2.get(i))) {
				index++;
			}
		}
		if ((index == list1.size()))
			return true;

		return false;
	}

	// create Nodee
	public void createNodee(BanDo map) {
		PriorityQueue<Nodee> result = new PriorityQueue<>();
		boolean nodeDau = false;
		boolean nodeDuoi = false;
		Nodee temp1;
		Nodee temp2;
		double khoangCach = 0;
		double chiPhi = 0;
		for (KhoangCachCacTram kc : map.getListKhoangCach()) {
			temp1 = null;
			temp2 = null;
			nodeDau = false;
			nodeDuoi = false;
			khoangCach = 0;
			chiPhi = 0;
			for (Nodee nodee : result) {
				if (nodee.getTram().equals(kc.getTramXuatPhat())) {
					nodeDau = true;
					temp1 = nodee;
				}
				if (nodee.getTram().equals(kc.getTramDich())) {
					nodeDuoi = true;
					temp2 = nodee;
				}
			}

			khoangCach = kc.getKhoangCach();
			chiPhi = kc.getChiPhi();
			// chi co node dau
			if (nodeDau && !nodeDuoi) {
				Nodee n = new Nodee(kc.getTramDich());
				temp1.addNeighbour(n, khoangCach, chiPhi);
				result.add(n);

			} else
			// chi co node dich
			if (!nodeDau && nodeDuoi) {
				Nodee n = new Nodee(kc.getTramXuatPhat());
				n.addNeighbour(temp2, khoangCach, chiPhi);
				result.add(n);

			} // ko co node nao
			else if (!nodeDau && !nodeDuoi) {
				Nodee n1 = new Nodee(kc.getTramXuatPhat());
				Nodee n2 = new Nodee(kc.getTramDich());
				n1.addNeighbour(n2, khoangCach, chiPhi);
				result.add(n1);
				result.add(n2);
			} else {
				// co 2 node dau va duoi
				temp1.addNeighbour(temp2, khoangCach, chiPhi);
			}
		}

		List<Nodee> finalList = new LinkedList<>();
		finalList.addAll(result);
		this.setListNode(finalList);
	}

	// al Disjtra
	public void alDijkstra(List<Nodee> listNodee, Nodee start) {
		for (Nodee Nodee : listNodee) {
			if (Nodee.getCost() < 0) {
				return;
			}
		}
		PriorityQueue<Nodee> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(start);

		for (Nodee Nodee : listNodee) {
			if (!Nodee.equals(start)) {
				Nodee.setCost(Double.MAX_VALUE);
			}
		}
		while (!priorityQueue.isEmpty()) {

			Nodee current = priorityQueue.poll();
			for (KhoangCachCacTram kc : current.getNeighbours()) {
				if (kc.getTramXuatPhat().equals(current.getTram())) {
					for (Nodee nodee : listNodee) {
						if (nodee.getTram().equals(kc.getTramDich())) {
							if (current.getCost() + kc.getKhoangCach() < nodee.getCost()) {
								priorityQueue.remove(nodee);
								nodee.setCost(current.getCost() + kc.getKhoangCach());
								nodee.setParent(current);
								priorityQueue.add(nodee);
							}
						}
					}
				}
			}
		}
	}

	// al disjtra cho 2 điểm
	public List<Nodee> alDijkstra2Node(List<Nodee> listNodee, Nodee start, Nodee end) {
		List<Nodee> result = new LinkedList<>();

		for (Nodee node : listNodee) {
			if (node.getCost() < 0) {
				return null;
			}
		}

		// Đặt chi phí ban đầu
		start.setCost(0);
		PriorityQueue<Nodee> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(start);

		// Đặt chi phí ban đầu
		for (Nodee node : listNodee) {
			if (!node.equals(start)) {
				node.setCost(Double.MAX_VALUE);
			}
			node.setParent(null);
		}

		Set<Nodee> visited = new HashSet<>();
		while (!priorityQueue.isEmpty()) {
			Nodee current = priorityQueue.poll();

			if (visited.contains(current)) {
				continue;
			}
			visited.add(current);

			if (current.equals(end)) {
				while (current != null) {
					result.add(0, current); // Thêm từ đích về đầu
					current = current.getParent();
				}
				return result;
			}

			for (KhoangCachCacTram kc : current.getNeighbours()) {
				if (kc.getTramXuatPhat().equals(current.getTram())) {
					for (Nodee neighbor : listNodee) {
						if (neighbor.getTram().equals(kc.getTramDich()) && !visited.contains(neighbor)) {
							double newCost = current.getCost() + kc.getKhoangCach();
							if (newCost < neighbor.getCost()) {
								priorityQueue.remove(neighbor);
								neighbor.setCost(newCost);
								neighbor.setParent(current);
								priorityQueue.add(neighbor);
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static void print(List<Nodee> li) {
		for (Nodee nodee : li) {
			System.out.println(nodee.getTram().toString() + "___" + nodee.getCost());
		}
	}

	public static void check(List<List<Integer>> li) {
		for (int i = 0; i < li.size(); i++) {
			for (Integer n : li.get(i)) {
				System.out.print(n + "  ");
			}
			System.out.println();
		}
	}
}
