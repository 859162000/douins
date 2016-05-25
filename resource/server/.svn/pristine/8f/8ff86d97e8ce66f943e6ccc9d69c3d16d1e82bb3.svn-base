package com.mango.framework.basic.service.impl;

import com.mango.core.logger.SimpleLogger;
import com.mango.core.tree.TreeNode;
import com.mango.exception.DataBaseAccessException;
import com.mango.framework.basic.model.RoleMenu;
import com.mango.framework.basic.model.UserMenu;
import com.mango.framework.basic.service.MenuService;
import com.mango.framework.basic.service.RoleMenuService;
import com.mango.orm.BaseDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
	private SimpleLogger logger = SimpleLogger.getLogger(getClass());

	@Autowired
	private BaseDao<RoleMenu> baseDao;

	@Autowired
	private BaseDao<UserMenu> userMenuDao;

	@Autowired
	MenuService menuService;
	private String mapperStr = RoleMenu.class.getName() + "Mapper.";

	private static Map<String, Map<String, Long>> userMenuMap = null;

	private static Map<String, Set<String>> menuUserMap = null;

	private static Map<String, Set<String>> roleMenuMap = null;

	private static Map<String, Map<String, Long>> menuRoleMap = null;

	public List<RoleMenu> getList() {
		return this.baseDao.getAll(this.mapperStr + "findRoleMenus");
	}

	public List<UserMenu> getUserMenuList() {
		return this.userMenuDao.getAll(this.mapperStr + "findUserMenus");
	}

	public boolean deleteByRole(String userId) {
		try {
			if (this.baseDao.delete(this.mapperStr + "deleteByRoleId", userId) > 0) {
				flush();
				return Boolean.TRUE.booleanValue();
			}
		} catch (DataBaseAccessException e) {
			this.logger.error(" deleteByRole DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean delete(RoleMenu roleMenu) {
		try {
			return this.baseDao.delete(this.mapperStr + "delete", roleMenu) > 0;
		} catch (DataBaseAccessException e) {
			this.logger.error(" delete DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean deleteByMenu(String roleId) {
		try {
			if (this.baseDao.delete(this.mapperStr + "deleteByMenuId", roleId) > 0) {
				flush();
				return Boolean.TRUE.booleanValue();
			}
		} catch (DataBaseAccessException e) {
			this.logger.error(" deleteByMenu DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean save(RoleMenu roleMenu) {
		try {
			return this.baseDao.save(this.mapperStr + "createRoleMenu",
					roleMenu) > 0;
		} catch (DataBaseAccessException e) {
			this.logger.error("save DataBaseAccessException", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public List<String> getMenusList(String roleId) {
		List menulist = new ArrayList();
		if (roleId == null)
			return menulist;
		if (roleMenuMap == null)
			initMenus();
		Set map = (Set) roleMenuMap.get(roleId);
		if (map == null)
			return menulist;
		Iterator it = map.iterator();
		while (it.hasNext()) {
			menulist.add((String) it.next());
		}
		return menulist;
	}

	public Map<String, Long> getRoleMenu(String menuId) {
		if (menuId == null)
			return new HashMap();
		if (menuRoleMap == null)
			initMenus();
		if (menuRoleMap.containsKey(menuId)) {
			return (Map) menuRoleMap.get(menuId);
		}
		return new HashMap();
	}

	public long getMenuRoleAuth(String roleId, String menuId) {
		if ((StringUtils.isBlank(roleId)) || (StringUtils.isBlank(menuId)))
			return 0L;
		if (menuRoleMap == null)
			initMenus();
		if ((menuRoleMap.containsKey(Integer.valueOf(menuId)))
				&& (((Map) menuRoleMap.get(Integer.valueOf(menuId)))
						.containsKey(Integer.valueOf(roleId))))
			return ((Long) ((Map) menuRoleMap.get(Integer.valueOf(menuId)))
					.get(Integer.valueOf(roleId))).longValue();
		return 0L;
	}

	public Map<String, Long> getUserMenu(String userId) {
		if (userId == null)
			return new HashMap();
		if (userMenuMap == null)
			initUserAuthority();
		if (userMenuMap.containsKey(userId)) {
			return (Map) userMenuMap.get(userId);
		}
		return new HashMap();
	}

	public Set<String> getMenuUser(String menuId) {
		if (menuId == null)
			return new HashSet();
		if (menuUserMap == null)
			initUserAuthority();
		return (Set) menuUserMap.get(menuId);
	}

	public void flush() {
		if (userMenuMap != null)
			userMenuMap = null;
		if (roleMenuMap != null)
			roleMenuMap = null;
		if (menuRoleMap != null)
			menuRoleMap = null;
	}

	private void initMenus() {
		if (roleMenuMap == null) {
			roleMenuMap = new ConcurrentHashMap();
		}
		if (menuRoleMap == null)
			menuRoleMap = new ConcurrentHashMap();
		List<RoleMenu> roleMenus = getList();
		Set rmenus = null;
		Map menuMap = null;

		for (RoleMenu roleMenu : roleMenus) {
			if (roleMenuMap.containsKey(roleMenu.getRoleId()))
				rmenus = (Set) roleMenuMap.get(roleMenu.getRoleId());
			else {
				rmenus = new HashSet();
			}
			rmenus.add(roleMenu.getMenuId());
			roleMenuMap.put(roleMenu.getRoleId(), rmenus);
			if (menuRoleMap.containsKey(roleMenu.getMenuId()))
				menuMap = (Map) menuRoleMap.get(roleMenu.getMenuId());
			else {
				menuMap = new HashMap();
			}
			menuMap.put(roleMenu.getRoleId(), roleMenu.getPurview());
			menuRoleMap.put(roleMenu.getMenuId(), menuMap);
		}
	}

	private void initUserAuthority() {
		if (userMenuMap == null) {
			userMenuMap = new ConcurrentHashMap();
		}
		if (menuUserMap == null) {
			menuUserMap = new ConcurrentHashMap();
		}
		Map menus = null;
		Set users = null;
		List<UserMenu> authList = getUserMenuList();
		for (UserMenu userMenu : authList) {
			if (userMenuMap.containsKey(userMenu.getUserId()))
				menus = (Map) userMenuMap.get(userMenu.getUserId());
			else {
				menus = new HashMap();
			}
			menus.put(userMenu.getMenuId(), userMenu.getPurview());
			userMenuMap.put(userMenu.getUserId(), menus);
			if (menuUserMap.containsKey(userMenu.getMenuId()))
				users = (Set) menuUserMap.get(userMenu.getMenuId());
			else {
				users = new HashSet();
			}
			users.add(userMenu.getUserId());
			menuUserMap.put(userMenu.getMenuId(), users);
		}
	}

	public boolean isPermissions(String userId, String menuId) {
		if (userMenuMap == null)
			initUserAuthority();
		Map map = (Map) userMenuMap.get(userId);
		if (map == null)
			return Boolean.FALSE.booleanValue();
		if (map.containsKey(menuId)) {
			return Boolean.TRUE.booleanValue();
		}

		TreeNode parent = this.menuService.getMenuNode(menuId);
		if (parent == null)
			return Boolean.FALSE.booleanValue();
		List<TreeNode> sub = parent.getList();
		for (TreeNode node : sub) {
			if ((node.getList() != null) && (node.getList().size() > 0)
					&& (isPermissions(userId, node.getId()))) {
				return Boolean.TRUE.booleanValue();
			}

			if (map.containsKey(node.getId()))
				return Boolean.TRUE.booleanValue();
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean isRolePermissions(String roleId, String menuId) {
		if (roleMenuMap == null)
			initMenus();
		Set map = (Set) roleMenuMap.get(roleId);
		if (map == null)
			return Boolean.FALSE.booleanValue();
		return map.contains(menuId);
	}
}