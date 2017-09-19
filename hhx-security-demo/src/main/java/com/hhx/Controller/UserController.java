package com.hhx.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hhx.entity.User;
import com.hhx.service.UserService;
import com.hhx.util.ObjectUtil;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetail.class)
    public User getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @JsonView(User.SimpleUserView.class)
    @GetMapping
    public List<User> getAll(@PageableDefault(page=0,size=10) Pageable pageable){
        //pageable 设置默认页码和条数以及排序规则等
        return service.getPage(pageable).getContent();
    }

    @PostMapping    //@Valid 检查字段是否合法,在插入数据库的时候会再检查一边。（由@Valid指定）
    public User create(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream()
                    .forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.DEFAULT_STYLE));
        return service.save(user);
    }

    @PutMapping("/{id:\\d+}")
    public User update(@PathVariable Integer id,@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream()
                    .forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        user.setId(null);//不允许指定ID
        User oldUser = service.getById(id);
        //更新新数据到数据库取出的实体类
        ObjectUtil.mergeObject(user,oldUser);
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.DEFAULT_STYLE));
        return service.updateUser(oldUser);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Integer id){
        throw new RuntimeException("test");
        //service.deleteById(id);
    }
}
