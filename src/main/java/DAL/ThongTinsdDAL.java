/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.ThanhVienBLL;
import DTO.ThanhVien;
import DTO.ThietBi;
import DTO.ThongTinsd;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author minht
 */
public class ThongTinsdDAL {
     private static SessionFactory factory;
    Session session;

    public ThongTinsdDAL() {
        session = HibernateUtils.getSessionFactory().openSession();
    }
    
     public List loadThongTinsd() {
        List<ThongTinsd> tb;
        session.beginTransaction();
        tb = session.createQuery("FROM ThongTinsd", ThongTinsd.class).list();
        session.getTransaction().commit();       
        return tb;
    }
    
    public int getmaTTfrommaTV(int matv){
        int kq=0;
        List<ThongTinsd> tv = this.loadThongTinsd();
        for(ThongTinsd ttsd: tv){
            if(matv == ttsd.getMaTV().getMaTV() && ttsd.getTGVao()==null)
                kq=ttsd.getMaTT();
        }
        return kq;
    }
    public ThongTinsd getTTSDfrommaTT(int maTT){
        ThongTinsd temp = new ThongTinsd();
        List<ThongTinsd> tv = this.loadThongTinsd();
        for(ThongTinsd ttsd: tv){
            if(maTT == ttsd.getMaTT()){
                temp=ttsd;
                break;
            }
            
        }
        return temp;

    }
    public boolean createNew(ThongTinsd ttsd){
        session.beginTransaction();
        session.save(ttsd);
        session.getTransaction().commit();
        return true;
    }
    
    public boolean update(ThongTinsd ttsd){
        session.beginTransaction();
        session.update(ttsd);
        session.getTransaction().commit();
        return true;
    } 
    
    public boolean delete(int maTT) {
    	//session = HibernateUtils.getSessionFactory().openSession();
    	session.beginTransaction();
    	ThongTinsd xoaTT = session.get(ThongTinsd.class, maTT);
        if (xoaTT != null) {
            session.delete(xoaTT);
            session.getTransaction().commit();
            //session.close();
            return true;
        } else {
            session.getTransaction().rollback(); 
            //session.close();
            return false; 
        }
    }
}
