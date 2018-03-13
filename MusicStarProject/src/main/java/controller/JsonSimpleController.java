package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import _global.utils.Parser;
import model.bean.MusicBean;
import model.service.MusicService;

/**
 * @author Yeh
 */

@Controller
public class JsonSimpleController {
	
	@Autowired
	private MusicService service;
	
	@RequestMapping(
			path={"/music/jsonSimple"},
			method={RequestMethod.GET, RequestMethod.POST},
			produces="application/json; charset=utf-8"
	)
	@ResponseBody
	protected String method(String keyword,HttpServletRequest request,HttpServletResponse response){
		System.out.println("At JsonSimpleController");
		List<MusicBean> listBean = new ArrayList<MusicBean>();
		
		if(keyword == null){
			keyword = "";
		}
		
		listBean = service.selectAllMusicName(keyword);
		
		return Parser.toJson(listBean);
	}
}
