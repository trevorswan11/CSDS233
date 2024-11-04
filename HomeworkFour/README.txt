HW4_tcs94
- I created an Entry nested class as shown in class in order to better organize the data in the table and keep track of removed slots
- As the hash functions are only to be used in the `doubleHashingInsert` method, they are implemented in a nested local class for better project organization
- Created a nested class called ProbingError to throw when an error is ocurred, specifically when probing runs for too many iterations
- ProbingError is set to public to be used in testing and actual applications. Would just make a new Java file in real world applications
HW4_tcs94_test
- Uses the better comments extension in VSCode for personal readability 
- Each method is tested with a few different table sizes to make sure that the methods work as expected
- To throw the desired exception, I tested the linear probing method for the 4th case using a too small table size
- As the double hashing method is best with prime sizes, I tested a non-prime size to purposefully throw an Exception
