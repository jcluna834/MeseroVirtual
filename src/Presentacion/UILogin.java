package Presentacion;

import java.sql.SQLException;

import Clases.Usuario;
import ClasesControl.*;

public class UILogin {
	private CUsuario cus;
	
	public UILogin() {
		cus=new CUsuario();
	}
	
	public void login (String id, String contra) throws ClassNotFoundException, SQLException{
		if(!id.isEmpty() && !contra.isEmpty()){
			Usuario user=new Usuario();
			user.setUPASSWORD(contra);
			user.setUID(id);
			user=cus.existeUsuario(user);
			if(user!=null){
				String tipo=cus.tipoUsuario(user);
				if(tipo.compareTo("administrador")==0){
					// llamar a la interfaz del administrador
				}
				if(tipo.compareTo("cajero")==0){
					// llamar a la interfaz del cajero
				}
				if(tipo.compareTo("mesero")==0){
					UIMesero Mesero=new UIMesero(user.getUID());
					// llamar a la interfaz del mesero
					//ej: Mesero.show();
				}
			}
		}
	}
}
