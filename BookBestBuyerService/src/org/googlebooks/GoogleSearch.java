package org.googlebooks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleSearch {
	
	/**
	public static void main(String[] args) {
		try {
			GoogleSearch gs = new GoogleSearch();
			gs.searchByKeyword("paul anka");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 **/
	public GoogleSearch() {

	}

	@SuppressWarnings("static-access")
	public String searchByKeyword(String keyword) throws IOException {
		String[] splitKeyword = keyword.split(" ");
		String url = "https://www.googleapis.com/books/v1/volumes?q=";
		for (int i = 0; i < splitKeyword.length; i++) {
			url = url + splitKeyword[i] + "+";
		}
		url = url.substring(0, url.length() - 1) + "&printType=books";
		URL urlmy = null;
		urlmy = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlmy.openConnection();
		con.setFollowRedirects(true);
		con.setInstanceFollowRedirects(false);
		con.connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				con.getInputStream(), "UTF-8"));
		String s = "";
		StringBuffer sb = new StringBuffer("");
		while ((s = br.readLine()) != null) {
			sb.append(s + "\r\n");
		}
		s = sb.toString();
		//System.out.println(sb.toString());
		return s;
	}

}
