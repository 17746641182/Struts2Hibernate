package POJO.Emp;

import POJO.Dept.Dept;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity

@Table(name = "EMP")
public class Emp {


    //    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "seq_emp",sequenceName = "seq_emp_id",allocationSize = 10,initialValue = 1)
    @Id
    @Column(name = "empNo")
    private long empNo;

    @Column(name = "eName")
    private String eName;

    @Column(name = "job")
    private String job;

    @Column(name = "mgr")
    private Long mgr;

    @Column(name = "hireDate")
    private Date hireDate;

    @Column(name = "sal")
    private Long sal;

    @Column(name = "comm")
    private Long comm;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptNo")
    private Dept dept;


    public Emp() {
    }

    public long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(long empNo) {
        this.empNo = empNo;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getMgr() {
        return mgr;
    }

    public void setMgr(Long mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Long getSal() {
        return sal;
    }

    public void setSal(Long sal) {
        this.sal = sal;
    }

    public Long getComm() {
        return comm;
    }

    public void setComm(Long comm) {
        this.comm = comm;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }


}
