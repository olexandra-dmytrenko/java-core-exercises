package bytecode;

/**
 * Run javac to compile
 * javac /Users/olexandra/IdeaProjects/java-core-exercises/bytecode/src/main/java/bytecode/TestClass.java
 *
 * Run javap to see bytecode
 * javap -v /Users/olexandra/IdeaProjects/java-core-exercises/bytecode/src/main/java/bytecode/TestClass.class
 *
 * Classfile /Users/olexandra/IdeaProjects/java-core-exercises/bytecode/src/main/java/bytecode/TestClass.class
 *   Last modified Mar 27, 2019; size 252 bytes
 *   MD5 checksum 7095dc2de0cb2c4275e9b7c01480149c
 *   Compiled from "TestClass.java"
 * public class bytecode.TestClass
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #3.#12         // java/lang/Object."<init>":()V
 *    #2 = Class              #13            // bytecode/TestClass
 *    #3 = Class              #14            // java/lang/Object
 *    #4 = Utf8               <init>
 *    #5 = Utf8               ()V
 *    #6 = Utf8               Code
 *    #7 = Utf8               LineNumberTable
 *    #8 = Utf8               m1
 *    #9 = Utf8               ()I
 *   #10 = Utf8               SourceFile
 *   #11 = Utf8               TestClass.java
 *   #12 = NameAndType        #4:#5          // "<init>":()V
 *   #13 = Utf8               bytecode/TestClass
 *   #14 = Utf8               java/lang/Object
 * {
 *   public bytecode.TestClass();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 3: 0
 *
 *   public int m1();
 *     descriptor: ()I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: iconst_1
 *          1: ireturn
 *       LineNumberTable:
 *         line 6: 0
 * }
 */

public class TestClass {

    public int m1() {
        return 1;
    }
    /*Function<String, Integer> len = str -> str.length();

    int m(){
        return len.apply("ds");
    }*/
    //Node head;

//     class Node {
//        int val;
//    }
}
/*

enum Month {
    JAN(10);

    Month(int i) {

    }
}*/
