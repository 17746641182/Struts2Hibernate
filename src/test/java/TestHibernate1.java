import POJO.Dept.Dept;
import Service.Dept.DeptBiz;
import org.junit.Test;

public class TestHibernate1 {

    @Test
    public void TestDeptDemo(){
        //1.加载数据操作
        Dept dept = new DeptBiz().findDeptById(new Byte("10"));

        //2.输出数据
        System.out.println(dept.getDeptName());
    }
}
