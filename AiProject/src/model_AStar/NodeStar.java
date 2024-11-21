package model_AStar;

import java.util.HashMap;
import java.util.Map;

import data.TramGiao;

public class NodeStar {
	private TramGiao tram;
	private double chiPhi;
	private double hn;
	private double gn;
	private NodeStar parent;
	private Map<NodeStar, Double> listneighbours = new HashMap<>();

	public TramGiao getTram() {
		return tram;
	}

	public void setTram(TramGiao tram) {
		this.tram = tram;
	}

	public double getChiPhi() {
		return chiPhi;
	}

	public void setChiPhi(double chiPhi) {
		this.chiPhi = chiPhi;
	}

	public Map<NodeStar, Double> getNeighbours() {
		return listneighbours;
	}

	

	public void addNeighbour(NodeStar n, double khoangCach) {
		listneighbours.put(n, khoangCach);
	}

	public double getHn() {
		return hn;
	}

	public void setHn(double hn) {
		this.hn = hn;
	}

	public double getGn() {
		return gn;
	}

	public void setGn(double gn) {
		this.gn = gn;
	}

	public NodeStar getParent() {
		return parent;
	}

	public void setParent(NodeStar parent) {
		this.parent = parent;
	}

	public Map<NodeStar, Double> getListneighbours() {
		return listneighbours;
	}

	public void setListneighbours(Map<NodeStar, Double> listneighbours) {
		this.listneighbours = listneighbours;
	}

	@Override
	public String toString() {
		return "NodeStar [tram=" + tram + ", chiPhi=" + chiPhi + ", listneighbours=" + listneighbours + "]";
	}

}
