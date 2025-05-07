<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>Edit Book</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                }

                .form-group {
                    margin-bottom: 15px;
                }

                .btn {
                    padding: 5px 10px;
                    text-decoration: none;
                    color: white;
                    background-color: #007bff;
                }
            </style>
        </head>

        <body>
            <h1>Edit Book</h1>
            <form action="/book/${book.id}" method="post">
                <div class="form-group">
                    <label>Title:</label>
                    <input type="text" name="title" value="${book.title}" required>
                </div>
                <div class="form-group">
                    <label>ISBN:</label>
                    <input type="text" name="isbn" value="${book.isbn}" required>
                </div>
                <div class="form-group">
                    <label>Author:</label>
                    <select name="authorId" required>
                        <c:forEach items="${authors}" var="author">
                            <option value="${author.id}" ${author.id==book.author.id ? 'selected' : '' }>${author.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn">Update</button>
            </form>
            <a href="/" class="btn">Back</a>
        </body>

        </html>