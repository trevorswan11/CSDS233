A list of changes I made that are notable with respect to CSDS233 HW1
- getter/setters used in all classes (typically under the "boilerplate" comment). They are self explanatory, and therefore not commented
- Javadoc formatting used for important methods
- BankAccount's withdraw returns a boolean instead of the amount to be removed - if the user is sending an amount to remove, then I wanted the method to tell them if that amount was valid or not and remove it from the account accordingly
- Added numGrades and sum to Student so that the grade calculation is constant time. Variables are updated as grades are added, so that prevents long iteration through the arraylist
- Test cases just examine conditional branches when applicable and test according to the assignments guidelines more generally
- Wrote a test class for the math functions to ensure consistency