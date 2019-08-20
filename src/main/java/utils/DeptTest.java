package utils;

import POJO.Dept.Dept;
import POJO.Emp.Emp;
import Service.Dept.DeptBiz;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.net.DatagramPacket;
import java.util.Date;
import java.util.List;

public class DeptTest {

    private DeptBiz deptBiz = new DeptBiz();


    @Test
    public void HibernateDemo() {
        //1.读取并解析配置文件及映射文件
        Configuration conf = new Configuration().configure();

        //2.依据配置文件和映射文件中的信息,创建SessionFactory对象
        SessionFactory sf = conf.buildSessionFactory();

        //3.打开Session
        Session session = sf.getCurrentSession();

        //4.开始一个事务
        Transaction tx = session.beginTransaction();

        //5.数据库操作
        session.save(new Dept());

        //6.结束事务
        tx.commit();    //提交事务
        tx.rollback();   //回滚事务

        //7.如果是通过SessionFactory 的openSession()方法获取的Session对象,则需session
        session.close();
    }

    @Test
    public void TestDept1() {
        Dept dept = new DeptBiz().findDeptById(new Byte("10"));
        System.out.println("名称:" + dept.getDeptName());
    }

    /**
     * 测试保存Dept对象
     */
    @Test
    public void TestSaveDept() {
        //构建测试数据
//        Dept dept = new Dept();
//        dept.setDeptNo(new Short("13"));
//        dept.setDeptName("测试部");
//        dept.setLocation("刘明良");
//
//        //保存新部门信息
//        new DeptBiz().addNewDept(dept);
        Dept dept = new Dept((short) 6,"产品部");
        Emp emp = new Emp();
        emp.seteName("Villy");
        emp.setHireDate(new Date());

        dept.getEmps().add(emp);
        emp.setDept(dept);

        deptBiz.addNewDept(dept);
    }

    /**
     * 测试修改Dept对象
     */
    @Test
    public void TestUpdateDept() {
        Dept dept = new Dept();
        dept.setDeptNo(new Short("11"));

        //需要修改的属性
        dept.setDeptName("质管部");
        dept.setLocation("艾欧尼亚区");

        //更新部门信息
        new DeptBiz().updateDept(dept);
    }


    /**
     * 删除指定的Dept对象
     */
    @Test
    public void TestDeleteDept() {
        new DeptBiz().deleteDept(new Byte("11"));
    }

    /**
     * 更新数据的方法:merge()
     */
    @Test
    public void TestMergeDept() {
        Dept dept = new Dept();
        dept.setDeptNo(new Short("12"));   //游离状态,去掉本行代码则为临时状态
        dept.setDeptName("开发部");
        dept.setLocation("西区");
        //合并游离状态dept的数据或者保存临时状态的dept副本
        new DeptBiz().mergeDept(dept);
    }

    /**
     * 根据参数位置绑定,使参数入参
     */
    @Test
    public void TestQueryByParams() {
        List<Dept> result = new DeptBiz().findDeptByName("SALES");
        for (Dept dept : result) {
            System.out.println("部门地址为:" + dept.getLocation());
        }
    }


    /**
     * 根据参数位置绑定,使参数入参
     */
    @Test
    public void TestQueryByParams1() {
        List<Dept> result = new DeptBiz().findDeptByName1("测试部");
        for (Dept dept : result) {
            System.out.println("部门地址为:" + dept.getLocation());
        }
    }

    /**
     *
     */
    @Test
    public void testSave1(){
        //创建一个Dept对象和Emp对象
        Dept dept = new Dept((short) 22,"质控部","中部");

        Emp emp = new Emp();
        emp.seteName("李四");

        //建立Dept对象和Emp对象的双向关联关系
        emp.setDept(dept);
        dept.getEmps().add(emp);

        //保存Dept对象
        new DeptBiz().addNewDept1(dept);
    }


    /**
     *
     */
    @Test
    public void testDeleteDept(){
        //封装待删除的Dept对象
        Dept dept = new Dept();
        dept.setDeptNo((short) 12);
        new DeptBiz().deleteDept(dept); //删除该Dept对象
    }

    /**
     *
     */
    @Test
    public void testSaveByLazy(){
        new DeptBiz().saveLazyDept();
    }

    /**
     *
     */
    @Test
    public void testInnerJoin(){
//        deptBiz.testInnerJoinSer();
//        deptBiz.testInnerJoinSer2();
//          deptBiz.testInnerJoinSer3();

    }

    /**
     * 测试分组查询
     */
    @Test
    public void testGroupByQuery(){
//        deptBiz.testGroupBy();
//        deptBiz.testGroupBy2();
//          deptBiz.testGroupBy3();
//        deptBiz.testGroupBy4();
        deptBiz.testGroupBy5();
    }

    /***
     * 测试子查询
     */
    @Test
    public  void testSubQuery(){
//        deptBiz.testSubQuery();
//        deptBiz.testSubQuery1();
//        deptBiz.testSubQuery2();
        deptBiz.testSubQuery3();
    }

    /**
     * 测试操作集合的函数或属性:查询员工个数大于5的部门
     */
    @Test
    public void testSetFunction(){
//        deptBiz.testSetFunction();
        deptBiz.testSetFunction2();
    }

}
