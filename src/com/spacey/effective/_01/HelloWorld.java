package com.spacey.effective._01;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String args[]) throws IOException {
/*        // create an array of string objs
        String initList[] = { "One", "Two", "Four", "One",};

        // create one list
        List list = Arrays.asList(initList);

        System.out.println("List value before: "+list);

        // create singleton list
        list = Collections.singletonList("OnlyOneElement");
        list.set(0, "sadsa");
//        list.add("five"); //throws UnsupportedOperationException
        System.out.println("List value after: "+list);*/
    	
    	
    	try(Scanner scanner = new Scanner(new File("C:\\Users\\OrionIndia-PC13\\Downloads\\list.txt"))){
    		while(scanner.hasNextLine()) {
    			String fName = scanner.nextLine();
    			StringBuilder sb = new StringBuilder();
    			try(FileWriter fw = new FileWriter("C:\\Users\\OrionIndia-PC13\\Downloads\\somil\\" + fName + ".js")){
    				fw.write("// import React from 'react';\r\n" + 
    						"// import { ReactComponent as UserIcon } from '../../media/apps/user.svg';\r\n" + 
    						"\r\n" + 
    						"const get" + fName.replace(fName, fName.substring(0, 1).toUpperCase() + fName.substring(1)) + "Object = (typeCd, platform, typeNm, className) => {\r\n" + 
    						"  console.log(typeCd, platform, typeNm, className);\r\n" + 
    						"  switch (typeCd) {\r\n" + 
    						"    default:\r\n" + 
    						"      return null;\r\n" + 
    						"  }\r\n" + 
    						"};\r\n" + 
    						"export default get" + fName.replace(fName, fName.substring(0, 1).toUpperCase() + fName.substring(1)) + "Object;");
    				sb.append("import get" + fName.replace(fName, fName.substring(0, 1).toUpperCase() + fName.substring(1)) + "Object from " + "'./" + fName + "'");
    			}
    			System.out.println(sb.toString());
    		}
    		
    		
    		
    	}
    }
}