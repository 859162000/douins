package com.mango.framework.basic.model;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;
import java.util.Date;

public class Role extends AbstractModel
{
  private static final long serialVersionUID = 4968674915854493101L;
  private Integer id;
  private String roleId;
  private String code;
  private String name;
  private String descn;
  private String status;
  private Date updateTime;

  public final Integer getId()
  {
    return this.id;
  }

  public final void setId(Integer id) {
    this.id = id;
  }

  public final String getRoleId() {
    return this.roleId;
  }

  public final void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public final String getCode() {
    return this.code;
  }

  public final void setCode(String code) {
    this.code = code;
  }

  public final String getName() {
    return this.name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public final String getDescn() {
    return this.descn;
  }

  public final void setDescn(String descn) {
    this.descn = descn;
  }

  public final String getStatus() {
    return this.status;
  }

  public final void setStatus(String status) {
    this.status = status;
  }

  public final Date getUpdateTime() {
    return this.updateTime;
  }

  public final void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public void initPrimaryKey() {
    setRoleId(KeyGenerator.randomSeqNum());
  }
}