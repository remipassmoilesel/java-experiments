package org.remipassmoilesel.lightplayground.losttime;

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
                    ", links=" + links +
                    '}';
        }
    }

    static class Graph {
        Node root;
        final HashMap<Integer, Node> allNodes;

        Graph(Integer totalVertices) {
            this.root = new Node(0);
            this.allNodes = new HashMap<>(totalVertices);
        }

        void addEdge(Integer nId1, Integer nId2) {
            Node n1 = this.getNode(nId1);
            Node n2 = this.getNode(nId2);

            n1.links.add(n2);
        }

        private Node getNode(Integer id) {
            if (id == 0) {
                return this.root;
            }

            Node existing = this.allNodes.get(id);
            if (existing != null) {
                return this.allNodes.get(id);
            }

            Node newNode = new Node(id);
            this.allNodes.put(id, newNode);
            return newNode;
        }

        Integer getMaximumDepth() {
            return getMaximumDepth(this.root);
        }

        Integer getMaximumDepth(Node rootN) {
            if (rootN.links.size() < 1) {
                return 0;
            }
            int height = 0;
            for (Node child : rootN.links) {
                height = Math.max(height, getMaximumDepth(child));
            }
            return height + 1;
        }

    }

    public static void main(String[] args) {

        Graph graph = new Graph(21);
        graph.addEdge(0, 1);
        graph.addEdge(0, 8);
        graph.addEdge(0, 15);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(8, 9);
        graph.addEdge(8, 12);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(12, 13);
        graph.addEdge(12, 14);
        graph.addEdge(15, 16);
        graph.addEdge(15, 19);
        graph.addEdge(16, 17);
        graph.addEdge(16, 18);
        graph.addEdge(19, 20);
        graph.addEdge(19, 21);

        Integer maxDepth = graph.getMaximumDepth();

        System.out.println(maxDepth);
    }

}
