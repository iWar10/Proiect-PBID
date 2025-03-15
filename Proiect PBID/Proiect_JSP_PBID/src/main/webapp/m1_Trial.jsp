<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, db.JavaBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify Trial</title>
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

    <jsp:useBean id="jb" scope="session" class="db.JavaBean" />
    <jsp:setProperty name="jb" property="*" />

    <div class="container">
        <div class="card">
            <h1>Modify Trial</h1>
            <%
                String primaryKey = request.getParameter("primarykey");
                if (primaryKey == null || primaryKey.trim().isEmpty()) {
            %>
            <div class="alert alert-danger text-center" role="alert">
                <strong>Error:</strong> No trials selected. Please select at least one trial to modify.
            </div>
            <div class="text-center mt-3">
                <a href="Modify_Trial.jsp" class="btn btn-custom">Go Back</a>
            </div>
            <%
                } else {
                    try {
                        jb.connect();
                        int aux = Integer.parseInt(primaryKey);
                        ResultSet rs = jb.intoarceLinieDupaId("trials", "idTrial", aux);
                        rs.first();
                        String caseName = rs.getString("caseName");
                        String caseType = rs.getString("caseType");
                        Date trialDate = rs.getDate("trialDate");
                        rs.close();
                        jb.disconnect();
            %>
            <form action="m2_Trial.jsp" method="post">
                <div class="mb-3">
                    <label for="idTrial" class="form-label">Trial ID</label>
                    <input type="text" class="form-control" id="idTrial" name="idTrial" value="<%= aux %>" readonly>
                </div>
                <div class="mb-3">
                    <label for="caseName" class="form-label">Case Name</label>
                    <input type="text" class="form-control" id="caseName" name="caseName" value="<%= caseName %>" required>
                </div>
                <div class="mb-3">
                    <label for="caseType" class="form-label">Case Type</label>
                    <input type="text" class="form-control" id="caseType" name="caseType" value="<%= caseType %>" required>
                </div>
                <div class="mb-3">
                    <label for="trialDate" class="form-label">Trial Date</label>
                    <input type="date" class="form-control" id="trialDate" name="trialDate" value="<%= trialDate %>" required>
                </div>
                <div class="text-center mt-3">
                    <button type="submit" class="btn btn-custom">Modify the Trial</button>
                </div>
            </form>
            <div class="text-center mt-3">
                <a href="index.html" class="btn btn-custom">Home</a>
            </div>
            <%
                    } catch (Exception e) {
            %>
            <div class="alert alert-danger text-center" role="alert">
                <strong>Error:</strong> An error occurred while retrieving trial data. <%= e.getMessage() %>
            </div>
            <div class="text-center mt-3">
                <a href="Modify_Trial.jsp" class="btn btn-custom">Go Back</a>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
