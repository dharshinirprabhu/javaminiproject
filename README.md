# Satellite Management System (Java Console + MySQL)

A simple student-level Java console project using JDBC and MySQL. It supports:
- INSERT: Add a satellite
- SELECT: View all satellites and search by ID
- UPDATE: Change satellite status
- DELETE: Remove a satellite
- Persistent storage in MySQL

## Requirements
- JDK 17 or later
- MySQL Server (8.x recommended)
- Eclipse IDE with Maven support

## 1. Create the database
1. Start MySQL Server.
2. Open MySQL Workbench or the MySQL command line.
3. Run the complete script in `database/satellite_db.sql`.

This creates `satellite_db` and the `satellites` table, with two optional sample records.

## 2. Configure the database password
Open `src/main/java/com/student/satellite/DBConnection.java` and change:
`PASSWORD = "your_mysql_password"`
to your MySQL root password. If your MySQL username is not `root`, change `USER` too.

## 3. Import into Eclipse
1. Extract the ZIP file.
2. Open Eclipse.
3. Select **File → Import → Maven → Existing Maven Projects**.
4. Browse to the extracted `SatelliteManagementSystem` folder.
5. Select the project and click **Finish**.
6. Wait for Maven to download MySQL Connector/J.

## 4. Run
1. Open `src/main/java/com/student/satellite/Main.java`.
2. Right-click → **Run As → Java Application**.
3. Use the numbered console menu.

## Notes
- Enter dates in `YYYY-MM-DD` format, for example `2024-08-15`.
- The application uses `PreparedStatement` for database operations.
- If connection fails, confirm MySQL is running, the database script was executed, and the username/password are correct.
