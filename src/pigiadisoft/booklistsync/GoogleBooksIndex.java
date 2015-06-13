package pigiadisoft.booklistsync;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.VolumeInfo.IndustryIdentifiers;
import com.google.api.services.books.model.Volumes;

public class GoogleBooksIndex extends ExternalBookIndex {

	private static String apikey=null;
	private static JsonFactory jsonFactory=null;
	private static String APPLICATION_NAME=null;

	public GoogleBooksIndex() {
		if(jsonFactory==null){
			jsonFactory = JacksonFactory.getDefaultInstance();
		}
		if(APPLICATION_NAME==null){
			APPLICATION_NAME = "PigiadiBooks";
		}
		if(apikey==null){
			apikey = "AIzaSyAaEUJM1fVB91_nhO2lWrzma0DcxeJ1Bj0";
		}
	}

	@Override
	public List<BookBean> getBooksByTitle(String title) {
		Books books = null;
		try {
			books = new Books.Builder(
					GoogleNetHttpTransport.newTrustedTransport(), jsonFactory,
					null)
					.setApplicationName(APPLICATION_NAME)
					.setGoogleClientRequestInitializer(
							new BooksRequestInitializer(apikey)).build();

			com.google.api.services.books.Books.Volumes.List volumesList = books
					.volumes().list("intitle:" + title);
			Volumes volumes = volumesList.execute();
			if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
				return new ArrayList<BookBean>();
			}
			List<BookBean> toRet = new ArrayList<BookBean>();
			for (Volume volume : volumes.getItems()) {
				BookBean toAdd = new BookBean();
				Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
				toAdd.setTitolo(volumeInfo.getTitle());
				toAdd.addAutori((volumeInfo.getAuthors()));
				for (IndustryIdentifiers ii : volumeInfo.getIndustryIdentifiers()) {
					if(ii.getType().equals("ISBN_10") || ii.getType().equals("ISBN_13")){
						//autoconversione in caso sia isbn10
						toAdd.setIsbn_13(ii.getIdentifier());
						break;
					}
				}
				toRet.add(toAdd);
			}
			return toRet;

		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
