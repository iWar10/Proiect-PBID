package db;

import java.sql.*;

public class JavaBean {

	String error;
	Connection con;

	public JavaBean() {
	}

	public void connect() throws ClassNotFoundException, SQLException, Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lawyers?useSSL=false", "root", "02septembrie");
		} catch (ClassNotFoundException cnfe) {
			error = "ClassNotFoundException: Database driver not found.";
			throw new ClassNotFoundException(error);
		} catch (SQLException sqle) {
			error = "SQLException: Cannot connect to the database.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "Exception: Unexpected error while establishing a database connection.";
			throw new Exception(error);
		}
	} // connect()

	public void connect(String bd) throws ClassNotFoundException, SQLException, Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bd, "root", "02septembrie");
		} catch (ClassNotFoundException cnfe) {
			error = "ClassNotFoundException: Database driver not found.";
			throw new ClassNotFoundException(error);
		} catch (SQLException sqle) {
			error = "SQLException: Cannot connect to the database.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "Exception: Unexpected error while establishing a database connection.";
			throw new Exception(error);
		}
	} // connect(String bd)

	public void connect(String bd, String ip) throws ClassNotFoundException, SQLException, Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + bd, "root", "02septembrie");
		} catch (ClassNotFoundException cnfe) {
			error = "ClassNotFoundException: Database driver not found.";
			throw new ClassNotFoundException(error);
		} catch (SQLException sqle) {
			error = "SQLException: Cannot connect to the database.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "Exception: Unexpected error while establishing a database connection.";
			throw new Exception(error);
		}
	} // connect(String bd, String ip)

	public void disconnect() throws SQLException {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException sqle) {
			error = ("SQLException: Cannot close the database connection.");
			throw new SQLException(error);
		}
	} // disconnect()

	public void addJudge(String lastName, String firstName, String court)
			throws SQLException, Exception {
		if (con != null) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO judges(lastname, firstname, court) VALUES('" + lastName + "', '" + firstName + "', '" + court + "');");

			} catch (SQLException sqle) {
				error = "SQLException: Failed to update; duplicate might exist.";
				throw new SQLException(error);
			}
		} else {
			error = "Exception: Database connection was lost.";
			throw new Exception(error);
		}
	} // end of addJudge()

	public void addTrial(String caseName, String caseType, String trialDate)
			throws SQLException, Exception {
		if (con != null) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO trials(casename, casetype, trialdate) VALUES('" + caseName + "', '" + caseType + "', '" + trialDate + "');");

			} catch (SQLException sqle) {
				error = "SQLException: Failed to update; duplicate might exist.";
				throw new SQLException(error);
			}
		} else {
			error = "Exception: Database connection was lost.";
			throw new Exception(error);
		}
	} // end of addTrial()

	public void addVerdict(int idJudge, int idTrial, String verdictDate, String decision, String reasoning)
			throws SQLException, Exception {
		if (con != null) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO verdicts(idjudge, idtrial, verdictdate, decision, reasoning) VALUES(" + idJudge + ", " + idTrial + ", '" + verdictDate + "', '" + decision + "', '" + reasoning + "');");

			} catch (SQLException sqle) {
				error = "SQLException: Failed to update; duplicate might exist.";
				throw new SQLException(error);
			}
		} else {
			error = "Exception: Database connection was lost.";
			throw new Exception(error);
		}
	} // end of addVerdict()

	public ResultSet viewTable(String table) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			// Ensure table name is safe and valid
			if (!table.matches("^[a-zA-Z0-9_]+$")) {
				throw new SQLException("Invalid table name.");
			}
			String queryString = "SELECT * FROM " + table + ";";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(queryString);
		} catch (SQLException sqle) {
			error = "SQLException: Query failed. Check if the table name is correct.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "Exception: An error occurred while retrieving data.";
			throw new Exception(error);
		}
		return rs;
	} // viewTable()

	public ResultSet viewVerdicts() throws SQLException, Exception {
	    ResultSet rs = null;
	    try {
	        String queryString = "SELECT j.idJudge, j.lastname AS JudgeLastName, j.firstname AS JudgeFirstName, j.court AS Court, " +
	                             "t.idTrial, t.casename AS CaseName, t.casetype AS CaseType, t.trialdate AS TrialDate, " +
	                             "v.idVerdict, v.verdictdate AS VerdictDate, v.decision AS Decision, v.reasoning AS Reasoning " +
	                             "FROM verdicts v " +
	                             "JOIN judges j ON v.idjudge = j.idjudge " +
	                             "JOIN trials t ON v.idtrial = t.idtrial";
	        Statement stmt = con.createStatement();
	        rs = stmt.executeQuery(queryString);
	    } catch (SQLException sqle) {
	        error = "SQLException: Query failed.";
	        throw new SQLException(error);
	    } catch (Exception e) {
	        error = "Exception: An error occurred while retrieving data.";
	        throw new Exception(error);
	    }
	    return rs;
	}
 // viewVerdicts()

	public void deleteTableData(String[] primaryKeys, String table, String idColumn) throws SQLException, Exception {
		if (con != null) {
			try {
				long aux;
				PreparedStatement delete;
				delete = con.prepareStatement("DELETE FROM " + table + " WHERE " + idColumn + "=?;");
				for (int i = 0; i < primaryKeys.length; i++) {
					aux = java.lang.Long.parseLong(primaryKeys[i]);
					delete.setLong(1, aux);
					delete.execute();
				}
			} catch (SQLException sqle) {
				error = "SQLException: Failed to update; duplicate might exist.";
				throw new SQLException(error);
			} catch (Exception e) {
				error = "Exception: An error occurred while deleting records.";
				throw new Exception(error);
			}
		} else {
			error = "Exception: Database connection was lost.";
			throw new Exception(error);
		}
	} // end of deleteTableData()

	public void stergeTabela(String tabela) throws SQLException, Exception {
		if (con != null) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				stmt.executeUpdate("delete from " + tabela + ";");
			} catch (SQLException sqle) {
				error = "SQLException: Deletion failed; duplicates might exist.";
				throw new SQLException(error);
			}
		} else {
			error = "Exception: Database connection was lost.";
			throw new Exception(error);
		}
	} // end of stergeTabela()

	public void modificaTabela(String tabela, String IDTabela, int ID, String[] campuri, String[] valori) throws SQLException, Exception {
		String update = "update " + tabela + " set ";
		String temp = "";
		if (con != null) {
			try {
				for (int i = 0; i < campuri.length; i++) {
					if (i != (campuri.length - 1)) {
						temp = temp + campuri[i] + "='" + valori[i] + "', ";
					} else {
						temp = temp + campuri[i] + "='" + valori[i] + "' where " + IDTabela + " = '" + ID + "';";
					}
				}
				update = update + temp;
				// create a prepared SQL statement
				Statement stmt;
				stmt = con.createStatement();
				stmt.executeUpdate(update);
			} catch (SQLException sqle) {
				error = "SQLException: Update failed; duplicates might exist.";
				throw new SQLException(error);
			}
		} else {
			error = "Exception: Database connection was lost.";
			throw new Exception(error);
		}
	} // end of modificaTabela()

	public ResultSet intoarceLinie(String tabela, int ID) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String queryString = ("SELECT * FROM " + tabela + " where idJudge=" + ID + ";");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(queryString);
		} catch (SQLException sqle) {
			error = "SQLException: Query failed.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "Exception: An error occurred while retrieving data.";
			throw new Exception(error);
		}
		return rs;
	} // end of intoarceLinie()

	public ResultSet intoarceLinieDupaId(String tabela, String denumireId, int ID) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String queryString = ("SELECT * FROM " + tabela + " where " + denumireId + "=" + ID + ";");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(queryString);
		} catch (SQLException sqle) {
			error = "SQLException: Query failed.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "Exception: An error occurred while retrieving data.";
			throw new Exception(error);
		}
		return rs;
	} // end of intoarceLinieDupaId()

	public ResultSet intoarceVerdictId(int ID) throws SQLException, Exception {
	    ResultSet rs = null;
	    try {
	        String queryString = "SELECT j.lastname AS JudgeLastName, j.firstname AS JudgeFirstName, j.court AS Court, " +
	                             "t.casename AS CaseName, t.casetype AS CaseType, t.trialdate AS TrialDate, " +
	                             "v.idVerdict, v.verdictdate AS VerdictDate, v.decision AS Decision, v.reasoning AS Reasoning " +
	                             "FROM verdicts v " +
	                             "JOIN judges j ON v.idjudge = j.idjudge " +
	                             "JOIN trials t ON v.idtrial = t.idtrial " +
	                             "WHERE v.idVerdict = " + ID + ";";
	        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        rs = stmt.executeQuery(queryString);
	    } catch (SQLException sqle) {
	        error = "SQLException: Query failed.";
	        throw new SQLException(error);
	    } catch (Exception e) {
	        error = "Exception: An error occurred while retrieving data.";
	        throw new Exception(error);
	    }
	    return rs;
	}
 // end of intoarceVerdictId()
}
