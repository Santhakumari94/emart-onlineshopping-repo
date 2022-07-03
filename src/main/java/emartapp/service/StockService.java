package emartapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emartapp.dao.StockDAO;
import emartapp.model.Stock;

@Service
public class StockService 
{
	@Autowired
	private StockDAO stockDAO;
	
	public void save(Stock stock) {
		stockDAO.save(stock);
	}
	
	public Stock findByStockId(int stockId) {
		return stockDAO.findByStockId(stockId);
	}
	
	public void delete(int stockId) {
		stockDAO.deleteById(stockId);
	}
	
	public void updateQty(int stockId, String action, int qty) throws Exception  {
		
		 Stock stock = stockDAO.findByStockId(stockId);
		 
		 if(null!=action && action.equalsIgnoreCase("add")) {
			 System.out.println("stock.getStockQuantity()+qty "+(stock.getStockQuantity()+qty));
			 stock.setStockQuantity(stock.getStockQuantity()+qty);
		 }else {
			 if(stock.getStockQuantity()<=0) {
				 throw new Exception("Out of stock");
			 }
			 System.out.println("stock.getStockQuantity(-qty "+(stock.getStockQuantity()-qty));
			 stock.setStockQuantity(stock.getStockQuantity()-qty);
		 }
		stockDAO.save(stock);
	}
	
}
