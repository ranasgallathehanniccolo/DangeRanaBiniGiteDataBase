/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dangeranabinigitedatabase;

import java.sql.*;

/**
 *
 * @author ranasgalla.niccolo
 */
public class DangeRanaBiniGiteDataBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rg = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:gite.db");
            stmt = conn.createStatement();
            

            String sqlCreaClassi = "CREATE TABLE IF NOT EXISTS Classi ("
                    + "  CLA_ID        integer primary key autoincrement, "
                    + "  CLA_Anno      integer, "
                    + "  CLA_Sezione   varchar(10), "
                    + "  CLA_Indirizzo varchar(50)"
                    + ");";

            stmt.execute(sqlCreaClassi);

            String sqlCreaGite = "CREATE TABLE IF NOT EXISTS Gite ("
                    + "  GIT_ID           integer primary key autoincrement, "
                    + "  GIT_Destinazione varchar(50), "
                    + "  GIT_Durata       integer, "
                    + "  GIT_Prezzo       double"
                    + ");";

            stmt.execute(sqlCreaGite);

            String sqlCreaAlunni = "CREATE TABLE IF NOT EXISTS Alunni ("
                    + "  ALU_Matricola integer primary key autoincrement, "
                    + "  ALU_Nome      varchar(50), "
                    + "  ALU_Cognome   varchar(50), "
                    + "  ALU_CLA_ID    integer references CLA_ID(Classi)"
                    + ");";

            stmt.execute(sqlCreaAlunni);

            String sqlCreaPartecipazione = "CREATE TABLE IF NOT EXISTS Partecipazione ("
                    + "  PAR_ID     integer primary key autoincrement, "
                    + "  PAR_ALU_ID integer references ALU_Matricola(Alunni), "
                    + "  PAR_GIT_ID integer references GIT_ID(Gite)"
                    + ");";

            stmt.execute(sqlCreaPartecipazione);

            rs = stmt.executeQuery("SELECT * FROM Classi;");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("CLA_ID") + " | "
                        + rs.getInt("CLA_Anno") + " | "
                        + rs.getString("CLA_Sezione") + " | "
                        + rs.getString("CLA_Indirizzo")
                );
            }
            
            rg = stmt.executeQuery("SELECT * FROM Gite;");
            while (rg.next()) {
                System.out.println(
                        rg.getInt("GIT_ID") + " | "
                        + rg.getString("GIT_Destinazione") + " | "
                        + rg.getInt("GIT_Durata") + " | "
                        + rg.getInt("GIT_Prezzo")
                );
            }
            
            final Connection connection = conn;
            java.awt.EventQueue.invokeLater(() -> new ClassiFrame(connection).setVisible(true));
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
