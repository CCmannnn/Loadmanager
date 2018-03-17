package com.zeng.Controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/v1/api")
public class HomeApiController{

    @ApiOperation(value = "一个测试API", notes = "第一个测试API")
    @ResponseBody
    @RequestMapping(value = "/test/{path}", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogArticleBeen", value = "文档对象", required = true, paramType = "body", dataType = "BlogArticleBeen"),
            @ApiImplicitParam(name = "path", value = "url上的数据", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "query", value = "query类型参数", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "apiKey", value = "header中的数据", required = true, paramType = "header", dataType = "String")
    })
    public JSONResult test(@RequestBody BlogArticleBeen blogArticleBeen,
            @PathVariable Long path,String query,
            @RequestHeader String apiKey,
            PageInfoBeen pageInfoBeen){


        System.out.println("blogArticleBeen.getLastUpdateTime():"+blogArticleBeen.getLastUpdateTime());
        System.out.println("blogArticleBeen.getSorter():"+blogArticleBeen.getSorter());
        System.out.println("path:"+path);
        System.out.println("query:"+query);
        System.out.println("apiKey:"+apiKey);
        System.out.println("pageInfoBeen.getNowPage():"+pageInfoBeen.getNowPage());
        System.out.println("pageInfoBeen.getPageSize():"+pageInfoBeen.getPageSize());

        JSONResult jsonResult = new JSONResult();
        jsonResult.setMessage("success");
        jsonResult.setMessageCode(null);
        jsonResult.setCode(0);
        jsonResult.setBody(null);
        return jsonResult;
    }


    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public  HomeApiController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public String helloFacebook(Model model) {
        if (null == connectionRepository.findPrimaryConnection(Facebook.class)) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";
    }

}
