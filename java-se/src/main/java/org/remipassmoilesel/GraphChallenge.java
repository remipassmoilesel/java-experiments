package org.remipassmoilesel;

import java.util.*;
import java.util.stream.Collectors;

public class GraphChallenge {

    static class Node {
        final Integer id;
        List<Node> links = new ArrayList<>();

        public Node(Integer id) {
            this.id = id;
        }

        void setLinks(Node... nodes) {
            this.links = Arrays.asList(nodes);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(id, node.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", links=" + links.stream().map(n -> n.id).collect(Collectors.toList()) +
                    '}';
        }
    }

    static class Graph {
        Node root;

        Graph() {
            this.root = new Node(0);
        }

        void addEdge(Node n1, Node n2) {
            if (this.root.links.size() == 0) {
                this.root.setLinks(n1, n2);
            }

            n1.links.add(n2);
        }

        Integer getMaximumDepth() {
            return getMaximumDepth(this.root) - 1;
        }

        Integer getMaximumDepth(Node rootN) {
            int height = 0;
            if (rootN == null) {
                return height;
            }
            if (rootN.links == null) {
                return 1;
            }
            for (Node child : rootN.links) {
                height = Math.max(height, getMaximumDepth(child));
            }
            return height + 1;
        }

    }

    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addEdge(new Node(1), new Node(2));
        graph.addEdge(new Node(2), new Node(3));
        graph.addEdge(new Node(3), new Node(4));
        graph.addEdge(new Node(3), new Node(7));
        graph.addEdge(new Node(4), new Node(5));
        graph.addEdge(new Node(4), new Node(6));
        graph.addEdge(new Node(7), new Node(8));

        Integer maxDepth = graph.getMaximumDepth();

        System.out.println(maxDepth);
    }

}
