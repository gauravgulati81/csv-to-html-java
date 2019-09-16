import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class CSVToHTML {
	private static String htmlTableRows;
	private static String[] rowData;
	private static Formatter file;
	private static FileWriter f;
	private static String htmlHeader = "<html>" + "<body>" + "<table border = '1' align='center'>";
	private static String htmlFooter = "</table>" + "</body>" + "</html>";

	public static void convert(String csvFilePath) {
		htmlTableRows = "";
		int noOfLines = countLines(csvFilePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(csvFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < noOfLines; i++) {
			try {
				rowData = reader.readLine().split(",");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			htmlTableRows = htmlTableRows + "<tr>";
			for (String j : rowData)
				htmlTableRows = htmlTableRows + "<td>" + j + "</td>";
			htmlTableRows = htmlTableRows + "</tr>";
		}

		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		generateHTMLFile(csvFilePath, htmlTableRows);
		System.out.println("Done!");
	}

	private static int countLines(String csvFilePath) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(csvFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int lines = 0;
		try {
			while (reader.readLine() != null)
				lines++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}

	private static void generateHTMLFile(String htmlFilePath, String htmlTableRows) {
		htmlFilePath = htmlFilePath + ".html";
		try {
			f = new FileWriter(htmlFilePath, false);
			file = new Formatter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.format("%s%s%s", htmlHeader, htmlTableRows, htmlFooter);
		file.close();
	}

}
