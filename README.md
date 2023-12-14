# LibrarySystem

Most important Rest API URLs:
http://localhost:8080/api/books/excelExport  =====> for download the excel report
http://localhost:8080/api/books/{/bookId}/borrow/{/userId}  =====> To call the book borrowing service
http://localhost:8080/api/books/{/bookId}/return  ======> To call the book returning service
http://localhost:8080/api/books/getBooksByName?name=...  =====> Search books using book name 
http://localhost:8080/api/books/getBooksByAuthor?name=...  =====> Search books using author name
http://localhost:8080/api/members/getMembersByFirstName?name=... ====> Search members using member first name
http://localhost:8080/api/members/getMembersByRegisterDate?date=  =====> Search members using member register date
...
