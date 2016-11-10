package mx.tecno.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSingleton {
	
	private static SessionFactory instance =  new Configuration().configure().buildSessionFactory();
	
	private HibernateSingleton(){}

	public static SessionFactory getInstance() {
		return instance;
	}

}
