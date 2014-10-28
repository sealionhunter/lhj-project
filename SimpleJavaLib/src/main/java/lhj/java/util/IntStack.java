package lhj.java.util;

public class IntStack extends IntList {
	public IntStack() {
	}

	public void push(int value) {
		add(value);
	}

	public int pop() {
		if (isEmpty())
			throw new java.util.EmptyStackException();
		return remove(size() - 1);
	}

	public int top() {
		if (isEmpty())
			throw new java.util.EmptyStackException();
		return get(size() - 1);
	}

	public void resetTop(int value) {
		if (!isEmpty())
			set(size() - 1, value);
	}
}
