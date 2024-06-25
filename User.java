import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class User {
	private String viewTime;//再生時間
	private String searchWord;//検索ワード
	private int condition;//最適化する条件
	private Client client;
	
	private String allTime;//最終画面表示用再生時間
	private String URL;//最終画面表示用プレイリストURL
	
	//画面遷移
	public User(Client client) {
		this.client = client;
		Home home = new Home();
	}

//	public static void main(String[] args) {
//		// TODO 自動生成されたメソッド・スタブ
//	}

	//ホーム画面
	class Home extends JFrame implements ActionListener{
		private JLabel MuTimer;//Mu-Timerのラベル
		private JButton generateButton;//プレイリスト生成のボタン
		private Container c;
		private int portNumber;//ポート番号
		
		public Home() {
			setTitle("ホーム画面");//上のタイトル指定
			setSize(800,600);//画面のサイズ指定
			
			c = getContentPane();//フレームのペインを取得
			c.setLayout(null);
			
			//Mu-Timerを生成、配置
			MuTimer = new JLabel("Mu-Timer");
			MuTimer.setFont(new Font("Arial", Font.BOLD, 50));
			MuTimer.setBounds(270,80,250,80);
			c.add(MuTimer);
			
			//プレイリスト生成ボタンを生成、配置
			generateButton = new JButton("プレイリスト生成");
			generateButton.setBounds(265,350,250,80);
			c.add(generateButton);
			generateButton.addActionListener(this);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e) {
			this.setVisible(false);
			SearchFrame1 frame1 = new SearchFrame1();
		}
	}

	//検索画面1
	class SearchFrame1 extends JFrame implements ActionListener{
		private JLabel viewingTimeLabel;//再生時間ラベル
		private JLabel minutes;//分ラベル
		private JLabel searchWords;//検索ワードラベル
		private JLabel warn1;//ジャンルかアーティスト選んでくださいラベル
		private JLabel warnGenre;//検索ワード入力してくださいラベル
		private JLabel warnArtist;//検索ワード入力してくださいラベル
		private JButton next;//次へボタン
		private JTextField genreText;//ジャンルテキストフィールド
		private JTextField artistText;//アーティストテキストフィールド
		private Container c;//コンテナ
		private JRadioButton genre;//ジャンルボタン
		private JRadioButton artist;//アーティストボタン
		private JComboBox time;//再生時間選択プルダウン
		
		public SearchFrame1() {
			setTitle("検索画面1");//上のタイトル指定
			setSize(800,600);//画面のサイズ指定
			
			c = getContentPane();//フレームのペインを取得
			c.setLayout(null);
			
			//再生時間を設定してくださいラベル配置
			viewingTimeLabel = new JLabel("再生時間を設定してください");
			viewingTimeLabel.setBounds(50, 30, 200, 20);
			c.add(viewingTimeLabel);
			
			//分ラベル配置
			minutes = new JLabel("分");
			minutes.setBounds(570, 30, 50, 20);
			c.add(minutes);
			
			//警告1ラベル配置
			warn1 = new JLabel("ジャンルまたはアーティストを選択してください");
			warn1.setBounds(400, 350, 400, 20);
			c.add(warn1);
			warn1.setVisible(false);
			
			//警告ラベル配置(検索ワードをジャンルのテキスト欄に入力してください)
			warnGenre = new JLabel("検索ワードをジャンルのテキスト欄に入力してください");
			warnGenre.setBounds(400, 350, 400, 20);
			c.add(warnGenre);
			warnGenre.setVisible(false);
			
			//警告ラベル配置(検索ワードをアーティストのテキスト欄に入力してください)
			warnArtist = new JLabel("検索ワードをアーティストのテキスト欄に入力してください");
			warnArtist.setBounds(400, 350, 400, 20);
			c.add(warnArtist);
			warnArtist.setVisible(false);
			
			//検索ワードを入力してくださいラベル配置
			searchWords = new JLabel("検索ワードをテキスト欄に入力してください");
			searchWords.setBounds(50, 75, 400, 20);
			c.add(searchWords);
			
			//プルダウン配置
			String[] timeoption = {"3","5","10","15","20","25","30","45","60","90","120","150","180"};
			time = new JComboBox<>(timeoption);
			time.setBounds(500, 30, 50, 20);
			c.add(time);
			
			//ラジオボタン配置
			genre = new JRadioButton("ジャンル", false);
			artist = new JRadioButton("アーティスト", false);
			
			ButtonGroup group = new ButtonGroup();
			group.add(genre);
			group.add(artist);
			
			genre.setBounds(50, 100, 100, 20);
			c.add(genre);
			artist.setBounds(50, 200, 100, 20);
			c.add(artist);
			genre.addActionListener(this);
			artist.addActionListener(this);
			
			//テキストフィールド配置
			genreText = new JTextField();
			artistText = new JTextField();
			
			genreText.setBounds(500, 100, 150, 40);
			artistText.setBounds(500, 200, 150, 40);
			
			c.add(genreText);
			c.add(artistText);
			
			//ボタンが押されないと入力できない
			genreText.setEnabled(false);
			artistText.setEnabled(false);
			
			//次へボタン配置
			next = new JButton("次へ");
			next.setBounds(600,400,150,50);
			c.add(next);
			next.addActionListener(this);
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e) {
			
			boolean status1 = genre.isSelected();//ジャンルのボタン
			boolean status2 = artist.isSelected();//アーティストのボタン
			
			//ラジオボタンが押された方を入力できるようにする
			genreText.setEnabled(status1);
			artistText.setEnabled(status2);
			
			//次へボタンが押されたとき
			if(e.getActionCommand()=="次へ") {
				if(!status1 && !status2) {//ラジオボタンが両方とも押されてないなら
					//ジャンルまたはアーティストを選択してくださいを表示
					warn1.setVisible(true);
					//他の警告文は非表示
					warnGenre.setVisible(false);
					warnArtist.setVisible(false);
					
				}else if( status1 && genreText.getText().isEmpty()) {
					//検索ワードをジャンルのテキスト欄に入力してくださいを表示
					warnGenre.setVisible(true);
					//他の警告文は非表示
					warn1.setVisible(false);
					warnArtist.setVisible(false);//検索ワードが空白なら警告文表示
					
				}else if( status2 && artistText.getText().isEmpty()) {
					//検索ワードをジャンルのアーティスト欄に入力してくださいを表示
					warnArtist.setVisible(true);
					//他の警告文は非表示
					warn1.setVisible(false);
					warnGenre.setVisible(false);
					
				}else if(status1 && !(genreText.getText().isEmpty())){//検索ワードが空白でないなら
					
					viewTime  = (String)time.getSelectedItem();//再生時間取得
					searchWord = genreText.getText();//検索ワード取得
					
					this.setVisible(false);//検索画面①を不可視に
					SearchFrame2 frame2 = new SearchFrame2();//検索画面②へ
					
				}else if(status2 && !(artistText.getText().isEmpty())) {//検索ワードが空白でないなら
					
					viewTime  = (String)time.getSelectedItem();//再生時間取得
					searchWord = artistText.getText();//検索ワード取得
					
					this.setVisible(false);
					SearchFrame2 frame2 = new SearchFrame2();
					
				}
				
			}
		}
		
	}

	//検索画面2
	class SearchFrame2 extends JFrame implements ActionListener{
		private JLabel text;//どんなプレイリストを生成しますか？ラベル
		private JLabel warn;//警告文ラベル
		private JRadioButton totalReview;//総再生回数多めボタン
		private JRadioButton moreSongs;//曲数多めボタン
		private JRadioButton fewerSongs;//曲数少なめボタン
		private JRadioButton justTime;//総再生時間時間ぴったりボタン
		private Container c;//コンテナ
		private JButton generate;//生成ボタン
		private JButton back;//戻るボタン
		
		SearchFrame2(){
			setTitle("検索画面2");//上のタイトル指定
			setSize(800,600);//画面のサイズ指定
			
			c = getContentPane();//フレームのペインを取得
			c.setLayout(null);
			
			text = new JLabel("どんなプレイリストを生成しますか？");
			text.setBounds(50, 75, 250, 20);
			c.add(text);
			
			//ラジオボタン配置
			totalReview = new JRadioButton("総再生回数多め", false);
			moreSongs = new JRadioButton("曲数多め", false);
			fewerSongs = new JRadioButton("曲数少なめ", false);
			justTime = new JRadioButton("総再生時間ぴったり", false);
			
			ButtonGroup group = new ButtonGroup();
			group.add(totalReview);
			group.add(moreSongs);
			group.add(fewerSongs);
			group.add(justTime);
			
			totalReview.setBounds(50, 100, 150, 20);
			c.add(totalReview);
			moreSongs.setBounds(50, 150, 100, 20);
			c.add(moreSongs);
			fewerSongs.setBounds(50, 200, 100, 20);
			c.add(fewerSongs);
			justTime.setBounds(50, 250, 150, 20);
			c.add(justTime);
			
			//警告ラベル
			warn = new JLabel("どれかボタンを選択してください");
			warn.setBounds(550, 350, 250, 20);
			c.add(warn);
			warn.setVisible(false);
			
			//戻るボタン配置
			back = new JButton("戻る");
			back.setBounds(50,400,150,50);
			c.add(back);
			back.addActionListener(this);
			
			//生成ボタン配置
			generate = new JButton("生成");
			generate.setBounds(600,400,150,50);
			c.add(generate);
			generate.addActionListener(this);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e) {
			
			boolean status1 = totalReview.isSelected();//総再生回数多めのボタン
			boolean status2 = moreSongs.isSelected();//曲数多めのボタン
			boolean status3 = fewerSongs.isSelected();//曲数少なめのボタン
			boolean status4 = justTime.isSelected();//総再生時間ぴったりのボタン
			
			
			if(e.getActionCommand()=="戻る") {
				SearchFrame1 frame1 = new SearchFrame1();
				this.setVisible(false);
			}else if(e.getActionCommand() == "生成") {
				if(status1 || status2 || status3 || status4) {//ラジオボタンがどれか推されているなら
					//警告文非表示
					warn.setVisible(false);
					
					//最適化条件取得
					if(status1) {
						condition = 1;
					}else if(status2) {
						condition = 2;
					}else if(status3) {
						condition = 3;
					}else if(status4) {
						condition = 4;
					}
					
					//待機画面表示
					Wating wating = new Wating();
					this.setVisible(false);
				}else {
					//警告文表示
					warn.setVisible(true);
				}
				
			}
		}
	}

	class Wating extends JFrame{
		private JLabel generateNowLabel;//生成していますラベル
		private JLabel watingLabel;//しばらくお待ちくださいラベル
		private Container c;//コンテナ
		
		public Wating() {
			setTitle("生成待機画面");//上のタイトル指定
			setSize(800,600);//画面のサイズ指定
			
			c = getContentPane();//フレームのペインを取得
			c.setLayout(null);
			
			generateNowLabel = new JLabel("プレイリストを生成しています");
			generateNowLabel.setBounds(300, 75, 250, 20);
			c.add(generateNowLabel);
			
			watingLabel = new JLabel("しばらくお待ちください");
			watingLabel.setBounds(320, 100, 250, 20);
			c.add(watingLabel);
			
			client.userReceive(viewTime, searchWord,condition);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
			playList list = new playList();
			setVisible(false);
		}
		
	}
	
	class playList extends JFrame implements ActionListener{
		private Container c;//コンテナ
		 private JScrollPane scrollPane;
		private JTextArea playlistTextArea;
		private JLabel yourPlayList;//あなたのプレイリストラベル
		private JLabel allViewTime;//総再生時間テキストラベル
		private JLabel time;//総再生時間を表示するラベル
		private JLabel playListURL;//生成したプレイリストのURL
		private JButton change;//検索条件を変えるボタン
		
		
		playList(){
			setTitle("プレイリスト");//上のタイトル指定
			setSize(800,600);//画面のサイズ指定
			
			c = getContentPane();//フレームのペインを取得
			c.setLayout(null);
			
			//ラベル配置
			yourPlayList = new JLabel("あなたのプレイリスト");
			yourPlayList.setBounds(50, 20, 250, 20);
			c.add(yourPlayList);
			
			allViewTime = new JLabel("総再生時間 : ");
			allViewTime.setBounds(600, 20, 250, 20);
			c.add(allViewTime);
			
			
			//プレイリスト表示エリア
		    playlistTextArea = new JTextArea();
	        playlistTextArea.setLineWrap(true);
	        playlistTextArea.setWrapStyleWord(true);
	        playlistTextArea.setEditable(false);
	         
	        // 仮のテキストを設定
	        StringBuilder sb = new StringBuilder();
	        for(int i=0;i<100;i++) {
	         	 sb.append("Song"+i+"\n");
	        }

	        playlistTextArea.setText(sb.toString());

	        //スクロールバー
	        scrollPane = new JScrollPane(playlistTextArea);
	        scrollPane.setBounds(50, 50, 700, 400);
	        c.add(scrollPane);

			//検索条件を変えるボタン
			change = new JButton("検索条件を変える");
			change.setBounds(600,500,150,50);
			c.add(change);
			change.addActionListener(this);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e) {
			SearchFrame1 frame1 = new SearchFrame1();
			this.setVisible(false);
		}
		
	}
}
