/**
 *
 */
package azuki.project.test.commonscsv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * Apache commons CSVを用い、CSVファイルを書き込むテストです。
 *
 * @author seiya
 *
 */
public class WriteTest {
	
	static final String CSV_ENCODE = "UTF-8";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 引数チェック
		if (args.length != 1) {
			System.out.println("第一引数にファイルパスを指定してください。");
			return;
		}
		
		Path path = Paths.get(args[0]);
		// 存在チェック
		if (Files.exists(path)) {
			System.out.println("ファイルが既に存在します。");
			System.out.println("args[0] : " + args[0]);
			return;
		}
		
		// CSV書き込み用データ
		String[][] csvDataArray = new String[10][3];
		for (int i = 0; i < 10; i++) {
			String[] record = new String[3];
			record[0] = "data" + i;
			record[1] = "aあ,a";
			record[2] = "1\"23";
			csvDataArray[i] = record;
			
		}
		
		// CSVファイル書き込み
		System.out.println("書き込み先ファイル : " + args[0]);
		CSVFormat format = CSVFormat.RFC4180.withDelimiter(',').withQuote('\"');
		CSVPrinter csvPrinter = null;
		try {
			csvPrinter = new CSVPrinter(new FileWriter(path.toString()), format);
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 3; j++) {
					csvPrinter.print(csvDataArray[i][j]);
				}
				csvPrinter.println();
			}
			csvPrinter.flush();
			System.out.println("書き込み完了");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (csvPrinter != null) {
				try {
					csvPrinter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
