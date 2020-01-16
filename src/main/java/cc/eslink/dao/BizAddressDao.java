package cc.eslink.dao;


import cc.eslink.entity.BizAddress;
import cc.eslink.entity.BizAddress2;
import cc.eslink.entity.BizAddress3;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * BizAddressDao数据库操作接口类
 *
 * @author zyk
 */
@Repository
public interface BizAddressDao {

    int insert(@Param("pojo") BizAddress pojo);

    int insertList(@Param("list") List<BizAddress> list);

    int insertList2(@Param("list") List<BizAddress2> list);

    int insertList3(@Param("list") List<BizAddress3> list);

    int count(@Param("id") Integer id);

    int count3(@Param("id") Integer id);
}