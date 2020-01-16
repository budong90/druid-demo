package cc.eslink;

import cc.eslink.dao.BizAddressDao;
import cc.eslink.dao.BizUserDao;
import cc.eslink.dao.SafeTempPhotoDao;
import cc.eslink.entity.*;
import cc.eslink.util.IDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *@ClassName MvcTest
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2019/12/24 16:17
 *@Version 1.0
 **/
public class MvcTest extends BaseTest {

    @Resource
    private BizUserDao bizUserDao;
    @Resource
    private BizAddressDao bizAddressDao;
    @Autowired
    private SafeTempPhotoDao safeTempPhotoDao;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @Test
    public void test() {
        System.out.println(LocalTime.now().toString());
    }

    private static final int total = 7000001;

    @Test
    public void saveList() throws Exception {
        int per = 1000;
        List<BizUser> userList = new ArrayList<>(per);
        List<BizAddress> addrList = new ArrayList<>(per);
        int i = 1, count = 1;
        for (; i < total; i++) {
            int rdm = RANDOM.nextInt(per);
            BizUser bizUser = new BizUser();
            bizUser.setUserName("hello-" + System.currentTimeMillis() + "-" + rdm);
            bizUser.setAddress("杭州市文一西路1288号-" + rdm);
            bizUser.setSex(rdm % 2);
            bizUser.setTelephone(rdm + "");
            userList.add(bizUser);

            BizAddress bizAddress = new BizAddress();
            BeanUtils.copyProperties(bizAddress, bizUser);
            bizAddress.setUserId(i);
            addrList.add(bizAddress);
            if (addrList.size() >= per) {
                bizUserDao.insertList(userList);
                bizAddressDao.insertList(addrList);
                System.out.println(String.format("第%d次，插入成功%d条", count++, addrList.size()));
                userList.clear();
                addrList.clear();
            }
        }
        if (!addrList.isEmpty()) {
            bizUserDao.insertList(userList);
            bizAddressDao.insertList(addrList);
            System.out.println(String.format("第%d次，插入成功%d条", count++, addrList.size()));
            userList.clear();
            addrList.clear();
        }
    }

    @Test
    public void saveList2() throws Exception {
        int per = 1000;
        List<BizUser2> userList = new ArrayList<>(per);
        List<BizAddress2> addrList = new ArrayList<>(per);
        int i = 1, count = 1;
        for (; i < total; i++) {
            int rdm = RANDOM.nextInt(per);
            BizUser2 bizUser = new BizUser2();
            bizUser.setUserName("hello-" + System.currentTimeMillis() + "-" + rdm);
            bizUser.setAddress("杭州市文一西路1288号-" + rdm);
            bizUser.setSex(rdm % 2);
            bizUser.setTelephone(rdm + "");
            bizUser.setId(IDUtil.getUniqueId());
            userList.add(bizUser);

            BizAddress2 bizAddress = new BizAddress2();
            BeanUtils.copyProperties(bizAddress, bizUser);
            bizAddress.setId(IDUtil.getUniqueId());
            bizAddress.setUserId(bizUser.getId());
            addrList.add(bizAddress);
            if (addrList.size() >= per) {
                bizUserDao.insertList2(userList);
                bizAddressDao.insertList2(addrList);
                System.out.println(String.format("第%d次，插入成功%d条", count++, addrList.size()));
                userList.clear();
                addrList.clear();
            }
        }
        if (!addrList.isEmpty()) {
            bizUserDao.insertList2(userList);
            bizAddressDao.insertList2(addrList);
            System.out.println(String.format("第%d次，插入成功%d条", count++, addrList.size()));
            userList.clear();
            addrList.clear();
        }
    }

    @Test
    public void saveList3() throws Exception {
        int per = 1000;
        List<BizUser3> userList = new ArrayList<>(per);
        List<BizAddress3> addrList = new ArrayList<>(per);
        int i = 2000001, count = 1;
        for (; i < total + 500 * 10000; i++) {
            int rdm = RANDOM.nextInt(per);
            BizUser3 bizUser = new BizUser3();
            bizUser.setUserName("hello-" + System.currentTimeMillis() + "-" + rdm);
            bizUser.setAddress("杭州市文一西路1288号-" + rdm);
            bizUser.setSex(rdm % 2);
            bizUser.setTelephone(rdm + "");
            bizUser.setSid(IDUtil.getUniqueId());
            userList.add(bizUser);

            BizAddress3 bizAddress = new BizAddress3();
            BeanUtils.copyProperties(bizAddress, bizUser);
            bizAddress.setUserId(i);
            bizAddress.setUserSid(bizUser.getSid());
            addrList.add(bizAddress);
            if (addrList.size() >= per) {
                bizUserDao.insertList3(userList);
                bizAddressDao.insertList3(addrList);
                System.out.println(String.format("第%d次，插入成功%d条", count++, addrList.size()));
                userList.clear();
                addrList.clear();
            }
        }
        if (!addrList.isEmpty()) {
            bizUserDao.insertList3(userList);
            bizAddressDao.insertList3(addrList);
            System.out.println(String.format("第%d次，插入成功%d条", count++, addrList.size()));
            userList.clear();
            addrList.clear();
        }
    }

