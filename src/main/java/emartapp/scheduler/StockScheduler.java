//package emartapp.scheduler;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import emartapp.model.Cart;
//import emartapp.service.CartService;
//import emartapp.service.StockService;
//
//@Configuration
//@EnableScheduling	
//public class StockScheduler {
//	@Autowired
//	private StockService stockService;
//	@Autowired
//	private CartService cartService;
//
//	@Scheduled(fixedDelay = 1000*60)
//	public void scheduleFixedDelayTask() {
//		int time = 1000 * 60;
//	    long crtTime = System.currentTimeMillis();
//	    
//	    Cart c = 	cartService.getCart();
//	    c.getProducts();
//	    if (c.equals("")){
//	    	c.getCartId();
//	    
//	}
//	
//	}
//}
