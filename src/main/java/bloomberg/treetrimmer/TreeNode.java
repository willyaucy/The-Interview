package bloomberg.treetrimmer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by yauyin on 1/10/17.
 */
class TreeNode {
  private final int val;
  private final List<TreeNode> childs;

  TreeNode(int val) {
    this.val = val;
    this.childs = new ArrayList<>();
  }

  public List<TreeNode> getChilds() {
    return Collections.unmodifiableList(childs);
  }

  public int getVal() {
    return val;
  }

  public void addChild(TreeNode node) {
    childs.add(node);
  }

  @Override
  public String toString() {
    return String.format("[TreeNode val=%d]", val);
  }

  public String toStringDetailed() {
    return String.format("[TreeNode val=%d, childs: %s]", val, childs.toString());
  }
}
