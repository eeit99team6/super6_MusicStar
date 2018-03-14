package json.slideshow;

import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlideOrderMap {
	
	@SerializedName("orderMap")
	@Expose
	private Map<Integer,Short> orderMap;

	public Map<Integer, Short> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<Integer, Short> orderMap) {
		this.orderMap = orderMap;
	}
		
}
