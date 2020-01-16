package cc.eslink.dao;

import cc.eslink.entity.SafeTempPhoto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 临时安检图片处理DAO
 * @author 1919
 *
 */
public interface SafeTempPhotoDao {

    /**
     * 新增图片记录
     * @param safeTempPhoto
     */
    public void insertSafeTempPhoto(SafeTempPhoto safeTempPhoto);

    /**
     * 根据条件查询图片记录
     * @param photoKey
     * @return
     */
    public SafeTempPhoto querySafeTempPhoto(@Param("photoKey") String photoKey);

    public SafeTempPhoto querySafeTempPhoto2(@Param("photoKey") String photoKey);

    int insertList(@Param("pojos") List<SafeTempPhoto> list);

    int insertList2(@Param("pojos") List<SafeTempPhoto> list);

    List<String> queryKeys(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("offset") int offset, @Param("rows") int rows);

    List<String> queryKeys2(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("offset") int offset, @Param("rows") int rows);
}
