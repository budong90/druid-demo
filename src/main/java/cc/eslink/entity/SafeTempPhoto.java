package cc.eslink.entity;

import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 安检图片临时信息
 * @author 1919
 *
 */
@Table(name = "safe_temp_photo")
public class SafeTempPhoto implements java.io.Serializable {

    /**serialVersionUID */
    private static final long serialVersionUID = 667663648094962819L;
    /** 图片key */
    private String photoKey;
    /** 图片url */
    private String photoUrl;
    /** 租户ID */
    private String tenantId;
    /** 创建时间 */
    private Date createTime;

    public SafeTempPhoto() {
    }

    public SafeTempPhoto(String photoKey, String photoUrl, String tenantId, Date createTime) {
        this.photoKey = photoKey;
        this.photoUrl = photoUrl;
        this.tenantId = tenantId;
        this.createTime = createTime;
    }

    public String getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static SafeTempPhoto newInstance(int number) {
        String key = UUID.randomUUID() + "-" + number;
        return new SafeTempPhoto(key, "http://eslink-safetycheck.oss-cn-beijing.aliyuncs.com/" + key + ".jpg", null, randomDate("2018-01-01", "2020-01-16"));
    }

    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}
