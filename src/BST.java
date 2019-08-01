import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<T extends Comparable<T>> {

    private class Node {
        T e;
        Node left, right;
        int size;   //该节点之下的所有节点总数

        private Node(T e) {
            this.e = e;
            left = null;
            right = null;
            size = 1;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else
        root = add(root, e);
    }

    private Node add(Node node, T e) {
        //先解决基本问题
//        if (root.e.equals(e)) {
//            return;
//        } else if (root.e.compareTo(e) < 0 && root.left == null) {
//            root.left = new Node(e);
//            size++;
//            return;
//        } else if (root.e.compareTo(e) > 0 && root.right == null) {
//            root.right = new Node(e);
//            size++;
//            return;
//        }

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
            node.size++;
        } else if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
            node.size++;
        }

        return node;
    }

    public boolean contains(T e) {
        return contains(root, e);
    }

    private boolean contains(Node node, T e) {
        if (node == null)
            return false;

        if (node.e.compareTo(e) == 0)
            return true;
        else if (node.e.compareTo(e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    public void preOrderWithoutRecursion() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.println(current.e);

            if (current.right != null)
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder() {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            System.out.println(current.e);
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
    }

    public T removeMin() {
        T min = minimum();
        root = removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
//            node.size--;
            return right;
        } else {
            node.left = removeMin(node.left);
            node.size--;
            return node;
        }
    }

    public T removeMax() {
        T max = maximum();
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
//            node.size--;
            return left;
        } else {
            node.right = removeMax(node.right);
            node.size--;
            return node;
        }
    }

    public void remove(T e) {
//        root = remove_1(root, e);
        root = remove_2(root, e);
    }

    private Node remove_2(Node node, T e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove_2(node.left, e);
            node.size--;
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove_2(node.right, e);
            node.size--;
            return node;
        } else {
            //左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                node.size--;
                return rightNode;
            }

            //右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                node.size--;
                return leftNode;
            }

            Node predecessor = maximum(node.left);
            predecessor.left = removeMax(node.left);
            size++;
            predecessor.right = node.right;

            node.left = node.right = null;
            size--;

            return predecessor;
        }
    }


    private Node remove_1(Node node, T e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove_1(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove_1(node.right, e);
            return node;
        } else {    //e.compareTo(node.e) == 0
            //左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            //右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            /*待删除节点左右都不为空的情况
            （1）找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            （2）用这个节点代替待删除节点的位置*/
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            size++;
            successor.left = node.left;

            node.left = node.right = null;
            size--;
            return successor;
        }
    }

    public T minimum() {
        if (isEmpty())
            throw new IllegalArgumentException("The Tree is empty.");

        return minimum(root).e;
    }

    private Node minimum(Node node) {
//        if (node == null)
//            return null;

        if (node.left == null)
            return node;
        else
            return minimum(node.left);
    }

    public T maximum() {
        if (isEmpty())
            throw new IllegalArgumentException("The Tree is empty.");

        return maximum(root).e;
    }

    private Node maximum(Node node) {
//        if (node == null)
//            return null;

        if (node.right == null)
            return node;
        else
            return maximum(node.right);
    }

    public T ceil(T e) {
        return ceil(root, e).e;
    }

    private Node ceil(Node node, T e) {
        //寻找天花板数
        if (node == null)
            return null;

        if (e.compareTo(node.e) == 0) {
            return node;
        } else if (e.compareTo(node.e) > 0) {
            return ceil(node.right, e);
        } else {
            Node tempNode = ceil(node.left, e);
            if (tempNode != null) return tempNode;
            else return node;
        }
    }

    public T floor(T e) {
        return floor(root, e).e;
    }

    private Node floor(Node node, T e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) == 0)
            return node;
        else if (e.compareTo(node.e) < 0)
            return floor(node.left, e);
        else {    //e.compareTo(node.e) > 0
            Node tempNode = floor(node.right, e);
            if (tempNode != null) return tempNode;
            else return node;
        }
    }

    public int rank(T e) {
        return rank(root, e);
    }

    private int rank(Node node, T e) {
        if (node == null)
            return 0;

        int leftSize = node.left == null ? 0 : node.left.size;

        if (node.e.compareTo(e) == 0) {
            return leftSize + 1;
        } else if (node.e.compareTo(e) > 0) {
            return rank(node.left, e);
        } else {
            return leftSize + 1 + rank(node.right, e);
        }
    }

    public T select(int index) {
        if (index >= size)
            throw new IllegalArgumentException(
                    String.format("select failed; rank out of bound; SIZE=%d, RANK=%d", size, index));

        return select(root, index).e;
    }

    private Node select(Node node, int index) {
        if (node == null)
            return null;

        int t = node.left == null ? 0 : node.left.size;
        if (t == index)
            return node;
        else if (t > index)
            return select(node.left, index);
        else
            return select(node.right, index - t - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        generateBSTString(root, 0, stringBuilder);
        return stringBuilder.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder result) {
        if (node == null) {
            result.append(generateDepthString(depth)).append("null\n");
            return;
        }

        result.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth + 1, result);
        generateBSTString(node.right, depth + 1, result);
    }

    private String generateDepthString(int depth) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++)
            stringBuilder.append("--");
        return stringBuilder.toString();
    }
}
