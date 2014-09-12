/**
 *
 */
package azuki.project.test.commonscsv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Apache commons CSVを用い、CSVファイルを読み込むテストです。
 *
 * @author seiya
 *
 */
public class ReadTest {
	
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
		if (!Files.exists(path)) {
			System.out.println("ファイルが存在しません。");
			System.out.println("args[0] : " + args[0]);
		}
		
		// CSVファイル読み込み
		System.out.println("テスト用ファイル : " + args[0]);
		CSVParser csvParser = null;
		List<CSVRecord> recordList = new ArrayList<CSVRecord>();
		try {
			csvParser = CSVParser.parse(path.toFile(), Charset.forName(CSV_ENCODE),CSVFormat.RFC4180);
			recordList = csvParser.getRecords();
			
			// 画面出力
			for (CSVRecord record : recordList) {
				for (int i = 0; i < record.size(); i++) {
					System.out.print(record.get(i));
					if (i != record.size()-1) {
						System.out.print(", ");
					}
				}
				System.out.print("\n");
			}
			// パーサのクローズ
			if (csvParser != null) {
				csvParser.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
