package com.beanpodtech.pkms.iot.ota.service.impl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTDeviceImageRepository;
import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTDeviceRepository;
import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDevice;
import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDeviceImage;
import com.beanpodtech.pkms.iot.ota.service.IoTDeviceImageService;

/**
* @author dapeng
* @date 2019-08-12
*/
@Service
public class IoTDeviceImageServiceImpl implements IoTDeviceImageService {

    @Autowired
    private IoTDeviceImageRepository iotDeviceImageRepository;
    
    @Autowired
    private IoTDeviceRepository iotDeviceRepository;
    
    
    @Autowired  
    private MongoTemplate template;

    
    @Override
    public IoTDeviceImage findById(String id) {
        Optional<IoTDeviceImage> device = iotDeviceImageRepository.findById(id);
        return device.get();
    }

    @Override
    public IoTDeviceImage create(IoTDeviceImage resources) {
        return iotDeviceImageRepository.save(resources);
    }

    @Override
    public void update(IoTDeviceImage resources) {
        Optional<IoTDeviceImage> optionalDeviceImage = iotDeviceImageRepository.findById(resources.getId());
//        ValidationUtil.isNull( optionalDeviceImage,"IoTDeviceImage","id",resources.getId());
        IoTDeviceImage de = optionalDeviceImage.get();
        de.copy(resources);
        iotDeviceImageRepository.save(de);
    }

	@Override
	public Object queryAllSemiAutomatic(IoTDeviceImage criteria, Pageable pageable) {
//		@Query("{ 'firstname' : ?0 }")
		
	    IoTDeviceImage t = new IoTDeviceImage();
	    BeanUtils.copyProperties(criteria, t);
	    
	    //创建匹配器，即如何使用查询条件
	    ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
	            .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//	            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
	            .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询
	            .withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询

	    //创建实例
	    Example<IoTDeviceImage> example = Example.of(t, matcher);
	    Page<IoTDeviceImage> s = iotDeviceImageRepository.findAll(example, pageable);
	    
//	链接：https://www.imooc.com/article/42399
		return s;
	}
	
	
	/*
	 * 不分页
	 */
	@Override
	public List<IoTDeviceImage> queryListNoPage(IoTDeviceImage criteria) {
		IoTDeviceImage t = new IoTDeviceImage();
		BeanUtils.copyProperties(criteria, t);
		System.out.println("-----------------------3333---------------------------"+JSONObject.toJSONString(t));
		List<IoTDeviceImage> list = iotDeviceImageRepository.findAll();
		System.out.println("-----------------------3333 0000---------------------------"+JSONObject.toJSONString(list));
		
		List<IoTDeviceImage> res = new ArrayList<>();
		for(IoTDeviceImage temp : list) {
			System.out.println(temp+"---temp.getDeviceid()-------------------"+temp.getDeviceid());
			if(criteria.getDeviceid().equals(temp.getDeviceid())) {
				res.add(temp);
			}
		}
		
		System.out.println("-----------------------4444---------------------------"+JSONObject.toJSONString(res));
		return res;
	}
	
	/*
	 * 不分页
	 */
//	@Override
	public List<IoTDeviceImage> queryListNoPage2(IoTDeviceImage criteria) {
		IoTDeviceImage t = new IoTDeviceImage();
		BeanUtils.copyProperties(criteria, t);
		System.out.println("-----------------------3333---------------------------"+JSONObject.toJSONString(t));
		List<IoTDeviceImage> list = iotDeviceImageRepository.findAll();
		System.out.println("-----------------------3333 0000---------------------------"+JSONObject.toJSONString(list));
		
		
		//创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
				.withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//	            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
				.withMatcher("deviceid", ExampleMatcher.GenericPropertyMatchers.exact()) //
//				.withIgnorePaths("pageNum", "pageSize")
				;  //忽略属性，不参与查询
		
		//创建实例
		Example<IoTDeviceImage> example = Example.of(t, matcher);
		List<IoTDeviceImage> s = iotDeviceImageRepository.findAll(example);
		
		System.out.println("-----------------------4444---------------------------"+JSONObject.toJSONString(s));
		return s;
		
		
	}
	
