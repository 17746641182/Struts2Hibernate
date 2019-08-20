package Dao.Emp;

import Dao.BaseDao;
import POJO.Dept.Dept;
import POJO.Emp.Emp;
import POJO.Emp.EmpCondition;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmpDao extends BaseDao {

    /**
     * 使用list()方法执行查询并输出结果
     *
     * @return
     */
    public List<Emp> findAll() {
        String hql = " from Emp";    //定义HQL语句
        Query query = currentSession().createQuery(hql);   //构建Query对象
        return query.list();     //执行查询
    }

    /**
     * 使用iterate()方法执行查询并输出结果
     *
     * @return
     */
    public Iterator<Emp> findAll1() {
        String hql = "from Emp";     //定义HQL语句
        Query query = currentSession().createQuery(hql);   //构建Query对象
        return query.iterate();      //执行查询
    }

    /**
     * 使用Object 类型作为HQL参数的类型
     *
     * @param conditions
     * @return
     */
    public List<Emp> findByConditions(Object[] conditions) {
        //查询依赖多个条件,且类型各异
        System.out.println("===================================");
        String hql = "from Emp where job= ?1  and sal > ?2";

        //疑问: 无法使用 ? 占位符 赋值
        Query query = currentSession().createQuery(hql);


        for (int i = 1; i < conditions.length+1; i++) {
            query.setParameter(i, conditions[i-1]);     //为占位符赋值
        }
        return query.list();
    }


    /**
     * 使用setProperties()方法:绑定命名参数与一个对象的属性值
     *
     * @param conditions
     * @return
     */
    public List<Emp> findByConditions(Emp conditions) {
        String hql = " from Emp where job = :job and sal > :sal";
        Query query = this.currentSession().createQuery(hql);

        //根据命名参数的名称,从conditions中获取相应的属性值进行赋值
        query.setProperties(conditions);
        return query.list();
    }

    /**
     * 动态查询: 创建Query对象,为参数赋值,执行查询获取查询结果
     * @param hql
     * @param conditions
     * @return
     */
    public List<Emp> findByConditions1(String hql, EmpCondition conditions){

        return currentSession().createQuery(hql).setProperties(conditions).list();
    }

    /**
     * 使用uniqueResult()方法获取唯一结果
     * @param sal
     * @return
     */
    public Long obtainTheRowCount(Long sal){
        String hql = "select count(id) from Emp where sal >= :sal";
        //执行返回唯一结果,以Object 类型封装
        return (Long) currentSession().createQuery(hql).setParameter("sal",sal).uniqueResult();
    }

    public List<Emp> findByPage(int pageNo,int pageSize){
        String hql = "from Emp order by id";
        return currentSession().createQuery(hql).
                setFirstResult((pageNo-1)*pageSize).   //设置获取结果的起始下标
                setMaxResults(pageSize).               //设置最大返回结果数
                list();
    }

    /**
     *  添加emp对象
     * @param emp
     */
    public void save(Emp emp){
        this.currentSession().save(emp);
    }

    /**
     * 按照指定的Dept对象来查询相关的Emp对象
     * @param dept
     * @return
     */
    public List<Emp> findByDept(Dept dept){
        String hql = "from Emp where dept = ?1";
        List<Emp> list = new ArrayList<>();
        return currentSession().createQuery(hql).setParameter(1,dept).list();
    }

    /**
     *
     * 输出指定EMP集合中所有EMP对象及其关联的Dept对象的信息
     * @return
     */
    public List<Emp> findAll2(){
        return currentSession().createQuery("from Emp ").list();
    }


    /**
     *
     * @param empNo
     * @return
     */
    public  Emp load(Serializable empNo){
        return currentSession().load(Emp.class,empNo);
    }
}
