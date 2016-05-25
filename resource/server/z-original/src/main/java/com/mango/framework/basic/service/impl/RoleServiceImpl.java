package com.mango.framework.basic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mango.core.common.util.JsonUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.core.tree.DepthIterator;
import com.mango.core.tree.JcTreeNode;
import com.mango.core.tree.TreeFormat;
import com.mango.core.tree.TreeNode;
import com.mango.exception.DataBaseAccessException;
import com.mango.framework.basic.model.Role;
import com.mango.framework.basic.model.RoleMenu;
import com.mango.framework.basic.service.LoginUserService;
import com.mango.framework.basic.service.MenuService;
import com.mango.framework.basic.service.RoleMenuService;
import com.mango.framework.basic.service.RoleService;
import com.mango.framework.basic.service.UserRoleService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	private SimpleLogger logger = SimpleLogger.getLogger(getClass());

	@Autowired
	private MenuService menuService;

	@Autowired
	@Qualifier("loginUserService")
	private LoginUserService loginUserService;

	@Autowired
	private RoleMenuService roleMenuService;

	@Autowired
	UserRoleService userRoleService;
	final String Default_Role = "39d81abd4fe549569514bbaa70dfc9d1";

	@Autowired
	@Qualifier("checkedMenuTreeJsonFormat")
	private TreeFormat checkedTreeJsonFormat;

	@Autowired
	private BaseDao<Role> roleDao;
	private String mapper = Role.class.getName() + "Mapper.";

	public String getDefautRole() {
		return "39d81abd4fe549569514bbaa70dfc9d1";
	}

	public Role findByKey(String key) {
		return (Role) this.roleDao.get(this.mapper + "selectByPrimaryKey", key);
	}

	public Page<Role> getPage(String user, Role t, Page<Role> page) {
		if (page != null) {
			if (this.loginUserService.isAdmin(user)) {
				this.roleDao.getList(this.mapper + "getList", t, page);
			} else {
				Set roles = this.userRoleService.getRoles(user);
				if ((roles == null) || (roles.isEmpty())) {
					return page;
				}
				Map paraMap = new HashMap();
				paraMap.put("code", t.getCode());
				paraMap.put("name", t.getName());
				paraMap.put("roles", new ArrayList(roles));
				this.roleDao
						.getList(this.mapper + "getRoleList", paraMap, page);
			}
		}
		return page;
	}

	public Page<Role> getPage(Role role, Page<Role> page) {
		return page;
	}

	public List<Role> getRoleList(Role role) {
		return this.roleDao.getList(this.mapper + "getList", role);
	}

	public String getRoleMenuTree(String userId, String roleId) {
		StringBuffer treebf = new StringBuffer(1000);
		TreeNode root = this.menuService.getRootNode();
		JcTreeNode child = null;
		for (Iterator itsub = new DepthIterator(root.iterator()); itsub
				.hasNext();) {
			child = (JcTreeNode) itsub.next();
			if (child != null) {
				if (this.roleMenuService.isRolePermissions(roleId,
						child.getId()))
					child.setChecked(Boolean.TRUE.booleanValue());
			}
		}
		String tempchild = null;
		Map treeMp = this.menuService.getMenuMap();
		if (this.loginUserService.isAdmin(userId))
			treebf = this.checkedTreeJsonFormat.treeDataFormat(root, treeMp,
					roleId, 2);
		else
			treebf = this.checkedTreeJsonFormat.treeDataFormat(root, treeMp,
					roleId, 1);
		if ((treebf != null) && (treebf.length() > 0)) {
			String jsbegin = "\"children\":[";
			String jsend = "]";
			tempchild = treebf.substring(treebf.indexOf(jsbegin) + 12,
					treebf.lastIndexOf(jsend));
		} else {
			tempchild = "{\"id\":\"-1\",\"text\":\"很抱歉,没有您权限范围的菜单!\",\"leaf\":true}";
		}
		for (Iterator itsub = new DepthIterator(root.iterator()); itsub
				.hasNext();) {
			child = (JcTreeNode) itsub.next();
			if (child != null) {
				child.setChecked(Boolean.FALSE.booleanValue());
			}
		}
		return tempchild;
	}

	public String saveRoleMenuTree(String roleId, String menuStr) {
		if (StringUtils.isBlank(roleId))
			return JsonUtils.buildJsonRtnEro("");
		if (StringUtils.isBlank(menuStr))
			this.roleMenuService.deleteByRole(roleId);
		String[] menuIds = menuStr.split(",");
		Set newList = new HashSet();
		int ef = 0;
		for (String menuId : menuIds) {
			newList.add(menuId);
		}
		List newList2 = new ArrayList();
		Iterator iterator = newList.iterator();
		String tmpid = null;
		RoleMenu roleMenu = null;
		while (iterator.hasNext()) {
			tmpid = (String) iterator.next();
			newList2.add(tmpid);
			if ((!StringUtils.isBlank(tmpid))
					&& (!this.roleMenuService.isRolePermissions(roleId, tmpid))) {
				roleMenu = new RoleMenu();
				roleMenu.setMenuId(tmpid);
				roleMenu.setRoleId(roleId);
				roleMenu.setPurview(Long.valueOf(7L));
				if (this.roleMenuService.save(roleMenu))
					ef++;
			}
		}
		List<String> hadList = this.roleMenuService.getMenusList(roleId);
		hadList.removeAll(newList2);
		if (!hadList.isEmpty()) {
			RoleMenu delroleMenu = null;
			for (String delmenuId : hadList) {
				delroleMenu = new RoleMenu();
				delroleMenu.setMenuId(delmenuId);
				delroleMenu.setRoleId(roleId);
				if (this.roleMenuService.delete(delroleMenu))
					ef++;
			}
		}
		if (ef > 0) {
			this.roleMenuService.flush();
		}
		return JsonUtils.buildJsonRtnSuc();
	}

	public List<Role> getListByIds(List<String> ids) {
		return this.roleDao.getList(this.mapper + "selectByIds", ids);
	}

	public boolean save(String user, Role t) {
		try {
			return this.roleDao.update(this.mapper + "insertSelective", t) > 0;
		} catch (DataBaseAccessException e) {
			this.logger.error("create role DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean update(String user, Role t) {
		try {
			return this.roleDao.update(this.mapper + "update", t) > 0;
		} catch (DataBaseAccessException e) {
			this.logger.error("update role DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean delete(String user, Role t) {
		try {
			if (this.roleDao.delete(this.mapper + "deleteByPrimaryKey",
					t.getRoleId()) > 0) {
				this.roleMenuService.deleteByRole(t.getRoleId());
				this.roleMenuService.flush();
				return Boolean.TRUE.booleanValue();
			}
			return Boolean.FALSE.booleanValue();
		} catch (DataBaseAccessException e) {
			this.logger.error("delete role DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public Role getRoleByCode(String code) {
		return (Role) this.roleDao.get(this.mapper + "selectByCode", code);
	}
}