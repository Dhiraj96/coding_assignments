//* Author: Dhiraj Amarnani
//* Date: October 29 2017

#include <list>
#include <vector>
#include <sstream>

using namespace std;

//*******************************************************************
//* 
//* This header file declares the methods to be included in the Linked 
//* List template class. Methods include default and copy constructors, 
//* a destructor, get, size, add, remove and toArray methods as well as
//* the following operator overloader: +=. 
//* 
//*******************************************************************
template <class T>
class LinkedList{

	private:
		list<T> myList; 

	public:
		LinkedList();
		~LinkedList(); //destructor
		LinkedList(const LinkedList& otherList); //copy constructor

		T get(int index) const;
		int size() const;
		vector<T> toArray() const;

		void add(T element);
		T remove(int index);

		LinkedList<T>& operator +=(const T& item);
};







//*******************************************************************
//* LinkedList()
//* 
//* The default constructor of our linked list class. It creates a 
//* new instance of our linked list class. It does not take 
//* any parameters and does not return anything.
//* 
//*******************************************************************
template <class T>
LinkedList<T>::LinkedList(){

}


//*******************************************************************
//* LinkedList(const LinkedList& otherList)
//* 
//* The copy constructor of our linked list class. It creates a linked
//* list populated with the elements of the Linked List object passed
//* to it. It does not return anything. 
//* 
//* Pre-conditions: A Linked List object passed as an argument
//*
//*******************************************************************
template <class T>
LinkedList<T>::LinkedList(const LinkedList& otherList){

	myList = otherList.myList;
}


//*******************************************************************
//* int size()
//* 
//* This method determines the length of this instances String object.
//* It does not take any parameters in and returns the int length of
//* this string. 
//*
//* Post-conditions: Int length of this String object
//*******************************************************************
template <class T>
int LinkedList<T>::size() const{

	return myList.size();
}


//*******************************************************************
//* ~LinkedList()
//* 
//* The destructor of our Linked list class. It does not take any 
//* parameters or return anything. 
//* 
//*******************************************************************
template <class T>
LinkedList<T>::~LinkedList(){

}


//*******************************************************************
//* vector<T> toArray()
//* 
//* The toArray method of our Linked list class. Takes no parameters
//* and returns a vector of the elements in our Linked list. 
//* 
//* Post-Conditions: Vector containing all elements in this linked list
//* 
//*******************************************************************
template <class T>
vector<T> LinkedList<T>::toArray() const{

	vector<T> myArray;

	typename list<T>::const_iterator begin = myList.begin();
	typename list<T>::const_iterator end = myList.end();

	myArray.assign(myList.begin(), myList.end());

	return myArray;
}


//*******************************************************************
//* add(T element)
//* 
//* The add method of our Linked list class. It takes an element of
//* template type T to be added to the end of our linked list. 
//* 
//* Pre-Conditions: Element of template type T to be added to Linked
//* 	List
//* 
//*******************************************************************
template <class T>
void LinkedList<T>::add(T element){

	myList.push_back(element);
}

//*******************************************************************
//* T get(int index)
//* 
//* The get method of our Linked list class. It takes an int index
//* and returns the element at the specified position in the list.
//* 
//* Pre-Conditions: Int index of the position we attempt to retrieve
//* element from
//*
//* Post-Conditions: Element of template type T at specified index
//*
//* Throws: Invalid Argument exception if negative index provided or
//* index provided is larger than list
//* 
//*******************************************************************
template <class T>
T LinkedList<T>::get(int index) const{

	stringstream error;

	//if index provided is negative, throw exception
	if(index < 0){

		error << "Invalid Index Provided: " << index << " is less than zero" << endl;
		throw(invalid_argument(error.str().c_str()));
	}

	//if index provided is larger than index of last element in list, throw exception
	if(index >= myList.size()){

		error << "Invalid Index Provided: " << index << " Length of list: " << myList.size() << endl;
		throw(invalid_argument(error.str().c_str()));
	}

	//initialize iterator object and move position to index position
	typename list<T>::const_iterator myIterator = myList.begin();
	for(int i = 0; i < index; ++i){

		myIterator++;
	}

	return *myIterator;

}

//*******************************************************************
//* T remove(int index)
//* 
//* The remove method of our Linked list class. It takes an int index
//* and returns as well as removes the speicifed element in the list.
//* 
//* Pre-Conditions: Int index of the position we attempt to remove
//* element from
//*
//* Post-Conditions: Element of template type T at specified index 
//* remove from. 
//*
//* Throws: Invalid Argument exception if negative index provided or
//* index provided is larger than list
//* 
//*******************************************************************
template <class T>
T LinkedList<T>::remove(int index){

	stringstream error;

	//if index provided is negative, throw exception
	if(index < 0){

		error << "Invalid Index Provided: " << index << " is less than zero" << endl;
		throw(invalid_argument(error.str().c_str()));
	}

	//if index provided is larger than index of last element in list, throw exception
	if(index >= myList.size()){

		error << "Invalid Index Provided: " << index << " Length of list: " << myList.size() << endl;
		throw(invalid_argument(error.str().c_str()));
	}


	//initialize iterator object and move position to index position
	typename list<T>::const_iterator myIterator = myList.begin();
	for(int i = 0; i < index; ++i){

		myIterator++;
	}

	T element = *myIterator;

	//remove element at current iterator position
	myList.erase(myIterator);

	return element;
}

//*******************************************************************
//* LinkedList<T>& operator+=(const T& item)
//* 
//* This method overloads the += assignment operator for objects of
//* the Linked list class
//* 
//* Pre-Conditions: A reference to an instance of the template 
//* element T to be appended to end of our list
//*
//* Post-Conditions: A reference to this Linked Lists new list after
//* the += operator
//* 
//*******************************************************************
template <class T>
LinkedList<T>& LinkedList<T>::operator+=(const T& item){

	myList.push_back(item);
	return (*this);
}



