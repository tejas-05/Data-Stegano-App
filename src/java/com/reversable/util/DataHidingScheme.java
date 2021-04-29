/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ramu Maloth
 */
public class DataHidingScheme {
	public static final String VERSION= "2.0.0";
	public static final byte[] VERSION_BYTE= {'2','0','0'};
	public static final int OFFSET_JPG= 3;
	public static final int OFFSET_PNG= 42;
	public static final int OFFSET_GIF_BMP_TIF= 32;
	public static final short HEADER_LENGTH= 15* 4;
	
         //  Three letters indicate:
	//  Uncompressed/Compressed  Encrypted/Unencrypted  Message/File
	public static final byte UUM= 0;
	public static final byte UUF= 1;
	public static final byte UEM= 2;
	public static final byte UEF= 3;
	public static final byte CUM= 4;
	public static final byte CUF= 5;
	public static final byte CEM= 6;
	public static final byte CEF= 7;

	private static Cipher cipher;

	private static SecretKeySpec spec;
	private static String masterExtension, message;
	//private static AboutFrame about= new AboutFrame();

	private static File masterFile;
	
         // This byte stores the features being used by the file
	
        private static byte features;
	private static int inputFileSize;
	private static int i, j, inputOutputMarker, messageSize, tempInt;
	private static short compressionRatio= 0, temp;
	private static byte byte1, byte2, byte3, byteArrayIn[];
	private static ByteArrayOutputStream byteOut;

	public DataHidingScheme()
	{
		System.out.println(" "+" ready...");
	}

	public static String getMessage()
	{
		return message;
	}

	// Embeds a message into a Master file
	public static boolean embedMessage(File masterFile, File outputFile, String msg, int compression, String password)
	{
		if(msg==null)
		{
			message= "Message is empty";
			return false;
		}
		if(msg.length()<1)
		{
			message= "Message is empty";
			return false;
		}

		if(password!= null && password.length()<8)
		{
			message= "Password should be minimum of 8 Characters";
			return false;
		}

		messageSize= msg.length();
		
		if(compression!= -1)
		{
			// Make sure that the compression is a valid numerical between 0 and 9
			if(compression< 0)		compression= 0;
			if(compression>9)		compression= 9;

			if(password== null)	features= CUM;
			else					features= CEM;
		}
		else
		{
			if(password== null)	features= UUM;
			else					features= UEM;
		}

		try
		{
			byteOut= new ByteArrayOutputStream();
			// Convert message into a character array
			byte []messageArray= msg.getBytes();
			messageSize= messageArray.length;
			inputFileSize= (int) masterFile.length();

			// create a byte array of length equal to size of input file
			byteArrayIn= new byte[inputFileSize];

			// Open the input file read all bytes into byteArrayIn
			DataInputStream in= new DataInputStream(new FileInputStream(masterFile));
			in.read(byteArrayIn, 0, inputFileSize);
			in.close();

			String fileName= masterFile.getName();
			masterExtension= fileName.substring(fileName.length()-3, fileName.length());

			if(masterExtension.equalsIgnoreCase("jpg"))
			{
				// Skip past OFFSET_JPG bytes
				byteOut.write(byteArrayIn, 0, OFFSET_JPG);
				inputOutputMarker= OFFSET_JPG;
			}
			else if(masterExtension.equalsIgnoreCase("png"))
					{
						// Skip past OFFSET_PNG bytes
						byteOut.write(byteArrayIn, 0, OFFSET_PNG);
						inputOutputMarker= OFFSET_PNG;
					}
				  else
					{
						// Skip past OFFSET_JPG_BMP_TIF bytes
						byteOut.write(byteArrayIn, 0, OFFSET_GIF_BMP_TIF);
						inputOutputMarker= OFFSET_GIF_BMP_TIF;
					}

			
                    // Convert the 32 bit input file size into byte array
			
                        byte tempByte[]= new byte[4];
			for(i=24, j=0; i>=0; i-=8, j++)
			{
				tempInt= inputFileSize;
				tempInt>>= i;
				tempInt&= 0x000000FF;
				tempByte[j]= (byte) tempInt;
			}
			// Embed 4 byte input File size array into the master file
			embedBytes(tempByte);

			// Write the remaining bytes
			byteOut.write(byteArrayIn, inputOutputMarker, inputFileSize- inputOutputMarker);
			inputOutputMarker= inputFileSize;

			// Embed the 3 byte version information into the file
			writeBytes(VERSION_BYTE);

			// Write 1 byte for features
			writeBytes(new byte[]{features});
		
			// Compress the message if required
			if(features== CUM || features== CEM)
			{
				ByteArrayOutputStream arrayOutputStream= new ByteArrayOutputStream();
				ZipOutputStream zOut= new ZipOutputStream(arrayOutputStream);
				ZipEntry entry= new ZipEntry("MESSAGE");
				zOut.setLevel(compression);
				zOut.putNextEntry(entry);

				zOut.write(messageArray, 0, messageSize);
				zOut.closeEntry();
				zOut.finish();
				zOut.close();
				
				// Get the compressed message byte array
				messageArray= arrayOutputStream.toByteArray();
				compressionRatio= (short) ((double)messageArray.length / (double)messageSize * 100.0);
				messageSize= messageArray.length;
			}

			// Embed 1 byte compression ratio into the output file
			writeBytes(new byte[]{(byte) compressionRatio});


			// Encrypt the message if required
			if(features== UEM || features== CEM)
			{
				Cipher cipher= Cipher.getInstance("DES");
				SecretKeySpec spec= new SecretKeySpec(password.substring(0, 8).getBytes(), "DES");
				cipher.init(Cipher.ENCRYPT_MODE, spec);
				messageArray= cipher.doFinal(messageArray);
				messageSize= messageArray.length;
			}

			// Convert the 32 bit message size into byte array
			tempByte= new byte[4];
			for(i=24, j=0; i>=0; i-=8, j++)
			{
				tempInt= messageSize;
				tempInt>>= i;
				tempInt&= 0x000000FF;
				tempByte[j]= (byte) tempInt;
			}
			// Embed 4 byte messageSize array into the master file
			writeBytes(tempByte);

			// Embed the message
			writeBytes(messageArray);

			DataOutputStream out= new DataOutputStream(new FileOutputStream(outputFile));
			//out.write(byteArrayOut, 0, byteArrayOut.length);
			byteOut.writeTo(out);
			out.close();
		}
		catch(EOFException e)
		{
		}
		catch(Exception e)
		{
			message= "Oops!!\nError: "+ e.toString();
			e.printStackTrace();
			return false;
		}

		message= "Message embedded successfully in file '"+ outputFile.getName()+ "'.";
		return true;
	}
	
