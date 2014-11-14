package ustc.app.shopping.service.impl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qsh.shopping.model.SaleItem;
import com.qsh.shopping.model.SalesOrder;
import com.qsh.shopping.service.SalesOrderService;
import com.qsh.shopping.util.ordercode.FileEveryDaySerialNumber;
import com.qsh.shopping.util.ordercode.QshSerialNumber;

public class TestSalesOrderServiceImpl {
	@Test
	public void testAdd(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		
		SalesOrderService salesOrderService = (SalesOrderService)ctx.getBean("salesOrderService");
		
		SalesOrder order = new SalesOrder();
		QshSerialNumber serial = new FileEveryDaySerialNumber(5,"EverDayNumberSerial.dat");
		order.setOrderCode(serial.getSerialNumber());
		order.setUser("测试");
		order.setAddr("苏州");
		order.setPhone("4008208820");
		
		order.setOdate(new Timestamp(System.currentTimeMillis()));
		
		Set<SaleItem> saleItems = new HashSet<SaleItem>();
		SaleItem si1 = new SaleItem();
		si1.setProductName("华为");
		si1.setPrice(1500);
		si1.setNumber(33);
		SaleItem si2 = new SaleItem();
		si2.setProductName("小米");
		si2.setPrice(1300);
		si2.setNumber(55);
		saleItems.add(si1);
		saleItems.add(si2);
		
		
		order.setSaleItems(saleItems);
		if(salesOrderService.add(order)){
			System.out.println("定单保存成功！");
		}else{
			System.out.println("定单保存不成功！");
		}
	}
	
	@Test
	public void testFindAll(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		
		SalesOrderService salesOrderService = (SalesOrderService)ctx.getBean("salesOrderService");
		
		List<SalesOrder> list = salesOrderService.findALL();
	
		System.out.println(list.size());
		for(SalesOrder order: list){
			System.out.println("定单号："+order.getOrderCode());
			Set<SaleItem> items =order.getSaleItems();
			for(SaleItem item : items){
				System.out.println("定单所对应产品名称："+item.getProductName());
			}
		}
	}
	
	@Test
	public void testFindAllPage(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		
		SalesOrderService salesOrderService = (SalesOrderService)ctx.getBean("salesOrderService");
		
		List<SalesOrder> list = salesOrderService.findALL(0,3);
	
		System.out.println(list.size());
		for(SalesOrder order: list){
			System.out.println("定单号："+order.getOrderCode());
			Set<SaleItem> items =order.getSaleItems();
			for(SaleItem item : items){
				System.out.println("定单所对应产品名称："+item.getProductName());
			}
		}
	}
	
	@Test
	public void testFindByOderCode(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		
		SalesOrderService salesOrderService = (SalesOrderService)ctx.getBean("salesOrderService");
		
		String orderCode="dd";
		List<SalesOrder> list = salesOrderService.findByOderCode(orderCode);
	
		System.out.println(list.size());
		for(SalesOrder order: list){
			System.out.println("定单号："+order.getOrderCode());
			Set<SaleItem> items =order.getSaleItems();
			for(SaleItem item : items){
				System.out.println("定单所对应产品名称："+item.getProductName());
			}
		}
	}
	
	@Test
	public void testRemove(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		
		SalesOrderService salesOrderService = (SalesOrderService)ctx.getBean("salesOrderService");
		
		SalesOrder order = new SalesOrder();
		order.setId(1);
		if(salesOrderService.remove(order)){
			System.out.println("定单作废成功！");
		}else{
			System.out.println("定单作废不成功！");
		}
		
	}
	
	@Test 
	public void testVerify(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		
		SalesOrderService salesOrderService = (SalesOrderService)ctx.getBean("salesOrderService");
		
		int id=1;
		if(salesOrderService.verify(id)){
			System.out.println("定单审核成功！");
		}else{
			System.out.println("定单审核不成功！");
		}
	}
}
