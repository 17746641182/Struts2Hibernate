package Service.Emp;

import Dao.Dept.DeptDao;
import Dao.Emp.EmpDao;
import POJO.Dept.Dept;
import POJO.Emp.Emp;
import POJO.Emp.EmpCondition;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class EmpBiz {

    private Logger logger = Logger.getLogger(EmpBiz.class);

    private EmpDao empDao = new EmpDao();

    public EmpDao getEmpDao() {
        return empDao;
    }

    public void setEmpDao(EmpDao empDao) {
        this.empDao = empDao;
    }

    /**
     * 使用list()方式执行查询
     *
     * @return
     */
    public List<Emp> findAllEmps() {
        Transaction tx = null;
        List<Emp> emps = null;
        try {
            tx = empDao.currentSession().beginTransaction();   // (1)开启事务
            emps = empDao.findAll();      //(2)持久化操作
            tx.commit();           //(3)提交事务
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();      //(4)回滚事务
            }
        }
        return emps;
    }

    /**
     * 使用iterate 方式执行查询
     *
     * @return
     */
    public Iterator<Emp> findAllEmps1() {
        Transaction tx = null;
        Iterator<Emp> emps = null;

        try {
            tx = empDao.currentSession().beginTransaction();   //(1)开启事务
            emps = empDao.findAll1();   //(2)持久化操作

            //遍历并输出结果,与调用list()方法时不同,须在会话关闭前测试查询效果
            Emp emp = null;

            while (emps.hasNext()) {
                emp = emps.next();
                System.out.println("员工姓名:" + emp.geteName());
            }
            tx.commit();     //提交事务
        } catch (HeadlessException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();    //回滚事务
            }
        }
        return emps;
    }

    /**
     * 使用Object 数组入参
     *
     * @param conditions
     * @return
     */
    public List<Emp> findEmpsByConditions(Object[] conditions) {
        Transaction tx = null;
        List<Emp> empList = null;

        try {
            tx = empDao.currentSession().beginTransaction();    //开启事务
            empList = empDao.findByConditions(conditions);
            tx.commit();   //提交事务
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();   //事务回滚
            }
        }
        return empList;
    }

    /**
     * 使用setProperties()方法:绑定命名参数与一个对象的属性值
     *
     * @param conditions
     * @return
     */
    public List<Emp> findEmpsByConditions(Emp conditions) {
        Transaction tx = null;
        List<Emp> empList = null;

        try {
            tx = empDao.currentSession().beginTransaction();    //开启事务
            empList = empDao.findByConditions(conditions);     //持久化操作
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();   //回滚事务
            }
        }
        return empList;
    }


    public List<Emp> findEmpsByConditions1(EmpCondition empCondition) {
        Transaction tx = null;
        List<Emp> emps = null;

        try {
            tx = empDao.currentSession().beginTransaction();   //开启事务
            //HQL根据条件动态生成
            StringBuilder hql = new StringBuilder("from Emp as emp where 1=1");
            if (empCondition.getJob() != null && empCondition.getJob().length() > 0) {
                hql.append(" and emp.job = :job");
            }

            if (empCondition.getSal() != null && empCondition.getSal() != 0) {
                hql.append(" and emp.sal > :sal");
            }

            if (null != empCondition.getHireDateStart()) {
                hql.append(" and emp.hireDate > :hireDateStart");
            }

            if (null != empCondition.getHireDateStart()) {
                hql.append(" and emp.hireDate < :hireDateEnd");
            }

            emps = empDao.findByConditions1(hql.toString(), empCondition);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();   //回滚事务
            }
        }
        return emps;
    }


    public Long countBySalary(Long sal) {
        Transaction tx = null;
        Long result = null;

        try {
            tx = empDao.currentSession().beginTransaction();   //开启事务
            result = empDao.obtainTheRowCount(sal);
            tx.commit();        //提交事务
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
        return result;
    }

    /**
     * 数据分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Emp> findEmpsByPage(int pageNo, int pageSize) {
        Transaction tx = null;
        List<Emp> emps = null;

        try {
            tx = empDao.currentSession().beginTransaction();    //开启事务
            emps = empDao.findByPage(pageNo, pageSize);
            tx.commit();   //提交事务
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();  //回滚事务
            }
        }
        return emps;
    }

    /**
     * 添加EMP业务方法
     *
     * @param emp
     */
    public void addNewEmp(Emp emp) {
        Transaction tx = null;
        try {
            tx = empDao.currentSession().beginTransaction();   //开始事务
            empDao.save(emp);
            tx.commit();    //提交事务
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();  //回滚事务
            }
        }
    }


    /**
     *
     * @param dept
     * @return
     */
    public List<Emp> findEmpsByDept(Dept dept) {
        Transaction tx = null;
        List<Emp> result = null;

        try {
            tx = empDao.currentSession().beginTransaction();   //开启事务
            result = empDao.findByDept(dept);
            tx.commit();     //提交事务
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();   //事务回滚
            }
        }

        return result;
    }

    /**
     *
     * @return
     */
    public List<Emp> findAllEmps2(){
        Transaction tx = null;
        List<Emp> emps = null;

        try {
            tx = empDao.currentSession().beginTransaction();
            emps = empDao.findAll2();
            //测试对象间导航效果,注意须在会话关闭前测试查询效果
            //原因会子下文的延迟加载一节进行分析
            for(Emp emp:emps){
                System.out.println("员工姓名:"+emp.geteName()+"\t所在部门:"+emp.getDept().getDeptName());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if(tx != null){
                tx.rollback();
            }
        }
        return emps;
    }

    /**
     *
     * @param empNo
     * @param deptNo
     */
    public  void changeDept(Integer empNo,Byte deptNo){
        Transaction tx = null;

        try {
            tx = empDao.currentSession().beginTransaction();    //开启事务
            //加载Dept和Emp的持久化对象
            Dept dept= new DeptDao().load(deptNo);
            Emp emp = empDao.load(empNo);
            //建立Dept对象和Emp对象的关联关系
            emp.setDept(dept);
            dept.getEmps().add(emp);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }




}
