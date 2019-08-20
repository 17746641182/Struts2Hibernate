package Dao;

import org.hibernate.Session;
import utils.HibernateUtil;

public class BaseDao {

    public Session currentSession(){
        return HibernateUtil.currentSession();
    }
}
