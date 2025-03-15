<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, db.JavaBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify Verdict</title>
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
            max-width: 800px;
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
            <h1>Modify Verdict</h1>
            <% String primaryKey = request.getParameter("primarykey");
                if (primaryKey == null || primaryKey.trim().isEmpty()) { %>
            <div class="alert alert-danger text-center" role="alert">
                <strong>Error:</strong> No verdicts selected. Please select at least one verdict to modify.
            </div>
            <div class="text-center mt-3">
                <a href="Modify_Verdict.jsp" class="btn btn-custom">Go Back</a>
            </div>
            <% } else {
                JavaBean jb = new JavaBean();
                jb.connect();
                int aux = Integer.parseInt(primaryKey);
                ResultSet rs = jb.intoarceLinieDupaId("verdicts", "idVerdict", aux);
                rs.first();
                int idJudge = rs.getInt("idJudge");
                int idTrial = rs.getInt("idTrial");
                String verdictDate = rs.getString("verdictDate");
                String decision = rs.getString("decision");
                String reasoning = rs.getString("reasoning");
                ResultSet rs1 = jb.viewTable("judges");
                ResultSet rs2 = jb.viewTable("trials"); %>
            <form action="m2_Verdict.jsp" method="post">
                <div class="mb-3">
                    <label for="idVerdict" class="form-label">Verdict ID</label>
                    <input type="text" class="form-control" id="idVerdict" name="idVerdict" value="<%= aux %>" readonly>
                </div>
                <div class="mb-3">
                    <label for="idJudge" class="form-label">Judge</label>
                    <select class="form-select" id="idJudge" name="idJudge">
                        <% while (rs1.next()) {
                            int idJudgeTemp = rs1.getInt("idJudge");
                            String lastname = rs1.getString("lastname");
                            String firstname = rs1.getString("firstname"); %>
                        <option value="<%= idJudgeTemp %>" <%= idJudge == idJudgeTemp ? "selected" : "" %>>
                            <%= idJudgeTemp %> - <%= lastname %>, <%= firstname %>
                        </option>
                        <% } rs1.close(); %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="idTrial" class="form-label">Trial</label>
                    <select class="form-select" id="idTrial" name="idTrial">
                        <% while (rs2.next()) {
                            int idTrialTemp = rs2.getInt("idTrial");
                            String caseName = rs2.getString("caseName"); %>
                        <option value="<%= idTrialTemp %>" <%= idTrial == idTrialTemp ? "selected" : "" %>>
                            <%= idTrialTemp %> - <%= caseName %>
                        </option>
                        <% } rs2.close(); jb.disconnect(); %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="verdictDate" class="form-label">Verdict Date</label>
                    <input type="date" class="form-control" id="verdictDate" name="verdictDate" value="<%= verdictDate %>" required>
                </div>
                <div class="mb-3">
                    <label for="decision" class="form-label">Decision</label>
                    <input type="text" class="form-control" id="decision" name="decision" value="<%= decision %>" required>
                </div>
                <div class="mb-3">
                    <label for="reasoning" class="form-label">Reasoning</label>
                    <textarea class="form-control" id="reasoning" name="reasoning" required><%= reasoning %></textarea>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-custom">Modify the Verdict</button>
                </div>
            </form>
            <% } %>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
