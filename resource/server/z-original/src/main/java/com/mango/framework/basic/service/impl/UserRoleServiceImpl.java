package com.mango.framework.basic.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.framework.basic.model.UserRole;
import com.mango.framework.basic.service.RoleMenuService;
import com.mango.framework.basic.service.UserRoleService;
import com.mango.orm.BaseDao;

@Service("userRoleService")
public class UserRoleServiceImpl
  implements UserRoleService
{
  private SimpleLogger logger = SimpleLogger.getLogger(getClass());

  @Autowired
  private BaseDao<UserRole> userRoleDao;

  @Autowired
  private RoleMenuService roleMenuService;
  private String mapper = UserRole.class.getName() + "Mapper.";

  private static Map<String, Set<String>> userRoleMap = null;

  private static Map<String, Set<String>> roleUserMap = null;

  public List<UserRole> getUserRoles(UserRole ur)
  {
    List list = null;
    if (ur != null) {
      list = this.userRoleDao.getList(this.mapper + "selectByCondition", ur);
    }
    return list;
  }

  public boolean save(UserRole t)
  {
    try {
      if (this.userRoleDao.save(this.mapper + "insertSelective", t) > 0) {
        clear();
        return Boolean.TRUE.booleanValue();
      }
    } catch (DataBaseAccessException e) {
      this.logger.error("create UserRole DataBaseAccessException", e);
    }
    return Boolean.FALSE.booleanValue();
  }

  public boolean delete(UserRole t)
  {
    try {
      if (this.userRoleDao.delete(this.mapper + "delete", t) > 0) {
        clear();
        return Boolean.TRUE.booleanValue();
      }
    } catch (DataBaseAccessException e) {
      this.logger.error("delete UserRole DataBaseAccessException", e);
    }
    return Boolean.FALSE.booleanValue();
  }

  public boolean deleteByUser(String userId) {
    try {
      if (this.userRoleDao.delete(this.mapper + "deleteByUser", userId) > 0) {
        clear();
        return Boolean.TRUE.booleanValue();
      }
    } catch (DataBaseAccessException e) {
      this.logger.error("deleteByUser DataBaseAccessException", e);
    }
    return Boolean.FALSE.booleanValue();
  }

  public boolean deleteByRole(String roleId) {
    try {
      if (this.userRoleDao.delete(this.mapper + "deleteByRole", roleId) > 0) {
        clear();
        return Boolean.TRUE.booleanValue();
      }
    } catch (DataBaseAccessException e) {
      this.logger.error("deleteByRole DataBaseAccessException", e);
    }
    return Boolean.FALSE.booleanValue();
  }

  public Set<String> getRoles(String userId)
  {
    if (userId == null)
      return null;
    if (userRoleMap == null)
      initMap();
    return (Set)userRoleMap.get(userId);
  }

  public List<String> getRolesList(String userId) {
    List rolelist = new ArrayList();
    if (userId == null)
      return rolelist;
    if (userRoleMap == null)
      initMap();
    Set map = (Set)userRoleMap.get(userId);
    if (map == null)
      return rolelist;
    Iterator it = map.iterator();
    while (it.hasNext()) {
      rolelist.add((String)it.next());
    }
    return rolelist;
  }

  public Set<String> getUsers(String roleId) {
    if (roleId == null)
      return null;
    if (roleUserMap == null)
      initMap();
    return (Set)roleUserMap.get(roleId);
  }

  public void clear() {
    if (userRoleMap != null)
      userRoleMap = null;
    if (roleUserMap != null) {
      roleUserMap = null;
    }
    this.roleMenuService.flush();
  }

  private void initMap() {
    userRoleMap = new ConcurrentHashMap();
    roleUserMap = new ConcurrentHashMap();
    List<UserRole> list = getAll();
    if (list == null)
      return;
    Set users = null;
    Set roles = null;
    for (UserRole userRole : list) {
      if (userRoleMap.containsKey(userRole.getUserId()))
        roles = (Set)userRoleMap.get(userRole.getUserId());
      else {
        roles = new HashSet();
      }
      roles.add(userRole.getRoleId());
      if (roleUserMap.containsKey(userRole.getRoleId()))
        users = (Set)roleUserMap.get(userRole.getRoleId());
      else {
        users = new HashSet();
      }
      users.add(userRole.getUserId());
      userRoleMap.put(userRole.getUserId(), roles);
      roleUserMap.put(userRole.getRoleId(), users);
    }
  }

  private List<UserRole> getAll() {
    return this.userRoleDao.getAll(this.mapper + "findUserRoles");
  }
}