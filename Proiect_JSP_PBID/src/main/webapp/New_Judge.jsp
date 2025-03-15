<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, db.JavaBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Judge</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #d8c3a5;
            font-family: 'Verdana', sans-serif;
            color: #3e2723;
        }
        .navbar {
            background-color: #6d4c41;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }
        .navbar-brand {
            font-size: 1.6rem;
            font-weight: bold;
            color: #ffffff !important;
        }
        .container {
            margin-top: 30px;
        }
        .card {
            background: #f5f5f5;
            border: 2px solid #6d4c41;
            border-radius: 8px;
            box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
            padding: 25px;
            margin: 30px auto;
            max-width: 600px;
            position: relative;
        }
        h1 {
            font-weight: bold;
            color: #6d4c41;
            text-align: center;
        }
        .btn-custom {
            background-color: #6d4c41;
            border-color: #6d4c41;
            color: #ffffff;
            transition: background-color 0.3s ease;
            font-size: 1.2rem;
            width: 100%;
        }
        .btn-custom:hover {
            background-color: #4e342e;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-md">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Judicial Management System</a>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <h1>Add a New Judge</h1>
            <%
                String lastname = request.getParameter("Lastname");
                String firstname = request.getParameter("Firstname");
                String court = request.getParameter("Court");
                String message = null;
                boolean isSuccess = false;

                if (lastname != null && firstname != null && court != null) {
                    try {
                        JavaBean jb = new JavaBean();
                        jb.connect();
                        jb.addJudge(lastname, firstname, court);
                        jb.disconnect();
                        message = "Judge added successfully!";
                        isSuccess = true;
                    } catch (Exception e) {
                        message = "Error: " + e.getMessage();
                    }
                }
            %>
            <%
                if (message != null) {
            %>
            <div class="alert alert-<%= isSuccess ? "success" : "danger" %> text-center" role="alert">
                <%= message %>
            </div>
            <%
                }
            %>
            <form action="New_Judge.jsp" method="post">
                <div class="mb-3">
                    <label for="Lastname" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="Lastname" name="Lastname" placeholder="Enter last name" required>
                </div>
                <div class="mb-3">
                    <label for="Firstname" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="Firstname" name="Firstname" placeholder="Enter first name" required>
                </div>
                <div class="mb-3">
                    <label for="Court" class="form-label">Court</label>
                    <input type="text" class="form-control" id="Court" name="Court" placeholder="Enter court" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-custom">Add Judge</button>
                </div>
            </form>
            <div class="text-center mt-3">
                <a href="index.html" class="btn btn-custom">Home</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
