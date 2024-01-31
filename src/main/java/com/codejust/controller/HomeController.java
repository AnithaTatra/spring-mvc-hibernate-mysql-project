package com.codejust.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codejust.model.Product;
import com.codejust.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	ProductService productService;

	@Autowired
	MessageSource messageSource;

	/**
	 * This Method will provide way to Add New Product
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newProductForm(ModelMap model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("edit", false);
		return "addProductForm";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public ModelAndView saveProduct(@Valid Product product, BindingResult result, ModelMap model) {
		ModelAndView mv = new ModelAndView();
		Integer price = product.getPrice();
		if (price == null) {
			model.addAttribute("product", product);
			model.addAttribute("error", "Price must be Numeric.");
			mv.setViewName("addProductForm");
		} else {
			productService.saveProduct(product);
			List<Product> list = productService.listAllProducts();
			model.addAttribute("allproducts", list);
			model.addAttribute("success", "Product " + product.getName() + " added successfully.");
			mv.setViewName("allProducts");
		}
		return mv;
	}

	/**
	 * This Method will list All existing Products
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public ModelAndView welcome(ModelMap model) {
		ModelAndView mv = new ModelAndView();
		List<Product> list = productService.listAllProducts();
		model.addAttribute("allproducts", list);
		mv.setViewName("allProducts");
		return mv;
	}

	/**
	 * This Method will provide the way to update an existing Product
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.GET)
	public String editProduct(@PathVariable Long id, ModelMap model) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("edit", true);
		return "addProductForm";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating Product in database. It also validates the user input
	 * 
	 * @param Product
	 * @param result
	 * @param model
	 * @param ssn
	 * @return
	 */
	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.POST)
	public ModelAndView updateProduct(@Valid Product product, BindingResult result, ModelMap model,
			@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		Integer price = product.getPrice();
		if (price == null) {
			model.addAttribute("product", product);
			model.addAttribute("edit", true);
			model.addAttribute("error", "Price must be Numeric.");
			mv.setViewName("addProductForm");
		} else {
			productService.update(id, product);
			List<Product> list = productService.listAllProducts();
			model.addAttribute("allproducts", list);
			model.addAttribute("success", "Product " + product.getName() + " updated successfully.");
			mv.setViewName("allProducts");
		}
		return mv;
	}

	/**
	 * This method will Delete a Product by Id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/delete-{id}" }, method = RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable Long id, ModelMap model) {
		ModelAndView mv = new ModelAndView();
		Product product = productService.getProduct(id);
		productService.delete(id);
		List<Product> list = productService.listAllProducts();
		model.addAttribute("allproducts", list);
		model.addAttribute("success", "Product " + product.getName() + " deleted successfully.");
		mv.setViewName("allProducts");
		return mv;
	}

}
