package pigiadibooks.pagesutil;

import java.sql.SQLException;
import java.util.List;

import pigiadibooks.dbhandler.DMLCode;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookSearchModel;

public class ResearchLogger {
	
	SQLCode insertSearch, statTopSearches;
	private String ref_searches;
	private String ref_searchkey;
	
	public ResearchLogger(){
		this.setDefaults();
	}
	
	private void setDefaults(){
		this.ref_searches="Ricerche";
		this.ref_searchkey="ricercachiave";
	}
	
	public void insertSearch(String ricerca) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		DMLCode insert=(DMLCode) SQLCodeBuilder
				.createInsertInto(this.ref_searches
						, new String[]{this.ref_searchkey}
						, new Object[][]{{ricerca}});
		insert.executeQueryOnConnection(MyDriver.getInstance().getConnection());
	}
	
	public List<BookSearchModel> getTop10Searches(){
		String count="contoricerche";
		Query select=(Query) SQLCodeBuilder
				.createSelectFromGroupByOrderBy(this.ref_searchkey+",COUNT(*) AS "+count
						, this.ref_searches
						, this.ref_searchkey
						, count);
		return null;
	}
	
}
