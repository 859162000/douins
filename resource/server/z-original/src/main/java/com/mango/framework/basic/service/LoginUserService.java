package com.mango.framework.basic.service;

import com.mango.fortune.user.model.User;
import com.mango.orm.DbOperateService;

public abstract interface LoginUserService extends DbOperateService<User>
{
  public abstract boolean produceDefautUser(String paramString1, String paramString2, String paramString3);

  public abstract boolean isAdmin(String paramString);

  public abstract User getUserByAccount(String paramString);

  public abstract User getRegisterUserByAccount(String paramString);

  public abstract User getUserLocal(String paramString);

  public abstract void removeUserLocal(String paramString);

  public abstract boolean updateLoginInfo(User paramAdminUser);

  public abstract boolean updatePassword(User paramAdminUser);

  public abstract String getUserRoleTree(String paramString1, String paramString2);

  public abstract String saveUserRoleTree(String paramString1, String paramString2);
}