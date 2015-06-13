package pigiadisoft.dbhandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pigiadisoft.booklistsync.BookBean;

public class BookBeanStrategy extends DataBeanStrategy {
	private String ref_titolo,ref_isbn, ref_autore;
	
	private BookBeanStrategy(Query selectLibri, String ref_titolo, String ref_isbn,	String ref_autore){
		super(selectLibri);
		this.ref_titolo = ref_titolo;
		this.ref_isbn = ref_isbn;
		this.ref_autore = ref_autore;
	}

	public BookBeanStrategy() {
		super(QueryBuilder
				.createSelectAllOrderBy("ScrittoDa AS SD JOIN Libro L ON SD.libro_isbn=L.isbn "
				+ "JOIN Autore A ON A.nome=SD.autore_nome ","L.isbn"));
		this.ref_titolo="titolo";
		this.ref_isbn="isbn";
		this.ref_autore="nome";
	}
	
	@Override
	protected BookBean buildDataBean(ResultSet rs) throws SQLException {
		BookBean toRet=new BookBean();
		toRet.setIsbn_13(rs.getString(this.ref_isbn));
		toRet.setTitolo(rs.getString(this.ref_titolo));
		Set<String> autori=new HashSet<String>();
		autori.add(rs.getString(this.ref_autore));
		toRet.addAutori(autori);
		return toRet;
	}

	@Override
	protected List<DataBean> lastOpBeforeReturning(List<DataBean> list) {
		HashMap<String,BookBean> map=new HashMap<String,BookBean>();
		for (DataBean dataBean : list) {
			BookBean bb=(BookBean) dataBean;
			BookBean toAdd=map.get(bb.getIsbn_13());
			if(toAdd==null){
				map.put(bb.getIsbn_13(), bb);
				toAdd=bb;
			}
			//hanno tutti solo un autore
			toAdd.addAutore(bb.getAutori().iterator().next());
		}
		return new ArrayList<DataBean>(map.values());
	}

}
