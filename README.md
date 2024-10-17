# Flipkart Automation Project

## Challenges Ahead!
In this Buildout, we will automate the Flipkart website and return the count of items with different star ratings, prices, and number of reviews.

## Important
- Create TestNG methods named `testCase01`, `testCase02`, and `testCase03`, and write the automation code for each in separate methods.

## Activity
The automation script will perform the following tasks:

### Test Case 1: `testCase01`
1. **Go to Flipkart**
   - Navigate to [www.flipkart.com](https://www.flipkart.com).
2. **Search for "Washing Machine"**
   - Enter "Washing Machine" in the search bar.
3. **Sort Results by Popularity**
   - Sort the search results by popularity.
4. **Count Items with Rating ≤ 4 Stars**
   - Print the count of items having a rating of 4 stars or less.

### Test Case 2: `testCase02`
1. **Search for "iPhone"**
   - Enter "iPhone" in the search bar.
2. **Filter by Discount**
   - Print the titles and discount percentages of items that have more than 17% discount.

### Test Case 3: `testCase03`
1. **Search for "Coffee Mug"**
   - Enter "Coffee Mug" in the search bar.
2. **Filter by Rating (4 Stars and Above)**
   - Select only the items that have a rating of 4 stars and above.
3. **Print the Title and Image URL of Top 5 Items with Highest Number of Reviews**
   - Print the details for the top 5 items that have the highest number of reviews.

## Notes
- **Wrapper Methods for Each Action:** Use wrappers for common Selenium actions (like clicking, sending keys, etc.) to ensure reusability.
- **TestNG Flow:** Each flow (test case) should be structured as a separate TestNG test.

### Handling CAPTCHAs
- Since we are automating an external site (Flipkart), CAPTCHAs may appear during execution. To overcome this, add `Thread.sleep()` statements to pause execution and manually clear the CAPTCHA if it appears.

### Code Quality Guidelines
- **Use Comments:** Add meaningful comments to explain complex code logic.
- **Use `System.out.println()` Statements Sparingly:** Only for debugging or essential information output.
- **Manage Sleep and Wait Statements:** Optimize `Thread.sleep()` and Selenium `wait` statements for better code quality.

## Project Setup Instructions

1. **Clone the Repository**
   ```bash
   [git clone https://github.com/Azhar041189/ME_QA_XFLIPKART_SEARCH.git]
