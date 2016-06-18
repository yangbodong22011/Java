package src.xupt.se.ttms.service;

import java.util.List;

import src.xupt.se.ttms.idao.DAOFactory;
import src.xupt.se.ttms.idao.iSaleDAO;
import src.xupt.se.ttms.model.Ticket;

public class SaleSrv {
	private iSaleDAO stuDAO=DAOFactory.creatSaleDAO();

	public boolean doSale(List<Ticket> tickets){
		return stuDAO.doSale(tickets);
	}
}
