package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import _global.utils.Constant;
import _global.utils.Parser;
import model.bean.MemberBean;
import model.bean.MusicBean;
import model.service.MusicService;
import model.service.MusicStyleService;

@Controller
public class MusicController
{
	@Autowired
	MusicService musicservice;
     
	@Autowired
	MusicStyleService musicStyleService;
	
	@Autowired
	String profilesDirectoryPath;
	@Autowired
	String coverDirectoryPath;
	@Autowired
	String audiosDirectoryPath;

	// =========== music insert =========================================//
	@RequestMapping(value = "/musicInsert", method = RequestMethod.POST)
	public String insertMusic(@RequestParam(value = "music_description", required = false) String description,
			@RequestParam(value = "music_lyrics", required = false) String lyrics,
			@RequestPart("music_link") MultipartFile musicLink, @RequestPart("music_photo") MultipartFile musicPhoto,
			HttpSession session, MusicBean bean, BindingResult bindingResult, Model model)
	{

		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errors", errors);
		// 接收資料

		MemberBean memberLoginOk = (MemberBean) session.getAttribute("loginOK");
		if (memberLoginOk != null)
		{
			// 驗證資料
			if (bindingResult != null && bindingResult.hasErrors())
			{
				if (bindingResult.getFieldErrorCount("music_style_id") != 0)
				{
					errors.put("erroes_styld_id", "音樂類型有誤");
				}
				if (bindingResult.getFieldErrorCount("music_name") != 0)
				{
					errors.put("erroes_music_name", "音樂類型有誤");
				}
				if (bindingResult.getFieldErrorCount("music_member_id") != 0)
				{
					errors.put("erroes_member_id", "音樂類型有誤");
				}
				if (bindingResult.getFieldErrorCount("music_description") != 0)
				{
					errors.put("erroes_description", "音樂類型有誤");
				}
				if (bindingResult.getFieldErrorCount("music_lyrics") != 0)
				{
					errors.put("erroes_lyrics", "音樂類型有誤");
				}
			}

			// 轉換資料
			// music_link ---> musicLink
			// music_photo ---> musicPhoto

			// ==================== musicLink =============================
			String musicName = null;
			String musicFileType = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmSSS");
			String idNum = sdf.format(new java.util.Date().getTime());

			if (musicLink != null)
			{
				musicName = musicLink.getOriginalFilename();
				musicFileType = musicName.split("\\.")[1]; // 取 mp3 等...音樂檔案名子

				String fileName_music_link = idNum + "_" + bean.getMusic_name() + "." + musicFileType;

				try
				{
					musicLink.transferTo(new File(
							audiosDirectoryPath + memberLoginOk.getMbrId() + File.separator + fileName_music_link));
					bean.setMusic_link(
							Constant.audioDirectory + memberLoginOk.getMbrId() + File.separator + fileName_music_link);

				} catch (IllegalStateException | IOException e)
				{
					e.printStackTrace();
					System.out.println("塞音樂GG");
				}
			}

			// ==================== musicPhoto =============================

			String contentType = musicPhoto.getContentType();
			if (!musicPhoto.isEmpty())
			{
				if (contentType.indexOf("image") == -1)
				{
					errors.put("errors_photo", "音樂照片有誤");
				}
			}

			if (musicPhoto != null)
			{
				String photTypeName = contentType.split("/")[1];
				String fileName_music_photo = idNum + "_" + bean.getMusic_name() + "." + photTypeName;
				try
				{
					musicPhoto.transferTo(new File(
							coverDirectoryPath + memberLoginOk.getMbrId() + File.separator + fileName_music_photo));
					bean.setMusic_photo(
							Constant.coverDirectory + memberLoginOk.getMbrId() + File.separator + fileName_music_photo);

				} catch (IllegalStateException | IOException e)
				{
					e.printStackTrace();
					System.out.println("塞圖片GG");
				}
			}
			if (!errors.isEmpty() && errors != null)
			{
				System.out.println("Updaet errors");
				return "f.musicinsert.notOk"; // logical name is here ~~~~
			}
			// 呼叫model
			// 依據model 回傳 view
			if (bean != null)
			{
				MusicBean reslut = musicservice.insert(memberLoginOk.getMbrId(), bean);
				model.addAttribute("message", "上傳音樂成功唷!");
				model.addAttribute("insertMusicOk", reslut);
				return "f.musicinsert.ok";
			}
			// return logical name no login !
		}
		model.addAttribute("errorMessage", "上傳音樂失敗搂~");
		return "f.musicinsert.notOk";
	}
	
	
	
	
	
