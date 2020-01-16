package cc.eslink.dao;

import cc.eslink.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *@ClassName SysUserDAO
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/22 16:38
 *@Version 1.0
 **/
@Repository
public interface SysUserDAO {

    int insert(@Param("pojo") SysUser pojo);
}
