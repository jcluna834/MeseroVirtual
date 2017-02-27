package ClasesControl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Clases.Mesero;
import Clases.Pedido;
import Clases.Producto;
import PostgreSQL.SQL;

public class CMesero {
	private SQL postgresql;
	private Pedido pedido;
	private List<Producto> productos;
	private List<Integer> cantidades;
	
	public CMesero() {
		postgresql=new SQL("database","id", "password");
		pedido=new Pedido();
		productos=new ArrayList<Producto>();
		cantidades=new ArrayList<Integer>();
	}
	
	public void setCantidades(List<Integer> cantidades) {
		this.cantidades = cantidades;
	}
	
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public boolean atenderMesa(int mesa) throws ClassNotFoundException, SQLException{
		String sql="SELECT COUNT(*) FROM MESA WHERE MESAID=?";
		postgresql.abrirConexion(sql);
		int result=postgresql.ejecutarCount(sql, String.valueOf(mesa),"i");
		postgresql.cerrarConexion();
		if(result==1){
			pedido.setMESAID(mesa);
			return true;
		}else return false;
	}
	
	public boolean agregarPedido(Mesero mesero) throws NumberFormatException, ClassNotFoundException, SQLException {
		if(pedido!=null && mesero!=null && productos!=null && !productos.isEmpty()){
			long pedidoid=-1;
			int i=0;
			double costoTotal=0;
			// obtenemos el pedidoid del producto final
			String sql="SELECT COUNT(*) FROM PEDIDO";
			postgresql.abrirConexion(sql);
			pedidoid=postgresql.ejecutarCount(sql,null,null);
			postgresql.cerrarConexion();
			pedidoid++;
			// se agregará el pedido siguiente
			sql="INSERT INTO pedido(pedidoid, mesaid, meseroid, cocineroid, cajeroid, costototal, horapedido, horaentrega) VALUES( ? , ? , ? , ?, ? , ?, DEFAULT, ?)";
			postgresql.abrirConexion(sql);
			postgresql.ejecutarSQL(sql,
					pedidoid+","+pedido.getMESAID()+","+mesero.getMESEROID()+",null,null,0,null",
					"i,i,s,s,s,f,d");
			postgresql.cerrarConexion();
			double costo;
			// se agregaran todos los productos seleccionados en el pedido
			for(Producto producto: productos){
				costo=producto.getPRECIO()*cantidades.get(i);
				costoTotal=costo+costoTotal;
				sql="INSERT INTO contiene(pedidoid, productoid, cantidad, costoparcial) VALUES(?, ?, ?, ?)";
				postgresql.abrirConexion(sql);
				postgresql.ejecutarSQL(sql,
						pedidoid+","+producto.getPRODUCTOID()+","+cantidades.get(i)+","+costo,
						"i,i,i,f");
				postgresql.cerrarConexion();
				i++;
			}
			// se actualiza el valor total del pedido ingresado
			sql="UPDATE PEDIDO SET COSTOTOTAL=? WHERE PEDIDOID=?";
			postgresql.abrirConexion(sql);
			boolean op=postgresql.ejecutarSQL(sql, costoTotal+","+pedidoid,"f,i");
			postgresql.cerrarConexion();
			return (op);
		}
		return false;
	}
}
