package me.monkey.service.impl;

import me.monkey.service.TestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {


//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Async
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------run方法-----休眠3秒----");
    }
/*

//    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransaction() {
        Device device = new Device();
        device.setName("device0004");
        mongoTemplate.save(device);
//        int i = 3/0;
        Item item = new Item();
        item.setName("item0004");
        mongoTemplate.save(item);
    }
*/

    @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行
    @Override
    public void runScheduled() {
        System.out.println("--------runScheduled方法---------");
    }


    @Scheduled(cron="${time.cron}")
    @Override
    public void testPlaceholder1() {
        System.out.println("Execute testPlaceholder1 at " + System.currentTimeMillis());
    }

    @Scheduled(cron="*/${time.interval} * * * * *")
    @Override
    public void testPlaceholder2() {
        System.out.println("Execute testPlaceholder2 at " + System.currentTimeMillis());
    }

    @Override
    public void testTransaction() {

    }
    public void init() {
        System.out.println("----init---------");
    }



}
