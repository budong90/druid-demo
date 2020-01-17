package cc.eslink.dao;

import cc.eslink.entity.BizAddress0;
import cc.eslink.entity.BizMeter0;
import cc.eslink.entity.BizUser0;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *@ClassName BizUserAddressDao
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/1/17 15:31
 *@Version 1.0
 **/
@Repository
public interface BizUserAddressDao {

    /********************保存操作***********************/
    int insertUser(@Param("pojo") BizUser0 pojo);

    int insertAddress(@Param("pojo") BizAddress0 pojo);

    int insertMeter(@Param("pojo") BizMeter0 pojo);

    /********************查询列表***********************/
    List<BizUser0> queryUserList(String tenantId);

    List<BizMeter0> queryMeterList(String tenantId);

    List<Map<String, Object>> queryUserAddressList(String tenantId);

    List<Map<String, Object>> queryUserDetailList(String tenantId);

    /*********************主键查询**********************/
    BizUser0 getUser(String userId);

    BizUser0 getUserByTenant(@Param("tenantId") String tenantId, @Param("userId") String userId);

    BizMeter0 getMeter(String meterId);

    BizMeter0 getMeterByTenant(@Param("tenantId") String tenantId, @Param("meterId") String meterId);

    /*******************************************/

}
