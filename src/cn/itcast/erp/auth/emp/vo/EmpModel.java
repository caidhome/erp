package cn.itcast.erp.auth.emp.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.util.format.FormatUtil;

public class EmpModel {

	public static String EMP_LOGIN_USERNAME_OBJECT_NAME = "loginEm";
	// 数据结构思想应用
	public static final Integer EMP_GENDER_OF_MAN = 1;
	public static final Integer EMP_GENDER_OF_WOMAN = 0;

	public static final String EMP_GENDER_OF_MAN_VIEW = "男";
	public static final String EMP_GENDER_OF_WOMAN_VIEW = "女";

	public static final Map<Integer, String> genderMap = new HashMap<Integer, String>();
	static {
		genderMap.put(EMP_GENDER_OF_MAN, EMP_GENDER_OF_MAN_VIEW);
		genderMap.put(EMP_GENDER_OF_WOMAN, EMP_GENDER_OF_WOMAN_VIEW);
	}
	private Long uuid;
	private String username;
	private String name;
	private String pwd;
	private String email;
	private String tele;
	private String address;
	private Long lastLoginTime;
	private String lastLoginTimeView;

	private String lastLoginIp;
	private Integer loginTimes;

	private Integer gender;
	private String genderView;

	private Long birthday;
	private String birthdayView;

	// 该用户能访问的所有路径
	private String allRes;

	// 一对多
	private DepModel dm;
	// 多对多
	private Set<RoleModel> roles;

	public String getAllRes() {
		return allRes;
	}

	public void setAllRes(String allRes) {
		this.allRes = allRes;
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public String getGenderView() {
		return genderView;
	}

	public String getBirthdayView() {
		return birthdayView;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
		this.genderView = genderMap.get(gender);
	}

	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		this.lastLoginTimeView = FormatUtil.formatDate(lastLoginTime);
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
		this.birthdayView = FormatUtil.formatDate(birthday);
	}

	public DepModel getDm() {
		return dm;
	}

	public void setDm(DepModel dm) {
		this.dm = dm;
	}

}
