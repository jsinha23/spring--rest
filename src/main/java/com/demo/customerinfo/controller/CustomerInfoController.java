package com.demo.customerinfo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.customerinfo.model.CustomerModel;
import com.demo.customerinfo.serviceImpl.CustomerServiceImpl;
import com.demo.customerinfo.serviceImpl.DepartmentsServiceImpl;

@RequestMapping(value = "customers")
@Controller
public class CustomerInfoController {

	@Autowired
	private DepartmentsServiceImpl deparmentService;
	@Autowired
	private CustomerServiceImpl customerService;

	@GetMapping(value = { "/add", "/" })
	public String addCustomers(Model model) {
		model.addAttribute("pageTitle", "Customers | Add");
		model.addAttribute("departmentsList", deparmentService.getList());
		return "customers/add-customer";
	}

	@PostMapping(value = "/add")
	public String addCustomers(@Valid @ModelAttribute CustomerModel customers, RedirectAttributes redirectAttr,
			BindingResult result) {
		String msg = "";
		if (result.hasErrors()) {
			FieldError error = null;
			for (Object obj : result.getAllErrors()) {
				error = (FieldError) obj;
				msg += error.getDefaultMessage();
			}
		}
		if (msg.length() == 0) {
			try {
				
					customerService.save(customers);
					redirectAttr.addFlashAttribute("status", "success");
					redirectAttr.addFlashAttribute("message", "Customer information successfully saved");
				
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			redirectAttr.addFlashAttribute("status", "error");
			redirectAttr.addFlashAttribute("message", msg);
		}
		return "redirect:/customers/add";
	}

	@GetMapping(value = "view")
	public String list(Model model) {
		model.addAttribute("pageTitle", "Customers | View");
		model.addAttribute("customerList", customerService.getList());
		return "customers/view-customers";
	}

	@GetMapping(value = "/edit/{customerId}")
	public String edit(@PathVariable Integer customerId, Model model, RedirectAttributes redirectAttr) {
		try {
			if (customerId > 0) {
				CustomerModel customer = customerService.findByCustomerId(customerId);
				if (customer != null) {
					model.addAttribute("customer", customer);
					model.addAttribute("departmentsList", deparmentService.getList());
				} else {
					redirectAttr.addFlashAttribute("message", "No customer was matched");
					return "redirect:/customers/view";
				}
			} else {
				redirectAttr.addFlashAttribute("message", "No reference was found");
				return "redirect:/customers/view";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("pageTitle", "Customers | Update");
		return "customers/edit-customer";
	}

	@PostMapping(value = "/update")
	public String update(@Valid @ModelAttribute CustomerModel customer, RedirectAttributes redirectAttr,
			BindingResult result) {
		String msg = "";
		redirectAttr.addFlashAttribute("status", "error");
		try {
			if (customer != null && customer.getCustomerId() > 0) {
				if (result.hasErrors()) {
					FieldError error = null;
					for (Object obj : result.getAllErrors()) {
						error = (FieldError) obj;
						msg += error.getDefaultMessage();
					}
				}
				if (msg.length() == 0) {
					CustomerModel oldModel = customerService.findByCustomerId(customer.getCustomerId());
					if (!oldModel.getRole().equalsIgnoreCase(customer.getRole())) {
						if (customerService.findByRoll(customer.getRole()) != null) {
							redirectAttr.addFlashAttribute("message", customer.getRole() + " Role already exist");
							return "redirect:/customers/edit/" + customer.getCustomerId();
						}
					}
					customerService.save(customer);
					redirectAttr.addFlashAttribute("status", "success");
					redirectAttr.addFlashAttribute("message", "Customer Info Successfully updated");
					return "redirect:/customers/view";
				} else {
					redirectAttr.addFlashAttribute("message", msg);
					return "redirect:/customers/edit/" + customer.getCustomerId();
				}
			} else {
				redirectAttr.addFlashAttribute("message", "No reference was found");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/customers/view";
	}

	@GetMapping(value = "/delete/{customerId}")
	public String delete(@PathVariable Integer customerId, RedirectAttributes redirectAttr) {
		if (customerId != null) {
			try {
				customerService.deleteByCustomerId(customerId);
				redirectAttr.addFlashAttribute("status", "success");
				redirectAttr.addFlashAttribute("message", "Customer Information successfully deleted");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "redirect:/customers/view";
	}
}
