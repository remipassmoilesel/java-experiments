package org.prog2.graphs;

import java.util.HashSet;

import junit.framework.Assert;

import org.junit.Test;

public class GraphTests {

  private static Graph createBaseGraph() {
    // graph de base a tester
    Graph graph = new Graph();

    graph.addArete("a", "b", 85);
    graph.addArete("b", "f", 80);
    graph.addArete("f", "i", 250);
    graph.addArete("i", "j", 84);

    graph.addArete("a", "c", 217);
    graph.addArete("c", "g", 186);
    graph.addArete("c", "h", 103);
    graph.addArete("d", "h", 183);
    graph.addArete("h", "j", 167);

    graph.addArete("a", "e", 173);
    graph.addArete("e", "j", 502);

    return graph;
  }

  @Test
  public void graphTest() {

    Graph graph = createBaseGraph();

    // tester l'obtention des distances
    Assert.assertEquals(85, graph.getOrCreate("a").getDistanceFrom(graph.getOrCreate("b")));
    Assert.assertEquals(217, graph.getOrCreate("a").getDistanceFrom(graph.getOrCreate("c")));
    Assert.assertEquals(173, graph.getOrCreate("a").getDistanceFrom(graph.getOrCreate("e")));

    // tester la mesure des distances
    GraphPath path1 = new GraphPath(graph.getOrCreate("a"));
    path1.addSummit(graph.getOrCreate("b"));
    path1.addSummit(graph.getOrCreate("f"));
    path1.addSummit(graph.getOrCreate("i"));

    GraphPath path2 = new GraphPath(graph.getOrCreate("a"));
    path2.addSummit(graph.getOrCreate("b"));
    path2.addSummit(graph.getOrCreate("f"));

    Assert.assertEquals(415, path1.getDistance());
    Assert.assertEquals(165, path2.getDistance());

    // Tester le compareto dans une liste
    HashSet<GraphPath> paths = new HashSet<GraphPath>();
    paths.add(path1);
    paths.add(path2);

    Assert.assertEquals(path2, paths.iterator().next());

    GraphPath path3 = new GraphPath(graph.getOrCreate("a"));
    Assert.assertEquals(3, path3.extend().size());

    Graph graph2 = new Graph();
    graph2.addArete("a", "b", 50);
    graph2.addArete("b", "c", 50);
    graph2.addArete("c", "a", 50);

    // tester l'ordre lexicographique en cas d'égalitéde sommets et de
    // distance
    GraphPath path4 = new GraphPath();
    path4.addSummit(graph2.getOrCreate("a"));
    path4.addSummit(graph2.getOrCreate("b"));

    GraphPath path5 = new GraphPath();
    path5.addSummit(graph2.getOrCreate("a"));
    path5.addSummit(graph2.getOrCreate("c"));

    Assert.assertEquals(true, path4.compareTo(path5) > 0);

    // tester le constructeur d'extension de chemin
    GraphPath path6 = new GraphPath(path5, graph2.getOrCreate("a"));
    Assert.assertEquals(path5.getSummits().size() + 1, path6.getSummits().size());

  }
}
