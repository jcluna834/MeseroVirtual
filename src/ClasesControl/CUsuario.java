package ClasesControl;

import java.sql.SQLException;

import Clases.Usuario;
import PostgreSQL.SQL;

public class CUsuario{
	private SQL postgresql;
	
	public CUsuario() {
		postgresql=new SQL("database","postgres", "9011");
	}
	
	public Usuario existeUsuario(Usuario user) throws ClassNotFoundException, SQLException{
		String sql;
		if(user!=null && user.getUID()!=null && !user.getUID().isEmpty()){
			sql="SELECT COUNT(*) FROM USUARIO WHERE UID=? AND UPASSWORD=?";
			postgresql.abrirConexion(sql);
			int us = postgresql.ejecutarCount(sql,
					user.getUID()+","+user.getUPASSWORD(),
					"s,s");
			postgresql.cerrarConexion();
			if(us==1) {
				sql="SELECT UNOMBRE FROM USUARIO WHERE UID=? AND UPASSWORD=?";
				postgresql.abrirConexion(sql);
				user.setUNOMBRE(postgresql.ejecutarSelectEscalar(sql,
						user.getUID()+","+user.getUPASSWORD(),
						"s,s"));
				postgresql.cerrarConexion();
				sql="SELECT UPRIMERAPELLIDO FROM USUARIO WHERE UID=? AND UPASSWORD=?";
				postgresql.abrirConexion(sql);
				user.setUPRIMERAPELLIDO(postgresql.ejecutarSelectEscalar(sql,
						user.getUID()+","+user.getUPASSWORD(),
						"s,s"));
				postgresql.cerrarConexion();
				sql="SELECT USEGUNDOAPELLIDO FROM USUARIO WHERE UID=? AND UPASSWORD=?";
				postgresql.abrirConexion(sql);
				user.setUSEGUNDOAPELLIDO(postgresql.ejecutarSelectEscalar(sql,
						user.getUID()+","+user.getUPASSWORD(),
						"s,s"));
				postgresql.cerrarConexion();
				return user;
			}
			else return null;
		}else return null;
	}
	
	public String tipoUsuario(Usuario user) throws ClassNotFoundException, SQLException{
		String sql;
		int consulta;
		if(user!=null){
			sql="SELECT COUNT(*) FROM ADMINISTRADOR WHERE ADMINID=?";
			postgresql.abrirConexion(sql);
			consulta=postgresql.ejecutarCount(sql,user.getUID(),"s");
			postgresql.cerrarConexion();
			if(consulta==1){
				return "administrador";
			}
			sql="SELECT COUNT(*) FROM MESERO WHERE MESEROID=?";
			postgresql.abrirConexion(sql);
			consulta=postgresql.ejecutarCount(sql,user.getUID(),"s");
			postgresql.cerrarConexion();
			if(consulta==1){
				return "mesero";
			}
			sql="SELECT COUNT(*) FROM CAJERO WHERE CAJEROID=?";
			postgresql.abrirConexion(sql);
			consulta=postgresql.ejecutarCount(sql,user.getUID(),"s");
			if(consulta==1){
				return "cajero";
			}
			return null;
		}else return null;
	}
	/*
	public boolean insertUsuario(Usuario user){
		if(user!=null){
			postgresql.ejecutarSQL("INSERT");
			return true;
		}else return false;
	}
	
	public boolean updateUsuario(Usuario user){
		if(user!=null){
			postgresql.ejecutarSQL("UPDATE");
			return true;
		}else return false;
	}
	
	public boolean deleteUsuario(Usuario user){
		if(user!=null){
			postgresql.ejecutarSQL("DELETE");
			return true;
		}else return false;
	}*/
}
