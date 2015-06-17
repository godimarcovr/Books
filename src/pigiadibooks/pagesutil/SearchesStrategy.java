package pigiadibooks.pagesutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookSearchModel;
import pigiadibooks.model.DataModel;

public class SearchesStrategy extends DataBeanGetStrategy {

	private String ref_searches;
	private String ref_searchkey;
	private String ref_count;

	public SearchesStrategy() {
		this.setDefaults();
		this.code=(Query) SQLCodeBuilder
				.createSelectFromGroupByOrderBy(this.ref_searchkey+",COUNT(*) AS "+this.ref_count
						, this.ref_searches
						, this.ref_searchkey
						, this.ref_count);
		
	}
	
	private void setDefaults(){
		this.ref_searches="Ricerche";
		this.ref_searchkey="ricercachiave";
		this.ref_count="contoricerche";
	}
	
	@Override
	protected List<DataModel> lastOpBeforeReturning(List<DataModel> list) {
		List<DataModel> toRet=new ArrayList<DataModel>(10);
		for(int i=0;i<Math.min(10, list.size());i++){
			toRet.add(list.get(i));
		}
		return toRet;
	}

	@Override
	protected DataModel buildDataBean(ResultSet rs) throws SQLException {
		BookSearchModel bsm=new BookSearchModel();
		bsm.setKey(rs.getString(this.ref_searchkey));
		bsm.setCount(rs.getInt(this.ref_count));
		return bsm;
	}

}
