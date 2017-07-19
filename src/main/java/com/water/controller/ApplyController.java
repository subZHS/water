package com.water.controller;

import com.water.model.ApplyEntity;
import com.water.model.ApplyEntity;
import com.water.service.ApplyService;
import com.water.service.Impl.ApplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Created by 朱晨乾 on 2017/7/17.
 */
@Controller
public class ApplyController {

    @Autowired
    private ApplyService applyService;
    /**
     * @param request
     * @param response
     * @return 得到申请的list
     * @throws Exception
     */
    @RequestMapping("/applylist")
    @ResponseBody
    public void applylist(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String state = request.getParameter("state" );
        System.out.println(state);
        int state1 =0;
        if(state.equals("审核通过"))
            state1=1;
        if (state.equals("未通过审核"))
            state1=2;
        System.out.print("###"+state1);
        ArrayList<ApplyEntity> arrayList= applyService.getApplicationList(state1);
        System.out.println(arrayList);
        JSONArray array = JSONArray.fromObject(arrayList);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }
    /**
     * @param request
     * @param response
     * @return 得到一个申请的信息
     * @throws Exception
     */
    @RequestMapping("/getApplyInfo")
    public void getSampleInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id" );
        long id1 = Integer.parseInt(id);
        ApplyEntity apply = applyService.searchApplication(id1);
       JSONObject object = JSONObject.fromObject(apply);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(object.toString());
    }
    /**
     * @param request
     * @param response
     * @return 审核申请
     * @throws Exception
     */
    @RequestMapping("/dealApply")
    public void dealApply(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id" );
        String state = request.getParameter("state" );
        int state1 = Integer.parseInt(state);
        boolean bool = applyService.updateState(id,state1);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("success");
    }
}
