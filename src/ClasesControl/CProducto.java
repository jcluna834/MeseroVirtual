package ClasesControl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Clases.Producto;
import PostgreSQL.SQL;

public class CProducto {
	private SQL postgresql;
	
	public CProducto() {
		postgresql=new SQL("database","id", "password");
	}
	
	public List<Producto> obtenerProductos(String tipo) throws ClassNotFoundException, SQLException{
		String sql="SELECT COUNT(*) FROM PRODUCTOS WHERE TIPO=?";
		postgresql.abrirConexion(sql);
		int res=postgresql.ejecutarCount(sql,tipo,"s");
		postgresql.cerrarConexion();
		if(res>0){
			ResultSet result=null;
			sql="SELECT PRODUCTOID AS ID, PRECIO , INGREDIENTES , DESCRIPCION , NOMBREPRODUCTO AS NOMBRE, TIPO FROM PRODUCTOS WHERE TIPO=?";
			postgresql.abrirConexion(sql);
			result=postgresql.ejecutarSelect(sql,tipo,"s");
			List<Producto> productos=new ArrayList<Producto>();
			while(result.next()){
				productos.add(new Producto(result.getInt(1),result.getDouble(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6)));
			}
			postgresql.cerrarConexion();
			return productos;
		}else return null;
	}
	
	public boolean existeProducto(int prodid) throws ClassNotFoundException, SQLException{
		String sql="SELECT COUNT(*) FROM PRODUCTOS WHERE PRODUCTOID=?";
		postgresql.abrirConexion(sql);
		int result=postgresql.ejecutarCount(sql,String.valueOf(prodid),"i");
		postgresql.cerrarConexion();
		return (result>0);
	}
	
	public Producto obtenerProducto(int id) throws ClassNotFoundException, SQLException{
		if(existeProducto(id)){
			ResultSet result=null;
			String sql="SELECT PRODUCTOID AS ID, PRECIO , INGREDIENTES , DESCRIPCION , NOMBREPRODUCTO AS NOMBRE, TIPO FROM PRODUCTOS WHERE PRODUCTOID=?";
			postgresql.abrirConexion(sql);
			result=postgresql.ejecutarSelect(sql,String.valueOf(id),"i");
			List<Producto> productos=new ArrayList<Producto>();
			while(result.next()){
					productos.add(new Producto(result.getInt(1),result.getDouble(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6)));
			}
			postgresql.cerrarConexion();
			return productos.get(0);
		}else return null;
	}
}
