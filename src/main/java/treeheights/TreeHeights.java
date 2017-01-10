package treeheights;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by yauyin on 1/10/17.
 */
public class TreeHeights {
  public static void main(String[] args) {
    TreeNode tree = new TreeNode(1);
    tree.addChild(new TreeNode(2));
    tree.addChild(new TreeNode(3));

    System.out.println(findNodesWithHeight(tree, 0));
    System.out.println(findNodesWithHeight(tree, 1));
  }

  public static Collection<TreeNode> findNodesWithHeight(TreeNode tree, int height) {
    List<TreeNode> nodesWithHeight = new LinkedList<>();

    postOrderTraverse(tree, treeHeight -> {
      if (treeHeight.height == height) {
        nodesWithHeight.add(treeHeight.node);
      }
    });

    return nodesWithHeight;
  }

  /**
   * Visit the tree with post-order traversal
   * @param tree
   * @param visitor
   * @return the height of the tree
   */
  public static int postOrderTraverse(TreeNode tree, Consumer<TreeHeight> visitor) {
    int currentHeight = 0;

    for (TreeNode child : tree.getChilds()) {
      currentHeight = Math.max(currentHeight, postOrderTraverse(child, visitor) + 1);
    }

    visitor.accept(new TreeHeight(tree, currentHeight));

    return currentHeight;
  }

  static class TreeHeight {
    final TreeNode node;
    final int height;

    TreeHeight(TreeNode node, int height) {
      this.node = node;
      this.height = height;
    }
  }
}
