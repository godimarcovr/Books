package pigiadibooks.pagesutil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pigiadibooks.booklistsync.BookBeanStrategy;
import pigiadibooks.dbhandler.DMLCode;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookModel;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.OwnBookModel;

public class OwnedBooks {
	
	private String user;
	private String into;
	private String from;
	private String updateTable;
	private String ref_idLibro;
	private String ref_user;
	private String ref_condizioni;
	private String ref_voto;
	private String ref_recensione;
	
	private OwnBooksStrategy obs;
	
	public OwnedBooks(String user){
		this.user=user;
		this.setDBNames();
		this.obs=new OwnBooksStrategy(user);
	}
	
	public void setDBNames(){
		this.into="libroposseduto";
		this.from=this.into;
		this.updateTable=this.into;
		this.ref_idLibro="libro_industryid";
		this.ref_user="user_username";
		this.ref_condizioni="condizioni";
		this.ref_voto="voto";
		this.ref_recensione="recensione";
	}
	
	public List<OwnBookModel> selectOwnBooks() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		List<DataModel> ldm=this.obs.getSelectedBeans(MyDriver.getInstance().getConnection());
		List<OwnBookModel> lobm=new ArrayList<OwnBookModel>(ldm.size());
		for (DataModel dm : ldm) {
			lobm.add((OwnBookModel)dm);
		}
		return lobm;
	}
	
	public void insertBook(String idLibro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		DMLCode insert=(DMLCode) SQLCodeBuilder.createInsertInto(this.into
				, new String[]{this.ref_idLibro,this.ref_user}
				, new Object[][]{{idLibro,this.user}});
		insert.executeQueryOnConnection(MyDriver.getInstance().getConnection());
	}
	
	public void deleteBook(String idLibro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		DMLCode delete=(DMLCode) SQLCodeBuilder
				.createDeleteFromOnlyEquals(this.from
						, new String[]{this.ref_idLibro,this.ref_user}
						, new Object[][]{{idLibro,this.user}});
		delete.executeQueryOnConnection(MyDriver.getInstance().getConnection());
	}
	
	public void updateBook(String idLibro, Integer cond, Integer voto, String recensione) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		DMLCode update=(DMLCode) SQLCodeBuilder
				.createUpdate(this.updateTable
						, new String[]{this.ref_condizioni,this.ref_voto, this.ref_recensione}
						, new Object[]{cond,voto,recensione}
						, this.ref_idLibro+"='"+idLibro+"' AND "+this.ref_user+"='"+this.user+"'");
		update.executeQueryOnConnection(MyDriver.getInstance().getConnection());
	}
	
	
}
