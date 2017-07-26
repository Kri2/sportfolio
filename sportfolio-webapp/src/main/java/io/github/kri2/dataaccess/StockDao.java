package io.github.kri2.dataaccess;

import io.github.kri2.domain.Stock;
/**
 * This interface is providing stock data into Stock class
 * different sources could be used
 * @author kriz
 *
 */

public interface StockDao {
	public void setStock(Stock stock);
	public Stock getStock(String ticker);
}
