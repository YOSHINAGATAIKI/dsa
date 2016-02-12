package yakkaSELECT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YAKUZAISQL {
	public  void YAKUZAI(){
		String searchWord = "%";
		// ここでエスケープ処理を行い、%を\%に置き換えます
		searchWord = searchWord.replaceAll("%", "\\\\%");
		Connection con = null;


try{
	//ドライバの取得
	Class.forName("com.mysql.jdbc.Driver");
	//データベースへ接続する。
	//(URL,ユーザ名,パスワード)
	con = DriverManager.getConnection("jdbc:mysql://localhost/okusuri","root","kyoto947613");
    // データを検索するSQL文を作成
    String sql = "select SY.A,SY.B,SY.C,SY.D,SY.E,SY.F,SY.G,SY.H,SY.I,SY.J,YA.C,YA.D from SY,YA WHERE SY.G=YA.B";
    // ステートメントオブジェクトを生成
    PreparedStatement ps = con.prepareStatement(sql);
    System.out.println("データを表示します。");
    System.out.println("ファイルの書き込み場所を指定してください。");
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String buf=br.readLine();

      PrintWriter fp = new PrintWriter(new BufferedWriter(new FileWriter(buf)));
      // クエリーを実行して結果セットを取得
      ResultSet rs = ps.executeQuery();

      	//問い合わせSQLの書き出し
      	while(rs.next()){
      		fp.println("**************************************");
      		fp.println("処方日:"+rs.getDate(1));
      		fp.println("調剤薬局名:"+rs.getString(2));
      		fp.println("医療機関名:"+rs.getString(3));
      		fp.println("患者ID:"+rs.getInt(4));
      		fp.println("処方医:"+rs.getString(5)+" 先生");
      		fp.println("診療科:"+rs.getString(6));
      		fp.println("薬剤名:"+rs.getString(7));
      		fp.println("薬価:"+rs.getDouble(11)+"円/"+rs.getString(12));
      		fp.println("1日量:"+rs.getString(8));
      		fp.println("服用方法:"+rs.getString(9)+"服用");
      		fp.println("処方日数:"+rs.getInt(10)+"日分");
      		switch(rs.getString(9)){
			 case "１日１回　朝食後":
			switch(rs.getString(8)){
			 case "1錠":
			if(rs.getDouble(11)<15){
			 fp.println("薬剤点数:"+(1*rs.getInt(10)));
			 }else{//rs.getDouble(11)>=15
			 double a=Math.round((((rs.getDouble(11)))/10));
			 fp.println("薬剤点数:"+(int)(rs.getInt(10)*a));
			 }
			 break;
			 case "2錠":
			if(2*rs.getDouble(11)<15){
			 fp.println("薬剤点数:"+(1*rs.getInt(10)));
			 }else{//rs.getDouble(11)>=15
			 double a=Math.round(2*(((rs.getDouble(11)))/10));
			 fp.println("薬剤点数:"+(int)(rs.getInt(10)*a));
			 }
			 break;
			 }
			 break;
			 case "１日１回　就寝前":
			switch(rs.getString(8)){
			 case "1錠":
			if(rs.getDouble(11)<15){
			 fp.println("薬剤点数:"+(1*rs.getInt(10)));
			 }else{//rs.getDouble(11)>=15
			 double a=Math.round((((rs.getDouble(11)))/10));
			 fp.println("薬剤点数:"+(int)(rs.getInt(10)*a));
			 }
			 break;
			 case "2錠":
			if(2*rs.getDouble(11)<15){
			 fp.println("薬剤点数:"+(1*rs.getInt(10)));
			 }else{//rs.getDouble(11)>=15
			 double a=Math.round(2*(((rs.getDouble(11)))/10));
			 fp.println("薬剤点数:"+(int)(rs.getInt(10)*a));
			 }
			 break;
			 }
			 break;
			 case "１日２回　朝夕食後":
			switch(rs.getString(8)){
			 case "2錠":
			if(2*rs.getDouble(11)<15){
			 fp.println("薬剤点数:"+(1*rs.getInt(10)));
			 }else{//rs.getDouble(11)>=15
			 double a=Math.round(2*(((rs.getDouble(11)))/10));
			 fp.println("薬剤点数:"+(int)(rs.getInt(10)*a));
			 }
			 break;
			 }
			 break;
			 case "１日３回　毎食後":
			switch(rs.getString(8)){
			 case "3カプセル":
			if(3*rs.getDouble(11)<15){
			 fp.println("薬剤点数:"+(1*rs.getInt(10)));
			 }else{//rs.getDouble(11)>=15
				 double a=Math.round(3*(((rs.getDouble(11)))/10));
				 fp.println("薬剤点数:"+(int)(rs.getInt(10)*a));
			}
			break;
			 case "3錠":
			if(3*rs.getDouble(11)<15){
			 fp.println("薬剤点数:"+(1*rs.getInt(10)));
			 }else{//rs.getDouble(11)>=15
				 double a=Math.round(3*(((rs.getDouble(11)))/10));
				 fp.println("薬剤点数:"+(int)(rs.getInt(10)*a));
			}
			break;
			 }
			 break;
			 }
      	}
      	fp.close();
      	ps.close();
      	con.close();
      	}catch(ClassNotFoundException ee){
			ee.printStackTrace();
		}catch(SQLException ee){
			System.out.println("該当するSQLが存在しません。");
			ee.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("ファイルが見つかりません。");
		} catch (IOException e) {
			System.out.println("入出力エラーです。");
			}
	}}
