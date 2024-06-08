/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.ThietBi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author huy
 */
public class ThietBiDAL {
//    private static SessionFactory factory;
    static Session session;

    public ThietBiDAL() throws HibernateException {
        session = HibernateUtils.getSessionFactory().openSession();
    }
    
    public List<ThietBi> loadThietBi() {
        List<ThietBi> tb;
        session.beginTransaction();
        tb = session.createQuery("FROM ThietBi", ThietBi.class).list();
        session.getTransaction().commit();
        return tb;
    }
    public ArrayList<ThietBi> listThietBi() {
        ArrayList<ThietBi> tb = new ArrayList<ThietBi>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List devices = session.createQuery("From ThietBi").list();
            for (Iterator iterator = devices.iterator(); iterator.hasNext();) {
                ThietBi dp = (ThietBi) iterator.next();
                tb.add(dp);

            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return tb;
        }
    }
    public ArrayList<Integer> getlistmatb() {
        ArrayList<ThietBi> tb = this.listThietBi();
        ArrayList<Integer> matb = new ArrayList();
        for (ThietBi t : tb) {
            matb.add(t.getMaTB());
        }
        return matb;
    }
    public String getInfo(int matb) {
        ArrayList<ThietBi> tb = this.listThietBi();
        String name = "";
        for (ThietBi t : tb) {
            if (t.getMaTB()== matb) {
                name = t.getTenTB();
            }
        }
        return name;
    }
    // Lay ten thietbi tu ten thiet bi
    public List<ThietBi> getThietBifromName(String tentb){
    	List<ThietBi> tb = this.listThietBi();
    	
    	List<ThietBi> kq = new ArrayList<ThietBi>();
    	for(ThietBi t: tb) {
    		if(t.getTenTB().contains(tentb)) {
    			kq.add(t);
    		}
    	}
    	return kq;
    }
    public boolean addThietBi(ThietBi c) {
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        return true;
    }
    public boolean updateThietBi(ThietBi c) {
        session.beginTransaction();
        ThietBi tBi = session.get(ThietBi.class, c.getMaTB());
        tBi.setTenTB(c.getTenTB());
        tBi.setMotaTB(c.getMotaTB());
        session.saveOrUpdate(tBi);
        session.getTransaction().commit();
        return true;
    }
    public boolean deleteThietBi(int maTB) {
    	session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        ThietBi xoaTB = session.get(ThietBi.class, maTB);
        if (xoaTB != null) {
            session.delete(xoaTB);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            session.getTransaction().rollback(); 
            session.close();
            return false; 
        }
    }
}
