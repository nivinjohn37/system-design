-- Personal Expense Tracker
-- Assignment 1
-- Due Date: 20-06-2023

-- Problem Statement

-- Create a personal expense tracker to store information about our expenses, including the
-- date, amount, category, and description.

-- Step 1: Create a Database table to store the details about our personal expenses. Below
-- are the required columns in this table:
-- - expense_id (primary key, data type: serial)
-- - expense_date (data type: date),
-- - amount (data type: money),
-- - category_id (data type: integer), and
-- - description (data type: text)

CREATE TABLE Expense (
  expense_id SERIAL PRIMARY KEY,
  expense_date DATE,
  amount MONEY,
  category_id INTEGER,
  description VARCHAR(50) 
);

-- Step 2: Create another table named “Category” to store our expense categories. Below are
-- the required columns with their data types:
-- - category_id (primary key, data type: serial),
-- - category (data type: varchar), and
-- - budget (data type: money)

CREATE TABLE Category (
  category_id SERIAL PRIMARY KEY,
  category VARCHAR(50),
  budget MONEY
);


-- Step 3: Set up foreign key constraints to ensure our data remains consistent. Set the
-- `category` column in the `Expense` table to reference the `category_name` column in the
-- `Category` table. Provide the SQL command to add foreign key constraints as above to the
-- `Expense` table.

ALTER TABLE Expense
    ADD CONSTRAINT fk_expense_category FOREIGN KEY (category_id) REFERENCES Category (category_id);

-- Step 4: Insert data about your expenses in the tables above.

INSERT INTO Category (category, budget) VALUES
('Groceries', 100.00),
('Gas', 50.00),
('Entertainment', 20.00),
('Clothing', 30.00),
('Transportation', 40.00),
('Utilities', 50.00),
('Housing', 60.00),
('Healthcare', 70.00),
('Personal Care', 80.00);

INSERT INTO Expense (expense_date, amount, category_id, description) VALUES
('2023-05-01', 100.00, 1, 'Groceries'),
('2023-05-02', 50.00, 2, 'Gas'),
('2023-05-03', 20.00, 3, 'Entertainment'),
('2023-05-04', 30.00, 4, 'Clothing'),
('2023-05-05', 40.00, 5, 'Transportation'),
('2023-05-06', 50.00, 6, 'Utilities'),
('2023-05-07', 60.00, 7, 'Housing'),
('2023-05-08', 70.00, 8, 'Healthcare'),
('2023-05-09', 80.00, 9, 'Personal Care'),
('2023-05-03', 50.00, 1, 'Groceries'),
('2023-05-05', 150.00, 2, 'Gas'),
('2023-05-07', 120.00, 3, 'Entertainment'),
('2023-05-07', 130.00, 4, 'Clothing'),
('2023-05-02', 140.00, 5, 'Transportation'),
('2023-05-01', 150.00, 6, 'Utilities'),
('2023-05-21', 160.00, 7, 'Housing'),
('2023-05-14', 170.00, 8, 'Healthcare'),
('2023-05-15', 180.00, 9, 'Personal Care');



-- Step 5: To improve query performance, define an index on the amount column in the Expense table.

CREATE INDEX idx_expense_amount ON Expense (amount);

-- Step 6: Write a query to find the total expense in each category for the month of May 2023.

SELECT category_id,
       SUM(amount) AS total_expense
FROM Expense
WHERE expense_date BETWEEN '2023-05-01' AND '2023-05-31'
GROUP BY category_id
ORDER BY category_id;

-- in descending order.

SELECT category_id,
       SUM(amount) AS total_expense
FROM Expense
WHERE expense_date BETWEEN '2023-05-01' AND '2023-05-31'
GROUP BY category_id
ORDER BY total_expense DESC;
