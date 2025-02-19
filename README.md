# Bank Management System API
## Advanced Coding Playground Challenge-4

### Problem Statement
You are tasked with creating a RESTful web service for a **Bank Management System**. The API will allow the bank to manage customer accounts, including basic operations such as creating accounts, viewing balances, depositing and withdrawing funds, and transferring money between accounts. The data will be stored in an in-memory data structure (e.g., `List`) for simplicity.

## Steps to Follow
1. This GitHub repository will be accessible to you once you accept the Playground Challenge.
2. You have to work directly in this GitHub repository. It is like your own copy of the original repository.

3. The folder structure is as given below: (showing major files only)
   ```
   root
   ├── pom.xml
   └── src/main
       ├── java/com/bankmgmt/app
       |   ├── entity
       |   |   └── Account.java
       |   ├── rest
       |   |   └── BankController.java
       |   ├── service
       |   |   └── BankService.java
       |   └── AppApplication.java
       └── resources
           ├── static
           |   └── index.html
           └── application.properties
   ```

4. Review the code/comments present in above files to understand the structure.
5. Implement the missing classes/methods marked with **TODO** comments.
6. To work on the files, you can clone this GitHub repository onto your system and then open it with an IDE like IntelliJ, or Eclipse.
7. Once done, push your changes to this remote GitHub repository.


## Files to Work On
- `src/main/resources/static/index.html`
- `src/main/java/com/bankmgmt/app/entity/Account.java`
- `src/main/java/com/bankmgmt/app/service/BankService.java`
- `src/main/java/com/bankmgmt/app/rest/BankController.java`

### Requirements:
1. **Create a Spring Boot application:**
   - Use Spring Boot to set up the application.
   - Use `Spring Web` dependency to create the RESTful APIs. This steps is already done. Add additional dependencies if neccessary.
   - Implement in-memory storage for bank accounts using a `List`.

2. **Account Entity:**
   Define an `Account` class with the following fields:
   - `id` (Integer, Unique Identifier for the Account)
   - `accountHolderName` (String, Not Null)
   - `balance` (Double, Positive Number)
   - `accountType` (String, either SAVINGS or CURRENT)
   - `email` (String, Unique, Not Null)

3. **REST Endpoints:** Implement the following REST endpoints for managing the bank accounts:
   - **Create a new account:** `POST /accounts`
     - Request Body: JSON object with fields `accountHolderName`, `accountType`, and `email`.
     - Response: A JSON object with the `id`, `accountHolderName`, `accountType`, `email` and the initial `balance` (which should be `0.0`).
   - **Get all accounts:** `GET /accounts`
     - Response: A list of all accounts in JSON format.
   - **Get an account by ID:** `GET /accounts/{id}`
     - Response: A JSON object of a single account, or `404 Not Found` if the account doesn't exist.
   - **Deposit funds into an account:** `POST /accounts/{id}/deposit`
     - Request Body: JSON object with the `amount` field (positive number).
     - Response: The updated account with the new balance.
   - **Withdraw funds from an account:** `POST /accounts/{id}/withdraw`
     - Request Body: JSON object with the `amount` field (positive number). Ensure the withdrawal doesn't exceed the account's balance.
     - Response: The updated account with the new balance, or `400 Bad Request` if insufficient funds are available.
   - **Transfer funds between accounts:** `POST /accounts/transfer?fromId={fromId}&toId={toId}&amount={amount}`
     - Query parameters: 
       - `fromId`: ID of the sender account
       - `toId`: ID of the recipient account
       - `amount`: The amount to be transferred (positive number)
     - Response: Updated accounts for both sender and recipient after the transfer, or `400 Bad Request` if there are errors (e.g., insufficient funds, invalid accounts).
   - **Delete an account:** `DELETE /accounts/{id}`
     - Response: `204 No Content` if the account is deleted successfully, or `404 Not Found` if the account doesn't exist.

4. **Validation:**
   - Ensure that the `amount` for deposits, withdrawals, and transfers is a positive number.
   - Ensure that the `balance` cannot be negative after a withdrawal.
   - Ensure that an account can’t have a negative balance after a transaction.
   - Validate that the `accountType` is either `SAVINGS` or `CURRENT`.
   - Ensure that the `email` is unique for each account.
   - If any validation criteria are not met, return a `400 Bad Request` response.

5. **In-memory Data Structure:**
   - Use a `List<Account>` to store the accounts in memory.
   - Each account should have a unique `id` (auto-incremented).

## Submission Requirements:
Implement the Spring Boot application with all required endpoints and validations. You can use Postman or cURL commands to test your API endpoints.

After completing the challenge and developing the solution code in your system, present it to the faculty for grading. 

Later, commit and push the changes to this repository. 
  - Stage your changes and commit the files:
    ```
    git add .
    git commit -m "Completed playground challenge"
    ```
  - Push your changes to the GitHub repository:
    ```
    git push
    ```

Good luck, and happy coding!
