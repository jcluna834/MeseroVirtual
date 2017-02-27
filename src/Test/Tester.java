package Test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import ClasesControl.*;
import Clases.*;
import Presentacion.*;
import PostgreSQL.*;

public class Tester {
	@Test
	public void pruebaLogeoSinUsuario() throws ClassNotFoundException, SQLException{
		CUsuario cus=new CUsuario();
		Usuario user=new Usuario();
		assertTrue(cus.existeUsuario(user)==null);
	}
	
	@Test
	public void pruebaLogeoConUsuario() throws ClassNotFoundException, SQLException{
		CUsuario cus=new CUsuario();
		Usuario user=new Usuario();
		user.setUID("1061733703");
		user.setUPASSWORD("1061733703");
		assertTrue(cus.existeUsuario(user)!=null);
	}
	
	@Test
	public void pruebaCountSinCondicion() throws ClassNotFoundException, SQLException{
		SQL sql=new SQL("database", "id", "password");
		String query="SELECT COUNT(*) FROM USUARIO";
		sql.abrirConexion(query);
		int result=sql.ejecutarCount(query, null, null);
		sql.cerrarConexion();
		assertTrue(result!=0);
	}
	
	@Test
	public void pruebaSelectEscalarSinCondicion() throws ClassNotFoundException, SQLException{
		SQL sql=new SQL("database", "id", "password");
		String query="SELECT 'Camilo'";
		sql.abrirConexion(query);
		String resultado=sql.ejecutarSelectEscalar(query, null, null);
		sql.cerrarConexion();
		assertTrue(resultado.compareTo("Camilo")==0);
	}
	
	@Test
	public void pruebaCountConCondicion() throws ClassNotFoundException, SQLException{
		SQL sql=new SQL("database", "id", "password");
		String query="SELECT COUNT(*) FROM USUARIO WHERE UID=? AND UPASSWORD=?";
		sql.abrirConexion(query);
		int resultado=sql.ejecutarCount(query, 
				"1061733703,1061733703", 
				"s,s");
		sql.cerrarConexion();
		assertTrue(resultado==1);
	}
	
	@Test
	public void pruebaSelectEscalarConCondicion() throws ClassNotFoundException, SQLException{
		SQL sql=new SQL("database", "id", "password");
		String query="SELECT UNOMBRE FROM USUARIO WHERE UID=? AND UPASSWORD=?";
		sql.abrirConexion(query);
		String resultado=sql.ejecutarSelectEscalar(query,
				"1061733703,1061733703",
				"s,s");
		sql.cerrarConexion();
		assertTrue(resultado.compareTo("Julio")==0);
	}
	
	@Test
	public void pruebaInsertUsuario() throws ClassNotFoundException, SQLException{
		SQL sql=new SQL("database", "id", "password");
		String query="INSERT INTO USUARIO(UID, ADMINID, UNOMBRE, UPRIMERAPELLIDO, USEGUNDOAPELLIDO, UPASSWORD) VALUES(?, ?, ?, ?, ?, ?)";
		sql.abrirConexion(query);
		boolean flag=sql.ejecutarSQL(query,
				"opcion,null,unombre,uprimerapellido,usegundoapellido,upassword",
				"s,s,s,s,s,s");
		sql.cerrarConexion();
		assertTrue(flag);
	}
	
	@Test
	public void pruebaDeleteUsuario() throws ClassNotFoundException, SQLException{
		SQL sql=new SQL("database", "id", "password");
		String query="DELETE FROM USUARIO WHERE UID=?";
		sql.abrirConexion(query);
		boolean flag=sql.ejecutarSQL(query,
				"opcion",
				"s");
		sql.cerrarConexion();
		assertTrue(flag);
	}
	
	@Test
	public void pruebaLogeoCorrecto() throws ClassNotFoundException, SQLException{
		CUsuario usuario=new CUsuario();
		Usuario user=new Usuario();
		user.setUID("1061733703");
		user.setUPASSWORD("1061733703");
		assertTrue(usuario.existeUsuario(user)!=null);
	}
	
	
	@Test
	public void pruebaLogeoIncorrecto() throws ClassNotFoundException, SQLException{
		CUsuario usuario=new CUsuario();
		Usuario user=new Usuario();
		user.setUID("1234");
		user.setUPASSWORD("1234");
		assertTrue(usuario.existeUsuario(user)==null);
	}
	
	@Test
	public void pruebaRealizarPedidoIncorrecto() throws NumberFormatException, ClassNotFoundException, SQLException{
		CMesero mesero=new CMesero();
		Mesero mes=new Mesero();
		mesero.atenderMesa(1);
		mesero.setCantidades(null);
		mesero.setProductos(null);
		assertTrue(mesero.agregarPedido(mes)==false);
	}
	
	@Test
	public void pruebaRealizarPedidoCorrecto() throws NumberFormatException, ClassNotFoundException, SQLException{
		CMesero mesero=new CMesero();
		Mesero mes=new Mesero();
		mes.setMESEROID("123456");
		mesero.atenderMesa(1);
		List<Producto> productos=new ArrayList<Producto>();
		productos.add(new Producto(1, 500, "ingrediente1, ingrediente2", "descripcion1", "producto1", "platos"));
		List<Integer> cantidades=new ArrayList<Integer>();
		cantidades.add(2);
		mesero.setCantidades(cantidades);
		mesero.setProductos(productos);
		assertTrue(mesero.agregarPedido(mes));
	}
}
