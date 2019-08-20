package utils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static Logger logger = Logger.getLogger(HibernateUtil.class);

    private static Configuration configuration;
    private final static SessionFactory sessionFactory;


    //初始化Configuration和SessionFactory

    static {
        try {
           configuration = new Configuration().configure();
           sessionFactory = configuration.buildSessionFactory();
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
            logger.error(e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public  HibernateUtil(){

    }

    //获取Session对象
    public static Session currentSession(){

//        return sessionFactory.openSession();   open需要考虑线程安全和关闭Session
        return sessionFactory.getCurrentSession();
    }

}
