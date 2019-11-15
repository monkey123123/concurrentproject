package com.beanpodtech.pkms.iot.ota.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTDeviceRepository;
import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDevice;
import com.beanpodtech.pkms.iot.ota.service.IoTDeviceService;

/**
* @author dapeng
* @date 2019-08-12
*/
@Service
public class IoTDeviceServiceImpl implements IoTDeviceService {

    @Autowired
    private IoTDeviceRepository iotDeviceRepository;

    @Override
    public IoTDevice findById(String id) {
        Optional<IoTDevice> device = iotDeviceRepository.findById(id);
//        ValidationUtil.isNull(device,"Device","id",id);
        return device.get();
    }

    @Override
    public IoTDevice create(IoTDevice resources) {
        return iotDeviceRepository.save(resources);
    }

    @Override
    public void update(IoTDevice resources) {
        Optional<IoTDevice> optionalDevice = iotDeviceRepository.findById(resources.getId());
//        ValidationUtil.isNull( optionalDevice,"IoTDevice","id",resources.getId());
        IoTDevice device = optionalDevice.get();
        device.copy(resources);
        iotDeviceRepository.save(device);
    }


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object queryAllSemiAutomatic(IoTDevice criteria, Pageable pageable) {
//		@Query("{ 'firstname' : ?0 }")
		
	    IoTDevice t = new IoTDevice();
	    BeanUtils.copyProperties(criteria, t);
	    
	    //创建匹配器，即如何使用查询条件
	    ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
	            .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//	            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
	            .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询
	            .withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询

	    //创建实例
	    Example<IoTDevice> example = Example.of(t, matcher);
	    Page<IoTDevice> s = iotDeviceRepository.findAll(example, pageable);

	    //	链接：https://www.imooc.com/article/42399
		return s;
	}
	@Override
	public List<IoTDevice> queryAllSemiAutomaticNoPage(IoTDevice criteria) {
		IoTDevice t = new IoTDevice();
		BeanUtils.copyProperties(criteria, t);
		
		//创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
				.withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
	            .withMatcher("location", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询contains; exact精确
				.withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询
				.withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询
		
		//创建实例
		Example<IoTDevice> example = Example.of(t, matcher);
		List<IoTDevice> list = iotDeviceRepository.findAll(example);
		return list;
	}
	
	@Override
	public IoTDevice findOne(IoTDevice t) {
		//创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
				.withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//	            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
				.withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询
				.withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询
		
		//创建实例
		Example<IoTDevice> example = Example.of(t, matcher);
		Optional<IoTDevice> s = iotDeviceRepository.findOne(example);
		
//	链接：https://www.imooc.com/article/42399
		return s.get();
	}

}