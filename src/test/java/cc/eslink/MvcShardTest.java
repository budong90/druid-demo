package cc.eslink;

import cc.eslink.dao.BizUserAddressDao;
import cc.eslink.entity.BizAddress0;
import cc.eslink.entity.BizMeter0;
import cc.eslink.entity.BizUser;
import cc.eslink.entity.BizUser0;
import cc.eslink.util.IDUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Random;

/**
 *@ClassName MvcShardTest
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/1/17 16:08
 *@Version 1.0
 **/
public class MvcShardTest extends BaseTest {

    private static final String[] tenantIds = new String[]{"1P0101", "1P0107", "0185", "0302"};

    private static String rdmTenant() {
        return tenantIds[new Random().nextInt(tenantIds.length)];
    }

    @Resource
    private BizUserAddressDao dao;

    @Test
    public void test_insertUser() {
        BizUser0 bizUser = new BizUser0(IDUtil.getUniqueId(), rdmTenant());
        dao.insertUser(bizUser);
        System.out.println("userId=" + bizUser.getUserId());
    }

    @Test
    public void test_insertAddress() {
        BizAddress0 bizAddress = new BizAddress0(IDUtil.getUniqueId(), rdmTenant());
        dao.insertAddress(bizAddress);
        System.out.println("addressId=" + bizAddress.getAddressId());
    }

    @Test
    public void test_insertMeter() {
        BizMeter0 bizMeter = new BizMeter0(IDUtil.getUniqueId(), rdmTenant());
        dao.insertMeter(bizMeter);
        System.out.println("meterId=" + bizMeter.getMeterId());
    }

    @Test
    public void test_queryUserList() {

    }

    @Test
    public void test_queryMeterList() {

    }

    @Test
    public void test_queryUserAddressList() {

    }

    @Test
    public void test_queryUserDetailList() {

    }

    @Test
    public void test_getUser() {

    }

    @Test
    public void test_getUserByTenant() {

    }

    @Test
    public void test_getMeter() {

    }

    @Test
    public void test_getMeterByTenant() {

    }

}
