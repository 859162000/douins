package com.mango.framework.basic.service.impl;

import com.mango.core.logger.SimpleLogger;
import com.mango.core.tree.AbstractTreeOperate;
import com.mango.core.tree.DepthIterator;
import com.mango.core.tree.JcTreeNode;
import com.mango.core.tree.TreeFormat;
import com.mango.core.tree.TreeNode;
import com.mango.exception.DataBaseAccessException;
import com.mango.framework.basic.model.Menu;
import com.mango.framework.basic.service.LoginUserService;
import com.mango.framework.basic.service.MenuService;
import com.mango.framework.basic.service.RoleMenuService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuServiceImpl extends AbstractTreeOperate implements MenuService {
	private SimpleLogger logger = SimpleLogger.getLogger(MenuServiceImpl.class);

	@Autowired
	private BaseDao<Menu> menuDao;

	@Autowired
	private TreeFormat treeformat;

	@Autowired
	@Qualifier("checkedTreeformat")
	private TreeFormat checkedTreeJsonFormat;

	@Autowired
	private RoleMenuService roleMenuService;

	@Autowired
	@Qualifier("loginUserService")
	private LoginUserService loginUserService;
	private String R_M = "46265c9ac38b4186a88acda8b9269611";

	private String mapper = Menu.class.getName() + "Mapper.";

	private static Map<String, TreeNode> menuNodeMap = null;

	private static Map<String, String> tcodeMap = null;

	private static Map<String, String> menuMap = null;

	public boolean isRoot(String menuId) {
		if (StringUtils.isBlank(menuId)) {
			return Boolean.FALSE.booleanValue();
		}
		return this.R_M.equals(menuId);
	}

	public String getMenusByParentId(String userId, String pId) {
		if (menuNodeMap == null)
			getTreeData(null);
		StringBuffer jsonTreeBuf = new StringBuffer("[");
		if (StringUtils.isBlank(pId))
			pId = this.R_M;
		if (menuNodeMap.containsKey(pId)) {
			TreeNode treeNode = (TreeNode) menuNodeMap.get(pId);
			if (treeNode == null)
				return "]";
			jsonTreeBuf.append(getNodeStr(userId, treeNode.getList()));
		}
		if ((pId.equals(this.R_M)) && (jsonTreeBuf.toString().equals("["))) {
			jsonTreeBuf.append("{\"id\":\"-1\",\"text\":\"很抱歉,没有您权限范围的菜单!\"}");
		}
		jsonTreeBuf.append("]");
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("userId:" + userId + " menuId:" + pId
					+ " MenuStr:" + jsonTreeBuf.toString());
		}
		return "{\"success\":true,\"data\":" + jsonTreeBuf.toString() + "}";
	}

	public String getMenuTree(String userId) {
		StringBuffer treebf = new StringBuffer();
		try {
			if (menuNodeMap == null)
				getTreeData(null);
			TreeNode root = (TreeNode) menuNodeMap.get(this.R_M);
			treebf = this.treeformat.treeDataFormat(root, menuNodeMap);
		} catch (Exception e) {
			this.logger.error(" getMenuTree exception", e);
		}
		if (this.logger.isDebugEnabled())
			this.logger.debug("{{\"success\":true},\"data\":["
					+ treebf.toString() + "]}");
		return "{\"success\":true,\"data\":[" + treebf.toString() + "]}";
	}

	public TreeNode getRootNode() {
		if (menuNodeMap == null)
			getTreeData(null);
		return (TreeNode) menuNodeMap.get(this.R_M);
	}

	public Map<String, TreeNode> getMenuMap() {
		if (menuNodeMap == null) {
			getTreeData(null);
		}
		return Collections.unmodifiableMap(menuNodeMap);
	}

	public String getRoleMenuTree(String userId, String roleId) {
		StringBuffer treebf = new StringBuffer();
		try {
			if (menuNodeMap == null)
				getTreeData(null);
			TreeNode rootNode = (TreeNode) menuNodeMap.get(this.R_M);
			if (rootNode == null)
				return "";
			JcTreeNode child = null;
			for (Iterator itsub = new DepthIterator(rootNode.iterator()); itsub
					.hasNext();) {
				child = (JcTreeNode) itsub.next();
				if (child != null) {
					if (this.roleMenuService.isRolePermissions(roleId,
							child.getId()))
						child.setChecked(Boolean.TRUE.booleanValue());
				}
			}
			treebf = this.checkedTreeJsonFormat.treeDataFormat(rootNode,
					menuNodeMap);

			for (Iterator itsub = new DepthIterator(rootNode.iterator()); itsub
					.hasNext();) {
				child = (JcTreeNode) itsub.next();
				if (child != null) {
					child.setChecked(Boolean.FALSE.booleanValue());
				}
			}
		} catch (Exception e) {
			this.logger.error(" getMenuTree exception", e);
		}

		return treebf.toString();
	}

	public TreeNode getMenuNode(String menuId) {
		if (menuNodeMap == null) {
			getTreeData(null);
		}
		return (TreeNode) menuNodeMap.get(menuId);
	}

	public Page<Menu> getPage(Menu menu, Page<Menu> page) {
		if (page != null) {
			Page<Menu> pageresult = this.menuDao.getList(this.mapper
					+ "getMenu", menu, page);
			for (Menu m : pageresult.getResult()) {
				m.setParentName(getName(m.getParentId()));
			}
			return pageresult;
		}
		return null;
	}

	public Menu findByKey(String key) {
		return (Menu) this.menuDao.get(this.mapper + "selectByPrimaryKey", key);
	}

	public List<Menu> getAllMenus() {
		return this.menuDao.getAll(this.mapper + "getAllMenu");
	}

	public String getTcode(Integer menuId) {
		if (tcodeMap == null)
			flush();
		return (String) tcodeMap.get(menuId);
	}

	public String getMenuId(String tcode) {
		if (menuMap == null)
			flush();
		return (String) menuMap.get(tcode);
	}

	public String getTreeData(String condition) {
		List<Menu> list = getAllMenus();
		if (list.isEmpty())
			return null;
		if (menuNodeMap == null)
			menuNodeMap = new ConcurrentHashMap();
		else
			menuNodeMap.clear();
		if (menuMap == null)
			menuMap = new ConcurrentHashMap();
		else
			menuMap.clear();
		if (tcodeMap == null)
			tcodeMap = new ConcurrentHashMap();
		else
			tcodeMap.clear();
		this.treeMp = new ConcurrentHashMap();
		JcTreeNode treeNode = null;
		for (Menu menu : list) {
			treeNode = new JcTreeNode();
			treeNode.setId(menu.getMenuId());
			if (StringUtils.isBlank(menu.getParentId()))
				treeNode.setParentId("kinjo");
			else {
				treeNode.setParentId(menu.getParentId());
			}
			treeNode.setNodeValue(menu.getMenuName());
			treeNode.setNodeName(menu.getMenuName());
			treeNode.setLink(menu.getMenuLink());
			treeNode.setIcon(menu.getMenuIcon());
			treeNode.setPriority(menu.getMenuSeq() == null ? 0 : menu
					.getMenuSeq().intValue());
			this.treeMp.put(treeNode.getId(), treeNode);
			tcodeMap.put(menu.getMenuId(), menu.getMenuCode());
			menuMap.put(menu.getMenuCode(), menu.getMenuId());
		}
		try {
			super.constructTree();
			if (this.root == null)
				return null;
			TreeNode child = null;
			for (Object itsub = new DepthIterator(this.root.iterator()); ((Iterator) itsub)
					.hasNext();) {
				child = (TreeNode) ((Iterator) itsub).next();
				if (child != null) {
					menuNodeMap.put(child.getId(), child);
				}
			}
		} catch (Exception e) {
			this.logger.error(" get menu tree exception", e);
		} finally {
			super.clear();
		}
		return null;
	}

	private StringBuffer getNodeStr(String userId, List<TreeNode> nodeList) {
		StringBuffer jsonTreeBufSub = new StringBuffer();
		StringBuffer result = null;
		mySort(nodeList);
		TreeNode subNode = null;
		if (this.loginUserService.isAdmin(userId)) {
			int i = 0;
			for (int j = nodeList.size(); i < j; i++) {
				subNode = (TreeNode) nodeList.get(i);
				jsonTreeBufSub.append(this.treeformat.getLeafNode(subNode));
				if (i != j - 1)
					jsonTreeBufSub.append(",");
			}
		} else {
			int i = 0;
			for (int j = nodeList.size(); i < j; i++) {
				subNode = (TreeNode) nodeList.get(i);
				if (!subNode.getId().equals(this.R_M)) {
					if ((this.roleMenuService.isPermissions(userId,
							subNode.getId()))
							&& (Boolean.TRUE.booleanValue())) {
						jsonTreeBufSub.append(this.treeformat
								.getLeafNode(subNode));
						if (i != j - 1)
							jsonTreeBufSub.append(",");
					}
				}
			}
		}
		String lastW = "";
		if (jsonTreeBufSub.length() > 0) {
			lastW = jsonTreeBufSub.substring(jsonTreeBufSub.length() - 1,
					jsonTreeBufSub.length());
		}
		if (",".equals(lastW)) {
			if (jsonTreeBufSub.length() > 0)
				result = new StringBuffer(jsonTreeBufSub.substring(0,
						jsonTreeBufSub.length() - 1));
		} else {
			result = jsonTreeBufSub;
		}
		return result;
	}

	private void mySort(List<TreeNode> v) {
		Collections.sort(v, new Comparator<TreeNode>() {
			public int compare(TreeNode o1, TreeNode o2) {
				if (o1.getPriority() > o2.getPriority())
					return 1;
				if (o1.getPriority() < o2.getPriority()) {
					return -1;
				}
				return 0;
			}
		});
	}

	private String getName(String id) {
		if (menuNodeMap == null)
			getTreeData(null);
		if (StringUtils.isBlank(id))
			return "";
		if (menuNodeMap.containsKey(id)) {
			return ((TreeNode) menuNodeMap.get(id)).getNodeName();
		}
		return "";
	}

	private void flush() {
		getTreeData(null);
	}

	protected void init() {
	}

	public boolean save(String user, Menu menu) {
		try {
			menu.setOpUser(user);
			menu.setMenuStatus("1");
			if ((StringUtils.isNotBlank(menu.getParentId()))
					&& (StringUtils.isBlank(menu.getParentName())))
				menu.setParentName(getName(menu.getParentId()));
			if (StringUtils.isBlank(menu.getParentId())) {
				menu.setParentId(this.R_M);
			}
			if (this.menuDao.save(this.mapper + "insert", menu) > 0) {
				flush();
				return Boolean.TRUE.booleanValue();
			}
			return Boolean.FALSE.booleanValue();
		} catch (DataBaseAccessException e) {
			this.logger.error(" save DataBaseAccessException:", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean update(String user, Menu menu) {
		try {
			if (this.menuDao.save(this.mapper + "updateMenu", menu) > 0) {
				flush();
				return Boolean.TRUE.booleanValue();
			}
			return Boolean.FALSE.booleanValue();
		} catch (DataBaseAccessException e) {
			this.logger.error(" update DataBaseAccessException:", e);
		}
		return Boolean.FALSE.booleanValue();
	}

	public boolean delete(String user, Menu menu) {
		try {
			if (this.menuDao.delete(this.mapper + "deleteMenu", menu) > 0) {
				this.roleMenuService.deleteByMenu(menu.getMenuId());
				flush();
				return Boolean.TRUE.booleanValue();
			}
			return Boolean.FALSE.booleanValue();
		} catch (DataBaseAccessException e) {
			this.logger.error(" delete DataBaseAccessException:", e);
		}
		return Boolean.FALSE.booleanValue();
	}
}