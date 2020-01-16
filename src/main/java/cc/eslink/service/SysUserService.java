package cc.eslink.service;

import cc.eslink.dao.SysUserDAO;
import cc.eslink.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 *@ClassName SysUserService
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2019/11/22 16:57
 *@Version 1.0
 **/
@Component
public class SysUserService {

    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    @Resource
    private SysUserDAO sysUserDAO;

    @Transactional(rollbackFor = Exception.class)
    public int save(SysUser user) {
        THREAD_LOCAL.set(new Date());
        sysUserDAO.insert(user);
        return user.getId();
    }

    public String get() {
        return Objects.toString(THREAD_LOCAL.get());
    }
}
