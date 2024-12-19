package findingPath;

import java.util.ArrayList;
import java.util.List;

import data.KhoangCachCacTram;
import data.TramGiao;

public class Nodee implements Comparable<Nodee>{
	private TramGiao tram;
	private List<KhoangCachCacTram> neighbours = new ArrayList<>();
	private double cost;
	private Nodee parent;
	

	// mahattan
	public double MahattanAl(TramGiao bd, TramGiao dich) {
		return Math.sqrt(Math.pow(bd.getToaDo().getX() - dich.getToaDo().getX(), 2)
				+ Math.pow(bd.getToaDo().getY() - dich.getToaDo().getY(), 2));
	}



	public Nodee getParent() {
		return parent;
	}

	public void setParent(Nodee parent) {
		this.parent = parent;
	}



	public TramGiao getTram() {
		return tram;
	}

	public void setTram(TramGiao tram) {
		this.tram = tram;
	}

	public void addNeighbour(Nodee nodee, double d, double c) {
		this.neighbours.add(new KhoangCachCacTram(this.getTram(), nodee.getTram(), d, c));
	}

	public Nodee(TramGiao tram) {
		this.tram = tram;
	}

	public void setChiPhi(double chiPhi) {
		this.getTram().setChiPhi(chiPhi);

	}

	public List<KhoangCachCacTram> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<KhoangCachCacTram> neighbours) {
		this.neighbours = neighbours;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(Nodee that) {
		return Double.compare(this.cost, that.cost);
	}
	@Override
	public String toString() {
		return getTram().getTenTram()+" "+ neighbours.toString()+"\n";
	}
	
}
