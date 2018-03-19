package model.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _global.utils.Checker;
import model.bean.SlideshowBean;
import model.dao.SlideshowDAO;

@Service
@Transactional
public class SlideshowService {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private SlideshowDAO slideshowDAO;

	public List<SlideshowBean> getAllSlides() {
		return slideshowDAO.selectAll();
	}

	/**
	 * @author Phil 2018.03.14
	 */
	public synchronized boolean changeSlidesOrder(Map<Integer, Short> orderMap) {
		if (orderMap != null) {
			List<SlideshowBean> slides = (List<SlideshowBean>) servletContext.getAttribute("slides");
			if (Checker.notEmpty(slides)) {
				for (SlideshowBean bean : slides) {
					bean.setSlide_order(orderMap.get(bean.getSlide_id()));
					slideshowDAO.update(bean);
				}
				servletContext.setAttribute("slides", getAllSlides());
				return true;
			}
		}
		return false;
	}

	/**
	 * @author Phil 2018.03.14
	 */
	public boolean addSlide(SlideshowBean bean) {
		if (bean != null) {
			List<SlideshowBean> slides = (List<SlideshowBean>) servletContext.getAttribute("slides");
			bean.setSlide_order((short) (slides.size() + 1));
			bean = slideshowDAO.insert(bean);
			slides.add(bean);
			return true;
		}
		return false;
	}

	/**
	 * @author Phil 2018.03.14
	 */
	public boolean updateSlide(SlideshowBean sb, String sildeshowDirectoryPath) {
		if (sb != null) {
			List<SlideshowBean> slides = (List<SlideshowBean>) servletContext.getAttribute("slides");
			for (SlideshowBean bean : slides) {
				if (sb.getSlide_id() == bean.getSlide_id()) {
					bean.setSlide_name(sb.getSlide_name());
					bean.setSlide_link(sb.getSlide_link());
					bean.setSlide_description(sb.getSlide_description());
					String slidePhoto = sb.getSlide_photo();
					if( slidePhoto != null) {
						String photoDirectory = bean.getSlide_photo();			
						String fileName = photoDirectory.substring(photoDirectory.lastIndexOf("/")+1);
						File file = new File(sildeshowDirectoryPath + fileName);
						if (file.exists()){ 
							file.delete(); 
							} 
						bean.setSlide_photo(slidePhoto);					
					}
					if (slideshowDAO.update(bean)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @author Phil 2018.03.14
	 */
	public synchronized boolean removeSlide(List<Integer> slideIdList, String sildeshowDirectoryPath) {
		
		if (Checker.notEmpty(slideIdList)) {
			List<SlideshowBean> slides = (List<SlideshowBean>) servletContext.getAttribute("slides");
			Iterator<SlideshowBean> iterator = slides.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				SlideshowBean bean = iterator.next();
				if (slideIdList.contains(bean.getSlide_id())) {
					slideshowDAO.delete(bean);
					String photoDirectory = bean.getSlide_photo();			
					String fileName = photoDirectory.substring(photoDirectory.lastIndexOf("/")+1);
					File file = new File(sildeshowDirectoryPath + fileName);
					if (file.exists()){ 
						file.delete(); 
						} 
					iterator.remove();
					i++;
				}
			}			
			return true;
		}
		return false;
	}

}
