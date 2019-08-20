package Service.Project;

import Dao.Project.ProjectDao;
import POJO.Project.Project;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class ProjectBiz {

    private Logger logger = Logger.getLogger(ProjectBiz.class);
    private ProjectDao pjDao = new ProjectDao();

    /**
     * 保存多对对关联 对象
     * @param project
     */
    public void addNewProject(Project project){
        Transaction tx = null;

        try {
            tx = pjDao.currentSession().beginTransaction();  //开启事务
            pjDao.save(project);
            tx.commit();   //提交事务
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if( tx != null){
                tx.rollback();
            }
        }
    }



}
