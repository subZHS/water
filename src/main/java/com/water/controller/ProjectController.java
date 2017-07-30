package com.water.controller;

import com.water.entity.Project;
import com.water.entity.Sample;
import com.water.service.ProjectService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * @param request
     * @return 项目id
     * @throws Exception
     */
    @RequestMapping("/publish")
    @ResponseBody
    public long publishProject(HttpServletRequest request) throws IOException {
        String head=request.getParameter("headline");
        String body=request.getParameter("body");

        Project project=new Project();
        project.setName(head);
        project.setDescription(body);

        long id=projectService.saveProject(project);

        return id;
//        return 0;
    }

    /**
     * @param response
     * @return 项目列表
     * @throws Exception
     */
    @RequestMapping("/allProject")
    @ResponseBody
    public void getAll(HttpServletResponse response) throws IOException {
        List<Project> projects=projectService.findAllProjects();

        System.out.println(projects.get(0).getName());
        JSONArray jsonObject=JSONArray.fromObject(projects);

        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonObject);
        response.getWriter().flush();
    }

    /**
     * @param response
     * @return 项目列表
     * @throws Exception
     */
    @RequestMapping("/getProjectName")
    @ResponseBody
    public void getProjectName(HttpServletResponse response) throws IOException {
        List<Project> projects=projectService.findAllProjects();
        ArrayList<String> projectNames = new ArrayList<String>();
        for(Project temp:projects){
            projectNames.add(String.valueOf(temp.getName()+""));
        }
        JSONArray array = JSONArray.fromObject(projectNames);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    /**
     * @param request
     * @return boolean
     * @throws Exception
     */
    @RequestMapping("/deleteProject")
    @ResponseBody
    public boolean delete(HttpServletRequest request) throws IOException {
        String id=request.getParameter("id");
        boolean success=projectService.deleteProject(Long.parseLong(id));
        return success;
//        return true;
    }

    /**
     * @param request
     * @return boolean
     * @throws Exception
     */
    @RequestMapping("/modifyProject")
    @ResponseBody
    public boolean modify(HttpServletRequest request) throws IOException {
        long id=Long.parseLong(request.getParameter("id"));
        String head=request.getParameter("headline");
        String body=request.getParameter("body");

        Project project=new Project();
        project.setIdProject(id);
        project.setName(head);
        project.setDescription(body);

        boolean success=projectService.updateProject(project);

        return success;
//        return true;
    }

    /**
     * @param request
     * @return boolean
     * @throws Exception
     */
    @RequestMapping("/uploadProPic")
    @ResponseBody
    public boolean upload(HttpServletRequest request){
        return false;
    }

}
