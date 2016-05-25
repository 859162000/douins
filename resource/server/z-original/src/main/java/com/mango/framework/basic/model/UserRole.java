package com.mango.framework.basic.model;

import com.mango.core.abstractclass.AbstractModel;

public class UserRole extends AbstractModel
{
  private static final long serialVersionUID = -6468180286349507878L;
  private Integer id;
  private String userId;
  private String roleId;

  public final Integer getId()
  {
    return this.id;
  }

  public final void setId(Integer id) {
    this.id = id;
  }

  public final String getUserId() {
    return this.userId;
  }

  public final void setUserId(String userId) {
    this.userId = userId;
  }

  public final String getRoleId() {
    return this.roleId;
  }

  public final void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public void initPrimaryKey()
  {
  }
}