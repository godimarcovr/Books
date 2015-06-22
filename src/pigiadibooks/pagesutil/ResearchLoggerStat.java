package pigiadibooks.pagesutil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import pigiadibooks.dbhandler.DMLCode;
import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookSearchModel;
import pigiadibooks.model.DataModel;

public class ResearchLoggerStat implements Observer{
	
	private SQLCode insertSearch, statTopSearches;
	private String ref_searches;
	private String ref_searchkey;
	private SearchesStrategy ss;
	
	public ResearchLoggerStat(){
		this.setDefaults();
		this.ss=new SearchesStrategy();
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
	
	public List<BookSearchModel> getTop10Searches() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		List<DataModel> ldm=this.ss.getSelectedBeans(MyDriver.getInstance().getConnection());
		List<BookSearchModel> toRet=new ArrayList<BookSearchModel>(10);
		for (DataModel dm : ldm) {
			toRet.add((BookSearchModel)dm);
		}
		return toRet;
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			this.insertSearch((String)arg);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
