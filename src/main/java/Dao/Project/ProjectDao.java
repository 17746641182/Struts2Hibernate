package Dao.Project;

import Dao.BaseDao;
import POJO.Project.Project;

public class ProjectDao extends BaseDao {

    /**
     * 保存Project对象的同时保存Employee对象
     * @param project
     */
    public void save(Project project){
        this.currentSession().save(project);
    }



}
