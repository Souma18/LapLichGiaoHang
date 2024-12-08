package findingPath;

import java.util.List;

import data.KhoangCachCacTram;

public class BanDo {
	private List<KhoangCachCacTram> listKhoangCach;

	public BanDo(List<KhoangCachCacTram> listKhoangCach) {
		this.listKhoangCach = listKhoangCach;
	}

	public List<KhoangCachCacTram> getListKhoangCach() {
		return listKhoangCach;
	}

	public void setListKhoangCach(List<KhoangCachCacTram> listKhoangCach) {
		this.listKhoangCach = listKhoangCach;
	}
	
}
