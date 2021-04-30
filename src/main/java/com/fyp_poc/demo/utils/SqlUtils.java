package com.fyp_poc.demo.utils;

import java.util.List;

public class SqlUtils {
   public static String generateInArgument(List<String> arguments){
       String res = arguments.stream().map(x->"'"+x+"',").reduce("",(y,z)->y+z);
       res="("+res.substring(0,res.length()-1)+")";
       return res;
   }
}
