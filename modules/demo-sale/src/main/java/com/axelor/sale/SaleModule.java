package com.axelor.sale;

import com.axelor.app.AxelorModule;
import com.axelor.contact.service.HelloServiceImpl;
import com.axelor.sale.service.HelloServiceSaleImpl;

public class SaleModule extends AxelorModule {
	@Override
	protected void configure() {
		bind(HelloServiceImpl.class).to(HelloServiceSaleImpl.class);
	}
}
