package com.hwua.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwua.dao.AuthMapper;
import com.hwua.po.Sys_Auth;
import com.hwua.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    //查询权限列表
    @Override
    public JSONObject getAllAuths() {
        List<Map<String, Object>> list = authMapper.getAllAuths();

        for(Map<String, Object> map:list){
            Integer type = (Integer) map.get("auth_type");
            map.put("auth_type",type==0?"菜单":"资源");

            Integer valid = (Integer) map.get("is_valid");
            map.put("is_valid",valid==0?"启用":"禁用");
        }


        String s = JSON.toJSONString(list);
        JSONArray array = JSONArray.parseArray(s);

        Integer count = authMapper.countAuth();

        JSONObject obj = new JSONObject();
        obj.put("data",array);
        obj.put("code",0);
        obj.put("msg","ok");
        obj.put("count",count);

        return obj;
    }

    //添加或修改权限
    @Override
    public boolean addOrUpdateAuth(Sys_Auth auth) {
        if(auth.getAuth_id()==null){
            Integer add = authMapper.addAuth(auth);
            return add==1;
        }else{
            Integer edit = authMapper.editAuth(auth);
            return edit==1;
        }
    }

}