	// =========== music update =========================================//
		@RequestMapping(value = "/musicupdate", method = RequestMethod.POST)
		public String updateMusic(@RequestParam(value = "music_description", required = false) String description,
				@RequestParam(value = "music_lyrics", required = false) String lyrics,
				@RequestPart("music_link") MultipartFile musicLink, @RequestPart("music_photo") MultipartFile musicPhoto,
				HttpSession session, MusicBean bean, BindingResult bindingResult, Model model)
		{

			Map<String, String> errors = new HashMap<>();
			model.addAttribute("errors", errors);
			// 接收資料
			
			

			MemberBean memberLoginOk = (MemberBean) session.getAttribute("loginOK");
			if (memberLoginOk != null)
			{
				// 驗證資料
				if (bindingResult != null && bindingResult.hasErrors())
				{
					if (bindingResult.getFieldErrorCount("music_style_id") != 0)
					{
						errors.put("erroes_styld_id", "音樂類型有誤");
					}
					if (bindingResult.getFieldErrorCount("music_name") != 0)
					{
						errors.put("erroes_music_name", "音樂類型有誤");
					}
					if (bindingResult.getFieldErrorCount("music_member_id") != 0)
					{
						errors.put("erroes_member_id", "音樂類型有誤");
					}
					if (bindingResult.getFieldErrorCount("music_description") != 0)
					{
						errors.put("erroes_description", "音樂類型有誤");
					}
					if (bindingResult.getFieldErrorCount("music_lyrics") != 0)
					{
						errors.put("erroes_lyrics", "音樂類型有誤");
					}
				}

				// 轉換資料
				// music_link ---> musicLink
				// music_photo ---> musicPhoto

				// ==================== musicLink =============================
				String musicName = null;
				String musicFileType = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmSSS");
				String idNum = sdf.format(new java.util.Date().getTime());

				if (musicLink != null)
				{
					musicName = musicLink.getOriginalFilename();
					musicFileType = musicName.split("\\.")[1]; // 取 mp3 等...音樂檔案名子

					String fileName_music_link = idNum + "_" + bean.getMusic_name() + "." + musicFileType;

					try
					{
						musicLink.transferTo(new File(
								audiosDirectoryPath + memberLoginOk.getMbrId() + File.separator + fileName_music_link));
						bean.setMusic_link(
								Constant.audioDirectory + memberLoginOk.getMbrId() + File.separator + fileName_music_link);

					} catch (IllegalStateException | IOException e)
					{
						e.printStackTrace();
						System.out.println("塞音樂GG");
					}
				}

				// ==================== musicPhoto =============================

				String contentType = musicPhoto.getContentType();
				if (!musicPhoto.isEmpty())
				{
					if (contentType.indexOf("image") == -1)
					{
						errors.put("errors_photo", "音樂照片有誤");
					}
				}

				if (musicPhoto != null)
				{
					String photTypeName = contentType.split("/")[1];
					String fileName_music_photo = idNum + "_" + bean.getMusic_name() + "." + photTypeName;
					try
					{
						musicPhoto.transferTo(new File(
								coverDirectoryPath + memberLoginOk.getMbrId() + File.separator + fileName_music_photo));
						bean.setMusic_photo(
								Constant.coverDirectory + memberLoginOk.getMbrId() + File.separator + fileName_music_photo);

					} catch (IllegalStateException | IOException e)
					{
						e.printStackTrace();
						System.out.println("塞圖片GG");
					}
				}
				if (!errors.isEmpty() && errors != null)
				{
					System.out.println("Updaet errors");
					return "f.musicinsert.notOk"; // logical name is here ~~~~
				}
				// 呼叫model
				// 依據model 回傳 view
				if (bean != null)
				{
					MusicBean reslut = musicservice.update(memberLoginOk.getMbrId(), bean, bean.getMusic_id());
					model.addAttribute("message", "修改音樂成功唷!");
					model.addAttribute("insertMusicOk", reslut);
					return "f.musicinsert.ok";
				}
				// return logical name no login !
			}
			model.addAttribute("errorMessage", "修改音樂失敗搂~");
			return "f.musicinsert.notOk";
		}
	
	

	// =========== music select by id =========================================//
	@RequestMapping(value="/pages/musicSelectByIdAjax", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String selectById(HttpSession session) {
	
		MemberBean result = (MemberBean) session.getAttribute("loginOK");
     	if(result!=null){
		String memberId = result.getMbrId();
		 return  Parser.toJson(musicservice.selectById(memberId));
	   } 
     	return null;
	}
	
	/**
	 * 取得音樂型態的Map<styleId,styleName>
	 * @author Phil 2018.03.15
	 */
	@RequestMapping(value="/music/styleMap", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    @ResponseBody
	public String getMusicStyleMap(HttpSession session) {	 
		 return  Parser.toJson(musicStyleService.getStyleMap());
    }
}

	

