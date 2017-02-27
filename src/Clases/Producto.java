package Clases;

public class Producto {
	private int PRODUCTOID;
	private double PRECIO;
	private String INGREDIENTES;
	private String DESCRIPCION;
	private String NOMBREPRODUCTO;
	private String TIPO;
	
	public Producto() {}
	
	public Producto(int productoid, double precio, String ingredientes, String descripcion, String nombreproducto, String tipo) {
		setDESCRIPCION(descripcion);
		setINGREDIENTES(ingredientes);
		setPRECIO(precio);
		setNOMBREPRODUCTO(nombreproducto);
		setPRODUCTOID(productoid);
		setTIPO(tipo);
	}
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}public String getINGREDIENTES() {
		return INGREDIENTES;
	}public String getNOMBREPRODUCTO() {
		return NOMBREPRODUCTO;
	}public double getPRECIO() {
		return PRECIO;
	}public int getPRODUCTOID() {
		return PRODUCTOID;
	}public String getTIPO() {
		return TIPO;
	}public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}public void setINGREDIENTES(String iNGREDIENTES) {
		INGREDIENTES = iNGREDIENTES;
	}public void setNOMBREPRODUCTO(String nOMBREPRODUCTO) {
		NOMBREPRODUCTO = nOMBREPRODUCTO;
	}public void setPRECIO(double pRECIO) {
		PRECIO = pRECIO;
	}public void setPRODUCTOID(int pRODUCTOID) {
		PRODUCTOID = pRODUCTOID;
	}public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}
}
