package cc.eslink.dao;


import cc.eslink.entity.BizUser;
import cc.eslink.entity.BizUser2;
import cc.eslink.entity.BizUser3;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * BizUserDao数据库操作接口类
 *
 * @author zyk
 */
@Repository
public interface BizUserDao {

    int insert(@Param("pojo") BizUser pojo);

    int insertList(@Param("list") List<BizUser> list);

    int insertList2(@Param("list") List<BizUser2> list);

    int insertList3(@Param("list") List<BizUser3> list);
}