package com.gree.ant.controller;

import com.gree.ant.mo.BaseMoFactory;
import com.gree.ant.mo.BussMoFactory;
import com.gree.ant.util.ResultUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.vo.Cbase016VO;
import com.gree.ant.vo.Tbuss009VO;
import com.gree.ant.vo.response.DocTypeVO;
import com.gree.ant.vo.response.DocVO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@At("/search/doc")
@Ok("json")
@IocBean
@Filters
public class DocSearchController {

    @Inject
    private BaseMoFactory baseMoFactory;

    @Inject
    private BussMoFactory bussMoFactory;

    @At("/index")
    @Ok("jsp:jsp.doc.search.index")
    public String searchIndex(){
        return "search";
    }

    @POST
    @At("/getAllDocType")
    public Map<String,Object> getAllDocType(){
        List<Cbase016VO> cbase016VOList = baseMoFactory.getCbase016MO().queryAllSearch();
        return ResultUtil.getResult(200,"请求成功",getDocTypeView(cbase016VOList));
    }

    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @At("/getAllDocByType")
    public Map<String,Object> getAllDocByType(@Param("type")String type,@Param("key")String key){
        type = StringUtil.checkString(type)?type:"";
        key = StringUtil.checkString(key)?key:"";
        List<Tbuss009VO> tbuss009VOS = bussMoFactory.getTbuss009MO().queryAllDOc(type,key);
        return ResultUtil.getResult(200,"请求成功",getDocView(tbuss009VOS));
    }

    private List<DocTypeVO> getDocTypeView(List<Cbase016VO> cbase016VOList){
        List<DocTypeVO> docTypeVOS = new ArrayList<>();
        for (Cbase016VO cbase016VO:cbase016VOList){
            docTypeVOS.add(cbase016VO.builderBussiness());
        }
        return docTypeVOS;
    }

    private List<DocVO> getDocView(List<Tbuss009VO> tbuss009VOList){
        List<DocVO> docVOS = new ArrayList<>();
        for (Tbuss009VO tbuss009VO:tbuss009VOList){
            docVOS.add(tbuss009VO.getDocVO());
        }
        return docVOS;
    }
}
