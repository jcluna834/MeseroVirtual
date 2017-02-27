package Clases;

public class Pedido {
	private long PEDIDOID;
	private String CAJERO_UID;
	private String COCINERO_UID;
	private String MESERO_UID;
	private long MESAID;
	private double COSTOTOTAL;
	private String HORAPEDIDO;
	private String HORAENTREGA;
	
	public Pedido() {}
	
	public Pedido(long pedidoid, String cajero_uid, String mesero_uid, String cocinero_uid, int mesaid, double costototal, String horapedido, String horaentrega) {
		setCAJERO_UID(cajero_uid);
		setCOCINERO_UID(cocinero_uid);
		setCOSTOTOTAL(costototal);
		setHORAENTREGA(horaentrega);
		setHORAPEDIDO(horapedido);
		setMESAID(mesaid);
		setMESERO_UID(mesero_uid);
		setPEDIDOID(pedidoid);
	}
	public String getCAJERO_UID() {
		return CAJERO_UID;
	}public String getCOCINERO_UID() {
		return COCINERO_UID;
	}public double getCOSTOTOTAL() {
		return COSTOTOTAL;
	}public String getHORAENTREGA() {
		return HORAENTREGA;
	}public String getHORAPEDIDO() {
		return HORAPEDIDO;
	}public long getMESAID() {
		return MESAID;
	}public String getMESERO_UID() {
		return MESERO_UID;
	}public long getPEDIDOID() {
		return PEDIDOID;
	}public void setCAJERO_UID(String cAJERO_UID) {
		CAJERO_UID = cAJERO_UID;
	}public void setCOCINERO_UID(String cOCINERO_UID) {
		COCINERO_UID = cOCINERO_UID;
	}public void setCOSTOTOTAL(double cOSTOTOTAL) {
		COSTOTOTAL = cOSTOTOTAL;
	}public void setHORAENTREGA(String hORAENTREGA) {
		HORAENTREGA = hORAENTREGA;
	}public void setHORAPEDIDO(String hORAPEDIDO) {
		HORAPEDIDO = hORAPEDIDO;
	}public void setMESAID(long mESAID) {
		MESAID = mESAID;
	}public void setMESERO_UID(String mESERO_UID) {
		MESERO_UID = mESERO_UID;
	}public void setPEDIDOID(long pEDIDOID) {
		PEDIDOID = pEDIDOID;
	}
}
