import java.io.*;
import java.util.*;

class Decoder 
{
	static Node root = new Node(-1, -1, null, null);
	public static void main(String[] args) throws IOException
	{		
			String file = args[1];
			File code = new File(file);
			FileReader inputFile = new FileReader(code);
			BufferedReader br = new BufferedReader(inputFile);
			String str="";
			while((str=br.readLine())!=null && str.length()>0)
			{
				huffmantree(str);
			}
			String file1 = args[0];
			File encoded = new File(file1);
			String num;
			byte[] data = new byte[(int)encoded.length()];
			FileInputStream fis = new FileInputStream(encoded);
			fis.read(data);
			fis.close();
			System.setProperty("line.separator", "\n");
			File outPutFile = new File("decoded.txt");
			outPutFile.createNewFile();
			BitSet bitset = BitSet.valueOf(data);
			PrintWriter pw = new PrintWriter(outPutFile);
			traverse(bitset, pw);
	}
	
	static void huffmantree(String s){
		String []str = s.split(" ");
		String []path = str[1].split("");
		Node r = root;
		int i = 0;
		while(i<path.length){	
			if(path[i].equals("0")){
				if(r.left!=null){
					r = r.left;
				}
				else{
					r.left = new Node(-1,-1,null,null);
					r = r.left;
				}
			}
			else{
				if(r.right!=null){
					r = r.right;
				}
				else{
					r.right = new Node(-1,-1,null,null);
					r = r.right;
				}
			}
			i++;
		}
		r.data = Integer.parseInt(str[0]);
	}
	
	static void traverse(BitSet bitset, PrintWriter pw)
	{
		int length = bitset.length();
		Node current = root;
		int i =0;
		while(i<=length){
			// bit is 1
			if(bitset.get(i) == true){
				current = current.right;
				if(current.left==null && current.right==null){
					pw.println(""+current.data);
					current=root;
				}
			}
			// bit is 0
			else{
				current = current.left;
				if(current.left==null && current.right==null){
					pw.println(""+current.data);
					current=root;
				}
			}
			i++;
		}
		pw.flush();
		pw.close();
	}
}
class Node{
	int data;
	int frequency;
	Node left;
	Node right;
	
	Node(int d, int f, Node l, Node r){
		this.data = d;
		this.frequency = f;
		this.left = l;
		this.right = r;
	}
}