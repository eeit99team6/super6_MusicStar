package controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import _global.utils.Parser;
import model.bean.MusicContestBean;
import model.service.MusicContestService;

@Controller
public class MusicContestController {
	
	@InitBinder
	public void initializer(WebDataBinder webDataBinder) {
	webDataBinder.registerCustomEditor
		(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@Autowired
	String fileSourcePath;
	
	@Autowired
	MusicContestService musicContestService;
// ============================ 創建比賽  && 修改比賽用 ============================== //
	@RequestMapping(value = "/backend/pages/musicContext", method = RequestMethod.POST)
	public String musicCreate(@RequestParam(value="music_contest_id",required=false) String id,
			@RequestParam(value="music_contest_update",required=false) String update,@RequestPart(value="music_contest_photo",required=false) MultipartFile photo,
			MusicContestBean bean, BindingResult bindingResult, Model model
			) {
     System.out.println("我來了~~~");
		// 接收資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		// 轉換資料
		if (bindingResult != null && bindingResult.hasErrors()) {
			if (bindingResult.getFieldErrorCount("music_contest_name")!=0) {
				errors.put("errors_name", "名稱有誤");
			}
			if (bindingResult.getFieldErrorCount("music_contest_description")!=0) {
				errors.put("errors_music_contest_description", "介紹有誤");
			}
			if (bindingResult.getFieldErrorCount("music_contest_style_id")!=0) {
				errors.put("errors_tempStyleId", "音樂風格有誤");
			}
			if (bindingResult.getFieldErrorCount("music_contest_status")!=0) {
				errors.put("errors_tempStaus", "音樂狀態有誤");
			}
			if (bindingResult.getFieldErrorCount("music_contest_apply_start_date")!=0) {
				errors.put("errors_tempStartDate", "音樂開始日期有誤");
			}
			if (bindingResult.getFieldErrorCount("music_contest_validate_date")!=0) {
				errors.put("errors_tempValDate", "音樂審核日期有誤");
			}
			if (bindingResult.equals("music_contest_vote_start_date")) {
				errors.put("errors_tempVotDate", "音樂投票日期有誤");
			}
			if (bindingResult.equals("music_contest_end_date")) {
				errors.put("errors_tempEndDate", "比賽截止日期有誤");
			}
		}

		// 驗證資料
		   // photo
		if(photo!=null){
			String contentType = photo.getContentType();
			System.out.println("照片進來了");
			try {
				String photoName = bean.getMusic_contest_name() + "."+contentType.split("/")[1];
				System.out.println("photoName:"+ photoName);
				FileOutputStream outWrite = new FileOutputStream(fileSourcePath+"/images/"+photoName);
				System.out.println("塞給路徑");
				InputStream temInput = photo.getInputStream();
				byte[] buffer = new byte[8192];
				int bytesRead = 0;
				ByteArrayOutputStream output= new ByteArrayOutputStream();
				while ((bytesRead = temInput.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
				}
				output.flush();
				output.close();
				
				byte[] b = output.toByteArray();
				outWrite.write(b, 0, (int)b.length);
				outWrite.close();
				bean.setMusic_contest_photo("/FileSource/images/"+photoName); 
			    System.out.println("塞給BEAN");
			} catch (Exception e) {
				e.printStackTrace();
                System.out.println("lol GG");
				errors.put("errors_photo", "圖片有誤");
			}
		}
		  
		if(!errors.isEmpty() && errors!=null) {
			System.out.println("Updaet errors");
			return "f.musicContestCreate.notOk"; 
		}
		// 呼叫model
		// 依據model回傳view
		
		if(bean!=null && update==null && id==null) {
			MusicContestBean result=  musicContestService.insert(bean);
			model.addAttribute("createOK", result);
			return "r.musicContestCreate.ok";
		}else {
			// Update Information
		       //  id 
		       //  update key words 
			MusicContestBean updateResult= musicContestService.update(bean.getMusic_contest_id(), bean);
			model.addAttribute("updateOK", updateResult);
			return "r.musicContestUpdate.ok";
     	}
	}
	
// ============================ 讀取全部賽事圖表用 AJAX ============================== //
	@RequestMapping(value="/backend/pages/musicContextCheckAjax", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String musicCheckAjax() {
		return Parser.toJson(musicContestService.select());
	}
	
// ============================ 讀取報名中賽事圖表用 AJAX ============================== //
	
	@RequestMapping(value="/pages/musicContesetSignUpAjax", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String musicContesetSignUpAjax() {
		return Parser.toJson(musicContestService.selectMusicContestSignUp());
	}
	
	// ============================ 讀取比賽結束賽事圖表用 AJAX ============================== //
	
	@RequestMapping(value="/pages/musicContesetHistoryAjax", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String musicContesetHistoryAjax() {
		return Parser.toJson(musicContestService.selectHistoryMusicContest());
	}


}
