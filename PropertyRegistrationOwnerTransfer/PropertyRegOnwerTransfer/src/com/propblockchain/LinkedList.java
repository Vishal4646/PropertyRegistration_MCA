package com.propblockchain;

public class LinkedList {
	Block start;
	Block end;
	public int size ;
	public LinkedList()

	{

		start = null;

		end = null;

		size = 0;

	}

	public void insertAtEnd(Block block)

	{

		// Node nptr = new Node(block,null);

		size++;

		if (start == null)

		{

			start = block;

			end = start;

		}

		else

		{

//			end.setLink(block);

			end = block;

		}

	}

}
