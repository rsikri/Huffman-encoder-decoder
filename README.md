import java.util.*;
import java.io.*;
class FourWayHeap 
{
    HuffmanNode head = null;
    
    ArrayList<HuffmanNode> fourWayHeap = new ArrayList<HuffmanNode>();  

    public FourWayHeap()
    {
       fourWayHeap.add(new HuffmanNode());
       fourWayHeap.add(new HuffmanNode());
       fourWayHeap.add(new HuffmanNode());
    }
  
  public void insert(HuffmanNode node)
    {
        fourWayHeap.add(node);
        heapifyUp(fourWayHeap.size()-1);
    }

    public void heapifyUp(int child)
    {
        int parent = ((child-4)/4) + 3;
        
        HuffmanNode parentNode = fourWayHeap.get(parent);
        HuffmanNode childNode = fourWayHeap.get(child);
        
        if(parent > 3)
        {
            if(parentNode.frequency >= childNode.frequency)
            {
                fourWayHeap.set(parent, childNode);         
                fourWayHeap.set(child,parentNode);
                heapifyUp(parent);
            }
        }
    }
    
    public void heapifyDown(int index)
    {
      if(index < fourWayHeap.size())
        {
           int child;
           int min = index;
           
           for(int i = 0; i < 4; i++)
           {
               child = 4*(index-3) + i+3;
                if(child >= fourWayHeap.size())
                   break;
               if(fourWayHeap.get(child).frequency <= fourWayHeap.get(min).frequency)
                   min = child;
           }         
               if(min != index)
               {  
                   HuffmanNode minValue = fourWayHeap.get(min);
                   HuffmanNode parentValue = fourWayHeap.get(index);
                   fourWayHeap.set(min, parentValue);
                   fourWayHeap.set(index,minValue);
                   heapifyDown(min);
               }
            }
         }
    
        public HuffmanNode removeMin()
        {
        if(fourWayHeap.size()>4)
        {
          HuffmanNode a1= fourWayHeap.get(3);
          fourWayHeap.set(3, fourWayHeap.get(fourWayHeap.size()-1));
          fourWayHeap.remove(fourWayHeap.size()-1);
          heapifyDown(3);
          return a1;
        }
        else
        {
			   if(fourWayHeap.size()==4)
         {
            HuffmanNode a1= fourWayHeap.get(3);
            fourWayHeap.remove(3);
            return a1;
			   }
         else
         {
				  return null;
			   }

        }
      }
    }
class Encoder{
	public static void buildCodeTable(HuffmanNode root, String code, PrintWriter outFile)
 	{
   
   if(root.left!=null || root.right!=null)
   {
	   String rightchild = code + "1";
     String leftchild = code + "0";
     
     if(root.left!=null)
     {
       buildCodeTable(root.left, leftchild, outFile);
     }
     if(root.right!=null)
     {
       buildCodeTable(root.right, rightchild, outFile);
     }
   }
   else
   {
     outFile.println(root.data+" "+code);
	   outFile.flush();
   }
 }

	public static void main(String[] args)
	{
		

		try
            {
            String file1 = args[0];
            HashMap<Integer, Integer> freqtab = new HashMap<>();
            FileReader inputFile = new FileReader(file1); //input
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;
			while ((line = bufferReader.readLine()) != null)
            {
                if(line.length()!=0)
                {
                int i = Integer.parseInt(line);
                if(freqtab.get(i)==null)
                    {
                    freqtab.put(i,1);
                    }
                else
                    {
                    int c = freqtab.get(i);
                    c++;
                    freqtab.put(i,c);
                    }
                }
            }
            Iterator it = freqtab.entrySet().iterator();
            FileWriter fw= new FileWriter("freq.txt");
            BufferedWriter bw= new BufferedWriter(fw);
			
				    while (it.hasNext()) {
				        Map.Entry pair = (Map.Entry)it.next();
				        int a1 = Integer.parseInt(pair.getKey().toString());
				        int a2 = Integer.parseInt(pair.getValue().toString());
                        bw.write(a1+" "+a2);
                        bw.write('\n');
				    }
                    bw.close();
            }catch(Exception e){
			e.printStackTrace();
			System.out.println("Error while reading file line by line:100003" + e.getMessage());                      
		}
		try{   
    //long startTime = System.currentTimeMillis();
    FourWayHeap pm = new FourWayHeap();
    FileReader inputFile = new FileReader("freq.txt"); 
	  BufferedReader bufferReader = new BufferedReader(inputFile);
    String line;
    while ((line = bufferReader.readLine()) != null)
    {
      String[] arr = line.split(" ");
      HuffmanNode nodeob= new HuffmanNode();
      nodeob.frequency=Integer.parseInt(arr[1]);
      nodeob.data=Integer.parseInt(arr[0]);
      pm.insert(nodeob);
    }
  HuffmanNode pn = new HuffmanNode();
  HuffmanNode pn1=new HuffmanNode();
  HuffmanNode t=new HuffmanNode();

while(((pn=pm.removeMin())!=null)&&((pn1=pm.removeMin())!=null))
{
  HuffmanNode c=new HuffmanNode();
  c.frequency=pn.frequency+pn1.frequency;
  c.left=pn;
  c.right=pn1;
  pm.insert(c);
  t=c;
}
             
String codeTableFile = "code_table.txt";
PrintWriter outFile = new PrintWriter(new FileOutputStream(new File(codeTableFile)));
buildCodeTable(t,"",outFile);
int arr[]=new int[1000];
int top = 0;
//long endTime   = System.currentTimeMillis();
//long totalTime = endTime - startTime;
//System.out.println(totalTime);
}
catch(Exception e)
{
	e.printStackTrace();
}
		try
		{
		HashMap<Integer,String> hcodes = new HashMap<>();
		FileReader inputFile = new FileReader("code_table.txt"); 
	    BufferedReader bufferReader = new BufferedReader(inputFile);
        String line="";
		while ((line = bufferReader.readLine()) != null){
			if(line.length()!=0)
			{
			String[] arr = line.split(" ");
			int a1 =Integer.parseInt(arr[0]);
			String a2 =arr[1];
			hcodes.put(a1,a2);
			}
		}
		
		String file = args[0];
		FileReader inputFile1 = new FileReader(file); 
	    BufferedReader bufferReader1 = new BufferedReader(inputFile1);
		BitSet bitbuff = new BitSet();
        line="";
		int counter = 0;
		while ((line = bufferReader1.readLine()) != null)
		{
			if(line.length()!=0){
			int key = Integer.parseInt(line);
			if(hcodes.get(key)!=null){
				char[] c = hcodes.get(key).toCharArray();
				for(char i : c){
					if(i=='1')
						bitbuff.set(counter);
					else
						bitbuff.clear(counter);
					counter++;
				}
			}
			}
		}
		System.out.println(bitbuff.length());
		byte[] bytearr = bitbuff.toByteArray();
		FileOutputStream fout=new FileOutputStream("encoded.bin");    
		fout.write(bytearr);    
		fout.close();    
		System.out.println("success...");    
		}catch(Exception e)
		{
			e.printStackTrace();
        }
	}
}
class HuffmanNode 
{
    public int data;
    public int frequency;
    public HuffmanNode left;
    public HuffmanNode right;

public HuffmanNode()
{
    this.data=-1;
    this.frequency=-1;
    this.left = null;
    this.right = null;
}
}

