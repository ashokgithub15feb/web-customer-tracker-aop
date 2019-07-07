package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//need to inject the customer dao
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(Model theModel)
	{
		//get the customer from the dao
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customer to the model
		theModel.addAttribute("customer", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		//create model attribute to build from data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		//save the customer using aur service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	/*
	 * step-by-step
	 * 1- update list-customer.jsp ---> New "Update" link
	 * 2- create customer-form.jsp ----> pre-populate the form 
	 * 3- process form data ---> controller -> service -> DAO
	 */
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel)
	{
		// get the customer from the database
		Customer theCustomer = customerService.getCustomers(theId);
		//set the customer as a model attribute to pre-populated the form
		theModel.addAttribute("customer", theCustomer);
		//send over to our form
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public void delete(@RequestParam("customerId") int theId)
	{
		//delete the customer
		customerService.deleteCustomer(theId);
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel)
	{
		//search customer from the service 
		
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		theModel.addAttribute("customer", theCustomers);
		
		return "list-customers";
	}
}

//	0						1								2				3		4		5	                    
// web browser -> dispatcher servlet(Front controller) -> Controller -> Service -> DAO -> Database
//															  |
//															  |6
//															  |
//web browser	<------------------------------------------JSP pages