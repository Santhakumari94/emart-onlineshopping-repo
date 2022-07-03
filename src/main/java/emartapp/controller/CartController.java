package emartapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import emartapp.model.Cart;
import emartapp.model.Product;
import emartapp.service.BuyerService;
import emartapp.service.CartService;
import emartapp.service.ProductService;
import emartapp.service.SellerService;
import emartapp.service.StockService;

@Controller
@RequestMapping("/cart")
public class CartController 
{
	@Autowired
	private CartService cartService;
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockService stockService;
	
	@GetMapping("")
	public String getCartProducts(Model model)
	{
		Cart theCart = cartService.getCart();
		model.addAttribute("theCart", theCart);
		model.addAttribute("isBuyer", buyerService.isBuyer());
		model.addAttribute("isSeller", sellerService.isSeller());
		return "cart";
	}
	
	@GetMapping("/add/{productId}")
	public ModelAndView addProductToCart(@PathVariable("productId") int productId)
	{
		ModelAndView mac = new ModelAndView();
		mac.setViewName("redirect:/cart");
		Product theProduct = productService.findByProductId(productId);
		Cart theCart = cartService.getCart();
		theCart.addProductToCart(theProduct);
		try {
			stockService.updateQty(productId, "", 1);
		} catch (Exception e) {
			mac.addObject("error", "Out Of Stock");
			return mac;
		}
		cartService.save(theCart);
		return mac;
	}
	
	@GetMapping("/delete/{productId}")
	public String deleteProductFromCart(@PathVariable("productId") int productId)
	{
		Product theProduct = productService.findByProductId(productId);
		Cart theCart = cartService.getCart();
		theCart.removeProductFromCart(theProduct);
		try {
			stockService.updateQty(productId, "add", 1);
		} catch (Exception e) {
			
		}
		cartService.save(theCart);
		return "redirect:/cart";
	}
}
