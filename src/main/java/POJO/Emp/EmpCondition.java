package POJO.Emp;

import java.util.Date;

public class EmpCondition {

    private String job;      //员工职位
    private Long sal;    //员工工资
    private Date hireDateEnd;    //员工入职结束时间
    private Date hireDateStart;   //员工入职开始时间


    public EmpCondition() {
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getSal() {
        return sal;
    }

    public void setSal(Long sal) {
        this.sal = sal;
    }

    public Date getHireDateEnd() {
        return hireDateEnd;
    }

    public void setHireDateEnd(Date hireDateEnd) {
        this.hireDateEnd = hireDateEnd;
    }

    public Date getHireDateStart() {
        return hireDateStart;
    }

    public void setHireDateStart(Date hireDateStart) {
        this.hireDateStart = hireDateStart;
    }
}
