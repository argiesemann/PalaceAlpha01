package com.example.palacealpha01.GameFramework.palace;

/**
 * @author Maximilian
 * <p>
 * This class is a Stack data structure, with some extra methods specifically designed for our needs.
 * We plan to use this for the discard pile, because the discard pile is most easily represented as
 * as stack.
 */
public class Stack
{
	/**
	 * @author Maximilian
	 * <p>
	 * This class is only used inside the Stack.java class. The stack object created by the Stack.java
	 * class is essentially a linked list where the nodes are always prepended at the head of the list.
	 * Therefore, a node class was needed.
	 */
	private class StackNode
	{
		private Pair data;
		private StackNode next;

		/**
		 * default constructor for the StackNode class
		 * @param data
		 */
		public StackNode(Pair data)
		{
			this.data = data;
			this.next = null;
		}//END: StackNode() constructor

		public boolean equals(Object obj)
		{
			if (! (obj instanceof StackNode))
				return false;

			if (! this.data.equals(((StackNode) obj).data))
				return false;

			return true;
		}
	}//END: StackNode class

	private StackNode head; // This is the head/top of the Stack. It is the first Node in the stack's linked list.

	/**
	 * default constructor for the Stack.java class
	 */
	public Stack()
	{
		this.head = null;
	}//END: Stack() constructor

	/**
	 * copy constructor for the Stack.java class
	 * This is the wrapper method for the copy_constructor() recursive method.
	 * @param that
	 */
	public Stack(Stack that)
	{
		this.head = null;
		copy_constructor(that.head);
	}//END: Stack() copy constructor

	/**
	 * This is a recursive method.
	 * @param current
	 */
	private void copy_constructor(StackNode current)
	{
		if (current == null)
			return;   // NOTE: Exit recursion loop here.
		copy_constructor(current.next);   // NOTE: The is the only recursive call.

		this.push(current.data);
	}//END: copy_constructor() recursive method

	public void push(Pair data)
	{
		StackNode new_head = new StackNode(data);
		new_head.next = this.head;
		this.head = new_head;
	}//END: push() method

	public Pair pop()
	{
		if (this.head == null)
			return null;
		Pair data = this.head.data;
		this.head = this.head.next;
		return data;
	}//END: pop() method

	public Pair peek()
	{
		if (this.head == null)
			return null;
		return this.head.data;
	}//END: peek() method

	public boolean are_next_four_equal()
	{
		final int FOUR = 4;
		if (this.head == null)
			return false;
		StackNode current = this.head;
		for (int i = 1; i <= (FOUR - 1); i++)
		{
			if (current.next == null)
				return false;
			if (current.data != current.next.data)
				return false;
			current = current.next;
		}
		return true;
	}//END: are_next_four_equal() method

	public boolean is_empty()
	{
		return (this.head == null);
	}//END: is_empty() method

	public void clear()
	{
		while (! this.is_empty())
			this.pop();
	}//END: clear() method

	/**
	 * This is the wrapper function for the toString() recursive method.
	 * @return
	 */
	@Override
	public String toString()
	{
		String s = "";
		to_string_rec(s, this.head);
		return s;
	}

	/**
	 * This is a wrapper function for the equals_rec() method.
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (! (obj instanceof Stack))
			return false;

		return equals_rec(this.head, ((Stack) obj).head);
	}

	/**
	 * This is a recursive function.
	 * @param this_current
	 * @param that_current
	 * @return
	 */
	private static boolean equals_rec(StackNode this_current, StackNode that_current)
	{
		// if both Stacks are empty, or both Stacks have the same number of StackNodes
		if (this_current == null &&
			that_current == null)
			return true;    // NOTE: recursive loop exit

		// if the Stacks have differing numbers of StackNodes
		if ( (this_current == null) ^ (that_current == null) )      // X-OR logic operator
			return false;   // NOTE: recursive loop exit

		// if at any point, both StackNode's data is not equal
		if (! this_current.data.equals(that_current.data))
			return false;   // NOTE: recursive loop exit

		return equals_rec(this_current.next, that_current.next);    // NOTE: recurse on the next StackNodes
																	//       of both Stacks
	}

	/**
	 * This is a recursive method.
	 * @param s
	 * @param current
	 */
	private void to_string_rec(String s, StackNode current)
	{
		if (current == null)
			return;     // NOTE: we exit recursion loop here

		s.concat(current.data.toString() + "\n");

		to_string_rec(s, current.next);     // NOTE: this is the single recursive call within this function
	}//END: to_string() recursive method

	/**
	 * This is the wrapper method for the size_rec() recursive method.
	 * @return
	 */
	public int size()
	{
		return size_rec(this.head);
	}

	/**
	 * This is a recursive method.
	 * @param current
	 * @return
	 */
	private int size_rec(StackNode current)
	{
		if (current == null)
			return 0;
		return 1 + size_rec(current.next);
	}
}//END: Stack class