    @Test
    public void test4() {
        int start = (int) (Math.random() * total);
        List<Long> times = new ArrayList<>();
        List<Long> times3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long begin = System.currentTimeMillis();
            bizAddressDao.count(start);
            times.add(System.currentTimeMillis() - begin);
            begin = System.currentTimeMillis();
            bizAddressDao.count3(start);
            times3.add(System.currentTimeMillis() - begin);
//            System.out.println("第" + (i + 1) + "次[" + times.get(i) + "," + times3.get(i) + "]");
        }
        Double avg = times.stream().collect(Collectors.averagingLong(t -> t));
        Double avg3 = times3.stream().collect(Collectors.averagingLong(t -> t));
        System.out.println("times=" + times);
        System.out.println("times3=" + times3);
        System.out.println(String.format("start=%d,avg=%d,avg3=%d", start, avg.intValue(), avg3.intValue()));
    }

    @Test
    public void test5() {
        for (int i = 0; i < 10; i++) {
            test4();
            System.out.println("===================" + (i + 1) + "==================");
        }
    }

    @Test
    public void saveTempPhoto() throws InterruptedException {
        int count = 100, perSize = 10 * 10000;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + finalI + "开始执行");
                List<SafeTempPhoto> list = new ArrayList<>(1000);
                for (int i1 = 0; i1 < perSize; i1++) {
                    list.add(SafeTempPhoto.newInstance(i1));
                    if (list.size() > 1000) {
                        safeTempPhotoDao.insertList(list);
                        list.clear();
                    }
                }
                if (!list.isEmpty()) {
                    safeTempPhotoDao.insertList(list);
                }
                latch.countDown();
                System.out.println("线程" + finalI + "执行完成");
            }).start();
        }
        latch.await();
        System.out.println("执行结束");
    }

    private static final int poolSize = Runtime.getRuntime().availableProcessors() / 2;

    // 任务超过队列，抛出RejectedExecutionException
    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(poolSize, poolSize,
            0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024),
            new ThreadPoolExecutor.DiscardPolicy());

    @Test
    public void saveTempPhoto2() throws InterruptedException {
        int count = 500, perSize = (int) (1 * 1000), perSSize = 100;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            pool.submit(() -> {
                System.out.println("线程" + finalI + "开始执行");
                List<SafeTempPhoto> list = new ArrayList<>(perSSize);
                for (int i1 = 0; i1 < perSize; i1++) {
                    if (list.size() >= perSSize) {
                        safeTempPhotoDao.insertList(list);
                        System.out.println("线程" + finalI + "保存成功" + list.size() + "条");
                        list.clear();
                    }
                    list.add(SafeTempPhoto.newInstance(i1));
                }
                if (!list.isEmpty()) {
                    safeTempPhotoDao.insertList(list);
                }
                latch.countDown();
                System.out.println("线程" + finalI + "执行完成");
            });
        }
        latch.await();
        System.out.println("执行结束");
    }

    @Test
    public void saveTempPhoto3() {
        int count = 1000;
        List<SafeTempPhoto> list = new ArrayList<>(count);
        for (int i1 = 0; i1 < count; i1++) {
            list.add(SafeTempPhoto.newInstance(i1));
        }
        safeTempPhotoDao.insertList(list);
    }

    @Test
    public void testQuery() throws ParseException {
        int perSize = 1000;
        List<String> keys = new ArrayList<>(3 * perSize);
        keys.addAll(safeTempPhotoDao.queryKeys("2018-01-01", "2019-01-01", getRdm(1000, 10000), perSize));
        keys.addAll(safeTempPhotoDao.queryKeys("2019-01-01", "2020-01-01", getRdm(1000, 10000), perSize));
        keys.addAll(safeTempPhotoDao.queryKeys("2020-01-01", "2021-01-01", getRdm(1000, 10000), perSize));
        // 开始执行查询
        System.out.println("开始执行查询操作");
        long start = System.currentTimeMillis();
        List<Long> times = new ArrayList<>(keys.size());
        for (String photoKey : keys) {
            long begin = System.currentTimeMillis();
            safeTempPhotoDao.querySafeTempPhoto(photoKey);
            times.add(System.currentTimeMillis() - begin);
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println(String.format("查询%d耗时%d", times.size(), cost));
        System.out.println(times);
    }

    @Test
    public void testQuery2() throws ParseException {
        int perSize = 1000;
        List<String> keys = new ArrayList<>(3 * perSize);
        keys.addAll(safeTempPhotoDao.queryKeys2("2018-01-01", "2019-01-01", getRdm(1000, 10000), perSize));
        keys.addAll(safeTempPhotoDao.queryKeys2("2019-01-01", "2020-01-01", getRdm(1000, 10000), perSize));
        keys.addAll(safeTempPhotoDao.queryKeys2("2020-01-01", "2021-01-01", getRdm(1000, 10000), perSize));
        // 开始执行查询
        System.out.println("开始执行查询操作");
        long start = System.currentTimeMillis();
        List<Long> times = new ArrayList<>(keys.size());
        for (String photoKey : keys) {
            long begin = System.currentTimeMillis();
            safeTempPhotoDao.querySafeTempPhoto2(photoKey);
            times.add(System.currentTimeMillis() - begin);
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println(String.format("查询%d耗时%d", times.size(), cost));
        System.out.println(times);
    }

    public static int getRdm(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }
}
