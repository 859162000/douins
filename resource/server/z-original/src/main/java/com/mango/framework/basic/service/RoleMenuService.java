package com.mango.framework.basic.service;

import com.mango.framework.basic.model.RoleMenu;
import com.mango.framework.basic.model.UserMenu;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract interface RoleMenuService
{
  public abstract List<RoleMenu> getList();

  public abstract List<UserMenu> getUserMenuList();

  public abstract boolean deleteByRole(String paramString);

  public abstract boolean deleteByMenu(String paramString);

  public abstract boolean delete(RoleMenu paramRoleMenu);

  public abstract boolean save(RoleMenu paramRoleMenu);

  public abstract List<String> getMenusList(String paramString);

  public abstract Map<String, Long> getRoleMenu(String paramString);

  public abstract long getMenuRoleAuth(String paramString1, String paramString2);

  public abstract Map<String, Long> getUserMenu(String paramString);

  public abstract Set<String> getMenuUser(String paramString);

  public abstract boolean isPermissions(String paramString1, String paramString2);

  public abstract boolean isRolePermissions(String paramString1, String paramString2);

  public abstract void flush();
}