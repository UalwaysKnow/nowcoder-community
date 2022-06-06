package com.nowcoder.community.controller;

import com.nowcoder.community.util.CommunityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // GET请求，可以指定method
    // 查询学生 - 两种方式来传递参数，两种情况使用不同的注解
    // /students?current=1&limit=20,
    @RequestMapping(path="/students", method= RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required=false, defaultValue="1") int current,
            @RequestParam(name = "limit", required=false, defaultValue="10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    // /students/123
    @RequestMapping(path="/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // POST请求 - get请求传递的参数是在url中的，而post是表单里的 ---- 这里不知道为什么行不通
    @RequestMapping(path="/student", method=RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 相应HTML数据 - 以下两种方式
    @RequestMapping(path="/teacher", method=RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");

        return mav;
    }
    @RequestMapping(path="/school", method=RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "张三");
        model.addAttribute("age", 30);
        return "/demo/view";
    }

    // 响应json数据（异步请求）
    // JSON兼容JAVA和JS -- java对象 -》 json字符串 -》js对象
    @RequestMapping(path="/emp", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    // Cookie 示例
    @RequestMapping(path="/cookie/set", method=RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置Cookie的生效范围
        cookie.setPath("/community/alpha");
        // 设置cookie的生存时间 - cookie信息存储在硬盘里
        cookie.setMaxAge(60*10);
        // 发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path="/cookie/get", method=RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) { // 取key为code的cookie值返回
        System.out.println(code);
        return "get cookie";
    }

    @RequestMapping(path="/session/set", method=RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "Test");
        return "set session";
    }

    @RequestMapping(path="/session/get", method=RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

    // AJAX示例
    @RequestMapping(path="/ajax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0, "操作成功");
    }

}
