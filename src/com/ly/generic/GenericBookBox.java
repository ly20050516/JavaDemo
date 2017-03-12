package com.ly.generic;

public class GenericBookBox<T,E> extends GenericBox<Papper, Book>{

	@Override
	public Book process() {
		if(mArrayList.size() > 0) {
			Book book = new Book();
			return book;
		}
		return null;
	}

}
