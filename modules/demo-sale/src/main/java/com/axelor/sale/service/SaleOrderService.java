package com.axelor.sale.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.axelor.common.ObjectUtils;
import com.axelor.sale.db.Order;
import com.axelor.sale.db.OrderLine;
import com.axelor.sale.db.Tax;

public class SaleOrderService {

	/*
	 * public Order calculate(Order order) {
	 * 
	 * BigDecimal amount = BigDecimal.ZERO; BigDecimal taxAmount = BigDecimal.ZERO;
	 * 
	 * if (ObjectUtils.isEmpty(order.getItems())) { return order; }
	 * 
	 * if (!ObjectUtils.isEmpty(order.getItems())) { for (OrderLine item :
	 * order.getItems()) { BigDecimal value = item.getPrice().multiply(new
	 * BigDecimal(item.getQuantity())); BigDecimal taxValue = BigDecimal.ZERO;
	 * 
	 * if (!ObjectUtils.isEmpty(item.getTaxes())) { for (Tax tax : item.getTaxes())
	 * { taxValue = taxValue.add(tax.getRate().multiply(value)); } }
	 * 
	 * amount = amount.add(value); taxAmount = taxAmount.add(taxValue); } }
	 * 
	 * order.setAmount(amount.setScale(4, RoundingMode.HALF_UP));
	 * order.setTaxAmount(taxAmount.setScale(4, RoundingMode.HALF_UP));
	 * order.setTotalAmount(amount.add(taxAmount).setScale(2,
	 * RoundingMode.HALF_UP));
	 * 
	 * return order; }
	 */

	public Order calculate(Order order) {
		BigDecimal amount = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;

		if (ObjectUtils.isEmpty(order.getItems())) {
			for (OrderLine item : order.getItems()) {
				BigDecimal value = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
				BigDecimal taxValue = BigDecimal.ZERO;

				if (!ObjectUtils.isEmpty(item.getTaxes())) {
					for (Tax tax : item.getTaxes()) {
						taxValue = taxValue.add(tax.getRate().multiply(value));
					}
				}
					amount = amount.add(value);
					taxAmount = taxAmount.add(taxValue);
		}
		}
		order.setAmount(amount.setScale(4, RoundingMode.HALF_UP));
		order.setTaxAmount(taxAmount.setScale(4, RoundingMode.HALF_UP));
		order.setTotalAmount(amount.add(taxAmount).setScale(4, RoundingMode.HALF_UP));

		return order;

	}

}
