package POJO.Project;

import POJO.Employee.Employee;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Project implements Serializable {
    private Integer pRoId;    //项目ID
    private String pRoName;    //项目名称
    private Set<Employee> employees = new HashSet<Employee>(0);


    public Project() {
    }

    public Project(Integer pRoId, String pRoName) {
        this.pRoId = pRoId;
        this.pRoName = pRoName;
    }


    public Integer getpRoId() {
        return pRoId;
    }

    public void setpRoId(Integer pRoId) {
        this.pRoId = pRoId;
    }

    public String getpRoName() {
        return pRoName;
    }

    public void setpRoName(String pRoName) {
        this.pRoName = pRoName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
