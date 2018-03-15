package model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicStyleBean;
import model.dao.MusicStyleDAO;

@Service
@Transactional
public class MusicStyleService {
	@Autowired
	private MusicStyleDAO musicStyleDAO;

	/**
	 * 取得Music Style 的 Map
	 * @author Phil 2018.03.15
	 * @return styleMap 其中 key為styleId value為styleNname
	 */
	public Map<Integer, String> getStyleMap() {
		List<MusicStyleBean> styleList = musicStyleDAO.selectAll();
		Map<Integer, String> styleMap = new HashMap<>();
		for (MusicStyleBean style : styleList) {
			styleMap.put(style.getMusic_style_id(), style.getMusic_style_name());
		}
		return styleMap;
	}

}
