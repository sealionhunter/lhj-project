package com.ustcsoft.generalsolution.dmat.webui.notice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application message page.
 */
@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private NoticeService service ;
	@Inject
	public NoticeController(NoticeService service) {
		this.service = service;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/notice/noticeList", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		List<Notice> notices;
		try {
			notices = service.find();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			notices = new ArrayList<Notice>();
		}
		
		model.addAttribute("noticeList", notices );
		
		return "notice/noticeList";
	}

}
