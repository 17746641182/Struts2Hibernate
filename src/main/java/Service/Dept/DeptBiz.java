package Service.Dept;

import Dao.Dept.DeptDao;
import POJO.Dept.Dept;
import POJO.Emp.Emp;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.junit.Test;
import utils.HibernateUtil;

import java.util.List;

public class DeptBiz {

    private Logger logger = Logger.getLogger(DeptBiz.class);
    private DeptDao deptDao = new DeptDao();


    public DeptDao getDeptDao() {
        return deptDao;
    }

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    public Dept findDeptById(Byte id) {
        Transaction tx = null;
        Dept result = null;

        try {
            tx = HibernateUtil.currentSession().beginTransaction(); //开启事务
            result = deptDao.get(id);  //调用Dao方法,根据OID加载指定Dept对象
            tx.commit();    //提交事务

        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();   //回滚事务
            }
        }
        return result;
    }

    /**
     * 使用Hibernate实现增加部门记录
     *
     * @param dept
     */
    public void addNewDept(Dept dept) {
        Transaction tx = null;
        try {
            tx = deptDao.currentSession().beginTransaction();    //开启事务
            deptDao.save(dept);    //调用dao保存Dept对象的数据
            tx.commit();          //提交事务
        } catch (HibernateException e) {
            logger.error(e);
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();     //回滚事务
            }
        }
    }

    /**
     * 使用Hibernate实现部门的修改,删除
     *
     * @param dept
     */
    public void updateDept(Dept dept) {
        Transaction tx = null;
        try {
            tx = deptDao.currentSession().beginTransaction();    //开启事务
            //通过get()或load()方法加载要修改的部门对象
            Dept deptToUpdate = deptDao.load(dept.getDeptNo());
            //更新部门数据
            deptToUpdate.setDeptName(dept.getDeptName());
            deptToUpdate.setLocation(dept.getLocation());

            tx.commit();    //提交事务
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();    //回滚事务
            }
        }
    }


    /**
     * 根据id 删除指定的对象
     *
     * @param id
     */
    public void deleteDept(Byte id) {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();   //开启事务
            //通过get()或load()方法加载要删除的部门对象
            Dept deptToDelete = deptDao.load(id);
            deptDao.delete(deptToDelete);    //删除部门数据
            tx.commit();    //提交事务
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();    //回滚事务
            }
        }
    }


    /**
     * 更新数据的方法 mergeDept()
     *
     * @param dept
     * @return
     */
    public Dept mergeDept(Dept dept) {
        Transaction tx = null;
        Dept persistentDept = null;

        try {
            tx = deptDao.currentSession().beginTransaction();    //开启事务
            //合并dept的数据或者保存dept的副本,返回持久态对象
            persistentDept = deptDao.merge(dept);
            tx.commit();      //提交事务
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();   //回滚事务
            }
        }
        return persistentDept;
    }

    /**
     * 根据按参数位置绑定入参
     *
     * @param deptName
     * @return
     */
    public List<Dept> findDeptByName(String deptName) {
        Transaction tx = null;
        List<Dept> deptList = null;

        try {
            tx = deptDao.currentSession().beginTransaction();     //开启事务
            deptList = deptDao.findByDeptName(deptName);      //调用dao查询方法
            tx.commit();   //提交事务
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();    //回滚事务
            }
        }
        return deptList;
    }

    /**
     * 根据按参数名称入参
     *
     * @param deptName
     * @return
     */
    public List<Dept> findDeptByName1(String deptName) {
        Transaction tx = null;
        List<Dept> deptList = null;

        try {
            tx = deptDao.currentSession().beginTransaction();     //开启事务
            deptList = deptDao.findByDeptName1(deptName);      //调用dao查询方法
            tx.commit();   //提交事务
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();    //回滚事务
            }
        }
        return deptList;
    }

    /**
     * @param dept
     */
    public void addNewDept1(Dept dept) {
        Transaction tx = null;
        try {
            tx = deptDao.currentSession().beginTransaction();   //开启事务
            deptDao.save1(dept);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void deleteDept(Dept dept) {
        Transaction tx = null;
        try {
            tx = deptDao.currentSession().beginTransaction();  //开启事务
            deptDao.delete(dept);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 测试延迟加载 保存对象
     */
    public void saveLazyDept() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            Dept dept = deptDao.currentSession().load(Dept.class, 10);
//            Emp emp = new Emp();
//            emp.seteName("Tom");
//            emp.setDept(dept);
//            deptDao.currentSession().save(emp);

            if (!Hibernate.isInitialized(dept)) {
                Hibernate.initialize(dept);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 测试内连接
     */

    public void testInnerJoinSer() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Object[]> list = HibernateUtil.currentSession().
                    createQuery("from Dept d inner join d.emps").list(); //inner可以省略
            for (Object[] objects : list) {
                System.out.println(objects[0] + "\t" + objects[1]);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 迫切内连接
     */
    public void testInnerJoinSer2() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Dept> list = deptDao.currentSession()
                    .createQuery("from Dept d inner join fetch d.emps").list();
            for (Dept dept : list) {
                System.out.println(dept.getDeptName() + "\n\t" + dept.getEmps());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 隐式内连接
     */
    public void testInnerJoinSer3() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Emp> list = HibernateUtil.currentSession()
                    .createQuery("from Emp e where e.dept.deptName = ?1")
                    .setParameter(1, "研发部").list();
            for (Emp emp : list) {
                System.out.println(emp.geteName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /*
     * 分组统计数据:聚合函数
     */
    public void testGroupBy() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            //count():统计记录条数:查询Dept表中所有记录的条数
            Object count = deptDao.currentSession()
                    .createQuery("select count(id) from Dept ").uniqueResult();
            //sum():求和:计算所有员工应发的工资总和
            Object salarySum = deptDao.currentSession()
                    .createQuery("select sum(sal) from Emp ").uniqueResult();

            //min():求最小值:员工的最低工资是多少
            Object salaryMin = deptDao.currentSession()
                    .createQuery("select min(sal) from Emp").uniqueResult();

            //max():求最大值:员工的最高工资是多少
            Object salaryMax = deptDao.currentSession()
                    .createQuery("select max(sal) from Emp ").uniqueResult();

            //avg():求平均值:员工的平均工资是多少
            Object salaryAvg = deptDao.currentSession()
                    .createQuery("select avg (sal) from Emp ").uniqueResult();

            System.out.println("count:" + count + "\nsalarySum:" + salarySum + "\nsalaryMin:" +
                    salaryMin + "\nsalaryMax:" + salaryMax + "\nsalaryAvg:" + salaryAvg);

            Object[] salaryS = (Object[]) deptDao.currentSession()
                    .createQuery("select min(sal),max(sal),avg(sal) from Emp ").uniqueResult();
            System.out.println("Salary:" + salaryS[0] + ",\t" + salaryS[1] + ",\t" + salaryS[2]);

        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 分组查询:按职位统计员工个数
     */
    public void testGroupBy2() {
        Transaction tx = null;
        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Object[]> list = deptDao.currentSession()
                    .createQuery("select e.job,count(e) from Emp e group by e.job").list();

            for (Object[] obj : list) {
                System.out.println(obj[0] + "," + obj[1]);
            }
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 分组查询：统计各个部门平均工资
     */
    public void testGroupBy3() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Object[]> list = deptDao.currentSession()
                    .createQuery("select e.dept.deptName ,avg(e.sal) from Emp  e group by e.dept.deptName")
                    .list();
            System.out.println("部门,\t薪资");
            for (Object[] objects : list) {
                System.out.println(objects[0] + ",\t" + objects[1]);
            }

            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 分组查询:统计各个职位的最低工资和最高工资
     */
    public void testGroupBy4() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();

            List<Object[]> list = deptDao.currentSession()
                    .createQuery("select job, min(sal),max(sal) from Emp group by job").list();

            for (Object[] objects : list) {
                System.out.println("职位:" + objects[0] + "\t最低工资:" + objects[1] + "\t最高工资:" + objects[2]);
            }
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 分组查询:统计各个部门平均工资高于4000元的部门名称,输出部门名称,部门平均工资
     */
    public void testGroupBy5() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Object[]> list = deptDao.currentSession()
                    .createQuery("select e.dept.deptName, avg(e.sal) from Emp e group by e.dept.deptName having avg(e.sal)>4000")
                    .list();

            for (Object[] objects : list) {
                System.out.println("部门名称:" + objects[0] + "\t平均工资:" + objects[1]);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 子查询:查询所有员工工资都小于5000元的部门
     * all:子查询语句返回的所有记录
     */
    public void testSubQuery() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Dept> list = deptDao.currentSession()
                    .createQuery("from Dept d where 5000 > all(select e.sal from d.emps e)")
                    .list();

            for (Dept dept : list) {
                System.out.println(dept.getDeptName());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }

    }

    /**
     * 子查询:查询至少有一位员工的工资低于5000元的部门
     * any:子查询语句返回的任意一条记录
     */
    public void testSubQuery1() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Dept> list = deptDao.currentSession()
                    .createQuery("from Dept d where 5000 > any(select e.sal from d.emps e)")
                    .list();
            for (Dept dept : list) {
                System.out.println(dept.getDeptName());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 子查询:查询有员工工资刚好是5000元的部门
     * any:子查询语句返回的任意一条记录
     */
    public void testSubQuery2() {
        Transaction tx = null;
        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Dept> list = deptDao.currentSession()
                    .createQuery("from Dept d where 5000 = any(select e.sal from d.emps e)")
                    .list();
            for (Dept dept : list) {
                System.out.println(dept.getDeptName());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 子查询:查询至少有一位员工的部门
     * exists:子查询语句至少返回一条记录
     */
    public void testSubQuery3() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Dept> list = deptDao.currentSession()
                    .createQuery("from Dept d where exists (from d.emps)").list();
            for (Dept dept : list) {
                System.out.println(dept.getDeptName());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 操作集合函数:查询指定员工所在部门
     */
    public void testSetFunction() {
        //构建查询条件
        Emp emp = new Emp();
        emp.setEmpNo(1);
        Transaction tx = null;
        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Dept> list = deptDao.currentSession()
                    .createQuery("from Dept d where ?1 in elements(d.emps) ")
                    .setParameter(1, emp).list();
            //HQL:from Dept d where ?1 in (from d.empS)

            for (Dept dept : list) {
                System.out.println(dept.getDeptName());
            }
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    /**
     * 操作集合属性:查询员工个数大于5的部门
     */
    public void testSetFunction2() {
        Transaction tx = null;

        try {
            tx = deptDao.currentSession().beginTransaction();
            List<Dept> list = deptDao.currentSession()
                    .createQuery("from Dept d where d.emps.size > 5 ").list();
            // HQL: from Dept d where size(d.empS > 5)
            for (Dept dept : list) {
                System.out.println(dept.getDeptName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }


}
