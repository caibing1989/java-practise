package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-23
 */
public class Test {
    public static class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    public static List<Node> reBuildTree(List<Node> preList, List<Node> midList) {
        return help(preList, midList);
    }

    private static List<Node> help(List<Node> preList, List<Node> midList) {
        int length = preList.size();

        List<Node> afterList = new ArrayList<Node>(length);

        if (length == 0) {
            return afterList;
        }

        for (int i = 0; i < length; i++) {
            // 中节点
            Node midNodeFromPre = preList.get(i);

            // 右节点
            Node rightNodeFromPre = null;
            if (i + 2 < length) {
                rightNodeFromPre = preList.get(i + 2);
            }

            // 左节点
            Node leftNodeFromMid = midList.get(i);

            // rebuild
            // 左
            afterList.add(leftNodeFromMid);
            // 右
            afterList.add(rightNodeFromPre);
            // 中
            afterList.add(midNodeFromPre);

            if (afterList.size() == length) {
                break;
            }
        }

        return afterList;
    }

    public static void main(String[] args) {
        List<Node> preList = new ArrayList<>();
        Node midNode = new Node(1);
        Node leftNode =new Node(2);
        Node rightNode = new Node(3);

        preList.add(midNode);
        preList.add(leftNode);
        preList.add(rightNode);

        List<Node> midList = new ArrayList<>();
        midList.add(leftNode);
        midList.add(midNode);
        midList.add(rightNode);

        List<Node> afterList = reBuildTree(preList, midList);

        for (Node element : afterList) {
            System.out.println(element.val);
        }
    }
}
