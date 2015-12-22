package mplayer;

//import java.util.Scanner;

class linkedList {
	node first, last; 
	/*node t, h;
	t=new node();
	h=new node();
	t.setData(0);
	h.setData(0);
	t.setNext(null);
	h.setPrev(null);*/
	
	void add(String v) {
		node p = new node();
		p.setData(v);
		if (first == null){
			/*p.setPrev(h);
			p.setNext(t);*/
			first = p;
			last = p;
		}
		else {
			node q = first;
			while (q.getNext() != null) {
				q = q.getNext();
			}
			q.setNext(p);
			p.setPrev(q);
			//p.setNext(t);
			last = p;
		}
	}
	
	int sizeOf()
	{
		int c=0;
		if(first!=null)
		{
			node q = first;
			while (q.getNext() != null) {
				q = q.getNext();
				c++;
			}
		}
		return c;
	}
	
	boolean isEmpty()
	{
		if( first== null)
			return true;
		return false;
	}

	/*void addM(String v, int pos) {
		int c = 1;
		node p = new node();
		p.setData(v);
		node q = first;
		if(pos<sizeOf())
		{
		while (c != pos - 1 && q.getNext() != null) {
			q = q.getNext();
			c++;
		}
		
		p.setNext(q.getNext());
		q.setNext(p);
		}
		else if(pos == sizeOf())
			addL(v);
		else 
			System.out.println("position not present");	
	}*/

	String search(String v) {
		node q = first;
		int pos = 1;
		while (q != null) {
			String da;
			da = q.getData().substring(0,q.getData().length()-4 );
			//System.out.println(q.getData());
			System.out.println(da);
			System.out.println(q.getData());
			if (v.equalsIgnoreCase(da)){
				System.out.println("found");
				return q.getData();
			}
			pos++;
			q = q.getNext();
		}
		return "NF";
	}
	node searchN(String v) {
		node q = first;
		int pos = 1;
		while (q != null) {
			//System.out.println(q.getData());
			if (v.equals(q.getData())){
				return q;
			}
			pos++;
			q = q.getNext();
		}
		return null;
	}
/*
	void delete(String v) {
		node q = first;
		if (v == first.getData())
			first = first.getNext();
		else {
			while (q != null) {
				if (q.getNext().getData() == v)
					break;
				q = q.getNext();
			}
			System.out.println("node Deleted : " + v);
			q.setNext(q.getNext().getNext());
		}
	}*/

	/*void addB(int v) {
		node p = new node();
		p.setData(v);
		p.setNext(first);
		first = p;
	}

	void addL(int v) {
		node p = new node();
		p.setData(v);
		node q = first;
		if(isEmpty() == false)
		{
		while (q.getNext() != null) {
				q = q.getNext();
			}
		
		q.setNext(p);
		p.setNext(null);
		}
		else 
			addB(v);
	}*/
	/*boolean isPresent(String v)
	{
		node q = first;
		if(isEmpty() == false)
		{
		if(v==q.getData())
				return true; 
		while (q.getNext() != null)
		{
			
			q = q.getNext();
			if(v==q.getData())
				return true; 
		}
		}
		return false;
	}
	void display() {
		node temp = first;
		while (temp != null) {
			System.out.print(temp.getData() + "->");
			temp = temp.getNext();
		}
	}*/
	String[] toArray() {
		node temp = first;
		int i = 0;
		String[] arr = new String[sizeOf()+1];
		while (temp != null) {
			arr[i] = temp.getData();
			//System.out.print(temp.getData() + "->");
			temp = temp.getNext();
			i++;
		}
		return arr;
	}
}

