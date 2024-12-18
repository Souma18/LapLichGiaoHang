package model;

import java.util.Map;

import javax.swing.plaf.metal.MetalIconFactory.TreeLeafIcon;

import data.TuyenDuongDuocTaoRa;

public class ModelTuyenDuong {
	private TuyenDuongDuocTaoRa tuyenDuongDuocTaoRa;

	public ModelTuyenDuong(TuyenDuongDuocTaoRa tuyenDuongDuocTaoRa) {
		this.tuyenDuongDuocTaoRa = tuyenDuongDuocTaoRa;
	}

	public TuyenDuongDuocTaoRa getTuyenDuongDuocTaoRa() {
		return tuyenDuongDuocTaoRa;
	}

	public void setTuyenDuongDuocTaoRa(TuyenDuongDuocTaoRa tuyenDuongDuocTaoRa) {
		this.tuyenDuongDuocTaoRa = tuyenDuongDuocTaoRa;
	}
}
