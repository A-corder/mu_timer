import java.util.ArrayList;

public class sort {

	public static void main(String[] args) {
		
		//ここから先は他のクラスから取得するデータ
		
		int N=16;
		
		setArray list=new setArray(N);
		
		list.add("a","Ore",200,100,"id");
		list.add("b","Ore",210,100,"id");
		list.add("c","Ore",220,100,"id");
		list.add("d","Ore",230,100,"id");
		list.add("e","Ore",200,110,"id");
		list.add("f","Ore",210,110,"id");
		list.add("g","Ore",220,110,"id");
		list.add("h","Ore",230,110,"id");
		list.add("i","Ore",200,120,"id");
		list.add("j","Ore",210,120,"id");
		list.add("k","Ore",220,120,"id");
		list.add("l","Ore",230,120,"id");
		list.add("m","Ore",200,130,"id");
		list.add("n","Ore",210,130,"id");
		list.add("o","Ore",220,130,"id");
		list.add("p","Ore",230,130,"id");	//曲タイトル アーティスト 時間 評価値 idアドレス
		
		int maxtime=2000;
		
		String condition="time";	//時間：time   その他:nontime
		
		//ここまでが他のクラスから取得するデータ
		
		int array[]=new int[N];
		array=clear(array);
		list.update(array);
		
		make(list,array,maxtime,0,condition);
		
		list.print();
		
		list.delete();
		
		list.print();
		
	}
	
	public static void make(setArray list, int[] array,int maxtime, int i,String condition){
		
		if(condition=="nontime") {
			array[i]=1;
			if(list.calculate(array,2)<(Integer)maxtime*1.1) {
				if(list.calculate(array,3)>list.calculate(list.maxarray,3)&&list.calculate(array,2)>(Integer)maxtime*0.9) {
					list.update(array);
				}
				if(i+1<list.size()) {
					make(list,array,maxtime,i+1,condition);
				}
			}
			array[i]=0;
			if(i+1<list.size()) {
				make(list,array,maxtime,i+1,condition);
			}
		}
		else if(condition=="time") {
			array[i]=1;
			if(list.calculate(array,2)<(Integer)maxtime*1.1) {
				if(Math.abs(list.calculate(array,2)-maxtime)<=Math.abs(list.calculate(list.maxarray,2)-maxtime)) {
					if(list.calculate(array,2)>(Integer)maxtime*0.9) {
						if(list.calculate(array,3)>=list.calculate(list.maxarray,3)) {
							list.update(array);
						}
					}
				}
				if(i+1<list.size()) {
					make(list,array,maxtime,i+1,condition);
				}
			}
			array[i]=0;
			if(i+1<list.size()) {
				make(list,array,maxtime,i+1,condition);
			}
		}
	}

	public static int[] clear(int[] array) {
		int i;
		for(i=0;i<array.length;i=i+1) {
			array[i]=0;
		}
		return array;
	}
}


class setArray{
	
	static ArrayList<ArrayList<Object>> Arraylist = new ArrayList<ArrayList<Object>>();
	int[] maxarray;	
	
	setArray(int N){
		this.maxarray=new int[N];	
	}
	
	public void add(String name,String artist,int time,int score ,String id) {
		
		ArrayList<Object> array=new ArrayList<Object>();
		array.add(name);
		array.add(artist);
		array.add(time);
		array.add(score);
		array.add(id);
		
		Arraylist.add(array); 
	}
	
	public int calculate(int[] array,int n){
		int ret=0,i;
		
		for(i=0;i<Arraylist.size();i=i+1) {
			if(array[i]==1) {
				ret=ret+(Integer)Arraylist.get(i).get(n);
			}
		}
		return ret;
	}
	
	public void print() {
		 for (Object obj : Arraylist){
			 System.out.println(obj);
	     }
		 System.out.println();
		 
		 int sum=0,time=0;
		 for (ArrayList<Object> obj : Arraylist){
			 time=time+(Integer)obj.get(2);
			 sum=sum+(Integer)obj.get(3);
	     }
		 System.out.println("総時間："+time);
		 System.out.println("評価値："+sum);
		 System.out.println("-------------------");
		 System.out.println();
	}
	
	public int[] maxarray() {
		return maxarray;
	}
	
	public void update(int[] newlist){
		int i;
		for(i=0;i<newlist.length;i=i+1){
			maxarray[i]=newlist[i];
		}
	}
	
	public void delete() {
		int i;
		for(i=maxarray.length-1;i>=0;i=i-1) {
			if(maxarray[i]==0) {
				Arraylist.remove(i);
				
			}
		}
	}
	
	public int size() {
		return Arraylist.size();
	}
}