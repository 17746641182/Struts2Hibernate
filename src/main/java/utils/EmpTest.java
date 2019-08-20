package utils;

import POJO.Dept.Dept;
import POJO.Emp.Emp;
import POJO.Emp.EmpCondition;
import Service.Emp.EmpBiz;
import org.junit.Test;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

public class EmpTest {

    @Test
    public void testQueryList(){
        //执行查询
        List<Emp> empList = new EmpBiz().findAllEmps();

        //遍历并输出结果
        for(Emp emp:empList){
            System.out.println("员工姓名:"+emp.geteName());

        }
    }

    /**
     * 使用iterate 方式执行查询
     */
    @Test
    public void testQueryIterate(){
        //执行查询
        Iterator<Emp> empList = new EmpBiz().findAllEmps1();
    }

    /**
     * 使用Object数组入参
     */
    @Test
    public void testQueryByObject(){
        //执行查询
        Object[] conditions = {"CLERK", "1000"};
        List<Emp> empList = new EmpBiz().findEmpsByConditions(conditions);

        //遍历并输出结果
        for(Emp emp:empList){
            System.out.println("员工姓名:"+emp.geteName());
        }
    }

    /**
     * 使用setProperties()方法:绑定命名参数与一个对象的属性值
     */
    @Test
    public void testQueryByEmp(){
        //执行查询
        Emp conditions = new Emp();
        conditions.setJob("CLERK");
        conditions.setSal((long)1000);

        List<Emp> empList = new EmpBiz().findEmpsByConditions(conditions);

        //遍历输出结果
        for(Emp emp1: empList){
            System.out.println("员工姓名:"+emp1.geteName());
        }
    }


    @Test
    public void testQueryByEmp1() throws ParseException {
        //执行查询并输出结果
        EmpCondition empCondition = new EmpCondition();
        empCondition.setJob("CLERK");

        empCondition.setSal((long) 1000);
        empCondition.setHireDateStart(Tool.strToDate("1981-4-1","yyyy-MM-dd"));
        empCondition.setHireDateEnd(Tool.strToDate("1985-9-9","yyyy-MM-dd"));

        //执行查询并输出结果
        List<Emp> empList = new EmpBiz().findEmpsByConditions1(empCondition);

        for(Emp emp:empList){
            System.out.println("员工编号为:"+emp.getEmpNo());
        }
    }

    /**
     * 测试使用uniqueResult()方法获取唯一结果
     */
    @Test
    public void testUniqueResult(){
        long sal = 1000L;
        Long result = new EmpBiz().countBySalary(sal);
        System.out.println("薪资"+sal+"以上的员工有"+result+"人");
    }

    /**
     * 数据分页查询
     */
    @Test
    public void testFindByPage(){
        int pageNo =1 ;
        int pageSize = 2;
        List<Emp> empList = new EmpBiz().findEmpsByPage(pageNo,pageSize);

        for(Emp emp:empList){
            System.out.println("员工编号为:"+emp.getEmpNo());
        }
    }

    /**
     *  测试添加新对象
     */
    @Test
    public void testAddNewEmp(){
        //创建Emp对象
        Emp emp = new Emp();
        emp.seteName("丁启樑");
        //指定员工所在的部门为会计部门
        Dept dept = new Dept();
        dept.setDeptNo((short) 1);    //会计部门的编号
        emp.setDept(dept);

        //保存员工数据
        new EmpBiz().addNewEmp(emp);
    }

    /**
     *
     */
    @Test
    public void testFindByDept(){
        Dept dept = new Dept();
        dept.setDeptNo((short) 10);
        List<Emp> empList  = new EmpBiz().findEmpsByDept(dept);

        for(Emp emp:empList){
            System.out.println("员工姓名:"+emp.geteName());
        }
    }

    /**
     *
     */
    @Test
    public void testFindAll3(){
       List<Emp> empList = new EmpBiz().findAllEmps2();
    }


    /**
     *
     */
    @Test
    public void testChangDept(){
        new EmpBiz().changeDept(7369, (byte) 40);
    }
}
