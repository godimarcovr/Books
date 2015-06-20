package pigiadibooks.pagesutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.OwnBookModel;

public class OwnBooksStrategy extends DataBeanGetStrategy {

	private String ref_idLibroPosseduto;
	private String ref_user;
	private String ref_industryid;
	private String ref_titolo;
	private String ref_condizioni;
	private String ref_voto;
	private String ref_recensione;

	private String user;
	
	public OwnBooksStrategy(Query select) {
		this.code=select;
		this.setDefaults();
		//l'utente deve settare username
	}
	
	
	public OwnBooksStrategy(String username) {
		this.user=username;
		this.setDefaults();
		Query select=(Query) SQLCodeBuilder
		.createSelectAllFromWhere("ScrittoDa AS SD JOIN Libro L ON SD.libro_industryid=L.industryid "
				+ "JOIN Autore A ON A.nome=SD.autore_nome "
				+ "JOIN LibroPosseduto LP ON LP."+this.ref_idLibroPosseduto+"=L."+this.ref_industryid
				, this.ref_user+"='"+this.user+"';");
		this.code=select;
		
		
	}
	
	public void setDefaults(){
		this.ref_idLibroPosseduto = "libro_industryid";
		this.ref_user = "user_username";
		this.ref_industryid = "industryid";
		this.ref_titolo = "titolo";
		this.ref_condizioni = "condizioni";
		this.ref_voto = "voto";
		this.ref_recensione = "recensione";
	}
	
	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	@Override
	protected List<DataModel> lastOpBeforeReturning(List<DataModel> list) {
		return list;
	}

	@Override
	protected DataModel buildDataBean(ResultSet rs) throws SQLException {
		OwnBookModel obm=new OwnBookModel();
		obm.setIndustryID(rs.getString(this.ref_industryid));
		obm.setTitolo(rs.getString(this.ref_titolo));
		obm.setCondizioni(rs.getInt(this.ref_condizioni));
		obm.setVoto(rs.getInt(this.ref_voto));
		obm.setRecensione(rs.getString(this.ref_recensione));
		return obm;
	}

}
