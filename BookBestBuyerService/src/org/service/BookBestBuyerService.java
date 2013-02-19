package org.service;

import java.io.IOException;

import org.amazon.AmazonFinder;
import org.barnesandnoble.BNFinder;
import org.googlebooks.GoogleSearch;

public class BookBestBuyerService {

	public String searchByGoogle(String keyword) {
		GoogleSearch gs = new GoogleSearch();
		try {
			return gs.searchByKeyword(keyword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String searchByAmazon(String isbn) {
		AmazonFinder af = new AmazonFinder();
		try {
			return af.searchByISBN(isbn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String searchByBN(String isbn) {
		BNFinder bf = new BNFinder();
		try {
			return bf.searchByISBN(isbn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
