/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

/**
 *
 * @author shashank-sapphire-coder
 */

public class GetThisExecuted {
       public printOutput getStreamWrapper(InputStream is, String type, JTextArea myArea) {
		return new printOutput(is, type, myArea);
	}
      
       
       void getMyCommandExecuted (String givenCommand, JTextArea givenArea) {
           Runtime rt = Runtime.getRuntime();
           GetThisExecuted rte = new GetThisExecuted();
           printOutput errorReported, outputMessage;
           
 
		try {
			Process proc = rt.exec(givenCommand);
			// Process proc = rt.exec("mkdir /Users/<username>/Desktop/test1");
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR", givenArea);
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT", givenArea);
			errorReported.start();
			outputMessage.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
       }
}

class printOutput extends Thread {
		InputStream is = null;
                JTextArea myArea;
                
		printOutput(InputStream is, String type, JTextArea myArea) {
			this.is = is;
                        this.myArea = myArea;
		}
 
		public void run() {
			String s = null;
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				while ((s = br.readLine()) != null) {
					System.out.println(s);
                                        myArea.append(s+"\n");
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
}
