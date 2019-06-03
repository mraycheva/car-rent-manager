# Overview

A Java desktop application operating with relational database ([H2, v. 1.4.197](http://www.h2database.com/html/download.html)) of 3 related tables.

It offers the user the ability to:
  * perform CRUD operations with the 3 tables;
  * search given database table's entries by one or more of the table's attributes (excluding id);
  * filter rents by two additional characteristics (one is from the person table and other is from the car table).

The application allows only such changes that do not violate the database's consistency.

After every change the application's data is being updated.

## Starting the application

A database for exemplary purposes is located in the database folder of the repository, so in order for the application to run, it is only necessary that the H2 client is downloaded from the link above and started.

If preferred, the database can be deleted and a new one can be created in its place by executing db_creation_script from the scripts folder.
