package org.amazon;

import java.io.*;
import java.net.URL;
import java.util.regex.Pattern;

public class AmazonFinder {

	/**
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AmazonFinder af = new AmazonFinder();
			System.out.println(af.searchByISBN("9780123748560"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("expection");
		}
	}
	**/
	public AmazonFinder() {

	}

	public String searchByISBN(String isbn) throws IOException {
		String searchPageHTML = checkExist(isbn);
		// System.out.println(searchPageHTML);
		if (searchPageHTML.contains("Showing 1 Result")) {
			return getInformation(searchPageHTML);
		}
		return "no result";
	}

	public String checkExist(String isbn) throws IOException {
		URL url = new URL(
				"http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords="
						+ isbn);
		// 打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
		Reader reader = new InputStreamReader(new BufferedInputStream(
				url.openStream()));
		int c;
		StringBuffer sb = new StringBuffer();
		while ((c = reader.read()) != -1) {
			sb.append((char) c);
		}
		reader.close();
		return sb.toString();
	}

	public String getInformation(String searchPageHTML) {
		Pattern pattern = Pattern.compile("newPrice");
		String[] strs = pattern.split(searchPageHTML);
		String need = strs[1];
		String url = need.split("\"")[2];
		String price = need.split("<span>")[1];
		price = price.substring(1).split("</span>")[0];
		return url + "," + price;
	}

}
