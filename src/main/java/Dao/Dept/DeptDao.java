package Dao.Dept;

import Dao.BaseDao;
import POJO.Dept.Dept;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public class DeptDao extends BaseDao {

    /**
     * get()方法加载部门对象
     * @param id
     * @return
     */
    public Dept get(Serializable id){
        //通过Session的get()方法根据OID加载指定对象
//        return (Dept)HibernateUtil.currentSession().get(Dept.class,id);

            return (Dept) currentSession().get(Dept.class,id);
    }


    /**
     * 使用Hibernate实现增加部门记录
     * @param dept
     */
    public void save(Dept dept){
        currentSession().save(dept);   //保存指定的Dept对象
    }


    /**
     * 通过Session的get()或load()方法加载指定对象
     * @param id
     * @return
     */
    public Dept load(Serializable id){
        return (Dept) currentSession().load(Dept.class,id);
    }


    /**
     * 删除对象
     * @param dept
     */
    public void delete(Dept dept){
        currentSession().delete(dept);    //删除指定的Dept对象
    }

    /**
     * 更新数据的方法 :merge
     * @param dept
     * @return
     */
    public Dept merge(Dept dept){
        return (Dept) currentSession().merge(dept);
    }


    /**
     * 按参数位置绑定
     * @param deptName
     * @return
     */
    public List<Dept> findByDeptName(String deptName){
        String hql = "from Dept as dept where dept.deptName = ?";
//        String hql = "from Dept  as dept where dept.DeptName =: name";
        Query query = currentSession().createQuery(hql);
        //疑问处 :Hibernate 4.1过后不支持 ? 赋值,
        query.setString(0,deptName);

//        query.setParameter(0,deptName);      //为占位符赋值,占位符下标从0 开始
        //setParameter只能使用一次查询:运行过一次,需要重新编写 query.setParameter(0,deptName);


//        query.setParameter("name",deptName);

        return query.list();
    }

    /**
     *
     * @param deptName
     * @return
     */
    public  List<Dept> findByDeptName1(String deptName){
        //设置命名参数
        String hql = "from Dept as dept where dept.deptName =: name";
        Query query = currentSession().createQuery(hql);
        query.setString("name",deptName);
        return query.list();
    }


    /**
     * 投影查询1:
     * 每条查询结果仅包含一个结果列
     * @return
     */
    public List<String> findAllNames(){
        String hql = "select deptName from Dept ";

        return currentSession().createQuery(hql).list();
    }

    /**
     * 投影查询2:
     * 每条查询结果包含不止一个结果列
     * @return
     */
    public List<Object[]> findAllDeptList(){
        String hql = "select deptNo,deptName from Dept";
        return currentSession().createQuery(hql).list();
    }

    /**-
     * 投影结果3:
     * 要求Dept类中有Dept(Byte deptNo,String deptName)和Dept()构造方法
     * @return
     */
    public List<Dept> findAllDeptList2(){
        String hql = "select new Dept(deptNo,deptName) from Dept";
        return currentSession().createQuery(hql).list();
    }

    /**
     *
     * @param dept
     */
    public void save1(Dept dept){
        currentSession().save(dept);
    }

    /**
     *
     * @param dept
     */
    public void delete1(Dept dept){
        currentSession().delete(this.load(dept.getDeptNo()));
    }

}
