### Example project presenting problem with Spring R2DBC and camel case columns in Postgresql

#### Issue:
https://github.com/spring-projects/spring-data-r2dbc/issues/483

1. Tests in this project assumes that you have running Postgresql on your local machine with default settings. Username: Postgres, Password: Postgres, Host: 5432
2. Test method shouldThrowBadGrammarExceptionOnSave() in PersonRepositoryTestWithoutQuote class show that without setting forceQuote on R2dbcMappingContext
to true wrong insert SQL will be created and columns will not be found in Postgresql.
3. Test method shouldReturnPersonFromDB() in PersonRepositoryTestWithQuote class shows that Person taken from DB is returned but has all property set as null,
so test fails. Explanation for this fact and solution for it is written in linked issue.
4. Test method shouldSaveNewPersonInDbWithoutError() in PersonRepositoryTestWithQuote class shows that when forceQuote on R2dbcMappingContext is set to true, 
then person is properly saved in Postgresql.
5. As test from point 4 fails and DB is cleaned after each test maven clean install will fail too. 
Please use maven clean compile and run tests manually and one at a time.