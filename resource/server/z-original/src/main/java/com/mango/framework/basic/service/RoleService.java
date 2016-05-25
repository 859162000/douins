package com.mango.framework.basic.service;

import com.mango.framework.basic.model.Role;
import com.mango.orm.DbOperateService;
import com.mango.orm.page.Page;
import java.util.List;

public abstract interface RoleService extends DbOperateService<Role>
{
  public abstract String getDefautRole();

  public abstract Page<Role> getPage(String paramString, Role paramRole, Page<Role> paramPage);

  public abstract List<Role> getRoleList(Role paramRole);

  public abstract List<Role> getListByIds(List<String> paramList);

  public abstract String getRoleMenuTree(String paramString1, String paramString2);

  public abstract String saveRoleMenuTree(String paramString1, String paramString2);

  public abstract Role getRoleByCode(String paramString);
}