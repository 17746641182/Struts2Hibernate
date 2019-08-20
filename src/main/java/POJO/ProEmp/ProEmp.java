package POJO.ProEmp;

import java.io.Serializable;



public class ProEmp implements Serializable {
    private long rProId;
    private long rEmpId;

    public ProEmp() {
    }


    public long getrProId() {
        return rProId;
    }

    public void setrProId(long rProId) {
        this.rProId = rProId;
    }

    public long getrEmpId() {
        return rEmpId;
    }

    public void setrEmpId(long rEmpId) {
        this.rEmpId = rEmpId;
    }
}
