package com.jck.mpa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jck.mpa.model.Student;
import com.jck.mpa.model.StudentFormValidator;
import com.jck.mpa.service.StudentServiceImpl;

@Controller
public class StudentController {

	@Autowired
	StudentServiceImpl studentServiceImpl;
	
	@Autowired
	StudentFormValidator studentFormValidator;
	
	//Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(studentFormValidator);
		 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		    dateFormat.setLenient(false);
		   // true passed to CustomDateEditor constructor means convert empty String to null
		   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
	/**
	 * Initial Page for the student list
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String students(ModelMap model) {
		
		List<Student> students =  studentServiceImpl.getAllStudents();
		model.addAttribute("students", students);

		return "studentList";
	}
	
	/**
	 * Creations of student profile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String newStudent( ModelMap model) {
		Student studentForm = new Student();
		
		model.addAttribute("studentForm", studentForm);
		model.addAttribute("title", "New Student");		
		//did not use tile since this is just a small application
		return "student";
	}
	
	
	/**
	 * Creations of student profile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/iStudent", method = RequestMethod.POST)
	public String iStudent(@ModelAttribute("studentForm") @Validated Student student,  
			BindingResult result, ModelMap model) {
	
		if(result.hasErrors()) {
			
			if(student.getId() == 0) {
				model.addAttribute("title", "New Student");
			}else {
				model.addAttribute("title", "Update Student");
			}

			return "student";
			
		}
		
		String msg = null;
		if(student.getId() == 0) {
			if(studentServiceImpl.insertStudent(student) !=0 ) {
				msg = "Profile was successfully saved";
			}
		}else {
			if(studentServiceImpl.updateStudent(student) !=0 ) {
				msg = "Profile was successfully updated";
			}
		}
		
		
		model.addAttribute("msg", msg);
		List<Student> students =  studentServiceImpl.getAllStudents();
		model.addAttribute("students", students);
		
		return "studentList";
	}

	//SHOW PROFILE
	@RequestMapping(value = "/student/{id}/update", method = RequestMethod.GET)
	public String showStudentProfile(@PathVariable("id") int id, final ModelMap model) {
		
		Student student = studentServiceImpl.getStudent(id);
		model.addAttribute("studentForm", student);
		model.addAttribute("title", "Update Student");		
		//did not use tile since this is just a small application
		return "student";
		
	}
}
