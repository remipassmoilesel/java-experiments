package org.prog2.streams;

import java.io.Serializable;

import org.omg.CORBA.PERSIST_STORE;

public class SerializablePerson implements Serializable {

	private static final long serialVersionUID = 6670648477745491628L;
	private int age;
	private String name;

	public SerializablePerson() {
		this.age = 0;
		this.name = "John doe";
	}

	public SerializablePerson(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [" + name + ", " + age + "]";
	}
}
