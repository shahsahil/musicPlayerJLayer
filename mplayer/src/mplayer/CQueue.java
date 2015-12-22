package mplayer;
public class CQueue
{
  private String[] q;
  int f,r;
  private int size;
  int count;  

  public CQueue(int sz)
  {
    q=new String[sz];
    size=sz;
    f=r=-1;
    count=0;
  }
  
  public void insert(String x)
  {
   
   if(f==(r+1)%size)
     System.out.println("Queue full");
   else
    {
      r=(r+1)%size;
      q[r]=x;
      if(f==-1)//if x is the first element
        f=0;
      count++;
     }
   }
   
  public String remove()
  {
    String x="";
    if(f==-1)
     System.out.println("Queue empty");
    else
     {
       x=q[f];
       if(f==r)//only one element
       {
         f=-1;r=-1;
       }
       else
           f=(f+1)%size; 
       count--; 
    }
     return x;
   }
        
   public int getcount()
   {
     return count;
   }
 
   public void display()
   {
    int i;
    if(f==-1)
      System.out.println("Empty Queue");
    else
    {
     if(f>r)
     {
      for(i=f;i<size;i++)
         System.out.print(" "+q[i]);

      for(i=0;i<=r;++i)
        System.out.print(" "+q[i]);
     }
     else
     {
       for(i=f;i<=r;i++)
         System.out.print(" "+q[i]);  
     } 
    }
  }
   public String[] playall()
   {
    int i;
    String temp[] = new String[getcount()];
    if(f==-1){
      System.out.println("Empty Queue");
      temp[0] = null;
      
    }
    else
    {
     if(f>r)
     {
      for(i=f;i<size;i++)
         temp[i] =q[i];

      for(i=0;i<=r;++i)
    	  temp[i]= q[i];
     }
     else
     {
       for(i=f;i<=r;i++)
    	   temp[i] =q[i];  
     } 
    }
    return temp;
  }
 
  public String getplay(){
	  String x = q[f];
	  f=(f+1)%count;
	  r=(r+1)%count;
	  return x;
  }

}     
