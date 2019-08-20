package Filter;


import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.servlet.*;
import java.io.IOException;

public class OpenSessionInViewFilter implements Filter {

    private Logger logger = Logger.getLogger(OpenSessionInViewFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Transaction tx = null;

        try {
            //请求打开时,打开Session并启动事务
            tx = HibernateUtil.currentSession().beginTransaction();  //开启事务
            //执行请求处理链
            chain.doFilter(request,response);
            //返回响应时,提交事务
            tx.commit();   //提交事务

        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if( tx != null){
                tx.rollback();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
