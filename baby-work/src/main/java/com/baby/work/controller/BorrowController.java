package com.baby.work.controller;


import com.baby.common.exception.P2PException;
import com.baby.common.utils.FinanceCalcUtils;
import com.baby.common.utils.RepaymentTypeConst;
import com.baby.common.utils.ResponseCode;
import com.baby.common.utils.Result;
import com.baby.work.pojo.Borrow;
import com.baby.work.query.BorrowQuery;
import com.baby.work.query.PageQuery;
import com.baby.work.service.BorrowService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.baby.common.utils.Result;
import com.baby.work.pojo.Borrow;
import com.baby.work.service.BorrowService;
import com.baby.work.vo.BasicVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Adorez
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/admin/borrow")
@Api(tags = "借款管理")
public class BorrowController {
    @Autowired
    BorrowService borrowService;
    @GetMapping(value = "{pageSize}/{nowPage}")
    public Result query(
            @PathVariable("pageSize") Integer pageSize,@PathVariable("nowPage") Integer nowPage,
            @ApiParam(name = "BorrowQuery",value = "借款对象",required = false)BorrowQuery borrowQuery) throws P2PException {
       PageQuery pageQuery=new PageQuery();
       pageQuery.setPageSize(pageSize);
       pageQuery.setNowPage(nowPage);
        System.out.println(pageQuery.getPageSize()+"aaaa");
        if(pageQuery.getPageSize()<=0 || pageQuery.getNowPage()<=0){
                throw new P2PException(ResponseCode.PAGE_LIMIT_FAILD);
        }
        Page<Borrow> pageParam=new Page<>(pageQuery.getPageSize(),pageQuery.getNowPage());
        System.out.println(pageParam+"pageParam");
            borrowService.qryBorrowInfoPage(pageParam,borrowQuery);
        List<Borrow> record=pageParam.getRecords();
        long total=pageParam.getTotal();
        System.out.println(total+"total");
            return Result.success().result("datalist",record).result("allCount",total);
    }


    @PostMapping(value = "add")
    public Result add(@ApiParam(name = "Borrow" ,value = "借款对象",required = true)Borrow borrow) throws ParseException {
        Borrow borrow1=new Borrow();
        String id = UUID.randomUUID().toString();
        System.out.println(id+"idididididiidid");
        String borrowUserId=UUID.randomUUID().toString();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
     String date=  df.format(new Date());// new Date()为获取当前系统时间
        Date time = df.parse(date);
        System.out.println(time+"time");
        borrow1.setId(id);
        borrow1.setBorrowUserId(borrowUserId);
        borrow1.setApplyTime(time);
        borrow1.setTitle(borrow.getTitle());
        System.out.println(borrow1.setTitle(borrow.getTitle())+"adadad");
               borrow1.setBorrowAmount(borrow.getBorrowAmount());
               borrow1.setRepaymentMonth(borrow.getRepaymentMonth());
               borrow1.setBidDays(borrow.getBidDays());
               borrow1.setDescription(borrow.getDescription());
               borrow1.setYearRate(borrow.getYearRate());
               borrow1.setRepaymentType(borrow.getRepaymentType());
               if(borrow.getRepaymentType()==1){
                   System.out.println("进入等额本息");
                long result=   FinanceCalcUtils.calcTotalInterest(borrow1.getRepaymentType(),borrow1.getBorrowAmount(),borrow1.getYearRate(),borrow1.getRepaymentMonth());
                   System.out.println(result+"易洋易洋易洋");
                   borrow1.setTotalInterest(result);
               }else if(borrow.getRepaymentType()==2){
                   System.out.println("进入先息后本");
                   long result=   FinanceCalcUtils.calcTotalInterest(borrow1.getRepaymentType(),borrow1.getBorrowAmount(),borrow1.getYearRate(),borrow1.getRepaymentMonth());
                   System.out.println(result+"易洋易洋易洋");
                   borrow1.setTotalInterest(result);
               }
                borrowService.add(borrow1);
        return Result.success();
    }


    @PostMapping(value = "getById")
    public Result getById(@RequestParam("id") String id){
        System.out.println(id+"=========getid");
       Borrow list= borrowService.getById(id);
        System.out.println(list+"=======list");
        return Result.success().result("id",id).result("result",list);
    }

    @PostMapping(value = "updateStatus")
    public Result updateStatus(@ApiParam(value = "修改状态对象(申请同意时)",name = "Borrow",required = true) Borrow borrow){
            boolean borrow1= borrowService.updateById(borrow);
            return Result.success().result("result",borrow1);
    }
    @PostMapping(value = "RefuseStatus")
    public Result RefuseStatus(@ApiParam(value = "修改状态对象(申请拒绝时)",name = "Borrow",required = true) Borrow borrow) {
        boolean borrow1 = borrowService.updateById(borrow);
        return Result.success().result("result", borrow1);
    }
    @PostMapping("/getBorrowInfo")
    @ApiOperation(value = "分页获取借款信息")
    public Result getBorrowInfo(@ApiParam(name = "basicVo",value = "借款查询对象") BasicVo basicVo){
        Page<Borrow> page=new Page<>(basicVo.getNowPage(),basicVo.getPageSize());
        borrowService.getBorrowInfo(page,basicVo);
        return Result.success().result("datalist",page.getRecords()).result("allCount",page.getTotal());
    }
}

