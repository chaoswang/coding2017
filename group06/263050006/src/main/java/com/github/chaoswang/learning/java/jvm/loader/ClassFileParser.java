package com.github.chaoswang.learning.java.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.github.chaoswang.learning.java.jvm.clz.AccessFlag;
import com.github.chaoswang.learning.java.jvm.clz.ClassFile;
import com.github.chaoswang.learning.java.jvm.clz.ClassIndex;
import com.github.chaoswang.learning.java.jvm.constant.ClassInfo;
import com.github.chaoswang.learning.java.jvm.constant.ConstantPool;
import com.github.chaoswang.learning.java.jvm.constant.FieldRefInfo;
import com.github.chaoswang.learning.java.jvm.constant.MethodRefInfo;
import com.github.chaoswang.learning.java.jvm.constant.NameAndTypeInfo;
import com.github.chaoswang.learning.java.jvm.constant.NullConstantInfo;
import com.github.chaoswang.learning.java.jvm.constant.StringInfo;
import com.github.chaoswang.learning.java.jvm.constant.UTF8Info;
import com.github.chaoswang.learning.java.jvm.field.Field;
import com.github.chaoswang.learning.java.jvm.method.Method;

public class ClassFileParser {
	
	
	public ClassFile parse(byte[] codes) {
		ByteCodeIterator codeIterator = new ByteCodeIterator(codes);
		ClassFile classFile = new ClassFile();
		//跳过魔数
		codeIterator.getBytes(4);
		classFile.setMinorVersion(codeIterator.nextU2ToInt());
		classFile.setMajorVersion(codeIterator.nextU2ToInt());
		//跳过常量池计数器
		codeIterator.getBytes(2);
		//解析常量池
		classFile.setConstPool(parseConstantPool(codeIterator));
		classFile.setAccessFlag(parseAccessFlag(codeIterator));
		classFile.setClassIndex(parseClassIndex(codeIterator));
		
		parseInterfaces(codeIterator);
		parseFileds(classFile, codeIterator);
		parseMethods(classFile, codeIterator);
		
		return classFile;
	}

	
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2ToInt());
		classIndex.setSuperClassIndex(iter.nextU2ToInt());
		return classIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());//占个位置，因为常量池编号从1开始
		
		parseClassInfo(iter, pool);//#1
		parseUTF8Info(iter, pool);
		parseClassInfo(iter, pool);
		for(int i=0;i<8;i++){
			parseUTF8Info(iter, pool);
		}
		parseMethodRefInfo(iter, pool);//#12
		parseNameAndTypeInfo(iter, pool);
		parseUTF8Info(iter, pool);
		parseFieldRefInfo(iter, pool);
		parseNameAndTypeInfo(iter, pool);
		parseFieldRefInfo(iter, pool);
		parseNameAndTypeInfo(iter, pool);
		for(int i=0;i<9;i++){
			parseUTF8Info(iter, pool);
		}
		parseFieldRefInfo(iter, pool);//#28
		parseClassInfo(iter, pool);
		parseUTF8Info(iter, pool);
		parseNameAndTypeInfo(iter, pool);//#31
		for(int i=0;i<2;i++){
			parseUTF8Info(iter, pool);
		}
		parseStringInfo(iter, pool);//#34
		parseUTF8Info(iter, pool);
		parseMethodRefInfo(iter, pool);
		parseClassInfo(iter, pool);
		parseUTF8Info(iter, pool);
		parseNameAndTypeInfo(iter, pool);//#39
		for(int i=0;i<3;i++){
			parseUTF8Info(iter, pool);
		}
		parseStringInfo(iter, pool);//#43
		parseUTF8Info(iter, pool);
		parseMethodRefInfo(iter, pool);
		parseNameAndTypeInfo(iter, pool);
		parseMethodRefInfo(iter, pool);
		parseNameAndTypeInfo(iter, pool);//#48
		for(int i=0;i<5;i++){
			parseUTF8Info(iter, pool);
		}
		
		return pool;
	}

	private void parseStringInfo(ByteCodeIterator iter, ConstantPool pool) {
		StringInfo stringInfo = new StringInfo(pool);
		//tag值
		iter.nextU1toInt();
		//string_index
		stringInfo.setIndex(iter.nextU2ToInt());
		pool.addConstantInfo(stringInfo);
	}


	private void parseFieldRefInfo(ByteCodeIterator iter, ConstantPool pool) {
		FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
		//tag值
		iter.nextU1toInt();
		//class_index
		fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
		//name_and_type_index
		fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
		pool.addConstantInfo(fieldRefInfo);
	}


	private void parseNameAndTypeInfo(ByteCodeIterator iter, ConstantPool pool) {
		NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
		//tag值
		iter.nextU1toInt();
		//name_index
		nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
		//descriptor_index
		nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
		pool.addConstantInfo(nameAndTypeInfo);
	}


	private void parseMethodRefInfo(ByteCodeIterator iter, ConstantPool pool) {
		MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
		//tag值
		iter.nextU1toInt();
		//class_index
		methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
		//name_and_type_index
		methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
		pool.addConstantInfo(methodRefInfo);
	}


	private void parseUTF8Info(ByteCodeIterator iter, ConstantPool pool) {
		UTF8Info utf8Info = new UTF8Info(pool);
		//UTF8Info tag值
		iter.nextU1toInt();
		//UTF8Info length值
		int length = iter.nextU2ToInt();
		utf8Info.setLength(length);
		byte[] byteArray = iter.getBytes(length);
		String value = null;
		try {
			value = new String(byteArray, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		utf8Info.setValue(value);
		pool.addConstantInfo(utf8Info);
	}

	private void parseClassInfo(ByteCodeIterator iter, ConstantPool pool) {
		ClassInfo classInfo = new ClassInfo(pool);
		//classInfo tag值
		iter.nextU1toInt();
		classInfo.setUtf8Index(iter.nextU2ToInt());
		pool.addConstantInfo(classInfo);
	}
	
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}

	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
		int fieldCount = iter.nextU2ToInt();
		
		for (int i = 1; i <= fieldCount; i++) {
			Field f = Field.parse(clzFile.getConstantPool(), iter);
			clzFile.addField(f);			
		}

	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {

		int methodCount = iter.nextU2ToInt();

		for (int i = 1; i <= methodCount; i++) {
			Method m = Method.parse(clzFile, iter);
			clzFile.addMethod(m);
		}

	}

}