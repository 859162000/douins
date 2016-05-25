package com.mango.framework.basic.service.impl;

import com.mango.core.tree.CheckedTreeJsonFormat;
import com.mango.core.tree.DepthIterator;
import com.mango.core.tree.JcStack;
import com.mango.core.tree.TreeNode;
import com.mango.framework.basic.service.MenuService;
import com.mango.framework.basic.service.RoleMenuService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("checkedMenuTreeJsonFormat")
public class CheckedMenuTreeJsonFormat extends CheckedTreeJsonFormat {
	@Autowired
	RoleMenuService roleMenuService;
	@Autowired
	MenuService menuService;
	Map<String, Long> authmp = null;
	boolean flag = true;
	Map<String, TreeNode> treeMpfor;
	int cp;

	public synchronized StringBuffer treeDataFormat(TreeNode treeNode, Map<String, TreeNode> treeMp, String condition,
			int type) {
		if (treeMp.isEmpty()) {
			return new StringBuffer();
		}
		this.treeMpfor = treeMp;
		this.treestack = new JcStack();
		this.counstack = new HashMap();
		this.loststack = new JcStack();
		this.treeBuffer = new HashMap();
		this.had = new HashMap();
		this.treestack.push(treeNode.iterator());
		this.loststack.push(treeNode);
		this.treeBuffer.put(treeNode.getId(), getLeafNode(treeNode));
		int i = 0;
		TreeNode pa = null;
		StringBuffer rtn = null;
		switch (type) {
		case 1:
			while (printhasNext()) {
				i++;
				this.flag = true;
				this.cp = 0;
				Iterator<TreeNode> it = (Iterator) this.treestack.peek();
				TreeNode next = (TreeNode) it.next();
				String menuId = next.getId();
				Map<String, Long> auth = this.roleMenuService.getRoleMenu(menuId);
				long useAuth = 0L;
				this.counstack.put(menuId, Integer.valueOf(next.getSize()));
				if (next.getSize() > 0) {
					this.treestack.push(next.iterator());
					this.loststack.push(next);
					i = 0;
				} else {
					if (auth.containsKey(condition)) {
						useAuth = ((Long) auth.get(condition)).longValue();
					} else if (auth.containsKey("default")) {
						useAuth = 0L;
					} else {
						Integer ii = (Integer) this.counstack.get(next.getParentId());
						if ((ii == null) || (i != ii.intValue())) {
							continue;
						}
						pa = (TreeNode) treeMp.get(next.getParentId());
						super.getParent(pa, pa.getList());
						i = 1;

						continue;
					}
					putParent(next);
					if (useAuth > 0L) {
						String childStr = getButtonbf(menuId, useAuth).toString();
						if (childStr.length() > 0) {
							childStr = childStr.substring(0, childStr.length() - 1);
						}
						next.setChecked(this.flag);
						this.treeBuffer.put(menuId,
								super.getBranchNode(next, super.getLeafNode(next).toString(), childStr));
					}
					Integer ii = (Integer) this.counstack.get(next.getParentId());
					if ((ii != null) && (i == ii.intValue())) {
						pa = (TreeNode) treeMp.get(next.getParentId());
						super.getParent(pa, pa.getList());
						i = 1;
					}
				}
			}
			super.getLost();
			rtn = (StringBuffer) this.treeBuffer.get(treeNode.getId());
			break;
		case 2:
			while (printhasNext()) {
				i++;
				this.flag = true;
				this.cp = 0;
				Iterator<TreeNode> it = (Iterator) this.treestack.peek();
				TreeNode next = (TreeNode) it.next();
				String menuId = next.getId();
				Map<String, Long> auth = this.roleMenuService.getRoleMenu(menuId);
				long useAuth = 0L;
				this.counstack.put(menuId, Integer.valueOf(next.getSize()));
				if (next.getSize() > 0) {
					this.treestack.push(next.iterator());
					this.loststack.push(next);
					i = 0;
				} else {
					if (auth.containsKey(condition)) {
						useAuth = ((Long) auth.get(condition)).longValue();
					} else {
						useAuth = 0L;
					}
					putParent(next);
					String childStr = getButtonbf(menuId, useAuth).toString();
					if (childStr.length() > 0) {
						childStr = childStr.substring(0, childStr.length() - 1);
					}
					if (useAuth > 0L) {
						next.setChecked(this.flag);
					}
					this.treeBuffer.put(menuId,
							super.getBranchNode(next, super.getLeafNode(next).toString(), childStr));

					Integer ii = (Integer) this.counstack.get(next.getParentId());
					if ((ii != null) && (i == ii.intValue())) {
						pa = (TreeNode) treeMp.get(next.getParentId());
						super.getParent(pa, pa.getList());
						i = 1;
					}
				}
			}
			super.getLost();
			rtn = (StringBuffer) this.treeBuffer.get(treeNode.getId());
			break;
		case 3:
			while (printhasNext()) {
				i++;
				Iterator<TreeNode> it = (Iterator) this.treestack.peek();
				TreeNode next = (TreeNode) it.next();
				next.setChecked(false);
				this.treeBuffer.put(next.getId(), super.getLeafNode(next));
				this.counstack.put(next.getId(), Integer.valueOf(next.getSize()));
				if (next.getSize() > 0) {
					this.treestack.push(next.iterator());
					this.loststack.push(next);
					i = 0;
				} else {
					Integer ii = (Integer) this.counstack.get(next.getParentId());
					if ((ii != null) && (i == ii.intValue())) {
						pa = (TreeNode) treeMp.get(next.getParentId());
						super.getParent(pa, pa.getList());
						i = 1;
					}
				}
			}
			super.getLost();
			rtn = (StringBuffer) this.treeBuffer.get(treeNode.getId());
		}
		this.treestack = null;
		this.counstack = null;
		this.loststack = null;
		this.treeBuffer = null;
		this.had = null;
		return rtn == null ? new StringBuffer() : rtn;
	}

