package org.barnesandnoble;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.regex.Pattern;

public class BNFinder {

	/**
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BNFinder bf = new BNFinder();
			// System.out.println(bf.searchByISBN("9780123748560"));
			System.out.println(bf.searchByISBN("9780399144462"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("expection");
		}
	}
	**/
	
	public BNFinder() {

	}

	public String searchByISBN(String isbn) throws IOException {
		String searchPageHTML = checkExist(isbn);
		// System.out.println(searchPageHTML);

		if (!searchPageHTML
				.contains("We found <strong>0 results</strong> for <strong>")) {
			return getInformation(searchPageHTML);
		}
		return "no result";
	}

	public String checkExist(String isbn) throws IOException {
		URL url = new URL("http://www.barnesandnoble.com/s?store=book&keyword="
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
		Pattern pattern = Pattern.compile("bnPriceOnline");
		String[] strs = pattern.split(searchPageHTML);
		String need = strs[1];
		String url = strs[0].split("og:url")[1].trim().split(">")[0];
		url = url.split("\"")[2];
		// url = url.substring(0, url.length()-1);
		// String price = need.split("'  ")[0];
		String price = need.split(" : ")[1].trim();
		price = price.substring(0, price.lastIndexOf(".") + 3);
		// price = price.substring(1).split("</span>")[0];
		// return url+","+price;
		return url + "," + price;
	}

}
