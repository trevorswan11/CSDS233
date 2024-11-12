BinarySearchTree:
- Created a reset method to clear a tree of its children, results in a tree with only a specific root node
- Used helper methods (specified in javadoc comments) to better execute more complex tree manipulations

BinarySearchTreeMain
- Each of the test cases are done in their own static methods
- All BST objects are created at the top of the file in one line as private fields
- ! is used to denote the test case
- ? is used to denote the requirements
- main method simply calls all of the test methods
- Javadoc formatting used to allow naming of test methods to testcase<Num>
- Javadoc formatting follows from a vs code extension allowing for more clear comment descriptions
- Created a basic Exception nested class to handle improper behavior without using Assertions
- I did more extensive testing than required to ensure proper behavior (comments outline what tests are doing)