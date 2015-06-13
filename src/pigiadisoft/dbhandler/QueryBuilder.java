package pigiadisoft.dbhandler;

public class QueryBuilder {
	//creare metodi man mano che servono
	//statici?
	
	public static Query createSelectAll(String from){
		Query toRet=new Query("SELECT * FROM "+from+";");
		return toRet;
	}
	
	public static Query createSelectAllOrderBy(String from, String orderBy){
		Query toRet=new Query("SELECT * FROM "+from
				+" ORDER BY "+orderBy+" ;");
		return toRet;
	}
}
