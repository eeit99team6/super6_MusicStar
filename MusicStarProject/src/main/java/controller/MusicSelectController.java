package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.bean.MusicBean;
import model.service.MusicService;

/**
 * @author Yeh
 */

@Controller
public class MusicSelectController {
	
	@Autowired
	private MusicService service;
	
	@RequestMapping(
		path={"/music/radioSelect"},
		method={RequestMethod.GET, RequestMethod.POST}
	)
	protected String method(String Style,String Singer,Model model,HttpSession session){
		
		Map<String,String> map = new HashMap<String,String>();
		List<MusicBean> bean = new ArrayList<MusicBean>();
		Integer integer = null;
		
		//根據接收到的資料，呼叫對應的DAO方法

		if(Style.equals(Style) && Singer.equals(Singer)){
			if("0".equals(Style) && "AllSinger".equals(Singer)){	// AllStyle + AllSinger
				bean = service.select();
			}
			else if("0".equals(Style) && "9527".equals(Singer)){	// AllStyle + 9527
				bean = service.selectSinger(Singer);
			}
			else if(Style.equals(Style) && "9527".equals(Singer) && !"0".equals(Style)){
				integer = Integer.parseInt(Style);					// Style + 9527
				bean = service.selectSingerStyle(Singer, integer);
			}
			else if("0".equals(Style) && Singer.equals(Singer)){	// AllStyle + Singer
				bean = service.selectSinger(Singer);
			}
			else if(Style.equals(Style) && "AllSinger".equals(Singer)){
				integer = Integer.parseInt(Style);					// Style + AllSinger
				bean = service.selectStyle(integer);
			}
			else if(Style.equals(Style) && Singer.equals(Singer)){	// Style + Singer
				integer = Integer.parseInt(Style);
				bean = service.selectSingerStyle(Singer, integer);
			}
			
			model.addAttribute("bean", bean);
			map.put("AllStyle", Style);
			map.put("Singer", Singer);
		}
		
		//存放物件至HttpSession，並導向View
		session.setAttribute("Style", map);
		
		return "music.select";
	}
}
