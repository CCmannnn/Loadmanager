package com.zeng.Controller;

import com.zeng.Service.UserService;
import com.zeng.dao.UserDao;
import com.zeng.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@RequestMapping(value="/springboot")
@RestController
@Api("userController相关api")
public class UserController {
    @Autowired
    public UserDao userDao;
    private UserService userService;

    @ApiOperation("获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="username",dataType="String",required=true,value="用户的姓名",defaultValue="zhaojigang"),
            @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码",defaultValue="wangna")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value="/getUser",method= RequestMethod.GET)
    public User getUser(@RequestHeader("username") String username, @RequestParam("password") String password) {
        return userService.getUser(username,password);
    }


    @RequestMapping(value="/getbyname")
    @ResponseBody
    public String getByName(String name) {
        String userId;
        User user = userDao.findByName(name);
        if (user != null) {
            userId = String.valueOf(user.getId());
            return "The user id is: " + userId;
        }
        return "user " + name + " is not exist.";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        User user = new User();
        user.setName("张三");
        User user2 = userDao.save(user);
        if (user != null) {
            return "The user id is: " + user2.getId();
        }
        return "user id is not exist.";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update() {
        User user = new User();
        user.setId(1);
        user.setName("李四");
        User user2 = userDao.save(user);
        return "The user id is: " + user2.getId();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        User user = new User();
        user.setId(2);
        userDao.delete(user);
        return "执行了删除操作";
    }

}
