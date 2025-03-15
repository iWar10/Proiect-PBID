<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, db.JavaBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trial Modification</title>
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
            text-align: center;
        }
        h1 {
            font-weight: bold;
            color: #6d4c41;
            margin-bottom: 20px;
        }
        .alert {
            margin: 20px 0;
            padding: 15px;
            border-radius: 5px;
        }
        .alert-success {
            background-color: #d1e7dd;
            color: #0f5132;
            border: 1px solid #badbcc;
        }
        .alert-danger {
            background-color: #f8d7da;
            color: #842029;
            border: 1px solid #f5c2c7;
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
            <% 
                try {
                    JavaBean jb = new JavaBean();
                    jb.connect();
                    int aux = Integer.parseInt(request.getParameter("idTrial"));
                    String caseName = request.getParameter("caseName");
                    String caseType = request.getParameter("caseType");
                    String trialDate = request.getParameter("trialDate");

                    String[] values = {caseName, caseType, trialDate};
                    String[] fields = {"caseName", "caseType", "trialDate"};
                    jb.modificaTabela("trials", "idTrial", aux, fields, values);

                    jb.disconnect();
            %>
            <h1 class="text-center">Success</h1>
            <div class="alert alert-success">
                The modifications have been saved successfully!
            </div>
            <% 
                } catch (Exception e) { 
            %>
            <h1 class="text-center">Error</h1>
            <div class="alert alert-danger">
                <strong>Error:</strong> <%= e.getMessage() %>
            </div>
            <% } %>
            <div class="text-center mt-3">
                <a href="index.html" class="btn btn-custom">Go Back</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>