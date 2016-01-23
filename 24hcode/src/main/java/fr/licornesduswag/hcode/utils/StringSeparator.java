package fr.licornesduswag.hcode.utils;

public class StringSeparator {
	
	  public static void main(String[] args) {
		  StringSeparator.separeString("yolowolofdks,fklq,klq,slkf,klqs,fk,qld,fklq,fkl,qkl,f,qls,fkl,qkfl,kl,flk,klq,kgnknqklnqkfngj,q,gsklf,lknsqkgn,qeùqg,",5);
		  
	  }
	  /**
	   * 
	   * @param s String à séparer
	   * @param taille tailleMax dans laquelle la string est séparée
	   * @return la string avec les \n
	   */
	public static String separeString (String s, int taille)
	{
		
		char [] chars = s.toCharArray();
		String toSend= new String();
		for (int i = 0;i<chars.length;i++)
		{			
			toSend+=chars[i];
			if ((i+1)%taille==0 && i!=1)
			{				
				
				toSend+='\n';				
			}			
		}				
		System.out.println(toSend);
		return toSend;
	}
}
