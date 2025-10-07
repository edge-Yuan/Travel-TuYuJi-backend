package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "系统用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户唯一标识")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty("登录账号（唯一）")
    private String username;

    @ApiModelProperty("加密存储的密码")
    private String password;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("手机号（唯一）")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

  @ApiModelProperty("性别：male/female/other")
  private String gender;

  @ApiModelProperty("出生日期")
  private java.time.LocalDate birthday;

  @ApiModelProperty("个人简介")
  private String bio;

  @ApiModelProperty("所在地(逗号分隔)")
  private String location;

    @ApiModelProperty("角色类型：1-游客，2-系统管理员，3-财务管理员，4-旅行商，5-导游")
    private int userRole;

    @ApiModelProperty("账号状态：0-禁用，1-正常")
    private Byte status;

    @ApiModelProperty("注册/创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("信息更新时间")
    private LocalDateTime updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  public String getGender() {
      return gender;
  }

  public void setGender(String gender) {
      this.gender = gender;
  }

  public java.time.LocalDate getBirthday() {
      return birthday;
  }

  public void setBirthday(java.time.LocalDate birthday) {
      this.birthday = birthday;
  }

  public String getBio() {
      return bio;
  }

  public void setBio(String bio) {
      this.bio = bio;
  }

  public String getLocation() {
      return location;
  }

  public void setLocation(String location) {
      this.location = location;
  }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
            "userId = " + userId +
            ", username = " + username +
            ", password = " + password +
            ", realName = " + realName +
            ", phone = " + phone +
            ", email = " + email +
            ", gender = " + gender +
            ", birthday = " + birthday +
            ", bio = " + bio +
            ", location = " + location +
            ", userRole = " + userRole +
            ", status = " + status +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
        "}";
    }
}
