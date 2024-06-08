package DAL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import DTO.ThanhVien;

public class ThanhVienDAL {
//	private static SessionFactory factory;
	private Session session;
	
	public ThanhVienDAL() {
		session = HibernateUtils.getSessionFactory().openSession();
	}
	
	public List<ThanhVien> getAllThanhVien() {
	    List<ThanhVien> thanhvien;
	    session.beginTransaction();
	    thanhvien = session.createQuery("FROM ThanhVien", ThanhVien.class).list();
	    session.getTransaction().commit();
	    return thanhvien;
	}

}
