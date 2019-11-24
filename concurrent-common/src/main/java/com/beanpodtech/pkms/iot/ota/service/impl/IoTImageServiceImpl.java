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
import org.springframework.transaction.annotation.Transactional;

import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTImageRepository;
import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTImage;
import com.beanpodtech.pkms.iot.ota.service.IoTImageService;

/**
* @author dapeng
* @date 2019-08-12
*/
@Service
public class IoTImageServiceImpl implements IoTImageService {

    @Autowired
    private IoTImageRepository iotImageRepository;

    public static final String SUCCESS = "success";

    public static final String CODE = "code";

    public static final String MSG = "msg";

    @Override
    public IoTImage findById(String id) {
        Optional<IoTImage> image = iotImageRepository.findById(id);
//        ValidationUtil.isNull(image,"Image","id",id);
        return image.get();
    }

    @Override
    public IoTImage create(IoTImage resources) {
        return iotImageRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(IoTImage resources) {
        Optional<IoTImage> optionalImage = iotImageRepository.findById(resources.getId());
//        ValidationUtil.isNull( optionalImage,"IoTImage","id",resources.getId());
        IoTImage image = optionalImage.get();
        image.copy(resources);
        iotImageRepository.save(image);
    }



	@Override
	public Object queryAllSemiAutomatic(IoTImage criteria, Pageable pageable) {
//		@Query("{ 'firstname' : ?0 }")
		
	    IoTImage t = new IoTImage();
	    BeanUtils.copyProperties(criteria, t);
	    
	    //创建匹配器，即如何使用查询条件
	    ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
	            .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//	            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
	            .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询
	            .withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询

	    //创建实例
	    Example<IoTImage> example = Example.of(t, matcher);
	    Page<IoTImage> s = iotImageRepository.findAll(example, pageable);

	    //	链接：https://www.imooc.com/article/42399
		return s;
	}
	@Override
	public List<IoTImage> queryAll() {
		return iotImageRepository.findAll();
	}
	@Override
	public List<IoTImage> queryAllSemiAutomaticNoPage(IoTImage criteria) {
		IoTImage t = new IoTImage();
		BeanUtils.copyProperties(criteria, t);
		
		//创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
				.withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
				.withMatcher("location", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询contains; exact精确
				.withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询
				.withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询
		
		//创建实例
		Example<IoTImage> example = Example.of(t, matcher);
		List<IoTImage> list = iotImageRepository.findAll(example);
		return list;
	}
	
	@Override
	public IoTImage findOne(IoTImage t) {
		//创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
				.withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//	            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
				.withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact()) //采用“包含匹配”的方式查询
				.withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询
		
		//创建实例
		Example<IoTImage> example = Example.of(t, matcher);
		Optional<IoTImage> s = iotImageRepository.findOne(example);
		
		return s.get();
	}
	
	
	

}