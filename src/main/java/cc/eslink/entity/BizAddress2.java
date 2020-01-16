package cc.eslink.entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 *
 * @author zyk 
 */
@Table(name ="biz_address2")
public class BizAddress2 {

	private static final long serialVersionUID = 1L;

	//=================================================

	/**  */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/**  */
	private String userId;

	/**  */
	private String userName;

	/**  */
	private String address;

	/**  */
	private String telephone;

	/**  */
	private Integer sex;

	/**  */
	private String createTime;

	//=================================================

	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return address;
	}
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public String getTelephone(){
		return telephone;
	}
	public void setSex(Integer sex){
		this.sex = sex;
	}
	public Integer getSex(){
		return sex;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
}
