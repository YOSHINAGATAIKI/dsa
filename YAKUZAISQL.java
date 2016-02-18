package syokujinaiyou;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class ReadCSV {

  public static void main(String[] args) {
	Connection con = null;
    try {
    	Class.forName("com.mysql.jdbc.Driver");
		//DBユーザ名入力用
		System.out.println("DB接続用のユーザ名を入力してください。");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String user=br.readLine();
		if("".equals(user)){
			System.out.println("ユーザ名を入力してください。");
			System.exit(0);
		}

		//DBパスワード入力用
		System.out.println("DB接続用のパスワードを入力してください。");
		BufferedReader br2=new BufferedReader(new InputStreamReader(System.in));
		String pass=br2.readLine();
		if("".equals(pass)){
			System.out.println("パスワードを入力してください。");
			System.exit(0);
		}

		//DB接続
		con = DriverManager.getConnection("jdbc:mysql://localhost/syokuji",user,pass);

		//食事内容の挿入用SQL文
		String SQL="insert into syokuji values(?,?,?,?,?)";
	    // ステートメントオブジェクトを生成
	    PreparedStatement ps = con.prepareStatement(SQL);
	    //1番目の?にIDを入力する。
	    int num = 0;
	    //2番目の?に日付を入力する。
	    String date = "" ;
	    //3番目の?に曜日を入力
	    String youbi = "";
	    //4番目の?に時間帯を入力する。
	    String zikanntai="";
	    //5番目の?に食事内容を入力する。
	    String syokujinaiyou = "";


	//読み込み場所の指定
	System.out.println("読み込み場所を指定してください。");
	BufferedReader br0=new BufferedReader(new InputStreamReader(System.in));
	String buf=br0.readLine();
	if("".equals(buf)||"　".equals(buf)){
		System.out.println("読み込み場所を入力してください。");
		System.exit(0);
	}
      File csv = new File(buf); // CSVデータファイル
      BufferedReader br8 = new BufferedReader(new FileReader(csv));
      // 最終行まで読み込む
      String line = "";
      while ((line = br8.readLine()) != null) {
        // 1行をデータの要素に分割
        StringTokenizer st = new StringTokenizer(line, ",");
        while (st.hasMoreTokens()) {
          ps.setInt(1,num);
          ps.setString(2,date);
          ps.setString(3,youbi);
          ps.setString(4,zikanntai);
          ps.setString(5,syokujinaiyou);
          System.out.print(st.nextToken() + "\t");
        }
        System.out.println();
      }
      //閉じる
      br8.close();
	    int r=ps.executeUpdate();
	    if(r!=0){
	    	System.out.println(r+"件挿入しました。");
	    				}
	    	ps.close();
	    	con.close();
    } catch (FileNotFoundException e) {
      // Fileオブジェクト生成時の例外捕捉
      /*e.printStackTrace();*/
    	System.out.println("ファイルが見つかりません。");
    } catch (IOException e) {
      // BufferedReaderオブジェクトのクローズ時の例外捕捉
      //e.printStackTrace();
    	System.out.println("入出力エラーが発生しました。");
    } catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		System.out.println("SQLエラーが発生しました。");
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO 自動生成された catch ブロック
		System.out.println("クラスが見つかりません。");
	}
  }
}
