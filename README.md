# SQLite Database Viewer

This project provides a basic viewer for SQLite databases. The viewer allows you to interact with SQLite databases, view table data, execute queries, and more. It is written in Java and utilizes the JDBC (Java Database Connectivity) API to establish connections with SQLite databases.

## Features

- Open and view SQLite databases.
- Select tables from the database and view their contents.
- Execute custom SQL queries.
- Display query results in a table format.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- SQLite JDBC Driver

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/JanInquisitor/SQLiteViewer.git
   ```

2. Navigate to the project directory:

   ```bash
   cd SQLiteViewer
   ```

3. Compile the Java code:

   ```bash
   javac -cp ".:path/to/sqlite-jdbc-driver.jar" org/example/viewer/*.java
   ```

   Replace `path/to/sqlite-jdbc-driver.jar` with the actual path to the SQLite JDBC driver JAR file.

### Usage

1. Run the application:

   ```bash
   java -cp ".:path/to/sqlite-jdbc-driver.jar" org.example.viewer.ApplicationRunner
   ```

   Replace `path/to/sqlite-jdbc-driver.jar` with the actual path to the SQLite JDBC driver JAR file.

2. The application GUI will open. Enter the name of the SQLite database file (e.g., `mydatabase.db`) in the text field and click the "Open" button.

3. After opening the database, you can select tables from the dropdown list, execute queries in the text area, and view the results.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

---

*Note: Replace `path/to/sqlite-jdbc-driver.jar` with the actual path to the SQLite JDBC driver JAR file.*