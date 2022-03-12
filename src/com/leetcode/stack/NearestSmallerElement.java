package com.leetcode.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 *  Author : Rohit Singh
 *  Date : 12 March 2022
 */
public class NearestSmallerElement {
    /**
     *    Inputs :
     *    ----------------
     *    1) testCase
     *    2) followed by Array space separated
     *
     *    Outputs :
     *    ----------------
     *    1) single line containing values space separated for each Array.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            String[] arr = br.readLine().split(" ");
            Stack<Integer> stack = new Stack<>();
            Integer[] ans = new Integer[arr.length];

            // Main logic goes here.
            for(int i = 0; i < arr.length; i++) {
                while(!stack.empty() && Integer.parseInt(arr[i]) <= stack.peek())
                    stack.pop();

                if(stack.empty())
                    ans[i] = -1;
                else
                    ans[i] = stack.peek();

                stack.push(Integer.parseInt(arr[i]));
            }

            printArray(ans);

        }
    }

    /**
     * This method print the Arrays value space separated in one line.
     * @param arr
     */
    public static void printArray(Integer[] arr) {
        for(var val : arr) System.out.print(val + " ");
        System.out.println();
    }

}
