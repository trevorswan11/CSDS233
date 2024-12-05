Graph Class
- I added a toString implementation to the graph class so that I could test the printGraph method more easily with junit
- I added an mstToString implementation tot the class so that I could test the MST method more easily with junit
- I added hasEdge to both the graph and vertex classes for easier testing of edge addition into the graph 
    -> These methods are implemented with and without respect of the weight for easier testing
- I used hash maps with the Vertex and Graph classes for efficient lookup, addition, and deletion into the graph
    -> This implementation also allows encapsulation by preventing the retrieval of the private Vertex and Edge classes outside of its parent class, but giving access to the names themselves
- There are NO private helper methods in the Graph class only because I needed methods to use for junit testing
- Vertex has two helper methods, mentioned earlier as overloaded hasEdge methods, in order to facilitate easy use of the hasEdge methods in the graph class, which are publicly accessible

GraphTest Class
- Aside from testing all of the required methods from this assignment, I also tested the example given in the assignment instructions and compared the example outputs to what my code outputted
- I wrote a 'removeEveryOtherWord' method so that I could compare String arrays in an adjacency list without taking into account the weights of the edges. This was done for alphabetical ordering
- I wrote a 'isAlphabeticallySorted' method so that I could check Vertex edge ordering. This checks all connected vertices for being returned in alphabetical order in the adjacency list, ignoring the 'head' (first) vertex
- I wrote a 'deepMSTEquals' method to compare the MST output to an expected list. THis method is designed to be independent of ordering, which is why Sets are used (all unique so no data loss).

Note: I made use of all the java.util classes imported to facilitate correct implementations