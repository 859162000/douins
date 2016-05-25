package com.mango.framework.basic.service;

import com.mango.core.tree.TreeNode;
import com.mango.framework.basic.model.Menu;
import com.mango.orm.DbOperateService;
import java.util.List;
import java.util.Map;

public abstract interface MenuService extends DbOperateService<Menu>
{
  public abstract String getMenusByParentId(String paramString1, String paramString2);

  public abstract String getMenuTree(String paramString);

  public abstract String getRoleMenuTree(String paramString1, String paramString2);

  public abstract String getTcode(Integer paramInteger);

  public abstract String getMenuId(String paramString);

  public abstract TreeNode getMenuNode(String paramString);

  public abstract Map<String, TreeNode> getMenuMap();

  public abstract TreeNode getRootNode();

  public abstract List<Menu> getAllMenus();

  public abstract boolean isRoot(String paramString);
}