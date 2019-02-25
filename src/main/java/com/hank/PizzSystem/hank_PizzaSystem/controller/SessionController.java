package com.hank.PizzSystem.hank_PizzaSystem.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {
    @RequestMapping(value = "/setSession")
    public Map<String,Object> setSession(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        request.getSession().setAttribute("message",request.getRequestURI());
        map.put("request Url",request.getRequestURI());
        return map;

    }


    @RequestMapping(value = "/getSession")
    public Map<String,Object> getSession(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("sessionId",request.getSession().getId());
        map.put("message",request.getSession().getAttribute("message"));
        return map;
    }
}
