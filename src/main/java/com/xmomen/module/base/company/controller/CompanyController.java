package com.xmomen.module.base.company.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.company.mapper.CompanyMapper;
import com.xmomen.module.base.company.model.CompanyModel;
import com.xmomen.module.base.company.model.CreateCompany;
import com.xmomen.module.base.company.service.CompanyService;
import com.xmomen.module.logger.Log;
@RestController
public class CompanyController {
	@Autowired
	CompanyService companyService;
	@Autowired
	CompanyMapper companyMapper;
	@Autowired
    MybatisDao mybatisDao;
    /**
     * 查询单位公司信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @Log(actionName = "查询单位公司信息")
    public Page<CompanyModel> getMemberList(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "keyword", required = false) String keyword){
    	 Map map = new HashMap<String,Object>();
         map.put("id", id);
         map.put("keyword", keyword);
        return (Page<CompanyModel>) mybatisDao.selectPage(CompanyMapper.CompanyMapperNameSpace + "getCompanyList", map, limit, offset);
    }
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @Log(actionName = "新增单位、公司")
    public void createCompany(@RequestBody @Valid CreateCompany createCompany, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        companyService.createCompany(createCompany);
    }
}