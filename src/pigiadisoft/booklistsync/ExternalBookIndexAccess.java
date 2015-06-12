package pigiadisoft.booklistsync;

public class ExternalBookIndexAccess {

	private static ExternalBookIndex ebi=null;
	
	public static ExternalBookIndex getInstance(){
		if(ebi==null){
			ebi=new GoogleBooksIndex();
		}
		return ebi;
	}

}
