package com.zzr.spring.mybatis.controller;

import com.zzr.spring.mybatis.common.JsonResp;
import com.zzr.spring.mybatis.common.PagerForDT;
import com.zzr.spring.mybatis.common.PagerResultForDT;
import com.zzr.spring.mybatis.entity.PowerEntity;
import com.zzr.spring.mybatis.service.PowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zhaozhirong
 * @Date: 2019/01/14
 * @Description:
 */
@Controller
@RequestMapping("power")
public class PowerController {

    private final Logger logger = LoggerFactory.getLogger(PowerController.class);

    @Autowired
    private PowerService powerService;



    @ResponseBody
    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    public PagerResultForDT findPage(HttpServletRequest request, PagerForDT pagerForDataTable, String code) {
        pagerForDataTable.setCondition(code);
        PagerResultForDT pagerResult = powerService.selectPage(pagerForDataTable);
        return pagerResult.initsEcho(request.getParameter("sEcho"));
    }

    @ResponseBody
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public JsonResp findAll() {
        JsonResp resp = new JsonResp();
        try {
            List<PowerEntity> powerEntityList = powerService.findAll();
            resp.setCode(JsonResp.SUCCESS);
            resp.setResult(powerEntityList);
        }catch (Exception e){
            logger.error("power findAll is error", e);
            resp.setCode(JsonResp.FAIL);
            resp.setMessage(e.getMessage());
        }
       return resp;
    }

    @ResponseBody
    @RequestMapping(value = "findByRoleCode", method = RequestMethod.GET)
    public JsonResp findByRoleCode(Integer roleCode) {
        JsonResp resp = new JsonResp();
        try {
            List<PowerEntity> powerEntityList = powerService.findByRoleCode(roleCode);
            resp.setCode(JsonResp.SUCCESS);
            resp.setResult(powerEntityList);
        }catch (Exception e){
            logger.error("power findAll is error", e);
            resp.setCode(JsonResp.FAIL);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "checkCode", method = {RequestMethod.POST, RequestMethod.GET})
    public Boolean checkCode(String code) {
        Boolean result = false;
        try {
             Boolean hasCode = powerService.hasCode(code.trim());
             result =  !hasCode;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "addPower", method = RequestMethod.POST)
    public JsonResp addPower(PowerEntity powerEntity) {
        JsonResp resp = new JsonResp();
        try {
            powerService.save(powerEntity);
            resp.setCode(JsonResp.SUCCESS);
            resp.setMessage("success");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            resp.setCode(JsonResp.FAIL);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonResp delete(Integer id) {
        JsonResp resp = new JsonResp();
        if(id == null){
            resp.setCode(JsonResp.FAIL);
            resp.setMessage("id can not be null");
            return resp;
        }
        try {
            powerService.delete(id);
            resp.setCode(JsonResp.SUCCESS);
            resp.setMessage("success");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            resp.setCode(JsonResp.FAIL);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

}