	@Override
	public Object queryAllSemiAutomaticComplex(IoTDeviceImage criteria, Pageable pageable) {
	    IoTDevice t = new IoTDevice();
	    BeanUtils.copyProperties(criteria, t);
	    
	    //创建匹配器，即如何使用查询条件
	    ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
	            .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//	            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
	            .withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询

	    //创建实例
	    Example<IoTDevice> example = Example.of(t, matcher);
	    Page<IoTDevice> s = iotDeviceRepository.findAll(example, pageable);
	    
	    List<IoTDeviceImage> content = new ArrayList<>(s.getPageable().getPageSize());
	    
	    List<IoTDevice> currentPage = s.getContent();
	    for(IoTDevice d : currentPage) {
	    	IoTDeviceImage di = new IoTDeviceImage();
	    	di.setDeviceid(d.getDeviceid());
	    	
	    	IoTDeviceImage criteria2 = new IoTDeviceImage();
	    	criteria2.setDeviceid(d.getDeviceid());
	    	List<IoTDeviceImage> list = this.queryListNoPage(criteria2);
	    	
	    	if(list != null && list.size() > 1) {
	    		System.out.println("数据有误，同一个deviceid查到了多个镜像");
	    	}else {
	    		if(list.size() == 1) {
	    			di = list.get(0);
	    		}
	    	}
	    	content.add(di);
	    }
	    Page<IoTDeviceImage> page = new PageImpl<>(content, s.getPageable(), s.getTotalElements());
	    //	链接：https://www.imooc.com/article/42399
		return page;
	}
	
	
	
	/*
	 * 目前4个参数都没用上
	 * @see me.monkey.modules.system.service.IoTDeviceImageService#findList(me.monkey.modules.system.service.dto.mongodb.IoTDeviceImage, org.springframework.data.domain.Pageable, java.lang.String, java.lang.String)
	 */
	@Override
	public List<IoTDeviceImage> findList(IoTDeviceImage criteria, Pageable pageable, LocalDate d1, LocalDate d2) {
//		Pageable pageable2 = PageRequest.of(0, 10,Sort.Direction.DESC,"createtime");
		
        Query query = new Query();
        Criteria cri = new Criteria();
	   //精确查询  
//	    cri.and("sid").is(183l);
	   //模糊查询  
//	    cri.and("title").regex(".*?"+"fileName"+".*");
        
        //必须是日期类型，如果转成字符串 会查不出来
        System.out.println("d1= "+d1);
        System.out.println("d2= "+d2);
        cri.and("createtime").gte(d1).lte(d2);
        
        Sort sort = new Sort(Sort.Direction.DESC, "createtime")
        		.and(new Sort(Sort.Direction.DESC, "id"));
        if (null != sort) {
        	query.with(sort);
        }
		query.addCriteria(cri);
        
		//查询出一共的条数  
//		Long count =  template.count(query, IoTDeviceImage.class); 
//		List<IoTDeviceImage> list = template.find(query.with(pageable), IoTDeviceImage.class);
		
		//将集合与分页结果封装  
//		Page<IoTDeviceImage> pagelist = new PageImpl<IoTDeviceImage>(list, pageable, count); 
		List<IoTDeviceImage> list = template.find(query, IoTDeviceImage.class);  
		return list;  
	}
	
//	@Override
	public List<IoTDeviceImage> findListByDeviceid(IoTDeviceImage criteria) {
		IoTDeviceImage c = new IoTDeviceImage();
		c.setDeviceid(criteria.getDeviceid());
		
		Set deviceidAll = new HashSet<>();
		deviceidAll.add(criteria.getDeviceid());
		
		Query query = new Query();
		Criteria cri = new Criteria();
		//精确查询  
//	    cri.and("sid").is(183l);
		//模糊查询  
//	    cri.and("title").regex(".*?"+"fileName"+".*");
		
		cri.and("deviceid").in(deviceidAll);
		
		Sort sort = new Sort(Sort.Direction.DESC, "createtime")
				.and(new Sort(Sort.Direction.DESC, "id"));
		if (null != sort) {
			query.with(sort);
		}
		query.addCriteria(cri);
		
		List<IoTDeviceImage> list = template.find(query, IoTDeviceImage.class);  
		return list;  
	}


}