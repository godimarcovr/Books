package pigiadibooks.booklistsync;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import pigiadibooks.model.BookModel;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.VolumeInfo.ImageLinks;
import com.google.api.services.books.model.Volume.VolumeInfo.IndustryIdentifiers;
import com.google.api.services.books.model.Volumes;

public class GoogleBooksIndex extends ExternalBookIndex {

	private static String apikey = null;
	private static JsonFactory jsonFactory = null;
	private static String APPLICATION_NAME = null;

	public GoogleBooksIndex() {
		if (jsonFactory == null) {
			jsonFactory = JacksonFactory.getDefaultInstance();
		}
		if (APPLICATION_NAME == null) {
			APPLICATION_NAME = "PigiadiBooks";
		}
		if (apikey == null) {
			apikey = "AIzaSyAaEUJM1fVB91_nhO2lWrzma0DcxeJ1Bj0";
		}
	}

	@Override
	public List<BookModel> getBooksByTitle(String title)
			throws GeneralSecurityException, IOException {
		Books books = null;

		books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				jsonFactory, null)
				.setApplicationName(APPLICATION_NAME)
				.setGoogleClientRequestInitializer(
						new BooksRequestInitializer(apikey)).build();

		com.google.api.services.books.Books.Volumes.List volumesList = books
				.volumes().list("intitle:" + title);
		// volumesList.set("langRestrict", "it");
		Volumes volumes = volumesList.execute();
		if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
			return new ArrayList<BookModel>();
		}
		List<BookModel> toRet = new ArrayList<BookModel>();
		for (Volume volume : volumes.getItems()) {
			BookModel toAdd = new BookModel();
			Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
			toAdd.setTitolo(volumeInfo.getTitle());
			toAdd.addAutori((volumeInfo.getAuthors()) != null ? (volumeInfo
					.getAuthors()) : new ArrayList<String>());
			boolean foundIndID = false;
			if (volumeInfo.getIndustryIdentifiers() != null) {
				
				for (IndustryIdentifiers ii : volumeInfo
						.getIndustryIdentifiers()) {
					if (ii.getType().equals("ISBN_10")
							|| ii.getType().equals("ISBN_13")) {
						// autoconversione in caso sia isbn10
						toAdd.setindustryID(ii.getIdentifier());
						foundIndID = true;
						break;
					}
				}
				if (!foundIndID) {
					for (IndustryIdentifiers ii : volumeInfo.getIndustryIdentifiers()) {
						if (ii.getType().equals("ISSN")){
							toAdd.setindustryID(ii.getIdentifier());
							foundIndID=true;
							break;
						}
						
					}
				}
				if (!foundIndID) {
					for (IndustryIdentifiers ii : volumeInfo.getIndustryIdentifiers()) {
						toAdd.setindustryID(ii.getIdentifier());
						foundIndID=true;
						break;
					}
				}
			}
			toAdd.setDescrizione(volumeInfo.getDescription());
			toAdd.setImgurl(volumeInfo.getImageLinks()!=null
					?this.getBiggestImageURL(volumeInfo.getImageLinks())
					:"");
			if(foundIndID)toRet.add(toAdd);
		}
		return toRet;
	}

	private String getBiggestImageURL(ImageLinks il) {
		if (il.getLarge() != null)
			return il.getLarge();
		if (il.getMedium() != null)
			return il.getMedium();
		if (il.getSmall() != null)
			return il.getSmall();
		if (il.getThumbnail() != null)
			return il.getThumbnail();
		if (il.getSmallThumbnail() != null)
			return il.getSmallThumbnail();
		if (il.getExtraLarge() != null)
			return il.getExtraLarge();
		return null;
	}

}
