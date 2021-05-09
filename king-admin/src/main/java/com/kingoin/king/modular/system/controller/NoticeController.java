package com.kingoin.king.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.kingoin.king.core.base.controller.BaseController;
import com.kingoin.king.core.common.annotion.BussinessLog;
import com.kingoin.king.core.common.constant.dictmap.NoticeMap;
import com.kingoin.king.core.common.constant.factory.ConstantFactory;
import com.kingoin.king.core.common.exception.BizExceptionEnum;
import com.kingoin.king.core.exception.KingException;
import com.kingoin.king.core.log.LogObjectHolder;
import com.kingoin.king.core.shiro.ShiroKit;
import com.kingoin.king.core.util.ToolUtil;
import com.kingoin.king.modular.biz.dao.MeterialMapper;
import com.kingoin.king.modular.biz.dao.MeterialTypeMapper;
import com.kingoin.king.modular.biz.dao.mapping.MeterialDetailMapper;
import com.kingoin.king.modular.biz.model.Meterial;
import com.kingoin.king.modular.biz.model.MeterialDetail;
import com.kingoin.king.modular.biz.model.MeterialType;
import com.kingoin.king.modular.system.model.Notice;
import com.kingoin.king.modular.system.service.INoticeService;
import com.kingoin.king.modular.system.warpper.NoticeWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 通知控制器  改为资料管理
 *
 * @author jack
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private String PREFIX = "/system/notice/";

    @Autowired
    private INoticeService noticeService;

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("")
    public String index() {
        //return PREFIX + "notice.html";
        return PREFIX + "wenzhang.html";
    }


    /**
     * 跳转到添加
     */
    @RequestMapping("/notice_add")
    public String noticeAdd(Model model) {
        Wrapper<MeterialType> type = new EntityWrapper<>();
        type.eq("is_valid",1);
        List<MeterialType> list = meterialTypeMapper.selectList(type);
        model.addAttribute("type", list);
        //return PREFIX + "notice_add.html";
        return PREFIX + "wenzhangadd.html";
    }

    /**
     * 跳转到添加品类
     */
    @RequestMapping("/type_add")
    public String TypeAdd() {
        return PREFIX + "type_add.html";
    }

    /**
     * 跳转到添加通知
     */
    @RequestMapping("/meterial_add")
    public String meterialAdd() {
        return PREFIX + "meterial_add.html";
    }
    /**
     * 跳转到添加通知
     */
    @RequestMapping("/net_meterial_add")
    public String netMeterialAdd(Model model) {
        Wrapper<MeterialType> type = new EntityWrapper<>();
        type.eq("is_valid",1);
        List<MeterialType> list = meterialTypeMapper.selectList(type);
        model.addAttribute("type", list);

        Wrapper<Meterial> wrapper = new EntityWrapper<>();
        wrapper.eq("is_valid",1);
        List<Meterial> file = meterialMapper.selectList(wrapper);
        model.addAttribute("file", file);
        return PREFIX + "net_meterial_add.html";
    }

    /**
     * 跳转到添加通知
     */
    @RequestMapping("/meterialUpload")
    public String meterialUpload() {
        return PREFIX + "meterial_upload.html";
    }
    /**
     * 跳转到添加通知
     */
    @RequestMapping("/meterialList")
    @ResponseBody
    public Object meterialList(String condition) {
        List<Map<String,Object>> list = meterialMapper.selectMeterialDetailList(condition);
        return list;
    }
    /**
     * 品类自定义列表查询
     */
    @Autowired
    private MeterialTypeMapper meterialTypeMapper;
    @RequestMapping("/typeList")
    @ResponseBody
    public Object typeList(String condition) {
        List<Map<String,Object>> list = meterialTypeMapper.selectMeterialTypeList(condition);
        return list;
    }

    /**
     * 跳转到通知列表首页
     */
    @RequestMapping("/meterialType")
    public String meterialType() {
        return PREFIX + "meterialType.html";
    }

    /**
     * 跳转到修改通知
     */
    @RequestMapping("/notice_update/{noticeId}")
    public String noticeUpdate(@PathVariable Integer noticeId, Model model) {
        Meterial notice = meterialMapper.selectById(noticeId);
        model.addAttribute("notice",notice);
        LogObjectHolder.me().set(notice);
        Wrapper<MeterialType> type = new EntityWrapper<>();
        type.eq("is_valid",1);
        List<MeterialType> list = meterialTypeMapper.selectList(type);
        model.addAttribute("type", list);

        //return PREFIX + "notice_edit.html";
        return PREFIX + "wenzhangedit.html";
    }
    /**
     * 跳转到修改通知
     */
    @RequestMapping("/meterial_update/{noticeId}")
    public String meterialUpdate(@PathVariable Integer noticeId, Model model) {
        MeterialDetail notice = meterialDetailMapper.selectById(noticeId);
        Map<String,Object> map = new HashMap<>();
        map.put("id",notice.getId());
        map.put("meterialName",notice.getMeterialName());
        map.put("url",notice.getUrl());
        map.put("remark",notice.getRemark());
        model.addAttribute("meterial",map);
        LogObjectHolder.me().set(notice);
        return PREFIX + "meterial_edit.html";
    }
    /**
     * 跳转到修改通知
     */
    @RequestMapping("/addNetLinkFile")
    @ResponseBody
    public Object addNetLinkFile(MeterialDetail meterialDetail) {
        System.out.println(meterialDetail.toString());
        meterialDetail.setCreateDate(new Date());
        meterialDetail.setIsValid(1);
        meterialDetailMapper.insert(meterialDetail);
        return SUCCESS_TIP;
    }


    /**
     * 跳转到修改通知
     */
    @RequestMapping("/type_update/{typeId}")
    public String typeUpdate(@PathVariable Integer typeId, Model model) {
        MeterialType type = meterialTypeMapper.selectById(typeId);
        Map<String,Object> map = new HashMap<>();
        System.out.println(type);
        map.put("id",type.getId());
        map.put("typeName",type.getTypeName());
        map.put("remark",type.getRemark());
        model.addAttribute("type",map);
        LogObjectHolder.me().set(type);
        return PREFIX + "type_edit.html";
    }

    /**
     * 跳转到首页通知
     */
    @RequestMapping("/hello")
    public String hello() {
        List<Map<String, Object>> notices = noticeService.list(null);
        super.setAttr("noticeList",notices);
        return "/blackboard.html";
    }

    @Autowired
    private MeterialMapper meterialMapper;

    @Autowired
    private MeterialDetailMapper meterialDetailMapper;

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Meterial> list = meterialMapper.selectMeterialList(condition);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for(Meterial meterial:list){
            Map<String,Object> map = new HashMap<>();
            map.put("id",meterial.getId());
            map.put("meterialName",meterial.getMeterialName());
            map.put("remark",meterial.getRemark());
            MeterialType type = meterialTypeMapper.selectById(meterial.getType());
            map.put("type",type.getTypeName());
            map.put("createBy",meterial.getCreateBy());
            map.put("createDate",meterial.getCreateDate());
            map.put("content",meterial.getContent());
            mapList.add(map);
        }
        return mapList;
    }


    /**
     * 新增通知
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Meterial meterial) {

//        if (ToolUtil.isOneEmpty(meterial, meterial.getMeterialName(), meterial.getType())) {
//            throw new KingException(BizExceptionEnum.REQUEST_NULL);
//        }
        meterial.setCreateBy("管理员");
        meterial.setCreateDate(new Date());
        meterial.setIsValid(1);
        meterialMapper.insert(meterial);
        return SUCCESS_TIP;
    }
    /**
     * 新增通知
     */
    @RequestMapping(value = "/addType")
    @ResponseBody
    public Object addType(MeterialType meterialType) {
        System.out.println(meterialType.toString());
        if (ToolUtil.isOneEmpty(meterialType, meterialType.getTypeName())) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        meterialType.setCreateBy("管理员");
        meterialType.setCreateDate(new Date());
        meterialType.setIsValid(1);
        meterialTypeMapper.insert(meterialType);
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除通知",key = "noticeId",dict = NoticeMap.class)
    public Object delete(@RequestParam Integer noticeId) {
        //删除文件
        Meterial meterial = meterialMapper.selectById(noticeId);
        meterial.setIsValid(0);
        meterialMapper.updateById(meterial);
//        //删除问价家里的文件
//        Wrapper<MeterialDetail> meterialDetailWrapper = new EntityWrapper<>();
//        meterialDetailWrapper.eq("file_id",noticeId);
//        List<MeterialDetail> meterialDetails = meterialDetailMapper.selectList(meterialDetailWrapper);
//        if(ToolUtil.isNotEmpty(meterialDetails)){
//            for(MeterialDetail po:meterialDetails){
//                po.setIsValid(0);
//                meterialDetailMapper.updateById(po);
//            }
//
//        }

        return SUCCESS_TIP;
    }


    /**
     * 删除通知
     */
    @RequestMapping(value = "/deleteMeterial")
    @ResponseBody
    public Object deleteMeterial(@RequestParam Integer noticeId) {
        //删除文件
        MeterialDetail meterial = meterialDetailMapper.selectById(noticeId);
        meterial.setIsValid(0);
        meterialDetailMapper.updateById(meterial);
        return SUCCESS_TIP;
    }
    /**
     * 删除品类
     */
    @RequestMapping(value = "/deleteType")
    @ResponseBody
    public Object deleteType(@RequestParam Integer typeId) {
        //删除品类
        MeterialType type = meterialTypeMapper.selectById(typeId);
        type.setIsValid(0);
        meterialTypeMapper.updateById(type);
        //删除品类下所有文件夹
        Wrapper<Meterial> entity = new EntityWrapper<Meterial>();
        entity.eq("type",typeId);
        List<Meterial> meterialList = meterialMapper.selectList(entity);
        if(ToolUtil.isNotEmpty(meterialList)){
            for(Meterial po : meterialList){
                po.setIsValid(0);
                meterialMapper.updateById(po);
//                //删除文件下所有文件夹
//                Wrapper<MeterialDetail> meterialDetailWrapper = new EntityWrapper<>();
//                meterialDetailWrapper.eq("file_id",po.getId());
//                List<MeterialDetail> meterialDetails = meterialDetailMapper.selectList(meterialDetailWrapper);
//                if(ToolUtil.isNotEmpty(meterialDetails)){
//                    for(MeterialDetail detail:meterialDetails){
//                        detail.setIsValid(0);
//                        meterialDetailMapper.updateById(detail);
//                    }
//                }
            }
        }

        //删除品类文件夹下所有文件

        return SUCCESS_TIP;
    }

    /**
     * 修改通知
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Meterial notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getMeterialName(), notice.getType())) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        System.out.println(notice.toString());
        Meterial old = this.meterialMapper.selectById(notice.getId());
        old.setMeterialName(notice.getMeterialName());
        old.setType(notice.getType());
        old.setContent(notice.getContent());
        old.setRemark(notice.getRemark());
        meterialMapper.updateById(old);
        return SUCCESS_TIP;
    }
    /**
     * 修改通知
     */
    @RequestMapping(value = "/updateType")
    @ResponseBody
    public Object updateType(MeterialType type) {
        if (ToolUtil.isOneEmpty(type, type.getId(), type.getTypeName())) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        System.out.println(type.toString());
        MeterialType old = this.meterialTypeMapper.selectById(type.getId());
        old.setTypeName(type.getTypeName());
        old.setRemark(type.getRemark());
        meterialTypeMapper.updateById(old);
        return SUCCESS_TIP;
    }
    /**
     * 修改通知
     */
    @RequestMapping(value = "/meterialUpdate")
    @ResponseBody
    public Object update(MeterialDetail notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getMeterialName())) {
            throw new KingException(BizExceptionEnum.REQUEST_NULL);
        }
        System.out.println(notice.toString());
        MeterialDetail old = this.meterialDetailMapper.selectById(notice.getId());
        old.setMeterialName(notice.getMeterialName());
        old.setUrl(notice.getUrl());
        old.setRemark(notice.getRemark());
        meterialDetailMapper.updateById(old);
        return SUCCESS_TIP;
    }
}
