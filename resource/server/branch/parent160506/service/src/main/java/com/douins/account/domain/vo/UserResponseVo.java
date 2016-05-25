package com.douins.account.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class UserResponseVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;// USER_ID
	private String userAccount;// 账户
	private String accountType;// 账户类型
	private String userMobile;// 手机
	private String userEmail;// 邮箱
	private String nickName;// 昵称
	private String novice;// 是否新用户
	private String grade;// 用户等级
	private String userName;// 姓名
	private String userSex;// 性别
	private String userBirthDay;// 出生日期
	private String certiType;// 证件类型
	private String certiCode;// 证件号码
	private String gesturePassword;// 手势密码
	private String switchGesturePassword;// 手势密码开关 1 已初始化，0未初始化
	private String openId;
    private Date certiValidDate;//证件有效期
    private String certiIsValid;
   
    private String province;//省
    private String city;//市
    private String district;//区
    private String detailedAdress;//详细地址

	// private List<UserPayAccountVo> accountlist;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNovice() {
		return novice;
	}

	public void setNovice(String novice) {
		this.novice = novice;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserBirthDay() {
		return userBirthDay;
	}

	public void setUserBirthDay(String userBirthDay) {
		this.userBirthDay = userBirthDay;
	}

	public String getCertiType() {
		return certiType;
	}

	public void setCertiType(String certiType) {
		this.certiType = certiType;
	}

	public String getCertiCode() {
		return certiCode;
	}

	public void setCertiCode(String certiCode) {
		this.certiCode = certiCode;
	}

	public String getGesturePassword() {
		return gesturePassword;
	}

	public void setGesturePassword(String gesturePassword) {
		this.gesturePassword = gesturePassword;
	}

	public String getSwitchGesturePassword() {
		return switchGesturePassword;
	}

	public void setSwitchGesturePassword(String switchGesturePassword) {
		this.switchGesturePassword = switchGesturePassword;
	}

    public Date getCertiValidDate() {
        return certiValidDate;
    }

    public void setCertiValidDate(Date certiValidDate) {
        this.certiValidDate = certiValidDate;
    }

    public String getCertiIsValid() {
        return certiIsValid;
    }

    public void setCertiIsValid(String certiIsValid) {
        this.certiIsValid = certiIsValid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetailedAdress() {
        return detailedAdress;
    }

    public void setDetailedAdress(String detailedAdress) {
        this.detailedAdress = detailedAdress;
    }
}
