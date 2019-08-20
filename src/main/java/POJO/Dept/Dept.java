package POJO.Dept;

import POJO.Emp.Emp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dept")
public class Dept implements Serializable {
    @Id
    @Column(name = "deptNo")
    private Short deptNo;                      //部门编号
    @Column(name = "DName")
    private String deptName;                   //部门名称
    @Column(name = "loc")
    private String location;                   //部门地址

    //mappedBy:将关联关系的控制权交给Emp类这一方,相当于在Dept.hbm.xml文件中配置的inverse = "true"
    //cascade:设置级联操作类型
    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL)
    private Set<Emp> emps = new HashSet<Emp>();  //部门员工集合

    public Dept() {
    }

    public Dept(Short deptNo, String deptName) {
        this.deptNo = deptNo;
        this.deptName = deptName;
    }

    public Dept(Short deptNo, String deptName, String location) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.location = location;
    }

    public Set<Emp> getEmps() {
        return emps;
    }

    public void setEmps(Set<Emp> emps) {
        this.emps = emps;
    }

    public Short getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Short deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
