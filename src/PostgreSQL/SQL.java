package PostgreSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

public class SQL {
	
	private Connection conn;
	private PreparedStatement cadena;
	private String database;
	private String id;
	private String password;
	
	public SQL(String database, String id, String password){
		// inicializa los valores de la cadena de conexion.
		this.database=database;
		this.id=id;
		this.password=password;
		conn=null;
		cadena=null;
	}
	
	public void abrirConexion(String sql) throws ClassNotFoundException, SQLException {
		// permite validar la conexion y abrirla
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database,id,password);
		cadena = conn.prepareStatement(sql);
	}
	
	public void cerrarConexion() throws SQLException {
		// cierra la conexion
		if(cadena!=null) cadena.close();
		if(conn!=null) conn.close();
	}
	
	public boolean ejecutarSQL(String sql, String valores, String columnas) throws ClassNotFoundException, SQLException{
		if(!sql.isEmpty()){
				if(valores!=null && !valores.isEmpty()) {
					// si el SQL contiene restricciones
					String[] datos=valores.split(",");
					String[] tipos=columnas.split(",");
					int i=0;
					for(String valor: datos){
						if(valor.compareTo("null")==0){
							cadena=isNull(cadena, tipos[i], i+1);
						}else{
							cadena=isNotNull(cadena, tipos[i], i+1, valor);
						}
						i++;
					}
				}
				int result=cadena.executeUpdate();
				return (result>0);
		} return false;
	}
	
	public int ejecutarCount(String sql, String valores, String columnas) throws ClassNotFoundException, SQLException{
		int resultado=-1;
		if(!sql.isEmpty()){
				if(valores!=null && !valores.isEmpty()) { // si el SQL contiene restricciones
					String[] datos=valores.split(",");
					String[] tipos=columnas.split(",");
					int i=0;
					for(String valor: datos){
						if(valor.compareTo("null")==0){
							cadena=isNull(cadena, tipos[i],i+1);
						}else{
							cadena=isNotNull(cadena, tipos[i], i+1, valor);
						}
						i++;
					}
				}
				ResultSet resul=cadena.executeQuery();
				while(resul.next()){
					resultado=resul.getInt(1);
				}
		}
		return resultado;
	}
	
	public String ejecutarSelectEscalar(String sql, String valores, String columnas) throws ClassNotFoundException, SQLException{
		String resultado=null;
		if(!sql.isEmpty()){
			if(valores!=null && !valores.isEmpty()) { // si el SQL contiene restricciones
				String[] datos=valores.split(",");
				String[] tipos=columnas.split(",");
				int i=0;
				for(String valor: datos){
					if(valor.compareTo("null")==0){
						cadena=isNull(cadena, tipos[i],i+1);
					}else{
						cadena=isNotNull(cadena, tipos[i], i+1, valor);
					}
					i++;
				}
			}
			ResultSet resul=cadena.executeQuery();
			while(resul.next()){
				resultado=resul.getString(1);
			}
		}
		return resultado;
	}
	
	public ResultSet ejecutarSelect(String sql, String valores, String columnas) throws ClassNotFoundException, SQLException {
		ResultSet resultado=null;
		if(!sql.isEmpty()){
			if(valores!=null && !valores.isEmpty()) { // si el SQL contiene restricciones
				String[] datos=valores.split(",");
				String[] tipos=columnas.split(",");
				int i=0;
				for(String valor: datos){
					if(valor.compareTo("null")==0){
						cadena=isNull(cadena, tipos[i],i+1);
					}else{
						cadena=isNotNull(cadena, tipos[i], i+1, valor);
					}
					i++;
				}
			}
			resultado=cadena.executeQuery();
		}
		return resultado;
	}
	
	public PreparedStatement isNull(PreparedStatement stat, String tipo, int i) throws SQLException{
		if(tipo.compareTo("s")==0) {stat.setNull(i, Types.VARCHAR);} // si es tipo String
		if(tipo.compareTo("i")==0) {stat.setNull(i, Types.INTEGER);} // si es tipo int
		if(tipo.compareTo("f")==0) {stat.setNull(i, Types.NUMERIC);} // si es tipo double
		if(tipo.compareTo("d")==0) {stat.setNull(i, Types.DATE);} // si es tipo date
		return stat;
	}
	
	public PreparedStatement isNotNull(PreparedStatement stat, String tipo, int i, String valor) throws SQLException{
		if(tipo.compareTo("s")==0) {stat.setString(i, valor);} // si el valor de la columna i es tipo String
		if(tipo.compareTo("i")==0) {stat.setInt(i, Integer.parseInt(valor));} // si el valor de la columna i es tipo int
		if(tipo.compareTo("f")==0) {stat.setDouble(i, Double.parseDouble(valor));} // si el valor de la columna i es tipo double
		if(tipo.compareTo("d")==0) {
			// si el valor de la columna i es tipo date
			Calendar aux=Calendar.getInstance();
			String[] date=valor.split("-");
			// dia-mes-año
			aux.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0]));
			aux.set(Calendar.MONTH, (Integer.parseInt(date[1])-1));
			aux.set(Calendar.YEAR, Integer.parseInt(date[2]));
			stat.setDate(i, new Date(aux.getTime().getTime()));
		}
		return stat;
	}
}
