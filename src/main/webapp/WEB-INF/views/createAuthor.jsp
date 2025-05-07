<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>

    <head>
        <title>Add New Author</title>
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
        <h1>Add New Author</h1>
        <form action="/author" method="post">
            <div class="form-group">
                <label>Name:</label>
                <input type="text" name="name" required>
            </div>
            <div class="form-group">
                <label>Nationality:</label>
                <input type="text" name="nationality" required>
            </div>
            <button type="submit" class="btn">Save</button>
        </form>
        <a href="/" class="btn">Back</a>
    </body>

    </html>