package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import _global.utils.Checker;
import _global.utils.Constant;
import _global.utils.Parser;
import json.slideshow.SlideIdList;
import json.slideshow.SlideOrderMap;
import model.bean.SlideshowBean;
import model.service.SlideshowService;

@Controller
public class SlideshowController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	SlideshowService slideshowService;
	@Autowired
	String sildeshowDirectoryPath;

	@RequestMapping(path = "/slideshow/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String addSlide(String slideName, String slideLink, String slideDescription,
			@RequestPart("slidePhoto") MultipartFile slidePhoto) {
		Map<String, String> data = new HashMap<>();

		if (!Checker.notEmpty(slideName)) {
			data.put("nameErr", "必須提供slideName");
		}
		if (!Checker.notEmpty(slideLink)) {
			data.put("linkErr", "必須提供slideLink");
		}
		if (!Checker.notEmpty(slideDescription)) {
			data.put("descriptionErr", "必須提供slideDescription");
		}
		if (slidePhoto.isEmpty()) {
			data.put("photoErr", "必須上傳輪播圖檔");
		} else if (!(slidePhoto.getContentType().indexOf("image") == 0)) {
			data.put("photoErr", "上傳之檔案必須為圖檔");
		}
		if (!Checker.notEmpty(data)) {
			String fileName = System.currentTimeMillis() + "_" + slidePhoto.getOriginalFilename();
			try {
				slidePhoto.transferTo(new File(sildeshowDirectoryPath + fileName));
				SlideshowBean bean = new SlideshowBean();
				bean.setSlide_name(slideName);
				bean.setSlide_photo(Constant.sildeshowDirectory + fileName);
				bean.setSlide_link(slideLink);
				bean.setSlide_description(slideDescription);
				if (slideshowService.addSlide(bean)) {
					data.put("success", "新增成功");
				} else {
					data.put("errMsg", "新增失敗");
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				data.put("errMsg", "新增失敗");
			}
		} else {
			data.put("errMsg", "請根據提示進行修正");
		}
		return Parser.toJson(data);
	}

	@RequestMapping(path = "/slideshow/remove", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String removeSlide(String slideIdList) {
		Map<String, String> data = new HashMap<>();
		SlideIdList sil = Parser.parseJson(slideIdList, SlideIdList.class);
		List<Integer> idList = sil.getIdList();
		if (Checker.notEmpty(idList)) {
			List<String> fileNameList = slideshowService.removeSlide(idList);
			if (Checker.notEmpty(fileNameList)) {
				for(String fileName : fileNameList) {
					File file = new File(sildeshowDirectoryPath + fileName);
					if (file.exists()){ 
						file.delete(); 
						} 
				}
				data.put("success", "刪除成功");
			} else {
				data.put("errMsg", "刪除失敗");
			}
		}else {
			data.put("errMsg", "沒有選擇任何要刪除的項目");
		}
		return Parser.toJson(data);
	}

	@RequestMapping(path = "/slideshow/getSlides", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getSlides() {
		Map<String, String> data = new HashMap<>();
		List<SlideshowBean> slides = (List<SlideshowBean>) servletContext.getAttribute("slides");
		if (slides != null) {
			return Parser.toJson(slides);
		} else {
			data.put("errMsg", "目前資料庫中沒有任何輪播圖");
			return Parser.toJson(data);
		}
	}

	@RequestMapping(path = "/slideshow/update", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String updateSlide(String slideId, String slideName, String slideLink, String slideDescription,
			@RequestPart("slidePhoto") MultipartFile slidePhoto) {
		Map<String, String> data = new HashMap<>();
		Integer intSlideId = null;

		if (!Checker.notEmpty(slideId)) {
			data.put("idErr", "必須提供slideId");
		} else if ((intSlideId = Parser.parseInt(slideId)) == null) {
			data.put("idErr", "slideId格式錯誤");
		}
		if (!Checker.notEmpty(slideName)) {
			data.put("nameErr", "必須提供slideName");
		}
		if (!Checker.notEmpty(slideLink)) {
			data.put("linkErr", "必須提供slideLink");
		}
		if (!Checker.notEmpty(slideDescription)) {
			data.put("descriptionErr", "必須提供slideDescription");
		}
		String fileName = null;
		if (!slidePhoto.isEmpty()) {
			if (!(slidePhoto.getContentType().indexOf("image") == 0)) {
				data.put("photoErr", "上傳之檔案必須為圖檔");
			} else {
				fileName = System.currentTimeMillis() + "_" + slidePhoto.getOriginalFilename();
			}
		}
		if (!Checker.notEmpty(data)) {
			SlideshowBean bean = new SlideshowBean();
			bean.setSlide_id(intSlideId);
			bean.setSlide_name(slideName);
			bean.setSlide_link(slideLink);
			bean.setSlide_description(slideDescription);
			if (fileName != null) {
				try {
					slidePhoto.transferTo(new File(sildeshowDirectoryPath + fileName));
					bean.setSlide_photo(Constant.sildeshowDirectory + fileName);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					data.put("photoErr", "上傳檔案失敗");
					return Parser.toJson(data);
				}
			}
			if (slideshowService.updateSlide(bean)) {
				data.put("success", "修改成功");
			} else {
				data.put("errMsg", "修改失敗");
			}
		} else {
			data.put("errMsg", "請根據提示進行修正");
		}
		return Parser.toJson(data);
	}

	@RequestMapping(path = "/slideshow/changeOrder", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String changeOrder(String sildeOrderMap) {
		Map<String, String> data = new HashMap<>();
		if (Checker.notEmpty(sildeOrderMap)) {
			try {
				SlideOrderMap som = Parser.parseJson(sildeOrderMap, SlideOrderMap.class);
				System.out.println("orderMap = " + som.getOrderMap());
				if (slideshowService.changeSlidesOrder(som.getOrderMap())) {
					data.put("success", "調整排序成功");
				} else {
					data.put("errMsg", "調整排序失敗");
				}
			} catch (Exception e) {
				data.put("errMsg", "orderMap格式錯誤");
			}
		} else {
			data.put("errMsg", "必須提供orderMap");
		}
		return Parser.toJson(data);
	}

}
