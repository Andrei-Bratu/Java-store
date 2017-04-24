package shopping;

import java.util.*;

import tema_magazin.Item;

public abstract class ItemList {
	Comparator<Item> c = new mycomp();
    private Node<Item> first = null;
    private Node<Item> last = null;
    private ItemIterator it = new ItemIterator();
    private Item lst = null;
    
    
	public static class Node<Item> {
		private Node<Item>next;
		private Node<Item>prev;
		private Item val;
		
		public Item getVal() {
			return val;
		}

		public Node (Item i){
			next = null;
			prev = null;
			val = i;
		}
	}
	
	public class ItemIterator implements ListIterator<Node<Item>>{

		private Node<Item> it;
		private int ind;
		
		public ItemIterator() {
			it = first;
			ind = 0;
		}
		
		@Override
		public boolean hasNext() {
			if (it.next != null)
				return true;
			else
				return false;
		}
		public boolean hasEl() {
			if (it != null)
				return true;
			else
				return false;
		}

		@Override
		public Node<Item> next() {
			it = it.next;
			ind++;
			return it;
		}

		@Override
		public boolean hasPrevious() {
			if (it.prev != null)
				return true;
			else
				return false;
		}

		@Override
		public Node<Item> previous() {
			it = it.prev;
			ind--;
			return it;
		}

		@Override
		public int nextIndex() {
			return ind + 1;
		}

		@Override
		public int previousIndex() {
			return ind - 1;
		}

		@Override
		public void remove() {
			if (hasPrevious())
				it.prev.next = it.next;
			if (hasNext())
				it.next.prev = it.prev;
			if (it == first)
			{
				first = it.next;
			}
			else if (it == last)
			{
				last = it.prev;
			}
			lst = last.val;
		}

		@Override
		public void set(Node<Item> e) {
			it.next = e;
			e.prev = it;
			e.next = null;
			last = e;
			
		}

		@Override
		public void add(Node<Item> e) {
			if (hasPrevious())
			{
				it.prev.next = e;
				e.prev = it.prev;
			}
			else
			{
				first = e;
				e.prev = null;
			}
			it.prev = e;
			e.next = it;
			
		}
	}
		/* returneaza ultimul item adaugat */
		
	public Item getLst() {
		return lst;
	}

	public  boolean  add ( Item  element ) {

		Node<Item> newNode = new Node<Item>(element);
		it = new ItemIterator();
		lst = element;
	    if (isEmpty())
	    {
	        first = newNode;
	        last = newNode;
			newNode.next = null;
			newNode.prev = null;
	    }else 
	    {
	    	/* se adauga elementele ordonat in lista */
		    while (it.hasNext())
		    {
		    	if (c.compare(it.it.val, element) > 0)
		    	{
		    		it.add(newNode);
		    		return true;
		    	}
		    	it.next();
		    }
		    if (c.compare(it.it.val, element) > 0)
		    	it.add(newNode);
		    else
		    {
		    	it.set(newNode);
		    }
	    }
		return true;
	}
	
	public  Item  getItem ( int index ) {
		it = new ItemIterator();
		while (it.ind != index)
			it.next();
		return it.it.val;
	}
	public  Item  getItembyid ( int id ) {
		it = new ItemIterator();
		while (it.it.val.getId() != id)
			it.next();
		return it.it.val;
	}
	public  Node<Item> getNode ( int  index ){
		it = new ItemIterator();
		while (it.ind != index)
			it.next();
		return it.it;
		
	}
	public  int  indexOf ( Item  item ) {
		it = new ItemIterator();
		while (it.it.val.getId() != item.getId())
			it.next();
		return it.ind;
	}
	public  int  indexOf (Node<Item> node ) {
		it = new ItemIterator();
		while (it.it != node)
			it.next();
		return it.ind;
		
	}
	public  boolean  contains (Node<Item> node ) {
		it = new ItemIterator();
		while (it.hasEl())
		{
			if (it.it == node)
				return true;
			it.next();
		}
			
		return false;
	}
	public  boolean  contains ( Item  item ) {
		it = new ItemIterator();
		while (it.hasEl())
		{
			if (it.it.val.getId() == item.getId())
				return true;
			it.next();
		}
			
		return false;
	}
	public  Item remove ( int  index ) {

		it = new ItemIterator();
		while (it.hasEl())
		{
			if (it.ind == index)
			{
				Node<Item> aux = it.it;
				it.remove();
				return aux.val;
			}
			it.next();
		}
		
		return null;
	}
	public  boolean  remove ( Item  item ) {

		it = new ItemIterator();
		while (it.hasEl())
		{
			if (it.it.val.getId() == item.getId())
			{
				it.remove();
				return true;
			}
			it.next();
		}
		
		return false;
		
	}
	public boolean  removeAll ( Collection <? extends Item> collection ){
		for (tema_magazin.Item i:collection)
			remove(i);
		return true;
		
	}
	public boolean  addAll ( Collection <? extends  Item> c ){
		for (tema_magazin.Item i:c)
			add(i);
		return true;
	}
	public  boolean  isEmpty () {
		return (first == null);
	}
	public ItemIterator listIterator ( int index ) {
		it = new ItemIterator();
		while (it.nextIndex() != index)
			it.next();
		return it;
	}
	public ItemIterator listIterator () {
		it = new ItemIterator();
		return it;
		
	}
	public Double getTotalPrice (){
		double tot = 0;
		it = new ItemIterator();
		while (it.hasEl()){
			tot += it.it.val.getPrice();
			it.next();
		}
		return tot;
	}
	public Vector<Item> getItems ()
	{
		Vector<Item> v = new Vector<>();
		it = new ItemIterator();
		
		while (it.hasEl()){
			v.add(it.it.val);
			it.next();
		}
		return v;
	}

	public class mycomp implements Comparator<Item>{
	
		@Override
		public int compare(Item o1, Item o2) {
			// TODO Auto-generated method stub
			if (o1.getPrice() > o2.getPrice())
				return 1;
			else
				if (o1.getPrice() == o2.getPrice())
					return o1.getName().compareTo(o2.getName());
				else
					return -1;
		}
		
	}
}