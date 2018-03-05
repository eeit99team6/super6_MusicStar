package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import _global.utils.Constant;

@Controller
public class FileController
{
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	String fileSourcePath;
	
	// 上傳文件儲存目錄
	private static final String UPLOAD_DIRECTORY = "upload";

	// 上傳配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	@RequestMapping(path = "/FileUpload", method = RequestMethod.POST)
	public String testFileUpload(HttpServletRequest request, HttpServletResponse response)
	{

		// 檢測是否為多媒體上傳
		if (ServletFileUpload.isMultipartContent(request))
		{
			// 創建 DiskFileItemFactory
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 設置內存臨界值 - 超過後將產生臨時文件並存儲於臨時目錄中
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			// 設置零時儲存目錄
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			// 創建 file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 設置最大文件上傳值
			upload.setFileSizeMax(MAX_FILE_SIZE);
			// 設置最大請求值 (包含文件和表單數據)
			upload.setSizeMax(MAX_REQUEST_SIZE);
			// 編碼設定
			upload.setHeaderEncoding("UTF-8");

			String uploadPath = fileSourcePath + UPLOAD_DIRECTORY;
			// 如果目錄不存在則創建此目錄
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
			{
				uploadDir.mkdir();
			}
			try
			{
				// FileItem List
				List<FileItem> items = upload.parseRequest(request);
				if (items != null && items.size() > 0)
				{
					for (FileItem item : items)
					{
						if (!item.isFormField())
						{

							String fileName = new File(item.getName()).getName();
							System.out.println(item.getName());
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							// 輸出上傳路徑
							System.out.println("filePath = " + filePath);
							// 保存文件到硬碟
							item.write(storeFile);
							System.out.println("上傳成功!!");
						}
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		return "f.test_fileupload";
	}
	
	@RequestMapping(path = "/FileUpload2", method = RequestMethod.POST)
	public String testFileUpload2(HttpServletRequest request, HttpServletResponse response)
	{
			Map<String,byte[]> data = null;
			// 檢測是否為多媒體上傳
			if (ServletFileUpload.isMultipartContent(request))
			{
				// 創建 DiskFileItemFactory
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 設置內存臨界值 - 超過後將產生臨時文件並存儲於臨時目錄中
				factory.setSizeThreshold(Constant.fileUploadMemoryThreshold);
				// 設置零時儲存目錄
				factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
				// 創建 file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 設置最大文件上傳值
				upload.setFileSizeMax(Constant.fileUploadMaxFileSize);
				// 設置最大請求值 (包含文件和表單數據)
				upload.setSizeMax(Constant.fileUploadMaxRequestSize);
				// 編碼設定
				upload.setHeaderEncoding("UTF-8");

				try
				{
					// FileItem List
					List<FileItem> items = upload.parseRequest(request);
					if (items != null && items.size() > 0)
					{
						data = new HashMap<>();
						for (FileItem item : items)
						{
							if (!item.isFormField())
							{
								byte[] file = new byte[(int)item.getSize()];							
								String fileFieldName = item.getFieldName();
								System.out.println("fileFieldName = " + fileFieldName);
								InputStream is = item.getInputStream();
								is.read(file);
								data.put(fileFieldName, file);
								is.close();
							}
						}
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		System.out.println(new String(data.get("audioFile")));
		return "f.test_fileupload";
	}
	
	@RequestMapping(path = "/FileUpload3", method = RequestMethod.POST)
	public String testFileUpload3(@RequestPart("file") MultipartFile file)
	{
		if(file != null) {
		try {
			String filePath = fileSourcePath + file.getOriginalFilename();
			file.transferTo(new File(filePath));
			System.out.println("filePath = " + filePath);
			System.out.println("上傳成功!!");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		}
		return "f.test_fileupload";
	}
}
