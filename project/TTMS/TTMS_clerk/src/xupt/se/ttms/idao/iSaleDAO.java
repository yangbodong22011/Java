/**
 * 
 */
package src.xupt.se.ttms.idao;
import src.xupt.se.ttms.model.Ticket;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface iSaleDAO {
	public boolean doSale(List<Ticket> tickets);
}
