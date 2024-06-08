package DAL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import DTO.XuLy;

public class XuLyDAL {
	
private static SessionFactory factory;
	Session session;
public XuLyDAL() {
	session = HibernateUtils.getSessionFactory().openSession();
}
public List loadXuLy() {
	List<XuLy> xuly;
	session.beginTransaction();
	xuly = session.createQuery("FROM XuLy", XuLy.class).list();
	session.getTransaction().commit();
	return xuly;
}
public boolean addXuLy(XuLy c)
{
    session.beginTransaction();
    session.save(c);
    session.getTransaction().commit();
    return true;
}
public boolean updateXuLy(XuLy c)
{
    session.beginTransaction();
    session.update(c);
    session.getTransaction().commit();
    return true;
}
public boolean deleteXuLy(int maXL) {
    session.beginTransaction();
    XuLy xuLyToDelete = session.get(XuLy.class, maXL);
    if (xuLyToDelete != null) {
        session.delete(xuLyToDelete);
        session.getTransaction().commit();
        return true;
    } else {
        session.getTransaction().rollback(); // Quay lại trạng thái trước khi bắt đầu giao dịch
        return false; // Trả về false nếu không tìm thấy XuLy với maXL tương ứng
    }
}

}


