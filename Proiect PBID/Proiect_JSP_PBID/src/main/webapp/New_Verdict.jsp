<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, db.JavaBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Verdict</title>
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
            <h1>Add a New Verdict</h1>
            <%
                String message = null;
                boolean isSuccess = false;

                try {
                    String idJudgeStr = request.getParameter("idJudge");
                    String idTrialStr = request.getParameter("idTrial");
                    String verdictDate = request.getParameter("VerdictDate");
                    String decision = request.getParameter("Decision");
                    String reasoning = request.getParameter("Reasoning");

                    if (idJudgeStr != null && idTrialStr != null && verdictDate != null && decision != null && reasoning != null) {
                        int idJudge = Integer.parseInt(idJudgeStr);
                        int idTrial = Integer.parseInt(idTrialStr);

                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                        sdf.setLenient(false);
                        sdf.parse(verdictDate);

                        JavaBean jb = new JavaBean();
                        jb.connect();
                        jb.addVerdict(idJudge, idTrial, verdictDate, decision, reasoning);
                        jb.disconnect();

                        isSuccess = true;
                        message = "Verdict added successfully!";
                    }
                } catch (Exception e) {
                    message = "Error: " + e.getMessage();
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
            <form action="New_Verdict.jsp" method="post">
                <div class="mb-3">
                    <label for="idJudge" class="form-label">Judge</label>
                    <select class="form-control" id="idJudge" name="idJudge" required>
                        <%
                            JavaBean jb = new JavaBean();
                            jb.connect();
                            ResultSet rs1 = jb.viewTable("judges");
                            while (rs1.next()) {
                                int idJudge = rs1.getInt("idJudge");
                                String lastname = rs1.getString("lastname");
                                String firstname = rs1.getString("firstname");
                        %>
                        <option value="<%= idJudge %>"><%= lastname %>, <%= firstname %></option>
                        <%
                            }
                            rs1.close();
                        %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="idTrial" class="form-label">Trial</label>
                    <select class="form-control" id="idTrial" name="idTrial" required>
                        <%
                            ResultSet rs2 = jb.viewTable("trials");
                            while (rs2.next()) {
                                int idTrial = rs2.getInt("idTrial");
                                String caseName = rs2.getString("caseName");
                        %>
                        <option value="<%= idTrial %>"><%= caseName %></option>
                        <%
                            }
                            rs2.close();
                            jb.disconnect();
                        %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="VerdictDate" class="form-label">Verdict Date</label>
                    <input type="date" class="form-control" id="VerdictDate" name="VerdictDate" required>
                </div>
                <div class="mb-3">
                    <label for="Decision" class="form-label">Decision</label>
                    <input type="text" class="form-control" id="Decision" name="Decision" placeholder="Enter decision" required>
                </div>
                <div class="mb-3">
                    <label for="Reasoning" class="form-label">Reasoning</label>
                    <textarea class="form-control" id="Reasoning" name="Reasoning" rows="3" placeholder="Enter reasoning" required></textarea>
                </div>
                <div class="text-center mt-3">
                    <button type="submit" class="btn btn-custom">Add Verdict</button>
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
