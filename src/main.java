import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class main {

	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String chaine="";
		String fichier ="Log-clients-themes.txt";
		
	    ArrayList theme = new ArrayList();
	    ArrayList client = new ArrayList();

	    File MyFile = new File("Clients.txt"); 
	    MyFile.delete(); 
	    File MyFile2 = new File("Themes.txt"); 
	    MyFile2.delete();
	    File MyFile3 = new File("matrice.txt"); 
	    MyFile3.delete();
	    File MyFile4 = new File("Matrice-Produit-Produit.txt"); 
	    MyFile4.delete();
	    File MyFile5 = new File("Matrice-User-User.txt"); 
	    MyFile5.delete();
		//lecture du fichier texte	
		try{

			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//System.out.println(ligne);
				String str[]=ligne.split(";");
				client.add(str[1]);
				theme.add(str[2]);

				chaine+=ligne+"\n";

			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		ArrayList listclient = CreationListeClients(client);
		ArrayList listtheme =CreationListeThemes(theme);
		System.out.println(listclient.size());

		int[][] matrice = CreationMatrice(listclient,listtheme);
		int[][] trans_arr = new int[listtheme.size()][listclient.size()];
        for (int i = 0; i < listclient.size(); i++) {
            for (int j = 0; j < listtheme.size(); j++) {
                trans_arr[j][i] = matrice[i][j];
            }
        }
		CreationMatriceUserUser(matrice,listclient);
		CreationMatriceProduitProduit(trans_arr,listtheme);
	}

	public static void CreationMatriceProduitProduit(int[][] matrice,ArrayList listtheme) throws IOException {
		String fichier ="Matrice-Produit-Produit.txt";
		int[][] matricePP = new int[listtheme.size()] [listtheme.size()];
		int res=0;
		for(int i =0;i<listtheme.size();i++)
		{
			for(int j = 0;j<listtheme.size();j++)
			{
				int []lp1 = matrice[i];
				int []lp2 = matrice[j];
				res = CalculPP(lp1,lp2);
				matricePP[i][j]=res;
				
				
			}
		}
		
		FileWriter fw = new FileWriter(fichier, true);
		
		BufferedWriter output = new BufferedWriter(fw);
		for(int i = 0; i < listtheme.size(); i++)
	    {
			for(int j = 0; j < listtheme.size(); j++)
		    {
				
				
				String c = matricePP[i][j]+ " ";
				
				output.write(c);
				
				
		    
		    }
			output.write("\n");
			
			
	    }
		output.close();
	}
	
	public static void CreationMatriceUserUser(int[][] matrice,ArrayList listclient) throws IOException {
		String fichier ="Matrice-User-User.txt";
		int[][] matricePP = new int[listclient.size()] [listclient.size()];
		int res=0;
		for(int i =0;i<listclient.size();i++)
		{
			for(int j = 0;j<listclient.size();j++)
			{
				int []lp1 = matrice[i];
				int []lp2 = matrice[j];
				res = CalculPP(lp1,lp2);
				matricePP[i][j]=res;
				
				
			}
		}
		
		FileWriter fw = new FileWriter(fichier, true);
		
		BufferedWriter output = new BufferedWriter(fw);
		for(int i = 0; i < listclient.size(); i++)
	    {
			for(int j = 0; j < listclient.size(); j++)
		    {
				
				
				String c = matricePP[i][j]+ " ";
				
				output.write(c);
				
				
		    
		    }
			output.write("\n");
			
			
	    }
		output.close();
	}
	
	public static int CalculPP(int []lp1,int []lp2)
	{
		int res=0;
		for(int i=0;i<lp1.length;i++) { 
		    res += lp1[i]; 
		} 
		for(int i=0;i<lp2.length;i++) { 
		    res += lp2[i]; 
		} 
		return res;
	}
	
	public static int[][] CreationMatrice(ArrayList listclient,ArrayList listtheme) throws IOException {
		
		String fichier ="Log-clients-themes.txt";
		int[][] matrice = new int[listclient.size()] [listtheme.size()];
		try{

			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//System.out.println(ligne);
				String str[]=ligne.split(";");
				int c = listclient.indexOf(str[1]);
				int t = listtheme.indexOf(str[2]);
				matrice[c][t] ++;

			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		FileWriter fw = new FileWriter("matrice.txt", true);
		
		BufferedWriter output = new BufferedWriter(fw);
		for(int i = 0; i < listclient.size(); i++)
	    {
			for(int j = 0; j < listtheme.size(); j++)
		    {
				
				
				String c = matrice[i][j]+ " ";
				
				output.write(c);
				
				
		    
		    }
			output.write("\n");
			
			
	    }
		output.close();
		return matrice;
	}


	public static ArrayList CreationListeClients(ArrayList client) throws IOException
	{	
		String fichier ="Clients.txt";
		ArrayList tmp = new ArrayList();
		ArrayList lc = new ArrayList();

		for(int i = 0; i < client.size(); i++)
	    {
			if(!tmp.contains(client.get(i)))
		    {
				FileWriter fw = new FileWriter(fichier, true);
				
				BufferedWriter output = new BufferedWriter(fw);
				String c = client.get(i)+ "\n";
				output.write(c);
				
				output.flush();
				
				output.close();
			    lc.add(client.get(i));

		    }
		    tmp.add(client.get(i));

	    }
		return lc;
	}
	
	public static ArrayList CreationListeThemes(ArrayList theme) throws IOException
	{	
		String fichier ="Themes.txt";
		ArrayList tmp = new ArrayList();
		ArrayList lt = new ArrayList();

		for(int i = 0; i < theme.size(); i++)
	    {
			
		    if(!tmp.contains(theme.get(i)))
		    {
				FileWriter fw = new FileWriter(fichier, true);
				
				BufferedWriter output = new BufferedWriter(fw);
				String c = theme.get(i)+ "\n";
				output.write(c);
				
				output.flush();
				
				output.close();
			    lt.add(theme.get(i));

		    }
	    tmp.add(theme.get(i));
	    }
		return lt;
}
}
