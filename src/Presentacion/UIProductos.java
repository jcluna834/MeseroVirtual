package Presentacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Clases.Producto;
import ClasesControl.CProducto;

public class UIProductos {
	private List<Producto> productos;
	private List<Integer> cantidades;
	private CProducto cpro;
	
	public UIProductos() {
		cpro=new CProducto();
		productos=new ArrayList<Producto>();
		cantidades=new ArrayList<Integer>();
	}
	
	public List<Integer> getCantidades() {return cantidades;}
	
	public List<Producto> getProductos() {return productos;}
	
	public void seleccionarTipoProductos(String tipo) throws ClassNotFoundException, SQLException{
		List<Producto> productos=cpro.obtenerProductos(tipo);
		if(productos!=null){
			int i=0;
			while(i!=productos.size()){ 
				// se visualizan los productos de dicho tipo
				i++;
			}
		}
	}
	
	public void seleccionarProducto(int id, int cantidad) throws ClassNotFoundException, SQLException{
		if(cantidad>0){
			if(cpro.existeProducto(id)){
				Producto p=cpro.obtenerProducto(id);
				if(p!=null){
					productos.add(p);
					cantidades.add(cantidad);
					// se resalta el producto seleccionado
					// se actualiza la vista de pedido
				}
			}
		}
	}
	
	
}
