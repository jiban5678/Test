package com.aaa.sbm.controller;

import com.aaa.sbm.bo.TreeNode;
import com.aaa.sbm.entity.Dept;
import com.aaa.sbm.service.DeptService;
import com.aaa.sbm.util.CustomException;
import com.aaa.sbm.util.Result;
import com.aaa.sbm.util.ResultStatus;
import com.aaa.sbm.vo.QueryPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ fileName:DeptController
 * @ description:
 * @ author:zhz
 * @ createTime:2022/3/31 9:43
 * @ version:1.0.0
 */
@RestController // 相当于@Controller @ResponseBody
@RequestMapping("dept") //父路径映射，防止多个controller  请求路径重复
@ApiOperation("部门控制器")
@Log4j2  //lombok 简化日志写法
public class DeptController extends BaseController{

    @Resource
    private DeptService deptService;



    /**
     * 根据编号查询
     * @param deptNo
     * @return
     */
    @GetMapping("getById")
    public Result getById(Integer deptNo){
        if(deptNo==null){
            //抛出自定义异常
            throw new CustomException(ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnCode(),
                    ResultStatus.ARGUMENT_IS_NOT_NULL.getReturnMessage());
        }
        return success("模拟根据编号查询部门");
    }
    /**
     * 根据参数查询部门列表
     * @param queryPage
     * @return
     */
    @PostMapping("queryByParam") //一旦方法的参数中含有@RequestBody  请求不能是get请求
    @ApiOperation("部门查询")
    public Result queryByParam(@ApiParam("部门查询参数")@RequestBody QueryPage<Dept> queryPage) {
      /*  return new Result<List<Dept>>(ResultStatus.SUCCESS.getReturnCode(),
                ResultStatus.SUCCESS.getReturnMessage(),
                deptService.queryByParam(dept));*/

       //不推荐这样写  代码重复量大
        /*try {
          return  success(deptService.queryByParam(dept));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error("错误");*/
        return  success(deptService.queryByParam(queryPage));
    }

    /**
     * 部门添加方法
     * @param dept
     * @return
     */
    /*@PostMapping("add")
    public Result add(@RequestBody Dept dept) {
        return success(deptService.add(dept));
    }*/

    /**
     * 模拟添加
     * @param dept
     * @return
     */
    //@PostMapping("add")  //一定要是restful风格  不要使用@RequestMapping  本质是下面配置
    @RequestMapping(value = "add",method = RequestMethod.POST)  //等同于上面的配置
    //地址栏只能模拟get请求   put   post  delete请求都无法地址栏模拟，会报405 方法不支持
    @ApiOperation("部门添加")
    public Result add(@ApiParam("部门添加参数")@RequestBody Dept dept){
        log.info("部门信息为："+dept);
       /* return  new Result(ResultStatus.SUCCESS.getReturnCode(),
                ResultStatus.SUCCESS.getReturnMessage(),1);*/

        //不推荐这样写  代码重复量大
       /* try {
            return success(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error(0);*/

        return success(deptService.add(dept));
    }
}
