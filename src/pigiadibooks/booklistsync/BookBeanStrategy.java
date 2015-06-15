package pigiadibooks.booklistsync;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pigiadibooks.dbhandler.DMLCode;
import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookModel;
import pigiadibooks.model.DataModel;

public class BookBeanStrategy extends DataBeanGetStrategy {
	private String ref_titolo,ref_industryid, ref_autore,ref_desc,ref_url;
	
	private DMLCode insertBook;
	
	public BookBeanStrategy(Query selectLibri, String ref_titolo, String ref_industryid
			,String ref_autore, String ref_desc, String ref_url){
		super(selectLibri);
		this.ref_titolo = ref_titolo;
		this.ref_industryid = ref_industryid;
		this.ref_autore = ref_autore;
		this.ref_desc=ref_desc;
		this.ref_url=ref_url;
	}
	
	public BookBeanStrategy(Query selectLibri){
		this(selectLibri,"titolo","industryid","nome","descrizione","imgurl");
	}

	public BookBeanStrategy() {
		this((Query)SQLCodeBuilder
				.createSelectAllFrom("ScrittoDa AS SD JOIN Libro L ON SD.libro_industryid=L.industryid "
				+ "JOIN Autore A ON A.nome=SD.autore_nome "));
	}
	
	@Override
	protected BookModel buildDataBean(ResultSet rs) throws SQLException {
		BookModel toRet=new BookModel();
		toRet.setindustryID(rs.getString(this.ref_industryid));
		toRet.setTitolo(rs.getString(this.ref_titolo));
		Set<String> autori=new HashSet<String>();
		autori.add(rs.getString(this.ref_autore));
		toRet.addAutori(autori);
		toRet.setDescrizione(rs.getString(this.ref_desc));
		toRet.setImgurl(rs.getString(this.ref_url));
		return toRet;
	}

	@Override
	protected List<DataModel> lastOpBeforeReturning(List<DataModel> list) {
		HashMap<String,BookModel> map=new HashMap<String,BookModel>();
		for (DataModel dataBean : list) {
			BookModel bb=(BookModel) dataBean;
			BookModel toAdd=map.get(bb.getindustryID());
			if(toAdd==null){
				map.put(bb.getindustryID(), bb);
				toAdd=bb;
			}
			//hanno tutti solo un autore
			toAdd.addAutore(bb.getAutori().iterator().next());
		}
		return new ArrayList<DataModel>(map.values());
	}

}
