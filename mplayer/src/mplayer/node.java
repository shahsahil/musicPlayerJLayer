package mplayer;

class node {
	node next, prev;
	String data;

	void setData(String v) {
		data = v;
	}

	void setNext(node n) {
		next = n;
	}
	void setPrev(node n) {
		prev = n;
	}

	String getData() {
		return data;
	}

	node getNext() {
		return next;
	}
	node getPrev() {
		return prev;
	}
}
