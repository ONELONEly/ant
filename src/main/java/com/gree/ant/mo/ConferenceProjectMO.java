package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.ConferenceProjectDAOImp;
import com.gree.ant.dao.daoImp.ConferenceProjectUserDAOImp;
import com.gree.ant.mo.basic.BasicMO;
import com.gree.ant.mo.basic.ConferenceProjectBasicMO;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.ConferenceProject;
import com.gree.ant.vo.ConferenceProjectUser;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.ArrayList;
import java.util.List;

@IocBean
public class ConferenceProjectMO implements ConferenceProjectBasicMO,BasicMO<ConferenceProject> {

    @Inject
    private ConferenceProjectUserDAOImp projectUserDAOImp;

    @Inject
    private ConferenceProjectDAOImp projectDAOImp;

    @Override
    public QueryResult loadProjectData(Integer pageNumber, Integer pageSize, String acco) {
        Cnd cnd = Cnd.NEW();
        if (StringUtil.checkString(acco)) {
            cnd.and("creator","in","(select usid from cbase000 where acco = '"+acco+"')");
        }
        int count = projectDAOImp.countByCnd(cnd);
        Pager pager = TableUtil.formatPager(pageSize, pageNumber, count);
        List<ConferenceProject> projects = projectDAOImp.queryAllByCndPage(cnd, pager);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(projects);
        queryResult.setPager(pager);
        return queryResult;
    }

    @Override
    public QueryResult loadProjectUserData(Integer pageNumber, Integer pageSize, String userId) {
        Cnd cnd = Cnd.NEW();
        userId = userId == null ? "" : userId;
        cnd.and("creator","like","%"+userId+"%");
        int count = projectDAOImp.countByCnd(cnd);
        Pager pager = TableUtil.formatPager(pageSize, pageNumber, count);
        List<ConferenceProject> projects = projectDAOImp.queryAllByCndPage(cnd, pager);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(projects);
        queryResult.setPager(pager);
        return queryResult;
    }

    @Override
    public ConferenceProject insertProject(ConferenceProject conferenceProject, String[] users) {
        final ConferenceProject[] project = new ConferenceProject[1];
        Trans.exec((Atom) () -> {
            List<ConferenceProjectUser> projectUsers = new ArrayList<>();
            project[0] = insertByVO(conferenceProject);
            for(String user: users) {
                ConferenceProjectUser projectUser = new ConferenceProjectUser();
                projectUser.setProjectGuid(project[0].getProjectGuid());
                projectUser.setUserId(user);
                projectUsers.add(projectUser);
            }
            projectUserDAOImp.insert(projectUsers);
        });
        return project[0];
    }

    @Override
    public Boolean updateProject(ConferenceProject conferenceProject, String[] users) {
        Trans.exec((Atom) ()-> {
            updateByVO(conferenceProject);
            projectUserDAOImp.deleteByProjectGuid(conferenceProject.getProjectGuid());
            List<ConferenceProjectUser> projectUsers = new ArrayList<>();
            for(String user: users) {
                ConferenceProjectUser projectUser = new ConferenceProjectUser();
                projectUser.setProjectGuid(conferenceProject.getProjectGuid());
                projectUser.setUserId(user);
                projectUsers.add(projectUser);
            }
            projectUserDAOImp.insert(projectUsers);
        });
        return true;
    }

    @Override
    public Boolean deleteProject(ConferenceProject conferenceProject) {
        Trans.exec((Atom) ()-> {
            deleteByVO(conferenceProject);
            projectUserDAOImp.deleteByProjectGuid(conferenceProject.getProjectGuid());
        });
        return true;
    }

    @Override
    public ConferenceProject insertByVO(ConferenceProject vo) {
        return projectDAOImp.insert(vo);
    }

    @Override
    public List<ConferenceProject> insertByVOS(List<ConferenceProject> vos) {
        return projectDAOImp.insert(vos);
    }

    @Override
    public Integer deleteByVO(ConferenceProject vo) {
        return projectDAOImp.delete(vo);
    }

    @Override
    public Integer deleteByName(String name) {
        return projectDAOImp.deleteByName(name);
    }

    @Override
    public Integer updateByVO(ConferenceProject vo) {
        return projectDAOImp.update(vo);
    }

    @Override
    public Integer updateByVOS(List<ConferenceProject> vos) {
        return projectDAOImp.update(vos);
    }

    @Override
    public ConferenceProject fetchProject(String conference_project_id) {
        ConferenceProject conferenceProject = projectDAOImp.fetchByName(conference_project_id);
        conferenceProject.setProjectUsers(projectDAOImp.queryALLUserById(conference_project_id));
        return conferenceProject;
    }

    @Override
    public List<ResultVO> queryAllAD() {
        return projectDAOImp.queryAllAD();
    }
}
