package model.service;

import java.io.File;
import java.util.ArrayList;
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

	public boolean updateSlide(SlideshowBean sb) {
		if (sb != null) {
			List<SlideshowBean> slides = (List<SlideshowBean>) servletContext.getAttribute("slides");
			for (SlideshowBean bean : slides) {
				if (sb.getSlide_id() == bean.getSlide_id()) {
					bean.setSlide_name(sb.getSlide_name());
					bean.setSlide_link(sb.getSlide_link());
					bean.setSlide_description(sb.getSlide_description());
					String slidePhoto = sb.getSlide_photo();
					if( slidePhoto != null) {
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

	public synchronized List<String> removeSlide(List<Integer> slideIdList) {
		
		if (Checker.notEmpty(slideIdList)) {
			List<SlideshowBean> slides = (List<SlideshowBean>) servletContext.getAttribute("slides");
			List<String> fileNameList = new ArrayList<>();
			Iterator<SlideshowBean> iterator = slides.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				SlideshowBean bean = iterator.next();
				if (slideIdList.contains(bean.getSlide_id())) {
					slideshowDAO.delete(bean);
					String photoDirectory = bean.getSlide_photo();			
					String fileName = photoDirectory.substring(photoDirectory.lastIndexOf("/")+1);
					fileNameList.add(fileName);
					iterator.remove();
					i++;
				}
			}			
			return fileNameList;
		}
		return null;
	}

}
