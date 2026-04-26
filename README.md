# Banking Application

A Java-based desktop banking application with a GUI interface for managing user accounts, transfers, and transactions.

## Table of Contents
- [Prerequisites](#prerequisites)
- [PostgreSQL Installation](#postgresql-installation)
- [Database Setup](#database-setup)
- [Project Setup in IntelliJ](#project-setup-in-intellij)
- [Running the Application](#running-the-application)

## Prerequisites

Before you begin, ensure you have the following installed on your computer:
- **Java Development Kit (JDK)** version 8 or higher
- **IntelliJ IDEA** (Community or Ultimate edition)
- **PostgreSQL** (version 10 or higher)

## PostgreSQL Installation

### Windows
1. Download the PostgreSQL installer from [postgresql.org](https://www.postgresql.org/download/windows/)
2. Run the installer and follow the setup wizard
3. When prompted for a password, remember it (you'll need it later)
4. Install pgAdmin (optional, for database management GUI)
5. Complete the installation

### macOS
Using Homebrew:
```bash
brew install postgresql
brew services start postgresql
```

### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo service postgresql start
```

### Verify Installation
Open a terminal/command prompt and run:
```bash
psql --version
```

## Database Setup

### Step 1: Access PostgreSQL
Open a terminal/command prompt and connect to PostgreSQL:

**Windows/macOS/Linux:**
```bash
psql -U postgres
```
You will be prompted for the password you set during installation.

### Step 2: Create the Database User
```sql
CREATE USER myuser WITH PASSWORD 'mypassword';
```

### Step 3: Create the Database
```sql
CREATE DATABASE bankdb OWNER myuser;
```

### Step 4: Grant Privileges
```sql
GRANT ALL PRIVILEGES ON DATABASE bankdb TO myuser;
```

### Step 5: Connect to the Database and Create Tables
Exit the postgres user and connect as the new user:
```bash
psql -U myuser -d bankdb
```

Create the required tables:

```sql
-- Users Table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    pin VARCHAR(4) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Accounts Table
CREATE TABLE accounts (
    account_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_type VARCHAR(50),
    balance DECIMAL(12, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Transactions Table
CREATE TABLE transactions (
    transaction_id SERIAL PRIMARY KEY,
    from_account_id INT,
    to_account_id INT,
    amount DECIMAL(12, 2) NOT NULL,
    transaction_type VARCHAR(50),
    description VARCHAR(255),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (from_account_id) REFERENCES accounts(account_id),
    FOREIGN KEY (to_account_id) REFERENCES accounts(account_id)
);
```

Type `\q` to exit psql.

## Project Setup in IntelliJ

### Step 1: Open the Project
1. Open IntelliJ IDEA
2. Click **File → Open** (or **Open Project**)
3. Navigate to the `banking-app` directory and select it
4. Click **Open**

### Step 2: Add PostgreSQL JDBC Driver
1. Go to **File → Project Structure → Libraries**
2. Click the **+** icon to add a new library
3. Choose **From Maven...**
4. Search for `org.postgresql:postgresql` (version 42.2.23 or latest)
5. Select it and click **Add to Project**
6. Click **OK**

Alternative: If you have a `lib` folder with a JAR file:
1. Go to **File → Project Structure → Libraries**
2. Click **+** and select **Java**
3. Navigate to your JAR file in the `lib` folder
4. Click **OK**

### Step 3: Configure Project Settings
1. Go to **File → Project Structure → Project**
2. Ensure **Project SDK** is set to a valid JDK (8 or higher)
3. Click **OK**

### Step 4: Verify Database Connection
1. Before running, verify that PostgreSQL is running on your system
2. The application will attempt to connect using credentials: `myuser` / `mypassword`

## Running the Application

### Option 1: Run from IntelliJ
1. Navigate to the main entry point: `ui/MainLauncher.java` or `ui/Main.java`
2. Right-click on the file and select **Run '[ClassName].main()'**
3. The application will start with the login screen

### Option 2: Build and Run from Terminal
```bash
# Navigate to the project directory
cd banking-app

# Compile the project
javac -d out -cp "lib/*" ui/*.java service/impl/*.java service/*.java dao/impl/*.java dao/*.java model/*.java util/*.java

# Run the application
java -cp "out:lib/*" ui.MainLauncher
```

### Troubleshooting

**Issue: Connection refused**
- Verify PostgreSQL is running
- Check the database credentials in `util/DBConnection.java`
- Ensure the database `bankdb` exists

**Issue: Driver not found**
- Ensure the PostgreSQL JDBC driver is added to the project classpath
- Rebuild the project: **Build → Rebuild Project**

**Issue: Login fails**
- Verify the database tables were created successfully
- Check that you're using the correct credentials

## Features
- User registration and authentication
- Account management
- Cash in/out operations
- Money transfers between accounts
- Transaction history viewing
- PIN change functionality

## Project Structure
```
banking-app/
├── dao/              # Data Access Objects
├── model/            # Data models (User, Account, Transaction)
├── service/          # Business logic services
├── ui/               # GUI components and forms
├── util/             # Utility classes (Database connection)
└── lib/              # External libraries
```

## Notes
- The application uses Java Swing for the GUI
- All financial data is persisted in PostgreSQL
- Ensure the PIN is exactly 4 digits
- Phone numbers should be in a valid format