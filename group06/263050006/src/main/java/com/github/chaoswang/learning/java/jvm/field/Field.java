package com.github.chaoswang.learning.java.jvm.field;

import com.github.chaoswang.learning.java.jvm.constant.ConstantPool;
import com.github.chaoswang.learning.java.jvm.constant.UTF8Info;
import com.github.chaoswang.learning.java.jvm.loader.ByteCodeIterator;

/**
 * field_info { 
 * u2 access_flags; 
 * u2 name_index; 
 * u2 descriptor_index; 
 * u2 attributes_count; 
 * attribute_info attributes[attributes_count];
 *
 */
public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	
	
	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	public String toString() {
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		return name +":"+ desc;
	}
	
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descIndex = iter.nextU2ToInt();
		int attribCount = iter.nextU2ToInt();
		//System.out.println("field attribute count:"+ attribCount);
		
		Field f = new Field(accessFlag, nameIndex, descIndex,pool);
		
		if(attribCount > 0){
			throw new RuntimeException("Field Attribute has not been implemented");
		}
		
		return f;
	}

}