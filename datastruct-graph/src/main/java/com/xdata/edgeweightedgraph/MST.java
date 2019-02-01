package com.xdata.edgeweightedgraph;

import java.util.Queue;
import com.xdata.edgeweightedgraph.struct.Edge;

public interface MST {
	Queue<Edge> edges();
	
	double weight();
}
