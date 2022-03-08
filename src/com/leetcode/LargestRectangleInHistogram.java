package com.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sizeOfArray = Integer.parseInt(br.readLine());

        Integer[] histogram = new Integer[sizeOfArray];

        int i = 0;

        for(String val : br.readLine().split(" ")) {
            histogram[i++] = Integer.parseInt(val);
        }

        Integer[] leftrSmaller = new Integer[sizeOfArray];

        Integer[] rightSmaller = new Integer[sizeOfArray];

        Stack<Integer> stack = new Stack<>();

        for(i=0;i<sizeOfArray;i++) {
            while(!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                leftrSmaller[i] = -1;
            } else {
                leftrSmaller[i] = stack.peek();
            }
            stack.push(i);
        }

        for(i = sizeOfArray-1 ; i>=0 ; i--) {
            while(!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                rightSmaller[i] = sizeOfArray;
            } else {
                rightSmaller[i] = stack.peek();
            }
            stack.push(i);
        }

        Arrays.stream(leftrSmaller).forEach(System.out::println);
        System.out.println("========================########=========================");
        Arrays.stream(rightSmaller).forEach(System.out::println);

        Integer maxRectangleArea = 0;

        for(i=0;i<sizeOfArray;i++) {
            int curr = (rightSmaller[i] - leftrSmaller[i] - 1) * histogram[i];
            maxRectangleArea = Math.max(curr,maxRectangleArea);
        }

        System.out.println(maxRectangleArea);
    }
}
