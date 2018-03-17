package com.zeng.Controller;

import com.zeng.dao.CategoryDAO;
import com.zeng.entity.Category;
import com.zeng.entity.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Administrator
 */
@Controller
public class CategoryController {

    @RequestMapping("/")
    public String index(io.swagger.models.Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }


    @Autowired
    CategoryDAO categoryDAO;

    @RequestMapping("/listCategory")
    public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        SpringDataWebProperties.Sort sort = new SpringDataWebProperties.Sort(Sort.Direction.DESC, "id");
        SpringDataWebProperties.Pageable pageable = new PageRequest(size, start, sort);
        Page<Category> page =categoryDAO.findAll((Pageable) pageable);

        m.addAttribute("page", page);
        return "listCategory";
    }

    @RequestMapping("/addCategory")
    public String addCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category c) throws Exception {
        categoryDAO.delete(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/editCategory")
    public String editCategory(int id,Model m) throws Exception {
        Category c= categoryDAO.getOne(id);
        m.addAttribute("c", c);
        return "editCategory";
    }


}