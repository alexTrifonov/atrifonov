package ru.job4j.taskList;

/**
 * Class for check on existence of cycle in linked list.
 * @author atrifonov.
 * @since 30.08.2017.
 * @version 1.
 */
public class CheckCycle {
    /**
     * Check linked list on existence of cycle.
     * @param first the sentinel of linked  list.
     * @return true if list has cycle.
     */
    public static boolean hasCycle(Node first) {
        Node node = first;
        boolean cycleHas = false;
        while (node.next != null) {
            Node tracer = first;
            while (tracer != node) {
                if(tracer.next == node.next) {
                    cycleHas = true;
                    break;
                }
                tracer = tracer.next;
            }
            if(cycleHas) {
                break;
            }
            node = node.next;
        }
        return cycleHas;
    }

    /**
     * Check linked list on existence of cycle.
     * @param first the sentinel of linked  list.
     * @return true if list has cycle.
     */
    public static boolean hasCycleTwo(Node first) {
        boolean cycleHas = false;
        Node node = first;
        Node fastNode;
        if (node.next != null) {
            fastNode = node.next.next;
            while (fastNode != null) {
                if(node.next == fastNode.next) {
                    cycleHas = true;
                    break;
                }
                node = node.next;
                if(fastNode.next != null) {
                    fastNode = fastNode.next.next;
                } else {
                    break;
                }
            }
        }
        return cycleHas;
    }
}
