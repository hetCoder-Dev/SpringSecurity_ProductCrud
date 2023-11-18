package com.spring.prod.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.prod.bean.ProductBean;
import com.spring.prod.dao.ProductDao;
import com.spring.prod.entity.Product;
import com.spring.prod.entity.User;

@Controller
public class SellerController {
	
	@Autowired
	private ProductDao productDao;

	@RequestMapping("/seller")
	public String seller(Model m) {
		m.addAttribute("pageTitle", "Seller Page");
		return "seller";
	}
	
	@RequestMapping("/product")
	public String productForm(Model m, HttpSession sess) {
		m.addAttribute("pageTitle", "Product Form");
		return "addProduct";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute ProductBean product, Model m) {
		product.setIsDeleted((byte) 1);
		productDao.createProduct(product.convertProduct());
		m.addAttribute("prod", "Product registered successfully");
		return "redirect:/viewProducts";
		
	}
	
	@RequestMapping("/viewProducts")
	public String viewProducts(Model m, HttpSession sess, HttpServletRequest req) {
		User auth = (User)sess.getAttribute("auth");
		List<ProductBean> product = productDao.getAllProducts();
		m.addAttribute("pageTitlte", "View Products");
		m.addAttribute("products", product);
		m.addAttribute("auth", auth);
		return "viewProduct";

	}
	
	@RequestMapping("/editProduct/{pId}")
	public String editProducts(Model m, @PathVariable("pId") int pId) {
		m.addAttribute("pageTitlte", "Edit Products");
		Product product = productDao.getProductById(pId);
		m.addAttribute("prod", product);
		return "updateProducts";
		
	}
	
	@PostMapping("/editProduct")
	public String updateProduct(@ModelAttribute ProductBean product, Model m, @RequestParam("user") String sellId, HttpServletRequest req) {
		m.addAttribute("pageTitlte", "Edit Products");
			
		product.setUser(sellId);
		product.setIsDeleted((byte) 1);
		productDao.updateProduct(product);
		m.addAttribute("updated", "Product updated!!");		
		return "redirect:/viewProducts";
		
	}
	
	@RequestMapping("/deleteProduct/{pId}")
	public String deleteProduct(@PathVariable("pId") int id) {
		
		productDao.removeProduct(id);
		return "redirect:/viewProducts";
		
	}
	
	@RequestMapping("/sellSummary")
	public String viewSummary(Model m, HttpSession sess, HttpServletRequest req) {
		List<ProductBean> product =productDao.getAllProducts();
		User auth = (User)sess.getAttribute("auth");
		m.addAttribute("pageTitlte", "View Products");
		m.addAttribute("products", product);
		m.addAttribute("auth", auth);
		return "sellSummary";
		
	}
	
}
