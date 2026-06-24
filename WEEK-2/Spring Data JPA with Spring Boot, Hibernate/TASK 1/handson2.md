HANDS ON 2 – HIBERNATE XML CONFIGURATION

Object Relational Mapping (ORM) in Hibernate XML
ORM maps a Java class to a database table using an XML mapping file. Each class attribute is mapped to a table column. Hibernate automatically converts Java objects into database records.

SessionFactory
- Creates Session objects.
- Created only once during application startup.
- Heavy-weight object.

Session
- Used to communicate with the database.
- Performs CRUD operations.

Transaction
- Represents a database transaction.
- Maintains data consistency.

beginTransaction()
- Starts a new transaction.

commit()
- Permanently saves changes to the database.

rollback()
- Cancels all changes if an error occurs.

session.save()
- Inserts a new record into the database.

session.createQuery().list()
- Retrieves multiple records from the database.

session.get()
- Fetches a single record using its primary key.

session.delete()
- Deletes a record from the database.
