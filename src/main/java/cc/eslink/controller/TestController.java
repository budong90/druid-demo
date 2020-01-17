package cc.eslink.controller;

import cc.eslink.base.spring.SpringContextHolder;
import cc.eslink.dao.SafeTempPhotoDao;
import cc.eslink.entity.SafeTempPhoto;
import cc.eslink.entity.SysUser;
import cc.eslink.service.SysUserService;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.Utils;
import com.alibaba.fastjson.JSON;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *@ClassName TestController
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/22 16:56
 *@Version 1.0
 **/
@RestController
public class TestController {

    private static boolean loop = false;

    @Resource
    private SysUserService sysUserService;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @RequestMapping("/user/save")
    public String saveUser(SysUser sysUser) {
        if (null == sysUser) {
            sysUser = new SysUser();
        }
        int rdm = RANDOM.nextInt(1000);
        sysUser.setUserName("hello-" + System.currentTimeMillis() + "-" + rdm);
        sysUser.setAddress("杭州市文一西路1288号-" + rdm);
        sysUser.setSex(rdm % 2);
        sysUser.setTelephone(rdm + "");
        return sysUserService.save(sysUser) + "";
    }

    @RequestMapping("/tl/get")
    public String get() {
        return sysUserService.get();
    }

    @RequestMapping("/druid/count")
    public void count() throws InterruptedException {
        DruidDataSource drs = (DruidDataSource) SpringContextHolder.getBean("dataSource");
        loop = true;
        String format = "[%s]ActiveCount:%d,PoolingCount:%d,CreateCount:%d,DestroyCount:%d,CloseCount:%d,ConnectCount:%d";
        while (loop) {
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println(String.format(format, LocalDateTime.now().toString(),
                    drs.getActiveCount(), drs.getPoolingCount(), drs.getCreateCount(),
                    drs.getDestroyCount(), drs.getCloseCount(), drs.getConnectCount()));
        }
    }

    @RequestMapping("/druid/stop")
    public void stopCount() {
        loop = false;
    }

    @Resource
    private SafeTempPhotoDao safeTempPhotoDao;

    @RequestMapping("/query")
    public void queryPhoto() {
        String photoKey = "000003b0-81ed-405e-895e-afce5f069f6b.jpg";
        SafeTempPhoto safeTempPhoto = safeTempPhotoDao.querySafeTempPhoto(photoKey);
        System.out.println(safeTempPhoto);
    }
}
