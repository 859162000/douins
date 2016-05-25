package com.mango.framework.basic.model;

import com.mango.core.abstractclass.AbstractModel;

public class UserMenu extends AbstractModel
{
  private static final long serialVersionUID = 8473914167406332193L;
  private Integer id;
  private String userId;
  private String menuId;
  private Long purview;

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