package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true, StatusCode.OK,"新增成功！");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> labelList = labelService.findAll();
        return new Result(true, StatusCode.OK,"查询成功！",labelList);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{labelId}")
    public Result delete(@PathVariable String labelId){
        labelService.delete(labelId);
        return new Result(true, StatusCode.OK,"删除成功！");
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/{labelId}")
    public Result update(@PathVariable String labelId,@RequestBody Label label){
        label.setId(idWorker.nextId()+"");
        labelService.update(label);
        return new Result(true, StatusCode.OK,"修改成功！");
    }
    @RequestMapping(method = RequestMethod.GET,value = "/{labelId}")
    public Result findById(@PathVariable String labelId){
        Label label = labelService.findById(labelId);
        return new Result(true, StatusCode.OK,"查询成功！",label);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSeach(label);
        return new Result(true,StatusCode.OK,"查询成功!",list);
    }
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功!",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
