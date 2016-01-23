package fr.licornesduswag.hcode.utils;

public class StringSeparator {
	
	  public static void main(String[] args) {
		  System.out.println(StringSeparator.separeString("yolowolofdks,fklq,klq,slkf,klqs,fk,qld,fklq,fkl,qkl,f,qls,fkl,qkfl,kl,flk,klq,kgnknqklnqkfngj,q,gsklf,lknsqkgn,qeùqg,".replace(',', ' '),20));
		  
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
        
		String toSend = new String();
		int start = 0;
        int stop = 0;
        int prevStop = 0;
        
        while (stop < chars.length) {
            // Tant qu'on a pas un espace on avance
            while (stop < chars.length && chars[stop] != ' ') {
                stop++;
            }
            
            // Si la taille est suffisemment grande
            if (stop - start > taille) {
                String line = new String(chars, start, prevStop - start);
                toSend += line + '\n';
                start = prevStop;
            } else {
                // Sinon on avance
                stop++;
            }
            
            prevStop = stop;
        }
        
        if (stop-start > 0) {
            toSend += new String(chars, start, stop-start-1);
        }

		return toSend;
	}
}
