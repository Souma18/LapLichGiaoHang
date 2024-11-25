package model;

import data.NSX;

public class Model {
	private ModelCumGiao modelCumGiao;
	private ModelTuyenDuong modelTuyenDuong;
	private ModelSapXep modelSapXep;
	private NSX nxs;
	public Model(ModelCumGiao modelCumGiao, ModelTuyenDuong modelTuyenDuong, NSX nxs) {
		this.modelCumGiao = modelCumGiao;
		this.modelTuyenDuong = modelTuyenDuong;
		this.nxs = nxs;
	}
	public ModelCumGiao getModelCumGiao() {
		return modelCumGiao;
	}
	public void setModelCumGiao(ModelCumGiao modelCumGiao) {
		this.modelCumGiao = modelCumGiao;
	}
	public ModelTuyenDuong getModelTuyenDuong() {
		return modelTuyenDuong;
	}
	public void setModelTuyenDuong(ModelTuyenDuong modelTuyenDuong) {
		this.modelTuyenDuong = modelTuyenDuong;
	}
	public NSX getNxs() {
		return nxs;
	}
	public void setNxs(NSX nxs) {
		this.nxs = nxs;
	}
	
	
}
