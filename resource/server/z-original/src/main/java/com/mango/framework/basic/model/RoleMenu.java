package com.mango.framework.basic.model;

import com.mango.core.abstractclass.AbstractModel;

public class RoleMenu extends AbstractModel
{
  private static final long serialVersionUID = -3318711003998083756L;
  private Integer id;
  private String roleId;
  private String menuId;
  private Long purview;

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

  public final String getMenuId() {
    return this.menuId;
  }

  public final void setMenuId(String menuId) {
    this.menuId = menuId;
  }

  public final Long getPurview() {
    return this.purview;
  }

  public final void setPurview(Long purview) {
    this.purview = purview;
  }

  public void initPrimaryKey()
  {
  }
}