	void _getLost() {
		TreeNode lost = null;
		Boolean flag = null;
		while (!this.loststack.isEmpty()) {
			lost = (TreeNode) this.loststack.pop();
			flag = (Boolean) this.had.get(lost.getId());
			if (flag == null) {
				_getParent(lost, lost.getList(), this.authmp);
			}
		}
	}

	private void _getParent(TreeNode parent, List<TreeNode> list, Map<String, Long> mp) {
		StringBuffer temppaBuffer = null;
		StringBuffer paBuffer = null;
		this.had.put(parent.getId(), Boolean.TRUE);
		for (TreeNode tree : list) {
			if (mp.containsKey(tree.getId())) {
				temppaBuffer = (StringBuffer) this.treeBuffer.get(parent.getId());
				if ((temppaBuffer != null) && (!temppaBuffer.toString().equals(""))) {
					paBuffer = getBranchNode(parent, temppaBuffer.toString(),
							((StringBuffer) this.treeBuffer.get(tree.getId())).toString());
					this.treeBuffer.put(parent.getId(), paBuffer);
				}
			}
		}
	}

	boolean isLegal(TreeNode childNode, String userId) {
		if (childNode == null) {
			return false;
		}
		Set<String> set = null;
		for (Iterator<TreeNode> itsub = new DepthIterator(childNode.iterator()); itsub.hasNext();) {
			TreeNode childone = (TreeNode) itsub.next();
			set = this.roleMenuService.getMenuUser(childone.getId());
			if (set != null) {
				if (set.contains(Integer.valueOf(userId))) {
					return true;
				}
			}
		}
		return false;
	}

	private void putParent(TreeNode node) {
		this.cp += 1;
		while ((!this.menuService.isRoot(node.getParentId())) && (this.cp < 10)) {
			node = (TreeNode) this.treeMpfor.get(node.getParentId());
			if (!this.treeBuffer.containsKey(node.getId())) {
				this.treeBuffer.put(node.getId(), getLeafNode(node));
			}
			putParent(node);
		}
	}

	public StringBuffer getButtonbf(String menuId, long useAuth) {
		StringBuffer tempjbf = new StringBuffer();
		return tempjbf;
	}
}
