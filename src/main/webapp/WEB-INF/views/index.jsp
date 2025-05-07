<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>Library Management</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }

                th,
                td {
                    border: 1px solid #ddd;
                    padding: 8px;
                    text-align: left;
                }

                th {
                    background-color: #f2f2f2;
                }

                .btn {
                    padding: 5px 10px;
                    margin: 5px;
                    text-decoration: none;
                    color: white;
                    background-color: #007bff;
                }

                .btn:hover {
                    background-color: #0056b3;
                }

                .search-form {
                    margin-bottom: 20px;
                }
            </style>
        </head>

        <body>
            <h1>Library Management System</h1>
            <form class="search-form" action="/" method="get">
                <input type="text" name="search" placeholder="Search by title, ISBN, or author">
                <button type="submit" class="btn">Search</button>
            </form>
            <a href="/author/new" class="btn">Add New Author</a>
            <a href="/book/new" class="btn">Add New Book</a>
            <table>
                <tr>
                    <th>Title</th>
                    <th>ISBN</th>
                    <th>Author</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.isbn}</td>
                        <td>${book.author.name}</td>
                        <td><a href="/book/edit/${book.id}" class="btn">Edit</a></td>
                    </tr>
                </c:forEach>
            </table>
        </body>

        </html>