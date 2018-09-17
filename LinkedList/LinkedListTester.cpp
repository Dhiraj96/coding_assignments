//* Author: Dhiraj Amarnani
//* Date: October 29 2017

#include <iostream>
#include "LinkedList.h"
#include <sstream>

using namespace std;

//*******************************************************************
//* int main()
//* 
//* This main method provides various tests for the Linked List 
//* template class methods and constructors. 
//* 
//* Pre-conditions:  None
//*
//* Post-conditions: prints various test results of each functionality 
//* in the Linked List template class to standard output
//*******************************************************************
int main(){

	cout << "+++++++++++++++++++++++++++++++++++++++++++++++++" << endl;
    cout << endl << "Testing default constructor(Passing no parameter):" << endl;

    LinkedList<int> list1;
    vector<int> array1 = list1.toArray();


    cout << "\tSize: "  << list1.size() << " Expected: [0]" << endl;
    cout << "\tElements: ";
    for(int i = 0; i < list1.size(); ++i){
    	cout << " " << array1[i];
    }
    cout << " Expected: " << endl;


    cout << "+++++++++++++++++++++++++++++++++++++++++++++++++" << endl;
    cout << endl << "Testing copy constructor(Passing empty list):" << endl;

    LinkedList<int> list2(list1);
    vector<int> array2 = list2.toArray();

    cout << "\tSize: "  << list2.size() << " Expected: [0]" << endl;
    cout << "\tElements: ";
    for(int i = 0; i < list2.size(); ++i){
    	cout << " " << array2[i];
    }
    cout << " Expected: " << endl;

    cout << endl << "Testing copy constructor(Passing non-empty list):" << endl;

    list2.add(5);
    list2.add(6);

    LinkedList<int> list3(list2);
    vector<int> array3 = list3.toArray();

    cout << "\tSize: "  << list3.size() << " Expected: [2]" << endl;
    cout << "\tElements: ";
    for(int i = 0; i < list3.size(); ++i){
    	cout << " " << array3[i];
    }
    cout << " Expected: 5 6" << endl;

    cout << "+++++++++++++++++++++++++++++++++++++++++++++++++" << endl;

    cout << endl << "Testing get method(Invalid index on empty list(index to large)):" << endl;

    try{

    	int element = list1.get(0);
    }
    catch(const exception& error){

    	cout << error.what() << endl;
    }

    cout << endl << "Testing get method(Invalid index on empty list(index negative)):" << endl;

    try{

    	int element = list1.get(-1);
    }
    catch(const exception& error){

    	cout << error.what() << endl;
    }

    cout << endl << "Testing get method(Valid index on non-empty list (head)):" << endl;

    int element1 = list2.get(0);
    cout << "\t Element: " << element1 << " Expected: " << 5 << endl;

    cout << endl << "Testing get method(Valid index on non-empty list(tail)):" << endl;

    int element2 = list2.get(1);
    cout << "\t Element: " << element2 << " Expected: " << 6 << endl;

    cout << endl << "Testing get method(Valid index on non-empty list(middle)):" << endl;

    list3.add(3);

    int element3 = list3.get(1);
    cout << "\t Element: " << element3 << " Expected: " << 6 << endl;

    cout << endl << "Testing get method(Invalid index on non-empty list(negative index)):" << endl;

    try{

    	int element = list2.get(-1);
    }
    catch(const exception& error){

    	cout << error.what() << endl;
    }

    cout << endl << "Testing get method(Invalid index on non-empty list(index too large)):" << endl;

    try{

    	int element = list2.get(3);
    }
    catch(const exception& error){

    	cout << error.what() << endl;
    }

    cout << "+++++++++++++++++++++++++++++++++++++++++++++++++" << endl;

    cout << endl << "Testing remove method(Invalid Index on empty List(negative index)):" << endl;

    try{

    	int element = list1.remove(-1);
    }
    catch(const exception& error){

    	cout << error.what() << endl;
    }

    cout << endl << "Testing remove method(Invalid Index on empty list(index too large)):" << endl;

    try{

    	int element = list1.remove(0);
    }
    catch(const exception& error){

    	cout << error.what() << endl;
    }

    cout << endl << "Testing remove method(Valid Index on non-empty list(head)):" << endl;

    array2 = list2.toArray();

    cout << "Before Remove Called: " << endl;
    cout << "\tSize: "  << list2.size() << " Expected: [2]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list2.size(); ++i){
    	cout << " " << array2[i];
    }
    cout << " Expected: 5 6" << endl;

    int element4 = list2.remove(0);
    array2 = list2.toArray();

    cout << "After Remove Called: " << endl;
    cout << "\tSize: "  << list2.size() << " Expected: [1]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list2.size(); ++i){
    	cout << " " << array2[i];
    }
    cout << " Expected: 6" << endl;

    cout << "Element Removed: " << element4;
    cout << " Expected: 5" << endl;

    cout << endl << "Testing remove method(Valid Index on non-empty list(tail)):" << endl;

    array3 = list3.toArray();

    cout << "Before Remove Called: " << endl;
    cout << "\tSize: "  << list3.size() << " Expected: [3]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list3.size(); ++i){
    	cout << " " << array3[i];
    }
    cout << " Expected: 5 6 3" << endl;

    int element5 = list3.remove(2);
    array3 = list3.toArray();

    cout << "After Remove Called: " << endl;
    cout << "\tSize: "  << list3.size() << " Expected: [2]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list3.size(); ++i){
    	cout << " " << array3[i];
    }
    cout << " Expected: 5 6" << endl;

    cout << "Element Removed: " << element5;
    cout << " Expected: 3" << endl;


    cout << endl << "Testing remove method(Valid Index on non-empty list(middle)):" << endl;

    list3.add(4);

    array3 = list3.toArray();

    cout << "Before Remove Called: " << endl;
    cout << "\tSize: "  << list3.size() << " Expected: [3]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list3.size(); ++i){
    	cout << " " << array3[i];
    }
    cout << " Expected: 5 6 4" << endl;

    int element6 = list3.remove(1);
    array3 = list3.toArray();

    cout << "After Remove Called: " << endl;
    cout << "\tSize: "  << list3.size() << " Expected: [2]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list3.size(); ++i){
    	cout << " " << array3[i];
    }
    cout << " Expected: 5 4" << endl;

    cout << "Element Removed: " << element6;
    cout << " Expected: 6" << endl;

    cout << endl << "Testing remove method(Valid Index on non-empty list(single element)):" << endl;

    array2 = list2.toArray();

    cout << "Before Remove Called: " << endl;
    cout << "\tSize: "  << list2.size() << " Expected: [1]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list2.size(); ++i){
    	cout << " " << array2[i];
    }
    cout << " Expected: 6" << endl;

    int element7 = list2.remove(0);
    array2 = list2.toArray();

    cout << "After Remove Called: " << endl;
    cout << "\tSize: "  << list2.size() << " Expected: [0]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list2.size(); ++i){
    	cout << " " << array2[i];
    }
    cout << " Expected: " << endl;

    cout << "Element Removed: " << element7;
    cout << " Expected: 6" << endl;

    cout << endl << "Testing remove method(Invalid Index on non-empty list(negative index)):" << endl;

    try{

    	int element = list2.remove(-1);
    }
    catch(const exception& error){

    	cout << error.what() << endl;
    }

    cout << endl << "Testing remove method(Invalid Index on non-empty list(index too large)):" << endl;

    try{

    	int element = list1.remove(0);
    }
    catch(const exception& error){

    	cout << error.what() << endl;
    }

    cout << "+++++++++++++++++++++++++++++++++++++++++++++++++" << endl;

    cout << endl << "Testing += (Add element to non-empty list):" << endl;

    array3 = list3.toArray();

    cout << "Before +=: " << endl;
    cout << "\tSize: "  << list3.size() << " Expected: [2]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list3.size(); ++i){
    	cout << " " << array3[i];
    }
    cout << " Expected: 5 6" << endl;

    list3 += 4;
    array3 = list3.toArray();

    cout << "After +=: " << endl;
    cout << "\tSize: "  << list3.size() << " Expected: [3]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list3.size(); ++i){
    	cout << " " << array3[i];
    }
    cout << " Expected: 5 4 4" << endl;

    cout << endl << "Testing += (Add element to empty list):" << endl;

    array1 = list1.toArray();

    cout << "Before +=: " << endl;
    cout << "\tSize: "  << list1.size() << " Expected: [0]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list1.size(); ++i){
    	cout << " " << array1[i];
    }
    cout << " Expected: " << endl;

    list1 += 10;
    array1 = list1.toArray();

    cout << "After +=: " << endl;
    cout << "\tSize: "  << list1.size() << " Expected: [1]" << endl;
    cout << "\tElements: ";

    for(int i = 0; i < list1.size(); ++i){
    	cout << " " << array1[i];
    }
    cout << " Expected: 10" << endl;

	return 0;
}