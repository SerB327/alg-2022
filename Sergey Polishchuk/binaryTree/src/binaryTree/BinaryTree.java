package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Класс, реализующий структуру "бинарное дерево поиска". Бинарное дерево поиска — это бинарное дерево, обладающее
 * дополнительными свойствами: значение левого потомка меньше значения родителя, а значение правого потомка больше
 * значения родителя для каждого узла дерева. То есть, данные в бинарном дереве поиска хранятся в отсортированном виде.
 * При каждой операции вставки нового или удаления существующего узла отсортированный порядок дерева сохраняется. При
 * поиске элемента сравнивается искомое значение с корнем. Если искомое больше корня, то поиск продолжается в правом
 * потомке корня, если меньше, то в левом, если равно, то значение найдено и поиск прекращается.
 */
public class BinaryTree<V extends Comparable<V>> {

    Node<V> root;

    /**
     * Добавляет новый элемент в дерево.
     *
     * @param value Добавляемый элемент.
     */
    public void put(V value) {

        Node<V> node;
        if (root == null) {
            node = new Node<>(value, null);
            root = node;
        } else {
            node = root;
            while (true) {
                if (value.compareTo(node.value) < 0) {
                    if (node.left == null) {
                        node.left = new Node<>(value, node);
                        break;
                    } else {
                        node = node.left;
                    }
                } else {
                    if (node.right == null) {
                        node.right = new Node<>(value, node);
                        break;
                    } else {
                        node = node.right;
                    }
                }
            }
        }
    }

    /**
     * Удаляет указанный элемент из дерева.
     *
     * @param value удаляемый элемент.
     */
    public void remove(V value) {

        if (root == null) {
            return;
        }
        Node<V> node = root;
        Stack<Node<V>> stack = new Stack<>();
        while (true) {
            if (value.compareTo(node.value) == 0) {
                if (node == root) {
                    if (node.left != null && node.right != null) {
                        root = node.right;
                        root.parent = null;
                        node = node.left;
                        node.parent = null;
                    }
                    if (node.left == null && node.right != null) {
                        root = node.right;
                        root.parent = null;
                        break;
                    }
                    if (node.left != null && node.right == null) {
                        root = node.left;
                        root.parent = null;
                        break;
                    }
                } else {
                    if (node.left == null && node.right == null) {
                        if (node.parent.left == node) {
                            node.parent.left = null;
                            node.parent = null;
                            break;
                        }
                        if (node.parent.right == node) {
                            node.parent.right = null;
                            node.parent = null;
                            break;
                        }
                    }
                    if (node.left != null && node.right == null) {
                        if (node.parent.left == node) {
                            node.parent.left = node.left;
                            node.parent = null;
                            break;
                        }
                        if (node.parent.right == node) {
                            node.parent.right = node.left;
                            node.parent = null;
                            break;
                        }
                    }
                    if (node.left == null && node.right != null) {
                        if (node.parent.left == node) {
                            node.parent.left = node.right;
                            node.parent = null;
                            break;
                        }
                        if (node.parent.right == node) {
                            node.parent.right = node.right;
                            node.parent = null;
                            break;
                        }
                    }
                    if (node.parent.left == node) {
                        node.parent.left = node.right;
                        node.parent = null;
                        node = node.left;
                    }
                    if (node.parent.right == node) {
                        node.parent.right = node.left;
                        node.parent = null;
                        node = node.right;
                    }
                }
                stack.add(node);
                while (!stack.empty()) {
                    node = stack.peek();
                    put(stack.pop().value);
                    if (node.left != null) {
                        stack.add(node.left);
                    }
                    if (node.right != null) {
                        stack.add(node.right);
                    }
                }
            }
            if (value.compareTo(node.value) < 0) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    return;
                }
            } else {
                if (node.right != null) {
                    node = node.right;
                } else {
                    return;
                }
            }
        }
    }

    /**
     * Осуществляет бинарный поиск по дереву.
     *
     * @param value Искомый в дереве элемент.
     * @return {@code true}, если элемент в параметре присутствует в дереве, иначе {@code false}.
     */
    public boolean contains(V value) {

        if (root == null) {
            return false;
        }
        Node<V> node = root;
        while (true) {
            if (value.compareTo(node.value) == 0) {
                return true;
            }
            if (value.compareTo(node.value) < 0) {
                if (node.left == null) {
                    return false;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    return false;
                }
                node = node.right;
            }
        }
    }

    /**
     * Преобразует дерево в {@link ArrayList}.
     *
     * @return {@link ArrayList} со всеми элементами дерева.
     */
    public ArrayList<V> toArray() {

        if (root == null) {
            return null;
        }
        Node<V> node;
        Stack<Node<V>> stack = new Stack<>();
        ArrayList<V> list = new ArrayList<>();
        stack.add(root);
        while (!stack.empty()) {
            node = stack.pop();
            list.add(node.value);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return list;
    }

    /**
     * Осуществляет обход всего дерева, вычисляет длины ветвей, затем находит наибольшую из них.
     *
     * @return Количество узлов в самой длинной ветке.
     */
    public int treeHeight() {

        if (root == null) {
            return 0;
        }
        Node<V> node;
        Stack<Node<V>> stack = new Stack<>();
        LinkedList<Integer> list = new LinkedList<>();
        stack.add(root);
        int count = 1;
        while (!stack.empty()) {
            node = stack.pop();
            count++;
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right == null && node.left == null) {
                list.add(count);
                count = 1;
            }
        }
        int max = 0;
        for (Integer value : list) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void clean() {

        root = null;
    }

    /**
     * Класс, представляющий составляющую дерева {@link BinaryTree} - узел, содержащий ключ {@link #value}, ссылку на
     * родителя {@link #parent} и ссылки на левого {@link #left} и правого {@link #right} потомков.
     */
    private static class Node<V extends Comparable<V>> {

        V value;
        Node<V> right;
        Node<V> left;
        Node<V> parent;

        public Node(V value, Node<V> parent) {

            this.value = value;
            this.parent = parent;
        }
    }
}