
# Crypto Exchange System

## Overview

The Crypto Exchange System is a Java-based application designed as a final project to simulate the functionalities of a cryptocurrency exchange platform. Users can create accounts, manage digital wallets, deposit fiat money, and trade cryptocurrencies such as Bitcoin (BTC) and Ethereum (ETH). The system ensures secure and efficient trading by matching buy and sell orders.

## Features

- **User Registration**: Users can register by providing their name, email, and password, and will receive a unique user ID.
- **User Authentication**: Secure login and logout functionality.
- **Digital Wallet**: Each user has a wallet that tracks both fiat money (USD) and cryptocurrency holdings.
- **Cryptocurrency Trading**: Users can buy or sell cryptocurrencies by placing orders in the system, which matches and executes trades.
- **Transaction History**: Users can view a detailed history of all their transactions.
- **Market Price Fluctuations**: The system simulates real market conditions by fluctuating cryptocurrency prices at random intervals.

## Use Cases

1. **User Registration**: Register with name, email, and password.
2. **User Login**: Login with email and password.
3. **Deposit Money**: Deposit fiat money into your wallet.
4. **View Wallet Balance**: Check your current fiat and cryptocurrency balances.
5. **Buy Cryptocurrencies**: Buy cryptocurrencies directly from the exchange or by placing buy orders.
6. **Sell Cryptocurrencies**: Sell cryptocurrencies by placing sell orders.
7. **View Transaction History**: Review all past trades.

## Technical Details

- **Programming Language**: Java
- **JDK Version**: JDK 17
- **Project Management**: Maven
- **Design Principles**:
  - Encapsulation
  - Inheritance
  - Polymorphism
  - Abstraction
  - Clean Code
  - Separation of Presentation Logic and Business Logic
- **Storage**: Files or in-memory database for data storage
- **Bonus Feature**: Random market price fluctuations to simulate real-world conditions.

## UML Class Diagram

A UML class diagram is provided in the repository to illustrate the system's design and relationships between classes.

## Installation

1. Clone the repository:
   \`\`\`bash
   git clone https://github.com/clerodri/crypto-exchange-system.git
   \`\`\`
2. Navigate to the project directory:
   \`\`\`bash
   cd crypto-exchange-system
   \`\`\`
3. Build the project using Maven:
   \`\`\`bash
   mvn clean install
   \`\`\`

## Running the Application

1. Ensure you have JDK 17 installed.
2. Run the application:
   \`\`\`bash
   java -jar target/crypto-exchange-system-1.0.jar
   \`\`\`

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.



## Submission Details

- **Due Date**: Monday, September 2, 11:59 PM
- **Submission Link**: [Project Submission Form](https://forms.office.com/r/rtTeFizVsn)
- **Repository Requirements**:
  - Use of \`.gitignore\`
  - Multiple commits
  - Public GitHub repository
