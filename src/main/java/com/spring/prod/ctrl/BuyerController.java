package com.spring.prod.ctrl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.prod.bean.ProductBean;
import com.spring.prod.dao.ProductDao;
import com.spring.prod.dao.UserDao;
import com.spring.prod.entity.Product;
import com.spring.prod.entity.Purchase;
import com.spring.prod.entity.User;
import com.spring.prod.service.PurchaseService;

@Controller
public class BuyerController {

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;

	@RequestMapping("/buyer")
	public String buyer(Model m, HttpSession sess) {
		m.addAttribute("pageTitle", "Buyer Page");
		List<ProductBean> products = productDao.getAllProducts();
		User auth = (User) sess.getAttribute("auth");
		sess.setAttribute("auth", auth);
		m.addAttribute("products", products);
		return "buyer";
	}

	@RequestMapping("/addToCart/{id}")
	public String addToCart(@ModelAttribute Purchase purchase, Model m, @PathVariable("id") int pId, HttpSession sess,
			HttpServletRequest req) {

		System.out.println("In purchase ctrl");
		Purchase pur = new Purchase();
		try {
			User auth = (User) sess.getAttribute("auth");
			auth.getId();
			System.out.println("Auth Id " + auth.getId());
			List<Purchase> cartlist = new ArrayList<>();

			Product product = productDao.getProductById(pId);
			product.setId(pId);
			product.getUser().getId();
			product.getProdCostPrice();

			pur.setProduct(product);
			List<Purchase> cart_list = (List<Purchase>) sess.getAttribute("cart-list");
			if (cart_list == null) {
				cartlist.add(pur);
				pur.setUser(auth);
				pur.setTotalUnit(1);
				pur.setTotalCostPrice(pur.getProduct().getProdCostPrice() * pur.getTotalUnit());

				sess.setAttribute("cart", cartlist);
				sess.setAttribute("msg", "Product Added To Cart");
				m.addAttribute("msg", "Product Added To Cart");

				return "cart";
			}
		} finally {
			System.out.println("Purchasing.....");
		}
		return "buyer";
	}

	@RequestMapping("/orderNow")
	public String buyNow(@ModelAttribute Purchase purchase, Model m, @RequestParam("id") Integer pId, HttpSession sess,
			HttpServletRequest req) throws ParseException {
		
		//Buyer Id
		User user = (User) sess.getAttribute("auth");
		user.getId();
		user.getBalance();

		Product prod = productDao.getProductById(pId);
		prod.setId(pId);
		prod.getUser().getId();

		//Seller Id
		User u = new User();
		u.setId(prod.getUser().getId());
		System.out.println("Seller id in ctrl: "+u.getId());
		User uById = userDao.getUserById(u.getId());
		System.out.println("Seller Bal Before: "+uById.getBalance());
		System.out.println("Qty: "+purchase.getTotalUnit());
		System.out.println("Sell Price: "+prod.getProdSellPrice());
		uById.setBalance(uById.getBalance() + (purchase.getTotalUnit() * prod.getProdSellPrice()));
		System.out.println("Seller Bal: "+uById.getBalance());
		user.setBalance(user.getBalance() - (purchase.getTotalUnit() * prod.getProdSellPrice()));

		prod.setStockUnit(prod.getStockUnit() - purchase.getTotalUnit());
		purchase.setProduct(prod);
		purchase.setUser(user);
		purchase.setTotalCostPrice(purchase.getProduct().getProdCostPrice() * purchase.getTotalUnit());
		purchase.setPurchaseDt(new Date());
		System.out.println(purchase.getPurchaseDt());
		purchase.setUser1(u);

		if ((user.getBalance() - prod.getProdSellPrice()) >= 0) {
			purchaseService.insertOrder(purchase);
			purchaseService.updateProduct(prod);
			purchaseService.updateSellBalance(uById);
			purchaseService.updateBuyerBal(user);

			sess.setAttribute("msg", "Product Bought");
			return "redirect:/viewPurchase";

		} else {
			sess.setAttribute("msg", "You don't have enough balance to buy the Product");
			return "redirect:/viewPurchase";
		}
	}

	@RequestMapping("/viewPurchase")
	public String viewPurchase(Model m, HttpSession sess) {
		User user = (User) sess.getAttribute("auth");
		if (user != null) {
			List<Purchase> purchases = purchaseService.getAllPurchases();
			m.addAttribute("orders", purchases);
		}

		return "orders";
	}

	@RequestMapping("/cancelOrder/{purchaseId}/{id}")
	public String cancelOrder(@ModelAttribute Purchase purchase, Model m, @PathVariable("purchaseId") String pId,
			@PathVariable("id") String prodId, HttpServletRequest req, HttpSession sess) {
		try {
			
			//Buyer Id
			User user = (User) sess.getAttribute("auth");
			System.out.println("User In cancel "+user);
			user.getId();
			System.out.println("User id In cancel "+user.getId());
			user.getBalance();
			System.out.println("User In cancel "+user.getBalance());
			
			
			int proId = Integer.parseInt(prodId);
			Product prod = productDao.getProductById(proId);
			prod.setId(proId);
			System.out.println(proId);
			
			//Seller Id
			User u = new User();
			u.setId(prod.getUser().getId());
			
			int purId = Integer.parseInt(pId);
			Purchase prId = purchaseService.getPurchaseById(purId);
			prId.setPurchaseId(purId);			
			prId.getTotalUnit();
			
			prod.setStockUnit(prod.getStockUnit() + prId.getTotalUnit());			
			user.setBalance(user.getBalance() + (prId.getTotalUnit() * prod.getProdSellPrice()));			
			u.setBalance(u.getBalance() - (prId.getTotalUnit() * prod.getProdSellPrice()));			
			purchase.setProduct(prod);
			
			if (pId != null) {
				purchaseService.cancelOrder(purId);
				purchaseService.updateProduct(prod);
				purchaseService.updateSellBalance(u);
				purchaseService.updateBuyerBal(user);
				req.setAttribute("msg", "Order Cancelled");
			}
			return "redirect:/viewPurchase";
		} catch (Exception e) {
			req.setAttribute("msg", "Could Not Cancel Order");
			e.printStackTrace();
			System.out.println("Exception " + e);
			return "redirect:/viewPurchase";
		}

	}

	@RequestMapping("/summary")
	public String billBuy(@ModelAttribute Purchase purchase, Model m, HttpSession sess) {
		User user = (User) sess.getAttribute("auth");
		if (user != null) {
			List<Purchase> purchases = purchaseService.getAllPurchases();
			m.addAttribute("orders", purchases);
		}
		return "summary";

	}

}
