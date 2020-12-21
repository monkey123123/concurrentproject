package me.monkey.demo.transaction;

//import cn.sw.study.web.dao.UserMapper;
//
//import cn.sw.study.web.model.User;
//
//import cn.sw.study.web.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**

 在默认情况下，大部分使用spring的事务都是使用代理的模式，代理实现的事务有一定的局限性：
 仅有在公有方法上标记的@Transactional有效；仅有外部方法调用过程才会被代理截获，事务才会有效，
 也就是说，一个方法调用本对象的另一个方法，没有通过代理类，事务也就无法生效。下面来说明下解决的方法

 addInfo方法上没有事务注解，addOne方法上有事务注解，此时运行addInfo调用addOne方法，不会产生事务，测试数据遇到异常没有回滚。如果从外部类直接调用addOne方法，则事务是可以正常生效的。

 4
 如果想类内部方法调用可以正常使用事务，使用AopContext.currentProxy()来获取代理类再调用

 */

@Service("userService")
public class UserServiceImpl implements UserService{

//    @Autowired
//    UserMapper userMapper;

    @Override
    public void addInfo() {
        addOne();
    }

    @Override
    @Transactional
    public void addOne() {
//
//        User record = new User();
//
//        record.setLoginName("tom");
//
//        record.setPwd("111111");
//
//        record.setMobile("13913913913");
//
//        record.setUsable(1);
//
//        record.setCreateTime(new Date());
//
//        userMapper.insertSelective(record);

        int i = 1/0;    // 测试事物的回滚

    }

}