	// Retrieves an embedded message from a Master file
	public static String retrieveMessage(MessageImformation info, String password)
	{
		String messg= null;
		features= info.getFeatures();

		try
		{
			masterFile= info.getFile();
			byteArrayIn= new byte[(int) masterFile.length()];

			DataInputStream in= new DataInputStream(new FileInputStream(masterFile));
			in.read(byteArrayIn, 0, (int)masterFile.length());
			in.close();

			messageSize= info.getDataLength();

			if(messageSize<=0)
			{
				message= "Unexpected size of message: 0.";
				return("#FAILED#");
			}

			byte[] messageArray= new byte[messageSize];
			inputOutputMarker= info.getInputMarker();
			readBytes(messageArray);

			//Decrypt the message if required
			if(features== CEM || features== UEM)
			{
				password= password.substring(0, 8);
				byte passwordBytes[]= password.getBytes();				
				cipher= Cipher.getInstance("DES");
				spec= new SecretKeySpec(passwordBytes, "DES");
				cipher.init(Cipher.DECRYPT_MODE, spec);
				try
				{
					messageArray= cipher.doFinal(messageArray);
				}
				catch(Exception bp)
				{
					message= "Incorrent Password";
					bp.printStackTrace();
					return "#FAILED#";
				}
				messageSize= messageArray.length;
			}

			// Uncompress the message if required
			if(features== CUM || features== CEM)
			{
				ByteArrayOutputStream by= new ByteArrayOutputStream();
				DataOutputStream out= new DataOutputStream(by);

				ZipInputStream zipIn= new ZipInputStream(new ByteArrayInputStream(messageArray));
				zipIn.getNextEntry();
				byteArrayIn= new byte[1024];
				while((tempInt= zipIn.read(byteArrayIn, 0, 1024))!= -1)
					out.write(byteArrayIn, 0, tempInt);

				zipIn.close();
				out.close();
				messageArray= by.toByteArray();
				messageSize= messageArray.length;
			}

			messg= new String(MessageImformation.byteToCharArray(messageArray));
		}
		catch(Exception e)
		{
			message= "Oops!!\n Error: "+ e;
			e.printStackTrace();
			return("#FAILED#");
		}

		message= "Message size: "+ messageSize+ " B";
		return messg;
	}

	// Method used to write bytes into the output array
	private static void embedBytes(byte[] bytes)
	{
		int size= bytes.length;

		for(int i=0; i< size; i++)
		{
			byte1= bytes[i];
			for(int j=6; j>=0; j-=2)
			{					
				byte2= byte1;
				byte2>>= j;
				byte2&= 0x03;

				byte3= byteArrayIn[inputOutputMarker];
				byte3&= 0xFC;
				byte3|= byte2;
				byteOut.write(byte3);
				inputOutputMarker++;
			}
		}
	}

	// Method used to write bytes into the output array
	private static void writeBytes(byte[] bytes)
	{
		int size= bytes.length;

		for(int i=0; i< size; i++)
		{
			byteOut.write(bytes[i]);
			inputOutputMarker++;
		}
	}

	// Method used to retrieve bytes into the output array
	private static void retrieveBytes(byte[] bytes)
	{
		int size= bytes.length;

		for(int i=0; i< size; i++)
		{
			byte1= 0;
			for(int j=6; j>=0; j-=2)
			{					
				byte2= byteArrayIn[inputOutputMarker];
				inputOutputMarker++;

				byte2&= 0x03;
				byte2<<= j;
				byte1|= byte2;
			}
			bytes[i]= byte1;
		}
	}

	// Method used to read bytes into the output array
	private static void readBytes(byte[] bytes)
	{
		int size= bytes.length;

		for(int i=0; i< size; i++)
		{
			bytes[i]= byteArrayIn[inputOutputMarker];
			inputOutputMarker++;
		}
	}

	public static void showAboutDialog()
	{
		//about.setDisplayed(true);
	}

	public static void updateUserInterface()
	{
		//SwingUtilities.updateComponentTreeUI(about);
	}

	public static void showHelpDialog()
	{
		//new HTMLFrame("file:"+ System.getProperty("user.dir") +	System.getProperty("file.separator") + "help.html", false);
        }
}
