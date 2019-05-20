package com.how2java.springboot.web;

import com.how2java.springboot.dao.CategoryDao;
import com.how2java.springboot.pojo.Category;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryDao categoryDao;
    @RequestMapping("/listCategory")
    public String listCaegory(Model m, @RequestParam(value = "start",defaultValue = "0") int start,
                              @RequestParam(value = "size",defaultValue = "5") int size) throws  Exception{
        start = (start < 0) ? 0 : start;

        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Category> page = categoryDao.findAll(pageable);

        System.out.println(page.getNumber());
        System.out.println(page.getNumberOfElements());
        System.out.println(page.getSize());
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());

        m.addAttribute("page", page);
        return "listCategory";
    }

    //增加
    @RequestMapping("/addCategory")
    public String addCategory(Category c)throws Exception{
        categoryDao.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/delCategory")
    public String delCategory(Category c) throws Exception{
        categoryDao.delete(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) throws Exception{
        categoryDao.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/selCategory")
    public String selCategory(int id,Model m) throws Exception{
        Category category = categoryDao.getOne(id);
        System.out.println(category.getId());
        m.addAttribute("category",category);
        return "selCategory";
    }

}
