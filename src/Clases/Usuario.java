package Clases;

public class Usuario {
	 private String UID;
	 private String UPASSWORD;
	 private String ADMINID;
	 private String UNOMBRE;
	 private String UPRIMERAPELLIDO;
	 private String USEGUNDOAPELLIDO;
	 private String FECHACREACION;
	 
	 public Usuario() {}
	 
	 public Usuario(String uid, String upassword, String adminid, String unombre, String uprimerapellido, String usegundoapellido, String fechacreacion) {
		 setADMINID(adminid);
		 setFECHACREACION(fechacreacion);
		 setUID(uid);
		 setUNOMBRE(unombre);
		 setUPASSWORD(upassword);
		 setUPRIMERAPELLIDO(uprimerapellido);
		 setUSEGUNDOAPELLIDO(usegundoapellido);
	 }
	 public String getADMINID() {
		return ADMINID;
	}public String getFECHACREACION() {
		return FECHACREACION;
	}public String getUID() {
		return UID;
	}public String getUNOMBRE() {
		return UNOMBRE;
	}public String getUPASSWORD() {
		return UPASSWORD;
	}public String getUPRIMERAPELLIDO() {
		return UPRIMERAPELLIDO;
	}public String getUSEGUNDOAPELLIDO() {
		return USEGUNDOAPELLIDO;
	}public void setADMINID(String aDMINID) {
		ADMINID = aDMINID;
	}public void setFECHACREACION(String fECHACREACION) {
		FECHACREACION = fECHACREACION;
	}public void setUID(String uID) {
		UID = uID;
	}public void setUNOMBRE(String uNOMBRE) {
		UNOMBRE = uNOMBRE;
	}public void setUPASSWORD(String uPASSWORD) {
		UPASSWORD = uPASSWORD;
	}public void setUPRIMERAPELLIDO(String uPRIMERAPELLIDO) {
		UPRIMERAPELLIDO = uPRIMERAPELLIDO;
	}public void setUSEGUNDOAPELLIDO(String uSEGUNDOAPELLIDO) {
		USEGUNDOAPELLIDO = uSEGUNDOAPELLIDO;
	}
}
