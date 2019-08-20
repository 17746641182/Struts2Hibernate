package utils;

import POJO.Employee.Employee;
import POJO.Project.Project;
import Service.Project.ProjectBiz;
import org.junit.Test;

public class ProjectTest {
    /**
     *  保存Project 对象的同时保存Employee对象
     */
    @Test
    public  void  testSave(){
        Employee employee1 = new Employee(1,"张三");
        Employee employee2 = new Employee(2,"李四");

        Project project1 = new Project(2,"2号项目");
        Project project2 = new Project(3,"3号项目");

        project1.getEmployees().add(employee1);
        project1.getEmployees().add(employee2);

        project2.getEmployees().add(employee1);

        ProjectBiz projectBiz = new ProjectBiz();

        projectBiz.addNewProject(project1);
        projectBiz.addNewProject(project2);

    }

    /**
     *  双向多对多关联的两端
     */
    @Test
    public void testSave2(){
        Employee emp1 = new Employee(3,"刘明良");
        Employee emp2 = new Employee(4,"黄泰兴");

        Project pro1 = new Project(4,"4号项目");
        Project pro2 = new Project(5,"5号项目");

        pro1.getEmployees().add(emp1);
        pro1.getEmployees().add(emp2);

        pro2.getEmployees().add(emp1);

        emp1.getProjects().add(pro1);
        emp1.getProjects().add(pro2);

        emp2.getProjects().add(pro1);

        ProjectBiz projectBiz = new ProjectBiz();
        projectBiz.addNewProject(pro1);
        projectBiz.addNewProject(pro2);
    }
}
