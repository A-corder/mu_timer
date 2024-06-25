import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client{
    
    String IPa;
    int port;
    int time;//時間
    String keyword;//検索ワード
    int condition;//最適化条件
    
    Client(String IPa, int port){
        this.IPa = IPa;
        this.port = 10001;
        User user = new User(this);
    }
    
    //IPアドレスとポート番号を使ってソケットを接続
    public Socket setConnect(String IPa, int port){
        
    	Socket socket = null;
        try{
            socket = new Socket(IPa, port);
            return socket;
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
        
    }
    public void userReceive(String time, String keyword, int condition) {
    	this.time = Integer.parseInt(time);
    	this.keyword = keyword;
    	this.condition = condition;
    	
    	System.out.println("時間:"+this.time);
    	System.out.println("検索ワード:"+this.keyword);
    	System.out.println("最適化条件:"+this.condition);
    }
    
    public void sendOperation(BufferedWriter out){
        
        try{
        	out.write(this.time);
        	out.write(this.keyword);
        	out.write(this.condition);
        }catch(Exception e){
            System.out.println(e.toString());
        }
        
    }
    
    //URL・曲名を受け取る
    ArrayList<String> getMessage(BufferedReader in){
        
    	int i = 0;
    	ArrayList<String> list = new ArrayList<String>();
    	
    	try {
    		while(list.add(in.readLine())) {
    			System.out.println("message : "+list.get(i)+"");
    			i++;
    		}
    	}catch(Exception e) {
    		System.out.println(e.toString());
    	}
    	
    	return list;
    }
    
	public static void main(String[] args) {
		
		//サーバープログラム用のソケット・入出力バッファ
		Socket socket = null;
		BufferedWriter out = null;
		BufferedReader in = null;
		Client client = new Client("aaa", 10001);
		
		try {
			//ソケットの接続試行。IPa、portは別で指定
			socket = client.setConnect("IP",10001);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//再生時間time, 検索キーワードword, 条件指定order
			//ユーザープログラムからの入力を受け取る
			String time = null;
    		String word = null;
    		String order = null;
			
			while(time.equals(null)||word.equals(null)||order.equals(null)) {
				//time = user.sendMessage(frame1.viewTime);
				
				//word = user.sendMessage(frame1.searchWord);
				
				//if(frame2.totalReview.isSelected())
				//	order = user.sendMessage("総再生回数多め");
				//else if(frame2.moreSongs.isSelected())
				//	order = user.sendMessage("曲数多め");
				//else if(frame2.fewSongs.isSelected())
				//	order = user.sendMessage("曲数少なめ");
				//else if(frame2.justTime.isSelected())
				//	order = user.sendMessage("総再生時間ぴったり");
			}
			
			//サーバー側へユーザープログラムの入力を送信
			client.sendOperation(out);
			
			//最終的にサーバー側からURLを受け取る処理。ユーザープログラムに投げる
			client.getMessage(in);
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			try {
				//ソケットとバッファを閉じる
				in.close();
				out.close();
				socket.close();
			}catch(Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}