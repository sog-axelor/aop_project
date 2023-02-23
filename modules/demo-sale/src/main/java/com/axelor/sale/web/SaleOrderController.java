package com.axelor.sale.web;

import java.time.LocalDate;

import javax.inject.Inject;

import com.axelor.db.JpaSupport;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.sale.db.Order;
import com.axelor.sale.db.OrderStatus;
import com.axelor.sale.service.SaleOrderService;

public class SaleOrderController extends JpaSupport{
	
	@Inject private SaleOrderService service;
	
	public void onConfirm(ActionRequest req,ActionResponse res) {			//1 . COmfirm method
		Order order = req.getContext().asType(Order.class);
		
		res.setReadonly("orderDate", order.getConfirmed());
		res.setReadonly("confirmDate", order.getConfirmed());
		
		if(order.getConfirmed()==Boolean.TRUE && order.getConfirmDate()==null) {
			res.setValue("comfirmDate", LocalDate.now());
		}
		
		if(order.getConfirmed() == Boolean.TRUE) {
			res.setValue("status", OrderStatus.OPEN);
		}
		else if(order.getStatus() == OrderStatus.OPEN) {
			res.setValue("status",OrderStatus.DRAFT);
		}		
	}
	
	
	
	public void calculate(ActionRequest req,ActionResponse res) {
		Order order = req.getContext().asType(Order.class);
		order = service.calculate(order);
		
		res.setValue("amount", order.getAmount());
		res.setValue("taxAmount", order.getTaxAmount());
		res.setValue("totalAmount", order.getTotalAmount());
	}

	
	
}
