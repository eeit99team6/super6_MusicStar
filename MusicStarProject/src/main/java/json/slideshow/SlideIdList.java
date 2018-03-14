package json.slideshow;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlideIdList {
	
	@SerializedName("idList")
	@Expose
	private List<Integer> idList = null;

	public List<Integer> getIdList() {
	return idList;
	}

	public void setIdList(List<Integer> idList) {
	this.idList = idList;
	}
		
}
