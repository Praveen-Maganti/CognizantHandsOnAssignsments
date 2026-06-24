HANDS ON 3 – HIBERNATE ANNOTATION CONFIGURATION

Object Relational Mapping using Annotations
In annotation-based mapping, Hibernate uses annotations inside the entity class instead of XML mapping files. These annotations define how the class is mapped to the database table.

@Entity
- Marks a class as a Hibernate entity.

@Table
- Specifies the database table name.

@Id
- Defines the primary key column.

@GeneratedValue
- Automatically generates primary key values.

@Column
- Maps a class field to a database column.


Hibernate Configuration (hibernate.cfg.xml)

Dialect
- Specifies the SQL dialect used by the database (MySQL, Oracle, etc.).

Driver
- JDBC driver used to connect to the database.

Connection URL
- Specifies the database location and connection details.

Username
- Username used to access the database.

Password
- Password used to access the database.
