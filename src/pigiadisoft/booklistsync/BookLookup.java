package pigiadisoft.booklistsync;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pigiadisoft.dbhandler.MyDriver;
import pigiadisoft.dbhandler.Query;
import pigiadisoft.dbhandler.SQLCode;
import pigiadisoft.dbhandler.SQLCodeBuilder;
import pigiadisoft.model.BookModel;
import pigiadisoft.model.DataModel;


public class BookLookup {
	
	private BookBeanStrategy strat;
	private String title;
	
	public BookLookup(String title){
		this.strat=new BookBeanStrategy((Query) SQLCodeBuilder
				.createSelectAllFromWhereWithParams("ScrittoDa AS SD JOIN Libro L ON SD.libro_industryid=L.industryid "
				+ "JOIN Autore A ON A.nome=SD.autore_nome "
						, "L.titolo ILIKE ?"
						,new Object[]{"%"+title+"%"}));
		this.title=title;
	}
	
	//TODO gestire il controllo in parallelo anche su Google Books oltre che sul db
	//magari metterlo nel costruttore che fa una thread che va a cercare.
	public List<BookModel>  lookupByTitle() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, GeneralSecurityException, IOException{
		Set<BookModel> toRet=new HashSet<BookModel>();
		List<DataModel> resFromDB=this.strat.getSelectedBeans(MyDriver.getInstance().getConnection());
		for (DataModel db : resFromDB) {
			toRet.add((BookModel) db);
		}
		ExternalBookIndex ind=new GoogleBooksIndex();
		List<BookModel> fromGoogleBooks=ind.getBooksByTitle(this.title);
		this.insertBooks(fromGoogleBooks, MyDriver.getInstance().getConnection());
		toRet.addAll(fromGoogleBooks);
		
		return new ArrayList<BookModel>(toRet);
	}
	
	public void insertBooks(List<BookModel> toIns,Connection c) throws SQLException{
		Object[][] params=new Object[toIns.size()][4];
		for(int i=0;i<toIns.size();i++){
			BookModel bm=toIns.get(i);
			params[i][0]=bm.getindustryID();
			params[i][1]=bm.getTitolo();
			params[i][2]=bm.getDescrizione();
			params[i][3]=bm.getImgurl();
		}
		SQLCode insertBooks=SQLCodeBuilder.createInsertIntoOnAllColumns("Libro",params);
		
		Set<String> autori=new HashSet<String>();
		for (BookModel bm : toIns) {
			autori.addAll(bm.getAutori());
		}
		Object[][] paramsAutori=new Object[autori.size()][1];
		int iAut=0;
		for (String autore:autori) {
			paramsAutori[iAut][0]=autore;
			iAut++;
		}
		SQLCode insertAuthors=SQLCodeBuilder.createInsertIntoOnAllColumns("Autore", paramsAutori);
		
		
		List<String> wby_autori=new ArrayList<String>();
		List<String> wby_libri=new ArrayList<String>();
		for (BookModel bm : toIns) {
			for(String autore:bm.getAutori()){
				wby_libri.add(bm.getindustryID());
				wby_autori.add(autore);
			}
		}
		Object[][] paramsWBy=new Object[wby_autori.size()][2];
		for(int i=0;i<wby_autori.size();i++){
			paramsWBy[i][0]=wby_libri.get(i);
			paramsWBy[i][1]=wby_autori.get(i);
		}
		SQLCode insertWrittenBy=SQLCodeBuilder.createInsertIntoOnAllColumns("ScrittoDa", paramsWBy);
		
		insertBooks.executeQueryOnConnection(c);
		insertAuthors.executeQueryOnConnection(c);
		insertWrittenBy.executeQueryOnConnection(c);
	}
}
