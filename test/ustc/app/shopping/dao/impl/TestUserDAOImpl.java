package ustc.app.shopping.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qsh.shopping.dao.ProductDAO;
import com.qsh.shopping.dao.UserDAO;
import com.qsh.shopping.dao.impl.UserDAOImpl;
import com.qsh.shopping.model.User;

public class TestUserDAOImpl {

	@Test
	public void testSave(){
		ApplicationContext cfx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		
	UserDAO dao = (UserDAO)cfx.getBean("userDao");
		//UserDAO dao = new UserDAOImpl();
		User u = new User();
		u.setName("测试");
		u.setPassword("123");
		u.setIP("127.0.0.1");
		//u.setRegDate()
		System.out.println("a user have saved,this is id:"+dao.save(u).getId());
		
	}
	
	@Test
	public void testCheckUserName(){
		UserDAO dao = new UserDAOImpl();
		if(dao.checkUserName("测试")){
			System.out.println("此用户名已存在！");
		}else{
			System.out.println("此用户名不存在！");
		}
	}
	
	@Test
	public void testDelete(){
		UserDAO dao = new UserDAOImpl();
		if(dao.delete(1)){
			System.out.println("删除成功！");
		}else{
			System.out.println("删除失败！");
		}
	}
	
	@Test
	public void testFindAll(){
		UserDAO dao = new UserDAOImpl();
		List list = dao.findAll();
		for(int i=0;i<list.size();i++){
			User u=(User)list.get(i);
			System.out.println("username:"+u.getName());
		}
	}
	
	@Test
	public void testLogin(){
		UserDAO dao = new UserDAOImpl();
		if(null!=dao.login("测试", "1234")){
			System.out.println("登陆成功！");
		}else{
			System.out.println("登陆失败！");
		}
	}
	
	@Test
	public void testUpdate(){
		ApplicationContext cfx = new ClassPathXmlApplicationContext("spring_xml_config/beans.xml");
		
		UserDAO dao = (UserDAO)cfx.getBean("userDao");
		//UserDAO dao = new UserDAOImpl();
		
		User user = new User();
		user.setId(6);
		user.setName("test");
		if(dao.update(user)){
			System.out.println("修改成功！");
		}else{
			System.out.println("修改失败！");
		}
	}
}
