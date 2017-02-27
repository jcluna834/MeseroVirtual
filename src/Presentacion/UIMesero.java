package Presentacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Clases.Mesero;
import Clases.Producto;
import ClasesControl.CMesero;

public class UIMesero {
	private CMesero cmes;
	private Mesero mesero;
	private List<Producto> productos;
	private List<Integer> cantidades;
	private UIProductos Productos;
	
	// Constructor que recibe el meseroid obtenido en la clase UILogin.java
	public UIMesero(String meseroid) {
		cmes=new CMesero();
		mesero=new Mesero(meseroid);
		productos=new ArrayList<Producto>();
		cantidades=new ArrayList<Integer>();
	}
	
	public void seleccionarMesa(int mesa) throws ClassNotFoundException, SQLException{
		if(cmes.atenderMesa(mesa)){
			Productos=new UIProductos();
			// activar y/o Llamar a la interfaz de productos
			// Productos.show();
			// la interfaz va a retornar la lista de productos seleccionados junto con la lista de cantidades
			// que pasaran a ser los parametros de la clase control CMesero
			cmes.setProductos(Productos.getProductos());
			cmes.setCantidades(Productos.getCantidades());
		}
	}
	
	public void agregarPedido() throws NumberFormatException, ClassNotFoundException, SQLException {
		if(!productos.isEmpty() && !cantidades.isEmpty() && !mesero.getMESEROID().isEmpty()) {
			if(cmes.agregarPedido(mesero))
				limpiarPantalla();
		}
	}
	
	public void limpiarPantalla(){
		// se limpia la ventana cerrando la interfaz de productos y se reinician los atributos
		cmes=new CMesero();
		mesero=new Mesero();
		productos=new ArrayList<Producto>();
		cantidades=new ArrayList<Integer>();
		//Productos.dispose();
	}
}
