<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, db.JavaBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify Verdicts</title>
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
            margin-bottom: 20px;
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
        }
        .btn-custom:hover {
            background-color: #4e342e;
        }
        .table {
            margin-top: 20px;
            text-align: center;
        }
        .table th {
            background-color: #6d4c41;
            color: white;
        }
        .table tbody tr:hover {
            background-color: #f1f1f1;
        }
        .btn-group {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }
        .btn-full-width {
            width: 100%;
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
            <h1>Modify Verdicts</h1>
            <div class="btn-group">
                <a href="New_Verdict.jsp" class="btn btn-custom">Add a New Verdict</a>
                <a href="index.html" class="btn btn-custom">Home</a>
            </div>
            <form action="m1_Verdict.jsp" method="post">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Mark</th>
                            <th>Verdict ID</th>
                            <th>Judge ID</th>
                            <th>Judge Firstname</th>
                            <th>Judge Lastname</th>
                            <th>Trial ID</th>
                            <th>Trial Name</th>
                            <th>Trial Date</th>
                            <th>Trial Type</th>
                            <th>Verdict Date</th>
                            <th>Decision</th>
                            <th>Reasoning</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            JavaBean jb = new JavaBean();
                            jb.connect();
                            ResultSet rs = jb.viewVerdicts();
                            while (rs.next()) {
                        %>
                        <tr>
                            <td><input type="checkbox" name="primarykey" value="<%= rs.getInt("idVerdict") %>"></td>
                            <td><%= rs.getInt("idVerdict") %></td>
                            <td><%= rs.getInt("idJudge") %></td>
                            <td><%= rs.getString("JudgeFirstName") %></td>
                            <td><%= rs.getString("JudgeLastName") %></td>
                            <td><%= rs.getInt("idTrial") %></td>
                            <td><%= rs.getString("caseName") %></td>
                            <td><%= rs.getString("trialDate") %></td>
                            <td><%= rs.getString("caseType") %></td>
                            <td><%= rs.getString("verdictDate") %></td>
                            <td><%= rs.getString("decision") %></td>
                            <td><%= rs.getString("reasoning") %></td>
                        </tr>
                        <%
                            }
                            rs.close();
                            jb.disconnect();
                        %>
                    </tbody>
                </table>
                <div class="mt-3">
                    <button type="submit" class="btn btn-custom btn-full-width">Modify Marked Rows</button>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
