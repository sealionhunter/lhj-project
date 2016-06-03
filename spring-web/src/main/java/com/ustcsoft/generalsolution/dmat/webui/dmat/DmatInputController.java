package com.ustcsoft.generalsolution.dmat.webui.dmat;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/dmat/dmatInput")
public class DmatInputController {
	private DmatService service;

	@Inject
	public DmatInputController(DmatService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getDmatDetails(Model model) throws SQLException {
		return getDmatDetailsForDay(new LocalDate(), model);
	}

	@RequestMapping(method = RequestMethod.GET, params = "day")
	public String getDmatDetailsForDay(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate day, Model model)
			throws SQLException {
		model.addAttribute(service.getDmatDetailsForDay(day));
		return "dmat/dmatInput";
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Long addDetail(@Valid DmatDetail detail) throws SQLException {
		return service.addDmatDetail(detail);
	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public @ResponseBody
//	void updateDetail(@Valid DmatDetail detail) throws SQLException {
//		service.updateDmatDetail(detail);
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDetail(@PathVariable Long id) throws SQLException {
		service.deleteDmatDetail(id);
	}
}
